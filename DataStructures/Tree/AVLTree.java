


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-11-14

package DataStructures.Tree;

import java.lang.Exception;

// Reference-based implementation of a BST (binary search tree).
public class AVLTree {

   // Static-nested class Node for the reference-based implementation of the ADT AVL tree.
   // Note: static because it does not need access to outer class members.
   // Note: private because it is designed to only be used by the outer class.
   static private class Node { 
      String key; // Unique key of the item.
      Object item; // Data.
      Node childLeft; // Reference to left child node.
      Node childRight; // Reference to right child node.
      int treeDepth; // Depth/height of the AVL subtree rooted at this node.
      int balanceFactor; // Balance factor of this AVL node (computed as its left subtree depth minus its right subtree depth).
      // Constructor (init only node key and data, not links).
      public Node( String k, Object i ) { this.key = k; this.item = i; this.childLeft = null; this.childRight = null; this.treeDepth = 1; this.balanceFactor = 0; }
   }
   
   private Node root; // Entry point to the AVL tree (reference to root node).
   private int numItems; // Number of items/keys in this AVL tree.
   
   // Desc.: Internal function to get the depth/height of the AVL subtree rooted at the input node.
   // Input: currRoot, a node of the AVL tree representing the root of an AVL subtree.
   // Output: Depth/height of the input AVL subtree rooted at input node (0 if input node is null).
   private int getTreeDepth(Node currRoot) {
      if(currRoot == null) { return 0; }
      else { return currRoot.treeDepth; }
   }
   
   // Desc.: Internal function to update the balance factor of the input node.
   // Input: inputNode, a node of the AVL tree.
   private void updateBalanceFactor(Node inputNode) {
      if(inputNode != null) { inputNode.balanceFactor = getTreeDepth(inputNode.childLeft) - getTreeDepth(inputNode.childRight); }
   }
   
   // Desc.: Internal function to rotate right (R) the subtree rooted at input node.
   // Input: inputNode, the root of the subtree to be rotated right (R) by this method.
   // Output: New root of the input subtree (rooted at inputNode before the rotation).   
   private Node rotateRight(Node inputNode) {
      // ...
      Node childLeft = inputNode.childLeft;
      Node childRightOfChildLeft = childLeft.childRight;
      // Perform right (R) rotation.
      childLeft.childRight = inputNode;
      inputNode.childLeft = childRightOfChildLeft;
      // Update depths/heights of input node and its left child.
      inputNode.treeDepth = Math.max(getTreeDepth(inputNode.childLeft), getTreeDepth(inputNode.childRight)) + 1;
      childLeft.treeDepth = Math.max(getTreeDepth(childLeft.childLeft), getTreeDepth(childLeft.childRight)) + 1;
      // Return new root of the input subtree (rooted at inputNode before the rotation).
      return childLeft;
   }

   // Desc.: Internal function to rotate left (L) the subtree rooted at input node.
   // Input: inputNode, the root of the subtree to be rotated left (L) by this method.
   // Output: New root of the input subtree (rooted at inputNode before the rotation).
   private Node rotateLeft(Node inputNode) { //x
      Node childRight = inputNode.childRight; //y
      Node childLeftOfChildRight = childRight.childLeft;
      // Perform left (L) rotation.
      childRight.childLeft = inputNode;
      inputNode.childRight = childLeftOfChildRight;
      //  Update depths/heights of input node and its right child.
      inputNode.treeDepth = Math.max(getTreeDepth(inputNode.childLeft), getTreeDepth(inputNode.childRight)) + 1;
      childRight.treeDepth = Math.max(getTreeDepth(childRight.childLeft), getTreeDepth(childRight.childRight)) + 1;
      // Return new root of the input subtree (rooted at inputNode before the rotation).
      return childRight;
   }
 
   // Desc.: Inserts the input key in this AVL tree.
   // Input: newKey, input key to be inserted in this AVL tree.
   //        newItem, item associated with input key.
   // Output: Throws a AVLException (critical) if insertion fails because input key is already in this AVL tree.
   public void insert( String newKey, Object newItem ) throws Exception {
      this.root = insertRec( this.root, newKey, newItem ); // Call to internal recursive implementation.
      this.numItems++; // Update number of items if insertion does not fail.
   }
 
   // Desc.: Recursive implementation for the "insert" method.
   // Input: currRoot, reference to root node of input AVL tree.
   //        newKey, input key to be inserted in input AVL tree.
   //        newItem, item associated with input key.
   // Output: Reference to root node of input AVL tree (could be same as currRoot or not).
   // Output: Throws a AVLException (critical) if insertion fails because input key is already in AVL tree.
   // Note: Recursive implementation.
   private Node insertRec(Node currRoot, String newKey, Object newItem) throws Exception {
      // 1 - PERFORM STANDARD INSERTION IN AVL/BST
      // Check if input BST is empty.
      if(currRoot == null) {
         // Input AVL tree is empty: create new node storing input key, return it as the new input AVL tree root.
         Node newNode = new Node( newKey, newItem );
         return newNode; // Simply return new node because no rotation needed.
      }
      else {
         // Input AVL tree is not empty: compare input item with currRoot, and proceed insertion.
         int comparisonResult = newKey.compareTo( currRoot.key );
         // Check result of 3-way comparison.
         if( comparisonResult == 0 ) {
            // Input key equals key stored at currRoot, insertion failed because input key is a duplicate, throw critical exception.
            throw new Exception( "AVLTree: insert failed because input key is already in AVL tree!" );
         }
         else if( comparisonResult < 0 ) {
            // Input key is less than key stored at currRoot, proceed insertion in left subtree of currRoot.
            currRoot.childLeft = insertRec( currRoot.childLeft, newKey, newItem ); // Recursive call.
            // Note: Do not return new root yet because a rotation may be needed to fix balance factor violations.
         }
         else {
            // Input key is greater than key stored at currRoot, proceed insertion in right subtree of currRoot.
            currRoot.childRight = insertRec( currRoot.childRight, newKey, newItem ); // Recursive call.
            // Note: Do not return new root yet because a rotation may be needed to fix balance factor violations.
         }
      }
      // 2 - UPDATE DEPTH/HEIGHT OF INPUT NODE
      currRoot.treeDepth = Math.max(getTreeDepth(currRoot.childLeft), getTreeDepth(currRoot.childRight)) + 1;
      // 3 - UPDATE BALANCE FACTOR OF INPUT NODE AND CHECK IT FOR UNBALANCE
      updateBalanceFactor(currRoot);
      // 3a - Current root violated balance factor requirements AND insertion in left subtree of left child THEN rotate right (R).
      if((currRoot.balanceFactor > 1) && (newKey.compareTo(currRoot.childLeft.key) < 0)) {
         // Note: Remember to update balance factors of nodes affected by any rotation (right after the rotation).
         Node newRoot = rotateRight(currRoot);
         updateBalanceFactor(currRoot);
         updateBalanceFactor(newRoot);
         return newRoot;
      }
      // 3b - Current root violated balance factor requirements AND insertion in right subtree of right child THEN rotate left (L).
      else if((currRoot.balanceFactor < -1) && (newKey.compareTo(currRoot.childRight.key) > 0)) {
         // Note: Remember to update balance factors of nodes affected by any rotation (right after the rotation).
         Node newRoot = rotateLeft(currRoot);
         updateBalanceFactor(currRoot);
         updateBalanceFactor(newRoot);
         return newRoot;
      }
      // 3c - Current root violated balance factor requirements AND insertion in right subtree of left child THEN rotate left-right (LR).
      else if((currRoot.balanceFactor > 1) && (newKey.compareTo(currRoot.childLeft.key) > 0)) {
         // Note: Remember to update balance factors of nodes affected by any rotation (right after the rotation).
         Node newChildLeft = rotateLeft(currRoot.childLeft);
         updateBalanceFactor(currRoot.childLeft);
         updateBalanceFactor(newChildLeft);
         currRoot.childLeft = newChildLeft;
         Node newRoot = rotateRight(currRoot);
         updateBalanceFactor(currRoot);
         updateBalanceFactor(newRoot);
         return newRoot;
      }
      // 3d - Current root violated balance factor requirements AND insertion in left subtree of right child THEN rotate right-left (RL).
      else if((currRoot.balanceFactor < -1) && (newKey.compareTo(currRoot.childRight.key) < 0)) {
         // Note: Remember to update balance factors of nodes affected by any rotation (right after the rotation).
         Node newChildRight = rotateRight(currRoot.childRight);
         updateBalanceFactor(currRoot.childRight);
         updateBalanceFactor(newChildRight);
         currRoot.childRight = newChildRight;
         Node newRoot = rotateLeft(currRoot);
         updateBalanceFactor(currRoot);
         updateBalanceFactor(newRoot);
         return newRoot;
      }
      // 3e - Current root does NOT violate balance factor, just return current root.
      else { return currRoot; }
   }

}


