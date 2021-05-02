package gui;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import Core.Board;
import Core.ColorPoint;
import Core.Load;
import Core.Save;


//frame oszt�ly
public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel mainPanel =new JPanel();	//balr�l a Boardpanelt, jobb�l a rightPanelt tartalmazza
	BoardPanel boardPanel =new BoardPanel();// p�lya
	JPanel rightPanel=new JPanel(); // gombokat �s jlabelt tartalmaz
	
	static JLabel LTurns; //ki�rja ki j�n melyik sz�n
	static JLabel LPlayer;// melyik j�t�kos j�n

	JButton BStart=new JButton("Start"); //restartolja a j�t�kot
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
	private class StartButtonActionListener implements ActionListener {//restartol�s
    	public void actionPerformed(ActionEvent ae) {
   		 //System.out.println("StartButton");
    	//	boardPanel=new BoardPanel();
    		
    	//valami�rt konstruktorral nem siker�lt megcsin�lni
   		//ez�rt k�nytelen voltam megcsin�lni k�zzel a fusimunk�t
    	// �gy ugyanazok az objektump�ld�nyok maradtak, de  a v�ltoz�k, az arraylist �s a m�trix vissza�ll az eredeti �llapotba
    		//v�ltoz�k vissza�ll�t�sa
    		 boardPanel.getGame().setGameOver(false);
    		 boardPanel.getGame().setBlackWin(false);
    		 boardPanel.getGame().setWhiteWin(false);
    		 boardPanel.getGame().setEgal(false);
    		 boardPanel.getGame().getPlayer1().setColor(Board.BLACK);
    		 boardPanel.getGame().getPlayer2().setColor(Board.WHITE);
    		 boardPanel.setQ(0);
    		 

    		 //M�trix vissza�ll�t�sa az eredeti �llapotra
    		 int [][] newMatrix = new int[ boardPanel.getGame().getBoard().getDim() ][ boardPanel.getGame().getBoard().getDim()];
    		 for(int i=0; i<boardPanel.getGame().getBoard().getDim(); i++)
    	        	for(int j=0; j<boardPanel.getGame().getBoard().getDim(); j++)
    	        		boardPanel.getGame().getBoard().setColor(i, j,Board.EMPTY);
    		 boardPanel.getGame().getBoard().setMatrix(newMatrix);
    		 
    		 //ArrayList null�z�sa
    		 ArrayList<ColorPoint> newpList= new ArrayList<>();
    		 boardPanel.getGame().getBoard().setpList(newpList);  
 
    		 repaint();
   		
    	}
    }
	
		//megnyit egy jchooser ablakot, �s az abban megadott helyre �s n�vvel elmenti
		//txt-vel es dat-tal pr�b�ltam ki
		private class SaveActionListener implements ActionListener {
		    	public void actionPerformed(ActionEvent ae) {
//		    		 System.out.println("Save");
//		    		 Save.SaveGame(boardPanel.game,"data.txt");
		    		 
		    		//csak a nyit�s v�gezte ut�n m�k�dik
		    		if(boardPanel.getQ()>7) {		 
		    		 JFileChooser fileChooser = new JFileChooser();  
		    		 int userSelection = fileChooser.showSaveDialog(null);    		  
		    		 if (userSelection == JFileChooser.APPROVE_OPTION) {
		    		    Save.SaveGame(boardPanel.getGame(),fileChooser.getSelectedFile().getName());
		    		 }
		    		}else {
		    			//System.out.println("A szekvencia k�zben a ment�s �s bet�lt�s nem lehets�ges");
		    			JOptionPane.showMessageDialog(null,"Saving is not possible during the sequence");
		    			}
		    	}
	    }
		
		
		//megnyit egy chooser ablakot, �s az ott kiv�lasztott f�jlt megpr�b�lja bet�lteni
		//txt-vel es dat-tal pr�b�ltam ki	
		private class LoadActionListener implements ActionListener {
		    	public void actionPerformed(ActionEvent ae) {
//		    		 System.out.println("Load");		    		
//		    		 boardPanel.game=Load.LoadGame("data.txt");
//		    		 repaint();
		   		 		
		    		// csak ha m�g nem kezdt�k a j�t�kot, vagy ha t�lvagyunk a nyit�son, akkor m�k�dik
		    		if(boardPanel.getQ()>7 || boardPanel.getQ()==0) { 
		    		 JFileChooser fileChooser = new JFileChooser();
		    	      int returnValue = fileChooser.showOpenDialog(null);
		    	      if (returnValue == JFileChooser.APPROVE_OPTION) {
		    	    	  boardPanel.setGame(Load.LoadGame(fileChooser.getSelectedFile().getName()));
		    	      }
		    	      repaint();   	      
		    	
		    		}else {
		    			//System.out.println("A szekvencia k�zben a ment�s �s bet�lt�s nem lehets�ges");
		    			JOptionPane.showMessageDialog(null,"Loading is not possible during the sequence");
		    			}		    	
		    		}	
		}
}
