package Presenters.Commands;

import Presenters.Deliver;
import Presenters.Presenter;

import java.io.IOException;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class LoadMaze  extends Deliver {
    public LoadMaze(Presenter p) {
        super(p);
    }
    @Override
    public void doCommand(String fileName) {
        String[] str=fileName.split(" ");
        try {
            if (str.length>1)
            if(pDeliver.getModel().loadMaze(str[0],str[1]) && (str[1]!=null || str[1].isEmpty())) {
                this.pDeliver.getView().display("The maze:" + str[0] + " are load to HashMap");
            }
            else
                this.pDeliver.getView().display("Cant load maze");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
