


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2021-10-12

package DataStructures.List;

// Array-based implementation of the ADT list.
public class ArrayList implements ListInterface {

   private static final int MAX_LIST = 5; // Maximum (physical) size of the list.
   private Object[] items; // An array of list items.
   private int numItems; // Number of items (logical size) of the list.

   // Default constructor.
   public ArrayList() { 
      // Init internal fields.
      this.items = new Object[ MAX_LIST ];
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
   
   // Desc.: Delete all the items in this list.
   public void removeAll() {
      // Init a new array (old array is unreferenced and implicitly marked for garbage collection).
      this.items = new Object[ MAX_LIST ];
      this.numItems = 0;
   }

   // Desc.: Inserts the input item at the input position in this list.
   // Input: An input index (array-like).
   //        An input item.
   // Output: Throws a ListException (non-critical) if this insertion fails because this list is full.
   //         Throws a ListIndexOutOfBoundsException (non-critical) if this insertion fails because input index is invalid.
   public void add( int index, Object item ) throws ListIndexOutOfBoundsException, ListException {
      // Check if internal array is full.
      if( this.numItems == this.MAX_LIST ) {
         // Internal array is full, insertion is impossible, raise the proper runtime error.
         throw new ListException( "Add operation failed, list is full!" );
      }
      // Check in input index is valid.
      if( ( index >= 0 ) && ( index <= numItems ) ) {
         // Input index is valid, perform insertion.
         // Insert new item by right shifting all items at position >= index.
         for( int pos = this.numItems; pos > index; pos-- ) {
            this.items[ pos ] = this.items[ pos-1 ];
         }
         // Right shift completed, insert new item and update number of list items.
         this.items[ index ] = item;
         this.numItems++;
      }
      else {
         // Input index is invalid, insertion is impossible, raise the proper runtime error.
         throw new ListIndexOutOfBoundsException("Add operation failed, input index out of range!");
      }
   }

   // Desc.: Returns the list item at input index.
   // Input: An input index (array-like).
   // Output: Throws a ListIndexOutOfBoundsException (non-critical) if this retrieval fails because input index is invalid.
   public Object get( int index ) throws ListIndexOutOfBoundsException {
      // Check in input index is valid.
      if( ( index >= 0 ) && ( index < this.numItems ) ) {
         // Return list item at input index.
         return this.items[ index ];
      }
      else {
         // Input index is invalid, retrieval is impossible, raise the proper runtime error.
         throw new ListIndexOutOfBoundsException("Get operation failed, input index out of range!");
      }
   }
   
   // Desc.: Deletes the list item at the input position from this list.
   // Input: An input index (array-like).
   // Output: Throws a ListIndexOutOfBoundsException (non-critical) if this removal fails because input index is invalid.
   public void remove( int index ) throws ListIndexOutOfBoundsException {
      // Check in input index is valid.
      if( ( index >= 0 ) && ( index < this.numItems ) ) {
         // Delete item by left shifting all items at position > index.
         for( int pos = index+1; pos < this.numItems; pos++ ) {
            this.items[ pos-1 ] = this.items[ pos ];
         }
         // Empty last data cell to complete the left shift, and update the number of list items.
         this.items[ this.numItems-1 ] = null;
         this.numItems--;
      }
      else {
         // Input index is invalid, removal is impossible, raise the proper runtime error.
         throw new ListIndexOutOfBoundsException("Remove operation failed, input index out of range!");
      }
   }
   
}


