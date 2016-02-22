package synthesizer;

/* Since this test is part of a package, we have to import the package version of StdAudio. */
/* Don't worry too much about this, we'll get there in due time. */
import edu.princeton.cs.introcs.StdAudio;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the GuitarString class.
 *  @author Josh Hug
 */

public class TestGuitarString {
/*    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }*/

    public void testTic() {
        GuitarString s = new GuitarString(4);
        // Dequeue all the zeros that go into a GuitarString by default.
        s.dequeue();
        s.dequeue();
        s.dequeue();
        s.dequeue();

        // add custom values instead of plucking so we know what is in the GuitarString.
        s.enqueue(0.1);
        s.enqueue(0.2);
        s.enqueue(0.3);
        s.enqueue(0.4);

        // GuitarString should be [0.1, 0.2, 0.3, 0.4] 
        // So check that front value is actaully 0.1, using tolerance of 0.001.
        // See JUnit documentation for a description of how tolerances work
        // for assertEquals(double, double)
        assertEquals(0.1, s.sample(), 0.001);

        s.tic();

        // After tic, GuitarString should be [0.2, 0.3, 0.4, 0.1494]
        assertEquals(0.2, s.sample(), 0.001);

        // Dequeue the 0.2, 0.3, 0.4 so we can get at the good stuff. 
        s.dequeue();
        s.dequeue();
        s.dequeue();
        assertEquals(0.1494, s.sample(), 0.001);
    }

    /** Calls tests for GuitarString. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestGuitarString.class);
    }
} 
