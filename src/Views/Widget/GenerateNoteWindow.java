package Views.Widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.MessageBox;

/**
 * Created by orrko_000 on 25/09/2016.
 */
public class GenerateNoteWindow extends DialogWindow {
    String note;
    public GenerateNoteWindow(String str) {
        note=str;
    }

    @Override
    protected void initWidgets() {
        shell.setText("Are you sure?");
        shell.setSize(300, 100);
        shell.setLayout(new GridLayout(3, false));
        MessageBox msg = new MessageBox(super.shell, SWT.OK);
        msg.setText("Title");
        msg.setMessage("Button was clicked");
        msg.open();


        }
    }

