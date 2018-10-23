package actions;

import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MousePressed implements EventHandler<MouseEvent> {
	@Override
	public void handle(MouseEvent e) {

		switch (Gamestate.state) {
		case pause:
			if (Collision.cButton(Gui.pausebuttons[0], (int) e.getX(), (int) e.getY())) {
				Gamestate.state = Gamestate_e.ingame;
			}
			if (Collision.cButton(Gui.pausebuttons[1], (int) e.getX(), (int) e.getY())) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.pausebuttons[2], (int) e.getX(), (int) e.getY())) {
				System.exit(0);
			}
			break;
		case startmenu:
			if (Collision.cButton(Gui.startmenubuttons[0], (int) e.getX(), (int) e.getY())) {
				Main.startNewGame();
			}
			if (Collision.cButton(Gui.startmenubuttons[1], (int) e.getX(), (int) e.getY())) {
				System.exit(0);
			}
			break;
		case victory:
			if (Collision.cButton(Gui.gameendbuttons[0], (int) e.getX(), (int) e.getY())) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.gameendbuttons[1], (int) e.getX(), (int) e.getY())) {
				System.exit(0);
			}
			break;
		case defeat:
			if (Collision.cButton(Gui.gameendbuttons[0], (int) e.getX(), (int) e.getY())) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.gameendbuttons[1], (int) e.getX(), (int) e.getY())) {
				System.exit(0);
			}
			break;
		default:
			break;
		}

	}
}
