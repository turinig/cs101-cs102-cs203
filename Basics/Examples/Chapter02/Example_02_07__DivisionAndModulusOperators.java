


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-03

package Basics.Examples.Chapter02;

// Example 2.7 from "Java Illuminated (5th Ed.)", chapter 2, page 66.
public class Example_02_07__DivisionAndModulusOperators {
	
	public static void main( String[] args ) {
      
      final int PENNIES_PER_QUARTER = 25;
      int pennies = 113;
      
      int quarters = pennies / PENNIES_PER_QUARTER;
      System.out.println("There are " + quarters + " quarters in " + pennies + " pennies.");
      
      int penniesLeftOver = pennies % PENNIES_PER_QUARTER;
      System.out.println("There are " + penniesLeftOver + " pennies left over.");
      
      final double MONTHS_PER_YEAR = 12;
      double annualSalary = 50000.0; // USD.
      
      double monthlySalary = annualSalary / MONTHS_PER_YEAR; // USD.
      System.out.println("The monthly salary is " + monthlySalary);
      
	}
		
}


