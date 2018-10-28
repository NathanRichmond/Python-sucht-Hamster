package draw;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import actions.Game;
import chars.Enemy;
import chars.Player;
import clocks.SpecialTile_Creation;
import clocks.Wall_Creation;
import game.GameTimer;
import game.Gamestate;
import game.Gamestate_e;
import game.SpecialTile;
import game.Wall;
import gui.Button;
import gui.Grid;
import gui.Gui;

public class Draw_Main {

	private static Player p;

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
		 * LEVEL SELECTION SCREEN
		 */
		if (Gamestate.state == Gamestate_e.lvlselect) {
			drawLevelSelection(g);
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
			drawGameTimer(g);

			/*
			 * GAME ELEMENTS: Title
			 */
			g.setFill(new Color(0, 0, 0, 0.4));
			g.fillRect(Grid.getX() + Grid.getWidth() / 2 - 160 / 2, Grid.getY() / 2 - 40 / 2, 160, 40);

			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Verdana Bold Italic", 30));
			g.setFill(Color.WHITE);
			g.fillText("LEVEL " + Game.getLevel(), Grid.getX() + Grid.getWidth() / 2, Grid.getY() / 2);

			/*
			 * GAME ELEMENTS: Restart Button
			 */
			g.setStroke(Color.WHITE);
			g.setFill(Color.WHITE);
			Button b = Gui.ingamebutton_restart;
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			if (b.isHover()) {
				g.setFill(new Color(1, 1, 1, 0.2));
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			g.drawImage(IL.ibrestart, b.getX(), b.getY(), b.getWidth(), b.getHeight());

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
//						g.setFont(new Font("Verdana", 16));
//						g.setFill(Color.WHITE);
//						g.fillText("Enemy speed is boosted!", x + width / 2, (y + i * 2 * height) + height / 2);
//					}
//				}
//			}
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
//					g.setFont(new Font("Verdana", 16));
//					g.setFill(Color.WHITE);
//					g.fillText("Game time is modified!", x + width / 2, y + height / 2);
//
//				}
//			}
//
//			/*
//			 * GAME ELEMENTS: Hamster Count
//			 */
//			if (Game.getLevel() == 11) {
//				int x = Grid.getX() - 240, y = Grid.getY(), width = 220, height = 40;
//				g.setFill(new Color(0, 0, 0, 0.4));
//				g.fillRect(x, y, width, height);
//
//				g.setTextAlign(TextAlignment.CENTER);
//				g.setTextBaseline(VPos.CENTER);
//				g.setFont(new Font("Verdana", 16));
//				g.setFill(Color.WHITE);
//				g.fillText("Enemies alive: " + Game.enemies.size(), x + width / 2, y + height / 4);
//				g.fillText("Enemies devoured: " + Game.hamstercount, x + width / 2, y + 3 * height / 4);
//			}
		}

		/*
		 * VICTORY SCREEN
		 */
		if (Gamestate.state == Gamestate_e.victory)

		{
			drawVictoryScreen(g);
		}

		/*
		 * DEFEAT SCREEN
		 */
		if (Gamestate.state == Gamestate_e.defeat) {
			drawDefeatScreen(g);
		}

		/*
		 * PAUSE SCREEN
		 */
		if (Gamestate.state == Gamestate_e.pause) {
			drawPauseScreen(g);
		}

	}

	private void drawLevelSelection(GraphicsContext g) {
		g.drawImage(IL.ilvlselect, 0, 0, Gui.getWidth(), Gui.getHeight());

		g.setStroke(Color.WHITE);

		for (int i = 0; i < Gui.lvlselectbuttons.length; i++) {
			Button b = Gui.lvlselectbuttons[i];
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			g.setFill(new Color(1, 1, 1, 0.2));
			if (b.isHover()) {
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

				g.setTextAlign(TextAlignment.LEFT);
				g.setFont(new Font("Verdana Italic", 26));
				g.setFill(Color.WHITE);
				drawLvlDesc(g, (i + 1));
			}

			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Verdana", 36));
			g.setFill(Color.WHITE);
			g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

		}

		Button b = Gui.lvlselectbutton_back;
		g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		if (b.isHover()) {
			g.setFill(new Color(1, 1, 1, 0.2));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Verdana", 19));
		g.setFill(Color.WHITE);
		g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);
	}

	private void drawLvlDesc(GraphicsContext g, int level) {
		int x = 60;
		int y = 410;
		switch (level) {
		case 1:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 2:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 3:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 4:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 5:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 6:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 7:
			g.fillText("Level 7: The Granary", x, y);
			break;
		case 8:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 9:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 10:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 11:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		case 12:
			g.fillText("[Description of level " + level + " here]", x, y);
			break;
		}

	}

	private void drawBackground(GraphicsContext g) {
		/*
		 * Grass background:
		 */
		g.drawImage(IL.bggras, 0, 0, Gui.getWidth(), Gui.getHeight());

		g.setFill(new Color(1, 1, 1, 0.1)); // semi-transparent layer to moderate the intense grass
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		/*
		 * Plain gray background:
		 */
//		g.setFill(Color.web("#F0F4F6"));
//		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());
	}

	private void drawPauseScreen(GraphicsContext g) {
		g.setFill(new Color(0, 0, 0, 0.7));
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Verdana Bold", 40));
		g.setFill(Color.WHITE);
		g.fillText("PAUSE", Gui.getWidth() / 2, Gui.getHeight() / 4);

		g.setStroke(Color.WHITE);
		g.setFill(Color.WHITE);

		for (Button b : Gui.pausebuttons) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			g.setFill(new Color(1, 1, 1, 0.2));
			if (b.isHover()) {
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Verdana", 16));
			g.setFill(Color.WHITE);
			g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

		}
	}

	private void drawDefeatScreen(GraphicsContext g) {
		g.setFill(new Color(0, 0, 0, 0.7));
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		g.drawImage(IL.tdefeat, 0, 0, Gui.getWidth(), Gui.getHeight());

		for (Button b : Gui.gameendbuttons) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			if (b.isHover() == true) {
				g.setFill(new Color(1, 1, 1, 0.2));
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Verdana", 16));
			g.setFill(Color.WHITE);
			g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

		}
	}

	private void drawVictoryScreen(GraphicsContext g) {
		g.setFill(new Color(0, 0, 0, 0.7));
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		g.drawImage(IL.tvictory, 0, 0, Gui.getWidth(), Gui.getHeight());

		for (Button b : Gui.gameendbuttons) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			if (b.isHover() == true) {
				g.setFill(new Color(1, 1, 1, 0.2));
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("Verdana", 16));
			g.setFill(Color.WHITE);
			g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

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
					if (e.isSpeedBoosted() == true) {
						g.drawImage(IL.ienemyred0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					} else {
						g.drawImage(IL.ienemy0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					}
					break;
				case 1:
					if (e.isSpeedBoosted() == true) {
						g.drawImage(IL.ienemyred1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					} else {
						g.drawImage(IL.ienemy1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					}
					break;
				case 2:
					if (e.isSpeedBoosted() == true) {
						g.drawImage(IL.ienemyred2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					} else {
						g.drawImage(IL.ienemy2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					}
					break;
				case 3:
					if (e.isSpeedBoosted() == true) {
						g.drawImage(IL.ienemyred3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					} else {
						g.drawImage(IL.ienemy3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
					}
					break;
				default:
					g.drawImage(IL.ienemy1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
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
//			g.setFont(new Font("Verdana", 20));
//			g.setFill(Color.WHITE);
//			g.fillText("" + e.index, e.getX() + 16, e.getY() + 16);

		}
	}

	private void drawPlayer(GraphicsContext g) {
		switch (p.getFaceDirection()) {
		case 0:
			g.drawImage(IL.iplayer0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			break;
		case 1:
			g.drawImage(IL.iplayer1, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			break;
		case 2:
			g.drawImage(IL.iplayer2, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			break;
		case 3:
			g.drawImage(IL.iplayer3, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			break;
		default:
			g.drawImage(IL.iplayerred0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
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
				case "babyhamster":
					g.drawImage(IL.ispecialtile_babyhamster, st.getX(), st.getY(), st.getWidth(), st.getHeight());
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
		case "05x10":
			g.drawImage(IL.igrid_05x10, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "10x05":
			g.drawImage(IL.igrid_10x05, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "10x10":
			g.drawImage(IL.igrid_10x10, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "20x20":
			g.drawImage(IL.igrid_20x20, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
			break;
		case "34x20":
			g.drawImage(IL.igrid_34x20, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
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

		g.setStroke(Color.WHITE);
		g.setFill(Color.WHITE);

		/*
		 * Button "Start New Game"
		 */
		Button b = Gui.startmenubuttons[0];
		g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		if (b.isHover() == true) {
			g.setFill(new Color(1, 1, 1, 0.2));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Verdana", 46));
		g.setFill(Color.WHITE);
		g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

		/*
		 * Button "Settings"
		 */
		b = Gui.startmenubuttons[1];
		g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		if (b.isHover() == true) {
			g.setFill(new Color(1, 1, 1, 0.2));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Verdana", 16));
		g.setFill(Color.WHITE);
		g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

		/*
		 * Button "Quit"
		 */
		b = Gui.startmenubuttons[2];
		g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		if (b.isHover() == true) {
			g.setFill(new Color(1, 1, 1, 0.2));
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("Verdana", 16));
		g.setFill(Color.WHITE);
		g.fillText(b.getText(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);
	}

}
