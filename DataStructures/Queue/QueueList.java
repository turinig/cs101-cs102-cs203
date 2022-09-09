


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-09

package DataStructures.Queue;

// Reference-based implementation of the ADT queue.
// Note: Implementation using circular linked list with reference to last list node.
public class QueueList implements QueueInterface {

   // Static-nested class Node for the reference-based implementation of the ADT queue.
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
   
   private Node last; // Reference to last node of this queue.
   private int numItems; // Current number of items in this queue.
   
   // Default constructor.
   public QueueList() {
      // Init internal fields to represent an empty queue.
      this.last = null;
      this.numItems = 0;   
   }
   
   // Desc.: Determines whether this queue is empty.
   // Output: True if this queue is empty, false otherwise.
   public boolean isEmpty() { 
      return ( this.numItems == 0 );
   }
   
   // Desc.: Removes all the items from this queue.
   public void dequeueAll() {
      // Reset last reference and number of list items.
      this.last = null;
      this.numItems = 0;
   }
   
   // Desc.: Adds input item to the back of this queue.
   // Input: newItem, the input item to be inserted in this queue.
   // Output: Throws a QueueRuntimeException (non-critical) if this insertion fails.
   public void enqueue( Object newItem ) throws QueueRuntimeException {
      // Check if this queue is empty.
      if( this.numItems == 0 ) {
         // This queue is empty, perform proper insertion.
         Node newNode = new Node( newItem ); // Create a new node storing input item. 
         newNode.next = newNode; // Connect new node to itself.
         this.last = newNode; // Update last reference now pointing to new node.
         this.numItems++; // Update number of items in this queue.
      }
      else {
         // This queue is not empty, perform regular insertion at back of queue.
         Node newNode = new Node( newItem ); // Create a new node storing input item. 
         newNode.next = this.last.next; // Connect new node to 1st list node.
         this.last.next = newNode; // Connect last list node to new node.
         this.last = newNode; // Update last reference now pointing to new node.
         this.numItems++; // Update number of items in this queue.
      }
   }
   
   // Desc.: Removes the front item of this queue, and returns it.
   // Output: The item just removed from the front of this queue.
   //         Throws a QueueRuntimeException (non-critical) if this removal fails (e.g. this queue is empty).
   public Object dequeue() throws QueueRuntimeException {
      // Check if this queue is empty.
      if( this.numItems == 0 ) {
         // This queue is empty, removal failed, throw non-critical exception.
         throw new QueueRuntimeException( "QueueReferenceBased: dequeue failed because queue is empty!" );
      }
      else {
         // This queue is not empty, check if queue has only 1 item.
         if( this.numItems == 1 ) {
            // This queue has only 1 item, return front item, and set this queue as empty.
            Object itemToBeReturned = this.last.item; // Store apart front item (same as last item).
            this.last = null; // Set last reference to represent an empty queue.
            this.numItems--; // Update number of items in this queue.
            return itemToBeReturned; // Returning item stored apart (former front item).
         }
         else {
            // This queue has 2 or more items, remove front item from this queue.
            Object itemToBeReturned = this.last.next.item; // Store apart front item.
            this.last.next = this.last.next.next; // Link last node to 2nd node (bypassing 1st node).
            this.numItems--; // Update number of items in this queue.
            return itemToBeReturned; // Returning item stored apart (former front item).
         }
      }
   }
   
   // Desc.: Returns the front item of this queue (without removing it).
   // Output: The item at the front of this queue.
   //         Throws a QueueRuntimeException (non-critical) if this retrieval fails (e.g. this queue is empty).
   public Object peek() throws QueueRuntimeException {
      // Check if this queue is empty.
      if( this.numItems == 0 ) {
         // This queue is empty, peek failed, throw non-critical exception.
         throw new QueueRuntimeException( "QueueReferenceBased: peek failed because queue is empty!" );
      }
      else {
         // This queue is not empty, return front item.
         return this.last.next.item; // Return front item.
      }
   }
   
}


