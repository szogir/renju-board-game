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
		// a game konstuktora meghivja a paraméter nélküli Board() konstruktort
		//a Board konstruktor által létrehozott mátrix(15x15)  minden sarka üresre van állítva alapból
		assertEquals("ba sarok üres", Board.EMPTY, g.getBoard().getColor(0, 0));
		assertEquals("bf sarok üres", Board.EMPTY, g.getBoard().getColor(0, 14));
		assertEquals("ja saroküres", Board.EMPTY, g.getBoard().getColor(14, 0));
		assertEquals("jf sarok üres", Board.EMPTY, g.getBoard().getColor(14, 14));
	}

	@Test
	public void testBoardSetGet() {
		
		//amilyen szint beállítunk a mátrixban, valóban azt is kapjuk vissza
		//ha nem állítottunk semmit akkor EMPTY
		g.getBoard().setColor(5, 5, Board.BLACK);
		g.getBoard().setColor(6, 6, Board.WHITE);
		
		assertEquals("5;5 jó", Board.BLACK, g.getBoard().getColor(5, 5));
		assertEquals("6;6 jó", Board.WHITE, g.getBoard().getColor(6, 6));
		assertEquals("6;6 jó", Board.EMPTY, g.getBoard().getColor(7, 7));

	}
	
	@Test
	public void testCoordNorm() {
		
		int a=80; 	//ebbõl 2-t kéne csinálnia
		int b=410;	//ebbõl 13-at csinál
		int c=342;	//ebbõl 11-et
		int d=345;	//ebbõl is 11-et
		assertEquals("normalizált a jó", 2, g.coordNormalize (a));
		assertEquals("normalizát b jó",13 , g.coordNormalize (b));
		assertEquals("normalizát c jó",11 , g.coordNormalize (c));
		assertEquals("normalizát d jó",11 , g.coordNormalize (d));
	}
	
	@Test
	public void testCoordNormDraw() {
		int a=80; 	//ebbõl 80-at kéne csinálnia
		int b=340;	//ebbõl 350-et csinál
		int c=350;	//ebbõl 350-et csinál
		int d=364;	//ebbõl 350-et csinál
		assertEquals("normalizált a jó", 80, g.coordNormalizeDraw (a));
		assertEquals("normalizát b jó",350 , g.coordNormalizeDraw (b));
		assertEquals("normalizát c jó",350 , g.coordNormalizeDraw (c));
		assertEquals("normalizát d jó",350 , g.coordNormalizeDraw (d));
	}
	
	
}
