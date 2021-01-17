import java.awt.Color;
import java.awt.Graphics;

public class Snake {
    private int x[];
	private int y[];
    private char direction='R';//la direction initialisée vers la droit
    public int t=5; //taille serpent
    private int size; //taille des unités du serpent
    
    public Snake(int[] X,int[] Y,int size) {
		this.x=X;
		this.y=Y;
		this.size=size;
	}
    //seteur et geteur de la direction
    public char getDirection() {
        return direction;
    }

    public void setDirection(char d) {
        this.direction = d;
    }
    public void move() {
        for(int i=t;i>0;i--) {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch(direction) {
        case 'D':
            y[0]=y[0]+1;
            break;
        case'U':
            y[0]=y[0]-1;
            break;
        case 'L':
            x[0]=x[0]-1;
            break;
        case 'R':
            x[0]=x[0]+1;
            break;}}
    
    public void draws(Graphics g) {
		g.setColor(new Color(34,102,10));
        for (int i = 0; i<t; i++) {	//dessiner le serpent element par element
			g.fillRect(x[i]*size, y[i]*size, size, size);
		}
        g.setColor(new Color(250,10,10));
        g.fillRoundRect(x[0]*size + size/4, y[0]*size + size/4 , size/2, size/2, 20 , 20 );
        g.drawRect(x[0]*size, y[0]*size, size, size);
        for (int i = 1; i<t; i++) {	
			g.fillRoundRect(x[i]*size + size/3 , y[i]*size + size/3 , size/3, size/3, 30 , 30 );
			g.drawRect(x[i]*size, y[i]*size, size, size);
		}
    }
	public void initialiserSerpent() {//POUR AVOIR LA POSSIBILITE DU REPLAY
		direction='R';
	    t=5;
	    for (int i = 0; i < x.length; i++) {
			x[i]=0;
			y[i]=0;
		}
	    
	}
	

}