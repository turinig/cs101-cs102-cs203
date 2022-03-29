


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-08

package Basics.Exercises.Chapter02;

// Exercise 2.6.7.50 from "Java Illuminated (5th Ed.)", chapter 2, page 86.
public class Exercise_02_06_07_50__CircumferenceOfCircle {
	
	// Exercise: write a program to compute and output the circumference of a circle having a radius of 3.2 inches (in).
	public static void main( String[] args ) {
      float radius = 3.2F;
      final float PI =3.14159F;
      float circumference = 2 * PI * radius;
      System.out.println("The circumference of a circle of radius " + radius + " in, is: " + circumference + " in.");
	}
		
}


