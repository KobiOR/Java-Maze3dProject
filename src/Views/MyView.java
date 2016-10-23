package Views;

import mazeGenerators.Maze3d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Observable;

import static mazeGenerators.Maze3dGenerator.*;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class MyView<T> extends BasicWindow {
    private Cli cmdLine;
    public MyView() {
        super();


    }
    public void start() throws IOException {
        cmdLine.start();
        instruction();

    }
    @Override
    public void setCli(Cli c) {
        cmdLine=c;
    }

    @Override
    public void display(Maze3d maze3d) {

    }

    @Override
    public int getUserCommand() {
        return 0;
    }

    @Override
    public void display(String str) {
        cmdLine.displaymessage(str);
    }
    @Override
    public void display(Object tValue) {

    }
    @Override
    public void display(int[][] maze3d) {
        for (int i = 0; i <maze3d.length ; i++) {
            for (int j = 0; j <maze3d[i].length ; j++)
                System.out.print(maze3d[i][j]);

            System.out.print("\n");
        }
    }
    @Override
    protected void initWidgets() {

    }
    private void instruction(){
        cmdLine.displaymessage("Hello");
        cmdLine.displaymessage("For Dir:dir <path>");
        cmdLine.displaymessage("To create maze write:generate_maze <name> <x,y,z> <algorithm> (Optinal)");
        cmdLine.displaymessage("To display maze write:display <name>");
        cmdLine.displaymessage("To display maze to 2d press: display_cross_section <Index> <x/y/z> <MazeName>");
        cmdLine.displaymessage("To save maze press: save_maze <MazeName> <FileName>");
        cmdLine.displaymessage("To load maze press:load_maze <FileName> <MazeName>");
        cmdLine.displaymessage("To solve maze press:solve <MazeName> <Algorithm>");
        cmdLine.displaymessage("To display solution press: display_solution <MazeName>");
        cmdLine.displaymessage("To exit press:exit");

    }
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }

    @Override
    public void run() {

    }
}

