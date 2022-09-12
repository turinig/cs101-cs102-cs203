


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-12

package Algorithms.Greedy;

import DataStructures.Graph.GraphInterface;

import java.lang.Integer;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

// "Static" class including some greedy algorithms to solve the minimum spanning tree (MST) problem.
public final class MinimumSpanningTree {

   // Static-nested class GraphVertex for the Prim implementation for the MST problem.
   // Note: static because it does not need access to outer class members.
   // Note: private because it is designed to only be used by the outer class.
   static private class GraphVertex {
      private String label;
      private Map<GraphVertex, GraphEdge> edges;
      private boolean selected;
   }

   // Static-nested class GraphEdge for the Prim implementation for the MST problem.
   // Note: static because it does not need access to outer class members.
   
   static public class GraphEdge {
      private int indexVertexStart;
      private int indexVertexEnd;
      private float weight;
      private boolean selected;
   }
   
   // Static-nested class PairVertexEdge for the Prim implementation for the MST problem.
   // Note: static because it does not need access to outer class members.
   // Note: private because it is designed to only be used by the outer class.
   static private class PairVertexEdge {
      private GraphVertex vertex;
      private GraphEdge edge;
   }
   
   // Note: private default constructor to forbid instantiation.
   private MinimumSpanningTree () {} 
   
   // Desc.: Internal function to check if input graph is still disconnected (has at least 1 vertex not selected).
   // Input: A graph (as an array of vertices).
   // Output: True or false.
   private static boolean isDisconnected( GraphVertex[] g ) {
      for(GraphVertex v : g) { if( !v.selected ) { return true; } }
      return false;
   }
   
   // Desc.: Internal function to select the edge with minimum weight connecting input vertex (selected) to an unselected vertex.
   // Input: A graph vertex.
   // Output: A pair vertex-edge. 
   private static PairVertexEdge getVertexNextMinEdge( GraphVertex v ) {
      // Init local variables.
      GraphEdge nextMinEdge = new GraphEdge();
      nextMinEdge.weight = Integer.MAX_VALUE;
      GraphVertex nextVertex = v;
      Iterator< Map.Entry<GraphVertex, GraphEdge> > iter = v.edges.entrySet().iterator();
      // Iterate through all edges of input vertex.
      while( iter.hasNext() ) {
         Map.Entry<GraphVertex, GraphEdge> pair = iter.next();
         // Check if current end vertex and current edge have already been selected or not.
         if( !pair.getKey().selected ) {
            if( !pair.getValue().selected ) {
               // Check weight of current edge.
               if( pair.getValue().weight < nextMinEdge.weight ) {
                  // Current edge has minimum weight, update temporary result.
                  nextMinEdge = pair.getValue();
                  nextVertex = pair.getKey();
               }
            }
         }
      }
      // Prepare result.
      PairVertexEdge result = new PairVertexEdge();
      result.vertex = nextVertex;
      result.edge = nextMinEdge;
      return result;
   }
   
   // Desc.: Prim's algorithm (greedy) to compute the minimum spanning tree (MST) of the input graph.
   // Input: A graph.
   // Output: An array of graph edges.
   public static GraphEdge[] Prim( GraphInterface g ) {
      // Init local variables.
      int numberEdgesSelected = 0;
      // Init internal vertex array.
      GraphVertex[] vertices = new GraphVertex[ g.getNumberVertices() ];
      for( int i = 0; i < g.getNumberVertices(); i++) {
         vertices[i] = new GraphVertex();
         vertices[i].label = g.getVertexLabel(i);
         vertices[i].edges = new HashMap<GraphVertex, GraphEdge>();
         vertices[i].selected = false;
      }
      // Init internal edge array.
      int numberEdges = 0;
      for( int i = 0; i < g.getNumberVertices(); i++) {
         for( int j = 0; j < g.getNumberVertices(); j++) {
            int w = g.getEdgeWeight(i, j);
            if(w != -1) {
               numberEdges++;
            }
         }
      }
      GraphEdge[] edges = new GraphEdge[ numberEdges ];
      int indexEdge = 0;
      for( int i = 0; i < g.getNumberVertices(); i++) {
         for( int j = 0; j < g.getNumberVertices(); j++) {
            int w = g.getEdgeWeight(i, j);
            if(w != -1) {
               edges[indexEdge] = new GraphEdge();
               edges[indexEdge].indexVertexStart = i;
               edges[indexEdge].indexVertexEnd = j;
               edges[indexEdge].weight = w;
               edges[indexEdge].selected = false;
               vertices[i].edges.put( vertices[j], edges[indexEdge] );
               indexEdge++;
            }
         }
      }
      // Prim's algorithm to compute MST of input graph.
      // Init starting vertex.
      if( g.getNumberVertices() > 0 ) { vertices[0].selected = true; }
      // Iterate until all vertices have been selected.
      while( isDisconnected(vertices) ) {
         // Init current edge with minimum weight.
         GraphEdge nextMinEdge = new GraphEdge();
         nextMinEdge.weight = Integer.MAX_VALUE;
         // Init current vertex with first graph vertex.
         GraphVertex nextVertex = vertices[0];
         // Iterate through all graph vertices.
         for( GraphVertex v : vertices) {
            // Check if current vertex has already been selected.
            if( v.selected ) {
               // Current vertex is selected, search for its minimum weight outgoing edge.
               PairVertexEdge candidate = getVertexNextMinEdge(v);
               // Check if edge found is better than current minimum weight edge.
               if( candidate.edge.weight < nextMinEdge.weight ) {
                  nextMinEdge = candidate.edge;
                  nextVertex = candidate.vertex;
               }
            }
         }
         // Perform selection of current minimum weight edge (and associated ending vertex).
         nextMinEdge.selected = true;
         nextVertex.selected = true;
         numberEdgesSelected++;
      }
      // Prepare result.
      GraphEdge[] result = new GraphEdge[numberEdgesSelected];
      int j = 0;
      for( int i = 0; i < edges.length; i++ ) {
         if( edges[i].selected ) { result[j] = edges[i]; j++; }
      }
      return result;
   }
   
}


