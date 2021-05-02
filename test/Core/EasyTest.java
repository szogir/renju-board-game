package Core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EasyTest {
	
	Game g;
	
	@Before
	public void setUp() {
	g=new Game();	
	}
	
	
	@Test
	public void testBoardKonst() {
		// a game konstuktora meghivja a param�ter n�lk�li Board() konstruktort
		//a Board konstruktor �ltal l�trehozott m�trix(15x15)  minden sarka �resre van �ll�tva alapb�l
		assertEquals("ba sarok �res", Board.EMPTY, g.getBoard().getColor(0, 0));
		assertEquals("bf sarok �res", Board.EMPTY, g.getBoard().getColor(0, 14));
		assertEquals("ja sarok�res", Board.EMPTY, g.getBoard().getColor(14, 0));
		assertEquals("jf sarok �res", Board.EMPTY, g.getBoard().getColor(14, 14));
	}

	@Test
	public void testBoardSetGet() {
		
		//amilyen szint be�ll�tunk a m�trixban, val�ban azt is kapjuk vissza
		//ha nem �ll�tottunk semmit akkor EMPTY
		g.getBoard().setColor(5, 5, Board.BLACK);
		g.getBoard().setColor(6, 6, Board.WHITE);
		
		assertEquals("5;5 j�", Board.BLACK, g.getBoard().getColor(5, 5));
		assertEquals("6;6 j�", Board.WHITE, g.getBoard().getColor(6, 6));
		assertEquals("6;6 j�", Board.EMPTY, g.getBoard().getColor(7, 7));

	}
	
	@Test
	public void testCoordNorm() {
		
		int a=80; 	//ebb�l 2-t k�ne csin�lnia
		int b=410;	//ebb�l 13-at csin�l
		int c=342;	//ebb�l 11-et
		int d=345;	//ebb�l is 11-et
		assertEquals("normaliz�lt a j�", 2, g.coordNormalize (a));
		assertEquals("normaliz�t b j�",13 , g.coordNormalize (b));
		assertEquals("normaliz�t c j�",11 , g.coordNormalize (c));
		assertEquals("normaliz�t d j�",11 , g.coordNormalize (d));
	}
	
	@Test
	public void testCoordNormDraw() {
		int a=80; 	//ebb�l 80-at k�ne csin�lnia
		int b=340;	//ebb�l 350-et csin�l
		int c=350;	//ebb�l 350-et csin�l
		int d=364;	//ebb�l 350-et csin�l
		assertEquals("normaliz�lt a j�", 80, g.coordNormalizeDraw (a));
		assertEquals("normaliz�t b j�",350 , g.coordNormalizeDraw (b));
		assertEquals("normaliz�t c j�",350 , g.coordNormalizeDraw (c));
		assertEquals("normaliz�t d j�",350 , g.coordNormalizeDraw (d));
	}
	
	
}
