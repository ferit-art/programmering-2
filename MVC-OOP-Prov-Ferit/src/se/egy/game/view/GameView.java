package se.egy.game.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Collection;
import javax.swing.JFrame;

public class GameView /* implements KeyListener */ {

	private BufferStrategy backBuffer;
	private int height;
	private int width;
	private String title;
	private Canvas canvas;
	private JFrame jf;
	private Image background;
	private Graphics2D g;

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
		canvas.createBufferStrategy(2);
		backBuffer = canvas.getBufferStrategy();
		g = (Graphics2D) backBuffer.getDrawGraphics();
	}

	public void beginRender() {
		g = (Graphics2D) backBuffer.getDrawGraphics();

		if (background != null) {
			g.drawImage(background, 0, 0, width, height, null);
		} else {
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
		}
	}

	public void openRender(Drawable obj) {
		obj.draw(g);
	}

	public void openRender(Collection<? extends Drawable> objs) {
		for (Drawable obj : objs) {
			obj.draw(g);
		}
	}

	public void show() {
		g.dispose();
		backBuffer.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public void addKeyListener(KeyListener listener) {
		canvas.addKeyListener(listener);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
