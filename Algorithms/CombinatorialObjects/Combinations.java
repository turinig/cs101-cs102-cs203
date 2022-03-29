


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.CombinatorialObjects;

// "Static" class including some simple algorithms to generate combinations.
public final class Combinations {

   // Note: private default constructor to forbid instantiation.
   private Combinations () {}
   
   // Internal method to print a combination (input array of integers) on console.
   private static void PrintCombination( int[] C ) {
      // Print all elements of input combination.
      for( int i = 0; i < C.length; i++ ) { System.out.print( C[i] ); }
      System.out.println();
   } 
   
   // Desc.: Internal method to generate all k-combinations of n of input set.
   //        The idea is to consider 1-by-1 every item in input set, and recur in two cases:
   //           (1) Current item is included in current combination. 
   //           (2) Current item is excluded in current combination.
   //        This strategy is based on Pascal’s Rule (see: https://en.wikipedia.org/wiki/Pascal's_rule).
   // Input: A, the input array storing the set of items to be combined.
   //        C, temp array storing the current combination.
   //        k, size of combination to be generated.
   //        indexC, current index in array C.
   //        indexA, current index in array A.
   // Output: Prints on console all k-combinations of n of input set.
   // Note: Recursive implementation.
   private static void GenerateCombinationsRec(int[] A, int[] C, int k, int indexC, int indexA) {
      // Check if current combination C is ready to be printed.
      if(indexC == k) {
         PrintCombination(C);
         return;
      }
      // Check if there are no more items in input set.
      if(indexA >= A.length) { return; }
      // CASE 1: Include current item.
      C[indexC] = A[indexA];
      GenerateCombinationsRec(A, C, k, indexC+1, indexA+1);
      // CASE 2: Exclude current item.
      GenerateCombinationsRec(A, C, k, indexC, indexA+1);
   }
  
   // Desc.: Public method to generate all k-combinations of n of input set.
   // Input: n, the size of the input set (auto generated using integers).
   //        k, size of combination to be generated.
   // Output: Prints on console all k-combinations of n of input set.
   public static void GenerateCombinations(int n, int k) {
      // Initialize item set.
      int[] A = new int[ n ];
      for( int i = 0; i < n; i++ ) { A[i] = i+1; }
      // Init temp variable to store current combination during generation.
      int[] C = new int[k];
      // Generate (and print) all k-combinations of n (recursively).
      GenerateCombinationsRec(A, C, k, 0, 0);
   }
    
   // Desc.: Internal method to generate all k-combinations of n of input set.
   //        The idea is to begin from item at start index in input set, then 1-by-1 fix items at current index and recur for remaining indices.
   // Input: A, the input array storing the set of items to be combined.
   //        C, temp array storing the current combination.
   //        indexStart, indexEnd, start and end indices for array A.
   //        indexCurr, current index in array C.
   //        k, size of combination to be generated.
   // Output: Prints on console all k-combinations of n of input set.
   // Note: Recursive implementation.
   private static void GenerateCombinationsRec2(int[] A, int[] C, int indexStart, int indexEnd, int indexCurr, int k) {
      // Check if current combination C is ready to be printed.
      if(indexCurr == k) {
         PrintCombination(C);
         return;
      }
      // Replace indexCurr with all possible elements in A.
      for(int i = indexStart; (i <= indexEnd) && (indexEnd-i+1 >= k-indexCurr); i++) {
         C[indexCurr] = A[i];
         GenerateCombinationsRec2(A, C, i+1, indexEnd, indexCurr+1, k);
      }
   }

   // Desc.: Public method to generate all k-combinations of n of input set.
   // Input: n, the size of the input set (auto generated using integers).
   //        k, size of combination to be generated.
   // Output: Prints on console all k-combinations of n of input set.
   public static void GenerateCombinations2(int n, int k) {
      // Initialize item set.
      int[] A = new int[ n ];
      for( int i = 0; i < n; i++ ) { A[i] = i+1; }
      // Init temp variable to store current combination during generation.
      int[] C = new int[k];
      // Generate (and print) all k-combinations of n (recursively).
      GenerateCombinationsRec2(A, C, 0, A.length-1, 0, k);
   }

}


