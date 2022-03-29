


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.DivideAndConquer;

// "Static" class including some simple algorithms to sort.
public final class Sorting {

   // Note: private default constructor to forbid instantiation.
   private Sorting () {}
   
   // Desc.: Algorithm (iterative) to merge 2 sorted arrays into another sorted array.
   // Input: Sorted arrays B and C (full) will be merged into sorted array A (empty, overwritten).
   // Note: Assuming arrays B and C are sorted and full, array A empty (overwritten), and A.length = B.length + C.length.
   public static void MergeSortedArrays( int[] B, int[] C, int[] A ) {
      // Init temp variables.
      int i = 0;
      int j = 0;
      int k = 0;
      // Scanning sorted arrays B and C, while inserting in A.
      while( ( i < B.length ) && ( j < C.length ) ) {
         if( B[i] <= C[j] ) { A[k] = B[i]; i++; }
         else { A[k] = C[j]; j++; }
         k++;
      }
      // One scan has terminated, transfer remaining sorted data in A.
      if( i == B.length ) { System.arraycopy( C, j, A, k, C.length - j ); }
      else { System.arraycopy( B, i, A, k, B.length - i ); }
   }

   // Desc.: Mergesort algorithm (divide-by-2, iterative, bottom-up) to sort input array (non-decreasing).
   // Input: An array of integers (A).
   // Note: Recursive implementation.
   public static void Mergesort( int[] A ) {
      // Check if sorting is really necessary.
      if( A.length > 1 ) {
         // Determine size of array halves.
         int halfN = (int) Math.floor( A.length / 2 );
         // Init half 1.
         int[] B = new int[halfN];
         System.arraycopy( A, 0, B, 0, halfN );
         // Init half 2.
         int[] C = new int[ A.length - halfN ];
         System.arraycopy( A, halfN, C, 0, A.length - halfN );
         // Sort (recursively) halves 1 and 2.
         Mergesort(B);
         Mergesort(C);
         // Merge sorted halves (arrays B and C) into final sorted array (A).
         MergeSortedArrays( B, C, A );
      }
   }
   
   // Desc.: Quicksort algorithm (divide-by-2, recursive, top-down) to sort input subarray (non-decreasing).
   // Input: An array of integers (A), leftmost index of input subarray (left), and rightmost index of input subarray (right).
   // Note: Recursive implementation.
   public static void Quicksort( int[] A, int left, int right ) {
      // ...
      if( left < right ) {
         // Array partitioning (any partitioning algorithm can be used here).
         // Note: if partitioning does not return pivot index (e.g. Hoare), recursive calls must be adjusted!
         int pivotIndex = Algorithms.DecreaseAndConquer.Partitioning.LomutoPartitioning(A, left, right);
         // Recursive application of Quicksort to left-part and right-part in respect to pivot.
         Quicksort( A, left, pivotIndex-1 );
         Quicksort( A, pivotIndex+1, right );
      }
   }

}


