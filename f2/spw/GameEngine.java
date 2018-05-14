package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();	
	private SpaceShip v;	
	private SpaceShip v2;
	
	private Timer timer;
	private long score = 0;
	private long score2 = 0;
	private double difficulty = 0.1;
	private boolean alive = true;
	private boolean alive2 = true;
	private int live = 2;
	private int live2 = 2;
	private String mylive = "live";

	
	public GameEngine(GamePanel gp, SpaceShip v,SpaceShip v2) {
		this.gp = gp;
		this.v = v;	
		this.v2 = v2;		

		gp.sprites.add(v2);
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 5);
		gp.sprites.add(e);
		enemies.add(e);

		Enemy2 e2 = new Enemy2((int)(Math.random()*390), 20);
		gp.sprites2.add(e2);
		enemies2.add(e2);
	}

	
	private void process(){
	
		if(Math.random() < difficulty){
			generateEnemy();
		}

		if(score > 1 || score2 >1){
			difficulty += 0.001;
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			}
		}

		Iterator<Enemy2> e_iter2 = enemies2.iterator();
		while(e_iter2.hasNext()){
			Enemy2 e2 = e_iter2.next();
			e2.proceed();
			
			if(!e2.isAlive()){
				e_iter2.remove();
				gp.sprites2.remove(e2);
				
			}
		}
		
		gp.updateGameUI(this);


		
		Rectangle2D.Double vr = v.getRectangle();
		Ellipse2D.Double er;
		for(Enemy2 e : enemies2){
			er = e.getCircle();
			if(er.intersects(vr)){
				if(alive == true){
					score += 100;
				}
				return;
			}
		}

		Rectangle2D.Double vr4 = v.getRectangle();
		Rectangle2D.Double er4;
		for(Enemy e : enemies){
			er4 = e.getRectangle();
			if(er4.intersects(vr4)){
				if(live != 0){
					live = live - 1;
				}
				if(live <= 0){
					alive = false;
				}
				return;
			}
		}

		Rectangle2D.Double vr3 = v2.getRectangle();
		Ellipse2D.Double er3;
		for(Enemy2 e : enemies2){
			er3 = e.getCircle();
			if(er3.intersects(vr3)){
				if(alive2 == true){
					score2 += 100;
				}
				return;
			}
		}

		Rectangle2D.Double vr2 = v2.getRectangle();
		Rectangle2D.Double er2;
		for(Enemy e : enemies){
			er2 = e.getRectangle();
			if(er2.intersects(vr2)){
				if(live2 != 0){
					live2 = live2 - 1;
				}
				if(live2 <= 0){
					alive2 = false;
				}
				return;
			}
		}
		if(alive == false && alive2 == false){
			die();
		}

	
	}

	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		if(alive == true){
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				v.move(-1);
				break;
			case KeyEvent.VK_RIGHT:
				v.move(1);
				break;
			}
		}
		if(alive2 == true){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
				v2.move(-1);
				break;
			case KeyEvent.VK_D:
				v2.move(1);
				break;
			}
		}	
	}

	public long getScore(){
		return score;
	}
	public long getScore2(){
		return score2;
	}
	public int getScorelive(){
		return live;
	}
	public int getScorelive2(){
		return live2;
	}
	public String getlive(){
		return mylive;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
