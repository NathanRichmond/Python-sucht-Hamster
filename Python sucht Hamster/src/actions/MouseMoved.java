package actions;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import data.Collision;
import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;

public class MouseMoved implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent e) {

		int x = (int) e.getX(); // x coord of current mouse position
		int y = (int) e.getY(); // y coord of current mouse position

		for (int i = 0; i < Gui.pausebuttons.length; i++) {
			if (Collision.cButton(Gui.pausebuttons[i], x, y)) {
				Gui.pausebuttons[i].setHover(true);
			} else {
				Gui.pausebuttons[i].setHover(false);

			}
		}

		for (int i = 0; i < Gui.startmenubuttons.length; i++) {
			if (Collision.cButton(Gui.startmenubuttons[i], x, y)) {
				Gui.startmenubuttons[i].setHover(true);
			} else {
				Gui.startmenubuttons[i].setHover(false);

			}
		}

		for (int i = 0; i < Gui.gameendbuttons.length; i++) {
			if (Collision.cButton(Gui.gameendbuttons[i], x, y)) {
				Gui.gameendbuttons[i].setHover(true);
			} else {
				Gui.gameendbuttons[i].setHover(false);

			}
		}

		for (int i = 0; i < Gui.lvlselectbuttons.length; i++) {
			if (Collision.cButton(Gui.lvlselectbuttons[i], x, y)) {
				Gui.lvlselectbuttons[i].setHover(true);
			} else {
				Gui.lvlselectbuttons[i].setHover(false);

			}
		}

		if (Collision.cButton(Gui.lvlselectbutton_back, x, y)) {
			Gui.lvlselectbutton_back.setHover(true);
		} else {
			Gui.lvlselectbutton_back.setHover(false);

		}

		if (Gamestate.state == Gamestate_e.ingame) {
			if (Collision.cButton(Gui.ingamebutton_restart, x, y)) {
				Gui.ingamebutton_restart.setHover(true);
			} else {
				Gui.ingamebutton_restart.setHover(false);

			}
		}

	}

}
