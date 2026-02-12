package uppgift1;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import se.egy.graphics.Drawable;

public abstract class Entity implements Drawable {
	private Image image;

	private double xPos; // Positionen

	private double yPos;

	protected int speed; // Hastighet i px/sekund

	protected int dx = 0, dy = 0; // Rörelseriktning

	private boolean active = false; // Gör alla nya objekt aktiva.

	private Rectangle rec = null;

	/**
	 * Konstruktor
	 */
	public Entity(Image image, double xPos, double yPos, int speed) {
		this.image = image;
		this.setXPos(xPos);
		this.setYPos(yPos);
		this.speed = speed;
		rec = new Rectangle((int) xPos, (int) yPos, image.getWidth(null), image.getHeight(null));
	}

	/**
	 * Ritar bilden på ytan g
	 */
	public void draw(Graphics2D g) {
		g.drawImage(image, (int) getXPos(), (int) getYPos(), null);
	}

	/**
	 * Vilken riktning i x-led
	 * 
	 * @param dx 0 = stilla, 1 = höger, -1 = vänster
	 */
	public void setDirectionX(int dx) {
		this.dx = dx;
	}

	/**
	 * Vilken riktning i y-led
	 * 
	 * @param dy 0 = stilla, 1 = höger, -1 = vänster
	 */
	public void setDirectionY(int dy) {
		this.dy = dy;
	}

	/**
	 * Metod som gör förflyttningen, dvs ändrar xPos och yPos Måste skapas i klasser
	 * som ärver entity
	 * 
	 * @param antal nanosekunder sedan förra anropet
	 */
	public abstract void move(long deltaTime);

	public int getHeight() {
		return image.getHeight(null);
	}

	public int getWidth() {
		return image.getWidth(null);
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getXPos() {
		return xPos;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}

	public double getYPos() {
		return yPos;
	}

	public Rectangle getRectangle() {
		rec.setLocation((int) xPos, (int) yPos);
		return rec;
	}

	public boolean collision(Entity entity) {
		getRectangle(); // Uppdaterar positionen på den egna rektangeln
		return rec.intersects(entity.getRectangle());
	}
}
