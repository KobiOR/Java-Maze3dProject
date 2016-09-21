package Boot;

import View.MyAsciiArtMaker;
import View.View;
import Presenters.Presenter;
import Model.Model;
import Model.MyModel;
import java.io.IOException;
import java.util.Observable;


public class Run{

    public static void main(String[] args) throws IOException {
        View myView = new MyAsciiArtMaker("HW", 800, 600);
        Model myModel = new MyModel();
        Presenter c = new Presenter(myView,myModel);
        ((Observable)myView).addObserver(c);
        ((Observable)myModel).addObserver(c);
        myView.start();
      }

}
