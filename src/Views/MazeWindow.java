package Views;

import Views.Widget.DialogWindow;
import Views.Widget.GenerateExitWindow;
import Views.Widget.GenerateMazeWindow;
import mazeGenerators.Maze3d;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import java.lang.*;
import java.util.Observable;
import java.util.Observer;

public class MazeWindow<T> extends BaseWindow {

	private MazeDisplay mazeDisplay;
	private Character character;
	BaseWindow b=this;


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
				// TODO Auto-generated method stub

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
				notifyObservers("save_maze "+mazeDisplay.mazeName+ " "+"c:// ");
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
				GenerateExitWindow win = new GenerateExitWindow();
				win.start(display);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

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
		setChanged();
		MessageBox msg = new MessageBox(b.shell, SWT.OK);
		msg.setText("Message");
		msg.setMessage(str);
		msg.open();
	}

	@Override
	public void display(Object tValue) {
		if (tValue.getClass().getName()=="String"){display((String)tValue);return;}
		if(tValue.getClass().getName()=="mazeGenerators.Maze3d") {
		mazeDisplay.setMyMaze((Maze3d) tValue);
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
		setChanged();
		String s=(String)arg;
		String[] str=s.split(" ");
		notifyObservers(str);
	}
}
