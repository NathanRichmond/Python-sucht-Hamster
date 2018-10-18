package chars;

import actions.Collision;
import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;

public class Player {

	private static int x, y;
	private static int width = 24, height = 24;
	private static int faceDirection = 0;

	public Player() {
		x = 5;
		y = Gui.getGridheight() - 29;
	}

	public static void move(int direction) {
		if (Gamestate.state == Gamestate_e.ingame) {
			switch (direction) {
			case 0:
				if ((y - 33) > 0) { // don't leave the grid
					turn(0);
					y = y - 33; // move up
				}
				break;
			case 1:
				if ((x + 33) < Gui.getGridwidth()) { // don't leave the grid
					turn(1);
					x = x + 33; // move right
				}
				break;
			case 2:
				if ((y + 33) < Gui.getGridheight()) { // don't leave the grid
					turn(2);
					y = y + 33; // move down
				}
				break;
			case 3:
				if ((x - 33) > 0) { // don't leave the grid
					turn(3);
					x = x - 33; // move left
				}
				break;
			}
			if (Collision.cEnemyPlayer() == true) {
				Enemy.killEnemy();
			}
		}
	}

	private static void turn(int direction) {
		switch (direction) {
		case 0: // turn upwards
			faceDirection = 0;
			break;
		case 1: // turn right
			faceDirection = 1;
			break;
		case 2: // turn downwards
			faceDirection = 2;
			break;
		case 3: // turn left
			faceDirection = 3;
			break;
		}
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Player.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Player.y = y;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Player.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Player.height = height;
	}

	public static int getFaceDirection() {
		return faceDirection;
	}

	public static void setFaceDirection(int faceDirection) {
		Player.faceDirection = faceDirection;
	}

}
