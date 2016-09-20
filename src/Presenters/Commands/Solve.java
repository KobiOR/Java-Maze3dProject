package Presenters.Commands;

import Presenters.Deliver;
import Presenters.Presenter;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class Solve  extends Deliver
{
    public Solve(Presenter p) {
        super(p);
    }

    @Override
    public void doCommand(String cmd)
    {
        String[] str=cmd.split(" ");
        try {
            pDeliver.getModel().solve(str[0],str[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}



