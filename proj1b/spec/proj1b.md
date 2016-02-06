~ number: 1b
~ title: Data Structures Part 2

This is the second part of project 1. Part 1C will be released by Sunday. While projects 1B and 1C have been combined into a single deadline (2/11) the specification and autograder for 1C  will be separate for logistical and conceptual simplicity. 

This project is brand new and is in alpha (will move to a more polished state in the morning of 2/16, but everything you need to complete 1B is technically here now). Please let us know on Piazza if you spot any bugs or issues.

Introduction
------------

In project 1b, you will build a rudimentary autograder for project 1a. In the skeleton, we have provided the following files:

 - StudentArrayDeque.class: A buggy implementation of ArrayDeque.
 - StudentLinkedListDeque.class: A buggy implementation of LinkedListDeque.
 - ArrayDequeSolution.class: A correct implementation of ArrayDeque.
 - LinkedListSolution.class: A correct implementation of LinkedList.
 - FailureSequence.java: A utility class for this assignment.
 - DequeOperation.java: A utility class for this assignment.
 - examples/: A folder with some examples that may be helpful.
 
Your goal for part 1B is to create at least two Java files: `TestArrayDeque1B.java` and `TestLinkedListDeque1B.java`. There is no specific API (that is, no specific methods that you must implement).

For this project, you are allowed to work with a partner, and your partner must be the same as you had for part a. If you wish to dissolve your partnership from part a, please send an email to the course staff for approval.

Getting the Skeleton Files
----------------

As with project 1, pull the skeleton using the command `git pull skeleton master`. 

Phase 1: Finding the Bugs
----------------

We've provided buggy implementations of the project 1a assignment, namely `StudentArrayDeque.class` and `StudentLinkedListDeque.class`. Note that we've provided the class files only. Despite not having access to the .java files for these buggy implementation, your code that uses these classes should still compile and run just fine.

In `TestArrayDeque1B.java`, you should give a JUnit test that `StudentLinkedListDeque` fails. Likewise in `TestLinkedListDeque1B.java` should give a JUnit test that `StudentLinkedListDeque` fails. Any tests you write should succeed for a correct implementation, e.g. you can't just do `assertEquals(5, 10)` and consider youself done with project 1B.

In this first phase of this project, you should simply write JUnit tests which are capable of consistently finding at least one bug in each of these two classes. For example, when you run `TestArrayDeque1B`, some assertEquals statement should get triggered into displaying an error.

Note: When building tests, you must use a Deque of Integers, e.g. `StudentArrayDeque<Integer>`. 

You are not required to write randomized tests (like those from the project 1A autograder), but this is probably the easiest and laziest way to succeed.

Once you feel reasonably comfortable that your JUnit tests are capable of identifying failures in a buggy implementation, it's time to improve your autograder.

Phase 2: Printing Out a Failure Sequence
----------------

Of course, simply telling the student that their code fails is only going to lead to tears, sadness, confusion and late night Piazza posts. Thus, you're going to modify your autograder sot that it tells the student something useful. 

To do this, we'll take advantage of the `assertEquals(message,  expected, actual)` [method](http://junit.sourceforge.net/javadoc/org/junit/Assert.html#assertEquals(java.lang.String, long, long&#41;), which outputs a helpful message to the user.

For an example of how this method works, see `AssertEqualsStringDemo.java` in the examples folder.

The string message provided to assertEquals must be a series of method calls that yields an incorrect return value. For example, if adding 5 to the front, then 3 to the front, then removing from the front yields an incorrect value, then the String message passed to assertEquals should be exactly the following:

    addFirst(5) 
    addFirst(3)
    removeFirst()
        
You do not need to supply the expected and actual values as part of the String message, since those are passed separately to the assertEquals statement.

To make your life easier, we've provided optional helper classes `DequeOperation.java` and `FailureCase.java`. You are not required to use these files, and you're free to modify them however you wish. However, if you DO use them, you should make sure to submit them to gradescope.

Submission
----------------

Submit `TestArrayDeque1B.java`, `TestLinkedListDeque1B.java`, and any supporting files you created or require, including `DequeOperation.java` and `FailureCase.java`. Do not submit .class files.

Tips
----------------

- None yet.

Frequently Asked Questions
----------------

- None yet.