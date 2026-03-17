import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.ImageIcon;

import se.egy.graphics.*; // Spelbiblioteket importeras

public class Game implements KeyListener {

	static GameScreen gameScreen = new GameScreen("Champ", 600, 600, false);
	private static boolean gameRunning = true;
	private static HashMap<String, Boolean> keyDown = new HashMap<>();
	private static Entity player;

	public Game() {
		gameScreen.setKeyListener(this);
		loadImages();
		keyDown.put("up", false);
		keyDown.put("down", false);
		keyDown.put("left", false);
		keyDown.put("right", false);
		keyDown.put("escape", false);

		gameLoop();
	}

	public void loadImages() {
		Image img = new ImageIcon(getClass().getResource("/playerImg.png")).getImage();
		player = new Entity(img, 384, 284);
		Image background = new ImageIcon(getClass().getResource("/space.png")).getImage();
		gameScreen.setBackground(background);
	}

	public void gameLoop() {
		while (gameRunning) {
			render();
			update();
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void render() {
		gameScreen.render(player);
	}

	public static void update() {
		if (keyDown.get("up")) {

			if (player.getY() > 0) {
				player.setY(player.getY() - 5); // Move up (decrease Y)
			}
		} else if (keyDown.get("down")) {

			if (player.getY() + 34 < gameScreen.getHeight()) {
				player.setY(player.getY() + 5); // Move down (increase Y)
			}
		} else if (keyDown.get("right")) {

			if (player.getX() + 33 < gameScreen.getWidth()) {
				player.setX(player.getX() + 5); // Move right (increase X)
			}
		} else if (keyDown.get("left")) {

			if (player.getX() > 0) {
				player.setX(player.getX() - 5); // Move left (decrease X)
			}
		} else if (keyDown.get("escape")) {
			System.exit(0);
		}
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new Game();
	}
}