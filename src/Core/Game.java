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
    private int q;// egy sima sz�m amit n�velek �s a p�soss�g�nak a v�ltoz�s�t felhaszn�lom a j�t�kosv�lt�sn�l


    
    //ezek v�lt�z�k a szab�lyok �rv�nyes�t�hez sz�gs�ges sz�mol�sokhoz kellenek
    int hosszVszint;// v�zszintesen �sszef�gg� sor hossza
    int hosszFleges;//f�gg�legesen
    int hosszSem;//sr�gan emelked�
    int hosszSle;//sr�gan lejt�
    int dupla3esely; //h�ny ir�nyban van 3 hossz� sora 
	int dupla4esely; //h�ny ir�nyban van 4 hossz� sora

	
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
    
    
    
    //a nyit�st megval�s�t� cs�nya f�ggv�ny
    public void startGame(int i,MouseEvent e) {
    	plotTwist=false;
    	//System.out.println(i);
    	if(i==1){//P1 feket�t rak (1.l�p�s)
    		getBoard().addPoint(e.getX(), e.getY(),getPlayer1().getColor());
			Window.setLPlayerText("P1's turn");		//ki�rja ki j�n
			Window.setLTurnsText("White's turn");	//ki�rja ki j�n
			
    	}else if(i==2) {//P1 feh�ret rak (2.l�p�s)
			getBoard().addPoint(e.getX(), e.getY(),getPlayer2().getColor());
			Window.setLPlayerText("P1's turn");
			Window.setLTurnsText("Black's turn");
			
    	}else if(i==3) { //P1 feket�t rak (3.l�p�s)
    		getBoard().addPoint(e.getX(), e.getY(),getPlayer1().getColor());
			Window.setLPlayerText("P2's turn");
			Window.setLTurnsText(" ");
			
			//felugr�ablak: feh�r akar-e cser�lni?
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
					
    		
    	}else if(i==4) {//feh�ret rak (4.l�p�s)
	    		getBoard().addPoint(e.getX(), e.getY(),Board.WHITE);
	    		if(plotTwist==true) {	
	    			Window.setLPlayerText("P2's turn");
	    			Window.setLTurnsText("Black's turn");
	    		}else if(plotTwist==false) {
		    			Window.setLPlayerText("P1's turn");
		    			Window.setLTurnsText("Black's turn");
		  		} 
    			
    	}else if(i==5) {// felaj�nl egy feket�t(piros)
    		getBoard().addPoint(e.getX(), e.getY(),3);
    		Window.setLTurnsText("White's turn");
    		
    		
    	}else if(i==6) {// felaj�nl m�g egy feket�t (piros) //felugr�ablak, majd t�rli a tetsz�legesset, a m�sik feket�v� v�lik
    		getBoard().addPoint(e.getX(), e.getY(),3); // a 3 a feket�t�l �s feh�rt�l is elt�r� sz�nt jelk�pezi, majd a Boardpanel drawPoints met�dusa ez�rt rajzolja pirosra
    		Window.setLTurnsText("White's turn");
    		
    		//felugr� ablak: melyiket a t�r�lje?
    		Object[] options = {"First one","Second one"};
			int n = JOptionPane.showOptionDialog(null,"Which one would you like to remove?","Which one",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]); 
			//System.out.println(n);
			
			if(n==0) {//ez els�t t�rli �s a m�sodik marad
				getBoard().deletePoint(getBoard().getpListSize()-1);//t�rl�s
				
				// a megmarad� piros pontot feket�v� teszem
				getBoard().addPoint(getBoard().getpListItem(getBoard().getpListSize()-1).getX(), getBoard().getpListItem(getBoard().getpListSize()-1).getY(),Board.BLACK);
				
				if(plotTwist==false) {Window.setLPlayerText("P2's turn");}else {Window.setLPlayerText("P1's turn");}
    			Window.setLTurnsText("White's turn");

				
			}else {//a m�sodikat t�rli �s az els� marad
				getBoard().deletePoint(getBoard().getpListSize());//t�rl�s
				
				//a megmarad� piros pontot feket�v� teszem
				getBoard().addPoint(getBoard().getpListItem(getBoard().getpListSize()-1).getX(), getBoard().getpListItem(getBoard().getpListSize()-1).getY(),Board.BLACK);
				
				
				if(plotTwist==false) {Window.setLPlayerText("P2's turn");}else {Window.setLPlayerText("P1's turn");}
    			Window.setLTurnsText("White's turn");

			}

    	}else if(i==7) {//feh�rrel rak (am�gy 6.l�p�s)
    		
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
    	//v�gigmegy a pontlist�n �s be�rja m�trixba a pontokat
    	//a m�trixkoordin�t�kat a coordNormalize met�dussal sz�molja a pontokb�l
    	//majd j�n egy sz�mol�s(count,rule) �s vizsg�lat(evaluate) hogy nyert e valaki (a dupla3, dupla4 �s overline k�nyszereket is figyelembe veszi)

		for (int i = 0; i < board.getpListSize(); i++) { 
					board.setColor( coordNormalize( board.getpListItem(i).getX()) , coordNormalize( board.getpListItem(i).getY() ), board.getpListItem(i).getColor());// m�trixban be��litja sz�nt
					
					//�sszef�gg� sorok hossz�nak sz�m�t�sa
					count(coordNormalize( board.getpListItem(i).getX()) , coordNormalize( board.getpListItem(i).getY() ), board.getpListItem(i).getColor());
					// a dupla3 �s dupla4hez sz�ks. sz�mol�s
					rule();
					//ki�rt�kel�s
					evaluate(board.getpListItem(i).getColor());

		}
		
	}

    //*****************************************************************************************************	

   
//ez a met�dus az egyes pontokb�l "sz�tszalad" a s�k 8 ir�ny�ba �s megsz�molja milyen hossz� megszak�tatlan sorok vannak
   public void count(int x, int y, int color) {

        int i;
        
        //az egyes ir�nyokba az �sszef�gg� hosszokat tartalmaz� v�ltoz�k inicializ�l�sa
	    hosszVszint=1;
	    hosszFleges=1;
	    hosszSem=1;
	    hosszSle=1;
	    
	    //"menj tov�bb" logikai v�ltoz�k a 8 ir�nyra: akkor igaz, ha a vizsg�lt ir�nyban l�v� szomsz�d is azonos sz�n�
	    boolean mtjobb=true, mtbal=true, mtle=true ,mtfel=true ,mtjobbfel=true ,mtjobble=true ,mtbalfel=true ,mtballe=true ;
		
	    int vizsgalt;
	    for (i = 1; i < 5; i++) {//�t messze n�z�nk sz�t
		
	    	//***EZT A S�K MIND A 8 IR�NY�RA MEGCSIN�LNI*** (fel, le jobbra, balra,�s diagon�lisan is 4 ir�nyba)
	    	
			    	//jobbra megy�nk (max 5-�t)
			    	 if (x + i < board.getDim()) {//ha nem �tk�z�nk falba
			             vizsgalt = board.getColor( x + i, y);// megk�rdezi a pont sz�n�t (ez l�pked jobbra)
			             if(mtjobb) {  //ha a menjtov�bb igaz 
			            	 if(vizsgalt==color) {//�s a jobbra lev� pont sz�ne megegyezik a param�ter�l kapottal
			            		 hosszVszint++; //n�veli az ir�nyban a hosszt
			            		 mtjobb=true;	 //�s menjtov�bb legyen igaz
			            	 }else if(vizsgalt==Board.EMPTY) {	//ha jobbra ures pont van 
			            		 mtjobb=false; //menjtov�bb legyen hamis
			            	 }else {  	//ha jobb ellens�g van
			            		 mtjobb=false;  // menjtov�bb legyen hamis
			            	 }
			      	 
					   }
			    	}// a t�bbi ir�nyba szint�n �gy
 
			    	 
			    	//balra megy�nk (max 5-�t)
			        if (x - i >= 0) {//ha nem �tk�z�nk a falba
			        	vizsgalt = board.getColor( x - i, y);// megk�rdeni a balra lev� pont sz�n�t
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
			        
			      //fel megy�nk (max 5-�t)
		          if (y + i < board.getDim()) {//ha nem �tk�z�nk a falba
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
		          
		            //le megy�nk (max 5-�t)
		            if (y - i >= 0) {//ha nem �tk�z�nk a falba
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
		            
		          //jobbrafel megy�nk (max 5-�t)
		            if (x + i < board.getDim() && y + i < board.getDim()) { //ha nem �tk�z�nk a falba
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
		            
		          //jobbrale megy�nk (max 5-�t)
		             if (x + i < board.getDim() && y - i >= 0) { //ha nem �tk�z�nk a falba
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
		            	 
		            	//balrafel megy�nk (max 5-�t)
		                    if (x - i >= 0 && y + i < board.getDim()) { //ha nem �tk�z�nk a falba
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
		                    	
		                   //balrale megy�nk (max 5-�t)
		                          if (x - i >= 0 && y - i >= 0) { //ha nem �tk�z�nk a falba
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
	    					
	    }//ez itt a ciklus v�ge
   }//count v�ge
   
   public void rule() {
			
	    //ha a j�t�kosnak legal�bb 2 ir�nyban is 3 vagy 4 hossz� sora van akkor lehet sz� dupla3r�l vagy dupla4r�l
	   
			dupla3esely=0; //h�ny k�l�nb�z� ir�nyban van 3 hossz� sora 
			dupla4esely=0; //h�ny k�l�nb�z� ir�nyban van 4 hossz� sora
			
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
   
   
   
   //a sz�n, az �sszef�gg� sorhosszok, a dupla3esely �s dupla4esely alapj�n meg�llap�tjuk nyert-e valaki
   public void evaluate(int color){
	   
		    // ha valamelyik ir�nyban kij�tt az �sszef�gg� pontosan 5 hossz� sor
		     if(hosszVszint==5 || hosszFleges==5 || hosszSem==5 || hosszSle==5){
		    	 if(color==Board.WHITE){	
		    		gameover=true;
		    	 	whiteWin=true;
		    	 	//System.out.println("feh�r nyert");
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
		     
		     
		  // ha valamelyik ir�nyban 5-n�l hosszabb �sszef�gg� sor j�n ki
		     if(hosszVszint > 5 || hosszFleges > 5 || hosszSem > 5 || hosszSle > 5){
		    	 if(color==Board.WHITE){	
			    		gameover=true;
			    	 	whiteWin=true;
			    	 	//System.out.println("feh�r nyert");
			    	 	Window.setLTurnsText("White won");
			    	 	Window.setLPlayerText("White won");

			    	 }else if(color==Board.BLACK) {//fekete overline esete
			    		gameover=true;
			    		whiteWin=true;
			    		//System.out.println("feh�r nyert");
			    		Window.setLTurnsText("White won");
			    		Window.setLPlayerText("White won");
			    	 } 
		     }
		     
		     // ha a feket�nek dupla3masa vagy dupla4ese van (legal�bb a s�k 2 ir�ny�ban van h�rmasa vagy n�gyese)
		     if ((color==Board.BLACK) && (dupla3esely >= 2 || dupla4esely >= 2 )) {
		            gameover = true;
		            whiteWin = true;
		            //System.out.println("feh�r nyert");
		            Window.setLTurnsText("White won");
		            Window.setLPlayerText("White won");
		        }
		     
		     //ha megtelt a p�lya
		     if(board.getpListSize() >= 225){
		            gameover = true;
		            egal=true;
		            //System.out.println("d�ntetlen");
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

	//a koordin�t�kat �talak�tja �rtelmes alakba a M�TRIXHOZ
    //ez a kev�s itt a m�gia r�sze
	public int coordNormalize (int n) {
		int z;
		if ((n - 20)% 30 >= 15) {
			z = (n - 20) / 30 + 1;
		} else {
			z = (n - 20) / 30;
		}
		return z;
	}
	

	// a koordin�t�kat �talakitja �rtelmes alakba a PONTKIRAJZOL�SHOZ
	//m�g egy pici m�gia
	public int coordNormalizeDraw (int n) {
		return coordNormalize(n)*30+20;
	}
	


		

 
}
