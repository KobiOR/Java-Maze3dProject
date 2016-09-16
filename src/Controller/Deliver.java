package Controller;

/**
 * Created by orrko_000 on 13/09/2016.
 */
public abstract class  Deliver implements Command {
    public Controller dController;


    public Deliver(Controller ctlr) {
        super();
        this.dController = ctlr;
    }
}
