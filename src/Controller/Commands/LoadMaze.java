package Controller.Commands;

import Controller.Controller;
import Controller.Deliver;

import java.io.IOException;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class LoadMaze  extends Deliver {
    public LoadMaze(Controller ctlr) {
        super(ctlr);
    }

    @Override
    public void doCommand(String fileName) {
        String[] str=fileName.split(" ");
        try {
            if(dController.getModel().loadMaze(str[0],str[1]))
                this.dController.getView().display("The maze:"+str[0] +" was save to file:"+str[1]);
            else
                this.dController.getView().display("Cant save to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
