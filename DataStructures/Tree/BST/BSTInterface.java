


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-06-17

package DataStructures.Tree.BST;

// Interface providing the specifications for the ADT BST operations.
public interface BSTInterface {

   // Desc.: Inserts the input key (and its item) in this BST.
   // Input: newKey, input key to be inserted in this BST.
   //        newItem, item associated with input key.
   // Output: Throws a BSTException (critical) if insertion fails because input key is already in BST.
   public void insert( String newKey, Object newItem ) throws BSTException;
   
   // Desc.: Searches input key in this BST.
   // Input: key, input key to be searched in this BST.
   // Output: The item associated with input key (if search is successful).
   //         Throws a BSTException (critical) if search fails because input key is not in BST.
   public Object search( String key ) throws BSTException;
   
   // Desc.: Deletes the input key (and its item) from this BST.
   // Input: key, input key to be deleted from this BST.
   // Output: Throws a BSTException (critical) if deletion fails because input key is not found in BST.
   public void delete( String key ) throws BSTException;
   
   // Desc.: Prints the content of this BST using preorder traversal.
   // Output: Throws a BSTRuntimeException (non-critical) if this BST is empty.
   public void printPreorder() throws BSTRuntimeException;
   
   // Desc.: Prints the content of this BST using inorder traversal.
   // Output: Throws a BSTRuntimeException (non-critical) if this BST is empty.
   public void printInorder() throws BSTRuntimeException;
   
   // Desc.: Prints the content of this BST using postorder traversal.
   // Output: Throws a BSTRuntimeException (non-critical) if this BST is empty.
   public void printPostorder() throws BSTRuntimeException;
   
   // Desc.: Returns true if this BST is empty, false otherwise.
   public boolean isEmpty();

   // Desc.: Returns the number of items in this BST.
   public int size();

   // Desc.: Delete all the items in this BST.
   public void deleteAll();

}


