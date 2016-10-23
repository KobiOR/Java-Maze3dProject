package Boot;

import Model.Model;
import Model.MyModel;
import Presenters.Presenter;
import Presenters.Properties;
import Presenters.PropertiesLoader;
import Views.MazeWindow;
import Views.MyView;
import Views.View;

import java.io.IOException;
import java.util.Observable;

import static java.lang.System.exit;
import static mazeGenerators.Maze3dGenerator.ANSI_RED;
import static mazeGenerators.Maze3dGenerator.ANSI_RESET;


public class Run {

    public static void main(String[] args) throws IOException {
        Properties p = PropertiesLoader.getInstance().getProperties();
        View myView = null;
        Model myModel = new MyModel();
        if (p != null) {
            if (p.getView().equals("GUI")) myView = new MazeWindow();
            if (p.getView().equals("CL")) myView = new MyView();
            else if (myView == null) {
                System.out.print(ANSI_RED + "Not supported view.Sorry :(\n" + ANSI_RESET);
                exit(1);
            }
            Presenter c = new Presenter(myView, myModel);
            ((Observable) myView).addObserver(c);
            ((Observable) myModel).addObserver(c);
            myView.start();


        }
        else
        {

                 myView = new MazeWindow();
                 Presenter c = new Presenter(myView, myModel);
                ((Observable) myView).addObserver(c);
                ((Observable) myModel).addObserver(c);
                myView.start();



        }
    }
}
