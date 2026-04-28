package se.egy.game.controller;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ImageIcon;
import se.egy.game.model.*;
import se.egy.game.view.*;

public class GameController implements KeyListener {
	private GameView gv;

	private boolean gameRunning = true;
	private int fps;

	private Entity player;

	private List<Entity> entityList = new CopyOnWriteArrayList<>();
	private List<Entity> toRemove = new ArrayList<>();
	private GhostEntity[] ghosts = new GhostEntity[5];

	private HashMap<String, Boolean> keyDown = new HashMap<>();

	public GameController(GameView gv, int fps) {
		this.gv = gv;
		this.gv.addKeyListener(this);

		this.fps = fps;

		controllSetup();
		loadObjects();
	}

	private void controllSetup() {
		// TODO Auto-generated method stub
		keyDown.put("left", false);
		keyDown.put("right", false);
		keyDown.put("down", false);
		keyDown.put("up", false);
		keyDown.put("space", false);
	}

	public void loadObjects() {
		Image img = new ImageIcon(getClass().getResource("/playerImg.png")).getImage();
		player = new Entity(img, 100, 200, 100);

		entityList.add(player);

		for (int i = 0; i < ghosts.length; i++) {
			Image alienImg = new ImageIcon(getClass().getResource("/ghostImg.png")).getImage();

			int alienX = gv.getWidth()
					- ((gv.getWidth() / 2 + alienImg.getWidth(null) / 2) + (100 - alienImg.getWidth(null) / 2));
			int alienY = 0;

			ghosts[i] = new GhostEntity(alienImg, alienX + 40 * i, alienY, 90);
		}

		for (Entity alien : ghosts) {
			entityList.add(alien);
		}
	}

	public void update(long deltaTime) {
		if (keyDown.get("right") && gv.getWidth() > player.getX() + player.getWidth()) {
			player.setDirectionX(1);
			player.setDirectionY(0);
		} else if (keyDown.get("left") && 0 < player.getX()) {
			player.setDirectionX(-1);
			player.setDirectionY(0);
		} else if (keyDown.get("up") && 0 < player.getY()) {
			player.setDirectionY(-1);
			player.setDirectionX(0);
		} else if (keyDown.get("down") && gv.getHeight() > player.getY() + player.getHeight()) {
			player.setDirectionY(1);
			player.setDirectionX(0);
		}

		for (Entity entity : entityList) {

			entity.move(deltaTime);
			if (entity instanceof GhostEntity) {

				if (player.collisionWith(entity)) {
					toRemove.add(entity);
				}
			}
		}

		if (!toRemove.isEmpty()) {
			entityList.removeAll(toRemove);
			toRemove.clear();
		}

		if (entityList.size() < 2) {
			System.exit(0);
		}
	}

	public void render() {
		gv.beginRender();

		gv.openRender(entityList);

		gv.show();
	}

	public void runGame() {
		long renderDelay = 1000000000 / fps;

		long lastUpdateTime = System.nanoTime();

		while (gameRunning) {
			long deltaTime = System.nanoTime() - lastUpdateTime;

			if (deltaTime >= renderDelay) {
				lastUpdateTime = System.nanoTime();
				render();
				update(deltaTime);
			}
		}
	}

	/**
	 * KeyListener
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			keyDown.put("up", true);
		} else if (key == KeyEvent.VK_DOWN) {
			keyDown.put("down", true);
		} else if (key == KeyEvent.VK_LEFT) {
			keyDown.put("left", true);
		} else if (key == KeyEvent.VK_RIGHT) {
			keyDown.put("right", true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			keyDown.put("up", false);
		} else if (key == KeyEvent.VK_DOWN) {
			keyDown.put("down", false);
		} else if (key == KeyEvent.VK_LEFT) {
			keyDown.put("left", false);
		} else if (key == KeyEvent.VK_RIGHT) {
			keyDown.put("right", false);
		}
	}
}
