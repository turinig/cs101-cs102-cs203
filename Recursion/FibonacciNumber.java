


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2021-10-08

package Recursion;

// "Static" class including some simple algorithms to compute the nth Fibonacci number.
public final class FibonacciNumber {

   // Desc.: Default constructor.
   // Note: Private default constructor to forbid instantiation.
   private FibonacciNumber() {} 
   
   // Desc.: Computes the nth Fibonacci number.
   // Input: n, a non-negative integer.
   // Output: nth Fibonacci number in the Fibonacci series (0, 1, 1, 2, 3, 5, 8, 13, etc.).
   // Note: Recusive implementation (decrease-and-conquer, decrease-by-1(2)-and-conquer, top-down).
   public static int fibonacci1( int n ) {
      if( n <= 2 ) { return 1; }
      else { 
         return ( fibonacci1( n-1 ) + fibonacci1( n-2 ) ); }
   }
   
   // Desc.: Computes the nth Fibonacci number.
   // Input: n, a non-negative integer.
   // Output: nth Fibonacci number in the Fibonacci series (0, 1, 1, 2, 3, 5, 8, 13, etc.).
   // Note: Iterative implementation (bottom-up).
   public static int fibonacci2( int n ) {
      if( n <= 2 ) { return 1; }
      else {
         int a = 1;
         int b = 1;
         int tmpA;
         for( int i = 3; i <= n; i++ ) {
            tmpA = a;
            a = b;
            b += tmpA; }
         return b; }
   }
   
   // Desc.: Computes the nth Fibonacci number.
   // Input: n, a non-negative integer.
   // Output: nth Fibonacci number in the Fibonacci series (0, 1, 1, 2, 3, 5, 8, 13, etc.).
   // Note: Iterative implementation (bottom-up) optimized (dynamic programming) to minimize number of additions.
   public static int fibonacci3( int n ) {
      if( n <= 2 ) { return 1; }
      else {
         int[] fibonacciSequence = new int[n+1];
         fibonacciSequence[0] = 0;
         fibonacciSequence[1] = 1;
         for( int i = 2; i <= n; i++ ) {
            fibonacciSequence[i] = fibonacciSequence[i-1] + fibonacciSequence[i-2]; }
         return fibonacciSequence[n]; }
   }
   
}


