


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-08

package Basics.Exercises.Chapter02;

// Exercise 2.6.6.45 from "Java Illuminated (5th Ed.)", chapter 2, page 85.
public class Exercise_02_06_06_45__AverageOfIntegers {
	
	// Exercise: write a program that calculates and outputs the average of integers 1, 7, 9, and 34.
	public static void main( String[] args ) {
      int total = 1 + 7 + 9 + 34;
      int count = 4;
      float average = (float) (total) / count;
		System.out.println("The average of 1, 7, 9, and 34 is: " + average);
	}
		
}


