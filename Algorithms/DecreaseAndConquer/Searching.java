


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.DecreaseAndConquer;

// "Static" class including some simple algorithms to search.
public final class Searching {

   // Note: private default constructor to forbid instantiation.
   private Searching () {} 
   
   // Desc.: Binary search algorithm (decrease-by-factor-2, iterative) to search for input item/key in a sorted input array.
   // Input: A sorted array of integers (A), and a search key (k).
   // Output: The array index where the search key is stored (search successful), or -1 otherwise (search failed).
   public static int BinarySearch1( int[] A, int k ) {
      // Init temp variables (search partition).
      int left = 0;
      int middle = 0;
      int right = A.length - 1;
      // Search until range (search partition) is invalid.
      while( left <= right ) { 
         middle = (int) Math.floor( ( left + right ) / 2 ); // Update pivot (middle).
         if( k == A[middle] ) { return middle; } // Search key found at pivot (middle).
         else if( k < A[middle] ) { right = middle - 1; } // Update range (search partition) as left half.
         else { left = middle + 1; } // Update range (search partition) as right half.
      }
      return -1; // Loop terminated without finding input key: search failed, return -1.
   }
   
   // Desc.: Binary search algorithm (decrease-by-factor-2, recursive) to search for input item/key in a sorted input array.
   // Input: A sorted array of integers (A), and a search key (k).
   // Output: The array index where the search key is stored (search successful), or -1 otherwise (search failed).
   public static int BinarySearch2( int[] A, int k ) {
      // Call internal recursive implementation using entire array as search partition.
      return BinarySearch2Rec( A, k, 0, A.length - 1 );      
   }
   
   
   // Desc.: Decrease-by-factor-2 algorithm (recursive, top-down) to search for input item/key in a sorted input array.
   // Input: A sorted array of integers (A), a search key (k), and 2 array indices (left, and right) representing the search partition.
   // Output: The array index where the search key is stored (search successful), or -1 otherwise (search failed).
   // Note: Recursive implementation.
   public static int BinarySearch2Rec( int[] A, int k, int left, int right ) {
      // BASE CASE: check if search partition is empty
      if( left > right ) { return -1; }
      else {
         // Search partition has at least 1 element, perform binary search.
         int middle = (int) Math.floor( ( left + right ) / 2 ); // Update pivot (middle).
         if( k == A[middle] ) { return middle; } // Search key found at pivot (middle).
         else if( k < A[middle] ) { return BinarySearch2Rec( A, k, left, middle - 1 ); } // Proceed search (recursively) in left half.
         else { return BinarySearch2Rec( A, k, middle + 1, right ); } // Proceed search (recursively) in right half.
      }
   }
   
   // Desc.: Interpolation search algorithm (variable-size-decrease, iterative) to search for input item/key in a sorted input array.
   // Input: A sorted array of integers (A), index of leftmost item in subarray (left), index of rightmost item in subarray (right), and a search key (k).
   // Output: The array index where the search key is stored (search successful), or -1 otherwise (search failed).
   public static int InterpolationSearch( int[] A, int left, int right, int k ) {
      // BASE CASES: check if search partition is empty or trivial (1 item only).
      if( left > right ) { return -1; }
      else if( left == right ) { 
         if( A[left] == k ) { return left; }
         else { return -1; }
      }
      else {
         // Input array is sorted, so search key has to be in first/last items range.
         if( ( k >= A[left] ) && ( k <= A[right] ) ) {
            // Generate probing index for search key assuming uniform distribution of array items.
            // Note: equation derived from 2D linear equation between 2 2D points.
            int indexK = left + ( ( ( right - left ) / ( A[right] - A[left] ) ) * ( k - A[left] ) );
            // Check if search key is at probing index.
            if( A[indexK] == k ) { return indexK; } // Search key found at probing index.
            else if( A[indexK] < k ) {
               // Item at probing index is less than search key, proceed search in right subarray (recursively).
               return InterpolationSearch( A, indexK+1, right, k );
            }
            else {
               // Item at probing index is greater than search key, proceed search in left subarray (recursively).
               return InterpolationSearch( A, left, indexK-1, k );
            }
         }
         else {
            // Input search key k out of range, search failed, return -1.
            return -1;
         }
      }
   }
   
}


