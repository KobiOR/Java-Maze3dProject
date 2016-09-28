package Views;

import Views.Widget.*;
import algorithms.search.Solution;
import com.sun.javafx.tk.*;
import com.sun.javafx.tk.Toolkit;
import javafx.concurrent.Task;
import mazeGenerators.Maze3d;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static java.lang.System.exit;

public class MazeWindow<T> extends BasicWindow {

	private MazeDisplay mazeDisplay;
	BasicWindow b = this;
	List<DialogWindow> dView = new ArrayList<>();
	String curr;
	Button btnDisplaySolution;
	boolean solutionAvailable = false;
	Button saveMaze;
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
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						GenerateLoadSaveWindow win=new GenerateLoadSaveWindow(true,mazeDisplay.mazeName);
						win.addObserver(b);
						win.start(display);
						shell.open();

				}
				});


			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		saveMaze = new Button(buttons, SWT.PUSH);
		saveMaze.setText("Save maze");
		saveMaze.setEnabled(false);
		saveMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						GenerateLoadSaveWindow win=new GenerateLoadSaveWindow(false,mazeDisplay.mazeName);
						win.addObserver(b);
						win.start(display);
					}
				});
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
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						DialogWindow win = new GenerateSolveMaze(mazeDisplay.mazeName);
						win.addObserver(b);
						win.start(display);
						dView.add(win);
					}
				});


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


						display.asyncExec(new Runnable() {

							@Override
							public void run() {
						mazeDisplay.solve();


							}
						});

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

		addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosed(WindowEvent e)
			{
		mazeDisplay.timer.cancel();
		mazeDisplay.task.cancel();
		mazeDisplay.status=false;
				setChanged();
				notifyObservers("exit");
				shell.close();
			}
		});

		mazeDisplay = new MazeDisplay(shell, SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		mazeDisplay.setFocus();
		run();

	}
	@Override
	public int getUserCommand() {
		return 0;
	}
	@Override
	public void display(String str) {
		if (str.length()<50)
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					GenerateNoteWindow.getInstance(str, display);

				}
			});
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
			display.asyncExec(new Runnable() {

				@Override
				public void run() {
					mazeDisplay.setSolution((Solution)tValue);
					mazeDisplay.solutionAvailable=true;
				}
			});

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

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				display.syncExec(new Runnable() {

					@Override
					public void run() {
					if(mazeDisplay.mazeName!=null)
					{
						saveMaze.setEnabled(mazeDisplay.activeMaze);
						btnDisplaySolution.setEnabled(mazeDisplay.solutionAvailable);
					}
					}
				});

			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0,1500);


	}
	public void display(Maze3d maze3d) {
		mazeDisplay.setMyMaze( maze3d);
		mazeDisplay.mazeName = this.curr;

	}

}


