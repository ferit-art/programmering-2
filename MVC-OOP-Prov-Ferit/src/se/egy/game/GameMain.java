package se.egy.game;

import se.egy.game.controller.GameController;
import se.egy.game.view.*;

public class GameMain {
	private GameView gv;
	private GameController gc;
	
	public GameMain() {
		gv = new GameView(800, 600, "Game"); // Sätt till false vid testkörning för enklare debugging
		gc = new GameController(gv, 60);
		
		gc.runGame();
	}

	public static void main(String[] args) {
		new GameMain();
	}
}
