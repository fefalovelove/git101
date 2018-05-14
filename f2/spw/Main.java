package f2.spw;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;  
import javax.swing.*; 

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 640);
		frame.getContentPane().setLayout(new BorderLayout());

		SpaceShip v = new SpaceShip(280, 550, 20, 20, 1);
		SpaceShip v2 = new SpaceShip(90, 550, 20, 20, 2);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v,v2);

		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);

		frame.setVisible(true);
		engine.start();
	}
}
