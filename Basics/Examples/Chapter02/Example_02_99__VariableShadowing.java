


// CS-101: "Computing and Algorithms I"
// Prof. Giuseppe Turini
// Kettering University
// 2022-02-05

package Basics.Examples.Chapter02;

// Example on variable shadowing (or name masking).
public class Example_02_99__VariableShadowing {
   
   static int var1 = 123; // A class variable.
	
	public static void main( String[] args ) {
      System.out.println(var1);
      int var1 = 456; // A local variable with same name of class variable (variable shadowing).
      System.out.println(var1);
	}
		
}


