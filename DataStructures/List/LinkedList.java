


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package DataStructures.List;

import DataStructures.List.ListInterface;
import DataStructures.List.ListIndexOutOfBoundsException;
import DataStructures.List.ListException;


// Reference-based implementation of the ADT list.
public class LinkedList implements ListInterface {

   // Static-nested class Node for the reference-based implementation of the ADT list.
   // Note: static because it does not need access to outer class members.
   // Note: private because it is designed to only be used by the outer class.
   static private class Node {
      Object item; // Data.
      Node next; // Reference to next list node.
      // Constructor 1 (init only node data).
      public Node( Object o ) { this.item = o; this.next = null; }
      // Constructor 2 (init both node data and reference to next node).
      public Node( Object o, Node n ) { this.item = o; this.next = n; }
   }

   private Node head; // Entry point to the list (reference to 1st list node).
   private int numItems; // Number of list items.

   // Desc.: Locates a specified node in a linked list.
   // Input: A valid input index (array-like).
   // Output: A reference to the list node at the input index.
   // Note: Iterative implementation.
   private Node find( int index ) {
      // Init traversal variable with head reference.
      Node curr = this.head;
      // Traverse this list until reaching target node.
      for( int skip = 0; skip < index; skip++ ) { 
         curr = curr.next;
      }
      // Return target node.
      return curr;
   }

   // Desc.: Default constructor.
   public LinkedList() { 
      // Init internal fields.
      this.head = null; 
      this.numItems = 0;
   }
   
   // Desc.: Returns true if this list is empty, false otherwise.
   public boolean isEmpty() { 
      return ( this.numItems == 0 );
   }
   
   // Desc.: Returns the number of items in this list.
   public int size() { 
      return this.numItems;
   }
   
   // Desc.: Recalculate the size of this list, updates internal field, and returns the size of this list.
   // Output: The number of items in this list.
   // Note: Recursive implementation.
   public int recalculateSize() {
      int currSize = recalculateSizeRec( this.head ); // Compute this list size recursively.
      this.numItems = currSize; // Update internal field.
      return this.numItems; // Return the size of this list.
   }
   
   // Desc.: Internal recursive implementation for the "recalculateSize" method.
   // Input: An input list, represented by the reference to its 1st node.
   // Output: Number of items in input list.
   // Note: Recursive implementation.
   private int recalculateSizeRec( Node currHead ) {
      // Check if input list is empty.
      if( currHead == null ) {
         // Here input list is empty, return 0.
         return 0;
      }
      else {
         // Here input list is not empty: compute size of rest of list, add 1, return result.
         int sizeRestOfList = recalculateSizeRec( currHead.next ); // Recursive call.
         return 1 + sizeRestOfList; // Return size of input list.
      }
   }
   
   // Desc.: Delete all the items in this list.
   public void removeAll() {
      this.head = null; // Set head to null (1st node is now unreferenced, marked for garbage collection, with rest of list).
      this.numItems = 0; // Update number of list items.
   }
   
   // Desc.: Inserts the input item at the input index in this list.
   // Input: An input index (array-like).
   //        An input item.
   // Output: Throws a ListException (non-critical) if this insertion fails because this list is full.
   //         Throws a ListIndexOutOfBoundsException (non-critical) if this insertion fails because input index is invalid.
   // Note: Iterative implementation.
   public void add( int index, Object item ) throws ListIndexOutOfBoundsException {
      // Check if input index is valid.
      if( ( index >= 0 ) && ( index < ( this.numItems + 1 ) ) ) {
         // Check if input index represents a special case (insertion at front).
         if( index == 0 ) {
            // Insertion at front: create a new node, link it to former 1st node, and update head reference.
            Node newNode = new Node( item, this.head );
            this.head = newNode;
         }
         else {
            // Insertion in the middle or at the end: find node before insertion point, and perform insertion.
            Node prev = find( index - 1 );
            Node newNode = new Node( item, prev.next );
            prev.next = newNode;
         }
         // Update number of list items.
         this.numItems++;
      }
      else {
         // Input index is invalid, insertion is impossible, raise the proper runtime error.
         throw new ListIndexOutOfBoundsException("Add operation failed, input index out of range!");
      }
   }
   
   // Desc.: Inserts the input item at the input index in this list.
   // Input: index, input index (array-like) to perform insertion at.
   //        item, input item to be inserted in this list.
   // Output: Throws a ListException (non-critical) if this insertion fails because this list is full.
   //         Throws a ListIndexOutOfBoundsException (non-critical) if this insertion fails because input index is invalid.
   // Note: Recursive implementation.
   public void insert( int index, Object item ) throws ListIndexOutOfBoundsException {
      // Check if input index is valid.
      if( ( index >= 0 ) && ( index < ( this.numItems + 1 ) ) ) {
         // Here input index is valid, perform insertion recursively.
         this.head = insertRec( this.head, index, item ); // Recursive call.
         // Update number of list items.
         this.numItems++;
      }
      else {
         // Input index is invalid, insertion is impossible, raise the proper runtime error.
         throw new ListIndexOutOfBoundsException("Add operation failed, input index out of range!");
      }
   }
   
   // Desc.: Recursive implementation for the "insert" method.
   // Input: currHead, reference to 1st node of input list.
   //        index, input index (array-like) to perform insertion at.
   //        item, input item to be inserted in input list.
   // Output: Reference to 1st node of input list (could be same as currHead or not).
   // Note: Recursive implementation.
   // Note: Input idex is valid (already checked elsewhere).
   private Node insertRec( Node currHead, int index, Object item ) {
      // Check if insertion is at front.
      if( index == 0 ) {
         // Insertion at front: create new node to store input item, connect it to input list, and return it.
         Node newNode = new Node( item );
         newNode.next = currHead;
         return newNode;
         // Warning: (if needed) connecting previous list to newNode is done by caller code.
      }
      else {
         // Insertion not at front: insert in rest of list (recursively), reconnect with rest of list, return currHead.
         Node newHeadRestOfList = insertRec( currHead.next, index-1, item );
         currHead.next = newHeadRestOfList;
         return currHead;
      }
   }
   
   // Desc.: Returns the list item at input index.
   // Input: An input index (array-like).
   // Output: Throws a ListIndexOutOfBoundsException (non-critical) if this retrieval fails because input index is invalid.
   // Note: Iterative implementation.
   public Object get( int index ) throws ListIndexOutOfBoundsException {
      // Check if input index is valid.
      if( ( index >= 0 ) && ( index < this.numItems ) ) {
         // Input index is valid, find target node and return its content (data).
         Node curr = find( index );
         return curr.item;
      }
      else {
         // Input index is invalid, retrieval is impossible, raise the proper runtime error.
         throw new ListIndexOutOfBoundsException("Get operation failed, input index out of range!");
      }
   }

   // Desc.: Deletes the list item at the input position from this list.
   // Input: An input index (array-like).
   // Output: Throws a ListIndexOutOfBoundsException (non-critical) if this removal fails because input index is invalid.
   // Note: Iterative implementation.
   public void remove( int index ) throws ListIndexOutOfBoundsException {
      // Check if input index is valid.
      if( ( index >= 0 ) && ( index < this.numItems ) ) {
         // Check if input index represents a special case (removal at front).
         if( index == 0 ) { 
            // Removal at front: update head reference to bypass 1st list node (now unreferenced and marker for garbage collection).
            this.head = this.head.next;
         }
         else {
            // Removal in the middle or at the end: find node before removal point, and perform removal.
            Node prev = find( index - 1 );
            Node curr = prev.next;
            prev.next = curr.next;
         }
         // Update number of list items.
         this.numItems--;
      }
      else {
         // Input index is invalid, removal is impossible, raise the proper runtime error.
         throw new ListIndexOutOfBoundsException("Remove operation failed, input index out of range!");
      }
   }
   
   // Desc.: Removes duplicates items (items with same reference) from the list, but keeping the 1st item occurrence in the list.
   // Note: In-place implementation.
   // Note: Iterative implementation.
   public void removeDuplicates() {
      // Check if the list is long enough to have duplicate items.
      if( this.numItems > 1 ) {
         // List is long enough to have duplicate items, search and remove all of them.
         // Iterate through all list items from 1st node to node before last.
         Node currNode = this.head; // Set current node to 1st list node.
         while( currNode.next != null ) {
            // Scan rest of list (from currNode forward).
            Node currDuplicate = currNode.next; // Set current duplicate to node right after current node.
            Node prevDuplicate = currNode; // Set previous duplicate to node right before current duplicate.
            while( currDuplicate != null ) {
               // Check if currDuplicate stores an item that is a duplicate (reference) of item stored in currNode.
               if( currNode.item.equals( currDuplicate.item ) ) {
                  // Duplicate found, delete node pointed by currDuplicate (modify prevDuplicate so that it bypasses currDuplicate).
                  prevDuplicate.next = currDuplicate.next; // Bypassing currDuplicate.
                  this.numItems--;
                  // Update previous duplicate and current duplicate, switching both of them to next list node.
                  // WARNING: do not update/change prevDuplicate!.
                  currDuplicate = currDuplicate.next;
               }
               else {
                  // Duplicate not found, do nothing.
                  // Update previous duplicate and current duplicate, switching both of them to next list node.
                  prevDuplicate = currDuplicate;
                  currDuplicate = currDuplicate.next;
               }
            }
            // Update current node switching it to next list node.
            currNode = currNode.next;
            // Special case: last removal has made currNode the last node, quit immediately to avoid executing loop header.
            if( currNode == null ) {
               return;
            }
         }
      }
      else {
         // List is not long enough to have duplicate items, do nothing and return.
         return;
      }
   }
   
   // Desc.: Prints all the list items from 1st to last.
   // Output: All list items, separated by a newline, printing a special message if this list is empty.
   // Note: Recursive implementation.
   public void print() {
      // Check if this list is empty.
      if( this.numItems == 0 ) {
         // Here this list is empty, print special message.
         System.out.println( "List is empty, nothing to print." );
      }
      else {
         // Here this list is not empty, print list recursively.
         printRec( this.head ); // Recursive call to print (recursively) this list.
      }
   }

   // Desc.: Recursive implementation for the "print" method.
   // Input: Reference to the 1st node of the input list.
   // NOte: Recursive implementation.
   private void printRec( Node currHead ) {
      // Check if input list (represented by "currHead") is empty.
      if( currHead == null ) {
         // Here input list is empty, nothing to print.
         return;
      }
      else {
         // Here input list is not empty: print item at 1st node, and print rest of list recursively.
         System.out.println( currHead.item );
         printRec( currHead.next ); // Recursive call, pass rest of list (represented by "currHead.next").
      }
   }

}


