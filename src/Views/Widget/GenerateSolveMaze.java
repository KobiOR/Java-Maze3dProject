package Views.Widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by orrko_000 on 25/09/2016.
 */
public class GenerateSolveMaze extends DialogWindow  {
    private static String mazeName;

    public GenerateSolveMaze(String mName) {
        this.mazeName=mName;
    }
    @Override
    protected void initWidgets() {
        shell.setText("Which algorithem ??");
        shell.setSize(300, 100);
        shell.setLayout(new GridLayout(2, false));

        Button DFS = new Button(shell, SWT.PUSH);
        DFS.setLayoutData(new GridData(SWT.CENTER, SWT.LEFT, false, false, 0,0));
        DFS.setText("DFS");
        DFS.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                setChanged();
                notifyObservers("solve "+mazeName+ " DFS");
                shell.close();
                }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {

            }
        });

        Button BFS = new Button(shell, SWT.PUSH);
        BFS.setLayoutData(new GridData(SWT.LEFT_TO_RIGHT, SWT.RIGHT, false, false,0, 0));
        BFS.setText("BFS");
        BFS.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                setChanged();
                notifyObservers("solve "+mazeName+ " BFS");
                shell.close();
            }
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {

            }
        });

        shell.setDefaultButton(BFS);


    }

}
