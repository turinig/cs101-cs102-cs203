


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package DataStructures.List;

import java.lang.RuntimeException;

// Exception (non-critical) used when the array storing the list becomes full.
public class ListException extends RuntimeException {

   // Constructor.
   public ListException( String s ) { 
      super(s);
   }

}


