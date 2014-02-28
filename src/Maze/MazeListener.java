/* Author: Ryan Houlihan
 * MazeListener.java
 * 
 * The purpose of this program is to create a maze and be able to click anywhere 
 * on the maze and find the path to the finish line. The finish line is the 
 * designated green block on the maze.
 */

package Maze;

public interface MazeListener {
    public void MazeClicked(int row, int col);
}

