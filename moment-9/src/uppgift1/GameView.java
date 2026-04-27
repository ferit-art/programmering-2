package uppgift1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import se.egy.graphics.*;

public class GameView /* implements KeyListener */ {

	private boolean gameRunning = true;
	private long lastUpdateTime;
	private int height, width;
	private String title;
	private Canvas canvas;
	private JFrame jf;

	private GameScreen GameScreen = new GameScreen(title, width, height, false);

	private HashMap<String, Boolean> keyDown = new HashMap<>();

	private CopyOnWriteArrayList<Entity> spriteList = new CopyOnWriteArrayList<>();
	private List<Entity> toRemove = new ArrayList<>();

	private ShipEntity ship;
	private AlienEntity[] aliens = new AlienEntity[5];
	private long lastRocketTime;

	public GameView(int width, int height, String title) {

		this.height = height;
		this.width = width;
		this.title = title;

		createWindow();
	}

	public void createWindow() {
		canvas = new Canvas();
		canvas.setSize(new Dimension(width, height));

		jf = new JFrame(title);
		jf.add(canvas);

		jf.setResizable(false);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setIgnoreRepaint(true);
		jf.setVisible(true);

		canvas.requestFocus();
	}

	public void render(Drawable drawObj) {
		Graphics2D g = (Graphics2D) canvas.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		drawObj.draw(g);
	}

	public static void main(String[] args) {
		GameView gv = new GameView(800, 600, "Spel");
		Image shipImg = new ImageIcon(GameView.class.getResource("/ship.png")).getImage();

		ShipEntity ship = new ShipEntity(shipImg, 300, 300, 20);
		
		gv.render(ship);
	}
}
