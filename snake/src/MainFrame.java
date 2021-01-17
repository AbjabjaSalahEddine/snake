import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame(){
		this.add(new ScreenGame());
		this.setTitle("Jeu de SERPENT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);// centrer la fenetre du Jeu
		this.setVisible(true);
		//ADD AN ICON TO THE GAME
		ImageIcon image=new ImageIcon(("icon.png"));
		this.setIconImage(image.getImage());
	}
	public static void main(String[] args) {
		//LANCER LE JEU
		new MainFrame();

	}

}
