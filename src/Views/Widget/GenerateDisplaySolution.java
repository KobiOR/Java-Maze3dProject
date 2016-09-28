package Views.Widget;

import Views.Widget.DialogWindow;
import algorithms.search.Solution;
import algorithms.search.State;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

/**
 * Created by orrko_000 on 27/09/2016.
 */
public class GenerateDisplaySolution<T> extends DialogWindow {
    Solution<State<T>> mazeSolution;

    public GenerateDisplaySolution(Solution<State<T>> solution) {
        super();
        this.mazeSolution =solution;
    }
    @Override
    protected void initWidgets() {
        shell.setText("Solution for/////////");
        shell.setSize(600, 600);
        shell.setLayout(new GridLayout(2, false));

        Button Here = new Button(shell, SWT.PUSH);
        Here.setLayoutData(new GridData(SWT.CENTER, SWT.LEFT, false, false, 0,0));
        Here.setText("DFS");
        Here.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                shell.close();

            }
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {

            }
        });

        Button InTheMaze = new Button(shell, SWT.PUSH);
        InTheMaze.setLayoutData(new GridData(SWT.LEFT_TO_RIGHT, SWT.RIGHT, false, false,0, 0));
        InTheMaze.setText("BFS");
        InTheMaze.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                shell.close();
            }
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {

            }
        });

        shell.setDefaultButton(InTheMaze);

    }
}
