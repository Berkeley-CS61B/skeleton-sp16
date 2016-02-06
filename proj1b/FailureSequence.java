import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** This class represents a sequence of operations that
  * results in a failure of the Student Deque that is being
  * tested.
  *
  * See FailureSequenceDemo.java for an example of how this class
  * might be used.
  */
public class FailureSequence {

    private ArrayDequeSolution<DequeOperation> opSequence;
    /* Regex black magic. */
    private static final String OP_STRING = "([a-zA-Z]+)\\(([0-9\\-]*)\\)";
    private static final Pattern OP_PATTERN = Pattern.compile(OP_STRING);

    /** Creates an empty failure sequence. */
    public FailureSequence() {
        opSequence = new ArrayDequeSolution<DequeOperation>();
    }

    /* Adds an operation to the failure sequence. */
    public void addOperation(DequeOperation dequeOp) {
        opSequence.addLast(dequeOp);
    }

    /** Returns a String representation for this FailureSequence. */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < opSequence.size(); i += 1) {
            sb.append(opSequence.get(i) + "\n");
        }

        return sb.toString();
    }


    /* Code below this line is probably not useful to you. */


    /** Creates a FailureSequence from a file. */
    public FailureSequence(String filename) {
        In in = new In(filename);
        opSequence = new ArrayDequeSolution<DequeOperation>();
        while (!in.isEmpty()) {
            String opRead = in.readString();
            Matcher m = OP_PATTERN.matcher(opRead);
            if (!m.find()) {
                throw new RuntimeException("Invalid Operation: " + opRead);
            }
            try {
                DequeOperation dequeOp;
                
                String opName = m.group(1);
                String opArg = m.group(2);
                if (opArg.length() > 0) {
                    int argument = Integer.parseInt(m.group(2));
                    dequeOp = new DequeOperation(opName, argument);
                } else {
                    dequeOp = new DequeOperation(opName);                   
                }
                addOperation(dequeOp);
            } catch (Exception e) {
                throw new RuntimeException("Invalid Operation: " + opRead);
            }
        }
    }

    /** Writes this failure case to a file. */
    public void toFile(String filename) {
        Out out = new Out(filename);
        out.print(this.toString());
        out.close();
    }
} 
