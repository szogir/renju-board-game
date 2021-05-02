package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import Core.Board;
import Core.Game;

// a pálya és pontok Megjelenítésért felelős osztály
public class BoardPanel extends JPanel {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Game game;

		
		//konstruktor
		public BoardPanel() {
			game=new Game();
			this.addMouseListener(new MListener());
	
		}
		
		//Listener!!!!!	kattintás
		class MListener extends MouseAdapter {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					
					
					//csak akkor tud lepni (a pointlistához hozzáadni), ha ures pontra kattint és még nincs vége a játéknak
					 if(game.getBoard().getColor( game.coordNormalize(e.getX()) , game.coordNormalize(e.getY()) ) ==Board.EMPTY && !game.isGameOver()) {
							
						 if(game.getQ()<7) {//nyitáshoz
								game.startGame(game.getQ()+1,e);
								
							//rendes játékhoz	
							}else if(game.getQ()%2==0) {
								game.getBoard().addPoint(e.getX(), e.getY(),game.getPlayer1().getColor());
								if(game.getplotTwist()==false) {Window.setLPlayerText("P1's turn");}else {Window.setLPlayerText("P2's turn");}//nyitás miatt
							}else {
								game.getBoard().addPoint(e.getX(), e.getY(),game.getPlayer2().getColor());
								if(game.getplotTwist()==false) {Window.setLPlayerText("P2's turn");}else {Window.setLPlayerText("P1's turn");}//nyitás miatt
							}
						game.incQ();;
						repaint();
					}
				}
			}
	
	
		@Override
		public void paint(Graphics g) {
			super.paint(g);

				drawGrid(g);   //kirajzoltatja a rácsot
				drawPoints(g); //kirajzoltatja a pontokat
				game.setPoints(); // a mátrixban is beállittatja

		}
	
		
		// kirajzolja pontlista elemeit
		private void drawPoints(Graphics g) {
			for (int i = 0; i < game.getBoard().getpListSize(); i++) {//végigmegy a pontlistán és kirajzolja

				// kirajzolja a pontokat a szinuknek megfeleloen
				//a pontos kirajzolási koordinátákat a coordNormalizeDraw metódussal számolja a pontokból
					if (game.getBoard().getpListItem(i).getColor()==Board.BLACK) {
						g.setColor(Color.BLACK);
						//kirajzolja a pontot, 20 széles és magas, a "-10" azért kell hogy pontosan jo helyre tegye
						g.fillOval(game.coordNormalizeDraw( game.getBoard().getpListItem(i).getX()) - 10, game.coordNormalizeDraw( game.getBoard().getpListItem(i).getY()) - 10, 20, 20); 
						Window.setLTurnsText("White's turn");
					} else if (game.getBoard().getpListItem(i).getColor()==Board.WHITE) {
						g.setColor(Color.WHITE);
						//kirajzolja a pontot, 20 széles és magas, a 13 azért kell hogy pontosan jo helyre tegye
						g.fillOval( game.coordNormalizeDraw( game.getBoard().getpListItem(i).getX()) - 10, game.coordNormalizeDraw( game.getBoard().getpListItem(i).getY()) - 10, 20, 20);  //kirajzolja a pontot
						Window.setLTurnsText("Black's turn");
					}else {
						g.setColor(Color.RED);// a nyitásban a választóshoz találtam ezt
						g.fillOval( game.coordNormalizeDraw( game.getBoard().getpListItem(i).getX()) - 10, game.coordNormalizeDraw( game.getBoard().getpListItem(i).getY()) - 10, 20, 20);  //kirajzolja a pontot
						
					}
			}
		
		}
	

		// kirajzoltatja a négyzetrácsot: a 20. képponttól kezdve 30 képpontonként vonalat, összesen 15-öt mindkét irányba (440ig)
		private void drawGrid(Graphics g) {
			g.setColor(Color.BLACK);
			for (int i = 20; i <= 440; i += 30) {
				g.drawLine(20, i, 440, i); //ez húzza a vízzinteseket
				g.drawLine(i, 20, i, 440); //ez húzza a függőlegeseket
			}
		}

	//setterek, getterek
	public void setQ(int a){
		game.setQ(a);
	}
	
	public int	getQ() {
		return game.getQ();
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game a) {
		game=a;
	}
}