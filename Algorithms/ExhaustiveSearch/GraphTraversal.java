


// CS-203: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-09

package Algorithms.ExhaustiveSearch;

import java.lang.Integer;

import DataStructures.Graph.GraphInterface;
import DataStructures.Stack.StackList;
import DataStructures.Queue.QueueList;

// "Static" class including some simple algorithms to traverse a graph.
public final class GraphTraversal {

   // Note: private default constructor to forbid instantiation.
   private GraphTraversal() {} 
   
   // Desc.: Depth-First Search (DFS) traversal of this graph.
   // Input: A graph.
   // Output: The DFS traversal order is returned as an array of vertex indices.
   // Note: Recursive implementation.
   public static int[] DFSRecursive( GraphInterface g ) {
      // Init local variables.
      int[] vertexVisits = new int[ g.getNumberVertices() ]; // Array index is vertex index, array cell value is visit order (0 means not-visited, 1 means first visit, etc.).
      int c = 0; // Vertex visit counter.
      // Start DFS run on each unvisited vertex, starting from first vertex index.
      for( int i = 0; i < g.getNumberVertices(); i++ ) {
         // Check if current vertex index has not been visited yet (order = 0).
         if( vertexVisits[i] == 0 ) { 
            // Current vertex index is unvisited, start DFS run at that vertex using current visit counter.
            c = DFSRecursive( g, i, c, vertexVisits ); // Recursive call (first call to internal recursive implementation).
         }
      }
      // Compute DFS order (from vertex visit array).
      int[] dfsOrder = new int[ vertexVisits.length ];
      for( int i = 0; i < vertexVisits.length; i++ ) {
         dfsOrder[ vertexVisits[i] - 1 ] = i;
      }
      // Return result as array of vertex indices ordered as the DFS visited them.
      return dfsOrder; 
   }
   
   // Desc.: Internal recursive implementation of the DFS traversal of this graph.
   // Input: (g) A graph.
   //        (i) A vertex index (i) as starting vertex of the traversal.
   //        (c) A counter to track traversal visiting order.
   //        (o) An array (input/output) of vertex indices to store traversal order.
   // Output: The number of vertices visited by a single run of the DFS traversal.
   //         The updated input/output array of vertex indices tracking the visiting order.
   // Note: Recursive implementation.
   private static int DFSRecursive( GraphInterface g, int i, int c, int[] o ) {
      c++; // Update visiting counter.
      o[i] = c; // Updating visiting order (c visit is visit of vertex i);
      // Iterate through all graph vertex indices (j).
      for( int j = 0; j < g.getNumberVertices(); j++ ) {
         // Check if vertex i and j are adjacent.
         if( g.isAdjacent( i, j ) ) {
            // Check if vertex j has already been visited or not (if visit order is 0, not visited yet).
            if( o[j] == 0 ) { 
               c = DFSRecursive( g, j, c, o ); // Recursive call.
            } 
         }
      }
      // Return updated counter (representing visits performed by this recursive function).
      return c;
   }
   
   // Desc.: Depth-First Search (DFS) traversal of this graph.
   // Input: A graph.
   // Output: The DFS traversal order is returned as an array of vertex indices.
   // Note: Iterative implementation.
   public static int[] DFSIterative( GraphInterface g ) {
      // Init local variables.
      StackList dfsStack = new StackList();
      int c = 0; // Init counter for DFS traversal.
      int[] vertexVisits = new int[ g.getNumberVertices() ]; // Array index is vertex index, array cell value is visit order (0 means not-visited, 1 means first visit, etc.).
      // Loop through all graph vertices.
      for( int i = 0; i < g.getNumberVertices(); i++ ) {
         // Check if current vertex (index) has already been visited.
         if( vertexVisits[i] == 0 ) { 
            // Visit current vertex (i).
            dfsStack.push( Integer.valueOf(i) );
            c++;
            vertexVisits[i] = c;
            // Perform a DFS run, and stops when DFS stack is empty.
            while( !dfsStack.isEmpty() ) {
               int j = ((Integer) dfsStack.peek()).intValue(); // Read current vertex (j) from top of DFS stack;
               boolean jIsDeadEnd = true; // Init flag to track if current vertex (j) is a dead-end.
               int k = 0; // Init vertex (k) as the vertex we will use to proceed the DFS traversal.
               while( jIsDeadEnd && ( k < g.getNumberVertices() ) ) { 
                  // Check if vertex (k) is adjacent to (j), and if (k) is unvisited.
                  if( g.isAdjacent( j, k ) && ( vertexVisits[k] == 0 ) ) {
                     // Visit vertex (k).
                     dfsStack.push( Integer.valueOf(k) );
                     c++;
                     vertexVisits[k] = c;
                     // Update flag since (j) is not a dead-end.
                     jIsDeadEnd = false;
                  }
                  k++; // Update (k) to switch to the next graph vertex to be checked in the DFS traversal.
               }
               // If (j) is a dead-end, pop it from the stack;
               if( jIsDeadEnd ) { dfsStack.pop(); }
            }
         }
      }
      // Compute DFS order (from vertex visit array).
      int[] dfsOrder = new int[ vertexVisits.length ];
      for( int i = 0; i < vertexVisits.length; i++ ) {
         dfsOrder[ vertexVisits[i] - 1 ] = i;
      }
      // Return result as array of vertex indices ordered as the DFS visited them.
      return dfsOrder;
   }
   
   // Desc.: Breadth-First Search (BFS) traversal of this graph.
   // Input: A graph.
   // Output: The BFS traversal order is returned as an array of vertex indices.
   // Note: Iterative implementation.
   public static int[] BFSIterative( GraphInterface g ) {
      // Init local variables.
      int[] vertexVisits = new int[ g.getNumberVertices() ]; // Array index is vertex index, array cell value is visit order (0 means not-visited, 1 means first visit, etc.).
      int c = 0; // Init counter for DFS traversal.
      // Iterate through all graph vertices.
      for( int i = 0; i < g.getNumberVertices(); i++ ) {
         // Check if current vertex has been already visited.
         if( vertexVisits[i] == 0 ) { 
            // Current vertex not visited yet.
            c++; // Increment visit counter.
            vertexVisits[i] = c; // Set vertex (i) as visited at iteration (c).
            QueueList q = new QueueList(); // Init internal queue.
            q.enqueue( Integer.valueOf(i) ); // Enqueue current vertex.
            int front = -1; // Init queue front vertex index.
            // Iterate until queue is empty.
            while( !q.isEmpty() ) {
               // Get queue front vertex (index).
               front = ((Integer) q.peek()).intValue();
               // Iterate through all graph vertices.
               for( int j = 0; j < g.getNumberVertices(); j++ ) {
                  // Check if current vertex (j) is not visited and adjacent to vertex (front).
                  if( ( g.isAdjacent( front, j ) ) && ( vertexVisits[j] == 0 ) ) {
                     c++; // Update visit counter
                     vertexVisits[j] = c; // Set vertex (j) as visited at iteration (c).
                     q.enqueue( Integer.valueOf(j) ); // Enqueue vertex (j).
                  }
               }
               // Dequeue vertex (front).
               q.dequeue();
            }
         }
      }
      // Compute BFS order (from vertex visit array).
      int[] bfsOrder = new int[ vertexVisits.length ];
      for( int i = 0; i < vertexVisits.length; i++ ) {
         bfsOrder[ vertexVisits[i] - 1 ] = i;
      }
      // Return result as array of vertex indices ordered as the BFS visited them.
      return bfsOrder;
   }

}


