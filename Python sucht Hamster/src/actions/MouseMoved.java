package actions;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import gui.Gui;

public class MouseMoved implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {

		for (int i = 0; i < Gui.pausebuttons.length; i++) {
			if (Collision.cButton(Gui.pausebuttons[i], (int) event.getX(), (int) event.getY())) {
				Gui.pausebuttons[i].setHover(true);
			} else {
				Gui.pausebuttons[i].setHover(false);

			}
		}

		for (int i = 0; i < Gui.startmenubuttons.length; i++) {
			if (Collision.cButton(Gui.startmenubuttons[i], (int) event.getX(), (int) event.getY())) {
				Gui.startmenubuttons[i].setHover(true);
			} else {
				Gui.startmenubuttons[i].setHover(false);

			}
		}

		for (int i = 0; i < Gui.gameendbuttons.length; i++) {
			if (Collision.cButton(Gui.gameendbuttons[i], (int) event.getX(), (int) event.getY())) {
				Gui.gameendbuttons[i].setHover(true);
			} else {
				Gui.gameendbuttons[i].setHover(false);

			}
		}
	}

}
