package Presenters.Commands;

import Presenters.Deliver;
import Presenters.Presenter;

import static java.lang.System.exit;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class Exit  extends Deliver {
    public Exit(Presenter p) {
        super(p);
    }

    @Override
    public void doCommand(String cmd) {
        try {
           pDeliver.getModel().saveHashMap();
            exit(1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
