package Presenters.Commands;

import Presenters.Deliver;
import Presenters.Presenter;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class Display  extends Deliver {

    public Display(Presenter p) {
        super(p);
    }

    @Override
    public void doCommand(String cmd) {

        try {
            pDeliver.getView().display(pDeliver.getModel().display(cmd));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
