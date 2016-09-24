package Boot;

import Views.MazeWindow;
import Views.View;
import Presenters.Presenter;
import Model.Model;
import Model.MyModel;
import java.io.IOException;
import java.util.Observable;


public class Run{

    public static void main(String[] args) throws IOException {
        View myView = new MazeWindow();
        Model myModel = new MyModel();
        Presenter c = new Presenter(myView,myModel);
        ((Observable)myView).addObserver(c);
        ((Observable)myModel).addObserver(c);
        myView.start();
      }

}
