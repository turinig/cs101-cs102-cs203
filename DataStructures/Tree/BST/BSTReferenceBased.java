


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-06-17

package DataStructures.Tree.BST;

// Reference-based implementation of a BST (binary search tree).
public class BSTReferenceBased implements BSTInterface {
   
   // Static-nested class Node for the reference-based implementation of the ADT BST.
   // Note: static because it does not need access to outer class members.
   // Note: private because it is designed to only be used by the outer class.
   static private class Node {
      String key; // Unique key of the item.
      Object item; // Data.
      Node childLeft; // Reference to left child node.
      Node childRight; // Reference to right child node.
      // Constructor (init only node key and data, not links).
      public Node( String k, Object i ) { this.key = k; this.item = i; this.childLeft = null; this.childRight = null; }
   }
   
   private Node root; // Entry point to the BST (reference to root node).
   private int numItems; // Number of items/keys in this BST.
      
   // Default constructor.
   public BSTReferenceBased() {
      // Init this BST as empty.
      this.root = null;
      this.numItems = 0;
   }
   
   // Desc.: Inserts the input key in this BST.
   // Input: newKey, input key to be inserted in this BST.
   //        newItem, item associated with input key.
   // Output: Throws a BSTException (critical) if insertion fails because input key is already in BST.
   public void insert( String newKey, Object newItem ) throws BSTException {
      this.root = insertRec( this.root, newKey, newItem ); // Call to internal recursive implementation.
      this.numItems++; // Update number of items if insertion does not fail.
   }
   
   // Desc.: Recursive implementation for the "insert" method.
   // Input: currRoot, reference to root node of input BST.
   //        newKey, input key to be inserted in input BST.
   //        newItem, item associated with input key.
   // Output: Reference to root node of input BST (could be same as currRoot or not).
   // Output: Throws a BSTException (critical) if insertion fails because input key is already in BST.
   // Note: Recursive implementation.
   private Node insertRec( Node currRoot, String newKey, Object newItem ) throws BSTException {
      // Check if input BST is empty.
      if( currRoot == null ) {
         // Input BST is empty: create new node storing input key, return it as the new input BST root.
         Node newNode = new Node( newKey, newItem );
         return newNode;
      }
      else {
         // Input BST is not empty: compare input item with currRoot, and proceed insertion.
         int comparisonResult = newKey.compareTo( currRoot.key );
         // Check result of 3-way comparison.
         if( comparisonResult == 0 ) {
            // Input key equals key stored at currRoot, insertion failed because input key is a duplicate, throw critical exception.
            throw new BSTException( "BSTReferenceBased: insert failed because input key is already in BST!" );
         }
         else if( comparisonResult < 0 ) {
            // Input key is less than key stored at currRoot, proceed insertion in left subtree of currRoot.
            currRoot.childLeft = insertRec( currRoot.childLeft, newKey, newItem ); // Recursive call.
            return currRoot;
         }
         else {
            // Input key is greater than key stored at currRoot, proceed insertion in right subtree of currRoot.
            currRoot.childRight = insertRec( currRoot.childRight, newKey, newItem ); // Recursive call.
            return currRoot;
         }
      }
   }
   
   // Desc.: Searches input key in this BST.
   // Input: key, input key to be searched in this BST.
   // Output: The item associated with input key (if search is successful).
   //         Throws a BSTException (critical) if search fails because input key is not in BST.
   public Object search( String key ) throws BSTException {
      return searchRec( this.root, key ); // Call to internal recursive implementation.
   }
   
   // Desc.: Recursive implementation for the "search" method.
   // Input: currRoot, reference to root node of input BST.
   //        key, input key to be searched in input BST.
   // Output: The item associated with input key (if search is successful).
   //         Throws a BSTException (critical) if search fails because input key is not in BST.
   // Note: Recursive implementation.
   private Object searchRec( Node currRoot, String key ) throws BSTException {
      // Check if input BST is empty.
      if( currRoot == null ) {
         // Input BST is empty, search failed, throw critical exception. 
         throw new BSTException( "BSTReferenceBased: search failed because input key is not in BST!" );
      }
      else {
         // Input BST is not empty: compare input key with currRoot, and proceed search.
         int comparisonResult = key.compareTo( currRoot.key );
         // Check result of 3-way comparison.
         if( comparisonResult == 0 ) {
            // Input key equals key stored at currRoot, search successful, return true.
            return currRoot.item;
         }
         else if( comparisonResult < 0 ) {
            // Input key is less than key stored at currRoot, proceed search in left subtree of currRoot.
            return searchRec( currRoot.childLeft, key ); // Recursive call.
         }
         else {
            // Input key is greater than key stored at currRoot, proceed search in right subtree of currRoot.
            return searchRec( currRoot.childRight, key ); // Recursive call.
         }
      }
   }
   
   // Desc.: Deletes the input key from this BST.
   // Input: key, input key to be deleted from this BST.
   // Output: Throws a BSTException (critical) if deletion fails because input key is not found in BST.
   public void delete( String key ) throws BSTException {
      this.root = deleteRec( this.root, key ); // Call to internal recursive implementation.
      this.numItems--; // Update number of items if deletion does not fail.
   }
   
   // Desc.: Recursive implementation for the "delete" method.
   // Input: currRoot, reference to root node of input BST.
   //        key, input key to be deleted from input BST.
   // Output: Reference to root node of input BST (could be same as currRoot or not).
   // Output: Throws a BSTException (critical) if deletion fails because input key is not found in BST.
   // Note: Recursive implementation.
   private Node deleteRec( Node currRoot, String key ) throws BSTException {
      // Check if input BST is empty.
      if( currRoot == null ) { 
         // Input BST is empty, input key not found, deletion failed, throw critical exception.
         throw new BSTException( "BSTReferenceBased: delete failed because input key is not in BST!" );
      }
      else {
         // Input BST is not empty: compare input key with currRoot, and proceed deletion.
         int comparisonResult = key.compareTo( currRoot.key );
         // Check result of 3-way comparison.
         if( comparisonResult == 0 ) {
            // Input key equals key stored at currRoot, delete currRoot.
            return deleteNodeRec( currRoot ); // Delete currRoot, and return new root node of input BST.
         }
         else if( comparisonResult < 0 ) {
            // Input key is less than key stored at currRoot, proceed insertion in left subtree of currRoot.
            currRoot.childLeft = deleteRec( currRoot.childLeft, key ); // Recursive call.
            return currRoot;
         }
         else {
            // Input key is greater than key stored at currRoot, proceed insertion in right subtree of currRoot.
            currRoot.childRight = deleteRec( currRoot.childRight, key ); // Recursive call.
            return currRoot;
         }
      }
   }

   // Desc.: Deletes input/root node from input BST.
   // Input: currRoot, reference to node to be deleted (root node of input BST).
   // Output: Reference to new root node of input BST (is always different than currRoot).
   // Note: Assuming input/root node is valid (never null).
   private Node deleteNodeRec( Node currRoot ) {
      // Check if currRoot has left child.
      if( currRoot.childLeft == null ) {
         // currRoot has no left child, check if currRoot has right child.
         if( currRoot.childRight == null ) { 
            // currRoot has no children (leaf), return null (as new root node of input BST now empty).
            return null;
         }
         else {
            // currRoot has only right child, return its right child (as new root node of input BST).
            return currRoot.childRight;
         }
      }
      else {
         // currRoot has left child, check if currRoot has right child.
         if( currRoot.childRight == null ) {
            // currRoot has only left child, return its left child (as new root node of input BST).
            return currRoot.childLeft;
         }
         else {
            // currRoot has both left and right children...
            // ...(A) find inorder successor (leftmost node of right subtree of currRoot)...
            // ...(B) swap content (key and data) between currRoot and its inorder successor...
            // ...then (C) delete inorder successor.
            Node replacementNode = findLeftmostRec( currRoot.childRight ); // (A)
            Node replacementChildRight = deleteLeftmostRec( currRoot.childRight ); // (C1)
            currRoot.key = replacementNode.key; // (B1)
            currRoot.item = replacementNode.item; // (B2)
            currRoot.childRight = replacementChildRight; // (C2)
            return currRoot;
         }
      }
   }

   // Desc.: Returns leftmost node of input BST.
   // Input: currRoot, reference to root node of input BST.
   // Output: Reference to leftmost node of input BST (could be same as currRoot or not).
   // Note: Assuming input/root node is valid (never null).
   // Note: Recursive implementation.
   private Node findLeftmostRec( Node currRoot ) {
      // Check if currRoot is leftmost node (has left child) of input BST.
      if( currRoot.childLeft == null ) {
         // currRoot has no left child, currRoot is leftmost node of input BST, return currRoot. 
         return currRoot;
      }
      else {
         // currRoot has left child, proceed searching leftmost node in left subtree of currRoot. 
         return findLeftmostRec( currRoot.childLeft ); // Recursive call.
      }
   }
   
   // Desc.: Deletes leftmost node of input BST.
   // Input: currRoot, reference to root node of input BST.
   // Output: Reference to new root node of input BST (could be same as currRoot or not).
   // Note: Assuming input/root node is valid (never null).
   // Note: Recursive implementation.
   private Node deleteLeftmostRec( Node currRoot ) {
      // Check if currRoot is leftmost node (has left child) of input BST.
      if( currRoot.childLeft == null ) { 
         // currRoot has no left child, currRoot is leftmost node, delete it by returning its right child as new root of input BST.
         return currRoot.childRight;
      }
      else {
         // currRoot has left child, proceed leftmost node deletion in left subtree of currRoot.
         Node replacementChildLeft = deleteLeftmostRec( currRoot.childLeft ); // Recursive call.
         currRoot.childLeft = replacementChildLeft;
         return currRoot;
      }
   }
   
   // Desc.: Prints the content of this BST using preorder traversal.
   // Output: Throws a BSTRuntimeException (non-critical) if this BST is empty.
   public void printPreorder() throws BSTRuntimeException {
      // Check if this BST is empty.
      if( this.numItems == 0 ) {
         // Ths BST is empty, print failed, throw non-critical exception.
         throw new BSTRuntimeException( "BSTReferenceBased: print failed because BST is empty!" ); 
      }
      else {
         // This BST is not empty, print its content using preorder traversal.
         printPreorderRec( this.root );// Call to internal recursive implementation.
      }
   }
   
   // Desc.: Recursive implementation for the printPreorder method.
   // Input: currRoot, reference to root node of input BST.
   // Note: Recursive implementation.
   private void printPreorderRec( Node currRoot ) {
      // Check if input BST is empty.
      if( currRoot == null ) {
         // Input BST is empty, do nothing.
      }
      else {
         // Input BST is not empty, print content using preorder traversal.
         System.out.println( currRoot.key + ", " + currRoot.item ); // Print content of currRoot.
         printPreorderRec( currRoot.childLeft ); // Recursive call to print left subtree of currRoot.
         printPreorderRec( currRoot.childRight ); // Recursive call to print right subtree of currRoot.
      }
   }
   
   // Desc.: Prints the content of this BST using inorder traversal.
   // Output: Throws a BSTRuntimeException (non-critical) if this BST is empty.
   public void printInorder() throws BSTRuntimeException {
      // Check if this BST is empty.
      if( this.numItems == 0 ) {
         // Ths BST is empty, print failed, throw non-critical exception.
         throw new BSTRuntimeException( "BSTReferenceBased: print failed because BST is empty!" ); 
      }
      else {
         // This BST is not empty, print its content using inorder traversal.
         printInorderRec( this.root );// Call to internal recursive implementation.
      }
   }
   
   // Desc.: Recursive implementation for the printInorder method.
   // Input: currRoot, reference to root node of input BST.
   // Note: Recursive implementation.
   private void printInorderRec( Node currRoot ) {
      // Check if input BST is empty.
      if( currRoot == null ) {
         // Input BST is empty, do nothing.
      }
      else {
         // Input BST is not empty, print content using inorder traversal.
         printInorderRec( currRoot.childLeft ); // Recursive call to print left subtree of currRoot.
         System.out.println( currRoot.key + ", " + currRoot.item ); // Print content of currRoot.
         printInorderRec( currRoot.childRight ); // Recursive call to print right subtree of currRoot.
      }
   }
   
   // Desc.: Prints the content of this BST using postorder traversal.
   // Output: Throws a BSTRuntimeException (non-critical) if this BST is empty.
   public void printPostorder() throws BSTRuntimeException {
      // Check if this BST is empty.
      if( this.numItems == 0 ) {
         // Ths BST is empty, print failed, throw non-critical exception.
         throw new BSTRuntimeException( "BSTReferenceBased: print failed because BST is empty!" ); 
      }
      else {
         // This BST is not empty, print its content using postorder traversal.
         printPostorderRec( this.root );// Call to internal recursive implementation.
      }
   }
   
   // Desc.: Recursive implementation for the printPostorder method.
   // Input: currRoot, reference to root node of input BST.
   // Note: Recursive implementation.
   private void printPostorderRec( Node currRoot ) {
      // Check if input BST is empty.
      if( currRoot == null ) {
         // Input BST is empty, do nothing.
      }
      else {
         // Input BST is not empty, print content using postorder traversal.
         printPostorderRec( currRoot.childLeft ); // Recursive call to print left subtree of currRoot.
         printPostorderRec( currRoot.childRight ); // Recursive call to print right subtree of currRoot.
         System.out.println( currRoot.key + ", " + currRoot.item ); // Print content of currRoot.
      }
   }
   
   // Desc.: Returns true if this BST is empty, false otherwise.
   public boolean isEmpty() { return this.numItems == 0; }

   // Desc.: Returns the number of items in this BST.
   public int size() { return this.numItems; }

   // Desc.: Delete all the items in this BST.
   public void deleteAll() {
      // Re-init this BST as empty.
      this.root = null;
      this.numItems = 0;
   }

}


