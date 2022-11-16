


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-11-15

package DataStructures.Tree.Generic;

// Class representing a node for a 2-3 tree (reference-based).
// Note: generic implementation.
// Note: package-private class to limit its usage inside this package.
class Tree23Node< T extends KeyedItem< KT >, KT extends Comparable<? super KT > > {
   
   private KT keySmall; // Only key in this node if 2-node, smallest key in this node if 3-node (or 4-node).
   private KT keyLarge; // Largest key in this node if 3-node, middle key in this node if 4-node (not used if 2-node).
   private KT keyAuxiliary; // Largest key in this node if 4-node (not used if 2-node or 3 node).
   
   private T itemSmall; // Item/record associated with small key.
   private T itemLarge; // Item/record associated with large key.
   private T itemAuxiliary; // Item/record associated with auxiliary key.

   private Tree23Node<T,KT> childLeft; // Left subtree (used in 2-nodes, 3-nodes, and 4-nodes).
   private Tree23Node<T,KT> childMiddle; // Middle subtree (used only in 2-nodes, 3-nodes, and 4-nodes).
   private Tree23Node<T,KT> childRight; // Right subtree (used only in 3-nodes, and 4-nodes).
   private Tree23Node<T,KT> childAuxiliary; // Auxialiry subtree (used only in 4-nodes).
   
   // Default constructor.
   // Note: Used to create empty nodes, to be configured afterwards.
   public Tree23Node() {
      this.keySmall = null;
      this.keyLarge = null;
      this.keyAuxiliary = null;
      this.itemSmall = null;
      this.itemLarge = null;
      this.itemAuxiliary = null;
      this.childLeft = null;
      this.childMiddle = null;
      this.childRight = null;
      this.childAuxiliary = null;
   }
   
   // Constructor 1: only 1 input item.
   public Tree23Node(KT inputKey, T inputItem) {
      this.keySmall = inputKey;
      this.keyLarge = null;
      this.keyAuxiliary = null;
      this.itemSmall = inputItem;
      this.itemLarge = null;
      this.itemAuxiliary = null;
      this.childLeft = null;
      this.childMiddle = null;
      this.childRight = null;
      this.childAuxiliary = null;
   }
   
   // Constructor 2: 2 input items.
   public Tree23Node(KT inputKeySmall, T inputItemSmall, KT inputKeyLarge, T inputItemLarge) {
      this.keySmall = inputKeySmall;
      this.keyLarge = inputKeyLarge;
      this.keyAuxiliary = null;
      this.itemSmall = inputItemSmall;
      this.itemLarge = inputItemLarge;
      this.itemAuxiliary = null;
      this.childLeft = null;
      this.childMiddle = null;
      this.childRight = null;
      this.childAuxiliary = null;
   }
   
   // Desc.: Checks if this node is a leaf.
   // Output: True if this node is a leaf, false otherwise.
   public boolean isLeaf(){ return ((this.childLeft == null) && (this.childMiddle == null) && (this.childRight == null)); }
   
   // Desc.: Returns the number of keys stored in this node.
   // Output: Returns 1 if this is a 2-node, 2 if this is a 3-node, 3 if this is a 4-node (should never return 0);
   public int numKeys() {
      if(this.keySmall == null) { return 0; } // Note: It should never happen (node storing no keys).
      else if(this.keyLarge == null) { return 1; } // 2-node storing only 1 key (the small key).
      else if(this.keyAuxiliary == null) { return 2; } // 3-node storing 2 keys (the small and large keys).
      else { return 3; } // 4-node storing 3 keys (small, large, and auxiliary).
   }
   
   // Getters/accessors.    
   public KT getKeySmall() { return this.keySmall; }
   public KT getKeyLarge() { return this.keyLarge; }
   public KT getKeyAuxiliary() { return this.keyAuxiliary; }
   public T getItemSmall() { return this.itemSmall; }
   public T getItemLarge() { return this.itemLarge; }
   public T getItemAuxiliary() { return this.itemAuxiliary; }
   public Tree23Node<T,KT> getChildLeft() { return this.childLeft; }
   public Tree23Node<T,KT> getChildMiddle() { return this.childMiddle; }
   public Tree23Node<T,KT> getChildRight() { return this.childRight; }
   public Tree23Node<T,KT> getChildAuxiliary() { return this.childAuxiliary; }

   // Setters/modifiers.
   public void setChildLeft(Tree23Node<T,KT> inputNode) { this.childLeft = inputNode; }
   public void setChildMiddle(Tree23Node<T,KT> inputNode) { this.childMiddle = inputNode; }
   public void setChildRight(Tree23Node<T,KT> inputNode) { this.childRight = inputNode; }
   public void setChildAuxiliary(Tree23Node<T,KT> inputNode) { this.childAuxiliary = inputNode; }

   // Desc.: Inserts input key (and its associated input item) in this node.
   // Input: inputKey, unique key of its associated input item.
   //        inputItem, item associated to the input key.
   // Note: Duplicate check performed before (at the tree-level).
   public void insert(KT inputKey, T inputItem ) {
      // Check keys stored in this node.
      if(this.keySmall == null) { 
         // No keys stored in this node, store input key as small key.
         this.keySmall = inputKey;
         this.itemSmall = inputItem;
      }
      else if(this.keyLarge == null) {
         // Only small key stored in this node, compare input key with small key.
         if(inputKey.compareTo(this.keySmall) > 0) {
            // Only small key stored in this node, input key is greater than small key, store input key as large key.
            this.keyLarge = inputKey;
            this.itemLarge = inputItem;
         }
         else {
            // Only small key stored in this node, input key is smaller than small key, old small key is new large key and input key is new small key.
            this.keyLarge = this.keySmall;
            this.itemLarge = this.itemSmall;
            this.keySmall = inputKey;
            this.itemSmall = inputItem;
         }
      }
      else {
         // Both small and large keys stored in this node, sort node keys and input key and store them properly.
         KT smallestKey; T smallestItem;
         KT middleKey; T middleItem;
         KT largestKey; T largestItem;
         if(inputKey.compareTo(this.keySmall) <= 0) {
            // inputKey <= keySmall < keyLarge
            smallestKey = inputKey; smallestItem = inputItem;
            middleKey = this.keySmall; middleItem = this.itemSmall;
            largestKey = this.keyLarge; largestItem = this.itemLarge;
         }
         else {
            // keySmall < inputKey, keySmall < keyLarge
            if(inputKey.compareTo(this.keyLarge) > 0) {
               // keySmall < keyLarge < inputKey
               smallestKey = this.keySmall; smallestItem = this.itemSmall;
               middleKey = this.keyLarge; middleItem = this.itemLarge;
               largestKey = inputKey; largestItem = inputItem;
            }
            else {
               // keySmall < inputKey <= keyLarge
               smallestKey = this.keySmall; smallestItem = this.itemSmall;
               middleKey = inputKey; middleItem = inputItem;
               largestKey = this.keyLarge; largestItem = this.itemLarge;
            }
         }
         // Arranging keys/items from smallest to largest.
         this.keySmall = smallestKey; this.itemSmall = smallestItem;
         this.keyLarge = middleKey; this.itemLarge = middleItem;
         this.keyAuxiliary = largestKey; this.itemAuxiliary = largestItem;
      }
   }

   // Desc.: Split this node relying on its parent node in input.
   // Input: parentNode, parent node of this node, provided by caller code (tree).
   // Note: This method manages only creation/linking of nodes because of the split, parent keys are configured by caller code (tree).
   public void splitNode(Tree23Node<T,KT> parentNode) {
      // Prepare 2 new nodes storing small key (new leftmost node after split) and auxiliary key (new rightmost node after split).
      Tree23Node<T,KT> newNodeLeft = new Tree23Node<T,KT>(this.keySmall, this.itemSmall);
      Tree23Node<T,KT> newNodeRight = new Tree23Node<T,KT>(this.keyAuxiliary, this.itemAuxiliary);
   	// Check if this node is not a leaf.
      if(!this.isLeaf()) {
         newNodeLeft.setChildLeft(this.getChildLeft());
         newNodeLeft.setChildMiddle(this.getChildMiddle());
         newNodeRight.setChildLeft(this.getChildRight());
         newNodeRight.setChildMiddle(this.getChildAuxiliary());
      }
   	// Check if this node is the left child of parent node (in input).
      if(this == parentNode.getChildLeft()) {
         // Check the number of keys stored in parent node (in input).
         if(parentNode.numKeys() == 1) {
            // Parent node stores only 1 key: setup 2 keys and 3 children.
            parentNode.setChildRight(parentNode.getChildMiddle()); // Change middle child to right child.
            parentNode.setChildMiddle(newNodeRight); // Middle child now links to new right node. 
            parentNode.setChildLeft(newNodeLeft); // Left child now links to new left node.
         }
         else if(parentNode.numKeys() == 2) {
            // Parent node stores 2 keys: setup 3 keys and 4 children.
            parentNode.setChildAuxiliary(parentNode.getChildRight()); // Change right child to auxiliary child.
            parentNode.setChildRight(parentNode.getChildMiddle()); // Change middle child to right child.
            parentNode.setChildMiddle(newNodeRight); // Middle child now links to new right node.
            parentNode.setChildLeft(newNodeLeft); // Left child now links to new left node. 
         }
      }
      // Check if this node is the middle child of parent node (in input).
      else if(this == parentNode.getChildMiddle()) {
         // Check the number of keys stored in parent node (in input). 
         if(parentNode.numKeys() == 1) {
            // Parent node stores only 1 key: ...
            parentNode.setChildRight(newNodeRight); // ...
            parentNode.setChildMiddle(newNodeLeft); // ...
         }
         else if(parentNode.numKeys() == 2) {
            // Parent node stores 2 keys: ...
            parentNode.setChildAuxiliary(parentNode.getChildRight()); // ...
            parentNode.setChildRight(newNodeRight); // ...
            parentNode.setChildMiddle(newNodeLeft); // ...
         }
      }
      // Check if this node is the right child of parent node (in input).
      else if(this == parentNode.getChildRight()) {
         // ...
         parentNode.setChildAuxiliary(newNodeRight); // ...
         parentNode.setChildRight(newNodeLeft); // ...
      }
      // This node is the auxiliary child of parent node (in input).
      else{
         parentNode.setChildLeft(newNodeLeft); // ...
         parentNode.setChildMiddle(newNodeRight); // ...
      }
   }

} 


