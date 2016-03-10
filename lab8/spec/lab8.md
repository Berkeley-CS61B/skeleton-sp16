~ number: 8
~ title: BSTs and Asymptotics

In this lab, you'll create **BSTMap**, a BST-based implementation of the Map61B interface, which represents a basic map.

After you've completed your implementation, you'll compare the performance of your implementation to a list-based Map implementation `ULLMap` as well as the built-in Java `TreeMap` class (which also uses a BST).

1: BSTMap
=======

Create a class **BSTMap** that implements the **Map61B** interface using a BST (Binary Search Tree) as its core data structure. You must do this in a file named `BSTMap.java`. Your implementation is required to implement all of the methods given in **Map61B** *except* for `remove`, `iterator` and `keyset`. For these methods you should throw an `UnsupportedOperationException`.

In your implementation you should assume that generic keys K in `BSTMap<K,V>` extend [Comparable<K>](http://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html). In other words, your class should be defined as `public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>`.

The following resources might prove useful:

* BST code from pages 109 and 111 of [Data Structures Into Java](http://www-inst.eecs.berkeley.edu/~cs61b/fa14/book2/data-structures.pdf), from our course references page.
* Pages 396-405 from our optional Algorithms textbook.
* BST code from [our optional textbook](http://algs4.cs.princeton.edu/32bst/BST.java.html). 
* `ULLMap.java` (provided), a working unordered linked list based **Map61B** implementation.
* Lecture 21 [slides](https://docs.google.com/presentation/d/1VCAceKkyEXlcznyNIz4B-lc1rlf72RFmA2o-gdhSYt4/edit#slide=id.g7536d2bb4_0484).

Your BSTMap should also add an additional method `printInOrder()` (not given in the **Map61B** interface) that prints out your **BSTMap** in order of increasing Key. You will find this helpful for testing your implementation!

You can test your implementation using the `TestBSTMap` class in the `lab8tester` package. 

2: So... How Fast Is It?
======

There are two interactive speed tests provided in `InsertRandomSpeedTest.java` and `InsertInOrderSpeedTest.java`. Do not attempt to run these tests before you've completed **BSTMap**. Once you're ready, compile with `javac oneOfTheTests.java` and run with `java oneOfTheTests`.

The `InsertRandomSpeedTest` class performs tests on element-insertion speed of your **BSTMap**, **ULLMap** (provided), and Java's built-in **TreeMap**. It works by asking the user for an input size `N`, then generates `N` Strings of length 10 and inserts them into the maps as <String,Integer> pairs.

Try it out and see how your data structure scales with `N` compared to the naive and industrial-strength implementations. Record your results in a file named `speedTestResults.txt`. There is no standard format required for your results, and there is no required number of data points.

Now try running `InsertInOrderSpeedTest`, which behaves similarly to `InsertRandomSpeedTest`, except this time the Strings in `<String, Integer>` key-value pairs are inserted in [lexicographically-increasing order](http://en.wikipedia.org/wiki/Lexicographical_order). If you observed anything interesting (hopefully you did), you should discuss it with your fellow students and/or TA.

Extra: Modify the testing classes so that they also compare the performance of your class to the built-in HashMap class, which uses an alternate technique for implementing maps (called hashing) that we'll develop next week.

3:  Optional Exercises
======

This will not be graded. 

Implement the methods `iterator()`, `keySet()`, `remove(K key)`, and `remove(K key, V value)`, in your **BSTMap** class. Implementing `remove()` is fairly challenging. For an extra challenge implement `keySet()` and `iterator` without using a second instance variable to store the set of keys.

For `remove`, you should return null if the argument key does not exist in the **BSTMap**. 
Otherwise, delete the key-value pair (key, value) and return value.

4:    Optional Asymptotics Problems
=====
Given `B`, a **BSTMap** with `N` key-value pairs, and `(K, V)`, a random key-value pair, answer the following questions.

Unless otherwise stated, "big-Oh" bounds (e.g. `O(N)`) and "big-Theta" bounds (e.g. &Theta;(`N`)) refer to the **number of comparisons** in the given method call(s). 

For questions 1-7, state whether the statement is true or false. For question 8, give a runtime bound.

1. `B.put(K, V)` &isin; O(log(`N`)).
2. `B.put(K, V)` &isin; &Theta;(log(`N`)).
3. `B.put(K, V)` &isin; &Theta;(`N`).
4. `B.put(K, V)` &isin; O(`N`).
5. `B.put(K, V)` &isin; O(`N`<sup>2</sup>).
6. On average, the total number of comparisons to complete N random calls to `B.put(K, V)` followed by `B.containsKey(K)` &isin; &Tilde;2(ln(`N`))

        Note: We write g(N)~f(N) to represent that ~g(N)/f(N) -> 1 as N gets large.

7. For key `C` != `K`, running both `B.containsKey(K)` and `B.containsKey(C)` &isin; &Omega;(log(`N`)).
8. Let **BSTMap** `b` be comprised of a `root` Node (Key, Value pair) and two **BSTMap** subtrees called `left` and `right`. Further, assume the method `numberOfNodes(BSTMap b)` returns the number of nodes of a **BSTMap** rooted in `b.root` and has a running time of &Theta;`(n)`, where `n` is the number of Nodes in the **BSTMap** rooted in `b`. What is the running time (in big O notation) of `mystery(b, z)` for some positive integer `z`? Give the tightest bound you can assuming `b` has `N` nodes. Your answer should not contain any unnecessary multiplicative constants or additive factors.

        public Key mystery(BSTMap b, int z) {
            if (z > numberOfNodes(b) || z <= 0) 
                return null;
            if (numberOfNodes(b.left) == z-1)
                return b.root.key;
            else if (numberOfNodes(b.left) > z)
                return mystery(b.left, z); 
            else 
                return mystery(b.right, z-numberOfNodes(b.left) - 1);
        }
