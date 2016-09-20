package Presenters.Commands;

import Presenters.Deliver;
import Presenters.Presenter;

import static mazeGenerators.Maze3dGenerator.*;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class GenerateMaze  extends Deliver {
    public GenerateMaze(Presenter p) {
        super(p);
    }
    @Override
        public void doCommand(String cmd) {
        try {

            String temp[] = cmd.split(" ");
            Integer mHeight=Integer.parseInt(temp[1]);
            Integer fHeight=Integer.parseInt(temp[2]);
            Integer fWidth=Integer.parseInt(temp[3]);
            if (temp.length>4)
                pDeliver.getModel().generate3dmaze(temp[0],mHeight,fHeight,fWidth,temp[4]);
            else
                pDeliver.getModel().generate3dmaze(temp[0],mHeight,fHeight,fWidth," ");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(ANSI_RED+ANSI_BOLD+"Catch Bad input"+ANSI_BOLD_END+ANSI_RESET);
            System.out.println(ANSI_RED+ANSI_BOLD+"Fix your request please"+ANSI_BOLD_END+ANSI_RESET);
            System.out.println(ANSI_RED+ANSI_BOLD+"Example generate_maze KobiMaze GrowingTree"+ANSI_BOLD_END+ANSI_RESET); }
    }
}
