package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import se.egy.graphics.*; // Spelbiblioteket importeras

public class Game implements KeyListener {

	static GameScreen gameScreen = new GameScreen("Invaderous", 600, 600, false);
	private static boolean gameRunning = true;
	private static ImgContainer ship;
	private static ImgContainer background;
	private static HashMap<String, Boolean> keyDown = new HashMap<>();

	public Game() {
		gameScreen.setKeyListener(this);
		loadImages();
		ship.setY(gameScreen.getHeight() - ship.getHeight());
		ship.setX(gameScreen.getWidth() / 2);
		keyDown.put("left", false);
		keyDown.put("right", false);
		keyDown.put("escape", false);
	}

	public void loadImages() {
		ship = new ImgContainer(50, 50, "ship.png");
		background = new ImgContainer(-100, -1000, "space.png");
		gameScreen.setBackground(background);
	}

	public static void main(String[] args) {
		new Game();
		while (gameRunning) {
			gameScreen.render(ship);
			update();
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void update() {
		if (keyDown.get("right")) {

			if (ship.getX() + ship.getWidth() < gameScreen.getWidth()) {
				ship.setX(ship.getX() + 5); // Move right (increase X)
			}
		} else if (keyDown.get("left")) {

			if (ship.getX() > 0) {
				ship.setX(ship.getX() - 5); // Move left (decrease X)
			}
		} else if (keyDown.get("escape")) {
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A)
			keyDown.put("left", true);
		else if (key == KeyEvent.VK_D)
			keyDown.put("right", true);
		else if (key == KeyEvent.VK_ESCAPE)
			keyDown.put("escape", true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A)
			keyDown.put("left", false);
		else if (key == KeyEvent.VK_D)
			keyDown.put("right", false);
	}
}