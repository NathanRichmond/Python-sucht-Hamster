package actions;

import chars.Enemy;
import chars.Player;
import gui.Button;

public class Collision {

	public static boolean cButton(Button button, int x, int y) {
		return (x < button.getX() + button.getWidth() && x > button.getX() && y < button.getY() + button.getHeight()
				&& y > button.getY());
	}

	public static boolean cEnemyPlayer() {
		if (Player.getX() == Enemy.getX() && Player.getY() == Enemy.getY()) { // both have the same coordinates
			return true;
		} else {
			return false;
		}
	}

}
