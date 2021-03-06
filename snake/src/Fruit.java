import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Fruit {
    public int fx, fy,size;
    Random random=new Random();
    public Fruit(int size) {
		this.size=size;
	}
	public void newfruit(int W,int H) { // repositinement aleatoirement du fruit
		fx=random.nextInt((W/size)-1);
		fy=random.nextInt((H/size)-1);
		
	}
	public void drawf(Graphics g) { // dessine le fruit 
		g.setColor(Color.red);
        g.fillOval(fx*size, fy*size, size, size);
    }
}