package Views.Widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

/**
 * Created by orrko_000 on 22/09/2016.
 */
public class GenerateExitWindow extends DialogWindow  {
    @Override
    protected void initWidgets() {
        shell.setText("Are you sure?");
        shell.setSize(300, 100);
        shell.setLayout(new GridLayout(2, false));


        Button yesButton = new Button(shell, SWT.PUSH);
        yesButton.setLayoutData(new GridData(SWT.CENTER, SWT.LEFT, false, false, 0,0));
        yesButton.setText("Yes");
        yesButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {

                setChanged();
                notifyObservers("exit");
                shell.close();
            }
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {

            }
        });

        Button noButton = new Button(shell, SWT.PUSH);
        noButton.setLayoutData(new GridData(SWT.LEFT_TO_RIGHT, SWT.RIGHT, false, false,0, 0));
        noButton.setText("No");
        noButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                shell.close();
            }
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {

            }
        });
        shell.setDefaultButton(noButton);

    }

}
