package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 8;
	int color;
	
	public SpaceShip(int x, int y, int width, int height, int color) {
		super(x, y, width, height);
		this.color = color;
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(color == 1){
			g.setColor(Color.YELLOW);
		}
		else if(color == 2){
			g.setColor(Color.GREEN);
		}
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}

}
