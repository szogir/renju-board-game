package Core;


import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import gui.Window;




public class Game implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
    private boolean gameover = false;
    private boolean whiteWin = false;
    private boolean blackWin = false;
    private boolean egal = false;
    private boolean plotTwist = false;
    private Player p1;
    private Player p2;
    private int q;// egy sima szám amit növelek és a pásosságának a változását felhasználom a játékosváltásnál


    
    //ezek váltózók a szabályok érvényesítéhez szügséges számolásokhoz kellenek
    int hosszVszint;// vízszintesen összefüggõ sor hossza
    int hosszFleges;//függõlegesen
    int hosszSem;//srégan emelkedõ
    int hosszSle;//srégan lejtõ
    int dupla3esely; //hány irányban van 3 hosszú sora 
	int dupla4esely; //hány irányban van 4 hosszú sora

	
	// konstruktor
    public Game() {
        board = new Board();
        gameover = false;
        whiteWin = false;
        blackWin = false;
        egal = false;
        p1= new Player(Board.BLACK);
        p2= new Player(Board.WHITE);
        q=0;
        }
    
    
    
    //a nyitást megvalósító csúnya függvény
    public void startGame(int i,MouseEvent e) {
    	plotTwist=false;
    	//System.out.println(i);
    	if(i==1){//P1 feketét rak (1.lépés)
    		getBoard().addPoint(e.getX(), e.getY(),getPlayer1().getColor());
			Window.setLPlayerText("P1's turn");		//kiírja ki jön
			Window.setLTurnsText("White's turn");	//kiírja ki jön
			
    	}else if(i==2) {//P1 fehéret rak (2.lépés)
			getBoard().addPoint(e.getX(), e.getY(),getPlayer2().getColor());
			Window.setLPlayerText("P1's turn");
			Window.setLTurnsText("Black's turn");
			
    	}else if(i==3) { //P1 feketét rak (3.lépés)
    		getBoard().addPoint(e.getX(), e.getY(),getPlayer1().getColor());
			Window.setLPlayerText("P2's turn");
			Window.setLTurnsText(" ");
			
			//felugróablak: fehér akar-e cserélni?
			JOptionPane opt = new JOptionPane("Player2 (White), do you want to plottwist?",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION); 
					JDialog jd = opt.createDialog( "PlotTwist Question");
					jd.setVisible(true);
					//System.out.println(opt.getValue());
					
				if( (int) opt.getValue()==0) {		// ha legyen plottwist (csere)
					System.out.println("PlotTwist!!!");
					plotTwist=true;
					p1.setColor(Board.WHITE);
					p2.setColor(Board.BLACK);
					Window.setLPlayerText("P1's turn");
					Window.setLTurnsText("White's turn ");
					
				}else {								//ha ne legyen plottwist (csere)
					plotTwist=false;
					p1.setColor(Board.BLACK);
					p2.setColor(Board.WHITE);
					Window.setLPlayerText("P2's turn");
					Window.setLTurnsText("White's turn ");
				}
					
    		
    	}else if(i==4) {//fehéret rak (4.lépés)
	    		getBoard().addPoint(e.getX(), e.getY(),Board.WHITE);
	    		if(plotTwist==true) {	
	    			Window.setLPlayerText("P2's turn");
	    			Window.setLTurnsText("Black's turn");
	    		}else if(plotTwist==false) {
		    			Window.setLPlayerText("P1's turn");
		    			Window.setLTurnsText("Black's turn");
		  		} 
    			
    	}else if(i==5) {// felajánl egy feketét(piros)
    		getBoard().addPoint(e.getX(), e.getY(),3);
    		Window.setLTurnsText("White's turn");
    		
    		
    	}else if(i==6) {// felajánl még egy feketét (piros) //felugróablak, majd törli a tetszõlegesset, a másik feketévé válik
    		getBoard().addPoint(e.getX(), e.getY(),3); // a 3 a feketétõl és fehértõl is eltérõ színt jelképezi, majd a Boardpanel drawPoints metódusa ezért rajzolja pirosra
    		Window.setLTurnsText("White's turn");
    		
    		//felugró ablak: melyiket a törölje?
    		Object[] options = {"First one","Second one"};
			int n = JOptionPane.showOptionDialog(null,"Which one would you like to remove?","Which one",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]); 
			//System.out.println(n);
			
			if(n==0) {//ez elsõt törli és a második marad
				getBoard().deletePoint(getBoard().getpListSize()-1);//törlés
				
				// a megmaradó piros pontot feketévé teszem
				getBoard().addPoint(getBoard().getpListItem(getBoard().getpListSize()-1).getX(), getBoard().getpListItem(getBoard().getpListSize()-1).getY(),Board.BLACK);
				
				if(plotTwist==false) {Window.setLPlayerText("P2's turn");}else {Window.setLPlayerText("P1's turn");}
    			Window.setLTurnsText("White's turn");

				
			}else {//a másodikat törli és az elsõ marad
				getBoard().deletePoint(getBoard().getpListSize());//törlés
				
				//a megmaradó piros pontot feketévé teszem
				getBoard().addPoint(getBoard().getpListItem(getBoard().getpListSize()-1).getX(), getBoard().getpListItem(getBoard().getpListSize()-1).getY(),Board.BLACK);
				
				
				if(plotTwist==false) {Window.setLPlayerText("P2's turn");}else {Window.setLPlayerText("P1's turn");}
    			Window.setLTurnsText("White's turn");

			}

    	}else if(i==7) {//fehérrel rak (amúgy 6.lépés)
    		
    		getBoard().addPoint(e.getX(), e.getY(),Board.WHITE);
    		Window.setLTurnsText("Black's turn");
    		if(plotTwist==false) {Window.setLPlayerText("P1's turn");}else {Window.setLPlayerText("P2's turn");}
    		if(plotTwist==false) {
    			p1.setColor(Board.WHITE);
    			p2.setColor(Board.BLACK);
    			}
    	}	
   } 
    	
    
    
    
    
    
    
    
    
    public void setPoints() {
    	//végigmegy a pontlistán és beírja mátrixba a pontokat
    	//a mátrixkoordinátákat a coordNormalize metódussal számolja a pontokból
    	//majd jön egy számolás(count,rule) és vizsgálat(evaluate) hogy nyert e valaki (a dupla3, dupla4 és overline kényszereket is figyelembe veszi)

		for (int i = 0; i < board.getpListSize(); i++) { 
					board.setColor( coordNormalize( board.getpListItem(i).getX()) , coordNormalize( board.getpListItem(i).getY() ), board.getpListItem(i).getColor());// mátrixban beáálitja színt
					
					//összefüggõ sorok hosszának számítása
					count(coordNormalize( board.getpListItem(i).getX()) , coordNormalize( board.getpListItem(i).getY() ), board.getpListItem(i).getColor());
					// a dupla3 és dupla4hez szüks. számolás
					rule();
					//kiértékelés
					evaluate(board.getpListItem(i).getColor());

		}
		
	}

    //*****************************************************************************************************	

   
//ez a metódus az egyes pontokból "szétszalad" a sík 8 irányába és megszámolja milyen hosszú megszakítatlan sorok vannak
   public void count(int x, int y, int color) {

        int i;
        
        //az egyes irányokba az összefüggõ hosszokat tartalmazó változók inicializálása
	    hosszVszint=1;
	    hosszFleges=1;
	    hosszSem=1;
	    hosszSle=1;
	    
	    //"menj tovább" logikai változók a 8 irányra: akkor igaz, ha a vizsgált irányban lévõ szomszéd is azonos színû
	    boolean mtjobb=true, mtbal=true, mtle=true ,mtfel=true ,mtjobbfel=true ,mtjobble=true ,mtbalfel=true ,mtballe=true ;
		
	    int vizsgalt;
	    for (i = 1; i < 5; i++) {//öt messze nézünk szét
		
	    	//***EZT A SÍK MIND A 8 IRÁNYÁRA MEGCSINÁLNI*** (fel, le jobbra, balra,és diagonálisan is 4 irányba)
	    	
			    	//jobbra megyünk (max 5-öt)
			    	 if (x + i < board.getDim()) {//ha nem ütközünk falba
			             vizsgalt = board.getColor( x + i, y);// megkérdezi a pont színét (ez lépked jobbra)
			             if(mtjobb) {  //ha a menjtovább igaz 
			            	 if(vizsgalt==color) {//és a jobbra levõ pont színe megegyezik a paraméterül kapottal
			            		 hosszVszint++; //növeli az irányban a hosszt
			            		 mtjobb=true;	 //és menjtovább legyen igaz
			            	 }else if(vizsgalt==Board.EMPTY) {	//ha jobbra ures pont van 
			            		 mtjobb=false; //menjtovább legyen hamis
			            	 }else {  	//ha jobb ellenség van
			            		 mtjobb=false;  // menjtovább legyen hamis
			            	 }
			      	 
					   }
			    	}// a többi irányba szintén így
 
			    	 
			    	//balra megyünk (max 5-öt)
			        if (x - i >= 0) {//ha nem ütközünk a falba
			        	vizsgalt = board.getColor( x - i, y);// megkérdeni a balra levõ pont színét
			        	  if(mtbal) { 
				            	 if(vizsgalt==color) {
				            		 hosszVszint++;
				            		 mtbal=true;	 
				            	 }else if(vizsgalt==Board.EMPTY) {	 
				            		 mtbal=false;
				            	 }else {
				            		 mtbal=false; 
				            	 }
						   } 
			        } 
			        
			      //fel megyünk (max 5-öt)
		          if (y + i < board.getDim()) {//ha nem ütközünk a falba
		        	  vizsgalt = board.getColor( x , y+i);
		        	  if(mtfel) { 
			            	 if(vizsgalt==color) {
			            		 hosszFleges++;
			            		 mtfel=true;	 
			            	 }else if(vizsgalt==Board.EMPTY) {	 
			            		 mtfel=false;
			            	 }else {
			            		 mtfel=false; 
			            	 }
					   } 
		          }
		          
		            //le megyünk (max 5-öt)
		            if (y - i >= 0) {//ha nem ütközünk a falba
		            	vizsgalt = board.getColor( x , y-i);
		            	if(mtle) { 
			            	 if(vizsgalt==color) {
			            		 hosszFleges++;
			            		 mtle=true;	 
			        
			            	 }else if(vizsgalt==Board.EMPTY) {	 
			            		 mtle=false;
			            	 }else {
			            		 mtle=false; 
			            	 }
					   } 
		            }
		            
		          //jobbrafel megyünk (max 5-öt)
		            if (x + i < board.getDim() && y + i < board.getDim()) { //ha nem ütközünk a falba
		            	vizsgalt = board.getColor( x+i , y+i);
		            	if(mtjobbfel) { 
			            	 if(vizsgalt==color) {
			            		 hosszSem++;
			            		 mtjobbfel=true;	 
			            	 }else if(vizsgalt==Board.EMPTY) {	 
			            		 mtjobbfel=false;
			            	 }else {
			            		 mtjobbfel=false; 
			            	 }
					   } 
		            }
		            
		          //jobbrale megyünk (max 5-öt)
		             if (x + i < board.getDim() && y - i >= 0) { //ha nem ütközünk a falba
		            	 vizsgalt = board.getColor( x+i , y-i);
		            	 if(mtjobble) { 
			            	 if(vizsgalt==color) {
			            		 hosszSle++;
			            		 mtjobble=true;	 
			            	 }else if(vizsgalt==Board.EMPTY) {	 
			            		 mtjobble=false;
			            	 }else {
			            		 mtjobble=false; 
			            	 }
		            	 }
		             }
		            	 
		            	//balrafel megyünk (max 5-öt)
		                    if (x - i >= 0 && y + i < board.getDim()) { //ha nem ütközünk a falba
		                    	vizsgalt = board.getColor( x-i , y+i);	
		                    	if(mtbalfel) { 
					            	 if(vizsgalt==color) {
					            		 hosszSle++;
					            		 mtbalfel=true;	 
					            	 }else if(vizsgalt==Board.EMPTY) {	 
					            		 mtbalfel=false;
					            	 }else {
					            		 mtbalfel=false; 
					            	 }
		                    	}
		                    }
		                    	
		                   //balrale megyünk (max 5-öt)
		                          if (x - i >= 0 && y - i >= 0) { //ha nem ütközünk a falba
		                        	  vizsgalt = board.getColor( x-i , y-i);	
		                        	  if(mtballe) { 
							            	 if(vizsgalt==color) {
							            		 hosszSem++;
							            		 mtballe=true;	 
							            	 }else if(vizsgalt==Board.EMPTY) {	 
							            		 mtballe=false; 
							            	 }else {
							            		 mtballe=false; 
							            	 }
		                        	  }
		                          }
	    					
	    }//ez itt a ciklus vége
   }//count vége
   
   public void rule() {
			
	    //ha a játékosnak legalább 2 irányban is 3 vagy 4 hosszú sora van akkor lehet szó dupla3ról vagy dupla4rõl
	   
			dupla3esely=0; //hány különbözõ irányban van 3 hosszú sora 
			dupla4esely=0; //hány különbözõ irányban van 4 hosszú sora
			
			if (hosszFleges == 3) 
				dupla3esely++;
			
		    if (hosszVszint == 3) 
		    	dupla3esely++;
		    
		    if (hosszSem == 3) 
		    	dupla3esely++;
		    
		    if (hosszSle == 3) 
		    	dupla3esely++;
		    
		    if (hosszFleges == 4)
				dupla4esely++;
		    
		    if (hosszVszint == 4)  
		    	dupla4esely++;
		    
		    if (hosszSem == 4) 
		    	dupla4esely++;
		    
		    if (hosszSle == 4) 
		    	dupla4esely++;
		    
   }
   
   
   
   //a szín, az összefüggõ sorhosszok, a dupla3esely és dupla4esely alapján megállapítjuk nyert-e valaki
   public void evaluate(int color){
	   
		    // ha valamelyik irányban kijött az összefüggõ pontosan 5 hosszú sor
		     if(hosszVszint==5 || hosszFleges==5 || hosszSem==5 || hosszSle==5){
		    	 if(color==Board.WHITE){	
		    		gameover=true;
		    	 	whiteWin=true;
		    	 	//System.out.println("fehér nyert");
		    	 	Window.setLTurnsText("White won");
		    	 	Window.setLPlayerText("White won");
		    	 	
		    	 }else if(color==Board.BLACK) {
		    		gameover=true;
		    		blackWin=true;
		    		//System.out.println("fekete nyert");
		    		Window.setLTurnsText("Black won");
		    		Window.setLPlayerText("Black won");
		    	 }
		     }
		     
		     
		  // ha valamelyik irányban 5-nél hosszabb összefüggõ sor jön ki
		     if(hosszVszint > 5 || hosszFleges > 5 || hosszSem > 5 || hosszSle > 5){
		    	 if(color==Board.WHITE){	
			    		gameover=true;
			    	 	whiteWin=true;
			    	 	//System.out.println("fehér nyert");
			    	 	Window.setLTurnsText("White won");
			    	 	Window.setLPlayerText("White won");

			    	 }else if(color==Board.BLACK) {//fekete overline esete
			    		gameover=true;
			    		whiteWin=true;
			    		//System.out.println("fehér nyert");
			    		Window.setLTurnsText("White won");
			    		Window.setLPlayerText("White won");
			    	 } 
		     }
		     
		     // ha a feketének dupla3masa vagy dupla4ese van (legalább a sík 2 irányában van hármasa vagy négyese)
		     if ((color==Board.BLACK) && (dupla3esely >= 2 || dupla4esely >= 2 )) {
		            gameover = true;
		            whiteWin = true;
		            //System.out.println("fehér nyert");
		            Window.setLTurnsText("White won");
		            Window.setLPlayerText("White won");
		        }
		     
		     //ha megtelt a pálya
		     if(board.getpListSize() >= 225){
		            gameover = true;
		            egal=true;
		            //System.out.println("döntetlen");
		            Window.setLTurnsText("Egal");
		            Window.setLPlayerText("Egal");
		        } 
}  
    //****************************************************************************************************
  
    //getterek, setterek
 	
    public Board getBoard() {
        return board;
    }
    
    
    public boolean isGameOver() {
        return gameover;
    }
    
   
    public boolean isBlackWin() {
        return blackWin;
    }
    
    public boolean isWhiteWin() {
        return whiteWin;
    }
    
    public boolean isEgal() {
        return egal;
    }
    
    public void setBoard(Board b) {
        this.board = b;
    }
    
    public void setGameOver(boolean a) {
        this.gameover = a;
    }

    public void setBlackWin(boolean a) {
        this.blackWin = a;
    }

    public void setWhiteWin(boolean a) {
        this.whiteWin = a;
    }
    
    public void setEgal(boolean a) {
        this.egal = a;
    }
    
    public Player getPlayer1() {
		   return this.p1;
	 }
		
    public Player getPlayer2() {
		   return this.p2;
	 }
	   
	 public void setPlayer1(Player p) {
		   this.p1=p;
	 }

	 public void setPlayer2(Player p) {
		   this.p2=p;
	 }

    public boolean getplotTwist() {
    	return plotTwist;
    }

    public void setQ(int a) {
    	q=a;
    }
    public int getQ() {
    	return q;
    }
    public void incQ() {
    	q++;
    }

	//a koordinátákat átalakítja értelmes alakba a MÁTRIXHOZ
    //ez a kevés itt a mágia része
	public int coordNormalize (int n) {
		int z;
		if ((n - 20)% 30 >= 15) {
			z = (n - 20) / 30 + 1;
		} else {
			z = (n - 20) / 30;
		}
		return z;
	}
	

	// a koordinátákat átalakitja értelmes alakba a PONTKIRAJZOLÁSHOZ
	//még egy pici mágia
	public int coordNormalizeDraw (int n) {
		return coordNormalize(n)*30+20;
	}
	


		

 
}
