


// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-11-15

package DataStructures.Tree.Generic;

import java.lang.Comparable;

// Class representing an object including a comparable key.
public abstract class KeyedItem< KT extends Comparable<? super KT > > {
   
   private KT key; // Unique key of this item (also allowing comparisons). 

   // Default constructor.
   // Note: Private to forbid instantiation without providing a key.
   private KeyedItem() {}
   
   // Constructor.
   public KeyedItem(KT key) { this.key = key; }
   
   // Desc.: Getter/accessor for the unique key of this item.
   // Output: The unique key of this item.
   // Note: No setter/modifier should be implemented (or made available) for the unique key,
   //       because the key should be read-only and initialized only during initialization/instantiation.
   public KT getKey() { return this.key; }
	
}


