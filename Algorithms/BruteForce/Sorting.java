


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.BruteForce;

// "Static" class including some simple algorithms to sort.
public final class Sorting {

   // Note: private default constructor to forbid instantiation.
   private Sorting () {} 
   
   // Selection sort algorithm.
   public static void SelectionSort( int[] A ) {
      // Temp variables.
      int min; // Index of current partition minimum.
      int temp; // Buffer variable to perform swap.
      // Loop to sort 1 item at time (except last).
      for( int i = 0; i < A.length - 1; i++ ) {
         min = i; // Init/reset index of current partition minimum.
         // Scan array partition [i+1,n-1] to find partition minimum.
         for( int j = i+1; j < A.length; j++ ) { 
            // Compare current partition minimum against current partition scan item.
            if( A[j] < A[min] ) { min = j; }
         }
         // Put partition minimum in its final sorted spot (swap A[i] and A[min]).
         temp = A[i];
         A[i] = A[min];
         A[min] = temp; 
      }
   }
   
   // Bubble sort algorithm (v1).
   public static void BubbleSort1( int[] A ) {
      // Loop to perform n-2 scans.
      for( int i = 0; i < A.length - 1; i++ ) {
         // Bubble up items (partial scan).
         for( int j = 0; j < A.length - 1 - i; j++ ) {
            // Check if adjacent items are out of order.
            if( A[j+1] < A[j] ) {
               // Swap adjacent items.
               int temp = A[j];
               A[j] = A[j+1];
               A[j+1] = temp;
            }
         }
      }
   }
   
   // Bubble sort algorithm (v2).
   public static void BubbleSort2( int[] A ) {
      // Loop to perform n-2 scans.
      for( int i = 0; i < A.length - 1; i++ ) {
         // Bubble up items (partial scan).
         boolean swapMade = false;
         for( int j = 0; j < A.length - 1 - i; j++ ) {
            // Check if adjacent items are out of order.
            if( A[j+1] < A[j] ) {
               // Swap adjacent items.
               int temp = A[j];
               A[j] = A[j+1];
               A[j+1] = temp;
               swapMade = true;
            }
         }
         if(!swapMade) { return; }
      }
   }

}


