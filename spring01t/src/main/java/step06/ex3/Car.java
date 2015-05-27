package step06.ex3;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

/* @Autowired 애노테이션을 사용하여 의존 객체 주입 설정하기
 * - 인스턴스 변수에 붙이기 
 */
public class Car {
  String  model;
  int     capacity;
  Date    outDate;
  @Autowired Engine  engine;
  
  @Override
  public String toString() {
    return "Car [model=" + model + ", capacity=" + capacity + ", outDate="
        + outDate + ", engine=" + engine + "]";
  }

  public Car() {
    System.out.println("Car() 생성");
  }
  
  public Car(String model, int capacity) {
    System.out.println("Car(String,int) 생성");
    this.model = model;
    this.capacity = capacity;
  }
  
  public Car(int capacity, String model) {
    System.out.println("Car(int,String) 생성");
    this.model = model;
    this.capacity = capacity;
  }
  
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    System.out.println("setModel() 호출됨.");
    this.model = model;
  }
  public int getCapacity() {
    return capacity;
  }
  public void setCapacity(int capacity) {
    System.out.println("setCapacity() 호출됨.");
    this.capacity = capacity;
  }
  public Date getOutDate() {
    return outDate;
  }
  public void setOutDate(Date outDate) {
    System.out.println("setOutDate() 호출됨.");
    this.outDate = outDate;
  }
  public Engine getEngine() {
    return engine;
  }
  public void setEngine(Engine engine) {
    System.out.println("setEngine() 호출됨.");
    this.engine = engine;
  }
  
}










