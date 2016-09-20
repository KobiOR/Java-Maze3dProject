package View;

import Presenters.Presenter;

import java.io.IOException;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public interface View {

   int getUserCommand();
    void display(String str);
    void display(int[][] maze3d);

    void start() throws IOException;
    void setCli(Cli c);
}