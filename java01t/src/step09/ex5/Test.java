package step09.ex5;

// 상속받은 메서드를 서브 클래스의 역할에 맞게 재정의하는 것!
// => 다형성에서 Overriding(재정의)라고 한다.
// => 예) ScoreEx에서 Score의 print()를 재정의
//
// 컴파일 할 때 오버라이딩 메서드에 대해 문법 검사를 하도록 강제하는 방법?
// => 소스 코드에서 오버라이딩 하는 메서드에 특별한 주석을 단다.
// => 즉, 컴파일러에게 내리는 명령어!
// => "애노테이션(annotation)"
//
// 애노테이션?
// - 컴파일러나 JVM에게 전달하는 아주 특별한 주석이다.
// - 컴파일러에게 내리는 명령일 수 있고, JVM에게 내리는 명령일 수 있다.
// - 또는 실행할 때 애플리케이션에서 뽑아 볼 수 있는 정보일 수 있다.
// - 애노테이션을 통해 코드의 오류를 최소화할 수 있다. 
// - 문법: 
//   클래스, 메서드, 인스턴스 변수, 클래스 변수 앞에 "@주석이름(파라미터, ...)"를 붙인다. 
// - ScoreEx의 print()와 compute()를 참고하라!
public class Test {

  public static void main(String[] args) {
    ScoreEx s1 = new ScoreEx();
    s1.setName("홍길동");
    s1.setKor(100); // 아빠 클래스에 있는 메서드 
    s1.setEng(100); // 아빠 클래스에 있는 메서드 
    s1.setMath(100); // 아빠 클래스에 있는 메서드 
    s1.setSoc(80);
    s1.setSci(70);
    
    //오버라이딩한 print() 메서드 호출
    s1.print();
  }

}















