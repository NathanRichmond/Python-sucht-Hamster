package draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import chars.Enemy;
import chars.Player;
import game.Gamestate;
import game.Gamestate_e;
import game.Timer;
import gui.Button;
import gui.Gui;

public class Draw_Main {

	private Text text = new Text();

	public void draw(GraphicsContext g) {

		/*
		 * BACKGROUND
		 */
		g.setFill(Color.web("#F0F4F6"));
		g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

		/*
		 * BACKGROUND IMAGE: Grid
		 */
		g.drawImage(IL.ibg, 0, 0, Gui.getGridwidth(), Gui.getGridheight());

		/*
		 * CHARACTERS: Enemy
		 */
		if (Enemy.isAlive()) {
			switch (Enemy.getFaceDirection()) {
			case 0:
				g.drawImage(IL.ienemy0, Enemy.getX(), Enemy.getY(), Enemy.getWidth(), Enemy.getHeight());
				break;
			case 1:
				g.drawImage(IL.ienemy1, Enemy.getX(), Enemy.getY(), Enemy.getWidth(), Enemy.getHeight());
				break;
			case 2:
				g.drawImage(IL.ienemy2, Enemy.getX(), Enemy.getY(), Enemy.getWidth(), Enemy.getHeight());
				break;
			case 3:
				g.drawImage(IL.ienemy3, Enemy.getX(), Enemy.getY(), Enemy.getWidth(), Enemy.getHeight());
				break;
			default:
				g.drawImage(IL.ienemy1, Enemy.getX(), Enemy.getY(), Enemy.getWidth(), Enemy.getHeight());
				break;
			}
		} else {
			// image to be displayed when hamster is dead
		}

		/*
		 * CHARACTERS: Player
		 */
		g.drawImage(IL.iplayer0, Player.getX(), Player.getY(), 24, 24);

		/*
		 * GAME ELEMENTS: Timer
		 */
		g.drawImage(IL.iblack, 431, 66, 20, 200); // black background column
		g.drawImage(IL.iorange, Timer.getX(), Timer.getY(), Timer.getWidth(), Timer.getHeight()); // orange foreground
																									// column

		/*
		 * GAME ELEMENTS: Victory Screen
		 */
		if (Gamestate.state == Gamestate_e.victory) {
			g.setFill(new Color(0, 0, 0, .5));
			g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

			g.drawImage(IL.tvictory, 50, 50, 330, 150);
		}

		/*
		 * GAME ELEMENTS: Defeat Screen
		 */
		if (Gamestate.state == Gamestate_e.defeat) {
			g.setFill(new Color(0, 0, 0, .5));
			g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

			g.drawImage(IL.tdefeat, 0, 0, 491, 331);
		}

		/*
		 * GAME ELEMENTS: Pause Screen
		 */
		if (Gamestate.state == Gamestate_e.pause) {
			g.setFill(new Color(0, 0, 0, 0.5));
			g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());

			g.setStroke(Color.WHITE);
			g.setFill(Color.WHITE);

			for (Button b : Gui.buttons) {
				g.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

				g.setFill(new Color(1, 1, 1, 0.2));
				if (b.isHover()) {
					g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
				}

				g.setFill(Color.WHITE);

				text.setText(b.getText());
				text.setFont(Font.font("Veranda", 25));

				g.fillText(b.getText(), b.getX() + b.getWidth() / 2 - text.getLayoutBounds().getWidth() / 2,
						b.getY() + b.getHeight() / 2 + text.getLayoutBounds().getHeight() / 4);

			}
		}

	}

}
