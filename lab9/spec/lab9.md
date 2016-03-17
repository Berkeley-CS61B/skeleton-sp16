~ number: 9
~ title: Hash Maps

In this lab, you'll create **MyHashMap**, an implementation of the Map61B interface, which represents a hash map. This will be very similar to Lab 8, except this time we're building a Hash Map rather than a Tree Map.

After you've completed your implementation, you'll compare the performance of your implementation to a list-based Map implementation `ULLMap` as well as the built-in Java `HashMap` class (which also uses a hash table).

1: MyHashMap
=======

Create a class **MyHashMap** that implements the **Map61B** interface. You must do this in a file named `MyHashMap.java`. Your implementation is required to implement all of the methods given in **Map61B** *except* for `remove`. For this methods you should throw an `UnsupportedOperationException`. Note that you should implement `keySet` and `iterator` this time. For these methods, we recommend you simply create a `HashSet` instance variable that holds all your keys.

Additionally, you should implement the following constructors:

    public MyHashMap();
    public MyHashMap(int initialSize);
    public MyHashMap(int initialSize, double loadFactor);

You should increase the size of your HashMap when the loadFactor exceeds some number of your choice, unless the HashMap was instantiated with the loadFactor parameter, in which case you should use that number. Your Hashmap should initially have a number of buckets equal to initialSize. You are not required to resize down. When resizing, make sure to multiplicatively increase the size, not additively (e.g. multiply by 2, don't add 100 or something). Your HashMap operation should all be constant amortized time, assuming that the hashCode of any objects inserted spread things out nicely.

You should handle collisions by chaining. You may not import any libraries other than `ArrayList`, `LinkedList`, `HashSet`, `iterator` and `Set`. 

You can test your implementation using the `TestMyHashMap` class in the `lab9tester` package. 

You may find the following resources useful:

* BST code from pages 136 and 137 of [Data Structures Into Java](http://www-inst.eecs.berkeley.edu/~cs61b/fa14/book2/data-structures.pdf), from our course references page.
* Chapter 3.4 of our optional Algorithms textbook.
* HashTable code code from [our optional textbook](http://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html). 
* `ULLMap.java` (provided), a working unordered linked list based **Map61B** implementation.
* Lecture 23 [slides](https://docs.google.com/presentation/d/1H7253NmqEyb4rvwEQ6FQL_10tXNmAf6qBh8YTqNIvM4/pub?start=false&loop=false&delayms=3000).

2: So... How Fast Is It (Redux)?
======

There are two interactive speed tests provided in `InsertRandomSpeedTest.java` and `InsertInOrderSpeedTest.java`. Do not attempt to run these tests before you've completed **MyHashMap**. Once you're ready, compile with `javac oneOfTheTests.java` and run with `java oneOfTheTests`.

The `InsertRandomSpeedTest` class performs tests on element-insertion speed of your **MyHashMap**, **ULLMap** (provided), and Java's built-in **HashMap**. It works by asking the user for an input size `N`, then generates `N` Strings of length 10 and inserts them into the maps as <String,Integer> pairs.

Try it out and see how your data structure scales with `N` compared to the naive and industrial-strength implementations. Record your results in a file named `speedTestResults.txt`. There is no standard format required for your results, and there is no required number of data points.

Now try running `InsertInOrderSpeedTest`, which behaves similarly to `InsertRandomSpeedTest`, except this time the Strings in `<String, Integer>` key-value pairs are inserted in [lexicographically-increasing order](http://en.wikipedia.org/wiki/Lexicographical_order). Note that unlike lab8, your code should be in the rough ballpark of Java's built in solution -- say within a factor of 10 or so. What this tells us is that state-of-the-art HashMaps are relatively easy to implement compared to state-of-the-art TreeMaps.



