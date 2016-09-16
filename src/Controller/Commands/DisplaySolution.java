package Controller.Commands;

import Controller.Controller;
import Controller.Deliver;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class DisplaySolution  extends Deliver {
    public DisplaySolution(Controller ctlr) {
        super(ctlr);
    }

    @Override
    public void doCommand(String cmd) {
        try {
            dController.getView().display(dController.getModel().displaySolution(cmd));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
