


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2021-10-08

package Recursion;

import java.lang.String;
import java.lang.StringBuilder;

// "Static" class including some simple algorithms to reverse a string.
public final class ReverseString {

   // Desc.: Default constructor.
   // Note: Private default constructor to forbid instantiation.
   private ReverseString() {} 
   
   // Desc.: Computes/return the reverse of the input string.
   // Input: s, a string.
   // Output: The reverse of the input string s.
   // Note: Iterative implementation (decrease-and-conquer, decrease-by-1-and-conquer, right-to-left reverse).
   public static String reverseString1( String s ) {
      StringBuilder tempResult = new StringBuilder();
      for( int i = s.length() - 1; i >= 0; i-- ) {
         tempResult.append( s.charAt(i) ); }
      return tempResult.toString();
   }
   
   // Desc.: Computes/return the reverse of the input string.
   // Input: s, a string.
   // Output: The reverse of the input string s.
   // Note: Recursive implementation (decrease-and-conquer, decrease-by-1-and-conquer, right-to-left reverse).
   public static String reverseString2( String s ) {
      if( s.length() > 0 ) {
         StringBuilder tempResult = new StringBuilder();
         tempResult.append( s.charAt( s.length() - 1 ) );
         String partialReverse = reverseString2( s.substring( 0, s.length() - 1 ) ); 
         tempResult.append( partialReverse ); 
         return tempResult.toString(); }
      return new String(); // Return empty string, do not return null!
   }
   
   // Desc.: Computes/return the reverse of the input string.
   // Input: s, a string.
   // Output: The reverse of the input string s.
   // Note: Recursive implementation (decrease-and-conquer, decrease-by-1-and-conquer, left-to-right reverse).
   public static String reverseString3( String s ) {
      if( s.length() > 0 ) {
         StringBuilder tempResult = new StringBuilder();
         String partialReverse = reverseString3( s.substring( 1, s.length() ) );
         tempResult.append( partialReverse );
         tempResult.append( s.charAt(0) );
         return tempResult.toString(); }
      return new String(); // Return empty string, do not return null!
   }

}


