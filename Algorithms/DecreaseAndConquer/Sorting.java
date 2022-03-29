


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.DecreaseAndConquer;

// "Static" class including some simple algorithms to sort.
public final class Sorting {

   // Note: private default constructor to forbid instantiation.
   private Sorting () {} 
   
   // Desc.: Insertion sort algorithm (decrease-by-1, iterative, bottom-up) to sort input array (non-decreasing).
   // Input: an array of integers (A).
   public static void InsertionSort( int[] A ) {
      // Init temp variables.
      int val = 0;
      int j = 0;
      // Iterate array items from 2nd to last.
      for( int i = 1; i < A.length; i++ ) {
         // Update temp variables.
         val = A[i];
         j = i-1;
         // Iterate array items from before-current to 1st (right-to-left), shifting right when necessary. 
         while( ( j >= 0 ) && ( A[j] > val ) ) {
            // shift right to order items, and decrement index.
            A[j+1] = A[j];
            j = j-1;
         }
         // Store current item at proper cell after right shift.
         A[j+1] = val;
      }
   }

}


