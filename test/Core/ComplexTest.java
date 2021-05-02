package Core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


import gui.Window;

public class ComplexTest {
	//elõször betöltök egy olyan ponthatlmazt amibõl sok féle végkimenetelt ki lehet hozni kevés pont lerakásával
	
	Game g;
	Window w;
	//ezek normalizálatlan koordináták, olyanok mint ha egérkattintásra keletkeznének
	ArrayList<ColorPoint> lista= new ArrayList<>(List.of(new ColorPoint(110,170,Board.BLACK),new ColorPoint(140,170,Board.BLACK),new ColorPoint(170,140,Board.BLACK),new ColorPoint(170,110,Board.BLACK),
			new ColorPoint(230,110,Board.WHITE),new ColorPoint(230,170,Board.WHITE),new ColorPoint(230,200,Board.WHITE),new ColorPoint(230,230,Board.WHITE)));
	

	@Before
	public void setUp() {
		w=new Window();
		g= new Game();
		g.getBoard().setpList(lista); //alap pontok beállítva
		}
	

	
	@Test
	public void testNoGameOver() {
		// a alapból beállított köveket ellenõrzöm, hogy nyert-e valaki
		g.setPoints();	//ez a metódushívás maga után vonja a count,rule és evaluate metódusok meghívódását is
		assertEquals( false ,g.isGameOver() );
		assertEquals( false ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//még nem nyert senki
	
	@Test
	public void testWhite5Win() { //ugy rakok le pontot hogy a fehérnek 5öse van, majd ellenõrzés
		g.getBoard().addPoint(230,140,Board.WHITE);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//vége, fehér nyert
	
	@Test
	public void testWhiteOverlineWin() { //ugy rakok le pontokat hogy a fehérnek overline-a van, majd ellenõrzés
		g.getBoard().addPoint(230,140,Board.WHITE);
		g.getBoard().addPoint(230,80,Board.WHITE);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//vége, fehér nyert
	
	@Test
	public void testBlackOverline() { //ugy rakok le pontokat hogy a feketének overline-a van, majd ellenõrzés
		g.getBoard().addPoint(200,170,Board.BLACK);
		g.getBoard().addPoint(50,170,Board.BLACK);
		g.getBoard().addPoint(80,170,Board.BLACK);
		g.getBoard().addPoint(170,170,Board.BLACK);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//vége, fehér nyert

	@Test
	public void testBlack5Win() { //ugy rakok le pontokat hogy a feketének 5öse van, majd ellenõrzés
		g.getBoard().addPoint(50,170,Board.BLACK);
		g.getBoard().addPoint(80,170,Board.BLACK);
		g.getBoard().addPoint(170,170,Board.BLACK);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( false ,g.isWhiteWin() );
		assertEquals( true ,g.isBlackWin() );
	}//vége, fekete nyert
	
	@Test
	public void testBlackD3() { //ugy rakok le pontot hogy a feketének dupla3 van, majd ellenõrzés
		g.getBoard().addPoint(170,170,Board.BLACK);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//vége, fehér nyert
	
	@Test
	public void testBlackD4() { //ugy rakok le pontokat hogy a feketének dupla4 van, majd ellenõrzés
		g.getBoard().addPoint(80,170,Board.BLACK);
		g.getBoard().addPoint(170,80,Board.BLACK);
		g.getBoard().addPoint(170,170,Board.BLACK);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//vége, fehér nyert
	
	@Test
	public void testSaveLoad() { 
		g.setPoints();//ez rögzíti a pontokat a mátrixban is
		
		//kimentem g-t
		Save.SaveGame(g,"testsaving.txt");//bezárza maga után a fájlt
		
		//a mentésem betöltöm g2-be
		Game g2=Load.LoadGame("testsaving.txt");//bezárza maga után a fájlt
		
		
		
		//objektum szinten nem sikerült összehasonlítani g-t és g2-t, ezért jobb híján megnézem pár változójára
		//a pontlista elso pontjának koordinátája
		assertSame( g.getBoard().getpListItem(0).getX() ,g2.getBoard().getpListItem(0).getX() );
		//a mátrix pár eleme
		assertSame( g.getBoard().getColor(0,0) ,g2.getBoard().getColor(0,0) );
		assertSame( g.getBoard().getColor(7,7) ,g2.getBoard().getColor(7,7) );
		assertSame( g.getBoard().getColor(5,4) ,g2.getBoard().getColor(5,4) );
		//pár játékváltozó
		assertSame( g.getQ() ,g2.getQ() );
		assertSame( g.isGameOver() ,g2.isGameOver());
		assertSame( g.isWhiteWin() ,g2.isWhiteWin());

	}// ezek alapján úgy tûnik, hogy a kimentett és a betöltött fájl tartalma megegyezik
	

}
