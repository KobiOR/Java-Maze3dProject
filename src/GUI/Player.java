package GUI;

import java.awt.Graphics;

import mazeGenerators.Coordinate;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

public class Player {
	private Coordinate c;


	private Image img;

	public Coordinate getC() {
		return new Coordinate(c);
	}
	public Player() {
		img = new Image(null,"images/player.gif");
	}
	public Coordinate getPos() {
		return c;
	}
	public void setPos(Coordinate c) {
		this.c = c;
	}
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height,cellWidth * c.getcFloorWidth(), cellHeight * c.getcFloorHeight(), cellWidth, cellHeight);
	}
	public void RIGHT() {
		c=c.RIGHT();
	}
	public void LEFT() {
		c=c.LEFT();
	}
	public void UP() {
		c=c.UP();
	}
	public void DOWN() {
		c=c.DOWN();	}
	public void BACKWORD() {
		c=c.BACKWORDS();	}
	public void STRAIGHT() {
		c=c.STRAIGHT();	}


	

}
