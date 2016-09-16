package Controller.Commands;

import Controller.Controller;
import Controller.Deliver;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class DisplayCrossSection  extends Deliver {
    public DisplayCrossSection(Controller ctlr) {
        super(ctlr);
    }

    @Override
    public void doCommand(String cmd) {
        String[] input =cmd.split(" ");
        if(input[1].equals("x")){
            dController.getView().display(dController.getModel().getCrossByX(Integer.valueOf(input[0]),input[2]));return;}
        if(input[1].equals("y")){
            dController.getView().display(dController.getModel().getCrossByY(Integer.valueOf(input[0]),input[2]));return;}
        if(input[1].equals("z")){
            dController.getView().display(dController.getModel().getCrossByZ(Integer.valueOf(input[0]),input[2]));return;}
         dController.getView().display("Cant display the cross maze\n");
    }
}
