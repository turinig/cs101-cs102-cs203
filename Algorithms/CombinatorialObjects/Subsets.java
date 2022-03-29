


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.CombinatorialObjects;

// "Static" class including some simple algorithms to generate subsets.
public final class Subsets {

   // Note: private default constructor to forbid instantiation.
   private Subsets () {}
   
   // Desc.: Internal method to reverse (and return) input list (2D array, L).
   private static int[][] ReverseList( int[][] L ) {
      int[][] LRev = new int[ L.length ][ L[0].length ];
      int indexRev = 0;
      for( int i = ( L.length - 1 ); i >= 0; i-- ) {
         for( int j = 0; j < L[0].length; j++ ) {
            LRev[indexRev][j] = L[i][j];
         }
         indexRev++;
      }
      return LRev;
   }
   
   // Desc.: Internal method to extend input list (2D array, L), adding input value (v) at front.
   private static int[][] ExtendListAtFront( int[][] L, int v ) {
      // Init new extended list.
      int[][] LExt = new int[ L.length ][ L[0].length + 1 ];
      // Store input value at front of new extended list.
      for( int i = 0; i < LExt.length; i++ ) { LExt[i][0] = v; }
      // Transfer all other values from input list to new extended list.
      for( int i = 0; i < LExt.length; i++ ) {
         for( int j = 1; j < LExt[0].length; j++ ) {
            LExt[i][j] = L[i][j-1];
         }
      }
      return LExt;
   }
   
   // Desc.: Internal method to merge input lists (2D arrays, L1 and L2) returning (L1+L2).
   private static int[][] MergeLists( int[][] L1, int[][] L2 ) {
      // Init temp merged list.
      int[][] LNew = new int[ L1.length + L2.length ][ L1[0].length ];
      // Transfer all data from input list 1 to new list (left part).
      for( int i = 0; i < L1.length; i++ ) {
         for( int j = 0; j < L1[0].length; j++ ) {
            LNew[i][j] = L1[i][j];
         }
      }
      // Transfer all data from input list 2 to new list (right part).
      for( int i = 0; i < L2.length; i++ ) {
         for( int j = 0; j < L2[0].length; j++ ) {
            LNew[ L1.length + i ][j] = L2[i][j];
         }
      }
      return LNew;
   }

   // Desc.: Algorithm to generate all bit-strings representing subsets of a set of N (input) items.
   // Input: n, the size of the input set.
   // Output: An array storing all bit-strings representing all subsets of input set.
   // Note: Recursive implementation.
   public static int[][] BinaryReflectedGrayCodeRec( int n ) {
      // Init temp list of bit-strings (subsets).
      int[][] L = new int[0][0];
      // Check if input set has a size of 1 (trivial).
      if( n == 1 ) {
         L = new int[ (int) Math.pow( 2, n ) ][ n ]; // Size is [2^1][1].
         L[0][0] = 0;
         L[1][0] = 1;
      }
      else {
         // Generate recursively all subsets of a smaller input set (decrease-by-1).
         int[][] L1 = BinaryReflectedGrayCodeRec( n-1 );
         // Reverse list of newly generated subsets (to keep correct order of generated subsets.
         int[][] L1Rev = ReverseList( L1 );
         // Extend newly generated subsets including/excluding first item of input set.
         int[][] LPart1 = ExtendListAtFront( L1, 0 );
         int[][] LPart2 = ExtendListAtFront( L1Rev, 1 );
         // Merge extended lists.
         L = MergeLists( LPart1, LPart2 );
      }
      return L;
   }

}


