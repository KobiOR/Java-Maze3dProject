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
        cmdLine.displaymessage(ANSI_BOLD+ANSI_WHITE+"Hello"+ANSI_RESET);
        cmdLine.displaymessage(ANSI_BOLD+ANSI_WHITE+"For Dir:"+ANSI_BOLD_END+" dir <path>"+ANSI_RESET);
        cmdLine.displaymessage(ANSI_WHITE+ANSI_BOLD+"To create maze write:"+ANSI_BOLD_END+"generate_maze <name> <x,y,z> <algorithm> (Optinal)"+ANSI_RESET);
        cmdLine.displaymessage(ANSI_WHITE+ANSI_BOLD+"To display maze write:"+ANSI_BOLD_END+" display <name>"+ANSI_RESET);
        cmdLine.displaymessage(ANSI_WHITE+ANSI_BOLD+"To display maze to 2d press: "+ANSI_BOLD_END+"display_cross_section <Index> <x/y/z> <MazeName>"+ANSI_BOLD_END+ANSI_RESET);
        cmdLine.displaymessage(ANSI_WHITE+ANSI_BOLD+"To save maze press: "+ANSI_BOLD_END+"save_maze <MazeName> <FileName>"+ANSI_RESET);
        cmdLine.displaymessage(ANSI_WHITE+ANSI_BOLD+"To load maze press:"+ANSI_BOLD_END+" load_maze <FileName> <MazeName>"+ANSI_RESET);
        cmdLine.displaymessage(ANSI_WHITE+ANSI_BOLD+"To solve maze press:"+ANSI_BOLD_END+" solve <MazeName> <Algorithm>"+ANSI_RESET);
        cmdLine.displaymessage(ANSI_WHITE+ANSI_BOLD+"To display solution press:"+ANSI_BOLD_END+" display_solution <MazeName>"+ANSI_RESET);
        cmdLine.displaymessage(ANSI_RED+ANSI_BOLD+"To exit press:"+ANSI_BOLD_END+"exit"+ANSI_RESET);

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

