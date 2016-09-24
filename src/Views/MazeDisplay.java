package Views;

import java.lang.*;
import java.util.TimerTask;

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
	private Maze3dType mBuilder;
	private Player player;

	public MazeDisplay(Composite parent, int style) {
		super(parent, style);
		mBuilder=new GrowingTreeGenerator();
		myMaze=mBuilder.Generate(1,23,47);
		player = new Player();
		player.setPos(myMaze.getEntry());
						
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
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
			}
		});
	    this.addPaintListener(new PaintListener() {
			@Override
		public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null,0,0,0));
				   e.gc.setBackground(new Color(null,0,0,0));
				   

				   int width=getSize().x;
				   int height=getSize().y;

				   int w=width/myMaze.getfWidth();
				   int h=height/myMaze.getfHeight();

				   for(int i=0;i<myMaze.getfHeight();i++)
				      for(int j=0;j<myMaze.getfWidth();j++){
				          int x=j*w;
				          int y=i*h;
				          if(myMaze.WallExist(new Coordinate(1,i,j)))
				              e.gc.fillRectangle(x,y,w,h);
				      }
				   
				 
				   player.draw(w, h, e.gc);
				
			}
		});
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {	
				getDisplay().syncExec(new Runnable() {					

					@Override
					public void run() {
						redraw();
					}
				});
				
			}
		};
		//Timer timer = new Timer();
		//timer.scheduleAtFixedRate(task, 0, 500);
	}

}
