



// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-09-12

package DataStructures.Tree.Heap.Generic;

import java.util.ArrayList;
import java.util.Comparator;
import java.lang.Comparable;
import java.lang.Exception;

// Array-based implementation of an heap (min or max) array-based using Java Generics.
// Note: input type parameter T (non built-in type) represents the type of the heap items.
public class Heap<T> { 

   private ArrayList<T> heap; // Internal array of items.
                              // Note: ArrayList used instead of arrays to avoid issues generics vs arrays.
                              // Note: Root stored at index 1, array index 0 unused to match standard heap design and to allow jGRASP heap visualization.
   
   private Comparator<? super T> comp; // Comparator defined on T or on a superclass of T (if comparator is null, Comparable interface is used instead).
   private boolean maxHeap; // Flag marking this heap as a maxheap (if true), or as a minheap (if false).

   // Default constructor.
   public Heap() {
      this.heap = new ArrayList<T>();
      this.comp = null;
      this.maxHeap = true;
      this.heap.add(null);
   }

   // Constructor.
   public Heap(Comparator<? super T> comp, boolean maxHeap) {
      this.heap = new ArrayList<T>();
      this.comp = comp;
      this.maxHeap = maxHeap;
      this.heap.add(null);
   }

   // Desc.: Heap condition in case a comparator is NOT provided (using Comparable interface instead).
   // Note: Different conditions if minheap or maxheap.
   public boolean heapConditionComparable(T parent, T child) {
      // Suppressing warning to avoid compiler message.
      @SuppressWarnings("unchecked")
      Comparable<? super T> comparableParent = (Comparable<? super T>) parent;
      if(maxHeap) { return (comparableParent.compareTo(child) > 0); }
      else { return (comparableParent.compareTo(child) < 0); }
   }
   
   // Desc.: Heap condition.
   // Note: Different conditions if minheap or maxheap.
   public boolean heapCondition(T parent, T child) {
      // Check if a comparator has been provided
      if(this.comp == null) {
         // Comparator NOT provided, assuming a Comparable interface.
         return heapConditionComparable(parent, child);
      }
      else {
         // Comparator provided, use it according to minheap/maxheap flag.
         if(maxHeap) { return (this.comp.compare(parent, child) > 0); }
         else { return (this.comp.compare(parent, child) < 0); }
      }
   }
   
   // Desc.: Check if this heap is empty.
   // Output: True if this heap is empty, false otherwise.
   // Note: Root stored at index 1, array index 0 unused to match standard heap design and to allow jGRASP heap visualization.
   public boolean isEmpty() { return (this.heap.size() == 1); }
   
   // Desc.: Inserts the input item in this heap.
   // Input: newItem, input item/key to be inserted in this heap.
   // Note: Internal use of ArrayList ensures this heap will never be full.
   public void insert( T newItem ) {
      // 1. Append input item/key in internal array.
      this.heap.add(this.heap.size(), newItem); // Append newItem at end of internal ArrayList.
      // 2. Swap up input item in this heap to its proper position.
      int indexNewItem = this.heap.size()-1;
      int indexParent = indexNewItem / 2;
      T parent = this.heap.get(indexParent);
      while( (indexParent > 0) && ( !this.heapCondition(parent, newItem) ) ) {
         // Swap up parent and child (newItem).
         T temp = parent;
         this.heap.set(indexParent, newItem);
         this.heap.set(indexNewItem, temp);
         // Update indices/items.
         indexNewItem = indexParent;
         indexParent = indexNewItem / 2;
         parent = this.heap.get(indexParent);
      }   
   }
   
   // Desc.: Extracts (returns and deletes) root item in this heap (maxheap or minheap).
   // Output: Root item in this heap.
   // Output: Throws a checked/critical exception if extraction fails because heap is empty.
   public T extract() throws Exception {
      // Check if this heap is empty.
      if( this.isEmpty() ) {
         // This heap is empty, extract failed, throw critical exception.
         throw new Exception( "Heap: extract failed, heap is empty!" );
      }
      else {
         // This heap is not empty, perform extract.
         // 1. Init local variables.
         T rootItem = this.heap.get(1);
         int indexLastItem = this.heap.size()-1;
         T lastItem = this.heap.get(indexLastItem);
         // 2. Swap root with "last" item.
         this.heap.set(1, lastItem);
         this.heap.remove(indexLastItem);
         // 3. Swap new root item to its proper position in this heap.
         swapDownRecursive(1);
         // 4. Return root item.
         return rootItem;
      }
   }
   
   // Desc.: Internal recursive implementation to swap down an item of this heap.
   // Note: Assuming input index is valid.
   private void swapDownRecursive( int indexItem ) {
      // Get item.
      T item = this.heap.get(indexItem);
      // Compute indices of children of input item.
      int indexChildLeft = 2 * indexItem; // Index of left child of input item, if any.
      int indexChildRight = indexChildLeft + 1; // Index of right child of input item, if any.
      // Check if left child exists.
      if( indexChildLeft < this.heap.size() ) {
         // Left child exists, get left child and check if right child exists.
         T childLeft = this.heap.get(indexChildLeft);
         if( indexChildRight < this.heap.size() ) {
            // Both left and right children exist, get right child and compare children.
            T childRight = this.heap.get(indexChildRight);
            int comparisonResult = 0;
            // Check if a comparator has been provided
            if(this.comp == null) {
               // Comparator NOT provided, assuming a Comparable interface.
               // Suppressing warning to avoid compiler message.
               @SuppressWarnings("unchecked")
               Comparable<? super T> comparableChildLeft = (Comparable<? super T>) childLeft;
               // Suppressing warning to avoid compiler message.
               @SuppressWarnings("unchecked")
               Comparable<? super T> comparableChildRight = (Comparable<? super T>) childRight;
               // Store comparison result in local variable.
               comparisonResult = comparableChildLeft.compareTo(childRight);         
            }
            else {
               // Comparator provided, use it.
               comparisonResult = this.comp.compare(childLeft, childRight);
            }
            // Swap down with proper child depending on maxheap/minheap.
            if(maxHeap) {
               // This heap is a maxheap, swap down with greater child.
               if(comparisonResult > 0) {
                  // Greater child is left child.
                  // Check if heap condition is violated (item vs left child).
                  if( !this.heapCondition(item, childLeft) ) {
                     // Heap condition violated, swap down item with left child.
                     // 1. Swap down.
                     T temp = item;
                     this.heap.set(indexItem, childLeft);
                     this.heap.set(indexChildLeft, temp);
                     // 2. Recursive call.
                     swapDownRecursive(indexChildLeft);
                  }
               }
               else {
                  // Greater child is right child.
                  // Check if heap condition is violated (item vs right child).
                  if( !this.heapCondition(item, childRight) ) {
                     // Heap condition violated, swap down item with right child.
                     // 1. Swap down.
                     T temp = item;
                     this.heap.set(indexItem, childRight);
                     this.heap.set(indexChildRight, temp);
                     // 2. Recursive call.
                     swapDownRecursive(indexChildRight);
                  }
               }
            }
            else {
               // This heap is a minheap, swap down with smaller child.
               if(comparisonResult < 0) {
                  // Smaller child is left child.
                  // Check if heap condition is violated (item vs left child).
                  if( !this.heapCondition(item, childLeft) ) {
                     // Heap condition violated, swap down item with left child.
                     // 1. Swap down.
                     T temp = item;
                     this.heap.set(indexItem, childLeft);
                     this.heap.set(indexChildLeft, temp);
                     // 2. Recursive call.
                     swapDownRecursive(indexChildLeft);
                  }
               }
               else {
                  // Smaller child is right child.
                  // Check if heap condition is violated (item vs right child).
                  if( !this.heapCondition(item, childRight) ) {
                     // Heap condition violated, swap down item with right child.
                     // 1. Swap down.
                     T temp = item;
                     this.heap.set(indexItem, childRight);
                     this.heap.set(indexChildRight, temp);
                     // 2. Recursive call.
                     swapDownRecursive(indexChildRight);
                  }
               }
            }  
         }
         else {
            // Left child exist, but right child does not exist, greatest child is left child.
            // Check if heap condition is violated (item vs left child).
            if( !this.heapCondition(item, childLeft) ) {
               // Heap condition violated, swap down item with left child.
               // 1. Swap down.
               T temp = item;
               this.heap.set(indexItem, childLeft);
               this.heap.set(indexChildLeft, temp);
               // 2. Recursive call.
               swapDownRecursive(indexChildLeft);
            }
         }
      }
   }
   
   // Desc.: Updates the item at input index swapping it down or up as necessary (maxheap or minheap).
   // Note: Assuming input index is valid.
   public void update( int indexItem, T item ) {
      // Change/update heap item.
      this.heap.set(indexItem, item);
      // Swap up input item in this heap to its proper position.
      boolean swappedUp = false;
      int indexParent = indexItem / 2;
      T parent = this.heap.get(indexParent);
      while( (indexParent > 0) && ( !this.heapCondition(parent, item) ) ) {
         // Swap up parent and child (newItem).
         T temp = parent;
         this.heap.set(indexParent, item);
         this.heap.set(indexItem, temp);
         swappedUp = true;
         // Update indices/items.
         indexItem = indexParent;
         indexParent = indexItem / 2;
         parent = this.heap.get(indexParent);
      }
      // Check if swap down is necessary.
      if(!swappedUp) {
         swapDownRecursive(indexItem);
      } 
   }
      
}


