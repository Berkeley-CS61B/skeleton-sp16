~ number: 13
~ title: Linear Sorting (It's possible!)

Navigation
-----------------

- [Counting Sort](#counting)
- [Radix Sort](#radix)
- [Submission](#Submission)

This week in lab you're going to be writing Counting sort and Radix sort. 

<a name="counting"></a> Counting Sort
--------------------------------

We've provided you with 1 files for part one to implement Counting sort and find its limitations.

#### CountingSort.java

Counting sort is a special sort of sort where we have the restriction that the sortable elements have to be able to be converted into numbers.

In this sort, we simply count the number of occurrences of each value in the array and then go through this counts array in order and fill in the sorted array with the number of counts each value has.

However, there is a restriction on counting sort, which you will find in this part of the lab.

Look at the method `naiveCountingSort` in `CountingSort.java`. Find an array with values that will cause this code to fail to sort the given array (either by error or by not sorting). Fill in `naiveArr` in the `CountingSort.java` class with this array. Do NOT change this method, it is used as reference in the autograder.

Great! Now that you know the one of the constraints of Counting Sort, can we try to solve it? Look at the next method, `betterCountingSort` and fill in this method so it does counting sort and will succeed where `naiveCountingSort` will fail.

Then write an array that will fail to be sorted with `naiveCountingSort` and succeed with `betterCountingSort`. Put this array in `betterArr` inside of `CountingSort.java`.

Unfortunately, this fix is not full-proof. There is still an array that Counting sort will not succeed. Write this array `naiveBetterArr` that will cause `naiveCountingSort` and `betterCountingSort` to fail to sort (again either by error or not sorting).



<a name="radix"></a> Radix Sort
------------------------------

#### RadixSort.java

In this part of lab you'll write an implementation of radix sort for ASCII Strings. Normally, if we just had decimal numbers, we would say that we would have a radix of 10 (R = 10) since there are 10 possible digits at each index, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]. It is important to note that the time Radix Sort takes does depend on the length of the longest value it has to sort. We consider running Radix Sort to be linear time for integers in Java because the number of digits allowed for an integer is limited (10 digits max) which means we will at most have to do 10N iterations.

For our purposes in this lab, we are going to be sorting ASCII Strings which have 256 possible characters (numbered 0-255) and are of variable length. In JAVA, you can get the ASCII code for a character by casting the `char` as an `int` (`int i = (int)'a'`) and get the character from the ASCII code by casting the other way (`char a = (char)97`). You may use either MSD (most significant digit) or LSD (least significant digit), but one is significantly easier (think about how you would sort words in your mind).

Since we have 256 characters to use, we have a radix of 256 (R = 256). Write the method 'sort' in `RadixSort.java` that will sort the list of ASCII Strings that is passed in and return the sorted list. Make sure the method is NON-destructive (so the original list cannot change). Feel free to add any helper methods you want (you can also use your counting sort implementation). [Here](https://www.cs.usfca.edu/~galles/visualization/RadixSort.html) is a great tool for seeing how Radix sort works visually (because we all like to see stuff right?).

Keep in mind that Radix Sort on Strings runs in `O(N*M)` time where `N` is the number of Strings and `M` is the length of the longest String.

Extra for experts: Compare the runtime of your Radix sort compared to `Arrays.sort`. Which is faster for short arrays? Long arrays? Do the values in the array matter?

<a name="submission"></a> Submission
--------------------------------

Submit zip file with `CountingSort.java`, `CountingSortTester.java`, `RadixSort.java`, and `MagicWord13.java` inside a folder called `lab13`.
