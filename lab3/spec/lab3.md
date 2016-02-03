~ number: 3
~ title: Unit Testing with JUnit, Debugging

This lab is in beta and will be officially released the evening of 2/3. Due to its late release, attendance is not required. Please report any errors directly to Josh: hug@cs.berkeley.edu

Navigation
----------------

- [Pre-lab](#pre-lab)
- [Introduction](#introduction)
- [An Arithmetic Bug](#arithmetic)
- [Application: IntLists](#intlists)
- [Debugging: Flik](#flik)
- [Recap](#recap)

<a name="pre-lab"></a> Pre-lab
-------------------------------

Lab 3B: Classpath setup.

<a name="introduction"></a> Introduction
--------------------------------
In this lab, you will learn about Unit Testing, JUnit, the 61B style checker, and we'll also get a bit more debugging experience.

####What is JUnit?
[JUnit](http://junit.org/) is a Unit Testing Framework for Java.

####What is Unit Testing?
Unit Testing is the best way to rigorously test each method of your code and ultimately ensure that you have a working project.
The “Unit” part of Unit Testing comes from the idea that you can break your program down into units, or the smallest testable part of an application.
Therefore, Unit Testing enforces good code structure (each method should only do “One Thing”), and allows you to consider all of the edge cases for each method and test for them individually.  In this class, you will be using JUnit to create and run tests on your code to ensure its correctness.  And when JUnit tests fail, you have an excellent starting point for debugging.

####JUnit Syntax
JUnit provides some special functionality on top of what you can normally do in java.

Ultimately, JUnit provides a testing framework, so you can test your code without stressing about details (formatting and printing of error messages, counting failures and succsses, etc.).

So what is different about a JUnit java file?  Go ahead and navigate to the Arithmetic directory and open `ArithmeticTest.java`.
The first thing you'll notice are the imports at the top.  These imports are what give you access to the JUnit methods and functionality that you'll need to run JUnit tests.
Next, you'll see that there are two methods in `ArithmeticTest.java`: `testProduct` and `testSum`
These methods follow this format:

    @Test
    public void testMethod() {
        assertEquals(<expected>, <actual>);
    }
    

`assertEquals` is a common method used in JUnit tests. It tests whether a variable's actual value is equivalent to its expected value. 

When you create JUnit test files, you should precede each test method with a `@Test` annotation, and can have one or more `assertEquals` or `assertTrue` methods (provided by the JUnit library). ** All tests must be non-static. ** This may seem weird since your tests don't use instance variables and you probably won't instantiate the class. However, this is how the designers of JUnit decided tests should be written, so we'll go with it.

From here, you have two choices of how to proceed. If you're planning on working in IntelliJ, read on. If you're planning on running your code from a terminal, skip to the [arithmetic_terminal](Terminal JUnit) section.

<a name="arithmetic_intellij"></a> Running JUnit Tests in IntelliJ (or another IDE)
--------------------------------

Open up lab/arithmetic/ArithmeticTest.java.

Run the tests in IntelliJ.

Main doesn't run. That's used for command line invocation. Go fix bug.

(need screenshots and exposition, but it's pretty intuitive. maybe discuss breakpoints)

<a name="arithmetic_terminal"></a> Running JUnit Tests from a Terminal
--------------------------------

*If you are using your own computer, you will need to complete [lab3b](../lab3b/lab3b.html) before JUnit test compilation will work.*

TODO: Add the right path to the student accounts!


If you're running tests from the command line, the main method in your JUnit testing file will do the work of starting up all the tests. 

All of your JUnit test files should have a main method that looks like the example below. The class specified as an argument to the TestRunner should have the same name as the running file, e.g. for a test class called SomeTest.java, you'd have:

    jh61b.junit.TestRunner.runTests(SomeTest.class);    

You don't need to memorize this syntax, just know that this code snippet will run all of the methods which are preceded by @Test, i.e. the line above will run all tests in `SomeTest` when put in the `main` method of your test program. 

Let's try it out. Go to the lab3/arithmetic folder, and try running the small test provided:
    
    javac *.java
    java ArithmeticTest
    
This will run all of the tests in ArithmeticTest.java and give you back a JUnit report. Notice it includes a failure! This tells you which test failed (`testSum` in `ArithmeticTest`), what the expected and actual values were, and on what line the failure occured. The output on your console should be something like this: 
    
    Running JUnit tests using jh61b.junit.TestRunner in "all" mode.

    Running testSum: 
    ====================================
    expected:<11> but was:<30>
        at ArithmeticTest.testSum:25 (ArithmeticTest.java)
    =====> FAILED!

    Running testProduct: 
    ====================================
    =====> Passed

    Passed: 1/2 tests.
    

As you can see above the `testProduct` test passed with flying colors. However, the `testSum` class failed miserably, apparently calculating 30 when it should have computed 11.

Open up `ArithmeticTest.java` and take a look around. Comparing against the output above, you'll see that even though `testSum` included many `assert` statements, you only saw the first failure (even though you know that all of the asserts would have failed!)

This is because JUnit tests are short-circuiting – as soon as one of the asserts in a method fails, it will output the failure and move on to the next test.

Try modifying `ArithmeticTest` so that it shows only failed test results (by changing the mode argument from "all" to "failed"). Re-run and you'll see only failed tests. We recommend that you run your test files from "failed" mode, as this will allow you to focus on what needs doing, rather than celebrating what has already been done. Debugging is a hard life.

Now it's time to look to see why `testSum` failed. Look at `testSum` to understand what its testing for, and then make the appropriate change in `Arithmetic.java`. 

After fixing the bug, execute the compilation and execution commands again:

    
    $ javac *.java
    $ java ArithmeticTest
    

If you've fixed the bug, it should look like this:
    
    $ java ArithmeticTest

    Running JUnit tests using jh61b.junit.TestRunner in "failure" mode.

    Passed: 2/2 tests.


Since you're running your code from the command line, you're going to need to use print statement debugging (as opposed to the cool debugger you hopefully saw in lab 2). Any code that is printed during a test will be output as part of the results message for a given test. Try adding print statements and see how the output changes.

If you're interested in learning to use a command line debugger for Java, you can try out Paul Hilfinger's gjdb tool. We will not provide official support for this tool, but you can find labs that outline its use in the Spring 2015 edition of this course. You can find gjdb.jar in the Spring 2015 skeleton files.

<a name="intlists"></a> Intlists
--------------------------------

Now a real-CS61B application of JUnit tests: IntLists.

As with last week's lab, we're going to take advantage of the list method of the IntList class, which makes creating IntLists (and writing IntList tests) much easier. For example, consider:
    
    IntList myList = IntList.list(0, 1, 2, 3);
    
Which will create the IntList 0 -> 1 -> 2 -> 3 -> null

###Test a Reverse Method

Copy your IntList.java that you created for lab2 into the lab3/IntList folder. In this section, our goal will be to write the reverse method from this week's discussion worksheet.

We'll showcase the idea of "test-driven development" for this exercise, where we write a unit test even before we write the new method.

Add a new test to IntListTest.java that tests the .reverse(), which you can assume has the following definition:

    /**
     * Returns the reverse of the given IntList.
     * This method is destructive. If given null
     * as an input, returns null.
     */
    public static IntList reverse(IntList A)

Your test should test at least the following:
 - That the method handles null lists properly.
 - That the function returns a reversed list.
 - That the function is destructive.

###Writing a Reverse Method

When you feel like your test is probably in good shape, try compiling IntListTest.java. You should get a compiler error along the lines of:

    IntListTest.java:72: error: cannot find symbol            

      symbol:   method reverse

This error is a great thing! It means that the compiler is actually finding our test.

Now copy and paste a dummy version of the reverse method into IntList.java. Your dummy version might simply return null.

Try compiling again, and your test should compile. Run the test using `java IntListTest` or IntelliJ, and the test should fail. This is great! We've now reached the "red" phase of the TDD cycle.

Write a reverse method, and rerun the tests until it passes. 

Protip: If you want to have your tests timeout after a certain amount of time (to prevent infinite loops), you can declare your test like this:

    @Test(timeout = 1000)

The given parameter specifies the maximum time in milliseconds.

Some people find the rush of TDD addictive. You basically set up a little game for yourself to solve. Some people hate it. Your mileage may vary. Whether you personally enjoy the TDD flow or not, writing tests will be one of the most important skills you learn here at Berkeley, and getting "[test-infected](http://c2.com/cgi/wiki?TestInfected)" will save you and your future colleagues an enormous amount of time and misery.

<a name="Flik"></a> A Debugging Mystery
--------------------------------

Another important skill to learn is how to exhaustively debug. When done properly, debugging should allow you to rapidly narrow down where a bug might be located, even when you are debugging code you don't fully understand yourself.

Your company, Flik Enterprises, has released a fine software library called Flik.java that is able to determine whether two Integers are the same or not.

You receive an email from someone named "Horrible Steve" who describes a problem they're having with your library:

"Dear Flik Enterprises,

Your library is very bad. See the attached code. It should print out 500 but actually it's printing out 128.

(attachment: HorribleSteve.java)"

Using any combination of the following techniques, figure out whether the bug is in Horrible Steve's code or in Flik enterprise's library:
 - Writing JUnit tests for the Flik library.
 - Using the IntelliJ debugger.
 - Using print statements.
 - Refactoring (this means changing the syntax without changing the functionality) Horrible Steve's code.

Horrible Steve's code uses syntax we haven't covered in class. 

Tip: JUnit provides methods `assertTrue(boolean)` and `assertTrue(String, boolean)` that you might find helpful.

<a name="Style"></a> Running the 61B Style Checker
--------------------------------

Starting after the midterm, your code will be required to obey the [CS61B style guidelines](http://cs61b.ug/sp16/materials/guides/style-guide.html). As noted, you should probably not try to read these rules, though they may be a useful reference. Instead, it will be much easier to simply run the style checker. You can do this by running the style61b.py script provided in the lib folder (you may need to pull from skeleton again if you don't see it). For example, on my machine, I can run it as follows.

    $ python3 /Users/jug/work/bqd/javalib/style61b.py *.java

Try it out on the files in your IntList folder. You should see that there are at least two style errors (the two we put in, plus whatever you may have introduced yourself). You are not required to pass these checks until after the midterm (though from now on, we will be running the style checker for your reference in the autograder, for no credit).

When you pass the style check, the output should look like:

    Starting audit...
    Audit done.

<a name="Deques"></a> Deque Unit Tests
--------------------------------

In project 1B (to be released 2/5), you'll be required to write JUnit tests for your Deque classes. If you have extra time in lab, start writing some tests for `LinkedListDeque` and `ArrayDeque` as a warmup

<a name="recap"></a> Recap
-------------------------------
In this lab, we went over:

- Unit Testing (big picture)
- JUnit syntax and details
- Writing JUnit tests 
- Debugging Using JUnit
- Running the Style checker