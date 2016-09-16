package View;

import Controller.Command;
import Controller.Commands.*;
import Controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import static mazeGenerators.Maze3dGenerator.*;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class MyView extends BasicWindow implements View {
    private Cli cmdLine;
    private Controller myCtlr;
    private HashMap<String, Command> hMap;


    public MyView(String title, int width, int height) {
        super(title, width, height);
    }
    public void start() throws IOException {

        hMap=new HashMap<>();
        installCommands();
        cmdLine=new Cli(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true), hMap);
        instruction();
        cmdLine.start();

    }
    private void installCommands()    {
        hMap.put("dir",new Dir(myCtlr));
        hMap.put("generate_maze",new GenerateMaze(myCtlr));
        hMap.put("display",new Display(myCtlr));
        hMap.put("display_cross_section",new DisplayCrossSection(myCtlr));
        hMap.put("save_maze",new SaveMaze(myCtlr));
        hMap.put("load_maze",new LoadMaze(myCtlr));
        hMap.put("solve",new Solve(myCtlr));
        hMap.put("display_solution",new DisplaySolution(myCtlr));
        hMap.put("exit",new Exit(myCtlr));

    }
    public void setController(Controller controller)
    {
        this.myCtlr = controller;
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
}

