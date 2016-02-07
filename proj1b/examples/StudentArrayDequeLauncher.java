/** If your computer is set up properly, this file should
  * compile when moved into the same directory as 
  * StudentArrayDeque.class, or if you're using
  * IntelliJ, after you have imported proj1b.jar */
public class StudentArrayDequeLauncher {
    public static void main(String[] args) {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
        sad1.addLast(5);
        sad1.addLast(10);
        sad1.printDeque();
    }
} 
