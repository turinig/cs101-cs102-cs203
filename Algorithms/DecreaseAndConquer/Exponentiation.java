


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.DecreaseAndConquer;

// "Static" class including some simple algorithms to compute the exponentiation.
public final class Exponentiation {

   // Note: private default constructor to forbid instantiation.
   private Exponentiation () {}
   
   // Desc.: Brute-force (or decrease-by-1 bottom-up, original) algorithm to compute a^n (exponentiation or power function).
   // Input: a (the base) and n (the exponent), assuming: a!=0 and n>=0.
   // Output: a^n.
   public static int Pow1( int a, int n ) {
      // Compute exponentiation.
      int res = 1; // Init temp result.
      // Iterate multiplications until exponent is exhausted.
      while( n > 0 ) {
         res = res * a; // Perform multiplication by base.
         n--; // Decrement current exponent.
      }
      return res; // Return result.
   }
   
   // Desc.: Brute-force (or decrease-by-1 bottom-up, optimized) algorithm to compute a^n (exponentiation or power function).
   // Input: a (the base) and n (the exponent), assuming: a!=0 and n>=0.
   // Output: a^n.
   public static int Pow2( int a, int n ) {
      // Check special cases (n==0, n==1).
      if( n == 0 ) { return 1; }
      else if ( n == 1 ) { return a; }
      else {
         // Compute exponentiation.
         int res = a; // Init temp result.
         // Iterate multiplications until exponent is exhausted.
         while( n > 1 ) {
            res = res * a; // Perform multiplication by base.
            n--; // Decrement current exponent.
         }
         return res; // Return result.
      }
   }

   // Desc.: Decrease-by-1 (top-down, original) algorithm to compute a^n (exponentiation or power function).
   // Input: a (the base) and n (the exponent), assuming: a!=0 and n>=0.
   // Output: a^n.
   // Note: Recursive implementation.
   public static int Pow3Rec( int a, int n ) {
      // BASE CASE: check if exponent is 0.
      if( n == 0 ) { return 1; }
      else {
         // RECURRENCE: recursive call using same base but smaller exponent.
         return a * Pow3Rec(a, n-1);
      }
   }
   
   // Desc.: Decrease-by-1 (top-down, optimized) algorithm to compute a^n (exponentiation or power function).
   // Input: a (the base) and n (the exponent), assuming: a!=0 and n>=0.
   // Output: a^n.
   // Note: Recursive implementation.
   public static int Pow4Rec( int a, int n ) {
      // BASE CASES: check if exponent is 0 or 1.
      if( n == 0 ) { return 1; }
      else if( n == 1 ) { return a; }
      else {
         // RECURRENCE: recursive call using same base but smaller exponent.
         return a * Pow4Rec(a, n-1);
      }
   }
   
   // Desc.: Decrease-by-factor-2 (top-down, not-optimized) algorithm to compute a^n (exponentiation or power function).
   // Input: a (the base) and n (the exponent), assuming: a!=0 and n>=0.
   // Output: a^n.
   // Note: Recursive implementation.
   public static int Pow5Rec( int a, int n ) {
      // BASE CASE: check if exponent is 0.
      if( n == 0 ) { return 1; }
      else {
         // Check if n is even.
         if( ( n % 2 ) == 0 ) {
            // RECURRENCE: recursive call using same base but smaller exponent.
            return Pow5Rec(a, n/2) * Pow5Rec(a, n/2);
         }
         else {
            // RECURRENCE: recursive call using same base but smaller exponent.
            return Pow5Rec(a, n/2) * Pow5Rec(a, n/2) * a;
         }
      }
   }
   
   // Desc.: Decrease-by-factor-2 (top-down, optimized) algorithm to compute a^n (exponentiation or power function).
   // Input: a (the base) and n (the exponent), assuming: a!=0 and n>=0.
   // Output: a^n.
   // Note: Recursive implementation.
   public static int Pow6Rec( int a, int n ) {
      // BASE CASES: check if exponent is 0 or 1.
      if( n == 0 ) { return 1; }
      else if( n == 1 ) { return a; }
      else {
         // Check if n is even.
         int tmpRes = Pow6Rec(a, n/2);
         if( ( n % 2 ) == 0 ) {
            return tmpRes * tmpRes;
         }
         else {
            return tmpRes * tmpRes * a;
         }
      }
   }


}


