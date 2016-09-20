package Presenters.Commands;

import Presenters.Deliver;
import Presenters.Presenter;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class SaveMaze  extends Deliver {
    public SaveMaze(Presenter p) {
        super(p);
    }

    @Override
    public void doCommand(String fileName) {
        String[] str=fileName.split(" ");
        if(pDeliver.getModel().saveMaze(str[1],str[0] ))
            this.pDeliver.getView().display("The maze:"+str[0] +" was save to file:"+str[1]);
        else
            this.pDeliver.getView().display("Cant save to file");
    }
}
