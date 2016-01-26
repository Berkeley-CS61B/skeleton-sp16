public class EscapeFromTheSodaLabs extends Trial {

    /**
     * General Ackbar has discovered treasures in the second floor labs. However, a nefarious entity, Leorge Gucas
     * has "deployed the algorithm" which is set to destroy the labs. To save Soda, collect all the treasures in as few
     * hops as possible. You may set only one breakpoint.
     */
    public static void main(String[] args) {
        Traveler cal = new Traveler("General Ackbar", 5, 8);
        treasureChest(cal);
        trap(cal);
        treasureChest(cal);
        superTrap(cal);
        cal.endJourney();
    }

    public static void trap(Traveler t) {
        treasureChest(t);
        for (int i = 0; i < 10; i++) {
            nothingHere(t);
        }
    }

    public static void superTrap(Traveler t) {
        treasureChest(t);
        trap(t);
        for (int i = 0; i < 10; i++) {
            nothingHere(t);
        }
    }
}
