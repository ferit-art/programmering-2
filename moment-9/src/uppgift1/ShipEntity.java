package uppgift1;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ShipEntity extends Entity {

	public MissileEntity missile = null; // ingen missil!

	public ShipEntity(Image image, double xPos, double yPos, int speed) {
		super(image, xPos, yPos, speed);
	}

	/**
	 * Ändrar läget i x-led
	 */
	public void move(long deltaTime) {
		if (missile != null && missile.getActive()) {
			missile.move(deltaTime);
		}
		setXPos((getXPos() + dx * (deltaTime / 1000000000.0) * speed));
		setYPos((getYPos() + dy * (deltaTime / 1000000000.0) * speed));
	}

	public void draw(Graphics2D g) {
		if (missile != null && missile.getActive()) {
			missile.draw(g);
		}
		super.draw(g);
	}

	public boolean tryToFire() {
		if (missile == null || !missile.getActive()) {
			missile = new MissileEntity(new ImageIcon(getClass().getResource("/rocket.png")).getImage(), getXPos() + 13,
					getYPos(), 90);
			missile.setActive(true);
			return true;
		} else
			return false;
	}

}
