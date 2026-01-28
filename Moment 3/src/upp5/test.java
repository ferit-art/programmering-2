package upp5;

import java.awt.Color;

import se.egy.graphics.Drawable;
import se.egy.graphics.GameScreen;

public class test {

	static GameScreen gameScreen = new GameScreen("Rektanglar", 1920, 1080, false);
	static Drawable[] shapeArray = new Drawable[4];
	private static boolean gameRunning = true;

	public static void main(String[] args) {
		shapeArray[0] = new Circle(200, 100, 100, 200, Color.green);
		shapeArray[1] = new Rectangle(200, 100, 500, 400, Color.red);
		shapeArray[2] = new Circle(200, 100, 200, 200, Color.blue);
		shapeArray[3] = new Rectangle(200, 100, 300, 400, Color.white);

		while (gameRunning) {
			gameScreen.render(shapeArray);
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
