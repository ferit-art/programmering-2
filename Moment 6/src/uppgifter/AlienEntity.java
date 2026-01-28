package uppgifter;

import java.awt.Image;

public class AlienEntity extends Entity{

	public AlienEntity(Image image, double xPos, double yPos, int speed) {
		super(image, xPos, yPos, speed);
		// TODO Auto-generated constructor stub
	}

	// uppgift 4

	@Override
	public void move(long deltaTime) {
		dy = 1;
		xPos += dx * (deltaTime / 1000000000.0) * speed;
		yPos += dy * (deltaTime / 1000000000.0) * speed;
	}

}
