package Views;

import Views.Widget.*;
import mazeGenerators.Maze3d;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MazeWindow<T> extends BasicWindow {

	private MazeDisplay mazeDisplay;
	BasicWindow b=this;
	List<DialogWindow> dView=new ArrayList<>();

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
				mazeDisplay.display(str);
			}
	@Override
	public void display(Object tValue) {
		if (tValue.getClass().getName()=="String" ||tValue.getClass().getName()=="java.lang.String"){this.display((String)tValue);return;}
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
		String s=(String)arg;
		String[] str=s.split(" ");
		if(str[0].equals("generate_maze"))mazeDisplay.mazeName=new String(str[1]);
		if(str.equals("exit"))mazeDisplay.exit();
		setChanged();
		notifyObservers(str);
	}

	@Override
	public void run() {

	}
}
