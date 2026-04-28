package se.egy.game.model;

import java.awt.Graphics2D;
import java.awt.Image;

import se.egy.game.view.Drawable;

public abstract class Sprite implements Drawable {
	protected double x, y;
	private Image image;

	public Sprite(Image image, int x, int y) {
		this.x = x;
		this.y = y;
		this.image = image;
	}

	protected abstract void moveX(long deltaTime); // Abstrakt 1/1

	protected abstract void moveY(long deltaTime); // Abstrakt 2/2

	public void move(long deltaTime) {
		moveX(deltaTime);
		moveY(deltaTime);
	}

	public boolean collisionWith(Sprite s) {
		if (this.equals(s)) // Om krock testa mot den egna instansen. D.v.s krock med sig själv.
			return false;

		if (x + getWidth() < s.getX() || y + getHeight() < s.getY() || x > s.getX() + s.getWidth()
				|| y > s.getY() + s.getHeight()) {
			return false;
		} else {
			return true;
		}
	}

	public Image getImage() {
		return this.image;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight() {
		return image.getHeight(null);
	}
	
	public void draw(Graphics2D arg0) {
		// TODO Auto-generated method stub
		arg0.drawImage(getImage(), (int) getX(), (int) getY(), null);
	}
}