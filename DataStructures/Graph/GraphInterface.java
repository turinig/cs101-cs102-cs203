


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-08

package DataStructures.Graph;

import java.lang.String;

// Interface providing the specifications for the ADT graph operations.
public interface GraphInterface {

   // Desc.: Loads graph data (vertices and edges) from file.
   // Input: A file name.
   // Output: Returns true if loading is fully successful, false otherwise.
   public boolean loadFromFile( String fn );

   // Desc.: Returns the number of graph vertices.
   public int getNumberVertices();
   
   // Desc.: Returns the number of graph edges.
   public int getNumberEdges();
   
   // Desc.: Converts this graph into a string.
   @Override
   public String toString();
   
   // Desc.: Checks if first input graph vertex (iva) is adjacent to second input graph vertex (ivb) (iff edge <iva,ivb> exists).
   // Input: Two graph vertices (indices).
   // Output: Returns true if the vertices (indices) in input are adjacent (edge <iva,ivb> exists), false otherwise.
   public boolean isAdjacent( int iva, int ivb );
   
   // Desc.: Returns the index of the input graph vertex (label).
   // Input: Label of a graph vertex.
   // Output: Index of the graph vertex in input (if found), or -1 (if not found).
   public int getVertexIndex( String l );
   
   // Desc.: Returns the label of the input graph vertex (index).
   // Input: Index of a graph vertex.
   // Output: Label of the input graph vertex.
   public String getVertexLabel( int i );
   
   // Desc.: Sets the label of the input graph vertex (index) using the input label.
   // Input: Index of a graph vertex (vi) and label (vl).
   public void setVertexLabel( int vi, String vl );
   
   // Desc.: Returns the weight of the input edge.
   // Input: Indices of the start (iva) and end (ivb) graph vertices of the edge.
   // Output: Weight of the input edge.
   public int getEdgeWeight( int iva, int ivb );
   
   // Desc.: Creates an edge (vertex adjecency) with its weight.
   // Input: Indices of the start (iva) and end (ivb) graph vertices of the edge, and its weight (w).
   public void setEdge( int iva, int ivb, int w );
   
   // Desc.: Creates an edge (vertex adjecency) with its weight.
   // Input: Labels of the start (lva) and end (lvb) graph vertices of the edge, and its weight (w).
   public void setEdge( String lva, String lvb, int w );
   
}


