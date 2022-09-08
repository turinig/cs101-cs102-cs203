


// CS-102: "Computing and Algorithms II"
// CS-102: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-08

package DataStructures.Stack;

// Array-based implementation of the ADT stack.
public class StackArray implements StackInterface {

   final int MAX_STACK = 50; // Max size (default access modifier: package-private).
   private Object items[]; // Internal array of stack items.
   private int top; // Array index representing the top of the stack.
   
   // Default constructor.
   public StackArray() {
      this.items = new Object[ this.MAX_STACK ]; // Create new internal array with constant max size.
      this.top = -1; // Init array index representing top of empty stack (special value).
   }
   
   // Desc.: Checks if this stack is empty.
   // Output: True if this stack is empty, false otherwise.
   public boolean isEmpty() { 
      return ( this.top < 0 ); // Check if top index is invalid.
   }

   // Desc.: Checks if this stack is full.
   // Output: True if this stack is full, false otherwise.
   // Note: This method is not part of StackInterface.
   public boolean isFull() { 
      return ( this.top == ( this.MAX_STACK - 1 ) ); // Check if top index is last array index.
   }

   // Desc.: Adds input item to the top of this stack.
   // Input: newItem, the input item to be inserted in this stack.
   // Output: Throws a StackRuntimeException (non-critical) if this insertion fails.
   public void push( Object newItem ) throws StackRuntimeException {
      // Check if this stack (internal array) is full.
      if( !isFull() ) { 
         // This stack (internal array) is not full, perform insertion at top.
         this.items[ ++this.top ] = newItem;
         // Note: even if both X++ and ++X increment X by 1: X++ returns X before the increment, whereas ++X returns X after the increment.
      }
      else {
         // This stack (internal array) is full: insertion failed, throw exception. 
         throw new StackRuntimeException( "StackRuntimeException on push: stack full!" );
      }
   }
   
   // Desc.: Removes all the items from this stack.
   public void popAll() { 
      this.items = new Object[ this.MAX_STACK ]; // Create new internal array with constant max size.
      this.top = -1; // Init array index representing top of empty stack (special value).
   }
   
   // Desc.: Removes the top item of this stack, and returns it. Throws StackException if the stack is empty.Inserts the input item at the input position in this list.
   // Output: The item just removed from the top of this stack.
   //         Throws a StackRuntimeException (non-critical) if this removal fails (e.g. this stack is empty).
   public Object pop() throws StackRuntimeException {
      // Check if this stack (internal array) is empty.
      if( !isEmpty() ) { 
         // This stack (internal array) is not empty: return top item, and decrement top index.
         return this.items[ this.top-- ];
      }
      else { 
         // This stack (internal array) is empty: removal failed, throw exception.
         throw new StackRuntimeException( "StackRuntimeException on pop: stack empty!" );
      }
   }

   // Desc.: Returns the top item of this stack (without removing it).
   // Output: The item at the top of this stack.
   //         Throws a StackRuntimeException (non-critical) if this retrieval fails (e.g. this stack is empty).
   public Object peek() throws StackRuntimeException {
      // Check if this stack (internal array) is empty.
      if( !isEmpty() ) { 
         // This stack (internal array) is not empty: return top item.
         return this.items[ this.top ];
      }
      else { 
         // This stack (internal array) is empty: retrieval failed, throw exception.
         throw new StackRuntimeException( "StackRuntimeException on peek: stack empty!" );
      }
   }
   
}


