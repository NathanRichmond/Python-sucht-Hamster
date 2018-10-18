package chars;

import actions.Collision;
import data.CustomMath;
import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;

public class Enemy {

	private static int x, y;
	private static int width = 24, height = 24;
	private static int faceDirection = 1;
	private static double speed = 0.5; // time in seconds before enemy makes another move
	private static boolean isAlive = true;

	public Enemy() { // spawns at random position
		x = CustomMath.genRandom();
		y = CustomMath.genRandom();
	}

	public static void move(int direction) {
		if (Gamestate.state == Gamestate_e.ingame || Gamestate.state == Gamestate_e.defeat) {
			if (isAlive) {
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
					killEnemy();
				}
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

	public static void killEnemy() {
		isAlive = false;
		Gamestate.state = Gamestate_e.victory;
	}

	public static void taunt() { // move to center square and spin
		x = 170;
		y = 170;
		turn(2);
		turn(3);
		turn(0);
		turn(1);
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Enemy.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Enemy.y = y;
	}

	public static int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		Enemy.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Enemy.height = height;
	}

	public static int getFaceDirection() {
		return faceDirection;
	}

	public void setFaceDirection(int faceDirection) {
		Enemy.faceDirection = faceDirection;
	}

	public static double getSpeed() {
		return speed;
	}

	public static void setSpeed(double speed) {
		Enemy.speed = speed;
	}

	public static boolean isAlive() {
		return isAlive;
	}

	public static void setAlive(boolean isAlive) {
		Enemy.isAlive = isAlive;
	}

}
