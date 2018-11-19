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

	private static int startmenugridWidth = 794;
	private static int startmenugridHeight = 266;
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
			// Extrabilder für das erste Tutorial
			if (Game.getLevel() == 101) {
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

//			/*
//			 * GAME ELEMENTS: Korn Tile Activated Info
//			 */
//			if (Game.isSpecialTiles() == true) {
//				int x = Grid.getX() - 240, y = Grid.getY();
//				for (int i = 0; i < Game.enemies.size(); i++) {
//					Enemy e = Game.enemies.get(i);
//
//					if (e.isSpeedBoosted() == true) {
//						int width = 220, height = 20;
//						g.setFill(new Color(0, 0, 0, 0.4));
//						g.fillRect(x, y + i * 2 * height, width, height);
//
//						g.setTextAlign(TextAlignment.CENTER);
//						g.setTextBaseline(VPos.CENTER);
//						g.setFont(new Font("Constantia", 16));
//						g.setFill(Color.WHITE);
//						g.fillText("Enemy speed is boosted!", x + width / 2, (y + i * 2 * height) + height / 2);
//					}
//				}
//			}
//			
//			/*
//			 * GAME ELEMENTS: Time Modified Info
//			 */
//			if (Game.isSpecialTiles() == true) {
//
//				if (GameTimer.isModified() == true) {
//					int x = Grid.getX() - 240, y = Grid.getY() + 2 * 20, width = 220, height = 20;
//					g.setFill(new Color(0, 0, 0, 0.4));
//					g.fillRect(x, y, width, height);
//
//					g.setTextAlign(TextAlignment.CENTER);
//					g.setTextBaseline(VPos.CENTER);
//					g.setFont(new Font("Constantia", 16));
//					g.setFill(Color.WHITE);
//					g.fillText("Game time is modified!", x + width / 2, y + height / 2);
//
//				}
//			}
//
			/*
			 * GAME ELEMENTS: Hamster Count (for Hamsterinflation)
			 */
			if (Game.getLevel() == 12) {
				int x = Grid.getX() - 240, y = Grid.getY(), width = 200, height = 40;
				g.setFill(new Color(0, 0, 0, 0.4));
				g.fillRect(x, y, width, height);

				g.setTextAlign(TextAlignment.CENTER);
				g.setTextBaseline(VPos.CENTER);
				g.setFont(new Font("Constantia", 16));
				g.setFill(Color.WHITE);
				g.fillText("Hamster am Leben: " + Game.enemies.size(), x + width / 2, y + height / 4);
				g.fillText("Hamster verschlungen: " + Game.hamstercount, x + width / 2, y + 3 * height / 4);
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

	private void drawLvlTitle(GraphicsContext g) {
		int width = 150; // width of the lvl title box
		int height = 70; // height of the lvl title box
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
			g.fillRect(Grid.getX() + Grid.getWidth() / 2 - 160 / 2, Grid.getY() / 2 - 40 / 2, 160, 40);

			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Constantia Bold Italic", 30));
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
					// draw box with text "zurück zum Menü" underneath the button
					int x = b.getX() + 1 * b.getHeight() / 6; // x coordinate of box
					int y = b.getY() + b.getHeight() + 1 * b.getHeight() / 6; // y coordinate of box
					int w = 240; // width of box
					int h = 2 * b.getHeight() / 3; // height of box
					g.strokeRect(x, y, w, h);
					g.setFill(new Color(0, 0, 0, 0.2));
					g.fillRect(x, y, w, h);
					g.setTextAlign(TextAlignment.CENTER);
					g.setTextBaseline(VPos.CENTER);
					g.setFont(new Font("Constantia", 26));
					g.setFill(Color.WHITE);
					g.fillText("zurück zum Menü", x + w / 2, y + h / 2);
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
		int y = getStartmenugridY() - 90;
		switch (level) {
		default:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 1:
			g.drawImage(IL.ilvldesc1, x, y);
			break;
		case 2:
			g.drawImage(IL.ilvldesc2, x, y);
			break;
		case 3:
			g.drawImage(IL.ilvldesc3, x, y);
			break;
		case 4:
			g.drawImage(IL.ilvldesc4, x, y);
			break;
		case 5:
			g.drawImage(IL.ilvldesc5, x, y);
			break;
		case 6:
			g.drawImage(IL.ilvldesc6, x, y);
			break;
		case 7:
			g.drawImage(IL.ilvldesc7, x, y);
			break;
		case 8:
			g.drawImage(IL.ilvldesc8, x, y);
			break;
		case 9:
			g.drawImage(IL.ilvldesc9, x, y);
			break;
		case 10:
			g.drawImage(IL.ilvldesc10, x, y);
			break;
		case 11:
			g.drawImage(IL.ilvldesc11, x, y);
			break;
		case 12:
			g.drawImage(IL.ilvldesc12, x, y);
			break;
		}

	}

	private void drawBackground(GraphicsContext g) {
		/*
		 * Getreidefeld background:
		 */
		g.drawImage(IL.bgfeld, 0, 0, Gui.getWidth(), Gui.getHeight());

		g.setFill(new Color(1, 1, 1, 0.1)); // semi-transparent layer to moderate the intense grass
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
		g.setFont(new Font("Constantia Bold", 40));
		g.setFill(Color.WHITE);
		g.fillText("PAUSE", Grid.getX() + Grid.getWidth() / 2, Grid.getY() + Grid.getHeight() / 4);
	}

	private void drawGameendScreen(GraphicsContext g) {
		// dark layer over grid
		g.setFill(new Color(0, 0, 0, 0.7));
		g.fillRect(Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());

		int w = 166; // width of image
		int h = 166; // height of image
		// position of image: centered on grid
		int x = Grid.getX() + (Grid.getWidth() / 2 - w / 2);
		int y = Grid.getY() + (Grid.getHeight() / 2 - h / 2);

		// border around the image (placeholder while there is no image)
		g.setStroke(Color.WHITE);
		g.strokeRect(x, y, w, h);

		switch (Gamestate.state) {
		case defeat:
			g.drawImage(IL.idefeat, x, y, w, h);
			break;
		case victory:
			g.drawImage(IL.ivictory, x, y, w, h);
			drawVictoryButton(g); // Button: "Next Level"
			break;
		default:
			break;
		}
	}

	private void drawVictoryButton(GraphicsContext g) {
		Button b = Gui.victorybutton;
		if (Game.getLevel() + 1 <= Gui.getnLvls() || (Game.getLevel() > 100 && Game.getLevel() < 108)) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			g.setFill(new Color(1, 1, 1, 0.8));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			drawButtonHover(g, b, null);
			g.drawImage(IL.ibnext, b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		if (Game.getLevel() > 100 && Game.getLevel() < 108) { // if victory is in a Tutorial level
			// draw box with text "zum nächsten Level" underneath the button
			int x = b.getX() + 1 * b.getHeight() / 6; // x coordinate of box
			int y = b.getY() + b.getHeight() + 1 * b.getHeight() / 6; // y coordinate of box
			int w = 270; // width of box
			int h = 2 * b.getHeight() / 3; // height of box
			g.strokeRect(x, y, w, h);
			g.setFill(new Color(0, 0, 0, 0.2));
			g.fillRect(x, y, w, h);
			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Constantia", 26));
			g.setFill(Color.WHITE);
			g.fillText("zum nächsten Tutorial", x + w / 2, y + h / 2);
		}
	}

	private void drawGameTimer(GraphicsContext g) {
		/*
		 * Long method; collapse it!
		 */
		int x = Grid.getX() + Grid.getWidth() + 50; // is vertically centered with grid
		int y = Grid.getY() + Grid.getHeight() / 2 - 125;
		int h = 75;
		int w = 250;

		switch (GameTimer.getGameTime()) {
		case 1:
			g.drawImage(IL.igametimer01, x, y, h, w);
			break;
		case 2:
			g.drawImage(IL.igametimer02, x, y, h, w);
			break;
		case 3:
			g.drawImage(IL.igametimer03, x, y, h, w);
			break;
		case 4:
			g.drawImage(IL.igametimer04, x, y, h, w);
			break;
		case 5:
			g.drawImage(IL.igametimer05, x, y, h, w);
			break;
		case 6:
			g.drawImage(IL.igametimer06, x, y, h, w);
			break;
		case 7:
			g.drawImage(IL.igametimer07, x, y, h, w);
			break;
		case 8:
			g.drawImage(IL.igametimer08, x, y, h, w);
			break;
		case 9:
			g.drawImage(IL.igametimer09, x, y, h, w);
			break;
		case 10:
			g.drawImage(IL.igametimer10, x, y, h, w);
			break;
		case 11:
			g.drawImage(IL.igametimer11, x, y, h, w);
			break;
		case 12:
			g.drawImage(IL.igametimer12, x, y, h, w);
			break;
		case 13:
			g.drawImage(IL.igametimer13, x, y, h, w);
			break;
		case 14:
			g.drawImage(IL.igametimer14, x, y, h, w);
			break;
		case 15:
			g.drawImage(IL.igametimer15, x, y, h, w);
			break;
		case 16:
			g.drawImage(IL.igametimer16, x, y, h, w);
			break;
		case 17:
			g.drawImage(IL.igametimer17, x, y, h, w);
			break;
		case 18:
			g.drawImage(IL.igametimer18, x, y, h, w);
			break;
		case 19:
			g.drawImage(IL.igametimer19, x, y, h, w);
			break;
		case 20:
			g.drawImage(IL.igametimer20, x, y, h, w);
			break;
		case 21:
			g.drawImage(IL.igametimer21, x, y, h, w);
			break;
		case 22:
			g.drawImage(IL.igametimer22, x, y, h, w);
			break;
		case 23:
			g.drawImage(IL.igametimer23, x, y, h, w);
			break;
		case 24:
			g.drawImage(IL.igametimer24, x, y, h, w);
			break;
		case 25:
			g.drawImage(IL.igametimer25, x, y, h, w);
			break;
		case 26:
			g.drawImage(IL.igametimer26, x, y, h, w);
			break;
		case 27:
			g.drawImage(IL.igametimer27, x, y, h, w);
			break;
		case 28:
			g.drawImage(IL.igametimer28, x, y, h, w);
			break;
		case 29:
			g.drawImage(IL.igametimer29, x, y, h, w);
			break;
		case 30:
			g.drawImage(IL.igametimer30, x, y, h, w);
			break;
		case 31:
			g.drawImage(IL.igametimer31, x, y, h, w);
			break;
		case 32:
			g.drawImage(IL.igametimer32, x, y, h, w);
			break;
		case 33:
			g.drawImage(IL.igametimer33, x, y, h, w);
			break;
		case 34:
			g.drawImage(IL.igametimer34, x, y, h, w);
			break;
		case 35:
			g.drawImage(IL.igametimer35, x, y, h, w);
			break;
		case 36:
			g.drawImage(IL.igametimer36, x, y, h, w);
			break;
		case 37:
			g.drawImage(IL.igametimer37, x, y, h, w);
			break;
		case 38:
			g.drawImage(IL.igametimer38, x, y, h, w);
			break;
		case 39:
			g.drawImage(IL.igametimer39, x, y, h, w);
			break;
		case 40:
			g.drawImage(IL.igametimer40, x, y, h, w);
			break;
		case 41:
			g.drawImage(IL.igametimer41, x, y, h, w);
			break;
		case 42:
			g.drawImage(IL.igametimer42, x, y, h, w);
			break;
		case 43:
			g.drawImage(IL.igametimer43, x, y, h, w);
			break;
		case 44:
			g.drawImage(IL.igametimer44, x, y, h, w);
			break;
		case 45:
			g.drawImage(IL.igametimer45, x, y, h, w);
			break;
		case 46:
			g.drawImage(IL.igametimer46, x, y, h, w);
			break;
		case 47:
			g.drawImage(IL.igametimer47, x, y, h, w);
			break;
		case 48:
			g.drawImage(IL.igametimer48, x, y, h, w);
			break;
		case 49:
			g.drawImage(IL.igametimer49, x, y, h, w);
			break;
		case 50:
			g.drawImage(IL.igametimer50, x, y, h, w);
			break;
		case 51:
			g.drawImage(IL.igametimer51, x, y, h, w);
			break;
		case 52:
			g.drawImage(IL.igametimer52, x, y, h, w);
			break;
		case 53:
			g.drawImage(IL.igametimer53, x, y, h, w);
			break;
		case 54:
			g.drawImage(IL.igametimer54, x, y, h, w);
			break;
		case 55:
			g.drawImage(IL.igametimer55, x, y, h, w);
			break;
		case 56:
			g.drawImage(IL.igametimer56, x, y, h, w);
			break;
		case 57:
			g.drawImage(IL.igametimer57, x, y, h, w);
			break;
		case 58:
			g.drawImage(IL.igametimer58, x, y, h, w);
			break;
		case 59:
			g.drawImage(IL.igametimer59, x, y, h, w);
			break;
		case 60:
			g.drawImage(IL.igametimer60, x, y, h, w);
			break;
		case 61:
			g.drawImage(IL.igametimer61, x, y, h, w);
			break;
		case 62:
			g.drawImage(IL.igametimer62, x, y, h, w);
			break;
		case 63:
			g.drawImage(IL.igametimer63, x, y, h, w);
			break;
		case 64:
			g.drawImage(IL.igametimer64, x, y, h, w);
			break;
		case 65:
			g.drawImage(IL.igametimer65, x, y, h, w);
			break;
		case 66:
			g.drawImage(IL.igametimer66, x, y, h, w);
			break;
		case 67:
			g.drawImage(IL.igametimer67, x, y, h, w);
			break;
		case 68:
			g.drawImage(IL.igametimer68, x, y, h, w);
			break;
		case 69:
			g.drawImage(IL.igametimer69, x, y, h, w);
			break;
		case 70:
			g.drawImage(IL.igametimer70, x, y, h, w);
			break;
		case 71:
			g.drawImage(IL.igametimer71, x, y, h, w);
			break;
		case 72:
			g.drawImage(IL.igametimer72, x, y, h, w);
			break;
		case 73:
			g.drawImage(IL.igametimer73, x, y, h, w);
			break;
		case 74:
			g.drawImage(IL.igametimer74, x, y, h, w);
			break;
		case 75:
			g.drawImage(IL.igametimer75, x, y, h, w);
			break;
		case 76:
			g.drawImage(IL.igametimer76, x, y, h, w);
			break;
		case 77:
			g.drawImage(IL.igametimer77, x, y, h, w);
			break;
		case 78:
			g.drawImage(IL.igametimer78, x, y, h, w);
			break;
		case 79:
			g.drawImage(IL.igametimer79, x, y, h, w);
			break;
		case 80:
			g.drawImage(IL.igametimer80, x, y, h, w);
			break;
		case 81:
			g.drawImage(IL.igametimer81, x, y, h, w);
			break;
		case 82:
			g.drawImage(IL.igametimer82, x, y, h, w);
			break;
		case 83:
			g.drawImage(IL.igametimer83, x, y, h, w);
			break;
		case 84:
			g.drawImage(IL.igametimer84, x, y, h, w);
			break;
		case 85:
			g.drawImage(IL.igametimer85, x, y, h, w);
			break;
		case 86:
			g.drawImage(IL.igametimer86, x, y, h, w);
			break;
		case 87:
			g.drawImage(IL.igametimer87, x, y, h, w);
			break;
		case 88:
			g.drawImage(IL.igametimer88, x, y, h, w);
			break;
		case 89:
			g.drawImage(IL.igametimer89, x, y, h, w);
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
//			g.fillRect(e.getX(), e.getY(), 32, 32);
//
//			g.setTextAlign(TextAlignment.CENTER);
//			g.setTextBaseline(VPos.CENTER);
//			g.setFont(new Font("Constantia", 20));
//			g.setFill(Color.WHITE);
//			g.fillText("" + e.index, e.getX() + 16, e.getY() + 16);

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
						drawButtonHover(g, b, "Pokémon-Hamster");
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
						drawButtonHover(g, b, "Pokémon-Python");
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
		g.drawImage(IL.iwall, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
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
		g.setFont(new Font("Constantia", 16));
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
		drawButtonHover(g, b, "Wirf einen Blick „hinter“ das Spiel");
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
		g.drawImage(IL.ibback, b.getX() + 3, b.getY() + 3, b.getWidth() - 6, b.getHeight() - 6);
		drawButtonHover(g, b, null);
	}

	private void drawButtonHover(GraphicsContext g, Button b, String hoverText) {
		// draw Layer over the Button that is hovered on
		if (b.isHover()) {
			g.setFill(new Color(0, 0, 0, 0.2));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			if (hoverText != null) {
				g.setTextAlign(TextAlignment.LEFT);
				g.setFont(new Font("Constantia", 31));
				g.setFill(Color.WHITE);
				g.fillText(hoverText, getStartmenugridX(), getStartmenugridY() - 90);
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
		g.setFont(new Font("Constantia", 16));
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
		g.setFont(new Font("Constantia", 16));
		g.setFill(Color.WHITE);
		g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

		g.drawImage(IL.iplayer0_0, 760, 190, 30, 30);
		g.drawImage(IL.ienemy0_0, 742, 230, 30, 30);
		g.drawImage(IL.ipicture, 1155, 260, 250, 250);
		g.drawImage(IL.iupKey, 853, 435, 40, 40);
		g.drawImage(IL.idownKey, 742, 483, 40, 40);
		g.drawImage(IL.ileftKey, 735, 435, 40, 40);
		g.drawImage(IL.irightKey, 900, 393, 40, 40);
	}

	public void drawPfeiltasten(GraphicsContext g) {
		if (p.getSchritte() < 6) {
			g.drawImage(IL.iright, p.getX() + 1 * 33, Grid.getY(), 40, 40);
			if (p.getX() > Grid.getX()) {
				g.drawImage(IL.ileft, p.getX() - 1 * 33, Grid.getY(), 40, 40);
			}
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