package View;

import java.io.IOException;

/**
 * Created by orrko_000 on 21/09/2016.
 */
public class MazeWindow extends BasicWindow implements View{
    public MazeWindow(String title, int width, int height) {
        super(title, width, height);
    }

    @Override
    public int getUserCommand() {
        return 0;
    }

    @Override
    public void display(String str) {

    }

    @Override
    public void display(int[][] maze3d) {

    }

    @Override
    public void start() throws IOException {

    }

    @Override
    public void setCli(Cli c) {

    }

    @Override
    protected void initWidgets() {

    }
}
