


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-03

package Basics.Examples.Chapter02;

// Example 2.8 from "Java Illuminated (5th Ed.)", chapter 2, page 68.
public class Example_02_08__DivisionByZero {
	
	public static void main( String[] args ) {
      
      double result1 = 4.3 / 0.0;
      System.out.println("The value of result1 is " + result1);
      
      double result2 = 0.0 / 0.0;
      System.out.println("The value of result2 is " + result2);
      
      int result3 = 4 / 0;
      System.out.println("The value of result3 is " + result3);
      
	}
		
}


