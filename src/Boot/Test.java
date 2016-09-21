package Boot;

import Model.Model;
import Model.MyModel;

import Presenters.Presenter;
import View.MyView;
import com.sun.deploy.util.Waiter;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

/**
 * Created by orrko_000 on 21/09/2016.
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        MyView myView = new MyView("HW", 200, 100);
        Model myModel = new MyModel();
        Presenter c = new Presenter(myView, myModel);
        ((Observable)myView).addObserver(c);
        ((Observable)myModel).addObserver(c);


    }
}
