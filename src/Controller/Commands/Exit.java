package Controller.Commands;

import Controller.Controller;
import Controller.Deliver;

import static java.lang.System.exit;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class Exit  extends Deliver {
    public Exit(Controller ctlr) {
        super(ctlr);
    }

    @Override
    public void doCommand(String cmd) {
        exit(1);

    }
}
