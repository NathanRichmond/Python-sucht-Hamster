package actions;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import game.Game;
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

		// only in Start Menu: Unlock max Levels by typing F1-F12
		if (Gamestate.state == Gamestate_e.startmenu) {
			switch (event.getCode()) {
			case F1:
				Game.setMaxLevelAvailable(1);
				break;
			case F2:
				Game.setMaxLevelAvailable(2);
				break;
			case F3:
				Game.setMaxLevelAvailable(3);
				break;
			case F4:
				Game.setMaxLevelAvailable(4);
				break;
			case F5:
				Game.setMaxLevelAvailable(5);
				break;
			case F6:
				Game.setMaxLevelAvailable(6);
				break;
			case F7:
				Game.setMaxLevelAvailable(7);
				break;
			case F8:
				Game.setMaxLevelAvailable(8);
				break;
			case F9:
				Game.setMaxLevelAvailable(9);
				break;
			case F10:
				Game.setMaxLevelAvailable(10);
				break;
			case F11:
				Game.setMaxLevelAvailable(11);
				break;
			case F12:
				Game.setMaxLevelAvailable(12);
				break;
			default:
				break;
			}
		}
	}

}
