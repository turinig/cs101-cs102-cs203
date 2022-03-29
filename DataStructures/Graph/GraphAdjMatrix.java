


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package DataStructures.Graph;

import java.lang.StringBuilder;
import java.lang.System;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

// Class modeling an undirected/directed unweighted graph represented internally as an adjacency matrix.
public class GraphAdjMatrix {

   private char[] v; // Array of vertices each labeled with a letter.
   private boolean[][] e; // Adjacency matrix to store graph edges.
   private int[] visited; // Array of vertex flags to mark as "visited" each vertex.
   private int edgeNum;
   private int vertexNum;
   
   // Constructor.
   public GraphAdjMatrix( int vNum ) {
      vertexNum = vNum;
      edgeNum = 0;
      v = new char[vertexNum];
      e = new boolean[vertexNum][vertexNum];
      visited = new int[vertexNum];
   }
   
   public int getVNum() { return vertexNum; }
   public int getENum() { return edgeNum; }
   
   // Desc.: Converts this graph into a string.
   // Output: String representation of this graph as its set of vertices and edges.
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append( "V: { " );
      for( int i = 0; i < v.length; i++ ) { sb.append( v[i] + " " ); }
      sb.append( "}, " );
      sb.append( "E: { " );
      for( int i = 0; i < e.length; i++ ) { 
         for( int j = 0; j <= i; j++ ) { 
            if( e[i][j] ) { sb.append( v[i] + "-" + v[j] + " " ); } } }
      sb.append( "}, " );
      sb.append( "visited: { " );
      for( int i = 0; i < v.length; i++ ) { sb.append( visited[i] + " " ); }
      sb.append( "}" );
      return sb.toString();
   }
   
   // Desc.: Adjacency check between 2 vertices (vertex indeces).
   // Output: Returns true if the vertices (indices) in input are adjacent in the adjacency matrix, false otherwise.
   public boolean isAdjacent( int ia, int ib ) {
      if( e[ia][ib] ) { return true; }
      return false;
   }
   
   // Desc.: Get the vertex index of the input vertex label va.
   // Output: The vertex index of the input vertex label va (if found), or -1 (if not found).
   public int getV( char va ) { 
      for( int i = 0; i < vertexNum; i++ ) { 
         if( v[i] == va ) { return i; } }
      return -1;
   }
   
   // Desc.: Getter and setter for the vertex label.
   public char getV( int i ) { return v[i]; }
   public void setV( int i, char va ) { v[i] = va; }
   
   // Desc.: Setter for an edge (using its vertex indices, or its vertex labels).
   public void setE( int ia, int ib ) { e[ia][ib] = true; edgeNum++; }
   public void setE( char va, char vb ) { 
      int ia = getV(va);
      int ib = getV(vb);
      setE( ia, ib );
      edgeNum++;
   }
   
   // Desc.: Getter and setter and resetter for a vertex marker/flag.
   // Output: The getter outputs the vertex marker/flag of the input vertex index.
   // Note: The vertex marker/flag is an integer used during different graph operation to mark different vertices with different values.
   public int getVisited( int i ) { return visited[i]; }
   public void setVisited( int i, int c ) { visited[i] = c; }
   public void resetVisited() { visited = new int[vertexNum]; }
   
   // Desc.: DFS traversal of this graph.
   // Note: The DFS traversal order is stored internally in the "visited" array.
   // Note: Recursive implementation.
   public void dfs1() {
      int c = 0;
      for( int v = 0; v < this.getVNum(); v++ ) {
         if( this.getVisited(v) == 0 ) { 
            c = dfsRec( v, c );
         }
      }
   }
   
   // Desc.: Internal recursive implementation of the DFS traversal of this graph.
   // Output: The number of vertices visited by a single run of the DFS traversal.
   // Note: The DFS traversal order is stored internally in the "visited" array.
   // Note: Recursive implementation.
   private int dfsRec( int v, int c ) {
      c++;
      this.setVisited( v, c );
      for( int w = 0; w < this.getVNum(); w++ ) {
         if( this.isAdjacent( v, w ) ) {
            if( this.getVisited( w ) == 0 ) { 
               c = dfsRec( w, c ); } } }
      return c;
   }
   
   // Desc.: DFS traversal of this graph.
   // Note: The DFS traversal order is stored internally in the "visited" array.
   // Note: Iterative implementation.
   public void dfs2() {
      Stack<Integer> dfsStack = new Stack<Integer>(); // Init DFS stack.
      int c = 0; // Init counter for DFS traversal.
      // Loop through
      for( int v = 0; v < this.getVNum(); v++ ) {
         if( this.getVisited(v) == 0 ) { 
            // Visit current vertex "v"
            dfsStack.push( v );
            c++;
            this.setVisited( v, c );
            // Perform a run of the DFS, and stops only when the stack is empty.
            while( ! dfsStack.empty() ) {
               v = dfsStack.peek(); // Read current vertex "v" from top of the stack;
               boolean vIsDeadEnd = true; // Init flag to track if current vertex "v" is a dead-end.
               int w = 0; // Init vertex "w" as the vertex we will use to proceed the DFS traversal.
               while( vIsDeadEnd && ( w < this.getVNum() ) ) { 
                  // Check if vertex "w" is adjacent to "v", and if "w" is unvisited.
                  if( this.isAdjacent( v, w ) && ( this.getVisited( w ) == 0 ) ) {
                     // Visit vertex "w".
                     dfsStack.push( w );
                     c++;
                     this.setVisited( w, c );
                     // Update flag since "v" is not a dead-end.
                     vIsDeadEnd = false;
                  }
                  w++; // Update "w" to switch to the next graph vertex to be checked in the DFS traversal.
               }
               // If "v" is a dead-end, pop it from the stack;
               if( vIsDeadEnd ) { dfsStack.pop(); }
            }
         }
      }
   }
   
   // Desc.: BFS traversal of this graph.
   // Note: The BFS traversal order is stored internally in the "visited" array.
   // Note: Iterative implementation.
   public void bfs1() {
      int c = 0;
      for( int v = 0; v < this.getVNum(); v++ ) {
         if( this.getVisited(v) == 0 ) { 
            c++;
            this.setVisited( v, c );
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(v);
            int front = -1; 
            while( !queue.isEmpty() ) {
               front = queue.peek();
               for( int w = 0; w < this.getVNum(); w++ ) {
                  if( ( this.isAdjacent( front, w ) ) && ( this.getVisited(w) == 0 ) ) {
                     c++;
                     this.setVisited( w, c );
                     queue.add(w);
                  }
               }
               queue.poll();
            }
         }
      }
   }

}


