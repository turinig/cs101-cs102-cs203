


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-08

package Basics.Exercises.Chapter02;

// Exercise 2.6.7.48 from "Java Illuminated (5th Ed.)", chapter 2, page 86.
public class Exercise_02_06_07_48__KgToLbConversion {
	
	// Exercise: write a program that converts 10, 50, and 100 kilograms (kg) to pounds (lb), considering 1 lb = 0.454 kg.
	public static void main( String[] args ) {
      float lbToKg = 0.454F;
      float kg10AsLb = 10 / lbToKg;
      float kg50AsLb = 50 / lbToKg;
      float kg100AsLb = 100 / lbToKg;
      System.out.println("10 kg are : " + kg10AsLb + " lb.");
      System.out.println("50 kg are : " + kg50AsLb + " lb.");
      System.out.println("100 kg are : " + kg100AsLb + " lb.");
	}
		
}


