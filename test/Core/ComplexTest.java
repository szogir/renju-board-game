package Core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


import gui.Window;

public class ComplexTest {
	//el�sz�r bet�lt�k egy olyan ponthatlmazt amib�l sok f�le v�gkimenetelt ki lehet hozni kev�s pont lerak�s�val
	
	Game g;
	Window w;
	//ezek normaliz�latlan koordin�t�k, olyanok mint ha eg�rkattint�sra keletkezn�nek
	ArrayList<ColorPoint> lista= new ArrayList<>(List.of(new ColorPoint(110,170,Board.BLACK),new ColorPoint(140,170,Board.BLACK),new ColorPoint(170,140,Board.BLACK),new ColorPoint(170,110,Board.BLACK),
			new ColorPoint(230,110,Board.WHITE),new ColorPoint(230,170,Board.WHITE),new ColorPoint(230,200,Board.WHITE),new ColorPoint(230,230,Board.WHITE)));
	

	@Before
	public void setUp() {
		w=new Window();
		g= new Game();
		g.getBoard().setpList(lista); //alap pontok be�ll�tva
		}
	

	
	@Test
	public void testNoGameOver() {
		// a alapb�l be�ll�tott k�veket ellen�rz�m, hogy nyert-e valaki
		g.setPoints();	//ez a met�dush�v�s maga ut�n vonja a count,rule �s evaluate met�dusok megh�v�d�s�t is
		assertEquals( false ,g.isGameOver() );
		assertEquals( false ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//m�g nem nyert senki
	
	@Test
	public void testWhite5Win() { //ugy rakok le pontot hogy a feh�rnek 5�se van, majd ellen�rz�s
		g.getBoard().addPoint(230,140,Board.WHITE);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//v�ge, feh�r nyert
	
	@Test
	public void testWhiteOverlineWin() { //ugy rakok le pontokat hogy a feh�rnek overline-a van, majd ellen�rz�s
		g.getBoard().addPoint(230,140,Board.WHITE);
		g.getBoard().addPoint(230,80,Board.WHITE);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//v�ge, feh�r nyert
	
	@Test
	public void testBlackOverline() { //ugy rakok le pontokat hogy a feket�nek overline-a van, majd ellen�rz�s
		g.getBoard().addPoint(200,170,Board.BLACK);
		g.getBoard().addPoint(50,170,Board.BLACK);
		g.getBoard().addPoint(80,170,Board.BLACK);
		g.getBoard().addPoint(170,170,Board.BLACK);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//v�ge, feh�r nyert

	@Test
	public void testBlack5Win() { //ugy rakok le pontokat hogy a feket�nek 5�se van, majd ellen�rz�s
		g.getBoard().addPoint(50,170,Board.BLACK);
		g.getBoard().addPoint(80,170,Board.BLACK);
		g.getBoard().addPoint(170,170,Board.BLACK);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( false ,g.isWhiteWin() );
		assertEquals( true ,g.isBlackWin() );
	}//v�ge, fekete nyert
	
	@Test
	public void testBlackD3() { //ugy rakok le pontot hogy a feket�nek dupla3 van, majd ellen�rz�s
		g.getBoard().addPoint(170,170,Board.BLACK);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//v�ge, feh�r nyert
	
	@Test
	public void testBlackD4() { //ugy rakok le pontokat hogy a feket�nek dupla4 van, majd ellen�rz�s
		g.getBoard().addPoint(80,170,Board.BLACK);
		g.getBoard().addPoint(170,80,Board.BLACK);
		g.getBoard().addPoint(170,170,Board.BLACK);
		g.setPoints();
		
		assertEquals( true ,g.isGameOver() );
		assertEquals( true ,g.isWhiteWin() );
		assertEquals( false ,g.isBlackWin() );
	}//v�ge, feh�r nyert
	
	@Test
	public void testSaveLoad() { 
		g.setPoints();//ez r�gz�ti a pontokat a m�trixban is
		
		//kimentem g-t
		Save.SaveGame(g,"testsaving.txt");//bez�rza maga ut�n a f�jlt
		
		//a ment�sem bet�lt�m g2-be
		Game g2=Load.LoadGame("testsaving.txt");//bez�rza maga ut�n a f�jlt
		
		
		
		//objektum szinten nem siker�lt �sszehasonl�tani g-t �s g2-t, ez�rt jobb h�j�n megn�zem p�r v�ltoz�j�ra
		//a pontlista elso pontj�nak koordin�t�ja
		assertSame( g.getBoard().getpListItem(0).getX() ,g2.getBoard().getpListItem(0).getX() );
		//a m�trix p�r eleme
		assertSame( g.getBoard().getColor(0,0) ,g2.getBoard().getColor(0,0) );
		assertSame( g.getBoard().getColor(7,7) ,g2.getBoard().getColor(7,7) );
		assertSame( g.getBoard().getColor(5,4) ,g2.getBoard().getColor(5,4) );
		//p�r j�t�kv�ltoz�
		assertSame( g.getQ() ,g2.getQ() );
		assertSame( g.isGameOver() ,g2.isGameOver());
		assertSame( g.isWhiteWin() ,g2.isWhiteWin());

	}// ezek alapj�n �gy t�nik, hogy a kimentett �s a bet�lt�tt f�jl tartalma megegyezik
	

}
