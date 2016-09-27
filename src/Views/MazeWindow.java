package Views;

import Views.Widget.*;
import algorithms.search.Solution;
import mazeGenerators.Maze3d;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.*;
import java.util.*;
import java.util.List;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

public class MazeWindow<T> extends BasicWindow {

	private JLabel label;
	private MazeDisplay mazeDisplay;
	BasicWindow b = this;
	List<DialogWindow> dView = new ArrayList<>();
	String curr;
	Button btnDisplaySolution;
	boolean solutionAvailable = false;

	@Override
	protected void initWidgets() {
		GridLayout grid = new GridLayout(2, false);
		shell.setLayout(grid);

		Composite buttons = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		buttons.setLayout(rowLayout);

		Button btnGenerateMaze = new Button(buttons, SWT.PUSH);
		btnGenerateMaze.setText("Generate maze");
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				DialogWindow win = new GenerateMazeWindow();
				win.addObserver(b);
				win.start(display);
				btnDisplaySolution.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {


			}
		});

		Button loadMaze = new Button(buttons, SWT.PUSH);
		loadMaze.setText("Load maze");
		loadMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateMazeWindow win = new GenerateMazeWindow();
				win.start(display);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		Button saveMaze = new Button(buttons, SWT.PUSH);
		saveMaze.setText("Save maze");
		saveMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				notifyObservers("save_maze " + mazeDisplay.mazeName + " " + "c:// ");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		Button btnSolveMaze = new Button(buttons, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		btnSolveMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				DialogWindow win = new GenerateSolveMaze(mazeDisplay.mazeName);
				win.addObserver(b);
				win.start(display);
				dView.add(win);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		btnDisplaySolution = new Button(buttons, SWT.PUSH);
		btnDisplaySolution.setText("Display Solution");
		btnDisplaySolution.setEnabled(solutionAvailable);
		btnDisplaySolution.addSelectionListener(new SelectionListener()	{
			@Override
			public void widgetSelected(SelectionEvent e) {
				mazeDisplay.status=false;
				mazeDisplay.solve();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		Button bExit = new Button(buttons, SWT.PUSH);
		bExit.setText("Exit");
		bExit.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DialogWindow win = new GenerateExitWindow();
				win.addObserver(b);
				win.start(display);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		mazeDisplay = new MazeDisplay(shell, SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		mazeDisplay.setFocus();
	}
	@Override
	public int getUserCommand() {
		return 0;
	}
	@Override
	public void display(String str) {
		JFrame frame = new JFrame("Get aNote");
		frame.getRootPane().setBorder(
		BorderFactory.createEmptyBorder(20, 20, 20, 20));
		label = new JLabel(str);
		label.setFont(new Font("Dialog", Font.PLAIN, 20));
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 150);
		frame.setLocation(200, 200);
		frame.setVisible(true);

	}
	@Override
	public void display(Object tValue) {

		if (tValue.getClass().getName() == "java.lang.String") {
			display((String) tValue);
			return;
		}
		if (tValue.getClass().getName() == "mazeGenerators.Maze3d") {
			mazeDisplay.setMyMaze((Maze3d) tValue);
			mazeDisplay.mazeName = this.curr;
			return;
		}
		if (tValue.getClass().getName() == "algorithms.search.Solution") {
			mazeDisplay.setSolution((Solution)tValue);
		}
	}
	@Override
	public void display(int[][] maze3d) {
	}
	@Override
	public void setCli(Cli c) {
	}
	@Override
	public void update(Observable o, Object arg) {
		String[] strSplit = o.getClass().getName().split("\\.");
		String s=(String)arg;
		String[] str = s.split(" ");
		switch (strSplit[0])
		{
				case "Views":
					{
					if (str[0].equals("exit")){ exit(1);break;}
					if (str[0].equals("generate_maze")) {
						curr = new String(str[1]);
						setChanged();
						notifyObservers(str);
						break;
					}
					else {
						setChanged();
						notifyObservers(str);
						break;
					}


				}
				case "java.lang.String": {
					setChanged();
					notifyObservers(str);
					break;
				}

		}
			//TODO לסדר את כל החרא פה! לעשות סוויץ קייס


	}

	@Override
	public void run() {

	}


}


