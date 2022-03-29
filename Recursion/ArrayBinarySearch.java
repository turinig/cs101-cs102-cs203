


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2021-10-13

package Recursion;

// "Static" class including some simple algorithms to perform the binary search in a sorted array of integers.
public final class ArrayBinarySearch {

   // Desc.: Find input value in input sorted array of integers.
   // Input: array, a sorted array of integers.
   //        value, the value we will be searching for.
   // Output: The array index if the input value is found, -1 otherwise.
   // Note: Recursive implementation, using array partition.
   public static int binarySearch1( int array[], int value ) {
      // Check if input array is valid.
      if( array != null ) {
         // Input array is valid, find max.
         return binarySearch1Rec( array, 0, array.length-1, value );
      }
      // Input array not valid, return special value -1.
      return -1; // Error code.
   }
   
   // Desc.: Internal recursive implementation for binarySearch1.
   // Input: array, a sorted array of integers.
   //        indexFirst, array index representing the beginning of the array partition (inclusive).
   //        indexLast, array index representing the end of the array partition (inclusive).
   //        value, the value we will be searching for.
   // Output: The array index if the input value is found, -1 otherwise.
   // Note: Recursive implementation (divide-and-conquer, divide-by-2-and-conquer), using array partition.
   private static int binarySearch1Rec( int array[], int indexFirst, int indexLast, int value ) {
      // Check if input "array partition" is valid.
      if( indexFirst > indexLast ) { return -1; }
      else {
         // Input "array partition" is valid.
         // Invariant: if value is in input array: A[indexFirst] <= value <= A[indexLast].
         int pivot = ( indexFirst + indexLast ) / 2;
         // Check if value is at array[pivot], or determine the "array partition" half that could contain input value.
         if( value == array[pivot] ) { return pivot; } // Input value found at index pivot.
         else if( value < array[pivot] ) {
            return binarySearch1Rec( array, indexFirst, pivot-1, value ); // Search half 1.
         }
         else {
            return binarySearch1Rec( array, pivot+1, indexLast, value ); // Search half 2.
         }
      }
   }
   
}


