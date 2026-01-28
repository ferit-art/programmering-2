package se.moment2.gt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import se.egy.graphics.*; // Spelbiblioteket importeras

public class GameTest implements KeyListener {

	static GameScreen gameScreen = new GameScreen("Champ", 600, 600, false);
	private static boolean gameRunning = true;
	private static ImgContainer player;
	private static ImgContainer background;
	private static HashMap<String, Boolean> keyDown = new HashMap<>();

	public GameTest() {
		gameScreen.setKeyListener(this);
		loadImages();
		keyDown.put("up", false);
		keyDown.put("down", false);
		keyDown.put("left", false);
		keyDown.put("right", false);
	}

	public void loadImages() {

		player = new ImgContainer(50, 50, "playerImg.png");
		background = new ImgContainer(-100, -1000, "space.png");
		gameScreen.setBackground(background);
	}

	public static void main(String[] args) {
		new GameTest();
		while (gameRunning) {
			gameScreen.render(player);
			update();
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void update() {
		if (Boolean.TRUE.equals(keyDown.get("up"))) {

			if (player.getY() > 0) {
				player.setY(player.getY() - 5); // Move up (decrease Y)
			}
		} else if (Boolean.TRUE.equals(keyDown.get("down"))) {

			if (player.getY() + player.getHeight() < gameScreen.getHeight()) {
				player.setY(player.getY() + 5); // Move down (increase Y)
			}
		} else if (Boolean.TRUE.equals(keyDown.get("right"))) {

			if (player.getX() + player.getWidth() < gameScreen.getWidth()) {
				player.setX(player.getX() + 5); // Move right (increase X)
			}
		} else if (Boolean.TRUE.equals(keyDown.get("left"))) {

			if (player.getX() > 0) {
				player.setX(player.getX() - 5); // Move left (decrease X)
			}
		} else if (Boolean.TRUE.equals(keyDown.get("escape"))) {
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
		else if (key == KeyEvent.VK_W)
			keyDown.put("up", true);
		else if (key == KeyEvent.VK_S)
			keyDown.put("down", true);
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
		else if (key == KeyEvent.VK_W)
			keyDown.put("up", false);
		else if (key == KeyEvent.VK_S)
			keyDown.put("down", false);
	}
}