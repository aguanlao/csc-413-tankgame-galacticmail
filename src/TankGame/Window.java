package TankGame;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Window extends JPanel {

	private int nTiles = 12;
	private ImageIcon[] theBackground;
	
	public Window() {
		
		loadImage();
	}
	
	private ImageIcon loadImage() {
	
		theBackground = new ImageIcon[nTiles];
		return theBackground;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
