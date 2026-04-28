package se.egy.game.model;

import java.awt.Image;

public class Entity extends Sprite {

	public Entity(Image image, int x, int y, int speed) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
		this.speed = speed;
	}

	private int dx = 0, dy = 0;
	private int speed; // Rörelsehastighet i px/s

	/* Konstruktor */

	public void setDirectionX(int dx) {
		this.dx = dx;
	}

	public void setDirectionY(int dy) {
		this.dy = dy;
	}

	public int getDirectionX() {
		return this.dx;
	}

	public int getDirectionY() {
		return this.dy;
	}

	@Override
	protected void moveX(long deltaTime) {
		// TODO Auto-generated method stub
		setX(getX() + dx * (deltaTime / 1000000000.0) * speed);
	}

	@Override
	protected void moveY(long deltaTime) {
		// TODO Auto-generated method stub
		setY(getY() + dy * (deltaTime / 1000000000.0) * speed);
	}
}
