package Controller;

import Model.Model;
import View.View;

import java.util.HashMap;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public class MyController implements Controller {

    private View cView;

    public View getView() {
        return cView;
    }
    public Model getModel() {
        return cModel;
    }
    private Model cModel;
    private HashMap<String,Command> hMap;
    public MyController(View cView, Model cModel) {
        this.cView = cView;
        this.cModel = cModel;
    }
    @Override
    public void convert(String fileName) {

    }
}
