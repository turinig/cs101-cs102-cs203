


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2021-10-13

package Recursion;

// "Static" class including some simple algorithms to find the maximum value in an unsorted array of integers.
public final class ArrayFindMax {

   // Desc.: Find max value in input unsorted array of integers.
   // Input: array, an unsorted array of integers.
   // Output: The max value in the input unsorted array.
   // Note: Iterative implementation.
   public static int findMax1( int[] array ) {
      // Check if input array is valid.
      if( array != null ) {
         // Input array is valid, check its size.
         if( array.length == 1 ) { return array[0]; }
         else {
            // Input array has more than 1 item, search for the max.
            int tmpMax = array[0]; // Temporary max value initialized with 1st array item.
            // Iterate array values from 2nd until last.
            for( int i = 1; i < array.length; i++ ) {
               // Check if current array item is greater than temporary max.
               if( array[i] > tmpMax ) { tmpMax = array[i]; }
            }
            return tmpMax; // Max value is now in local variable, return it.
         }
      }
      return -1; // Input array not valid, return special value -1.
   }
   
   // Desc.: Find max value in input unsorted array of integers.
   // Input: array, an unsorted array of integers.
   // Output: The max value in the input unsorted array.
   // Note: Recursive implementation (decrease-and-conquer, decrease-by-1-and-conquer), using real subarray creation.
   public static int findMax2( int[] array ) {
      // Check if input array is valid.
      if( array != null ) {
         // Input array is valid, check its size.
         if( array.length == 1 ) { return array[0]; }
         else {
            // Input array has more than 1 item, use recursive strategy.
            // Create another array tmpArray, storing same content of input array except item at index 0.
            int[] tmpArray = new int[ array.length-1 ];
            for( int i = 1; i < array.length; i++ ) {
               tmpArray[i-1] = array[i]; // For example: tmpArray[1] is array[2], etc.
            }
            // Find the max value in tmpArray.
            int maxInTmpArray = findMax2(tmpArray); // Recursive call.
            // Determine max value in input array.
            if( array[0] > maxInTmpArray ) { return array[0]; }
            else { return maxInTmpArray; }
         }
      }
      return -1; // Input array not valid, return special value -1.
   }
   
   // Desc.: Find max value in input unsorted array of integers.
   // Input: array, an unsorted array of integers.
   // Output: The max value in the input unsorted array.
   // Note: Recursive implementation (divide-and-conquer, divide-by-factor-2-and-conquer), using real subarray creation.
   public static int findMax3( int[] array ) {
      // Check if input array is valid.
      if( array != null ) {
         // Input array is valid, check its size.
         if( array.length == 1 ) { return array[0]; }
         else {
            // Input array has more than 1 item, use recursive strategy.
            // Create other 2 arrays tmpArray1 and tmpArray2, splitting input array in 2 "halves".
            int pivot = array.length / 2; // For example: 5 / 2 = 2.
            int[] tmpArray1 = new int[pivot]; // For example: first half with 2 items.
            int[] tmpArray2 = new int[ array.length-pivot ]; // For example: second half with 3 items.
            // Transfer/copy data from input array to subarrays tmpArray1 and tmpArray2.
            for( int i = 0; i < pivot; i++ ) {
               tmpArray1[i] = array[i];
            }
            for( int j = 0; j < array.length-pivot; j++ ) {
               tmpArray2[j] = array[ j+pivot ]; // For example: tmpArray2[0] is equal to array[pivot], etc.
            }
            // Find the max value in both subarrays tmpArray1 and tmpArray2, recursively.
            int maxInTmpArray1 = findMax3( tmpArray1 ); // Recursive call.
            int maxInTmpArray2 = findMax3( tmpArray2 ); // Recursive call.
            // Determine max value in input array.
            if( maxInTmpArray1 > maxInTmpArray2 ) { return maxInTmpArray1; }
            else { return maxInTmpArray2; }
         }
      }
      return -1; // Input array not valid, return special value -1.
   }
   
   // Desc.: Find max value in input unsorted array of integers.
   // Input: array, an unsorted array of integers.
   // Output: The max value in the input unsorted array.
   // Note: Recursive implementation (decrease-and-conquer, decrease-by-1-and-conquer), using array partition.
   public static int findMax4( int[] array ) {
      // Check if input array is valid.
      if( array != null ) {
         // Input array is valid, find max.
         return findMax4Rec( array, 0, array.length-1 );
      }
      return -1; // Input array not valid, return special value -1.
   }
   
   // Desc.: Internal recursive implementation for findMax4.
   // Input: array, an unsorted array of integers.
   //        indexFirst, array index representing the beginning of the array partition (inclusive).
   //        indexLast, array index representing the end of the array partition (inclusive).
   // Output: The max value in the input unsorted array partition.
   // Note: Recursive implementation (decrease-and-conquer, decrease-by-1-and-conquer), using array partition.
   private static int findMax4Rec( int[] array, int indexFirst, int indexLast ) {
      // Check size of input "array partition".
      if( indexLast - indexFirst == 0 ) { return array[indexFirst]; }
      else {
         // Input "array partition" has more than 1 item, use recursive strategy.
         // Find the max value in a smaller "array partition" (excluding first item).
         int maxInRest = findMax4Rec( array, indexFirst+1, indexLast ); // Recursive call.
         // Determine max value in input "array partition".
         if( array[indexFirst] > maxInRest ) { return array[indexFirst]; }
         else { return maxInRest; }
      }
   }
   
   // Desc.: Find max value in input unsorted array of integers.
   // Input: array, an unsorted array of integers.
   // Output: The max value in the input unsorted array.
   // Note: Recursive implementation (divide-and-conquer, divide-by-2-and-conquer), using array partition.
   public static int findMax5( int[] array ) {
      // Check if input array is valid.
      if( array != null ) {
         // Input array is valid, find max.
         return findMax5Rec( array, 0, array.length-1 );
      }
      return -1; // Input array not valid, return special value -1.
   }
   
   // Desc.: Internal recursive implementation for findMax5.
   // Input: array, an unsorted array of integers.
   //        indexFirst, array index representing the beginning of the array partition (inclusive).
   //        indexLast, array index representing the end of the array partition (inclusive).
   // Output: The max value in the input unsorted array partition.
   // Note: Recursive implementation (divide-and-conquer, divide-by-2-and-conquer), using array partition.
   private static int findMax5Rec( int[] array, int indexFirst, int indexLast ) {
      // Check size of input "partition".
      if( indexLast - indexFirst == 0 ) { return array[indexFirst]; }
      else {
         // Input "array partition" has more than 1 item, use recursive strategy.
         // Find the max value in the 2 halves of the input "array partition".
         int pivot = ( indexFirst + indexLast + 1 ) / 2; // For example: if indexFirst = 2 and indexLast = 6 then pivot is 4.
         int maxInHalf1 = findMax5Rec( array, indexFirst, pivot-1 ); // Recursive call.
         int maxInHalf2 = findMax5Rec( array, pivot, indexLast ); // Recursive call.
         // Determine max value in input "array partition".
         if( maxInHalf1 > maxInHalf2 ) { return maxInHalf1; }
         else { return maxInHalf2; }
      }
   }
   
}


