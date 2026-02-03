package uppgift1;

import java.awt.Image;

public class AlienEntity extends Entity{

	public AlienEntity(Image image, int xPos, int yPos, int speed) {
		super(image, xPos, yPos, speed);
		// TODO Auto-generated constructor stub
	}

	// uppgift 4

	@Override
	public void move(long deltaTime) {
		dy = 1;
		setXPos((int) (getXPos() + dx * (deltaTime / 1000000000.0) * speed));
		setYPos((int) (getYPos() + dy * (deltaTime / 1000000000.0) * speed));
	}

}
