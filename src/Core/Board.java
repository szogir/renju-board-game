package Core;



import java.io.Serializable;
import java.util.ArrayList;



//ez az osztály felel a pályamátrix és a pontlista kezeléséért
public class Board implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int EMPTY = 0;
    public static int WHITE = 1;
    public static int BLACK = 2;
    
	private int dim;//pályaméret
    private int[][] matrix; // a "pálya"
    
    //a lerakott színes pontok listája
    private ArrayList<ColorPoint> pList;
    

    
    //pálya konstruktora
    public Board(int d) {
        dim = d;
        pList = new ArrayList<>();
        matrix = new int[d][d];		
         for(int i=0; i<dim; i++)
        	for(int j=0; j<dim; j++)
        		setColor(i, j, EMPTY);
        
    }

    // konstruktor (15x15)
    public Board() {
        this(15);
    }
	
    //getterek és setterek
    //milyen színû egy mezõ?
    public int getColor(int x, int y) {
        return matrix[x][y];
    }
	//egy mezõ színének beállítsa
    public void setColor(int x, int y, int value) {
        matrix[x][y] = value;
    }
	
    //mekkora a pályaméret?
    public int getDim() {
        return dim;
    }
	
    //mátrix gettere
    public int[][] getMatrix() {
        return this.matrix;
    }
	
    //mátrix settere
    public void setMatrix(int[][] g) {
        this.matrix = g;
    }   
  
    //a pontlista adott elemét adja visza
    public ColorPoint getpListItem(int i) {
		return pList.get(i);
    }
    
    public ColorPoint getpListItem(int x, int y) {
    	for(ColorPoint p: pList) {
			if(p.getX()==x &&p.getY()==y)
				return p;
		}
		return null;	
    }
    
    //pontlista hossza
    public int getpListSize() {
    	return pList.size();
    }
    
    //visszaadja a pontlistát
    public ArrayList<ColorPoint> getpList() {
		return pList;
    }
    //uj ures listát állít be pontlistának
    public void resetpList() {
    	pList= new ArrayList<>();
    }
    //pontlistának beállít egy tetszõleges listát
    public void setpList(ArrayList<ColorPoint> nl) {
    	pList= nl;
    }
    //hosszáad egy x y pontot a listához
    public void addPoint(int x, int y,int c) {
    	pList.add(new ColorPoint(x,y,c));
    }
   
    //a pontlista egy elemát törli
    public void deletePoint(int i) {
    	pList.remove(i-1);
    }
}
