package Core;



import java.io.Serializable;
import java.util.ArrayList;



//ez az oszt�ly felel a p�lyam�trix �s a pontlista kezel�s��rt
public class Board implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int EMPTY = 0;
    public static int WHITE = 1;
    public static int BLACK = 2;
    
	private int dim;//p�lyam�ret
    private int[][] matrix; // a "p�lya"
    
    //a lerakott sz�nes pontok list�ja
    private ArrayList<ColorPoint> pList;
    

    
    //p�lya konstruktora
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
	
    //getterek �s setterek
    //milyen sz�n� egy mez�?
    public int getColor(int x, int y) {
        return matrix[x][y];
    }
	//egy mez� sz�n�nek be�ll�tsa
    public void setColor(int x, int y, int value) {
        matrix[x][y] = value;
    }
	
    //mekkora a p�lyam�ret?
    public int getDim() {
        return dim;
    }
	
    //m�trix gettere
    public int[][] getMatrix() {
        return this.matrix;
    }
	
    //m�trix settere
    public void setMatrix(int[][] g) {
        this.matrix = g;
    }   
  
    //a pontlista adott elem�t adja visza
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
    
    //visszaadja a pontlist�t
    public ArrayList<ColorPoint> getpList() {
		return pList;
    }
    //uj ures list�t �ll�t be pontlist�nak
    public void resetpList() {
    	pList= new ArrayList<>();
    }
    //pontlist�nak be�ll�t egy tetsz�leges list�t
    public void setpList(ArrayList<ColorPoint> nl) {
    	pList= nl;
    }
    //hossz�ad egy x y pontot a list�hoz
    public void addPoint(int x, int y,int c) {
    	pList.add(new ColorPoint(x,y,c));
    }
   
    //a pontlista egy elem�t t�rli
    public void deletePoint(int i) {
    	pList.remove(i-1);
    }
}
