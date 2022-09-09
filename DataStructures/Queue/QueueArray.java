


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-09

package DataStructures.Queue;

// Array-based implementation of the ADT queue.
public class QueueArray implements QueueInterface {

   private final int MAX_QUEUE = 50; // Maximum size of this queue (physical size).
   private Object[] items; // Internal array to store items in this queue.
   private int front; // Index of the queue front.
   private int back; // Index of the queue back.
   private int count; // Number of items in this queue (logical size).
   
   // Default constructor.
   public QueueArray() {
      this.items = new Object[ this.MAX_QUEUE ];
      this.front = 0;
      this.back = this.MAX_QUEUE - 1;
      this.count = 0;
   }
   
   // Desc.: Determines whether this queue is empty.
   // Output: True if this queue is empty, false otherwise.
   public boolean isEmpty() { 
      return ( this.count == 0 );
   }
   
   // Desc.: Determines whether this queue is full.
   // Output: True if this queue is full, false otherwise.
   // Note: This method is not part of the interface.
   public boolean isFull() { 
      return ( this.count == this.MAX_QUEUE );
   }
   
   // Desc.: Removes all the items from this queue.
   public void dequeueAll() {
      // Re-init all internal fields.
      this.items = new Object[ this.MAX_QUEUE ];
      this.front = 0;
      this.back = this.MAX_QUEUE - 1;
      this.count = 0;
   }
   
   // Desc.: Adds input item to the back of this queue.
   // Input: newItem, the input item to be inserted in this queue.
   // Output: Throws a QueueRuntimeException (non-critical) if this insertion fails.
   public void enqueue( Object newItem ) throws QueueRuntimeException {
      // Check if this queue is full.
      if( !isFull() ) {
         // This queue is not full, perform enqueue.
         this.back = ( this.back + 1 ) % this.MAX_QUEUE; // Update back index.
         this.items[ this.back ] = newItem; // Store input item at new back index.
         this.count++; // Update number of items in this queue.
      }
      else { 
         // This queue is full, enqueue/insertion impossible, throw exception.
         throw new QueueRuntimeException( "QueueRuntimeException on enqueue: queue full!" );
      }
   }
   
   // Desc.: Removes the front item of this queue, and returns it.
   // Output: The item just removed from the front of this queue.
   //         Throws a QueueRuntimeException (non-critical) if this removal fails (e.g. this queue is empty).
   public Object dequeue() throws QueueRuntimeException {
      // Check if this queue is empty.
      if( !isEmpty() ) {
         // This queue is not empty, perform dequeue.
         Object queueFront = this.items[ this.front ]; // Store temporarily front item in local variable.
         this.items[ this.front ] = null; // Optional (unnecessary): clean cell at front index.
         this.front = ( this.front + 1 ) % this.MAX_QUEUE; // Update front index.
         this.count--; // Update number of items in this queue.
         return queueFront; // Return front item (previously stored in local variable).
      }
      else { 
         // This queue is empty, dequeue/removal impossible, throw exception.
         throw new QueueRuntimeException( "QueueRuntimeException on dequeue: queue empty!" );
      }
   }
   
   // Desc.: Returns the front item of this queue (without removing it).
   // Output: The item at the front of this queue.
   //         Throws a QueueRuntimeException (non-critical) if this retrieval fails (e.g. this queue is empty).
   public Object peek() throws QueueRuntimeException {
      // CHeck if this queue is empty.
      if( !isEmpty() ) { 
         // This queue is not empty, return front item.
         return this.items[ this.front ];
      }
      else { 
         // This queue is empty, peek impossible, throw exception.
         throw new QueueRuntimeException( "QueueRuntimeException on peek: queue empty!" );
      }
   }
   
}


