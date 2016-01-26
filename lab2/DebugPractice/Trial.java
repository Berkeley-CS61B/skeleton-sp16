public abstract class Trial {

    public static void lava(Traveler t) {
        if (t.isStopped()) {
            t.hop();
            System.out.println("Ouch, that burns!");
            t.endJourney();
        }
    }

    public static void nothingHere(Traveler t) {
        if (t.isStopped()) {
            t.hop();
        }
    }

    public static void treasureChest(Traveler t) {
        if (t.isStopped()) {
            t.hop();
            t.collectTreasure();
        }
    }
}
