/*Ryan Houlihan
 * Lab 5
 *CIS 2168
 * Rolf Lakaemper
 *
 * The purpose of this lab is to create a maze and be able to click anywhere on the maze and find the path to the finish line.
 * The finish line is the designated green block on the maze
 */

package lab_6;
import java.awt.*;
import java.util.*;
public class SolveMaze {
    public static ArrayList<Point> path;
    public static ArrayList<Point> placesGone;
    public Maze myMaze;
    public MazeListen ml;
    public Point goal = new Point(0,0);
    public int width = 20;
    public int height = 25;
// Creates a new maze and adds a maze listner to the maze
    public SolveMaze(){
        myMaze = new Maze(height, width);
        MazeListen mListener = new MazeListen();
        myMaze.addMazeListener(mListener);
    }
    // Recursivly goes through the maze until it finds the finish line
    public boolean findTheWay(Point p){
        boolean northBarrier = false;
        boolean southBarrier = false;
        boolean eastBarrier = false;
        boolean westBarrier = false;
        int data = myMaze.getMazeData(p.y, p.x);
        // Returns false if Y position is out of range
        if(p.y < 0 || p.y > height)
            return false;

        // Returns false if X position is out of range
        if(p.x < 0 || p.x > height)
            return false;
        // Finds which paths are blocked for point P
        if (data > 14) {
            northBarrier = true;
            southBarrier = true;
            eastBarrier = true;
            westBarrier = true;
        }
        if (data <= 14 && data - 8 >= 0) {
            eastBarrier = true;
            data -= 8;
        }
        if (data <= 7 && data - 4 >= 0) {
            southBarrier = true;
            data -= 4;

        }
        if (data <= 3 && data - 2 >= 0) {
            westBarrier = true;
        }
        if (data % 2 != 0) {
            northBarrier = true;
        }

        // Returns true if the goal is reached
        if(p.equals(goal))
            return true;

        // Adds the point to the path of points
        path.add(p);
        placesGone.add(p);

        // Checks for North
        if(!northBarrier && !placesGone.contains(new Point(p.x, p.y - 1))){
           if(p.y - 1 >= 0){
                if(findTheWay(new Point(p.x, p.y - 1))){
                    return true;
                }
           }
        }
        // Checks for South
        if(!southBarrier && !placesGone.contains(new Point(p.x, p.y + 1))){
            if(p.y + 1 < height){
                if(findTheWay(new Point(p.x, p.y + 1))){
                    return true;
                }
            }
        }

        // Checks for East
        if(!eastBarrier && !placesGone.contains(new Point(p.x + 1, p.y))){
            if(p.x + 1 < width){
                if(findTheWay(new Point(p.x + 1, p.y))){
                     return true;
                }
            }
        }
    
        // Checks for West
        if(!westBarrier && !placesGone.contains(new Point(p.x - 1, p.y))){
            if(p.x - 1 >= 0){
                if (findTheWay(new Point(p.x - 1, p.y))) {
                    return true;
                }
            }
        }
        // Removes the point if its not part of the final solution
        path.remove(path.size() - 1);
        return false;
    }
    // Listens for a click on the maze and then finds the path to the finish from the clicked point
    public class MazeListen implements MazeListener{
        public void MazeClicked(int row, int col){
            path = new ArrayList<Point>();
            path.add(new Point(col, row));
            placesGone = new ArrayList<Point>();
            placesGone.add(new Point(col, row));
            if(findTheWay(path.get(0))){
                for(Point x: path){
                }
                ListIterator<Point> iterator = path.listIterator();
                myMaze.showPath(iterator);
            }
        }
    }
    public static void main(String[] args){
        new SolveMaze();
    }
}
