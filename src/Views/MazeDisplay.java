package Views;

import java.awt.*;
import java.lang.*;
import java.util.*;
import java.util.List;

import algorithms.search.Solution;
import algorithms.search.State;
import mazeGenerators.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
public class MazeDisplay extends Canvas {

	private Maze3d myMaze;
	public Player player;
	TimerTask task;
	boolean status=false;
	public String mazeName= "";
	private int mHeight=1;
	private Solution<Coordinate> mazeSolution=null;
	boolean solutionAvailable=false;
	boolean activeMaze=false;

	Timer timer;

	public MazeDisplay(Composite parent, int style) {
		super(parent, style);
		status=true;
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(!status)return;
				Coordinate pos = player.getPos();
				switch (e.keyCode) {
					case SWT.ARROW_RIGHT:
						if (!myMaze.WallExist(player.getPos().RIGHT()))
							player.RIGHT();
						redraw();
						break;

					case SWT.ARROW_LEFT:
						if (!myMaze.WallExist(player.getPos().LEFT()))
							player.LEFT();
						redraw();
						break;
					case SWT.ARROW_DOWN:
						if (!myMaze.WallExist(player.getPos().BACKWORDS()))
							player.BACKWORD();
						redraw();
						break;

					case SWT.ARROW_UP:
						if (!myMaze.WallExist(player.getPos().STRAIGHT()))
							player.STRAIGHT();
						redraw();
						break;

				}
				if(!myMaze.WallExist(player.getPos().UP())||!myMaze.WallExist(player.getPos().DOWN()))
					checkPosition();
			}
		});
		this.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				if (!status)return;
				if(myMaze==null || player==null) {
					e.gc.setForeground(new Color(null, 0, 0, 0));
					e.gc.setBackground(new Color(null, 0, 0, 0));
				}

				else if(status) {
					activeMaze=true;
					if(player.getPos().getcMazeHeight()==myMaze.getmHeight()-1)exit();
					e.gc.setForeground(new Color(null, 0, 0, 0));
					e.gc.setBackground(new Color(null, 0, 0, 0));

					Vector<Coordinate> v;
					int width = getSize().x;
					int height = getSize().y;
					int w = width / myMaze.getfWidth();
					int h = height / myMaze.getfHeight();
					for (int i = 0; i < myMaze.getfHeight(); i++)
					{
						v = myMaze.getPossibleFloors(new Coordinate(mHeight,0,0));
						for (int j = 0; j < myMaze.getfWidth(); j++) {
							int x = j * w;
							int y = i * h;
							if (myMaze.WallExist(new Coordinate(mHeight,i,j))) {
								e.gc.fillRectangle(x, y, w, h);//e.gc.fillGradientRectangle(x, y, w, h, true);
							}
							else {
								if(v!=null)
									for (Coordinate c : v) {
										if (c.equals(new Coordinate(mHeight,i,j).UP())) {
											e.gc.setForeground(new Color(null, 100, 100, 100));
											e.gc.setBackground(new Color(null, 100, 100, 100));
											e.gc.fillRectangle(x, y, w, h);
											e.gc.setForeground(new Color(null, 0, 0, 0));
											e.gc.setBackground(new Color(null, 0, 0, 0));

										}
										if (c.equals(new Coordinate(mHeight,i,j).DOWN())){
											e.gc.setForeground(new Color(null, 100, 100, 100));
											e.gc.setBackground(new Color(null, 100, 100, 100));
											e.gc.fillRectangle(x, y, w, h);
											e.gc.setForeground(new Color(null, 0, 0, 0));
											e.gc.setBackground(new Color(null, 0, 0, 0));
										}
									}
							}
						}

					}
					player.draw(w, h, e.gc);
				}

			}
		});
		start();

    }
	public void setMyMaze(Maze3d myMaze) {
		this.myMaze = new Maze3d(myMaze);
		player = new Player();
		player.setPos(new Coordinate(myMaze.getEntry()));

	}
	public void exit( ) {
		task.cancel();
		task=null;
		dispose();
	}
	public void checkPosition(){
		if (player.getPos().getcMazeHeight()==myMaze.getmHeight())
		{
			exit();
		}

		else{
		if (!myMaze.WallExist(player.getPos().UP())) {
			mHeight += 2;
			player.setPos(player.getPos().UP().UP());

		}
		else {
			if (!myMaze.WallExist(player.getPos().DOWN())) {
				mHeight -= 2;
				player.setPos(player.getPos().DOWN().DOWN());

			}
		}
	}


	}
	public void setSolution(Solution sol){
		getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				mazeSolution=sol;
			}
		});

	}
	public synchronized void solve() {

		List<State<Coordinate>> list = mazeSolution.getStates();
		Queue<Coordinate> q = new LinkedList<>();
		for (State<Coordinate> s : list)
			q.add((Coordinate) s.getValue());
			TimerTask tasker = new TimerTask() {

				@Override
				public void run() {
					getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							if(!q.isEmpty())
							{
								player.setPos(q.poll());
								mHeight=player.getPos().getcMazeHeight();
							}


						}
					});

				}
			};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(tasker, 0,500);
	}
	public Solution<Coordinate> getMazeSolution() {
		if(mazeSolution!=null)return mazeSolution;
		return null;
	}
	public void start()	{
		status=true;
		task = new TimerTask() {

			@Override
			public void run() {
				getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						redraw();if(!status)return;
				}
				});

			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 500);




	}
}
