package Boot;

import Model.Model;
import Model.MyModel;
import Presenters.Presenter;
import Presenters.Properties;
import Presenters.PropertiesLoader;
import Views.MazeWindow;
import Views.View;

import java.io.IOException;
import java.util.Observable;


public class Run{

    public static void main(String[] args) throws IOException {
        Properties p= PropertiesLoader.getInstance().getProperties();
        View myView = new MazeWindow();
        Model myModel = new MyModel();
        Presenter c = new Presenter(myView,myModel);
        ((Observable)myView).addObserver(c);
        ((Observable)myModel).addObserver(c);
        myModel.loadHashMap();
        myView.start();
        myModel.saveHashMap();
        myModel.saveProperties();
      }

}
