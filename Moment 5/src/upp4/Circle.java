package upp4;

import java.awt.Color;
import java.awt.Graphics2D;

/*Circle justeras till att vara en subklass av Ellipse istället för Shape
 * för att en cirkel är en ellipse också, som är en form
 * det vill säga !!Shape!!*/
public class Circle extends Ellipse {
	private int diameter;

	/** Konstruktor */
	public Circle(int diameter, int xPos, int yPos, Color color) {
		super(xPos, yPos, diameter, diameter, color); // anropar Ellipses konstruktor
		this.diameter = diameter;
	}
}
