package Presenters.Commands;

import Presenters.Controller;
import Presenters.Deliver;
import Presenters.Presenter;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class DisplayCrossSection  extends Deliver {
    public DisplayCrossSection(Presenter ctlr) {
        super(ctlr);
    }

    @Override
    public void doCommand(String cmd) {
        String[] input =cmd.split(" ");
        if(input[1].equals("x")){
            pDeliver.getView().display(pDeliver.getModel().getCrossByX(Integer.valueOf(input[0]),input[2]));return;}
        if(input[1].equals("y")){
            pDeliver.getView().display(pDeliver.getModel().getCrossByY(Integer.valueOf(input[0]),input[2]));return;}
        if(input[1].equals("z")){
            pDeliver.getView().display(pDeliver.getModel().getCrossByZ(Integer.valueOf(input[0]),input[2]));return;}
        pDeliver.getView().display("Cant display the cross maze\n");
    }
}
