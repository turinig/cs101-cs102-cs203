


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-11-15

package DataStructures.Tree.Generic;

import java.util.ArrayList;

// Class implementing a 2-3 tree reference-based.
// Note: Generic implementation.
public class Tree23< T extends KeyedItem< KT >, KT extends Comparable<? super KT > > {
	
   private Tree23Node<T,KT> root; // Root node of this tree.
   
   // List of parent nodes traversed during and insertion (eventually used to perform splits).
   private ArrayList<Tree23Node<T,KT>> listParents;
	
   // Default constructor.
   public Tree23() {
      this.root = null;
      this.listParents = new ArrayList<Tree23Node<T,KT>>();
   }
	
   // Desc.: Internal method to search the proper leaf node in input tree to perform insertion of input key.
   // Input: currRoot, the root node of the input tree.
   //        inputKey, the key we are about to insert in this tree (insertion performed/finalized elsewhere).
   // Output: Leaf node that should store input key (leaf node found), null otherwise (leaf node not found).
   // Note: Recursive implementation.
   private Tree23Node<T,KT> getLeafNode(Tree23Node<T,KT> currRoot, KT inputKey) {
      // Check if input tree is empty or storing only 1 node.
      if((currRoot == null) || (currRoot.isLeaf())) { return currRoot; }
      else {
         // Input tree has at least 2 nodes.
         this.listParents.add(currRoot);
         if(inputKey.compareTo(currRoot.getKeySmall()) < 0) { 
            return getLeafNode(currRoot.getChildLeft(), inputKey); // Search left subtree.
         }
         else if((currRoot.getKeyLarge() == null) || (inputKey.compareTo(currRoot.getKeyLarge()) < 0)) { 
            return getLeafNode(currRoot.getChildMiddle(), inputKey); // Search middle subtree.
         }
         else {
            return getLeafNode(currRoot.getChildRight(), inputKey); // Search right subtree.
         }
      }
   }
	
   // Desc.: Internal method to find the node storing input key in input tree (rooted at currRoot).
   // Input: currRoot, root node of input tree.
   //        inputKey, key we are looking for in input tree.
   // Output: Node storing input key (if found), null otherwise (not found).
   // NOte: Recursive implementation.
   private Tree23Node<T,KT> findNode(Tree23Node<T,KT> currRoot, KT inputKey) {
      // Check if input tree (currRoot) is empty.
      if(currRoot == null) { return currRoot; } // Input key not found, return null.
      // Check if input tree (currRoot) has only 1 node (currRoot is a leaf).
      else if(currRoot.isLeaf()) {
         // Check if input key is stored at currRoot.
         // Note: Using short-circuit evaluation.
         if((inputKey.compareTo(currRoot.getKeySmall()) == 0) || 
            ((currRoot.numKeys() == 2) && (inputKey.compareTo(currRoot.getKeyLarge()) == 0))) { 
            return currRoot; // Input key found in current root, return current root.
         }
         else { return null; } // Input key not found, return null.
      }
      else {
         // Input tree has more than 1 node.
         // Compare input key with keys in current root, and perform search recursively.
         if(inputKey.compareTo(currRoot.getKeySmall()) < 0) { return findNode(currRoot.getChildLeft(), inputKey); } // Search left subtree.
         else if(inputKey.compareTo(currRoot.getKeySmall()) == 0) { return currRoot; } // Found at current root (small key).
         else if((currRoot.numKeys() == 1) || (inputKey.compareTo(currRoot.getKeyLarge()) < 0)) { return findNode(currRoot.getChildMiddle(), inputKey); } // Search middle subtree.
         else if(inputKey.compareTo(currRoot.getKeyLarge()) == 0) { return currRoot; } // Found at current root (large key).
         else { return findNode(currRoot.getChildRight(), inputKey); } // Search right subtree.
      }
   }
   
   // Desc.: Insertion method.
   // Input: inputKey, key to be inserted in this tree.
   //        inputItem, item associated with input key.
   // Note: If input key is already in this tree, insertion is aborted.
   public void insert(KT inputKey, T inputItem) {
      // Check if tree is empty.
      if(this.root == null) {
         this.root = new Tree23Node<T,KT>(inputKey, inputItem);
         return;
      }
      // Tree not empty, check if input key already in tree.
      else if(findNode(this.root, inputKey) != null) { return; } // Input key already in tree, abort insertion.
      // Tree not empty and input key not in tree, perform insertion.
      else {
         this.listParents.clear();
         Tree23Node<T,KT> leaf = getLeafNode(this.root, inputKey);
         leaf.insert(inputKey, inputItem);
         if(leaf.numKeys() == 3) { splitNode(leaf); }
      }
   }
	
   // Desc.: Split node in input.
   // Input: currRoot, node to be split.
   private void splitNode(Tree23Node<T,KT> currRoot) {
      // Create a new parent node.
      Tree23Node<T,KT> parentNode = null;
      // Check if current root is original tree root.
      if(currRoot == this.root) {
         // Current root is original tree root, splitting root by creating a new level/root.
         // Create new node and set it as new root.
         parentNode = new Tree23Node<T,KT>();
         this.root = parentNode;
      }
      else {
         // Current root not original tree root, not a root-splitting, perform standard split.
         int indexLastParent = this.listParents.size()-1;
         parentNode = this.listParents.get(indexLastParent); // Get last parent stored.
         this.listParents.remove(indexLastParent); // Remove last parent stored.
      }
   	// Split input node providing its current parent node, and then configuring parent node keys.
      currRoot.splitNode(parentNode);
      parentNode.insert(currRoot.getKeyLarge(), currRoot.getItemLarge());
      // Check if parent node is now a 4-node, and split it if so.
      if(parentNode.numKeys() == 3) { splitNode(parentNode); } // Recursive call to chain-split.
   }
	
}


