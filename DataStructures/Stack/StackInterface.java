


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-08

package DataStructures.Stack;

// Interface providing a common specification for different ADT stack implementations.
public interface StackInterface {

   // Desc.: Determines whether this stack is empty.
   // Output: True if this stack is empty, false otherwise.
   public boolean isEmpty();

   // Desc.: Removes all the items from this stack.
   public void popAll();
   
   // Desc.: Adds input item to the top of this stack.
   // Input: newItem, the input item to be inserted in this stack.
   // Output: Throws a StackRuntimeException (non-critical) if this insertion fails.
   public void push( Object newItem ) throws StackRuntimeException;
   
   // Desc.: Removes the top item of this stack, and returns it.
   // Output: The item just removed from the top of this stack.
   //         Throws a StackRuntimeException (non-critical) if this removal fails (e.g. this stack is empty).
   public Object pop() throws StackRuntimeException;
   
   // Desc.: Returns the top item of this stack (without removing it).
   // Output: The item at the top of this stack.
   //         Throws a StackRuntimeException (non-critical) if this retrieval fails (e.g. this stack is empty).
   public Object peek() throws StackRuntimeException;
   
}
   

