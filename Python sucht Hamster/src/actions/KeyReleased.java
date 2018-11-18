package actions;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import chars.Player;

public class KeyReleased implements EventHandler<KeyEvent> {

	private static Player p;
	 

	@Override
	public void handle(KeyEvent event) {

		p = Game.p;

		switch (event.getCode()) {
		case UP:
			p.move(0);
			sc();
			break;
		case RIGHT:
			p.move(1);
			sc(); 
			break;
		case DOWN:
			p.move(2);
			sc();
			break;
		case LEFT:
			p.move(3);
			sc();
			break;
		default:
			break;
		}
		
	}

	private void sc() {
		if (Game.isFirstKeyPressInGame() == true) {
			Game.startClocks();
		}
	}
	

	}


