


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.BruteForce;

// "Static" class including some simple algorithms to search.
public final class Searching {

   // Note: private default constructor to forbid instantiation.
   private Searching () {} 
   
   // Sequential search algorithm (version 1, original).
   public static int SequentialSearch1( int[] A, int k ) {
      int i = 0; // Scan index.
      // Sequential list scan: stop at list end or when key is found. 
      while( ( i < A.length ) && ( A[i] != k ) ) { i++; }
      // Check if search is successful.
      if( i < A.length ) { return i; }
      else { return -1; }
   }

   // Append input item to input list/array.
   public static int[] Append( int[] A, int e ) {
      int[] newA = new int[ A.length+1 ]; // Create new list with 1 more item.
      // Transfer items from old list to new list.
      for( int i = 0; i < A.length; i++ ) { newA[i] = A[i]; }
      newA[A.length] = e; // Store input item at end of new list.
      return newA; // Return new list.
   }

   // Sequential search algorithm (version 2, no failed searches).
   public static int SequentialSearch2( int[] A, int k ) {
      int[] extA = Append( A, k ); // Append search key to input list.
      int i = 0; // Scan index.
      // Sequential list scan: stop when key is found (search always successful). 
      while( extA[i] != k ) { i++; }
      // Check if search is successful.
      if( i < extA.length-1 ) { return i; }
      else { return -1; }
   }
   
   // String matching by brute-force.
   public static int StringMatching( String T, String P ) {
      // Init text T and pattern P sizes.
      int n = T.length();
      int m = P.length();
      // Scan text T (stop at n-m character).
      for( int i = 0; i <= n - m; i++ ) {
         // Init matching index.
         int j = 0;
         // Start matching pattern P.
         while( ( j < m ) && ( P.charAt(j) == T.charAt(i+j) ) ) { j++; }
         // Check if matching was successful.
         if( j == m ) { return i; }
      }
      // Search failed.
      return -1;
   }

}


