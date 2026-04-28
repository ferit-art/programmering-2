package se.egy.game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List; // Added List import

import javax.swing.ImageIcon;

import se.egy.game.model.Entity;
import se.egy.game.view.Drawable; // Added Drawable import
import se.egy.game.view.GameView;

public class Test {

	public Test() {
		// Skapar spelfönstret
		GameView gv = new GameView(800, 600, "test"); 

		// Skapar två bilder som ligger i resoursekatalogen
		Image imgPlayer = new ImageIcon(getClass().getResource("/playerImg.png")).getImage();
		Image imgGhost = new ImageIcon(getClass().getResource("/ghostImg.png")).getImage();

		// Skapar två instanser av klassen Entity
		Entity player = new Entity(imgPlayer, 300, 222, 40);
		Entity gost = new Entity(imgGhost, 500, 322, 40);

		// FIX 1: Ändra från ArrayList<Entity> till List<Drawable>
		List<Drawable> entityList = new ArrayList<>();

		entityList.add(player);
		entityList.add(gost);

		// Renderar Frame 1
		gv.beginRender();
		gv.openRender(entityList);
		gv.show(); // FIX 2: Skjut fram den dolda bufferten till skärmen!

		// Liten paus så att man hinner se förflyttning (2 sekunder)
		try {
			Thread.sleep(2000);
		} catch (Exception e) {}

		player.setDirectionX(1);
		gost.setDirectionY(-1);

		// Eftersom player och gost skapades som "Entity" kan vi fortfarande 
		// använda deras rörelsemetoder utan problem!
		player.move(1000000000);
		gost.move(1000000000);

		// Renderar Frame 2 (efter rörelsen)
		gv.beginRender();
		gv.openRender(entityList);
		gv.show(); // Skjut fram den uppdaterade bufferten!
	}

	public static void main(String[] args) {
		new Test();
	}
}