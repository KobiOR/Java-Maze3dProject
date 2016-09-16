package Controller.Commands;

import Controller.Controller;
import Controller.Deliver;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class Solve  extends Deliver
{
    public Solve(Controller ctlr) {
        super(ctlr);
    }

    @Override
    public void doCommand(String cmd)
    {
        String[] str=cmd.split(" ");
        try {
            dController.getModel().solve(str[0],str[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}



