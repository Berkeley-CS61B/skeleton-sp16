public class JourneyToAlmostTheCenterOfTheEarth extends Trial {

    /**
     * Cal Ford learns that there is treasure near the center of the Earth! Help him gather it!
     * Beware, though, the molten core; don't dig too far! You may set only one breakpoint.
     */
    public static void main(String[] args) {
        Traveler cal = new Traveler("Not-quite-lava-proof Cal", 3);
        digForTreasure(cal);
        digForTreasure(cal);
        fallToCenterOfTheEarth(cal);
        digForTreasure(cal);
        cal.endJourney();
    }

    public static void digForTreasure(Traveler t) {
        double timeEnter = System.currentTimeMillis();
        double timeLeave = System.currentTimeMillis();
        if (timeLeave -timeEnter > Traveler.EPSILON) {
            treasureChest(t);
        }
    }

    public static void fallToCenterOfTheEarth(Traveler t) {
        double timeEnter = System.currentTimeMillis();
        double timeLeave = System.currentTimeMillis();
        if (timeLeave - timeEnter > Traveler.EPSILON) {
            t.hop();
            System.out.println("You fell to a fiery death in the molten core of the Earth");
            t.endJourney();
        }
    }
}
