


// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-11-15

package DataStructures.Tree;

import java.lang.Exception;

// Array-based implementation of an heap (binary maxheap).
public class Heap { 
   
   // Internal array storing heap items/keys.
   // Note: root stored at index 1.
   // Note: array index 0 unused to match standard heap design.
   // Note: array index 0 unused to allow jGRASP heap visualization.
   private String[] items;
   private int currNumItems; // Logical size.

   // Desc.: Default constructor.
   public Heap() {
      // Init internal fields to represent an empty heap.
      this.items = new String[ 20 ]; // 19 items max, index 0 unused.
      this.currNumItems = 0;
   }
   
   // Desc.: Check if this heap is empty.
   // Output: True if this heap is empty, false otherwise.
   public boolean isEmpty() { 
      // Check if the current number of items in this heap is 0.
      return ( this.currNumItems == 0 );
   }
   
   // Desc.: Inserts the input item in this heap.
   // Input: newItem, input item/key to be inserted in this heap.
   // Output: Throws a critical/checked exception if insertion fails because heap is full.
   public void insert( String newItem ) throws Exception {
      // Check if this heap is full.
      if( this.currNumItems == this.items.length-1 ) {
         // This heap is full, insert failed, throw critical exception.
         throw new Exception( "MaxHeapArrayBased: insert failed, heap is full!" );
      }
      else {
         // This heap is not full, perform insertion.
         // 1. Append input item/key in internal array.
         this.items[ this.currNumItems+1 ] = new String(newItem); // Storing a copy of input item.
         this.currNumItems++;
         // 2. Swap input item/key up in this heap to its proper position.
         int indexNewItem = this.currNumItems; 
         int indexParent = ( indexNewItem ) / 2;
         while( ( indexParent > 0 ) &&
                ( this.items[ indexNewItem ].compareTo( this.items[ indexParent ] ) > 0 ) ) {
            // Swap parent and new item.
            String temp = this.items[ indexParent ];
            this.items[ indexParent ] = this.items[ indexNewItem ];
            this.items[ indexNewItem ] = temp;
            // Update indices.
            indexNewItem = indexParent;
            indexParent = ( indexNewItem ) / 2;
         }  
      }   
   }
   
   // Desc.: Extracts (returns and deletes) topmost/root/max item/key in this heap (maxheap).
   // Output: Topmost/root/max item/key in this heap (maxheap).
   // Output: Throws a checked/critical exception if extraction fails because heap is empty.
   public String extractMax() throws Exception {
      // Check if this heap is empty.
      if( this.currNumItems == 0 ) {
         // This heap is empty, delete failed, throw critical exception.
         throw new Exception( "MaxHeapArrayBased: delete failed, heap is empty!" );
      }
      else {
         // This heap is not empty, perform delete.
         // 1. Init local variables.
         String rootItem = this.items[1];
         int indexLastItem = this.currNumItems;
         // 2. Swap root with "last" item.
         this.items[1] = this.items[ indexLastItem ];
         this.items[ indexLastItem ] = null;
         this.currNumItems--;
         // 3. Swap new root item to its proper position in this heap.
         swapDownRec(1);
         // 4. Return topmost/root/max item/key (before delete).
         return rootItem;
      }
   }
   
   // Desc.: Internal recursive implementation to swap down an item of this heap.
   // Note: Assuming input index is valid.
   private void swapDownRec( int indexItem ) {
      // Compute indices of children of input item.
      int indexChildLeft = ( 2 * indexItem ); // Index of left child of input item, if any.
      int indexChildRight = indexChildLeft + 1; // Index of right child of input item, if any.
      // Check if left child exists.
      if( indexChildLeft <= this.currNumItems ) {
         // Left child exists, check if right child exists.
         if( indexChildRight <= this.currNumItems ) {
            // Both left and right children exist, find greatest child.
            if( this.items[ indexChildLeft ].compareTo( this.items[ indexChildRight ] ) > 0 ) {
               // Greatest child is left child.
               // Check if input value is less than greatest child (left).
               if( this.items[ indexItem ].compareTo( this.items[ indexChildLeft ] ) < 0 ) {
                  // 1. Swap input item with greatest child (left).
                  String temp = this.items[ indexChildLeft ];
                  this.items[ indexChildLeft ] = this.items[ indexItem ];
                  this.items[ indexItem ] = temp;
                  // 2. Proceed with the swapping down (recursively).
                  swapDownRec( indexChildLeft );
               }
               else {
                  // Input item is greater than both children, do nothing.
               }
            }
            else {
               // Greatest child is right child.
               // Check if input value is less than greatest child (right).
               if( this.items[ indexItem ].compareTo( this.items[ indexChildRight ] ) < 0 ) {
                  // 1. Swap input item with greatest child (right).
                  String temp = this.items[ indexChildRight ];
                  this.items[ indexChildRight ] = this.items[ indexItem ];
                  this.items[ indexItem ] = temp;
                  // 2. Proceed with the swapping down (recursively).
                  swapDownRec( indexChildRight );
               }
               else {
                  // Input item is greater than both children, do nothing.
               }
            }
         }
         else {
            // Left child exist, but right child does not exist, greatest child is left child.
            // Check if input value is less than greatest child (left).
            if( this.items[ indexItem ].compareTo( this.items[ indexChildLeft ] ) < 0 ) {
               // 1. Swap input item with greatest child (left).
               String temp = this.items[ indexChildLeft ];
               this.items[ indexChildLeft ] = this.items[ indexItem ];
               this.items[ indexItem ] = temp;
               // 2. Proceed with the swapping down (recursively).
               swapDownRec( indexChildLeft );
            }
            else {
               // Input item is greater than the only child (left), do nothing.
            }
         }
      }
      else {
         // Left child does not exist, input item has no children (leaf), do nothing (swapping down complete).
      }
   }
      
}


