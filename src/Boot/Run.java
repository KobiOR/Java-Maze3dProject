package Boot;

import View.MyView;
import Presenters.Presenter;
import Model.Model;
import Model.MyModel;
import java.io.IOException;
import java.util.Observable;


public class Run{

    public static void main(String[] args) throws IOException {
        MyView myView = new MyView("HW", 200, 100);
        Model myModel = new MyModel();
        Presenter c = new Presenter(myView, myModel);
        ((Observable)myView).addObserver(c);
        ((Observable)myModel).addObserver(c);
        myView.start();
      }

}
