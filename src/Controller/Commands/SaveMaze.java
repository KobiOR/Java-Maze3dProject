package Controller.Commands;

import Controller.Controller;
import Controller.Deliver;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class SaveMaze  extends Deliver {
    public SaveMaze(Controller ctlr) {
        super(ctlr);
    }

    @Override
    public void doCommand(String fileName) {
        String[] str=fileName.split(" ");
        if(dController.getModel().saveMaze(str[1],str[0] ))
            this.dController.getView().display("The maze:"+str[0] +" was save to file:"+str[1]);
        else
            this.dController.getView().display("Cant save to file");
    }
}
