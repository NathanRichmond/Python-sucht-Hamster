package actions;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import chars.Player;

public class KeyReleased implements EventHandler<KeyEvent> {

	private static Player p;

	@Override
	public void handle(KeyEvent event) {

		p = Main.p;

		switch (event.getCode()) {
		case UP:
			p.move(0);
			break;
		case RIGHT:
			p.move(1);
			break;
		case DOWN:
			p.move(2);
			break;
		case LEFT:
			p.move(3);
			break;
		default:
			break;
		}

	}

}
