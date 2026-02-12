package uppgift1;

import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

import se.egy.graphics.*;

public class GameMain implements KeyListener {

	private boolean gameRunning = true;
	private long lastUpdateTime;
	private int height = 800;
	private int width = 1000;

	private GameScreen GameScreen = new GameScreen("Game", width, height, false);

	private HashMap<String, Boolean> keyDown = new HashMap<>();

	private CopyOnWriteArrayList<Entity> spriteList = new CopyOnWriteArrayList<>();
	private List<Entity> toRemove = new ArrayList<>();

	private ShipEntity ship;
	private AlienEntity[] aliens = new AlienEntity[5];
	private long lastRocketTime;

	public GameMain() {
		GameScreen.setKeyListener(this);

		keyDown.put("left", false);
		keyDown.put("right", false);
		keyDown.put("down", false);
		keyDown.put("up", false);
		keyDown.put("space", false);
		loadImages();
		gameLoop();
	}

	public void loadImages() {
		Image shipImg = new ImageIcon(getClass().getResource("/ship.png")).getImage();

		int shipX = GameScreen.getWidth() - (GameScreen.getWidth() / 2 + shipImg.getWidth(null) / 2);
		int shipY = GameScreen.getHeight() - (10 + shipImg.getHeight(null));

		ship = new ShipEntity(shipImg, shipX, shipY, 200);
		spriteList.add(ship);

		for (int i = 0; i < aliens.length; i++) {
			Image alienImg = new ImageIcon(getClass().getResource("/alien.png")).getImage();

			// To center the aliens

			int alienX = GameScreen.getWidth()
					- ((GameScreen.getWidth() / 2 + alienImg.getWidth(null) / 2) + (100 - alienImg.getWidth(null) / 2));
			int alienY = 0;

			aliens[i] = new AlienEntity(alienImg, alienX + 40 * i, alienY, 90);
		}

		for (Entity alien : aliens) {
			spriteList.add(alien);
		}

		spriteList.add(ship);
	}

	public void update(long deltaTime) {

		if (keyDown.get("right") && GameScreen.getWidth() > ship.getXPos() + ship.getWidth()) {
			ship.setDirectionX(1);
		} else if (keyDown.get("left") && 0 < ship.getXPos()) {
			ship.setDirectionX(-1);
		} else if (keyDown.get("up") && 0 < ship.getYPos()) {
			ship.setDirectionY(-1);
		} else if (keyDown.get("down") && GameScreen.getHeight() > ship.getYPos() + ship.getHeight()) {
			ship.setDirectionY(1);
		} else {
			ship.setDirectionY(0);
			ship.setDirectionX(0);
		}

		for (Entity entity : spriteList) {

			if (entity instanceof AlienEntity) {

				if (entity.getYPos() > GameScreen.getHeight() - entity.getHeight()) {
					entity.setDirectionY(-1);
				} else if (entity.getYPos() < 0) {
					entity.setDirectionY(1);
				}
			} else if (entity instanceof MissileEntity && entity.getYPos() < 0) {
				entity.setActive(false);
				toRemove.add(entity);
			}
			entity.move(deltaTime);
		}

		spriteList.removeAll(toRemove);
	}

	public void render(CopyOnWriteArrayList<Entity> spriteList) {
		GameScreen.render(spriteList);
	}

	public void gameLoop() {
		lastUpdateTime = System.nanoTime();
		long lastFpsUpdateTime = System.nanoTime();

		while (gameRunning) {
			long deltaTime = System.nanoTime() - lastUpdateTime;
			lastUpdateTime = System.nanoTime();
			update(deltaTime);
			render(spriteList);
			checkCollisionAndRemove();

			double fps = 1_000_000_000.0 / deltaTime;

			if (System.nanoTime() - lastFpsUpdateTime > 800000000) { // About 0.8 seconds
				System.out.println("Fps: " + (int) fps + " Frametime: " + (int) deltaTime / 100000 + " ms");
				lastFpsUpdateTime = System.nanoTime();
			}
		}
	}

	/** Spelets tangentbordslyssnare **/
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			keyDown.put("up", true);
		} else if (key == KeyEvent.VK_S) {
			keyDown.put("down", true);
		} else if (key == KeyEvent.VK_A) {
			keyDown.put("left", true);
		} else if (key == KeyEvent.VK_D) {
			keyDown.put("right", true);
		} else if (key == KeyEvent.VK_SPACE && System.nanoTime() - lastRocketTime > 800_000_000L) {
			launchRocket();
		}
	}

	public void checkCollisionAndRemove() {
		// alien <-> missile
		
		for (Entity entity : spriteList) {

			if (entity instanceof AlienEntity) {

	            if (ship.missile != null && ship.missile.getActive() && entity.collision(ship.missile)) {

					entity.setActive(false);
					ship.missile.setActive(false);

					toRemove.add(entity);
					toRemove.add(ship.missile);

				}
				if (entity.collision(ship)) {

					entity.setActive(false);
					ship.setActive(false);

					toRemove.add(entity);
					toRemove.add(ship);
				}
			}
		}
		spriteList.removeAll(toRemove);
		toRemove.clear();
	}

	private void launchRocket() {
		Image rocketImg = new ImageIcon(getClass().getResource("/rocket.png")).getImage();
		ship.missile = new MissileEntity(rocketImg, ship.getXPos() + ship.getWidth() / 2 - rocketImg.getWidth(null) / 2, // Center
				ship.getYPos(), 200);
		ship.missile.setActive(true);
		spriteList.add(ship.missile);
		lastRocketTime = System.nanoTime();
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			keyDown.put("up", false);
		} else if (key == KeyEvent.VK_S) {
			keyDown.put("down", false);
		} else if (key == KeyEvent.VK_A) {
			keyDown.put("left", false);
		} else if (key == KeyEvent.VK_D) {
			keyDown.put("right", false);
		} else if (key == KeyEvent.VK_SPACE) {
			keyDown.put("space", false);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		new GameMain();
	}
}
