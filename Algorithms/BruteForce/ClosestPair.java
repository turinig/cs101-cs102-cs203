


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.BruteForce;

import java.awt.Point; // Importing a class representing a 2D point (x,y), specified in integer precision.
import java.lang.Math; // Importing a class including basic numeric operations.
import java.lang.Double; // Importing a class including info of the primitive type "double".

// "Static" class including some simple algorithms to solve the closest-pair problem.
public final class ClosestPair {

   // Note: private default constructor to forbid instantiation.
   private ClosestPair () {} 
   
   // Closest-pair 2D brute-force algorithm.
   public static double ClosestPair2D( Point[] P ) {
      double d = Double.MAX_VALUE;
      for( int i = 0; i < ( P.length - 1 ); i++ ) {
         for( int j = i+1; j < P.length; j++ ) {
            d = Math.min( d, P[i].distance( P[j] ) );
         }
      }
      return d;
   }
   
}
