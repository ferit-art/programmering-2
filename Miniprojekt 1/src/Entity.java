import java.awt.Graphics2D;
import java.awt.Image;

import se.egy.graphics.Drawable;

public class Entity implements Drawable {
	private Image img;
	private double xPos, yPos;

	/**
	 * Konstruktor
	 */
	public Entity(Image img, int xPos, int yPos) {
		this.img = img;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public double getX() {
		return xPos;
	}

	public double getY() {
		return yPos;
	}

	public void setX(double value) {
		xPos = value;
	}

	public void setY(double value) {
		yPos = value;
	}

	public void draw(Graphics2D g) {
		g.drawImage(img, (int) xPos, (int) yPos, null);
	}
}
