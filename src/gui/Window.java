package gui;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import Core.Board;
import Core.ColorPoint;
import Core.Load;
import Core.Save;


//frame osztály
public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel mainPanel =new JPanel();	//balról a Boardpanelt, jobból a rightPanelt tartalmazza
	BoardPanel boardPanel =new BoardPanel();// pálya
	JPanel rightPanel=new JPanel(); // gombokat és jlabelt tartalmaz
	
	static JLabel LTurns; //kiírja ki jön melyik szín
	static JLabel LPlayer;// melyik játékos jön

	JButton BStart=new JButton("Start"); //restartolja a játékot
	JButton BLoad=new JButton("Load");
	JButton BSave=new JButton("Save"); 
	
	JMenuBar menubar = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem load = new JMenuItem("Load");


	public Window() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Rendzsu");

		BStart.addActionListener(new StartButtonActionListener());
		BSave.addActionListener(new SaveActionListener());
		BLoad.addActionListener(new LoadActionListener());
		save.addActionListener(new SaveActionListener());
		load.addActionListener(new LoadActionListener());
		
		menu.add(save);
		menu.add(load);
		menubar.add(menu);
		this.setJMenuBar(menubar);
		
		LTurns= new JLabel("Let's play!");
		LPlayer= new JLabel("Let's play!");
		
		this.add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.add(boardPanel);
		mainPanel.add(rightPanel);
		
		boardPanel.setSize(600, 600);
		rightPanel.setSize(50, 600);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(BStart);
		rightPanel.add(BLoad);
		rightPanel.add(BSave);
		rightPanel.add(LTurns);
		rightPanel.add(LPlayer);
      
		this.pack();
		this.setSize(650, 600);
		this.setVisible(true);
	}
	
	
	public static void setLTurnsText(String s) {
		LTurns.setText(s);
	}

	public static void setLPlayerText(String s) {
		LPlayer.setText(s);
	}
	
	//listenerek!!!!!
	private class StartButtonActionListener implements ActionListener {//restartolás
    	public void actionPerformed(ActionEvent ae) {
   		 //System.out.println("StartButton");
    	//	boardPanel=new BoardPanel();
    		
    	//valamiért konstruktorral nem sikerült megcsinálni
   		//ezért kénytelen voltam megcsinálni kézzel a fusimunkát
    	// így ugyanazok az objektumpéldányok maradtak, de  a változók, az arraylist és a mátrix visszaáll az eredeti állapotba
    		//változók visszaállítása
    		 boardPanel.getGame().setGameOver(false);
    		 boardPanel.getGame().setBlackWin(false);
    		 boardPanel.getGame().setWhiteWin(false);
    		 boardPanel.getGame().setEgal(false);
    		 boardPanel.getGame().getPlayer1().setColor(Board.BLACK);
    		 boardPanel.getGame().getPlayer2().setColor(Board.WHITE);
    		 boardPanel.setQ(0);
    		 

    		 //Mátrix visszaállítása az eredeti állapotra
    		 int [][] newMatrix = new int[ boardPanel.getGame().getBoard().getDim() ][ boardPanel.getGame().getBoard().getDim()];
    		 for(int i=0; i<boardPanel.getGame().getBoard().getDim(); i++)
    	        	for(int j=0; j<boardPanel.getGame().getBoard().getDim(); j++)
    	        		boardPanel.getGame().getBoard().setColor(i, j,Board.EMPTY);
    		 boardPanel.getGame().getBoard().setMatrix(newMatrix);
    		 
    		 //ArrayList nullázása
    		 ArrayList<ColorPoint> newpList= new ArrayList<>();
    		 boardPanel.getGame().getBoard().setpList(newpList);  
 
    		 repaint();
   		
    	}
    }
	
		//megnyit egy jchooser ablakot, és az abban megadott helyre és névvel elmenti
		//txt-vel es dat-tal próbáltam ki
		private class SaveActionListener implements ActionListener {
		    	public void actionPerformed(ActionEvent ae) {
//		    		 System.out.println("Save");
//		    		 Save.SaveGame(boardPanel.game,"data.txt");
		    		 
		    		//csak a nyitás végezte után mûködik
		    		if(boardPanel.getQ()>7) {		 
		    		 JFileChooser fileChooser = new JFileChooser();  
		    		 int userSelection = fileChooser.showSaveDialog(null);    		  
		    		 if (userSelection == JFileChooser.APPROVE_OPTION) {
		    		    Save.SaveGame(boardPanel.getGame(),fileChooser.getSelectedFile().getName());
		    		 }
		    		}else {
		    			//System.out.println("A szekvencia közben a mentés és betöltés nem lehetséges");
		    			JOptionPane.showMessageDialog(null,"Saving is not possible during the sequence");
		    			}
		    	}
	    }
		
		
		//megnyit egy chooser ablakot, és az ott kiválasztott fájlt megpróbálja betölteni
		//txt-vel es dat-tal próbáltam ki	
		private class LoadActionListener implements ActionListener {
		    	public void actionPerformed(ActionEvent ae) {
//		    		 System.out.println("Load");		    		
//		    		 boardPanel.game=Load.LoadGame("data.txt");
//		    		 repaint();
		   		 		
		    		// csak ha még nem kezdtük a játékot, vagy ha túlvagyunk a nyitáson, akkor mûködik
		    		if(boardPanel.getQ()>7 || boardPanel.getQ()==0) { 
		    		 JFileChooser fileChooser = new JFileChooser();
		    	      int returnValue = fileChooser.showOpenDialog(null);
		    	      if (returnValue == JFileChooser.APPROVE_OPTION) {
		    	    	  boardPanel.setGame(Load.LoadGame(fileChooser.getSelectedFile().getName()));
		    	      }
		    	      repaint();   	      
		    	
		    		}else {
		    			//System.out.println("A szekvencia közben a mentés és betöltés nem lehetséges");
		    			JOptionPane.showMessageDialog(null,"Loading is not possible during the sequence");
		    			}		    	
		    		}	
		}
}
