


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package Algorithms.CombinatorialObjects;

// "Static" class including some simple algorithms to generate permutations.
public final class Permutations {

   // Note: private default constructor to forbid instantiation.
   private Permutations () {} 
   
   // Internal method to print a permutation (input array of integers) on console.
   private static void PrintPermutation( int[] P ) {
      // Print all elements of input permutation.
      for( int i = 0; i < P.length; i++ ) { System.out.print( P[i] ); }
      System.out.println();
   }
   
   // Internal method for the Johnson-Trotter algorithm.
   private static boolean PermutationHasMobileElement( int[] P, boolean[] D ) {
      // Iterate permutation elements.
      for( int i = 0; i < P.length; i++ ) {
         // Check if current element is mobile using its direction and its value.
         if( ( ( D[i] ) && ( i > 0 ) && ( P[i] > P[i-1] ) ) ||
             ( ( !D[i] ) && ( i < ( P.length - 1 ) ) && ( P[i] > P[i+1] ) ) ) { return true; } }
      // Return false (mobile element not found).
      return false;
   }
   
   // Internal method for the Johnson-Trotter algorithm.
   private static int FindMaxMobileElement( int[] P, boolean[] D ) {
      // Init temp variables.
      int maxMobileVal = 0;
      int maxMobileIndex = -1;
      // Iterate permutation elements.
      for( int i = 0; i < P.length; i++ ) {
         // Check if current element is mobile using its direction and its value.
         if( ( ( D[i] ) && ( i > 0 ) && ( P[i] > P[i-1] ) ) ||
             ( ( !D[i] ) && ( i < ( P.length - 1 ) ) && ( P[i] > P[i+1] ) ) ) {
            // Check if current mobile element is greater than current max mobile element.
            if( P[i] > maxMobileVal ) {
               maxMobileVal = P[i];
               maxMobileIndex = i; } } }
      // Return index of max mobile element.
      return maxMobileIndex;
   }
   
   // Internal method for the Johnson-Trotter algorithm.
   private static void SwapMobileElement( int[] P, boolean[] D, int k ) {
      // Init temp variables.
      int tempVal = P[k];
      boolean tempDir = D[k];
      // Swap mobile element following its direction.
      if( D[k] ) {
         P[k] = P[k-1];
         P[k-1] = tempVal;
         D[k] = D[k-1];
         D[k-1] = tempDir; }
      else {
         P[k] = P[k+1];
         P[k+1] = tempVal;
         D[k] = D[k+1];
         D[k+1] = tempDir; }
   }
   
   // Internal method for the Johnson-Trotter algorithm.
   private static void ReverseDirection( int[] P, boolean[] D, int v ) {
      // Iterate permutation elements.
      for( int i = 0; i < P.length; i++ ) {
         // Reverse direction if element is greater than input value (max mobile element).
         if( P[i] > v ) { D[i] = !D[i]; } }
   }

   // Johnson-Trotter algorithm to generate all n-permutations of n (first n integers).
   public static void JohnsonTrotter( int n ) {
      // Initialize the first permutation.
      int[] P = new int[ n ]; // Permutation array.
      boolean[] D = new boolean[ n ]; // Direction array (true = left, false = right).
      for( int i = 0; i < n; i++ ) { P[i] = i+1; D[i] = true; }
      // Print initial permutation. 
      PrintPermutation( P );
      // Start generation until there is no mobile element.
      while( PermutationHasMobileElement( P, D ) ) {
         int maxMobileIndex = FindMaxMobileElement( P, D );
         int maxMobileValue = P[maxMobileIndex];
         SwapMobileElement( P, D, maxMobileIndex );
         ReverseDirection( P, D, maxMobileValue );
         PrintPermutation( P ); }
   }
   
   // Internal method for the Lexicographic Permute algorithm.
   private static boolean HasTwoConsecutiveElementsIncreasing( int[] P ) {
      // Iterate permutation elements.
      for( int i = 0; i < ( P.length - 1 ); i++ ) {
         // Check if consecutive elements are in increasing order.
         if( P[i] < P[i+1] ) { return true; } }
      return false;
   }
   
   // Internal method for the Lexicographic Permute algorithm.
   private static int MaxIndexConsecutiveElementsIncreasing( int[] P ) {
      // Init temp variables.
      int maxI = -1;
      // Iterate permutation elements.
      for( int i = 0; i < ( P.length - 1 ); i++ ) {
         // Check if consecutive elements are increasing, and if max index needs to be updated.
         if( ( P[i] < P[i+1] ) && ( i > maxI ) ) { maxI = i; } }
      // Return max index of increasing consecutive elements.
      return maxI;
   }

   // Internal method for the Lexicographic Permute algorithm.
   private static int MaxIndexPILessThanPJ( int[] P, int i ) {
      // Init temp variables.
      int maxJ = -1;
      // Iterate permutation elements after input index.
      for( int j = i+1; j < P.length; j++ ) {
         // Check increasing consecutive elements, and update index J if necessary.
         if( ( P[i] < P[j] ) && ( j > maxJ ) ) { maxJ = j; } }
      // Return max index J (of increasing consecutive elements after input index I).
      return maxJ;
   }

   // Internal method for the Lexicographic Permute algorithm.   
   private static void ReverseSuffix( int[] P, int i ) {
      // Iterate suffix indices.
      for( int k1 = i+1, k2 = ( P.length - 1 ); k1 < k2; k1++, k2-- ) {
         // Swap P[k1] and P[k2].
         int temp = P[k1];
         P[k1] = P[k2];
         P[k2] = temp; }
   }

   // Lexicographic Permute algorithm to generate all n-permutations of n (first n integers).
   public static void LexicographicPermute( int n ) {
      // Initialize the first permutation.
      int[] P = new int[ n ]; // Permutation array.
      for( int i = 0; i < n; i++ ) { P[i] = i+1; }
      PrintPermutation( P );
      // Generate all permutations in lexicographic order.
      while( HasTwoConsecutiveElementsIncreasing( P ) ) {
         int i = MaxIndexConsecutiveElementsIncreasing( P );
         int j = MaxIndexPILessThanPJ( P, i );
         int temp = P[i]; P[i] = P[j]; P[j] = temp; // Swap P[i] and P[j].
         ReverseSuffix( P, i );
         PrintPermutation( P ); }
   }

   
}


