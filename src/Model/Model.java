package Model;

import mazeGenerators.Maze3d;

import java.io.IOException;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public interface Model {
    /**
     * generate a 3d  maze in the current name and gets its sizes
     * @param name
     * @param y - the y size of the maze
     * @param z - the z size of the maze
     * @param x - the x size of the maze
     * @param algoname - means the name of the algorithm we want to generate the maze
     * @return - return a maze with start position , goal position and the right cells in a randomalic way
     * @throws Exception
     */
    Maze3d generate3dmaze(String name, int y, int z, int x, String algoname) throws Exception ;

    /**
     * solving the maze in name "name" by the algorithm in name "algorithm"
     * and return a solution to the right maze in the right algorithm we wanted
     * @param name
     * @param algorithm
     * @return
     * @throws Exception
     */
    void solve(String name, String algorithm) throws Exception;
    /**
     * solving the maze in name "name" by the algorithm in name "algorithm"
     * and return a solution to the right maze in the right algorithm we wanted
     * @param name
     * @param algorithm
     * @return
     * @throws Exception
     */
    String displaySolution(String name ) throws Exception;
    /**
     * gets the name of the maze and return the maze in the right name (as string)
     * @param name
     * @return string presented the maze
     * @throws Exception
     */
    String display(String name) throws Exception;

    /**
     * gets the name of the file and return the size of the file
     * (return value is in long type)
     * @param name
     * @return
     * @throwsFileNotFoundException
     * @throws Exception
     */
    long fileSize(String name) throws Exception;

    /**
     * return a 2d array presented a cross section of the maze
     * (using the "getCrossBy" function in the Maze3d class
     * @param index
     * @param name
     * @return
     */
    int[][] getCrossByX(int index, String name);

    /**
     * return a 2d array presented a cross section of the maze
     * (using the "getCrossBy" function in the Maze3d class
     * @param index
     * @param name
     * @return
     */
    int[][] getCrossByY(int index, String name);

    /**
     * return a 2d array presented a cross section of the maze
     * (using the "getCrossBy" function in the Maze3d class
     * @param index
     * @param name
     * @return
     */
    int[][] getCrossByZ(int index, String name);

    /**
     * gets the name of the maze and return the size of the maze in the current name in int
     * format : 9+y_size * z_size * x_size
     * @param name
     * @return
     * @throws Exception
     */
    int mazeSize(String name) throws Exception;

    /**
     * return a string presented the files and folders
     * that in the right path
     * @param path
     * @return
     */
    String dir(String path);
    /**
     * return a true or false if the save succsses
     *
     * @param fileName for the fileName of the file
     * @param mazeName for the maze we want to save
     * @return bollean
     */
    boolean saveMaze(String fileName, String mazeName);
    boolean saveHashMap() throws IOException;
    boolean loadHashMap() throws IOException;
    boolean loadMaze(String s, String s1) throws IOException;
    void saveProperties();
    void loadProperties(String str);
    void exit();

}
