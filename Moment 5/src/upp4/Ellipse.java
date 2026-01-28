package upp4;

import java.awt.Color;
import java.awt.Graphics2D;

/*Ellipse skapas som ett bredare kategori, precis som Rectangle av Shape */
public class Ellipse extends Shape {
	protected int xD;
	protected int yD;

	public Ellipse(int xPos, int yPos, int xD, int yD, Color color) {
		super(xPos, yPos, color);
		this.xD = xD;
		this.yD = yD;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(getColor());
		g.fillOval(getxPos(), getyPos(), xD, yD);
	}

	@Override
	public int getArea() {
		double area = Math.PI * xD * yD;
		return (int) area;
	}

	@Override
	public int getOmk() {
		double omk = Math.sqrt(2 * xD * xD + 2 * yD * yD);
		return (int) omk;
	}

}
