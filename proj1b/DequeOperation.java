/** This class is a convenience class for keeping track of
  * Deque operations. Each DequeOperation has an opName
  * and an optional argument. 
  * 
  * For example, new DequeOperation("addFront", 10)
  * returns an object which represents addFront(10).
  *
  * new DequeOperation("size") returns an object which 
  * represents a call to size()
  *
  * DequeOperation objects are mostly useful for 
  * storing in data other structures and being converted
  * to Strings.
  */
public class DequeOperation {
    private String opName;
    private boolean hasArgument;
    private int argument;

    /** Constructor for operations with an argument. */
    public DequeOperation(String op, int arg) {
        this.opName = op;
        this.hasArgument = true;
        this.argument = arg;
    }

    /** Constructor for operations with no argument. */
    public DequeOperation(String op) {
        this.opName = op;
        this.hasArgument = false;
    }

    /** Returns a string representation of this object, e.g.
      * if opName = "addFront" and argument = 10, this method
      * would return "addFront(10)"
      */
    public String toString() {
        String returnString;
        returnString = opName + "(";
        
        if (hasArgument) {
            returnString = returnString + argument;
        }

        returnString = returnString + ")";
        return returnString;
    }
} 
