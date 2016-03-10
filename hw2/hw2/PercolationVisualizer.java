/******************************************************************************
 *
 *  Compilation:  javac PercolationVisualizer.java
 *  Execution:    java PercolationVisualizer input.txt
 *  Dependencies: Percolation.java
 *
 ******************************************************************************/
package hw2;                       
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;


public class PercolationVisualizer {

/*    public static void main(String[] args) {
        // input file
        In in = new In(args[0]);

        // N-by-N lattice
        int N = in.readInt();

        // number of distinct sites opened
        int opened = 0;

        // set x- and y-scale
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);

        // turn on animation mode
        StdDraw.show(0);

        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            if (!perc.isOpen(i, j)) {
                perc.open(i, j);
                opened++;
            }

            // draw percolation system
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (perc.isFull(row, col)) {
                        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    } else if (perc.isOpen(row, col)) {
                        StdDraw.setPenColor(StdDraw.WHITE);
                    } else {
                        StdDraw.setPenColor(StdDraw.BLACK);
                    }
                    StdDraw.filledSquare(col - 0.5, N - row + 0.5, 0.45);
                }
            }

            // write status text
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(.25 * N, -N * .025, "sites opened = " + opened);
            if (perc.percolates()) {
                StdDraw.text(.75 * N, -N * .025, "percolates");
            } else {
                StdDraw.text(.75 * N, -N * .025, "does not percolate");
            }                  

            StdDraw.show(10);
        }
    }*/
}
