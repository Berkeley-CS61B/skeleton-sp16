/** 
 *  @author Josh Hug
 */

public class CyclesDemo {
    /* Identifies a cycle (if any exist) in the given graph, and draws the cycle with
     * a purple line. */
    public static void main(String[] args) {
        Maze maze = new Maze("maze.config");

        MazeCycles mc = new MazeCycles(maze);
        mc.solve();
    }

} 