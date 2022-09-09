


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-09

package DataStructures.Queue;

import java.lang.RuntimeException;
import java.lang.String;

// Exception (non-critical) used for a runtime failure performing a queue operation.
public class QueueRuntimeException extends java.lang.RuntimeException {
   
   // Constructor.
   public QueueRuntimeException( String s ) { 
      super(s);
   }
   
}


