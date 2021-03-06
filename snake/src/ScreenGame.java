import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ScreenGame extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800, HEIGHT = 600;//LES DIMENESION
	public static final int taille_unit=20;
	public static final int DELAY=100; //DETERMINE LA VITESSE DU JEU
	Fruit F=new Fruit(taille_unit);
	int nombreDesCases=(WIDTH/taille_unit)*HEIGHT/taille_unit;
	int[] X=new int[nombreDesCases];
	int[] Y=new int[nombreDesCases];	
	Snake S=new Snake(X,Y,taille_unit);
	Timer timer;
	boolean running=false;
	int Score=0;
	boolean win=false;
	
	public ScreenGame() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(new Color(180,150,30));
		this.setFocusable(true);
		this.addKeyListener(new LectureClavier());
		lancerlejeu();
	}
	
	
	public void lancerlejeu(){
		F.newfruit(WIDTH,HEIGHT);//creation du Fruit et
		S.initialiserSerpent();   // du Serpent
		Score=0;
		running = true;
		timer=new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(running) {
			F.drawf(g);  //Affichage du fruit
			S.draws(g);  //Affichage du Snake
		}else {
			if(win) {
				drawWin(g);
			}else {
				drawGO(g);
			}

		}
		drawscore(g);//Affichage du score
	}
	public void drawscore(Graphics g) {
		g.setColor(Color.blue);
		g.setFont(new Font("Helvetica", Font.PLAIN, 18));
		g.drawString("SCORE: "+Score, 360, 20); /* les 2 derniers argu concerne la position souhaité 
														pour le message*/
	}
	public void drawGO(Graphics g) {
		g.setColor(new Color(128, 0, 128));
		g.setFont(new Font("ZapfDingbats", Font.PLAIN, 65));//police et taille de "game over"
		g.drawString("  GAME  OVER", 170, 230);
		g.setFont(new Font("ZapfDingbats", Font.PLAIN, 25));//police et taille de la demande de replay
		g.drawString("press (r) to replay", 300, 280);  
	}
	public void drawWin(Graphics g) {
		g.setColor(new Color(128, 0, 128));
		g.setFont(new Font("ZapfDingbats", Font.PLAIN, 65));//police et taille de "game over"
		g.drawString("  CONGRATS U WON", 170, 230);
		g.setFont(new Font("ZapfDingbats", Font.PLAIN, 25));//police et taille de la demande de replay
		g.drawString("press (r) to replay", 300, 280);  
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			S.move();// deplacement du serpent
			verifierCollisions();// verifier les Collisions
			timer.setDelay(DELAY*(1-(Score+5)/nombreDesCases));
			eatFruit();//mangerle fruit
		}
		repaint();
	}
	public class LectureClavier extends KeyAdapter{
		@Override      //CONTROLE DES DIRECTIONS
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(S.getY()[0]!=S.getY()[1]) {
					S.setDirection('L');
				}break;
			case KeyEvent.VK_RIGHT:
				if(S.getY()[0]!=S.getY()[1]) {
					S.setDirection('R');
				}break;
			case KeyEvent.VK_DOWN:
				if(S.getX()[0]!=S.getX()[1]) {
					S.setDirection('D');
				}break;
			case KeyEvent.VK_UP:
				if(S.getX()[0]!=S.getX()[1]) {
					S.setDirection('U');
				}break;
			case KeyEvent.VK_R:
				if(!running) { 	//NE RELANCER LE JEU QUE SI ON EST DANS LE "GAME OVER" 
					lancerlejeu();
				}break;
			}
		}
	}
	public void verifierCollisions() {
		for (int i=S.t ; i >0; i--) {	//COLLISION DU SERPENT AVEC LUI MEME
			if ((X[0]==X[i])&&(Y[0]==Y[i])) {
				running=false;
			}
		}
		if(X[0]<0||X[0]>(WIDTH/taille_unit)-1||Y[0]<0||Y[0]>(HEIGHT/taille_unit)-1) { //COLLISION AVEC MUR
			running=false;
		}
		if(!running) {
			timer.stop();
		}
	}
	public void eatFruit() {
		if((X[0]==F.fx)&&(Y[0]==F.fy)) {
			S.t++;
			Score++;
			F.newfruit(WIDTH,HEIGHT);
		}
	}
	public void verifyWin() {
		if(S.t==nombreDesCases) {
			win=true;
			running=false;
			timer.stop();
		}
	}
	
}
