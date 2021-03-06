import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	private int x[];
    private int y[];
    private char direction='R';//la direction initialisée vers la droit
    public int t=5; //taille serpent
    private int size; //taille des unités du serpent
    
    public Snake(int[] X,int[] Y,int size) {
		this.setX(X);
		this.setY(Y);
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
            getX()[i]=getX()[i-1];
            getY()[i]=getY()[i-1];
        }
        switch(direction) {
        case 'D':
            getY()[0]=getY()[0]+1;
            break;
        case'U':
            getY()[0]=getY()[0]-1;
            break;
        case 'L':
            getX()[0]=getX()[0]-1;
            break;
        case 'R':
            getX()[0]=getX()[0]+1;
            break;}}
    
    public void draws(Graphics g) {
		g.setColor(new Color(34,102,10));
        for (int i = 0; i<t; i++) {	//dessiner le serpent element par element
			g.fillRect(getX()[i]*size, getY()[i]*size, size, size);
		}
        g.setColor(new Color(250,10,10));
        g.fillRoundRect(getX()[0]*size + size/4, getY()[0]*size + size/4 , size/2, size/2, 20 , 20 );
        g.drawRect(getX()[0]*size, getY()[0]*size, size, size);
        for (int i = 1; i<t; i++) {	
			g.fillRoundRect(getX()[i]*size + size/3 , getY()[i]*size + size/3 , size/3, size/3, 30 , 30 );
			g.drawRect(getX()[i]*size, getY()[i]*size, size, size);
		}
    }
	public void initialiserSerpent() {//POUR AVOIR LA POSSIBILITE DU REPLAY
		direction='R';
	    t=5;
	    for (int i = 0; i < getX().length; i++) {
			getX()[i]=0;
			getY()[i]=0;
		}
	    
	}
	public int[] getY() {
		return y;
	}
	public void setY(int y[]) {
		this.y = y;
	}
	public int[] getX() {
		return x;
	}
	public void setX(int x[]) {
		this.x = x;
	}
	

}