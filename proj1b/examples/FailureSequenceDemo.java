/** Demonstrates how the FailureSequence class works. */
public class FailureSequenceDemo {
    public static void main(String[] args) {
        FailureSequence fs = new FailureSequence();
        DequeOperation dequeOp1 = new DequeOperation("addFirst", 5);
        DequeOperation dequeOp2 = new DequeOperation("addFirst", 10);
        DequeOperation dequeOp3 = new DequeOperation("size");

        fs.addOperation(dequeOp1);
        fs.addOperation(dequeOp2);
        fs.addOperation(dequeOp3);

        System.out.println(fs.toString());

    }
} 
