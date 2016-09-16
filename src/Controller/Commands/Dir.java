package Controller.Commands;


import Controller.Controller;
import Controller.Deliver;

import static mazeGenerators.Maze3dGenerator.*;

/**
 * Created by orrko_000 on 12/09/2016.
 *
 */
public class Dir extends Deliver {

    public Dir(Controller ctlr) {
        super(ctlr);
    }

    @Override
    public void doCommand(String cmd) {
        try {
            dController.getView().display(dController.getModel().dir(cmd));
        } catch (Exception e) {

            System.out.println(ANSI_RED+ANSI_BOLD+"Catch NullPointerException"+ANSI_BOLD_END+ANSI_RESET);
            System.out.println(ANSI_RED+ANSI_BOLD+"Fix your location"+ANSI_BOLD_END+ANSI_RESET);
        }
    }
}
