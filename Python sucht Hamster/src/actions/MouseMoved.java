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

		/*
		 * PAUSE Buttons
		 */
		for (int i = 0; i < Gui.pausebuttons.length; i++) {
			if (Collision.cButton(Gui.pausebuttons[i], x, y)) {
				Gui.pausebuttons[i].setHover(true);
			} else {
				Gui.pausebuttons[i].setHover(false);

			}
		}

		/*
		 * START MENU Buttons
		 */
		for (int i = 0; i < Gui.startmenubuttons.length; i++) {
			if (Collision.cButton(Gui.startmenubuttons[i], x, y)) {
				Gui.startmenubuttons[i].setHover(true);
			} else {
				Gui.startmenubuttons[i].setHover(false);

			}
		}

		/*
		 * SETTINGS Buttons
		 */
		for (int i = 0; i < Gui.settingsbuttons.length; i++) {
			if (Collision.cButton(Gui.settingsbuttons[i], x, y)) {
				Gui.settingsbuttons[i].setHover(true);
			} else {
				Gui.settingsbuttons[i].setHover(false);

			}
		}

		/*
		 * DEFEAT/VICTORY Buttons
		 */
		for (int i = 0; i < Gui.gameendbuttons.length; i++) {
			if (Collision.cButton(Gui.gameendbuttons[i], x, y)) {
				Gui.gameendbuttons[i].setHover(true);
			} else {
				Gui.gameendbuttons[i].setHover(false);

			}
		}
		if (Game.getLevel() + 1 <= Gui.getnLvls()) {
			if (Collision.cButton(Gui.victorybutton_nextlvl, x, y)) {
				Gui.victorybutton_nextlvl.setHover(true);
			} else {
				Gui.victorybutton_nextlvl.setHover(false);

			}
		}

		/*
		 * LEVEL SELECT Buttons
		 */
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

		/*
		 * INGAME Restart Button
		 */
		if (Gamestate.state == Gamestate_e.ingame) {
			if (Collision.cButton(Gui.ingamebutton_restart, x, y)) {
				Gui.ingamebutton_restart.setHover(true);
			} else {
				Gui.ingamebutton_restart.setHover(false);

			}
		}

	}

}
