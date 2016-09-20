package Presenters;

import Model.Model;
import View.View;

/**
 * Created by orrko_000 on 12/09/2016.
 */
public interface Controller {
    void convert(String fileName);
    public View getView();
    public Model getModel();

}
