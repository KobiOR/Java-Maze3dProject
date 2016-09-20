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
        exit(1);

    }
}
