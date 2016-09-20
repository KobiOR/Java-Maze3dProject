package Presenters;

import java.util.Observable;

/**
 * Created by orrko_000 on 13/09/2016.
 */
public abstract class  Deliver extends Observable implements Command {
    public Presenter pDeliver;

    public Deliver(Presenter pDeliver) {
        super();
        this.pDeliver = pDeliver;
    }
}
