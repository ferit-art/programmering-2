package upp4;

import java.awt.Color;
import java.awt.Graphics2D;

import se.egy.graphics.Drawable;

public class Circle implements Drawable {
	private int height;
	private int width;
	private int xPos, yPos;
	private Color color;

	/**
	 * Konstruktor
	 */
	public Circle(int width, int height, int xPos, int yPos, Color color) {
		this.height = height;
		this.width = width;
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = color;
	}

	public double getArea() {
		double area = 3.14 * (height * width);
		return area;
	}

	public double getOmk() {
		double omk = 2 * 3.14 * height;
		return omk;
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(xPos, yPos, width, height);
	}
}
