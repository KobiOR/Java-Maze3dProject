package Boot;

import View.MyView;
import Controller.MyController;
import Model.MyModel;


import java.io.IOException;


public class Run{

    public static void main(String[] args) throws IOException {
        MyView mv = new MyView("HW", 200, 100);
        MyModel m = new MyModel();
        MyController c = new MyController(mv, m);
        m.setController(c);
        mv.setController(c);
        mv.start();



      }
      /*
    private static void buildHelloWorld() throws IOException {
        MyView hello = new MyView("HW", 200, 100);
        MyModel m = new MyModel();
        MyController c = new MyController(hello, m);
        m.setController(c);
        hello.start();
    }
    private static void buildMyAsciiArtMaker() {
        MyAsciiArtMaker win = new MyAsciiArtMaker("ASCII maker", 500, 300);
        MyModel m = new MyModel();
        MyController c = new MyController(win, m);
        m.setController(c);
        win.setController(c);
        win.start();
    }
*/
}
