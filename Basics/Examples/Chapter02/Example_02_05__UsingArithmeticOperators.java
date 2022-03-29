


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-03

package Basics.Examples.Chapter02;

// Example 2.5 from "Java Illuminated (5th Ed.)", chapter 2, page 61.
public class Example_02_05__UsingArithmeticOperators {
	
	public static void main( String[] args ) {
      
      // Calculate (and output) the cost of lunch.
      double salad = 5.95; // USD.
      double water = .89; // USD
      System.out.println("The cost of lunch is: $ " + ( salad + water ) );
      
      // Calculate (and output) your age as of a certain year.
      int targetYear = 2025; // years.
      int birthYear = 2005; // years.
      System.out.println("Your age in " + targetYear + " is " + ( targetYear - birthYear ) );
      
      // Calculate (and output) the total calories of some apples.
      int caloriesPerApple = 127; // cal.
      int numberOfApples = 3;
      System.out.println("The calories in " + numberOfApples + " apples are " + ( caloriesPerApple * numberOfApples ) );
      
      // Calculate (and output) miles per gallon.
      double miles = 426.8; // mi.
      double gallons = 15.2; // gal.
      double mileage = miles / gallons; // mpg.
      System.out.println("The mileage is " + mileage + " miles per gallon.");
	}
		
}


