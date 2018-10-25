package actions;

import data.Collision;
import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MousePressed implements EventHandler<MouseEvent> {
	@Override
	public void handle(MouseEvent e) {

		int x = (int) e.getX(); // x coord of mouse position where clicked
		int y = (int) e.getY(); // y coord of mouse position where clicked

		switch (Gamestate.state) {

		case startmenu:
			if (Collision.cButton(Gui.startmenubuttons[0], x, y)) {
				Gamestate.state = Gamestate_e.lvlselect;
			}
			if (Collision.cButton(Gui.startmenubuttons[1], x, y)) {
				System.exit(0);
			}
			break;

		case lvlselect:
			for (int i = 0; i < Gui.getnLvls(); i++) { // cycle through buttons
				if (Collision.cButton(Gui.lvlselectbuttons[i], x, y)) {
					Game.setLevel(i + 1); // i+1 because i starts at 0 but levels start at 1
					Game.startNewGame();
				}
			}
			break;

		case pause:
			if (Collision.cButton(Gui.pausebuttons[0], x, y)) {
				Gamestate.state = Gamestate_e.ingame;
			}
			if (Collision.cButton(Gui.pausebuttons[1], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.pausebuttons[2], x, y)) {
				System.exit(0);
			}
			break;

		case victory:
			if (Collision.cButton(Gui.gameendbuttons[0], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.gameendbuttons[1], x, y)) {
				System.exit(0);
			}
			break;
		case defeat:
			if (Collision.cButton(Gui.gameendbuttons[0], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.gameendbuttons[1], x, y)) {
				System.exit(0);
			}
			break;
		default:
			break;
		}

	}
}
