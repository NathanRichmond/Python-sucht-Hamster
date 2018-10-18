package actions;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import gui.Gui;

public class MouseMoved implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {

		for (int i = 0; i < Gui.buttons.length; i++) {
			if (Collision.cButton(Gui.buttons[i], (int) event.getX(), (int) event.getY())) {
				Gui.buttons[i].setHover(true);
			} else {
				Gui.buttons[i].setHover(false);
			}
		}
	}

}
