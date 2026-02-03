package uppgift1;

import java.awt.Image;

public class RocketEntity extends Entity {

	public RocketEntity(Image image, int xPos, int yPos, int speed) {
		super(image, xPos, yPos, speed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(long deltaTime) {
		setXPos((int) (getXPos() + dx * (deltaTime / 1000000000.0) * speed));
		setYPos((int) (getYPos() + dy * (deltaTime / 1000000000.0) * speed));
	}
}
