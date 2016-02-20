This folder contains examples that you might find useful for this project. These include (listed roughly in the order that we anticipate they will be useful to you):
 - SingleLetterDisplaySimple.java
 - SingleLetterDisplay.java
 - KeyPressPrinter.java
 - ShortcutKeyPrinter.java
 - MouseClickPositionPrinter.java
 - CopyFile.java
 - ResizeAllen.java
 - ScrollBarExample.java
 - PathDrawer.java
 - ClipboardExample.java

The example_files folder also contains two example files that may be useful: one that uses Linux-style newline characters (a "\n" represents a newline) and one that uses Windows-style newlines ("\r\n" denotes a newline).  Both of these files should look the same when opened by your editor.

SingleLetterDisplaySimple
--------------------

This file contains a simple program that displays the most recently typed letter to the screen in a graphical user interface (GUI) window. 

SingleLetterDisplay
--------------------

An enhanced version of SingleLetterDisplaySimple that also creates a color-changing box that surrounds the text. You may find this handy when figuring out how to do your cursor (hint: one way to represent a cursor is a very skinny blinking rectangle).

KeyPressPrinter
--------------------

Creates a GUI window that is not used for drawing, but instead just for collecting and printing KeyEvents. KeyEvents are a bit counterintuitive. We highly recommend that you attempt to diagnose any KeyEvent confusion using this program, as opposed to one of the clients above or your editor.

ShortcutKeyPrinter
--------------------

Creates a GUI window that is not used for drawing, but instead just for collecting and printing KeyEvents.  In particular, this file prints out a message anytime the user presses Control+a or Control+z.

MouseClickPositionPrinter
--------------------

Creates a GUI window, and whenever the user clicks the mouse, prints the position of the mouse click.

CopyFile
--------------------

Copys text from one file to another.  Illustrates how to use Java's classes for reading and writing files.

ResizeAllen
--------------------

Illustrates how to determine when the window has been resized.

ScrollBarExample
--------------------

Provides an example of how to use scrollbars.

PathDrawer
--------------------

Creates a GUI window that draws the path taken whenever the mouse was dragged (this example is useful if you're implementing text selection for gold points).

ClipboardExample
--------------------

Illustrates how to interact with the system clipboard (to implement copy and paste for gold points).
