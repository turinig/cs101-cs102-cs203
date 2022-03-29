


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.DivideAndConquer;

// "Static" class including some simple algorithms to add.
public final class Adding {

   // Note: private default constructor to forbid instantiation.
   private Adding () {} 
   
   // Desc.: Algorithm (divide-by-2, recursive, top-down) to add all integers between a and b (with a <= b).
   // Input: value a and value b representing the summation range (inclusive).
   // Note: Recursive implementation.
   public static int AddIntegersInRangeRec( int a, int b ) {
      // BASE CASE: input range trivial.
      if( a == b ) { return a; }
      else {
         // Solving 2 subproblems.
         int resSP1 = AddIntegersInRangeRec( a, (a+b)/2 );
         int resSP2 = AddIntegersInRangeRec( ((a+b)/2)+1, b );
         // Merging subproblems solutions.
         return resSP1 + resSP2;
      }
   }

}


