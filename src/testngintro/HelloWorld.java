package testngintro;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

 
  

public class HelloWorld {
	
	@BeforeTest
	public void beforeAnyTest(){
		System.out.println("This is a beforeTest method");
	}
	
	
  @Test
  public void sayHello() {
	  System.out.println("Hello World!");
  }
  
  @Test
  public void googleSearch() {
	  System.out.println("Google");
  }
}
