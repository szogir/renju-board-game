package Core;


import java.awt.Point;
import java.io.Serializable;
// színes pont
public class ColorPoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int c; //szín tárolja
	private Point p; //koordinátákat tárol
	
	//konstruktor
	public ColorPoint(int x, int y, int color) {
		p = new Point(x, y);
		this.c = color;
	}
	
	//getterek setterek
	public int getColor() {
		return c;
	}
	public void setColor(int color) {
		c=color;
	}

	public int getX() {
		return (int) p.getX();
	}

	public int getY() {
		return (int) p.getY();
	}
	
	
	
	
}
