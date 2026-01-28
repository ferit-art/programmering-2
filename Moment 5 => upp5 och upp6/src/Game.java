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
	private static ShipEntity player;

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
		Image img = new ImageIcon(getClass().getResource("/ship.png")).getImage();
		player = new ShipEntity(img, 384, 284, 10);
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

		player.setDirectionX(0);
		player.setDirectionY(0);

		if (keyDown.get("up")) {

			if (player.getY() > 0) {
				player.setDirectionY(-1);
			}
			if (player.getY() < 0) {
				player.setY(0);
			}
		} else if (keyDown.get("down")) {

			if (player.getY() + player.getHeight() < gameScreen.getHeight()) {
				player.setDirectionY(1); // Move down (increase Y)
			}
		} else if (keyDown.get("right")) {

			if (player.getX() + player.getWidth() < gameScreen.getWidth()) {
				player.setDirectionX(1);
			}
		} else if (keyDown.get("left")) {

			if (player.getX() > 0) {
				player.setDirectionX(-1);
			}
			if (player.getX() < 0) {
				player.setX(0);
			}

		} else if (keyDown.get("escape")) {
			System.exit(0);
		}
		player.move();
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