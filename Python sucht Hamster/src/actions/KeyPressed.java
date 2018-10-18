package actions;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import chars.Player;
import game.Gamestate;
import game.Gamestate_e;

public class KeyPressed implements EventHandler<KeyEvent> {

	@Override
	public void handle(KeyEvent event) {

		switch (event.getCode()) {
		case ESCAPE:											//ESC: switch between pause-ingame
			if (Gamestate.state == Gamestate_e.pause) {
				Gamestate.state = Gamestate_e.ingame;
			} else {
				Gamestate.state = Gamestate_e.pause;
			}
			break;
		case UP:
			Player.move(0);
			break;
		case RIGHT:
			Player.move(1);
			break;
		case DOWN:
			Player.move(2);
			break;
		case LEFT:
			Player.move(3);
			break;
		default:
			break;
		}
	}
}
