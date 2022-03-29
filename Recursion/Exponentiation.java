


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2021-10-06

package Recursion;

// "Static" class including some simple algorithms to perform the exponentiation operation base^exp.
public final class Exponentiation {

   // Desc.: Default constructor.
   // Note: Private default constructor to forbid instantiation.
   private Exponentiation() {} 
   
   // Desc.: Computes the exponentiation base^exp.
   // Input: base, an integer used as the base for exponentiation.
   //        exp, a non-negative integer used as the exponent for the exponentiation.
   // Output: base^exp.
   // Note: Recusive implementation (decrease-and-conquer, decrease-by-factor-2-and-conquer), optimized (trivial dynamic programming) to minimize number of multiplications and recursive calls (number multiplications performed = log2(exp)).
   public static int pow1( int base, int exp ) {
      if( exp == 0 ) { return 1; }
      else {
         int powHalfExp = pow1( base, ( exp/2 ) );
         if( ( exp % 2 ) == 0 ) { return powHalfExp * powHalfExp; }
         else { return base * powHalfExp * powHalfExp; }
      }
   }
   
   // Desc.: Computes the exponentiation base^exp.
   // Input: base, an integer used as the base for exponentiation.
   //        exp, a non-negative integer used as the exponent for the exponentiation.
   // Output: base^exp.
   // Note: Iterative implementation (number multiplications performed = exp).
   public static int pow2( int base, int exp ) {
      if( exp == 0 ) { return 1; }
      else {
         int tempResult = 1;
         for( int i = 0; i < exp; i++ ) {
            tempResult *= base; }
         return tempResult;
      }
   }
   
   // Desc.: Computes the exponentiation base^exp.
   // Input: base, an integer used as the base for exponentiation.
   //        exp, a non-negative integer used as the exponent for the exponentiation.
   // Output: base^exp.
   // Note: Iterative implementation (number multiplications performed = exp-1).
   public static int pow3( int base, int exp ) {
      if( exp == 0 ) { return 1; }
      else {
         int tempResult = base;
         for( int i = 1; i < exp; i++ ) {
            tempResult *= base; }
         return tempResult; }
   }
   
   // Desc.: Computes the exponentiation base^exp.
   // Input: base, an integer used as the base for exponentiation.
   //        exp, a non-negative integer used as the exponent for the exponentiation.
   // Output: base^exp.
   // Note: Recusive implementation (decrease-and-conquer, decrease-by-1-and-conquer), (number multiplications performed = exp-1).
   public static int pow4( int base, int exp) {
      if( exp == 0 ) { return 1; }
      else if( exp == 1 ) { return base; }
      else {
         return base * pow4( base, exp-1 ); }
   }
   
   // Desc.: Computes the exponentiation base^exp.
   // Input: base, an integer used as the base for exponentiation.
   //        exp, a non-negative integer used as the exponent for the exponentiation.
   // Output: base^exp.
   // Note: Recusive implementation (divide-and-conquer, divide-by-2-and-conquer), (number multiplications performed = exp-1).
   public static int pow5( int base, int exp ) {
      if( exp == 0 ) { return 1; }
      else if( exp == 1 ) { return base; }
      else {
         if( ( exp % 2 ) == 0 ) { return pow5( base, ( exp/2 ) ) * pow5( base, ( exp/2 ) ); }
         else { return base * pow5( base, ( exp/2 ) ) * pow5( base, ( exp/2 ) ); }
      }
   }
   
}


