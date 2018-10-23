package actions;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import game.Gamestate;
import game.Gamestate_e;

public class KeyPressed implements EventHandler<KeyEvent> {

	@Override
	public void handle(KeyEvent event) {

		switch (event.getCode()) {
		case ESCAPE: // ESC: switch between pause-ingame
			if (Gamestate.state == Gamestate_e.pause) {
				Gamestate.state = Gamestate_e.ingame;
			} else {
				if (Gamestate.state == Gamestate_e.ingame) {
					Gamestate.state = Gamestate_e.pause;
				}
			}
			break;
		default:
			break;
		}
	}
}
