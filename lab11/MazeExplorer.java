
import java.util.Observable;
/** 
 *  @author Josh Hug
 */

public abstract class MazeExplorer extends Observable {
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    public Maze maze;


    /** Notify all Oberservers of a change. */
    protected void announce() {
        setChanged();
        notifyObservers();
    }

    public MazeExplorer(Maze m) {
        maze = m;

        distTo = new int[maze.V()];
        edgeTo = new int[maze.V()];
        marked = new boolean[maze.V()];
        for (int i = 0; i < maze.V(); i += 1) {
            distTo[i] = Integer.MAX_VALUE;
            edgeTo[i] = Integer.MAX_VALUE;
        }
        addObserver(maze);
    }

    /** Solves the maze, modifying distTo and edgeTo as it goes. */
    public abstract void solve();
} 