


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2021-10-08

package Recursion;

// "Static" class including some simple algorithms to compute the factorial n!.
public final class Factorial {

   // Desc.: Default constructor.
   // Note: Private default constructor to forbid instantiation.
   private Factorial() {} 
   
   // Desc.: Computes the factorial n!.
   // Input: n, a non-negative integer.
   // Output: n! = n * (n-1) * (n-2) * ... * 2 * 1.
   // Note: Recusive implementation (decrease-and-conquer, decrease-by-1-and-conquer).
   public static int fact1( int n ) {
      if( n == 0 ) { return 1; }
      else { 
         return ( n * fact1( n-1 ) ); }
   }
   
   // Desc.: Computes the factorial n!.
   // Input: n, a non-negative integer.
   // Output: n! = n * (n-1) * (n-2) * ... * 2 * 1.
   // Note: Iterative implementation (decrease-and-conquer, decrease-by-1-and-conquer).
   public static int fact2( int n ) {
      int tempN = n;
      int tempResult = 1;
      while( tempN > 1 ) {
         tempResult *= tempN;
         tempN--; }
      return tempResult;
   }
   
   
}


