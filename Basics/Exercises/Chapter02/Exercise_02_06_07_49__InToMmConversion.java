


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-08

package Basics.Exercises.Chapter02;

// Exercise 2.6.7.49 from "Java Illuminated (5th Ed.)", chapter 2, page 86.
public class Exercise_02_06_07_49__InToMmConversion {
	
	// Exercise: write a program that converts 2, 5, and 10 inches (in) to millimiters (mm), considering 1 in = 25.4 mm.
	public static void main( String[] args ) {
      float inToMm = 25.4F;
      float in2AsMm = 2 * inToMm;
      float in5AsMm = 5 * inToMm;
      float in10AsMm = 10 * inToMm;
      System.out.println("2 in are : " + in2AsMm + " mm.");
      System.out.println("5 in are : " + in5AsMm + " mm.");
      System.out.println("10 in are : " + in10AsMm + " mm.");
	}
		
}


