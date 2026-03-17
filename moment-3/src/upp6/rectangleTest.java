package upp6;

import java.awt.Color;

import se.egy.graphics.GameScreen;

public class rectangleTest {

	
	static Rectangle rect = new Rectangle(200, 100, 100, 200, Color.green);
	private static boolean gameRunning = true;

	public static void main(String[] args) {
		GameScreen gameScreen = new GameScreen("Rektanglar", 1920, 1080, false);
		
		while (gameRunning) {
			gameScreen.render(rect);
			try {
				Thread.sleep(4000);
			} catch (Exception e) {
			} // pausar i 2000 ms = 2 s

			rect.setX(300);
			rect.setY(400);

			gameScreen.render(rect);

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
