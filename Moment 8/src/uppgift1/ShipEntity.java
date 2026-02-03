package uppgift1;

import java.awt.Image;

public class ShipEntity extends Entity {

	public ShipEntity(Image image, int xPos, int yPos, int speed) {
		super(image, xPos, yPos, speed);
	}

	/**
	 * Ändrar läget i x-led
	 */
	public void move(long deltaTime) {
		setXPos((int) (getXPos() + dx * (deltaTime / 1000000000.0) * speed));
		setYPos((int) (getYPos() + dy * (deltaTime / 1000000000.0) * speed));
	}
}
