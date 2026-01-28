import java.awt.Image;

public class ShipEntity extends Entity {

	public ShipEntity(Image image, double xPos, double yPos, int speed) {
		super(image, xPos, yPos, speed);
	}

	@Override
	public void move() {
		setX(getX() + getDx() * getSpeed());
		setY(getY() + getDy() * getSpeed());
	}
}
