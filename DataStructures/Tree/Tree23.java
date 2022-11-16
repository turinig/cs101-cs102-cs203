


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-11-16

package DataStructures.Tree;

// Class implementing a 2-3 tree reference-based.
public class Tree23 {
	
   // Static-nested class Node for the reference-based implementation of the ADT 2-3 tree.
   // Note: static because it does not need access to outer class members.
   // Note: private because it is designed to only be used by the outer class.
   static private class Node { 
      String key1; // Only key in this node if 2-node, smallest key in this node if 3-node (or 4-node).
      String key2; // Largest key in this node if 3-node, middle key in this node if 4-node (not used if 2-node).
      String key3; // Largest key in this node if 4-node (not used if 2-node or 3 node).
      Object item1; // Item/record associated with key1.
      Object item2; // Item/record associated with key2.
      Object item3; // Item/record associated with key3.
      Node child1; // Leftmost subtree in 2-nodes, 3-nodes, and 4-nodes.
      Node child2; // Right subtree in 2-nodes, middle subtree in 3-nodes, and middle-left subtree in 4-nodes.
      Node child3; // Rightmost subtree in 3-nodes, and middle-right subtree in 4-nodes.
      Node child4; // Rightmost subtree in 4-nodes.
      Node parent; // Parent node of this node.
      // Default constructor.
      // Note: Used to create empty nodes, to be configured afterwards.
      public Node() {
         this.key1 = null; this.key2 = null; this.key3 = null; 
         this.item1 = null; this.item2 = null; this.item3 = null; 
         this.child1 = null; this.child2 = null; this.child3 = null; this.child4 = null;
         this.parent = null;
      }
      // ...
      public String toString() { return "bla"; }
   }
   
   private Node root; // Root node of this tree.
   
   // Default constructor.
   public Tree23() { this.root = null; }
   
   // Desc.: Checks if input node is a leaf.
   // Output: True if input node is a leaf, false otherwise.
   private boolean isLeaf(Node inputNode){ 
      if(inputNode == null) { return false; }
      else { return ((inputNode.child1 == null) && (inputNode.child2 == null) && (inputNode.child3 == null) && (inputNode.child4 == null)); }
   }
   
   // Desc.: Checks if input node is child 1 of its parent.
   // Output: True if input node is child 1 of its parent, false otherwise.
   private boolean isChild1(Node inputNode){ 
      if(inputNode == null) { return false; }
      else if(inputNode.parent == null) { return false; }
      else { return (inputNode == inputNode.parent.child1); }
   }
   
   // Desc.: Checks if input node is child 2 of its parent.
   // Output: True if input node is child 2 of its parent, false otherwise.
   private boolean isChild2(Node inputNode){ 
      if(inputNode == null) { return false; }
      else if(inputNode.parent == null) { return false; }
      else { return (inputNode == inputNode.parent.child2); }
   }
   
   // Desc.: Checks if input node is child 3 of its parent.
   // Output: True if input node is child 3 of its parent, false otherwise.
   private boolean isChild3(Node inputNode){ 
      if(inputNode == null) { return false; }
      else if(inputNode.parent == null) { return false; }
      else { return (inputNode == inputNode.parent.child3); }
   }
   
   // Desc.: Checks if input node is child 4 of its parent.
   // Output: True if input node is child 4 of its parent, false otherwise.
   private boolean isChild4(Node inputNode){ 
      if(inputNode == null) { return false; }
      else if(inputNode.parent == null) { return false; }
      else { return (inputNode == inputNode.parent.child4); }
   }
   
   // Desc.: Returns the number of keys stored in input node.
   // Output: Returns 1 if input node is a 2-node, 2 if input node is a 3-node, 3 if input node is a 4-node (should never return 0);
   private int getNumNodeKeys(Node inputNode) {
      if(inputNode.key1 == null) { return 0; } // Note: It should never happen (input node storing no keys).
      else if(inputNode.key2 == null) { return 1; } // Input node is 2-node (storing 1 key).
      else if(inputNode.key3 == null) { return 2; } // Input node is 3-node (storing 2 keys).
      else { return 3; } // Input node is 4-node (storing 3 keys).
   }
   
   // Desc.: Inserts input key (and its associated input item) in input node.
   // Input: inputNode, node in which input key (and its associated item) will be inserted.
   //        inputKey, unique key of its associated input item.
   //        inputItem, item associated to the input key.
   // Note: Duplicate check performed by caller code.
   private void insertInNode(Node inputNode, String inputKey, Object inputItem ) {
      // Check how many keys are stored in input node.
      // If no keys stored in input node: then store input key/item as key/item 1 in input node.
      if(inputNode.key1 == null) { inputNode.key1 = inputKey; inputNode.item1 = inputItem; }
      // If input node is a 2-node (only 1 key): then store input key/item as key/item 1/2 (depending on comparison) in input node.
      else if(inputNode.key2 == null) {
         // Compare input key with key 1 in input node to decide how to store key/item 1.
         // If input key greater than key 1, then store input key/item as key/item 2 in input node.
         if(inputKey.compareTo(inputNode.key1) > 0) { inputNode.key2 = inputKey; inputNode.item2 = inputItem; }
         // Otherwise, input key smaller than key 1, then store input key/item as key/item 1 in input node (move old key/item 1 to key/item 2).
         else { inputNode.key2 = inputNode.key1; inputNode.item2 = inputNode.item1; inputNode.key1 = inputKey; inputNode.item1 = inputItem; }
      }
      // Otherwise, input node is a 3-node (2 keys): then store input key/item as key/item 1/2/3 (depending on comparison) in input node.
      else {
         // Local variables to determine new keys/items 1/2/3.
         String newKey1; Object newItem1; String newKey2; Object newItem2; String newKey3; Object newItem3;
         // Compare input key with keys 1/2/3 in input node.
         // If input key smaller than key 1: shift-right old keys/items, and set input key/item as new key/item 1.
         if(inputKey.compareTo(inputNode.key1) <= 0) {
            newKey1 = inputKey; newItem1 = inputItem; newKey2 = inputNode.key1; newItem2 = inputNode.item1; newKey3 = inputNode.key2; newItem3 = inputNode.item2;
         }
         // Otherwise, compare input key with key 2...
         else {
            // If input key greater than key 2: set input key/item as new key/item 3.
            if(inputKey.compareTo(inputNode.key2) > 0) {
               newKey1 = inputNode.key1; newItem1 = inputNode.item1; newKey2 = inputNode.key2; newItem2 = inputNode.item2; newKey3 = inputKey; newItem3 = inputItem;
            }
            // Otherwise, input key is between key 1 and key 2.
            else {
               newKey1 = inputNode.key1; newItem1 = inputNode.item1; newKey2 = inputKey; newItem2 = inputItem; newKey3 = inputNode.key2; newItem3 = inputNode.item2;
            }
         }
         // Reconfigure input node with proper arrangement of keys/items (from smallest to greatest).
         inputNode.key1 = newKey1; inputNode.item1 = newItem1; inputNode.key2 = newKey2; inputNode.item2 = newItem2; inputNode.key3 = newKey3; inputNode.item3 = newItem3;
      }
   }
   
   // Desc.: Split input node.
   // Input: inputNode, node to be split.
   // Note: This method manages only creation/linking of nodes because of the split, parent keys are configured by caller code.
   private void splitNode(Node inputNode) {
      // Prepare 2 new nodes as left/right nodes after the split.
      Node newNodeLeft = new Node(); newNodeLeft.key1 = inputNode.key1; newNodeLeft.item1 = inputNode.item1;
      Node newNodeRight = new Node(); newNodeRight.key1 = inputNode.key3; newNodeRight.item1 = inputNode.item3;
   	// If input node is not a leaf, reconfigure its children (and parents) linking them to new left/right nodes.
      if(!isLeaf(inputNode)) {
         newNodeLeft.child1 = inputNode.child1; newNodeLeft.child1.parent = newNodeLeft;
         newNodeLeft.child2 = inputNode.child2; newNodeLeft.child2.parent = newNodeLeft;
         newNodeRight.child1 = inputNode.child3; newNodeRight.child1.parent = newNodeRight;
         newNodeRight.child2 = inputNode.child4; newNodeRight.child2.parent = newNodeRight;
      }
   	// If input node is child 1 of parent node, ...
      if(isChild1(inputNode)) {
         // Get number of keys stored in parent node, and configure its children (and parents) accordingly.
         int numKeysParentNode = getNumNodeKeys(inputNode.parent);
         // If parent node is 2-node, configure its 3 children (and parents).
         if(numKeysParentNode == 1) {
            inputNode.parent.child3 = inputNode.parent.child2; inputNode.parent.child3.parent = inputNode.parent;
            inputNode.parent.child2 = newNodeRight; inputNode.parent.child2.parent = inputNode.parent;
            inputNode.parent.child1 = newNodeLeft; inputNode.parent.child1.parent = inputNode.parent;
         }
         // If parent node is 3-node, configure its 4 children (and parents).
         else if(numKeysParentNode == 2) {
            inputNode.parent.child4 = inputNode.parent.child3; inputNode.parent.child4.parent = inputNode.parent;
            inputNode.parent.child3 = inputNode.parent.child2; inputNode.parent.child3.parent = inputNode.parent;
            inputNode.parent.child2 = newNodeRight; inputNode.parent.child2.parent = inputNode.parent;
            inputNode.parent.child1 = newNodeLeft; inputNode.parent.child1.parent = inputNode.parent;
         }
      }
      // If input node is child 2 of parent node, ...
      else if(isChild2(inputNode)) {
         // Get number of keys stored in parent node, and configure its children accordingly.
         int numKeysParentNode = getNumNodeKeys(inputNode.parent);
         // If parent node is 2-node, configure its 3 children. 
         if(numKeysParentNode == 1) {
            inputNode.parent.child3 = newNodeRight; inputNode.parent.child3.parent = inputNode.parent;
            inputNode.parent.child2 = newNodeLeft; inputNode.parent.child2.parent = inputNode.parent;
         }
         // If parent node is 3-node, configure its 4 children.
         else if(numKeysParentNode == 2) {
            inputNode.parent.child4 = inputNode.parent.child3; inputNode.parent.child4.parent = inputNode.parent;
            inputNode.parent.child3 = newNodeRight; inputNode.parent.child3.parent = inputNode.parent;
            inputNode.parent.child2 = newNodeLeft; inputNode.parent.child2.parent = inputNode.parent;
         }
      }
      // If input node is child 3 of parent node, configure parent children.
      else if(isChild3(inputNode)) {
         inputNode.parent.child4 = newNodeRight; inputNode.parent.child4.parent = inputNode.parent;
         inputNode.parent.child3 = newNodeLeft; inputNode.parent.child3.parent = inputNode.parent;
      }
      // Otherwise, input node is child 4 of parent node, configure parent children.
      else{
         inputNode.parent.child1 = newNodeLeft; inputNode.parent.child1.parent = inputNode.parent;
         inputNode.parent.child2 = newNodeRight; inputNode.parent.child2.parent = inputNode.parent;
      }
   }
   
   // Desc.: Internal method to search the proper leaf node in input tree to perform insertion of input key.
   // Input: currRoot, the root node of the input tree.
   //        inputKey, the key we are about to insert in this tree (insertion performed/finalized elsewhere).
   // Output: Leaf node that should store input key (leaf node found), null otherwise (leaf node not found).
   // Note: Recursive implementation.
   // Note: This method also configure links to parents in each node traversed.
   private Node getLeaf(Node currRoot, String inputKey) {
      // Check if input tree is empty or storing only 1 node.
      if((currRoot == null) || isLeaf(currRoot)) { return currRoot; }
      else {
         // Input tree has at least 2 nodes.
         // Compare input key with keys in current root, and proceed the search in proper subtree.
         if(inputKey.compareTo(currRoot.key1) < 0) { 
            return getLeaf(currRoot.child1, inputKey); // Search left subtree.
         } 
         else if((currRoot.key2 == null) || (inputKey.compareTo(currRoot.key2) < 0)) { 
            return getLeaf(currRoot.child2, inputKey); // Search middle subtree.
         }
         else {
            return getLeaf(currRoot.child3, inputKey); // Search right subtree.
         }
      }
   }
	
   // Desc.: Internal method to find the node storing input key in input tree (rooted at currRoot).
   // Input: currRoot, root node of input tree.
   //        inputKey, key we are looking for in input tree.
   // Output: Node storing input key (if found), null otherwise (not found).
   // Note: Recursive implementation.
   private Node findNode(Node currRoot, String inputKey) {
      // If input tree (currRoot) is empty, return null.
      if(currRoot == null) { return null; } // Input key not found, return null.
      // If input tree has only 1 node, compare input key with keys in current root.
      else if(isLeaf(currRoot)) {
         // Check if input key is stored at currRoot (using short-circuit evaluation).
         if((inputKey.compareTo(currRoot.key1) == 0) || ((getNumNodeKeys(currRoot) == 2) && (inputKey.compareTo(currRoot.key2) == 0))) { 
            return currRoot; // Input key found in current root, return current root.
         }
         else { return null; } // Input key not found, return null.
      }
      // Otherwise, proceed search recursively depending on comparison of input key with keys in current root.
      else {
         if(inputKey.compareTo(currRoot.key1) < 0) { return findNode(currRoot.child1, inputKey); } // Search left subtree.
         else if(inputKey.compareTo(currRoot.key1) == 0) { return currRoot; } // Found at current root (key 1).
         else if((getNumNodeKeys(currRoot) == 1) || (inputKey.compareTo(currRoot.key2) < 0)) { return findNode(currRoot.child2, inputKey); } // Search middle subtree.
         else if(inputKey.compareTo(currRoot.key2) == 0) { return currRoot; } // Found at current root (key 2).
         else { return findNode(currRoot.child3, inputKey); } // Search right subtree.
      }
   }
	
   // Desc.: Split node in input.
   // Input: currRoot, node to be split.
   private void searchSplitNode(Node currRoot) {
      // Create a new parent node.
      Node parentNode = null;
      // If current root is original tree root, split root by creating a new level/root
      if(currRoot == this.root) { parentNode = new Node(); this.root = parentNode; currRoot.parent = parentNode; }
      // Otherwise, perform standard split.
      else { parentNode = currRoot.parent; }
   	// Split input node providing its current parent node, and then configuring parent node keys.
      splitNode(currRoot);
      insertInNode(parentNode, currRoot.key2, currRoot.item2);
      // Check if parent node is now a 4-node, and split it if so.
      if(getNumNodeKeys(parentNode) == 3) { searchSplitNode(parentNode); } // Recursive call to chain-split.
   }
	
   // Desc.: Insertion method.
   // Input: inputKey, key to be inserted in this tree.
   //        inputItem, item associated with input key.
   // Note: If input key is already in this tree, insertion is aborted.
   public void insert(String inputKey, Object inputItem) {
      // If tree is empty, insert input key/item as new root.
      if(this.root == null) { this.root = new Node(); this.root.key1 = inputKey; this.root.item1 = inputItem; return; }
      // If tree is not empty, check if input key already in tree.
      else if(findNode(this.root, inputKey) != null) { return; } // Input key already in tree, abort insertion.
      // Otherwise (tree not empty and input key not in tree), perform insertion recursively using internal method.
      else {
         Node leaf = getLeaf(this.root, inputKey);
         insertInNode(leaf, inputKey, inputItem);
         if(getNumNodeKeys(leaf) == 3) { searchSplitNode(leaf); }
      }
   }
   
}


