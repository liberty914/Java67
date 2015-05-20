package net.bitacademy.java67.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bitacademy.java67.annotation.RequestMapping;
import net.bitacademy.java67.context.ApplicationContext;

import org.reflections.ReflectionUtils;

/* 실습 목표: 메서드의 파라미터 분석하여 원하는 값을 자동으로 넣어주기
 */

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String servletPath = request.getServletPath();
      
      ApplicationContext beanContainer = ApplicationContext.getInstance();
      Object controller = beanContainer.getBean(servletPath);
      if (controller == null) {
        throw new Exception("해당 URL의 자원을 찾을 수 없습니다!");
      }
      
      // controller 객체의 클래스 정보로부터 호출할 메서드를 찾는다.
      @SuppressWarnings("unchecked")
      Set<Method> methodList = ReflectionUtils.getMethods(
          controller.getClass(), // 클래스 정보 
          ReflectionUtils.withAnnotation(RequestMapping.class) // 찾는 조건
      );
      
      // @RequestMapping 애노테이션이 붙은 메서드를 호출한다.
      String viewUrl = null;
      List<Object> paramValues = null;
      for (Method m : methodList) {
        // 메서드를 호출하기 전에 메서드의 파라미터를 분석한다.
        // 분석한 결과에 맞춰서 파라미터 값을 준비한다.
        paramValues = analyzeMethodParameter(m, request, response);
        
        // 준비한 파라미터 값을 가지고 메서드를 호출한다.
        viewUrl = (String)m.invoke(controller, paramValues.toArray());
        break;
      }
      
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
        return;
      } else {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
        rd.include(request, response);
      }
      
    } catch (Exception e) {
      RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
      request.setAttribute("error", e);
      rd.forward(request, response);
      return;
    }
  }

  private List<Object> analyzeMethodParameter(Method m,
      HttpServletRequest request, HttpServletResponse response) 
          throws Exception {
    
    // 파라미터 값을 저장할 컬렉션(Collection)을 준비한다.
    ArrayList<Object> paramValues = new ArrayList<Object>();
    
    /* m이 가리키는 메서드의 시그너처(signature)가 다음과 같다고 가정하자!
     * 예) String list(
     *          HttpServletRequest request, 
     *          int pageNo, 
     *          int pageSize, 
     *          String word,
     *          String order)
     * 
     * getParameters()의 리턴 값?
     * HttpServletRequest --> Parameter[0]
     * int                --> Parameter[1]
     * int                --> Parameter[2]
     * String             --> Parameter[3]
     * String             --> Parameter[4]
     */
    
    Parameter[] params = m.getParameters();
    Class<?> paramType = null;
    String value = null;
    for (Parameter param : params) {
      paramType = param.getType();
      
      if (paramType.isInstance(request)) {
        paramValues.add(request);
      } else if (paramType.isInstance(response)) {
        paramValues.add(response);
      } else if (paramType.isPrimitive() || paramType.equals(String.class)) {
        value = request.getParameter(param.getName());
        if (value != null) {
          switch (paramType.getSimpleName()) {
          case "boolean": 
            paramValues.add(Boolean.parseBoolean(value)); break;
          case "byte": 
            paramValues.add(Byte.parseByte(value)); break;
          case "short": 
            paramValues.add(Short.parseShort(value)); break;
          case "int": 
            paramValues.add(Integer.parseInt(value)); break;
          case "long": 
            paramValues.add(Long.parseLong(value)); break;
          case "float": 
            paramValues.add(Float.parseFloat(value)); break;
          case "double": 
            paramValues.add(Double.parseDouble(value)); break;
          case "char": 
            paramValues.add(value.charAt(0)); break;
          default: //"String" 
            paramValues.add(value);  
          }
        } else {
          switch (paramType.getSimpleName()) {
          case "boolean": paramValues.add(false); break;
          case "String": paramValues.add(null); break;
          default: //byte, char, short, int, long, float, double 
            paramValues.add(0);  
          }
        }
      }
    }
    
    return paramValues;
  }
  
}










