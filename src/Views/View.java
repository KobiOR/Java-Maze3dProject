package Views;

import Presenters.Presenter;
import mazeGenerators.Maze3d;

import java.io.IOException;
import java.util.Observable;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public interface View<T> {

    int getUserCommand();
    void display(String str);
    void display(T tValue);
    void display(int[][] maze3d);
    void start() throws IOException;
    void setCli(Cli c);
    void display(Maze3d maze3d);
}