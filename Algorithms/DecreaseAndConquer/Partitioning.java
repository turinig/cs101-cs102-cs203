


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.DecreaseAndConquer;

// "Static" class including some simple algorithms to partition arrays (solve selection problem and computing median).
public final class Partitioning {

   // Note: private default constructor to forbid instantiation.
   private Partitioning () {} 
   
   // Desc.: Lomuto partitioning algorithm (decrease-by-1, iterative) to partition input array.
   // Input: An array of integers (A), index of leftmost item in subarray (left), and index of rightmost item in subarray (right).
   // Output: The array index of the pivot in the final partitioning obtained.
   public static int LomutoPartitioning( int[] A, int left, int right ) {
      // Init internal variables.
      int p = A[left]; // Pivot value, initialized as first item in subarray.
      int s = left; // s, last index of segment <p.
      // Process all items in unknown segment.
      for( int i = left+1; i <= right; i++ ) {
         // Compare current unprocessed item (at index i) with pivot (p).
         if( A[i] < p ) {
            s++; // Increment s to expand segment <p.
            // Swap A[s] with A[i], to reposition item at index i to index s.
            int temp = A[s];
            A[s] = A[i];
            A[i] = temp;
         }
         // Note: in any case index i is incremented.
      }
      // Swap A[left] with A[s], to reposition pivot (p) at index left to index s. 
      int temp = A[left];
      A[left] = A[s];
      A[s] = temp;
      // Return index of pivot in final partitioning.
      return s;
   }
   
   // Desc.: Hoare partitioning algorithm (decrease-by-1, iterative) to partition input array.
   // Input: An array of integers (A), index of leftmost item in subarray (left), and index of rightmost item in subarray (right).
   // Output: The array index of the last element in left segment (<pivot) in the final partitioning obtained.
   public static int HoarePartitioning( int[] A, int left, int right ) {
      int pivotIndex = left;
      int pivotValue = A[pivotIndex];
      int i = left - 1;
      int j = right + 1;
      while(true) {
         do { i++; } while ( A[i] < pivotValue );
         do { j--; } while ( A[j] > pivotValue );
         if( i < j ) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
         }
         else { return j; }
      }
   }
   
   // Desc.: Quick select algorithm (variable-size-decrease, recursive) to solve selection problem.
   // Input: An array of integers (A), index of leftmost item in subarray (left), and index of rightmost item in subarray (right).
   //        The order of the smallest item for the search (K).
   // Output: The value of the k-th smallest item in the input subarray.
   public static int QuickSelect( int[] A, int left, int right, int k ) {
      int s = LomutoPartitioning( A, left, right ); // Note: any partitioning algorithm can be used here.
      // Check pivot position after partitioning is completed.
      if( s == left+k-1 ) { 
         // Current pivot is k-th smallest item, selection problem solved, return pivot.
         return A[s];
      }
      else if( s > (left+k-1) ) {
         // k-th smallest item smaller than pivot, call (recursively) Quick Select on left segment (<pivot) with same k. 
         return QuickSelect( A, left, s-1, k );
      }
      else { 
         // k-th smallest item greater than pivot, call (recursively) Quick Select on right segment (>=pivot) with updated k (k-1-s).
         return QuickSelect( A, s+1, right, k-1-s );
      }
   }
   
}


