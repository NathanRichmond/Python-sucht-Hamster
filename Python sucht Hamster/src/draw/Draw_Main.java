package draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import actions.Main;
import chars.Enemy;
import chars.Player;
import clocks.Korn_Creation;
import clocks.Wall_Creation;
import game.GameTimer;
import game.Gamestate;
import game.Gamestate_e;
import game.Korn;
import game.Upgrade;
import game.Wall;
import gui.Button;
import gui.Grid;
import gui.Gui;

public class Draw_Main {

	private Text text = new Text();

	private static Player p;
	private static Enemy e;

	public void draw(GraphicsContext gc) {
		p = Main.p;
		e = Main.e;
		GraphicsContext g = gc;

		/*
		 * START MENU SCREEN
		 */
		if (Gamestate.state == Gamestate_e.startmenu) {
			drawStartMenu(g);
		}

		/*
		 * INGAME ELEMENTS
		 */
		if (Gamestate.state == Gamestate_e.ingame || Gamestate.state == Gamestate_e.pause
				|| Gamestate.state == Gamestate_e.victory || Gamestate.state == Gamestate_e.defeat) {

			/*
			 * BACKGROUND
			 */
			drawBackground(g);

			/*
			 * BACKGROUND IMAGE: Grid
			 */
			drawGrid(g);

			/*
			 * GRID CONTENTS: Korn
			 */
			drawKorn(g);

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
			 * GAME ELEMENTS: Player Upgraded
			 */
			if (p.isUpgraded() == true) {
				g.setFill(new Color(0, 0, 0, 0.6));
				g.fillRect(Grid.getX() + Grid.getWidth() + 145, Grid.getY(), 125, 40);

				g.setFill(Color.WHITE);
				text.setText("Player Upgraded");
				text.setFont(Font.font("Verdana", 48));

				g.fillText("Player Upgraded", Grid.getX() + Grid.getWidth() + 147, Grid.getY() + 15);
				if (Upgrade.getDuration() + 1 > 1) {
					g.fillText(Upgrade.getDuration() + 1 + " Sekunden", Grid.getX() + Grid.getWidth() + 147,
							Grid.getY() + 35); // duration
				} else {
					if (Upgrade.getDuration() + 1 == 1) {
						g.fillText(Upgrade.getDuration() + 1 + " Sekunde", Grid.getX() + Grid.getWidth() + 147,
								Grid.getY() + 35); // duration
					}
				}

				g.setStroke(Color.WHITE); // border
				g.strokeRect(Grid.getX() + Grid.getWidth() + 145, Grid.getY(), 125, 40);
			}
		}

		/*
		 * VICTORY SCREEN
		 */
		if (Gamestate.state == Gamestate_e.victory) {
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

	private void drawBackground(GraphicsContext g) {
		/*
		 * Grass background:
		 */
		g.drawImage(IL.bggras, 0, 0, Gui.getWidth(), Gui.getHeight());

		/*
		 * Plain gray background:
		 */
//		g.setFill(Color.web("#F0F4F6"));
//		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());
	}

	private void drawPauseScreen(GraphicsContext g) {
		g.setFill(new Color(0, 0, 0, 0.5));
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		g.setFill(Color.WHITE);
		text.setText("PAUSE");
		text.setFont(Font.font("Verdana", 48));

		g.fillText("PAUSE", Gui.getWidth() / 2 - text.getLayoutBounds().getWidth() / 4,
				Gui.getHeight() / 4 + text.getLayoutBounds().getHeight() / 4);

		g.setStroke(Color.WHITE);
		g.setFill(Color.WHITE);

		for (Button b : Gui.pausebuttons) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			g.setFill(new Color(1, 1, 1, 0.2));
			if (b.isHover()) {
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			g.setFill(Color.WHITE);

			text.setText(b.getText());
			text.setFont(Font.font("Veranda", 25));

			g.fillText(b.getText(), b.getX() + b.getWidth() / 2 - text.getLayoutBounds().getWidth() / 4,
					b.getY() + b.getHeight() / 2 + text.getLayoutBounds().getHeight() / 4);

		}
	}

	private void drawDefeatScreen(GraphicsContext g) {
		g.setFill(new Color(0, 0, 0, .5));
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		g.drawImage(IL.tdefeat, 0, 0, 491, 331);

		for (Button b : Gui.gameendbuttons) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			if (b.isHover() == true) {
				g.setFill(new Color(1, 1, 1, 0.2));
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			g.setFill(Color.WHITE);

			text.setText(b.getText());
			text.setFont(Font.font("Verdana", 25));

			g.fillText(b.getText(), b.getX() + b.getWidth() / 2 - text.getLayoutBounds().getWidth() / 4,
					b.getY() + b.getHeight() / 2 + text.getLayoutBounds().getHeight() / 4);

		}
	}

	private void drawVictoryScreen(GraphicsContext g) {
		g.setFill(new Color(0, 0, 0, .5));
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		g.drawImage(IL.tvictory, 50, 50, 330, 150);

		for (Button b : Gui.gameendbuttons) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			if (b.isHover() == true) {
				g.setFill(new Color(1, 1, 1, 0.2));
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			g.setFill(Color.WHITE);

			text.setText(b.getText());
			text.setFont(Font.font("Verdana", 25));

			g.fillText(b.getText(), b.getX() + b.getWidth() / 2 - text.getLayoutBounds().getWidth() / 4,
					b.getY() + b.getHeight() / 2 + text.getLayoutBounds().getHeight() / 4);

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
		if (e.isAlive() == true) {
			switch (e.getFaceDirection()) {
			case 0:
				g.drawImage(IL.ienemy0, e.getX(), e.getY(), e.getWidth(), e.getHeight());
				break;
			case 1:
				g.drawImage(IL.ienemy1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
				break;
			case 2:
				g.drawImage(IL.ienemy2, e.getX(), e.getY(), e.getWidth(), e.getHeight());
				break;
			case 3:
				g.drawImage(IL.ienemy3, e.getX(), e.getY(), e.getWidth(), e.getHeight());
				break;
			default:
				g.drawImage(IL.ienemy1, e.getX(), e.getY(), e.getWidth(), e.getHeight());
				break;
			}
		} else {
			// image to be displayed when hamster is dead
		}
	}

	private void drawPlayer(GraphicsContext g) {
		switch (p.getFaceDirection()) {
		case 0:
			if (p.isUpgraded() == false) {
				g.drawImage(IL.iplayer0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			} else {
				g.drawImage(IL.iplayerzunge0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			}
			break;
		case 1:
			if (p.isUpgraded() == false) {
				g.drawImage(IL.iplayer1, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			} else {
				g.drawImage(IL.iplayerzunge1, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			}
			break;
		case 2:
			if (p.isUpgraded() == false) {
				g.drawImage(IL.iplayer2, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			} else {
				g.drawImage(IL.iplayerzunge2, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			}
			break;
		case 3:
			if (p.isUpgraded() == false) {
				g.drawImage(IL.iplayer3, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			} else {
				g.drawImage(IL.iplayerzunge3, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			}
			break;
		default:
			if (p.isUpgraded() == false) {
				g.drawImage(IL.iplayer0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			} else {
				g.drawImage(IL.iplayerzunge0, p.getX(), p.getY(), p.getWidth(), p.getHeight());
			}
			break;
		}

	}

	private void drawWall(GraphicsContext g) {
		for (Wall w : Wall_Creation.walls) {
			g.drawImage(IL.iwall, w.getX(), w.getY(), w.getWidth(), w.getHeight());
		}
	}

	private void drawKorn(GraphicsContext g) {
		for (Korn k : Korn_Creation.koerner) {
			if (k.isConsumed == false) {
				g.drawImage(IL.ikorn, k.getX(), k.getY(), k.getWidth(), k.getHeight());
			}
		}
	}

	private void drawGrid(GraphicsContext g) {
		g.setFill(new Color(1, 1, 1, 0.7)); // semi-transparent layer underneath the grid
		g.fillRect(Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
		g.drawImage(IL.igrid, Grid.getX(), Grid.getY(), Grid.getWidth(), Grid.getHeight());
	}

	private void drawStartMenu(GraphicsContext g) {
		g.drawImage(IL.istartmenu, 0, 0, Gui.getWidth(), Gui.getHeight());

		g.setStroke(Color.WHITE);
		g.setFill(Color.WHITE);

		for (Button b : Gui.startmenubuttons) {
			g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

			if (b.isHover() == true) {
				g.setFill(new Color(1, 1, 1, 0.2));
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			g.setFill(Color.WHITE);

			text.setText(b.getText());
			text.setFont(Font.font("Verdana", 25));

			g.fillText(b.getText(), b.getX() + b.getWidth() / 2 - text.getLayoutBounds().getWidth() / 4,
					b.getY() + b.getHeight() / 2 + text.getLayoutBounds().getHeight() / 4);

		}
	}

}
