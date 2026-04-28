package alla_uppgifter;

import java.awt.Graphics2D;

public interface Drawable {
	public void draw(Graphics2D g);

	public double getYPos();

	public double getHeight();

	public void setDirectionY(int i);

	public void setActive(boolean b);

	public void move(long deltaTime);

	public boolean collision(Entity entity);
}
