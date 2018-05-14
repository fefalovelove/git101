package f2.spw;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public abstract class Sprite2 {
	int x;
	int y;
	int diameter;
	int diameters;
	
	public Sprite2(int x, int y, int diameter, int diameters) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.diameters = diameters;
	}

	abstract public void draw(Graphics2D g);
	
	public Double getCircle() {
		return new Ellipse2D.Double(x, y, diameter, diameters);
	}
}
