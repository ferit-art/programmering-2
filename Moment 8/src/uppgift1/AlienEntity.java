package uppgift1;

import java.awt.Image;

public class AlienEntity extends Entity {

	public AlienEntity(Image image, double xPos, double yPos, int speed) {
		super(image, xPos, yPos, speed);
		dy = 1;
	}

	@Override
	public void move(long deltaTime) {
		setXPos((getXPos() + dx * (deltaTime / 1000000000.0) * speed));
		setYPos((getYPos() + dy * (deltaTime / 1000000000.0) * speed));
	}

}
