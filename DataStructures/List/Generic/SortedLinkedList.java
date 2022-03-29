


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package DataStructures.List.Generic;

// SortedLinkedList generic class, implemeting a sorted linked list.
// Note: "T extends Comparable" is an upper bounded type parameter ("T" is any class derived/implementing Comparable).
// Note: "? super T" is a lower bounded wildcards ("?" is any superclass of "T").
public class SortedLinkedList< T extends Comparable< ? super T > > {

   // Static-nested generic Node class for the reference-based generic implementation of the ADT sorted linked list.
   // Note: input type parameter T (non built-in type) represents the type of the list items.
   // Note: static because it does not need access to outer class members.
   // Note: private because it is designed to only be used by the outer class.
   static private class Node<T> {
      T item; // Data (custom type determined by input type parameter.
      Node<T> next; // Reference to next list node.
      // Constructor 1 (init only node data).
      public Node( T o ) { this.item = o; this.next = null; }
      // Constructor 2 (init both node data and reference to next node).
      public Node( T o, Node<T> n ) { this.item = o; this.next = n; }
   }

   private Node<T> head; // Entry point: reference to 1st node, or null if list is empty.
   private int numItems; // Number of items-nodes in the list.

   // Constructor.
   public SortedLinkedList() {
      this.head = null;
      this.numItems = 0;
   }
   
   // Getter for the number of items in this list.
   public int size() { return this.numItems; }

   // Desc.: Insert an object of type T this container.
   // Input: An object (reference) of type T.
   // Output: Throws a checked (critical) exception if the insertion is not successful.
   public void insert( T m ) throws Exception {
      // Setup 2 references to right-before and right-after the insertion point.
      Node<T> currNode = this.head;
      Node<T> prevNode = null;
      // Find point of insertion.
      while( ( currNode != null ) && ( m.compareTo( currNode.item ) < 0 ) ) {
         prevNode = currNode;
         currNode = currNode.next;
      }
      // Wrap input data into a new node.
      Node<T> newNode = new Node<T>( m );
      // Check if insertion is at front, or if list is empty
      if( prevNode == null ) {
         this.head = newNode;
         newNode.next = currNode;
         this.numItems++;
      }
      // Insertion at middle, or at end.
      else {
         newNode.next = currNode;
         prevNode.next = newNode;
         this.numItems++;
      }
   }
      
   // Desc.: Returns the item at position i (array-like index) in the list.
   // Output: Throws an unchecked (non-critical) RuntimeException exception if the index is invalid.
   public T get( int i ) throws RuntimeException {
      // Check if input index is valid.
      if( ( i < 0 ) || ( i >= this.numItems ) ) { throw new RuntimeException( "Error getting list item: index invalid!" ); }
      else {
         // Retrieving the list item ad inded "i".
         Node<T> currNode = this.head;
         int indexCurrNode = 0;
         while( indexCurrNode < i ) {
            indexCurrNode++;
            currNode = currNode.next;
         }
         // Return node at "i" content.
         return currNode.item;
      }   
   }
   
   // Desc.: Deletes the item at position i (array-like index) in the list.
   // Output: Throws an unchecked (non-critical) RuntimeException exception if the index is invalid.
   public void delete( int i ) throws RuntimeException {
      // Check if input index is valid.
      if( ( i < 0 ) || ( i >= this.numItems ) ) { throw new RuntimeException( "Error getting list item: index invalid!" ); }
      else {
         // Retrieving the list item ad inded "i".
         Node<T> prevNode = null;
         Node<T> currNode = this.head;
         int indexCurrNode = 0;
         while( indexCurrNode < i ) {
            indexCurrNode++;
            prevNode = currNode;
            currNode = currNode.next;
         }
         // Delete node at "i" content.
         if( prevNode == null ) {
            this.head = currNode.next;
         }
         else {
            prevNode.next = currNode.next;
         }
      }   
   }
      
}


