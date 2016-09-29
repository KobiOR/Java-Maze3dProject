package Views.Widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


public class GenerateNoteWindow extends DialogWindow {

    static private String note;
    private static GenerateNoteWindow instance;

    private GenerateNoteWindow(String str,Display d) {
        super();
        shell=new Shell(d);
        note=str;
        MessageBox msg = new MessageBox(shell, SWT.OK);
        msg.setText("Note");

        msg.setMessage(note);
        msg.open();
    }
    public static GenerateNoteWindow getInstance(String str,Display d) {
        if (instance == null)
            new GenerateNoteWindow(str, d);
        return instance;

    }
    @Override
    protected void initWidgets() {



    }
}
