


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-09

package DataStructures.Queue;

// Interface providing a common specification for different ADT queue implementations.
public interface QueueInterface {

   // Desc.: Determines whether this queue is empty.
   // Output: True if this queue is empty, false otherwise.
   public boolean isEmpty();
   
   // Desc.: Removes all the items from this queue.
   public void dequeueAll();
   
   // Desc.: Adds input item to the back of this queue.
   // Input: newItem, the input item to be inserted in this queue.
   // Output: Throws a QueueRuntimeException (non-critical) if this insertion fails.
   public void enqueue( Object newItem ) throws QueueRuntimeException;
   
   // Desc.: Removes the front item of this queue, and returns it.
   // Output: The item just removed from the front of this queue.
   //         Throws a QueueRuntimeException (non-critical) if this removal fails (e.g. this queue is empty).
   public Object dequeue() throws QueueRuntimeException;
   
   // Desc.: Returns the front item of this queue (without removing it).
   // Output: The item at the front of this queue.
   //         Throws a QueueRuntimeException (non-critical) if this retrieval fails (e.g. this queue is empty).
   public Object peek() throws QueueRuntimeException;
   
}


