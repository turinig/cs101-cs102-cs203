


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-08

package DataStructures.Graph;

import java.lang.String;
import java.lang.StringBuilder;
import java.lang.System;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Desc.: Class modeling an undirected/directed weighted graph represented internally as a weight matrix.
// Note: -1 is a special edge weight marking non-existing edges (non-adjacent graph vertices).
public class GraphWeightMatrix implements GraphInterface {

   private String[] vertexLabels; // Array of vertex labels (each associated with the relative graph/array index).
   private int[][] edges; // Weight matrix to store graph edges (start edge vertex is row index, end edge vertex is column index, edge weight is cell value).
   private int numEdges; // Number of edges in this graph.
   private int numVertices; // Number of vertices in this graph.
   
   // Desc.: Default constructor.
   public GraphWeightMatrix() {
      this.numVertices = 0;
      this.numEdges = 0;
      this.vertexLabels = null;
      this.edges = null;
   }
   
   // Desc.: Constructor.
   // Input: Total number of graph vertices.
   public GraphWeightMatrix( int vn ) {
      this.numVertices = vn;
      this.numEdges = 0;
      this.vertexLabels = new String[ this.numVertices ];
      this.edges = new int[ this.numVertices ][ this.numVertices ];
      for( int i = 0; i < this.edges.length; i++ ) { 
         for( int j = 0; j < this.edges.length; j++ ) { 
            this.edges[i][j] = -1;
         }
      }
   }
   
   // Desc.: Loads graph data (vertices and edges) from file.
   // Input: A file name.
   // Output: Returns true if loading is fully successful, false otherwise.
   public boolean loadFromFile( String fn ) {
      try{ 
         // Init File and Scanner object to start parsing text data.
         File f = new File(fn);
         Scanner s = new Scanner(f);
         // Init number of graph vertices.
         this.numVertices = s.nextInt();
         // Init internal graph data structures.
         this.vertexLabels = new String[ this.numVertices ];
         this.edges = new int[ this.numVertices ][ this.numVertices ];
         // ...
         for( int i = 0; i < this.edges.length; i++ ) { 
            for( int j = 0; j < this.edges.length; j++ ) { 
               this.edges[i][j] = -1;
            }
         }
         // Init graph vertex labels.
         for( int i = 0; i < this.numVertices; i++ ) {
            // Read current graph vertex data (index and label) from current text line.
            int currVertexIndex = s.nextInt();
            String currVertexLabel = s.next();
            s.nextLine();
            // Store current graph vertex data (index and label) internally (array).
            this.vertexLabels[currVertexIndex] = currVertexLabel;
         } 
         // Init number of graph edges.
         this.numEdges = s.nextInt();
         // Init graph edges.
         for( int i = 0; i < this.numEdges; i++ ) {
            // Read current graph edge data (start vertex index, end vertex index, and edge weight) from current text line.
            int currEdgeStartVertexIndex = s.nextInt();
            int currEdgeEndVertexIndex = s.nextInt();
            int currEdgeWeight = s.nextInt();
            // Store current graph edge data (start vertex index, end vertex index, and edge weight) internally (adjacency matrix).
            this.edges[currEdgeStartVertexIndex][currEdgeEndVertexIndex] = currEdgeWeight;
         }
         // Return true because the loading was completed successfully.
         return true;
      }
      catch( FileNotFoundException e ) {}
      // Return false because reaching this statement means that a run-time error (exception) happened during loading from file.
      return false;
   }
   
   // Desc.: Returns the number of graph vertices.
   public int getNumberVertices() { return this.numVertices; }
   
   // Desc.: Returns the number of graph edges.
   public int getNumberEdges() { return this.numEdges; }
   
   // Desc.: Converts this graph into a string.
   @Override
   public String toString(){
      StringBuilder sb = new StringBuilder();
      sb.append( "V: { " );
      for( int i = 0; i < this.vertexLabels.length; i++ ) { sb.append( this.vertexLabels[i] + " " ); }
      sb.append( "}, " );
      sb.append( "E: { " );
      for( int i = 0; i < this.edges.length; i++ ) { 
         for( int j = 0; j < this.edges.length; j++ ) { 
            if( this.edges[i][j] != -1 ) { sb.append( this.vertexLabels[i] + "-" + this.vertexLabels[j] + "(" + this.edges[i][j] + ") "); } 
         }
      }
      sb.append( "}" );
      return sb.toString();
   }
   
   // Desc.: Checks if first input graph vertex (iva) is adjacent to second input graph vertex (ivb) (iff edge <iva,ivb> exists).
   // Input: Two graph vertices (indices).
   // Output: Returns true if the vertices (indices) in input are adjacent (edge <iva,ivb> exists), false otherwise.
   public boolean isAdjacent( int iva, int ivb ) {
      if( this.edges[iva][ivb] != -1 ) { return true; }
      return false;
   }
   
   // Desc.: Returns the index of the input graph vertex (label).
   // Input: Label of a graph vertex.
   // Output: Index of the graph vertex in input (if found), or -1 (if not found).
   public int getVertexIndex( String l ) { 
      for( int i = 0; i < this.numVertices; i++ ) { 
         if( this.vertexLabels[i].equals(l) ) { return i; }
      }
      return -1;
   }
   
   // Desc.: Returns the label of the input graph vertex (index).
   // Input: Index of a graph vertex.
   // Output: Label of the input graph vertex.
   public String getVertexLabel( int i ) { return this.vertexLabels[i]; }
   
   // Desc.: Sets the label of the input graph vertex (index) using the input label.
   // Input: Index of a graph vertex (vi) and label (vl).
   public void setVertexLabel( int vi, String vl ) { this.vertexLabels[vi] = vl; }
   
   // Desc.: Returns the weight of the input edge.
   // Input: Indices of the start (iva) and end (ivb) graph vertices of the edge.
   // Output: Weight of the input edge.
   // Note: -1 is special value representing non-existing edges.
   public int getEdgeWeight( int iva, int ivb ) {
      return this.edges[iva][ivb];
   }
   
   // Desc.: Creates an edge (vertex adjecency) with its weight.
   // Input: Indices of the start (iva) and end (ivb) graph vertices of the edge, and its weight (w).
   // Note: -1 is special value representing non-existing edges.
   public void setEdge( int ia, int ib, int w ) { this.edges[ia][ib] = w; this.numEdges++; }
   
   // Desc.: Creates an edge (vertex adjecency) with its weight.
   // Input: Labels of the start (lva) and end (lvb) graph vertices of the edge, and its weight (w).
   // Note: -1 is special value representing non-existing edges.
   public void setEdge( String lva, String lvb, int w ) { 
      int iva = this.getVertexIndex(lva);
      int ivb = this.getVertexIndex(lvb);
      this.setEdge( iva, ivb, w );
   }
   
}


