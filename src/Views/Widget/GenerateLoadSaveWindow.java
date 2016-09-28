package Views.Widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.FileDialog;

import java.io.File;
import java.nio.file.Paths;

public class GenerateLoadSaveWindow extends DialogWindow {

    String mazeName;
    boolean saveOrload;

    public GenerateLoadSaveWindow(boolean t, String mazeName) {
        saveOrload = t;
        this.mazeName = mazeName;
    }
    @Override
    protected void initWidgets() {
        if (saveOrload)//LOAD
        {
            FileDialog fd = new FileDialog(shell, SWT.OPEN);
            fd.setText("Open");
            fd.setFilterPath("C:/");
            String[] filterExt = {"*.maz", "*.text"};
            fd.setFilterExtensions(filterExt);
            String selected = fd.open();
            if(selected!=null) {
                File f = new File(selected);
                mazeName = new String(f.getName().split("\\.")[0]);
                setChanged();
                notifyObservers("load_maze " + selected + " " + mazeName);
                setChanged();
                notifyObservers("display " + mazeName);
            }
                while (!shell.isDisposed()) {
                    if (!shell.getDisplay().readAndDispatch())
                        shell.getDisplay().sleep();
                }
                shell.getDisplay().dispose();

        }


        else//SAVE
        {
            shell.setText("Save your maze :)");
            FileDialog fd = new FileDialog(shell, SWT.SAVE);
            fd.setText("Save");
            fd.setFilterPath("C:/");
            String[] filterExt = {"*.maz", "*.text"};
            fd.setFilterExtensions(filterExt);
            String selected = fd.open();
            if(selected!=null)  {
                System.out.print(selected);
                setChanged();
                notifyObservers("save_maze " + mazeName + " " + selected);
            }
            while (!shell.isDisposed()) {
            if (!shell.getDisplay().readAndDispatch())
                shell.getDisplay().sleep();
        }
            shell.getDisplay().dispose();
        }


    }

}
