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
        myView.start();
        myView.test("generate_maze aaa 5 11 13 GrowingTree");

        TimeUnit.SECONDS.sleep(1);
        myView.test("display aaa");
        TimeUnit.SECONDS.sleep(1);
         myView.test("solve a BFS");
      //  myView.test("solve a DFS");
       /// myView.test("display_solution a");
      //  myView.test("display_solution a");
       // myView.test("display_cross_section 3 x a");
       // myView.test("display_cross_section 1 y a");
       // myView.test("display_cross_section 5 z a");
    }
}
