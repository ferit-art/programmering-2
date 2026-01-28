package upp6;

import java.awt.Color;
import java.awt.Graphics2D;

import se.egy.graphics.Drawable;

public class Rectangle implements Drawable {
	private int width;
	private int height;
	private int xPos, yPos;
	private Color color;

	/**
	 * Konstruktor
	 */
	public Rectangle(int width, int height, int xPos, int yPos, Color color) {
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = color;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public void setX(int value) {
		xPos = value;
	}

	public void setY(int value) {
		yPos = value;
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(xPos, yPos, width, height);
	}
}
