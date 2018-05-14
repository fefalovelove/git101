package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	ArrayList<Sprite2> sprites2 = new ArrayList<Sprite2>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		big.drawString(String.format("%08d", reporter.getScore2()), 20, 20);
		big.drawString(String.format("%01d", reporter.getScorelive()), 340, 40);
		big.drawString(String.format("%01d", reporter.getScorelive2()), 50, 40);
		big.drawString(String.format(reporter.getlive()), 300, 40);
		big.drawString(String.format(reporter.getlive()), 20, 40);

		for(Sprite s : sprites){
			s.draw(big);
		}

		for(Sprite2 s : sprites2){
			s.draw(big);
		}
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
