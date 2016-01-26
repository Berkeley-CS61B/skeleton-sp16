public class Traveler {

    public static final long EPSILON = 300;

    private String name;
    private int treasures;
    private int treasuresGoal;
    private long timeOfLastStop;
    private int hopsTaken;
    private int hopsGoal;

    public Traveler(String name, int treasuresGoal, int hopsGoal) {
        this.name = name;
        this.treasuresGoal = treasuresGoal;
        this.hopsGoal = hopsGoal;
        treasures = 0;
        timeOfLastStop = System.currentTimeMillis();
        hopsTaken = 0;
    }

    public Traveler(String name, int treasuresGoal) {
        this(name, treasuresGoal, Integer.MAX_VALUE);
    }

    public void printStatus() {
        System.out.println(name + " gathered " + treasures + " out of " + treasuresGoal + " treasures in "
                            + hopsTaken + " hops");
    }

    public void endJourney() {
        printStatus();
        if (treasures < treasuresGoal) {
            System.out.println("Hmm, we seem to have missed some. Try again!");
        } else if (hopsTaken > hopsGoal) {
            System.out.println("Took too many hops! Can you gather the treasures more efficiently?");
        } else {
            System.out.println("You've done it! Well played!");
        }
        System.exit(0);
    }

    public void hop() {
        hopsTaken += 1;
        timeOfLastStop = System.currentTimeMillis();
    }
     public void collectTreasure() {
         treasures += 1;
     }

    public boolean isStopped() {
        return System.currentTimeMillis() - timeOfLastStop > EPSILON;
    }
}
