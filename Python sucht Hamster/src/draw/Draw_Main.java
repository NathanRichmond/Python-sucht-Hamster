package draw;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import chars.Enemy;
import chars.Player;
import game.Game;
import game.GameTimer;
import game.Gamestate;
import game.Gamestate_e;
import game.SpecialTile;
import game.SpecialTile_Creation;
import game.Wall;
import game.Wall_Creation;
import gui.Button;
import gui.Grid;
import gui.Gui;

public class Draw_Main {

	private static Player p;

	private static int startmenugridWidth = (int) (Gui.getWidth() / 1.934508816120906801007556675063);
	private static int startmenugridHeight = (int) (Gui.getHeight() / 3.2481203007518796992481203007519);
	private static int startmenugridX = Gui.getWidth() / 2 - startmenugridWidth / 2;
	private static int startmenugridY = 3 * (Gui.getHeight() / 4) - startmenugridHeight / 2;

	public void draw(GraphicsContext gc) {
		p = Game.p;
		GraphicsContext g = gc;

		/*
		 * START MENU SCREEN
		 */
		if (Gamestate.state == Gamestate_e.startmenu) {
			drawStartMenu(g);
		}

		/*
		 * TUTORIAL MENU SCREEN
		 */
		if (Gamestate.state == Gamestate_e.tutorialmenu) {
			drawTutorialMenu(g);
		}

		/*
		 * ANLEITUNGS SCREEN
		 */
		if (Gamestate.state == Gamestate_e.anleitung) {
			drawAnleitung(g);
		}

		/*
		 * INGAME ELEMENTS
		 */
		if (Gamestate.state == Gamestate_e.ingame || Gamestate.state == Gamestate_e.pause
				|| Gamestate.state == Gamestate_e.victory || Gamestate.state == Gamestate_e.defeat) {
			/*
			 * Note: Draw order is important! Example: Special Tiles are drawn before
			 * Characters because Characters should be in front of Special Tiles.
			 */

			/*
			 * BACKGROUND
			 */
			drawBackground(g);

			/*
			 * BACKGROUND IMAGE: Grid
			 */
			drawGrid(g);

			/*
			 * GRID CONTENTS: Special Tiles
			 */
			drawSpecialTile(g);

			/*
			 * GRID CONTENTS: Wall
			 */
			drawWall(g);

			/*
			 * CHARACTERS
			 */
			drawCharacters(g);

			/*
			 * GAME ELEMENTS: GameTimer
			 */
			if (Game.getLevel() != 101 && Game.getLevel() != 102) { // no timer in tut1 & tut2
				drawGameTimer(g);
			}

			// Extrabilder f�r die Tutorials
			if ((Game.getLevel() == 101 || Game.getLevel() == 102) && Game.isBehindTheGame() == false) {
				drawPfeiltasten(g);
			}

			/*
			 * GAME ELEMENTS: Ingame Buttons
			 */
			drawIngameButtons(g);

			/*
			 * GAME ELEMENTS: Level Title
			 */
			if (Game.getLevel() < 100) { // only for regular, not for tutlvls
				drawLvlTitle(g);
			}

			/*
			 * GAME ELEMENTS: Special Tile Activated Info
			 */
			if (Game.isSpecialTiles() == true) {
//				drawSpecialTileInfo(g);
			}

			/*
			 * GAME ELEMENTS: Hamster Count (for Hamsterinflation)
			 */
			if (Game.isHamsterinflation() == true) {
				drawHamstercount(g);
			}

			/*
			 * BEHIND THE GAME
			 */
			if (Game.isBehindTheGame() == true && Game.getLevel() > 100) {
				drawBehindTheGame(g);
			}
		}

		/*
		 * GAME END SCREENS
		 */
		if (Gamestate.state == Gamestate_e.victory || Gamestate.state == Gamestate_e.defeat) {
			drawGameendScreen(g);
		}

		/*
		 * PAUSE SCREEN
		 */
		if (Gamestate.state == Gamestate_e.pause) {
			drawPauseScreen(g);
		}

	}

	private void drawBehindTheGame(GraphicsContext g) {
		drawBehindTheGameGrid(g);
		drawBehindTheGameButton(g);
		if (Game.isWalls() == true) { // if level contains walls
			drawBehindTheGameWall(g);
		}
		if (Game.isSpecialTiles() == true && SpecialTile_Creation.specialtiles.size() > 0) {
			drawBehindTheGameSpecialTile(g);
		}
		if (Game.getLevel() != 101 && Game.getLevel() != 102) { // no timer in tutlvl1 & tutlvl2
			drawBehindTheGameZeitleiste(g);
		}
		drawBehindTheGameHamster(g);
		drawBehindTheGamePython(g);
	}

	private void drawBehindTheGameGrid(GraphicsContext g) {
		int w = (int) (Gui.getWidth() / 6.144);
		int h = (int) (Gui.getHeight() / 9.3913043478260869565217391304348); // size of the box
		int xb = (int) (Gui.getWidth() - (w + (Gui.getWidth() / 30.72)));
		int yb = (int) (Gui.getHeight() / 28.8); // coordinates of box
		int offset = (int) ((Gui.getWidth() * Gui.getHeight()) / 26542.08) / 2; // offset around Grid

		// Box with explanation
		g.setLineWidth(1);
		g.setStroke(new Color(1, 1, 1, 0.4));
		g.strokeRect(xb, yb, w, h);
		g.setFill(new Color(0, 0, 0, 0.4));
		g.fillRect(xb, yb, w, h);
		// text inside of it
		// header
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.TOP);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 36864) / 2));
		g.setFill(Color.WHITE);
		g.fillText("Objekt der Klasse Grid", xb + w / 2, yb + (Gui.getHeight() / 172.8));
		// header divider
		g.setLineWidth(1);
		g.setStroke(Color.WHITE);
		g.strokeLine(xb, yb + (Gui.getHeight() / 32), xb + w, yb + (Gui.getHeight() / 32));
		// content text
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 44236.8) / 2));
		String textleft = "";
		String textright = "";
		int numberOfLines = 3;
		for (int i = 0; i < numberOfLines; i++) {
			switch (i) {
			case 0:
				textleft = "x = " + Grid.getX();
				textright = "Abszisse";
				break;
			case 1:
				textleft = "y = " + Grid.getY();
				textright = "Ordinate";
				break;
			case 2:
				textleft = "size = \"" + Grid.getSize() + "\"";
				textright = "Gr��e";
				break;
			}
			g.setTextAlign(TextAlignment.LEFT);
			g.fillText(textleft, xb + (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
			g.setTextAlign(TextAlignment.RIGHT);
			g.fillText(textright, xb + w - (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));

			// Box around the Grid
			g.setLineWidth(3);
			g.setStroke(new Color(0, 0, 0, 0.2));
			g.strokeRect(Grid.getX() - offset, Grid.getY() - offset, Grid.getWidth() + offset * 2,
					Grid.getHeight() + offset * 2);

			// line between them
			g.setStroke(new Color(0, 0, 0, 0.2));
			g.strokeLine(Grid.getX() + Grid.getWidth() + offset, Grid.getY() + offset, xb, yb + h);
		}
	}

	private void drawBehindTheGameButton(GraphicsContext g) {
		Button b = Gui.ingamebuttons[2]; // restart button
		int w = (int) (Gui.getWidth() / 6.144);
		int h = (int) (Gui.getHeight() / 9.3913043478260869565217391304348); // size of the box
		int xb = (int) (b.getX() + b.getWidth() + (Gui.getWidth() / 5.12)), yb = b.getY(); // coordinates of box
		int offset = (int) ((Gui.getWidth() * Gui.getHeight()) / 132710.4) / 2; // offset around Grid

		// Box with explanation
		g.setLineWidth(1);
		g.setStroke(new Color(1, 1, 1, 0.4));
		g.strokeRect(xb, yb, w, h);
		g.setFill(new Color(0, 0, 0, 0.4));
		g.fillRect(xb, yb, w, h);
		// text inside of it
		// header
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.TOP);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 36864) / 2));
		g.setFill(Color.WHITE);
		g.fillText("Objekt der Klasse Button", xb + w / 2, yb + (Gui.getHeight() / 172.8));
		// header divider
		g.setLineWidth(1);
		g.setStroke(Color.WHITE);
		g.strokeLine(xb, yb + (Gui.getHeight() / 32), xb + w, yb + (Gui.getHeight() / 32));
		// content text
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 44236.8) / 2));
		String textleft = "";
		String textright = "";
		int numberOfLines = 3;
		for (int i = 0; i < numberOfLines; i++) {
			switch (i) {
			case 0:
				textleft = "x = " + b.getX();
				textright = "Abszisse";
				break;
			case 1:
				textleft = "y = " + b.getY();
				textright = "Ordinate";
				break;
			case 2:
				textleft = "isHover = " + b.isHover();
				textright = "";
				break;
			}
			g.setTextAlign(TextAlignment.LEFT);
			g.fillText(textleft, xb + (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
			g.setTextAlign(TextAlignment.RIGHT);
			g.fillText(textright, xb + w - (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));

			// Box around the Button
			g.setLineWidth(3);
			g.setStroke(new Color(0, 0, 0, 0.2));
			g.strokeRect(b.getX() - offset, b.getY() - offset, b.getWidth() + offset * 2, b.getHeight() + offset * 2);

			// line between them
			g.setStroke(new Color(0, 0, 0, 0.2));
			g.strokeLine(b.getX() + b.getWidth() + offset, b.getY() + offset, xb, yb);
		}
	}

	private void drawBehindTheGameZeitleiste(GraphicsContext g) {
		int xz = (int) (Grid.getX() + Grid.getWidth() + (Gui.getWidth() / 30.72)); // coordinates of Zeitleiste
		int yz = (int) (Grid.getY() + Grid.getHeight() / 2 - (Gui.getHeight() / 6.912));
		int wz = (int) (Gui.getWidth() / 20.48), hz = (int) (Gui.getHeight() / 3.456); // size of Zeitleiste
		int w = (int) (Gui.getWidth() / 6.144), h = (int) (Gui.getHeight() / 4.0754716981132075471698113207547);
		int xb = (int) (xz + wz + (Gui.getWidth() / 30.72));
		int yb = yz + hz / 2 - h / 2; // coordinates of box: center vertically with Zeitleiste
		int offset = (int) ((Gui.getWidth() * Gui.getHeight()) / 66355.2) / 2; // offset around Zeitleiste

		// Box with explanation
		g.setLineWidth(1);
		g.setStroke(new Color(1, 1, 1, 0.4));
		g.strokeRect(xb, yb, w, h);
		g.setFill(new Color(0, 0, 0, 0.4));
		g.fillRect(xb, yb, w, h);
		// text inside of it
		// header
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.TOP);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 36864) / 2));
		g.setFill(Color.WHITE);
		g.fillText("Objekt der Klasse GameTimer", xb + w / 2, yb + (Gui.getHeight() / 172.8));
		// header divider
		g.setLineWidth(1);
		g.setStroke(Color.WHITE);
		g.strokeLine(xb, yb + (Gui.getHeight() / 32), xb + w, yb + (Gui.getHeight() / 32));
		// content text
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 44236.8) / 2));
		String textleft = "";
		String textright = "";
		int numberOfLines = 9;
		for (int i = 0; i < numberOfLines; i++) {
			switch (i) {
			case 0:
				textleft = "gameDuration = " + GameTimer.getGameDuration();
				break;
			case 1:
				textleft = "";
				textright = "Zeit in Sekunden, bis";
				break;
			case 2:
				textright = "das Spiel verloren ist";
				break;
			case 3:
				textleft = "gameTime = " + GameTimer.getGameTime();
				textright = "";
				break;
			case 4:
				textleft = "";
				textright = "Aktuell angezeigtes Bild";
				break;
			case 5:
				textleft = "isModified = " + GameTimer.isModified();
				textright = "";
				break;
			case 6:
				textleft = "";
				textright = "Ist �true�, wenn ein";
				break;
			case 7:
				textright = "Special Tile der Art";
				break;
			case 8:
				textright = "�hourglass� aktiviert wird";
				break;
			}
			g.setTextAlign(TextAlignment.LEFT);
			g.fillText(textleft, xb + (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
			g.setTextAlign(TextAlignment.RIGHT);
			g.fillText(textright, xb + w - (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));

			// Box around the Zeitleiste
			g.setLineWidth(3);
			g.setStroke(new Color(0, 0, 0, 0.1));
			g.strokeRect(xz - offset, yz - offset, wz + offset * 2, hz + offset * 2);

			// line between them
			g.setStroke(new Color(0, 0, 0, 0.1));
			g.strokeLine(xz + wz + offset, yz + offset, xb, yb);
		}
	}

	private void drawBehindTheGameSpecialTile(GraphicsContext g) {
		SpecialTile st;
		int w = (int) (Gui.getWidth() / 6.5361702127659574468085106382979);
		int h = (int) (Gui.getHeight() / 7.7142857142857142857142857142857); // size of box
		int offset = (int) ((Gui.getWidth() * Gui.getHeight()) / 132710.4) / 2; // offset around Special Tile
		int xst = 0, yst = 0; // coordinates of one Special Tile
		int xb = (int) (Gui.getWidth() / 3.2);
		int yb = (int) (Gui.getHeight() / 1.1308900523560209424083769633508) - h; // coordinates of box
		int wst = (int) Gui.getTile() - 1, hst = (int) Gui.getTile() - 1; // size of Special Tile
		String type = ""; // type of Special Tile
		for (int i = 0; i < SpecialTile_Creation.specialtiles.size(); i++) {
			st = SpecialTile_Creation.specialtiles.get(i);
			// get st that is closest to box
			if (xb - st.getX() <= xb - xst && yst - st.getY() <= yb - yst) {
				xst = st.getX();
				yst = st.getY();
				wst = st.getWidth();
				hst = st.getHeight();
				type = st.getType();
			} else { // when above isn't possible for any reason, just take the st no matter what
				if (xst == 0) {
					xst = st.getX();
				}
				if (yst == 0) {
					yst = st.getY();
				}
			}
		}

		// Box with explanation
		g.setLineWidth(1);
		g.setStroke(new Color(1, 1, 1, 0.4));
		g.strokeRect(xb, yb, w, h);
		g.setFill(new Color(0, 0, 0, 0.4));
		g.fillRect(xb, yb, w, h);
		// text inside of it
		// header
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.TOP);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 36864) / 2));
		g.setFill(Color.WHITE);
		g.fillText("Objekt der Klasse SpecialTile", xb + w / 2, yb + (Gui.getHeight() / 172.8));
		// header divider
		g.setLineWidth(1);
		g.setStroke(Color.WHITE);
		g.strokeLine(xb, yb + (Gui.getHeight() / 32), xb + w, yb + (Gui.getHeight() / 32));
		// content text
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 44236.8) / 2));
		String textleft = "";
		String textright = "";
		int numberOfLines = 4;
		for (int i = 0; i < numberOfLines; i++) {
			switch (i) {
			case 0:
				textleft = "x = " + xst;
				textright = "Abszisse";
				break;
			case 1:
				textleft = "y = " + yst;
				textright = "Ordinate";
				break;
			case 2:
				textleft = "type = " + type;
				textright = "Art";
				break;
			case 3:
				textleft = "isAlive = true";
				textright = "";
				break;
			}
			g.setTextAlign(TextAlignment.LEFT);
			g.fillText(textleft, xb + (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
			g.setTextAlign(TextAlignment.RIGHT);
			g.fillText(textright, xb + w - (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
		}

		// Box around the Special Tile
		g.setLineWidth(3);
		g.setStroke(new Color(0, 0, 0, 0.4));
		g.strokeRect(xst - offset, yst - offset, wst + offset * 2, hst + offset * 2);

		// line between them
		g.setStroke(new Color(0, 0, 0, 0.4));
		g.strokeLine(xst - offset, yst + wst / 2, xb + w, yb);
	}

	private void drawBehindTheGameWall(GraphicsContext g) {
		Wall w;
		int xw = 0, yw = 0; // coordinates of one Wall
		int width = (int) (Gui.getWidth() / 7.68), height = (Gui.getHeight() / 12); // size of box
		int xb = (int) (Gui.getWidth() / 2.0617449664429530201342281879195);
		int yb = (int) (Gui.getHeight() / 1.1308900523560209424083769633508) - height; // coordinates of box
		int ww = Gui.getTile() - 1, hw = Gui.getTile() - 1; // size of Wall
		int offset = (int) ((Gui.getWidth() * Gui.getHeight()) / 132710.4) / 2; // offset around Wall
		for (int i = 0; i < Wall_Creation.walls.size(); i++) {
			w = Wall_Creation.walls.get(i);
			// get wall that is closest to box
			if (xb - w.getX() <= xb - xw && yw - w.getY() <= yb - yw) {
				xw = w.getX();
				yw = w.getY();
				ww = w.getWidth();
				hw = w.getHeight();
			} else { // when above isn't possible for any reason, just take the wall no matter what
				if (xw == 0) {
					xw = w.getX();
				}
				if (yw == 0) {
					yw = w.getY();
				}
			}
		}

		// Box with explanation
		g.setLineWidth(1);
		g.setStroke(new Color(1, 1, 1, 0.4));
		g.strokeRect(xb, yb, width, height);
		g.setFill(new Color(0, 0, 0, 0.4));
		g.fillRect(xb, yb, width, height);
		// text inside of it
		// header
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.TOP);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 36864) / 2));
		g.setFill(Color.WHITE);
		g.fillText("Objekt der Klasse Wall", xb + width / 2, yb + (Gui.getHeight() / 172.8));
		// header divider
		g.setStroke(Color.WHITE);
		g.setLineWidth(1);
		g.strokeLine(xb, yb + (Gui.getHeight() / 32), xb + width, yb + (Gui.getHeight() / 32));
		// content text
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 44236.8) / 2));
		String textleft = "";
		String textright = "";
		int numberOfLines = 2;
		for (int i = 0; i < numberOfLines; i++) {
			switch (i) {
			case 0:
				textleft = "x = " + xw;
				textright = "Abszisse";
				break;
			case 1:
				textleft = "y = " + yw;
				textright = "Ordinate";
				break;
			}
			g.setTextAlign(TextAlignment.LEFT);
			g.fillText(textleft, xb + (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
			g.setTextAlign(TextAlignment.RIGHT);
			g.fillText(textright, xb + width - 4, yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
		}

		// Box around the Wall
		g.setLineWidth(3);
		g.setStroke(new Color(0, 0, 0, 0.4));
		g.strokeRect(xw - offset, yw - offset, ww + offset * 2, hw + offset * 2);

		// line between them
		g.setStroke(new Color(0, 0, 0, 0.4));
		g.strokeLine(xw + ww / 2, yw + hw + offset, xb + width, yb);
	}

	private void drawBehindTheGameHamster(GraphicsContext g) {
		if (Game.enemies.size() > 0) {
			Enemy e = Game.enemies.get(0); // first Enemy in the array
			int xe = e.getX(), ye = e.getY(); // coordinates of enemy
			int xb = (int) (Gui.getWidth() / 30.72), yb = (int) (Gui.getHeight() / 2); // coordinates of box
			int w = (int) (Gui.getWidth() / 3.84), h = (int) (Gui.getHeight() / 2.6024096385542168674698795180723);
			int offset = (int) ((Gui.getWidth() * Gui.getHeight()) / 132710.4) / 2; // offset around Hamster

			// Box on the left with explanation
			g.setLineWidth(1);
			g.setStroke(new Color(1, 1, 1, 0.4));
			g.strokeRect(xb, yb, w, h);
			g.setFill(new Color(0, 0, 0, 0.4));
			g.fillRect(xb, yb, w, h);
			// text inside of it
			// header
			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.TOP);
			g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 36864) / 2));
			g.setFill(Color.WHITE);
			g.fillText("Objekt der Klasse Enemy", xb + w / 2, yb + (Gui.getHeight() / 172.8));
			// header divider
			g.setLineWidth(1);
			g.setStroke(Color.WHITE);
			g.strokeLine(xb, yb + (Gui.getHeight() / 32), xb + w, yb + (Gui.getHeight() / 32));
			// content text
			g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 44236.8) / 2));
			String textleft = "";
			String textright = "";
			int numberOfLines = 15;
			for (int i = 0; i < numberOfLines; i++) {
				switch (i) {
				case 0:
					textleft = "x = " + e.getX();
					textright = "Abszisse";
					break;
				case 1:
					textleft = "y = " + e.getY();
					textright = "Ordinate";
					break;
				case 2:
					textleft = "faceDirection = " + e.getFaceDirection();
					textright = "Blickrichtung";
					break;
				case 3:
					textleft = "speed = " + e.getSpeed();
					textright = "Geschwindigkeit";
					break;
				case 4:
					textleft = "isSpeedBoosted = " + e.isSpeedBoosted();
					textright = "";
					break;
				case 5:
					textleft = "isAlive = " + e.isAlive();
					textright = "";
					break;
				case 6:
					textleft = "";
					textright = "";
					break;
				case 7:
					textleft = "turn(int direction)";
					break;
				case 8:
					textleft = "";
					textright = "�ndert faceDirection";
					break;
				case 9:
					textright = "abh�ngig von direction";
					break;
				case 10:
					textleft = "move(int direction)";
					textright = "";
					break;
				case 11:
					textleft = "";
					textright = "�ndert x und y abh�ngig";
					break;
				case 12:
					textright = "von faceDirection um 33";
					break;
				case 13:
					textleft = "killEnemy()";
					textright = "";
					break;
				case 14:
					textleft = "";
					textright = "Setzt isAlive auf false";
					break;
				}
				g.setTextAlign(TextAlignment.LEFT);
				g.fillText(textleft, xb + (Gui.getWidth() / 384),
						yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
				g.setTextAlign(TextAlignment.RIGHT);
				g.fillText(textright, xb + w - (Gui.getWidth() / 384),
						yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
			}

			// Box around the Hamster
			g.setLineWidth(3);
			g.setStroke(new Color(0, 0, 0, 0.4));
			g.strokeRect(xe - offset, ye - offset, e.getWidth() + offset * 2, e.getHeight() + offset * 2);

			// line between them
			g.setStroke(new Color(0, 0, 0, 0.4));
			g.strokeLine(xe - offset, ye + e.getWidth() / 2, xb + w, yb);
		}
	}

	private void drawBehindTheGamePython(GraphicsContext g) {
		int xp = p.getX(), yp = p.getY(); // coordinates of player
		int xb = (int) (Gui.getWidth() / 30.72);
		int yb = (int) (Gui.getHeight() / 5.0823529411764705882352941176471); // coordinates of box
		int w = (int) (Gui.getWidth() / 3.84), h = (int) (Gui.getHeight() / 3.7241379310344827586206896551724);
		int offset = (int) ((Gui.getWidth() * Gui.getHeight()) / 132710.4) / 2; // offset around Python

		// Box on the left with explanation
		g.setLineWidth(1);
		g.setStroke(new Color(1, 1, 1, 0.4));
		g.strokeRect(xb, yb, w, h);
		g.setFill(new Color(0, 0, 0, 0.4));
		g.fillRect(xb, yb, w, h);
		// text inside of it
		// header
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.TOP);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 36864) / 2));
		g.setFill(Color.WHITE);
		g.fillText("Objekt der Klasse Player", xb + w / 2, yb + (Gui.getHeight() / 172.8));
		// header divider
		g.setLineWidth(0.5);
		g.setStroke(Color.WHITE);
		g.strokeLine(xb, yb + (Gui.getHeight() / 32), xb + w, yb + (Gui.getHeight() / 32));
		// content text
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 44236.8) / 2));
		String textleft = "";
		String textright = "";
		int numberOfLines = 10;
		for (int i = 0; i < numberOfLines; i++) {
			switch (i) {
			case 0:
				textleft = "x = " + p.getX();
				textright = "Abszisse";
				break;
			case 1:
				textleft = "y = " + p.getY();
				textright = "Ordinate";
				break;
			case 2:
				textleft = "faceDirection = " + p.getFaceDirection();
				textright = "Blickr.";
				break;
			case 3:
				textleft = "";
				textright = "";
				break;
			case 4:
				textleft = "turn(int direction)";
				break;
			case 5:
				textleft = "";
				textright = "�ndert faceDirection";
				break;
			case 6:
				textright = "abh�ngig von direction";
				break;
			case 7:
				textleft = "move(int direction)";
				textright = "";
				break;
			case 8:
				textleft = "";
				textright = "�ndert x und y abh�ngig";
				break;
			case 9:
				textright = "von faceDirection um 33";
				break;
			}
			g.setTextAlign(TextAlignment.LEFT);
			g.fillText(textleft, xb + (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
			g.setTextAlign(TextAlignment.RIGHT);
			g.fillText(textright, xb + w - (Gui.getWidth() / 384),
					yb + (Gui.getHeight() / 28.8) + i * (Gui.getHeight() / 43.2));
		}

		// Box around the Python
		g.setLineWidth(3);
		g.setStroke(new Color(0, 0, 0, 0.4));
		g.strokeRect(xp - offset, yp - offset, p.getWidth() + offset * 2, p.getHeight() + offset * 2);

		// line between them
		g.setStroke(new Color(0, 0, 0, 0.4));
		g.strokeLine(xp + p.getWidth() / 2, yp - offset, xb + w, yb);
	}

	@SuppressWarnings("unused")
	private void drawSpecialTileInfo(GraphicsContext g) {
		// Enemy speed is boosted
		int x = Grid.getX() - (int) (Gui.getWidth() / 6.4), y = Grid.getY();
		int width = (int) (Gui.getWidth() / 6.4), height = (int) (Gui.getHeight() / 43.2);
		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if (e.isSpeedBoosted() == true) {
				g.setFill(new Color(0, 0, 0, 0.4));
				g.fillRect(x, y + i * 2 * height, width, height);
				g.setTextAlign(TextAlignment.CENTER);
				g.setTextBaseline(VPos.CENTER);
				g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 41472) / 2));
				g.setFill(Color.WHITE);
				g.fillText("Hamstergeschwindigkeit erh�ht!", x + width / 2, (y + i * 2 * height) + height / 2);
			}
		}

		// Time is modified

		if (GameTimer.isModified() == true) {
			y = (int) (Grid.getY() + 2 * (Gui.getHeight() / 43.2));
			width = (int) (Gui.getWidth() / 9.6);
			g.setFill(new Color(0, 0, 0, 0.4));
			g.fillRect(x, y, width, height);

			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 41472) / 2));
			g.setFill(Color.WHITE);
			g.fillText("Spielzeit modifiziert!", x + width / 2, y + height / 2);
		}
	}

	private void drawHamstercount(GraphicsContext g) {
		int x = (int) (Grid.getX() - (Gui.getWidth() / 6.4));
		int y = Grid.getY();
		int width = (int) (Gui.getWidth() / 7.68);
		int height = (int) (Gui.getHeight() / 21.6);
		g.setFill(new Color(0, 0, 0, 0.4));
		g.fillRect(x, y, width, height);

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 41472) / 2));
		g.setFill(Color.WHITE);
		g.fillText("Hamster am Leben: " + Game.enemies.size(), x + width / 2, y + height / 4);
		g.fillText("Hamster verschlungen: " + Game.hamstercount, x + width / 2, y + 3 * height / 4);
	}

	private void drawLvlTitle(GraphicsContext g) {
		int width = (int) (Gui.getWidth() / 10.24); // width of the lvl title box
		int height = (int) (Gui.getHeight() / 12.342857142857142857142857142857); // height of the lvl title box
		int x = Grid.getX() + Grid.getWidth() / 2 - width / 2; // x coordinate of the lvl title box
		int y = Grid.getY() / 2 - height / 2; // y coordinate of the lvl title box

		switch (Game.getLevel()) {
		case 1:
			g.drawImage(IL.ilvltitle1, x, y, width, height);
			break;
		case 2:
			g.drawImage(IL.ilvltitle2, x, y, width, height);
			break;
		case 3:
			g.drawImage(IL.ilvltitle3, x, y, width, height);
			break;
		case 4:
			g.drawImage(IL.ilvltitle4, x, y, width, height);
			break;
		case 5:
			g.drawImage(IL.ilvltitle5, x, y, width, height);
			break;
		case 6:
			g.drawImage(IL.ilvltitle6, x, y, width, height);
			break;
		case 7:
			g.drawImage(IL.ilvltitle7, x, y, width, height);
			break;
		case 8:
			g.drawImage(IL.ilvltitle8, x, y, width, height);
			break;
		case 9:
			g.drawImage(IL.ilvltitle9, x, y, width, height);
			break;
		case 10:
			g.drawImage(IL.ilvltitle10, x, y, width, height);
			break;
		case 11:
			g.drawImage(IL.ilvltitle11, x, y, width, height);
			break;
		case 12:
			g.drawImage(IL.ilvltitle12, x, y, width, height);
			break;
		default:
			g.setFill(new Color(0, 0, 0, 0.4));
			g.fillRect(Grid.getX() + Grid.getWidth() / 2 - (Gui.getWidth() / 9.6) / 2,
					Grid.getY() / 2 - (Gui.getHeight() / 21.6) / 2, (Gui.getWidth() / 9.6), (Gui.getHeight() / 21.6));

			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Constantia Bold Italic", ((Gui.getWidth() * Gui.getHeight()) / 22118.4) / 2));
			g.setFill(Color.WHITE);
			g.fillText("LEVEL " + Game.getLevel(), Grid.getX() + Grid.getWidth() / 2, Grid.getY() / 2);
			break;
		}
	}

	private void drawIngameButtons(GraphicsContext g) {
		for (int i = 0; i < Gui.ingamebuttons.length; i++) {
			Button b = Gui.ingamebuttons[i];

			/*
			 * Button border
			 */
			g.setStroke(Color.WHITE);
			g.setLineWidth(1);
			if (i == 1) { // for pause Button: only when ingame/pause
				if (Gamestate.state != Gamestate_e.ingame && Gamestate.state != Gamestate_e.pause) {
					g.setStroke(new Color(0, 0, 0, 0.25)); // dark gray border
				}
			}
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			/*
			 * Button background
			 */
			g.setFill(new Color(1, 1, 1, 0.8));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			/*
			 * Button hover background
			 */
			if (b.isHover()) {
				g.setFill(new Color(0, 0, 0, 0.2));
				if (i == 1) { // for pause Button: only when ingame/pause
					if (Gamestate.state == Gamestate_e.ingame || Gamestate.state == Gamestate_e.pause) {
						g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
					}
				} else {
					g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
				}
			}

			/*
			 * Button image
			 */
			switch (i) {
			case 0:
				g.drawImage(IL.ibexit, b.getX(), b.getY(), b.getWidth(), b.getHeight());
				if (Gamestate.state == Gamestate_e.victory && Game.getLevel() == 108) { // last Tutorial level
					// draw box with text "zur�ck zum Men�" underneath the button
					int x = b.getX() + 1 * b.getHeight() / 6; // x coordinate of box
					int y = b.getY() + b.getHeight() + 1 * b.getHeight() / 6; // y coordinate of box
					int w = 240; // width of box
					int h = 2 * b.getHeight() / 3; // height of box
					g.setStroke(Color.WHITE);
					g.setLineWidth(1);
					g.strokeRect(x, y, w, h);
					g.setFill(new Color(0, 0, 0, 0.2));
					g.fillRect(x, y, w, h);
					g.setTextAlign(TextAlignment.CENTER);
					g.setTextBaseline(VPos.CENTER);
					g.setFont(new Font("Constantia",
							((Gui.getWidth() * Gui.getHeight()) / 25521.230769230769230769230769231) / 2));
					g.setFill(Color.WHITE);
					g.fillText("zur�ck zum Men�", x + w / 2, y + h / 2);
				}
				break;
			case 1:
				switch (Gamestate.state) {
				case ingame:
					g.drawImage(IL.ibpause, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case pause:
					g.drawImage(IL.ibplay, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				default:
					g.drawImage(IL.ibpause, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					g.setFill(new Color(0, 0, 0, 0.4)); // dark layer over the button
					g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				}
				break;
			case 2:
				g.drawImage(IL.ibrestart, b.getX(), b.getY(), b.getWidth(), b.getHeight());
				break;
			}
		}
	}

	private void drawLevelSelection(GraphicsContext g) {
		g.setFill(new Color(1, 1, 1, 0.7)); // semi-transparent layer underneath the grid
		g.fillRect(startmenugridX, startmenugridY, startmenugridWidth, startmenugridHeight);

		g.drawImage(IL.igrid_12x04_large, startmenugridX, startmenugridY, startmenugridWidth, startmenugridHeight);

		for (int i = 0; i < Gui.lvlselectbuttons.length; i++) {
			Button b = Gui.lvlselectbuttons[i];
			if (Game.getMaxLevelAvailable() < i + 1) { // if level is still unavailable
				/*
				 * Draw Wall image. Slight coordinate adjustments due to Wall image already
				 * having a transparent 1px border
				 */
				g.drawImage(IL.iwalllarge, b.getX() - 1, b.getY() - 1, b.getWidth() + 2, b.getHeight() + 2);

			} else { // if level is available
				switch (i + 1) { // draw respective Korn image
				case 1:
					g.drawImage(IL.ikorn1, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 2:
					g.drawImage(IL.ikorn2, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 3:
					g.drawImage(IL.ikorn3, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 4:
					g.drawImage(IL.ikorn4, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 5:
					g.drawImage(IL.ikorn5, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 6:
					g.drawImage(IL.ikorn6, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 7:
					g.drawImage(IL.ikorn7, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 8:
					g.drawImage(IL.ikorn8, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 9:
					g.drawImage(IL.ikorn9, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 10:
					g.drawImage(IL.ikorn10, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 11:
					g.drawImage(IL.ikorn11, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 12:
					g.drawImage(IL.ikorn12, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				}
				if (b.isHover()) {
					g.setFill(new Color(0, 0, 0, 0.2));
					g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

					drawLvlDesc(g, (i + 1));
				}
			}
		}
	}

	private void drawLvlDesc(GraphicsContext g, int level) {
		int x = getStartmenugridX();
		int y = (int) (getStartmenugridY() - (Gui.getHeight() / 9.6));
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 22118.4) / 2));
		g.setFill(Color.WHITE);
		g.setTextBaseline(VPos.TOP);
		switch (level) {
		default:
			g.fillText("Level " + level, x, y);
			break;
		case 1:
			g.fillText("Level " + level + ": Die Jagd beginnt...", x, y);
			break;
		case 2:
			g.fillText("Level " + level + ": Um Mauern schl�ngeln", x, y);
			break;
		case 3:
			g.fillText("Level " + level + ": Wie eine Fliege", x, y);
			break;
		case 4:
			g.fillText("Level " + level + ": Im Labyrinth", x, y);
			break;
		case 5:
			g.fillText("Level " + level + ": Chronismus", x, y);
			break;
		case 6:
			g.fillText("Level " + level + ": Im Silo", x, y);
			break;
		case 7:
			g.fillText("Level " + level + ": Familientreffen", x, y);
			break;
		case 8:
			g.fillText("Level " + level + ": Zugemauert", x, y);
			break;
		case 9:
			g.fillText("Level " + level + ": Das volle Programm", x, y);
			break;
		case 10:
			g.fillText("Level " + level + ": Baustelle", x, y);
			break;
		case 11:
			g.fillText("Level " + level + ": Im Hamsternest", x, y);
			break;
		case 12:
			g.fillText("Level " + level + ": Hamsterinflation", x, y);
			break;
		}

	}

	private void drawBackground(GraphicsContext g) {
		/*
		 * Getreidefeld background:
		 */
		g.drawImage(IL.bgfeld, 0, 0, Gui.getWidth(), Gui.getHeight());

		g.setFill(new Color(1, 1, 1, 0.1)); // semi-transparent layer to moderate the intense corn field
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		/*
		 * Plain gray background:
		 */
//		g.setFill(Color.web("#F0F4F6"));
//		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());
	}

	private void drawPauseScreen(GraphicsContext g) {
		g.setFill(new Color(0, 0, 0, 0.5));
		g.fillRect(Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Constantia Bold", ((Gui.getWidth() * Gui.getHeight()) / 16588.8) / 2));
		g.setFill(Color.WHITE);
		g.fillText("PAUSE", Grid.getX() + Grid.getWidth() / 2, Grid.getY() + Grid.getHeight() / 4);
	}

	private void drawGameendScreen(GraphicsContext g) {
		// dark layer over grid
		g.setFill(new Color(0, 0, 0, 0.7));
		g.fillRect(Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());

		int w = (int) (Gui.getWidth() / 9.2530120481927710843373493975904); // width of rectangle
		int h = (int) (Gui.getHeight() / 5.2048192771084337349397590361446); // height of rectangle
		int threshold = (int) (Gui.getHeight() / 5.2048192771084337349397590361446); // if space is lower than this, no
																						// GIF
		if (Grid.getHeight() <= threshold) { // center text when there's no space for GIF
			h = 0;
		}
		// position of rectangle: centered on grid
		int x = Grid.getX() + (Grid.getWidth() / 2 - w / 2);
		int y = Grid.getY() + (Grid.getHeight() / 2 - h / 2);

		g.setFill(Color.WHITE);
		switch (Gamestate.state) {
		case defeat:
			g.setTextAlign(TextAlignment.CENTER);
			if (Grid.getHeight() > threshold) {
				g.drawImage(IL.idefeat, x + w / 4, y + h / 6, w / 2, h / 2);
				g.setTextBaseline(VPos.TOP);
				g.setFont(new Font("Constantia",
						((Gui.getWidth() * Gui.getHeight()) / 30161.454545454545454545454545455) / 2));
				g.fillText("Brr! Du bist\nerfroren.", x + w / 2, y + 3 * h / 4);
			} else { // center text when there's little space
				g.setTextBaseline(VPos.CENTER);
			}
			g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 20736) / 2));
			g.fillText("NIEDERLAGE!", x + w / 2, y);
			break;
		case victory:
			g.setTextAlign(TextAlignment.CENTER);
			if (Grid.getHeight() > threshold) {
				g.drawImage(IL.ivictory, x + w / 4, y + h / 6, w / 2, h / 2);
				g.setTextBaseline(VPos.TOP);
				g.setFont(new Font("Constantia",
						((Gui.getWidth() * Gui.getHeight()) / 30161.454545454545454545454545455) / 2));
				g.fillText("Hamster platt,\nPython satt.", x + w / 2, y + 3 * h / 4);
			} else {
				g.setTextBaseline(VPos.CENTER);
			}
			g.setFont(new Font("Constantia",
					((Gui.getWidth() * Gui.getHeight()) / 15080.727272727272727272727272727) / 2));
			if (Grid.getHeight() <= (Gui.getHeight() / 18.78260869565217391304347826087)) {
				g.setFont(new Font("Constantia", Grid.getHeight() - 2));
			}
			g.fillText("SIEG!", x + w / 2, y);

			drawVictoryButton(g); // Button: "Next Level"
			break;
		default:
			break;
		}
	}

	private void drawVictoryButton(GraphicsContext g) {
		Button b = Gui.victorybutton;
		g.setStroke(Color.WHITE);
		g.setLineWidth(1);
		if (Game.getLevel() + 1 <= Gui.getnLvls() || (Game.getLevel() > 100 && Game.getLevel() < 108)) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			g.setFill(new Color(1, 1, 1, 0.8));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			drawButtonHover(g, b, null);
			g.drawImage(IL.ibnext, b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		if (Game.getLevel() > 100 && Game.getLevel() < 108) { // if victory is in a Tutorial level
			// draw box with text "zum n�chsten Level" underneath the button
			int x = b.getX() + 1 * b.getHeight() / 6; // x coordinate of box
			int y = b.getY() + b.getHeight() + 1 * b.getHeight() / 6; // y coordinate of box
			int w = 270; // width of box
			int h = 2 * b.getHeight() / 3; // height of box
			g.strokeRect(x, y, w, h);
			g.setFill(new Color(0, 0, 0, 0.2));
			g.fillRect(x, y, w, h);
			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Constantia",
					((Gui.getWidth() * Gui.getHeight()) / 25521.230769230769230769230769231) / 2));
			g.setFill(Color.WHITE);
			g.fillText("zum n�chsten Tutorial", x + w / 2, y + h / 2);
		}
	}

	private void drawGameTimer(GraphicsContext g) {
		/*
		 * Long method; collapse it!
		 */
		int x = (int) (Grid.getX() + Grid.getWidth() + (Gui.getWidth() / 30.72)); // is vertically centered with grid
		int y = (int) (Grid.getY() + Grid.getHeight() / 2 - (Gui.getHeight() / 6.912));
		int w = (int) (Gui.getWidth() / 20.48);
		int h = (int) (Gui.getHeight() / 3.456);

		switch (GameTimer.getGameTime()) {
		case 1:
			g.drawImage(IL.igametimer01, x, y, w, h);
			break;
		case 2:
			g.drawImage(IL.igametimer02, x, y, w, h);
			break;
		case 3:
			g.drawImage(IL.igametimer03, x, y, w, h);
			break;
		case 4:
			g.drawImage(IL.igametimer04, x, y, w, h);
			break;
		case 5:
			g.drawImage(IL.igametimer05, x, y, w, h);
			break;
		case 6:
			g.drawImage(IL.igametimer06, x, y, w, h);
			break;
		case 7:
			g.drawImage(IL.igametimer07, x, y, w, h);
			break;
		case 8:
			g.drawImage(IL.igametimer08, x, y, w, h);
			break;
		case 9:
			g.drawImage(IL.igametimer09, x, y, w, h);
			break;
		case 10:
			g.drawImage(IL.igametimer10, x, y, w, h);
			break;
		case 11:
			g.drawImage(IL.igametimer11, x, y, w, h);
			break;
		case 12:
			g.drawImage(IL.igametimer12, x, y, w, h);
			break;
		case 13:
			g.drawImage(IL.igametimer13, x, y, w, h);
			break;
		case 14:
			g.drawImage(IL.igametimer14, x, y, w, h);
			break;
		case 15:
			g.drawImage(IL.igametimer15, x, y, w, h);
			break;
		case 16:
			g.drawImage(IL.igametimer16, x, y, w, h);
			break;
		case 17:
			g.drawImage(IL.igametimer17, x, y, w, h);
			break;
		case 18:
			g.drawImage(IL.igametimer18, x, y, w, h);
			break;
		case 19:
			g.drawImage(IL.igametimer19, x, y, w, h);
			break;
		case 20:
			g.drawImage(IL.igametimer20, x, y, w, h);
			break;
		case 21:
			g.drawImage(IL.igametimer21, x, y, w, h);
			break;
		case 22:
			g.drawImage(IL.igametimer22, x, y, w, h);
			break;
		case 23:
			g.drawImage(IL.igametimer23, x, y, w, h);
			break;
		case 24:
			g.drawImage(IL.igametimer24, x, y, w, h);
			break;
		case 25:
			g.drawImage(IL.igametimer25, x, y, w, h);
			break;
		case 26:
			g.drawImage(IL.igametimer26, x, y, w, h);
			break;
		case 27:
			g.drawImage(IL.igametimer27, x, y, w, h);
			break;
		case 28:
			g.drawImage(IL.igametimer28, x, y, w, h);
			break;
		case 29:
			g.drawImage(IL.igametimer29, x, y, w, h);
			break;
		case 30:
			g.drawImage(IL.igametimer30, x, y, w, h);
			break;
		case 31:
			g.drawImage(IL.igametimer31, x, y, w, h);
			break;
		case 32:
			g.drawImage(IL.igametimer32, x, y, w, h);
			break;
		case 33:
			g.drawImage(IL.igametimer33, x, y, w, h);
			break;
		case 34:
			g.drawImage(IL.igametimer34, x, y, w, h);
			break;
		case 35:
			g.drawImage(IL.igametimer35, x, y, w, h);
			break;
		case 36:
			g.drawImage(IL.igametimer36, x, y, w, h);
			break;
		case 37:
			g.drawImage(IL.igametimer37, x, y, w, h);
			break;
		case 38:
			g.drawImage(IL.igametimer38, x, y, w, h);
			break;
		case 39:
			g.drawImage(IL.igametimer39, x, y, w, h);
			break;
		case 40:
			g.drawImage(IL.igametimer40, x, y, w, h);
			break;
		case 41:
			g.drawImage(IL.igametimer41, x, y, w, h);
			break;
		case 42:
			g.drawImage(IL.igametimer42, x, y, w, h);
			break;
		case 43:
			g.drawImage(IL.igametimer43, x, y, w, h);
			break;
		case 44:
			g.drawImage(IL.igametimer44, x, y, w, h);
			break;
		case 45:
			g.drawImage(IL.igametimer45, x, y, w, h);
			break;
		case 46:
			g.drawImage(IL.igametimer46, x, y, w, h);
			break;
		case 47:
			g.drawImage(IL.igametimer47, x, y, w, h);
			break;
		case 48:
			g.drawImage(IL.igametimer48, x, y, w, h);
			break;
		case 49:
			g.drawImage(IL.igametimer49, x, y, w, h);
			break;
		case 50:
			g.drawImage(IL.igametimer50, x, y, w, h);
			break;
		case 51:
			g.drawImage(IL.igametimer51, x, y, w, h);
			break;
		case 52:
			g.drawImage(IL.igametimer52, x, y, w, h);
			break;
		case 53:
			g.drawImage(IL.igametimer53, x, y, w, h);
			break;
		case 54:
			g.drawImage(IL.igametimer54, x, y, w, h);
			break;
		case 55:
			g.drawImage(IL.igametimer55, x, y, w, h);
			break;
		case 56:
			g.drawImage(IL.igametimer56, x, y, w, h);
			break;
		case 57:
			g.drawImage(IL.igametimer57, x, y, w, h);
			break;
		case 58:
			g.drawImage(IL.igametimer58, x, y, w, h);
			break;
		case 59:
			g.drawImage(IL.igametimer59, x, y, w, h);
			break;
		case 60:
			g.drawImage(IL.igametimer60, x, y, w, h);
			break;
		case 61:
			g.drawImage(IL.igametimer61, x, y, w, h);
			break;
		case 62:
			g.drawImage(IL.igametimer62, x, y, w, h);
			break;
		case 63:
			g.drawImage(IL.igametimer63, x, y, w, h);
			break;
		case 64:
			g.drawImage(IL.igametimer64, x, y, w, h);
			break;
		case 65:
			g.drawImage(IL.igametimer65, x, y, w, h);
			break;
		case 66:
			g.drawImage(IL.igametimer66, x, y, w, h);
			break;
		case 67:
			g.drawImage(IL.igametimer67, x, y, w, h);
			break;
		case 68:
			g.drawImage(IL.igametimer68, x, y, w, h);
			break;
		case 69:
			g.drawImage(IL.igametimer69, x, y, w, h);
			break;
		case 70:
			g.drawImage(IL.igametimer70, x, y, w, h);
			break;
		case 71:
			g.drawImage(IL.igametimer71, x, y, w, h);
			break;
		case 72:
			g.drawImage(IL.igametimer72, x, y, w, h);
			break;
		case 73:
			g.drawImage(IL.igametimer73, x, y, w, h);
			break;
		case 74:
			g.drawImage(IL.igametimer74, x, y, w, h);
			break;
		case 75:
			g.drawImage(IL.igametimer75, x, y, w, h);
			break;
		case 76:
			g.drawImage(IL.igametimer76, x, y, w, h);
			break;
		case 77:
			g.drawImage(IL.igametimer77, x, y, w, h);
			break;
		case 78:
			g.drawImage(IL.igametimer78, x, y, w, h);
			break;
		case 79:
			g.drawImage(IL.igametimer79, x, y, w, h);
			break;
		case 80:
			g.drawImage(IL.igametimer80, x, y, w, h);
			break;
		case 81:
			g.drawImage(IL.igametimer81, x, y, w, h);
			break;
		case 82:
			g.drawImage(IL.igametimer82, x, y, w, h);
			break;
		case 83:
			g.drawImage(IL.igametimer83, x, y, w, h);
			break;
		case 84:
			g.drawImage(IL.igametimer84, x, y, w, h);
			break;
		case 85:
			g.drawImage(IL.igametimer85, x, y, w, h);
			break;
		case 86:
			g.drawImage(IL.igametimer86, x, y, w, h);
			break;
		case 87:
			g.drawImage(IL.igametimer87, x, y, w, h);
			break;
		case 88:
			g.drawImage(IL.igametimer88, x, y, w, h);
			break;
		case 89:
			g.drawImage(IL.igametimer89, x, y, w, h);
			break;
		}

		/*
		 * Attempt to do it with an Array, didn't work:
		 */
//		g.drawImage(IL.igametimer[GameTimer.getGameTime()], Grid.getX() + Grid.getWidth() + 50,
//				Grid.getY() + Grid.getHeight() / 2 - 125, 75, 250); // is vertically centered with grid
	}

	private void drawCharacters(GraphicsContext g) {
		drawPlayer(g);
		drawEnemy(g);
	}

	private void drawEnemy(GraphicsContext g) {
		for (Enemy e : Game.enemies) {

			if (e.isAlive() == true) {
				switch (e.getFaceDirection()) {
				case 0:
					switch (Gui.getHamsterSkin()) {
					case 0:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy0u_0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy0_0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					case 1:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy1u_0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy1_0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					case 2:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy2u_0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy2_0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					}
					break;
				case 1:
					switch (Gui.getHamsterSkin()) {
					case 0:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy0u_1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy0_1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					case 1:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy1u_1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy1_1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					case 2:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy2u_1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy2_1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					}
					break;
				case 2:
					switch (Gui.getHamsterSkin()) {
					case 0:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy0u_2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy0_2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					case 1:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy1u_2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy1_2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					case 2:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy2u_2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy2_2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					}
					break;
				case 3:
					switch (Gui.getHamsterSkin()) {
					case 0:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy0u_3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy0_3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					case 1:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy1u_3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy1_3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					case 2:
						if (e.isSpeedBoosted() == true) {
							g.drawImage(IL.ienemy2u_3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						} else {
							g.drawImage(IL.ienemy2_3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
						}
						break;
					}
					break;
				default:
					g.drawImage(IL.ienemy0_1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					break;
				}
			} else {

			}
			/*
			 * Draw index of Enemy
			 */
//			g.setFill(new Color(0, 0, 0, 0.4));
//			g.fillRect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
//
//			g.setTextAlign(TextAlignment.CENTER);
//			g.setTextBaseline(VPos.CENTER);
//			g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 33177.6) / 2));
//			g.setFill(Color.WHITE);
//			g.fillText("" + e.index, e.getX() + (e.getWidth()/2), e.getY() + (e.getHeight()/2));

		}
	}

	private void drawPlayer(GraphicsContext g) {
		switch (p.getFaceDirection()) {
		case 0:
			switch (Gui.getPythonSkin()) {
			default:
				g.drawImage(IL.iplayer0_0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 1:
				g.drawImage(IL.iplayer1_0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 2:
				g.drawImage(IL.iplayer2_0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			}
			break;
		case 1:
			switch (Gui.getPythonSkin()) {
			default:
				g.drawImage(IL.iplayer0_1, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 1:
				g.drawImage(IL.iplayer1_1, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 2:
				g.drawImage(IL.iplayer2_1, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			}
			break;
		case 2:
			switch (Gui.getPythonSkin()) {
			default:
				g.drawImage(IL.iplayer0_2, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 1:
				g.drawImage(IL.iplayer1_2, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 2:
				g.drawImage(IL.iplayer2_2, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			}
			break;
		case 3:
			switch (Gui.getPythonSkin()) {
			default:
				g.drawImage(IL.iplayer0_3, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 1:
				g.drawImage(IL.iplayer1_3, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 2:
				g.drawImage(IL.iplayer2_3, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			}
			break;
		default:
			switch (Gui.getPythonSkin()) {
			default:
				g.drawImage(IL.iplayer0_0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 1:
				g.drawImage(IL.iplayer1_0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			case 2:
				g.drawImage(IL.iplayer2_0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
				break;
			}
			break;
		}

//		// draw playerMargin:
//		double pm = Gui.getTile() * Game.getPlayerMargin();
//		g.setStroke(Color.DARKRED);
//		g.strokeRect(p.getX() - pm, p.getY() - pm, pm * 2 + p.getWidth(), pm * 2 + p.getHeight());
	}

	private void drawWall(GraphicsContext g) {
		for (Wall w : Wall_Creation.walls) {
			g.drawImage(IL.iwall, w.getX(), w.getY(), w.getWidth(), w.getHeight());
		}
	}

	private void drawSpecialTile(GraphicsContext g) {
		for (SpecialTile st : SpecialTile_Creation.specialtiles) {
			if (st.isAlive() == true) {
				switch (st.getType()) {
				case "korn":
					g.drawImage(IL.ispecialtile_korn, st.getX(), st.getY(), st.getWidth(), st.getHeight());
					break;
				case "babyhamsterTwo":
					g.drawImage(IL.ispecialtile_babyhamsterTwo, st.getX(), st.getY(), st.getWidth(), st.getHeight());
					break;
				case "babyhamsterThree":
					g.drawImage(IL.ispecialtile_babyhamsterThree, st.getX(), st.getY(), st.getWidth(), st.getHeight());
					break;
				case "babyhamsterFour":
					g.drawImage(IL.ispecialtile_babyhamsterFour, st.getX(), st.getY(), st.getWidth(), st.getHeight());
					break;
				case "hourglass":
					g.drawImage(IL.ispecialtile_hourglass, st.getX(), st.getY(), st.getWidth(), st.getHeight());
					break;
				case "hammer":
					g.drawImage(IL.ispecialtile_hammer, st.getX(), st.getY(), st.getWidth(), st.getHeight());
					break;
				}
			}
		}
	}

	private void drawGrid(GraphicsContext g) {
		g.setFill(new Color(1, 1, 1, 0.7)); // semi-transparent layer underneath the grid
		g.fillRect(Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());

		switch (Grid.getSize()) {
		case "05x05":
			g.drawImage(IL.igrid_05x05, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "05x10":
			g.drawImage(IL.igrid_05x10, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "08x05":
			g.drawImage(IL.igrid_08x05, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "08x08":
			g.drawImage(IL.igrid_08x08, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "10x05":
			g.drawImage(IL.igrid_10x05, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "10x10":
			g.drawImage(IL.igrid_10x10, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "12x05":
			g.drawImage(IL.igrid_12x05, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "14x03":
			g.drawImage(IL.igrid_14x03, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "15x01":
			g.drawImage(IL.igrid_15x01, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "15x08":
			g.drawImage(IL.igrid_15x08, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "20x20":
			g.drawImage(IL.igrid_20x20, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "32x20":
			g.drawImage(IL.igrid_32x20, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "40x20":
			g.drawImage(IL.igrid_40x20, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		default:
			g.drawImage(IL.igrid_ntransp, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		}

	}

	private void drawStartMenu(GraphicsContext g) {
		g.drawImage(IL.istartmenu, 0, 0, Gui.getWidth(), Gui.getHeight());

		drawLevelSelection(g); // draw Level Select buttons and level description

		// draw other buttons
		for (int i = 0; i < Gui.startmenubuttons.length; i++) {
			Button b = Gui.startmenubuttons[i];
			switch (i) {
			case 0: // Cross - Quit
				g.drawImage(IL.ibcross, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
				drawButtonHover(g, b, null);
				break;
			case 1: // Hamster settings
				switch (Gui.getHamsterSkin()) {
				case 0:
					g.drawImage(IL.ienemy0large_1, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 1:
					g.drawImage(IL.ienemy1large_1, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 2:
					g.drawImage(IL.ienemy2large_1, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				}
				break;
			case 2: // Python settings
				switch (Gui.getPythonSkin()) {
				case 0:
					g.drawImage(IL.iplayer0large_3, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 1:
					g.drawImage(IL.iplayer1large_3, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				case 2:
					g.drawImage(IL.iplayer2large_3, b.getX(), b.getY(), b.getWidth(), b.getHeight());
					break;
				}
				break;
			case 3: // Question mark - Manual
				g.drawImage(IL.ibquestionmark, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
				drawButtonHover(g, b, null);
				break;
			}
			if (b.isHover()) {
				switch (i) {
				case 1: // if hovering over button 1 (Hamster skins)
					switch (Gui.getHamsterSkin()) {
					default:
						drawButtonHover(g, b, "Standard-Hamster");
						break;
					case 0:
						drawButtonHover(g, b, "Standard-Hamster");
						break;
					case 1:
						drawButtonHover(g, b, "Zuckerwatte-Hamster");
						break;
					case 2:
						drawButtonHover(g, b, "Pok�mon-Hamster");
						break;
					}
					break;
				case 2: // if hovering over button 2 (Python skins)
					switch (Gui.getPythonSkin()) {
					default:
						drawButtonHover(g, b, "Standard-Python");
						break;
					case 0:
						drawButtonHover(g, b, "Standard-Python");
						break;
					case 1:
						drawButtonHover(g, b, "Blutsauger-Python");
						break;
					case 2:
						drawButtonHover(g, b, "Pok�mon-Python");
						break;
					}
					break;
				}
			}

		}

	}

	public void drawTutorialMenu(GraphicsContext g) {
		g.drawImage(IL.itutorialmenu, 0, 0, Gui.getWidth(), Gui.getHeight());

		g.setFill(new Color(1, 1, 1, 0.7)); // semi-transparent layer underneath the grid
		g.fillRect(startmenugridX, startmenugridY, startmenugridWidth, startmenugridHeight);

		g.drawImage(IL.igrid_12x04_large, startmenugridX, startmenugridY, startmenugridWidth, startmenugridHeight);

		// SCHLIESSEN BUTTON
		Button b = Gui.startmenubuttons[0];
		g.drawImage(IL.ibcross, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);

		// Button Tutorial 101
		b = Gui.tutorialmenubuttons[0];
		g.drawImage(IL.ikeys, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);

		// Button Tutorial 103
		b = Gui.tutorialmenubuttons[1];
		g.drawImage(IL.ibzeitleiste, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);

		// Button Tutorial 104
		b = Gui.tutorialmenubuttons[2];
		g.drawImage(IL.iwalllarge, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);

		// Button Tutorial 105
		b = Gui.tutorialmenubuttons[3];
		g.drawImage(IL.ispecialtile_korn, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);

		// Button Tutorial 106
		b = Gui.tutorialmenubuttons[4];
		g.drawImage(IL.ispecialtile_hourglass, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);

		// Button Tutorial 107
		b = Gui.tutorialmenubuttons[5];
		g.drawImage(IL.ibbabyhamster, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);

		// Button Tutorial 108
		b = Gui.tutorialmenubuttons[6];
		g.drawImage(IL.ispecialtile_hammer, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);

		// Button "Level 1"
		b = Gui.tutorialmenubuttons[8];
		g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		g.setFill(Color.CHOCOLATE);
		g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 41472) / 2));
		g.setFill(Color.WHITE);
		g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);
		drawButtonHover(g, b, "Starte das normale Level 1");

		// Button Fragezeichen - switch between "Behind the Game" and normal Tutlvls
		b = Gui.tutorialmenubuttons[7];
		g.drawImage(IL.ibquestionmark, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, "Durchlaufe das normale Tutorial");
		if (Game.isBehindTheGame() == false) {
			g.setFill(new Color(0, 0, 0, 0.4));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}

		// Button Compiler - switch between "Behind the Game" and normal Tutlvls
		b = Gui.tutorialmenubuttons[9];
		g.drawImage(IL.ibcompiler, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, "Wirf einen Blick �hinter� das Spiel");
		if (Game.isBehindTheGame() == true) {
			g.setFill(new Color(0, 0, 0, 0.4));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}

		// Button Anleitung
		b = Gui.tutorialmenubuttons[10];
		g.drawImage(IL.ibclipboard, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, "Lies die Anleitung im Textformat");

		// Button Back to Start Menu
		b = Gui.tutorialmenubuttons[11];
		g.drawImage(IL.ibhouse, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);
	}

	private void drawButtonHover(GraphicsContext g, Button b, String hoverText) {
		// draw Layer over the Button that is hovered on
		if (b.isHover()) {
			g.setFill(new Color(0, 0, 0, 0.2));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			if (hoverText != null) {
				g.setTextAlign(TextAlignment.LEFT);
				g.setFont(new Font("Constantia",
						((Gui.getWidth() * Gui.getHeight()) / 21404.903225806451612903225806452) / 2));
				g.setFill(Color.WHITE);
				g.fillText(hoverText, getStartmenugridX(), getStartmenugridY() - (Gui.getHeight() / 9.6));
			}
		}
	}

	public void drawAnleitung(GraphicsContext g) {
		g.drawImage(IL.bgfeld, 0, 0, Gui.getWidth(), Gui.getHeight());

		Button b = Gui.anleitungsbuttons[0];
		g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		g.setFill(new Color(1, 1, 1, 0.2));
		g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 41472) / 2));
		g.setFill(Color.BLACK);
		g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);
		/*
		 * Level 1 Button
		 */
		b = Gui.anleitungsbuttons[1];
		g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		if (b.isHover() == true) {
			g.setFill(new Color(1, 1, 1, 0.2));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		g.setFill(Color.DARKRED);
		g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 41472) / 2));
		g.setFill(Color.WHITE);
		g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

		g.drawImage(IL.iplayer0_0, (Gui.getWidth() / 2.0210526315789473684210526315789),
				(Gui.getHeight() / 4.5473684210526315789473684210526), (Gui.getWidth() / 51.2),
				(Gui.getHeight() / 28.8));
		g.drawImage(IL.ienemy0_0, (Gui.getWidth() / 2.0700808625336927223719676549865),
				(Gui.getHeight() / 3.7565217391304347826086956521739), (Gui.getWidth() / 51.2),
				(Gui.getHeight() / 28.8));
		g.drawImage(IL.ipicture, (Gui.getWidth() / 1.3298701298701298701298701298701),
				(Gui.getHeight() / 3.3230769230769230769230769230769), (Gui.getWidth() / 6144),
				(Gui.getHeight() / 3456));
		g.drawImage(IL.iupKey, (Gui.getWidth() / 1.8007033997655334114888628370457),
				(Gui.getHeight() / 1.9862068965517241379310344827586), (Gui.getWidth() / 38.4),
				(Gui.getHeight() / 21.6));
		g.drawImage(IL.idownKey, (Gui.getWidth() / 2.0700808625336927223719676549865),
				(Gui.getHeight() / 1.788819875776397515527950310559), (Gui.getWidth() / 38.4),
				(Gui.getHeight() / 21.6));
		g.drawImage(IL.ileftKey, (Gui.getWidth() / 2.0897959183673469387755102040816),
				(Gui.getHeight() / 1.9862068965517241379310344827586), (Gui.getWidth() / 38.4),
				(Gui.getHeight() / 21.6));
		g.drawImage(IL.irightKey, (Gui.getWidth() / 1.7066666666666666666666666666667),
				(Gui.getHeight() / 2.1984732824427480916030534351145), (Gui.getWidth() / 38.4),
				(Gui.getHeight() / 21.6));
	}

	public void drawPfeiltasten(GraphicsContext g) {
		int w = Gui.getTile() - 1, h = Gui.getTile() - 1; // size of the images
		switch (Game.getLevel()) {
		case 101:
			if (p.getSchritte() < 6) {
				g.drawImage(IL.iright, p.getX() + Gui.getTile(), p.getY() + (p.getHeight() / 2 - h / 2), w, h);
				if (p.getX() > Grid.getX() + 1) {
					g.drawImage(IL.ileft, p.getX() - Gui.getTile(), p.getY() + (p.getHeight() / 2 - h / 2), w, h);
				}
			}
			break;
		case 102:
			if (p.getSchritte() < 15) {
				if (p.getX() < Grid.getX() + 9 * Gui.getTile()) {
					g.drawImage(IL.iright, p.getX() + Gui.getTile(), p.getY() + (p.getHeight() / 2 - h / 2), w, h);
				}
				if (p.getX() > Grid.getX() + 1) {
					g.drawImage(IL.ileft, p.getX() - Gui.getTile(), p.getY() + (p.getHeight() / 2 - h / 2), w, h);
				}
				if (p.getY() > Grid.getY() + 1) {
					g.drawImage(IL.iup, p.getX() + (p.getWidth() / 2 - w / 2), p.getY() - Gui.getTile(), w, h);
				}
				if (p.getY() < Grid.getY() + 9 * Gui.getTile()) {
					g.drawImage(IL.idown, p.getX() + (p.getWidth() / 2 - w / 2), p.getY() + Gui.getTile(), w, h);
				}
			}
			break;
		}
	}

	public static int getStartmenugridWidth() {
		return startmenugridWidth;
	}

	public static void setStartmenugridWidth(int startmenugridWidth) {
		Draw_Main.startmenugridWidth = startmenugridWidth;
	}

	public static int getStartmenugridHeight() {
		return startmenugridHeight;
	}

	public static void setStartmenugridHeight(int startmenugridHeight) {
		Draw_Main.startmenugridHeight = startmenugridHeight;
	}

	public static int getStartmenugridX() {
		return startmenugridX;
	}

	public static void setStartmenugridX(int startmenugridX) {
		Draw_Main.startmenugridX = startmenugridX;
	}

	public static int getStartmenugridY() {
		return startmenugridY;
	}

	public static void setStartmenugridY(int startmenugridY) {
		Draw_Main.startmenugridY = startmenugridY;
	}

}