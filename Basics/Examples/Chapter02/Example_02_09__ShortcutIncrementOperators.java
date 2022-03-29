


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-03

package Basics.Examples.Chapter02;

// Example 2.9 from "Java Illuminated (5th Ed.)", chapter 2, page 72.
public class Example_02_09__ShortcutIncrementOperators {
	
	public static void main( String[] args ) {
      
      int a = 6;
      int b = 2;
      
      System.out.println("At the beginning, a is: " + a);
      System.out.println("Increment a with prefix shortcut operator: " + ++a);
      System.out.println("In the end, a is: " + a);
      System.out.println();
      System.out.println("At the beginning, b is: " + b);
      System.out.println("Increment b with postfix shortcut operator: " + b++);
      System.out.println("In the end, b is: " + b);
      
	}
		
}


