package Core;

import java.io.Serializable;
//játékos osztály
public class Player implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int color;
	 

	 //konstruktor
	 public Player (int c) {
	        this.color = c;
	    }

	 public Player () {}
	 
	 //getter
	 public int getColor() {
	        return color;
	    }
	 
	 //setter
	 public void setColor(int c) {
		 this.color=c;
	 }
}
