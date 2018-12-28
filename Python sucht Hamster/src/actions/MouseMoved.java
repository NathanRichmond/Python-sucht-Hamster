package actions;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import data.Collision;
import game.Game;
import gui.Gui;

public class MouseMoved implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent e) {

		int x = (int) e.getX(); // x coord of current mouse position
		int y = (int) e.getY(); // y coord of current mouse position

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
		for (int i = 0; i < Gui.lvlselectbuttons.length; i++) {
			if (Collision.cButton(Gui.lvlselectbuttons[i], x, y)) {
				Gui.lvlselectbuttons[i].setHover(true);
			} else {
				Gui.lvlselectbuttons[i].setHover(false);

			}
		}

		/*
		 * TUTORIAL MENU Buttons
		 */
		for (int i = 0; i < Gui.tutorialmenubuttons.length; i++) {
			if (Collision.cButton(Gui.tutorialmenubuttons[i], x, y)) {
				Gui.tutorialmenubuttons[i].setHover(true);
			} else {
				Gui.tutorialmenubuttons[i].setHover(false);

			}
		}
		
		/*
		 * INGAME Buttons
		 */
		for (int i = 0; i < Gui.ingamebuttons.length; i++) {
			if (Collision.cButton(Gui.ingamebuttons[i], x, y)) {
				Gui.ingamebuttons[i].setHover(true);
			} else {
				Gui.ingamebuttons[i].setHover(false);

			}
		}

		/*
		 * VICTORY Button
		 */
		if (Game.getLevel() + 1 <= Gui.getnLvls() || Game.getLevel() > 100) {
			if (Collision.cButton(Gui.victorybutton, x, y)) {
				Gui.victorybutton.setHover(true);
			} else {
				Gui.victorybutton.setHover(false);

			}
		}
		
		/*
		 * MANUAL Buttons
		 */
		for (int i = 0; i < Gui.manualbuttons.length; i++) {
			if (Collision.cButton(Gui.manualbuttons[i], x, y)) {
				Gui.manualbuttons[i].setHover(true);
			} else {
				Gui.manualbuttons[i].setHover(false);

			}
		}
		
		/*
		 * ANLEITUNG Buttons
		 */
		for (int i = 0; i < Gui.anleitungsbuttons.length; i++) {
			if (Collision.cButton(Gui.anleitungsbuttons[i], x, y)) {
				Gui.anleitungsbuttons[i].setHover(true);
			} else {
				Gui.anleitungsbuttons[i].setHover(false);

			}
		}
	}

}
