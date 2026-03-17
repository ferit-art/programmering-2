package upp4;

import java.awt.Color;

import se.egy.graphics.GameScreen;

public class CircleTest {

	static GameScreen gameScreen = new GameScreen("Rektanglar", 1920, 1080, false);
	static Circle recArray[] = new Circle[4];
	private static boolean gameRunning = true;

	public static void main(String[] args) {
		recArray[0] = new Circle(200, 100, 100, 200, Color.green);
		recArray[1] = new Circle(200, 100, 500, 400, Color.red);
		recArray[2] = new Circle(200, 100, 200, 200, Color.blue);
		recArray[3] = new Circle(200, 100, 300, 400, Color.white);

		while (gameRunning) {
			gameScreen.render(recArray);
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
