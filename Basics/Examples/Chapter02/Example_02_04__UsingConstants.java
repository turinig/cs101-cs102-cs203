


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-03

package Basics.Examples.Chapter02;

// Example 2.4 from "Java Illuminated (5th Ed.)", chapter 2, page 57.
public class Example_02_04__UsingConstants {
	
	public static void main( String[] args ) {
      // Definition of some constants.
      final char ZORRO = 'Z';
      final double PI = 3.14159;
      final int DAYS_IN_LEAP_YEAR = 366, DAYS_IN_NON_LEAP_YEAR = 365;
      // Output the values of the constants on the console.
      System.out.println("The value of constant ZORRO is: " + ZORRO);
      System.out.println("The value of constant PI is: " + PI);
      System.out.println("The number of days in a leap year is: " + DAYS_IN_LEAP_YEAR);
      System.out.println("The number of days in a non-leap year is: " + DAYS_IN_NON_LEAP_YEAR);
      // Note: Once a constant is initialized/defined, you cannot change its value!
	}
		
}


