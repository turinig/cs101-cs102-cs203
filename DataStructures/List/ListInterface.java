


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-03-29

package DataStructures.List;

// Interface providing the specifications for the ADT list operations.
public interface ListInterface {

   // Desc.: Returns true if this list is empty, false otherwise.
   public boolean isEmpty();

   // Desc.: Returns the number of items in this list.
   public int size();

   // Desc.: Delete all the items in this list.
   public void removeAll();

   // Desc.: Inserts the input item at the input position in this list.
   // Input: An input index (array-like).
   //        An input item.
   // Output: Throws a ListException (non-critical) if this insertion fails because this list is full.
   //         Throws a ListIndexOutOfBoundsException (non-critical) if this insertion fails because input index is invalid.
   public void add(int i, Object o) throws ListIndexOutOfBoundsException, ListException;

   // Desc.: Returns the list item at input index.
   // Input: An input index (array-like).
   // Output: Throws a ListIndexOutOfBoundsException (non-critical) if this retrieval fails because input index is invalid.
   public Object get(int i) throws ListIndexOutOfBoundsException;

   // Deletes an item from the list at a given position.
   // Desc.: Deletes the list item at the input position from this list.
   // Input: An input index (array-like).
   // Output: Throws a ListIndexOutOfBoundsException (non-critical) if this removal fails because input index is invalid.
   public void remove(int i) throws ListIndexOutOfBoundsException;

}


