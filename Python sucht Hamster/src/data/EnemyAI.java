package data;

import chars.Enemy;
import chars.Player;
import gui.Gui;

public class EnemyAI {

	/*
	 * Area around the player that is blocked for enemy;
	 * input: 1 = whole grid; 100 = 1 tile
	 */
	public static int playerMargin = CustomMath.calcPlayerMargin(4);

	public static int moveRand;

	public int moveDirection() {
		do {
			moveRand = randomMove(); // generate random direction
		} while (isValidMove() == false);
		setCoords(moveRand);
		return moveRand;

	}

	private static boolean isValidMove() {
		boolean flag = false;

		if (withinGridAfterMove() == true) {
			if (nearPlayer() == true && nearPlayerAfterMove() == true) { // if leaving pM within one move isn't possible
				leavePlayerMargin();
				flag = true;
			} else {
				if (nearPlayerAfterMove() == false) { // always try to leave playerMargin
					flag = true;
				}
			}
		}

		return flag;
	}

	private static void leavePlayerMargin() {

		/*
		 * Detect position relative to player
		 */
		int pos = 0;
		/*
		 * INT POS:
		 * 0 = North;
		 * 1 = Northeast;
		 * 2 = East;
		 * 3 = South-east;
		 * 4 = South;
		 * 5 = South-west;
		 * 6 = West;
		 * 7 = Northwest
		 */
		if (Enemy.getY() < Player.getY()) { // if N of player
			pos = 0;
			if (Enemy.getX() > Player.getX()) {
				pos = 1;
			}
			if (Enemy.getX() < Player.getX()) {
				pos = 7;
			}
		}
		if (Enemy.getX() > Player.getX()) { // if E of player
			pos = 2;
		}
		if (Enemy.getY() > Player.getY()) { // if S of player
			pos = 4;
			if (Enemy.getX() > Player.getX()) {
				pos = 3;
			}
			if (Enemy.getX() < Player.getX()) {
				pos = 5;
			}
		}
		if (Enemy.getX() < Player.getX()) { // if W of player
			pos = 6;
		}

		/*
		 * Generate random move number that won't move enemy towards player;
		 * If pos == 1 || 3 || 5 || 7, that doesn't matter --> dir1 = 4 (don't care)
		 */
		switch (pos) {
		case 0:
			leavePlayerMargin(0, 1, 3);
			break;
		case 1:
			leavePlayerMargin(4, 0, 1);
			break;
		case 2:
			leavePlayerMargin(1, 0, 2);
			break;
		case 3:
			leavePlayerMargin(4, 1, 2);
			break;
		case 4:
			leavePlayerMargin(2, 1, 3);
			break;
		case 5:
			leavePlayerMargin(4, 2, 3);
			break;
		case 6:
			leavePlayerMargin(3, 0, 2);
			break;
		case 7:
			leavePlayerMargin(4, 0, 3);
			break;
		}

	}

	private static void leavePlayerMargin(int dir1, int dir2, int dir3) { // dir1: preferred direction
		int rand = 0;
		if (dir1 < 4) { // if dir1 != don't care
			rand = dir1;
		} else {
			if (dir1 > 3) { // if dir1 == don't care (can also be because dir1 is blocked)
				do {
					rand = (int) (Math.random() * 4);
				} while (rand != dir2 && rand != dir3); // take random from dir2 and dir3
			}
		}
		setCoords(rand);
		if (withinGridAfterMove() == true) {
			moveRand = rand;
		} else {
			leavePlayerMargin(4, dir2, dir3); // if dir1 is blocked, don't care about dir1 anymore
		}
	}

	// check if enemy is within grid after moving
	private static boolean withinGridAfterMove() {
		boolean flag = false;

		if (Enemy.getyAfterMove() > 0 && Enemy.getxAfterMove() < Gui.getGridwidth()
				&& Enemy.getyAfterMove() < Gui.getGridheight() && Enemy.getxAfterMove() > 0) {
			flag = true;
		}

		return flag;
	}

	// check if enemy is within playerMargin after moving
	private static boolean nearPlayerAfterMove() {
		if (Enemy.getxAfterMove() > (Player.getX() - playerMargin)
				&& Enemy.getxAfterMove() < (Player.getX() + playerMargin)
				&& Enemy.getyAfterMove() > (Player.getY() - playerMargin)
				&& Enemy.getyAfterMove() < (Player.getY() + playerMargin)) {
			return true;
		} else {
			return false;
		}

	}

	// check if enemy is within playerMargin
	private static boolean nearPlayer() {
		if (Enemy.getX() > (Player.getX() - playerMargin) && Enemy.getX() < (Player.getX() + playerMargin)
				&& Enemy.getY() > (Player.getY() - playerMargin) && Enemy.getY() < (Player.getY() + playerMargin)) {
			return true;
		} else {
			return false;
		}

	}

	private static int randomMove() {
		int randomMove = (int) (Math.random() * 4); // generate random int 0-3
		setCoords(randomMove);
		return randomMove;
	}

	public static void setCoords(int direction) {
		switch (direction) {
		case 0:
			Enemy.setyAfterMove(Enemy.getY() - 33);
			Enemy.setxAfterMove(Enemy.getX());
			break;
		case 1:
			Enemy.setxAfterMove(Enemy.getX() + 33);
			Enemy.setyAfterMove(Enemy.getY());
			break;
		case 2:
			Enemy.setyAfterMove(Enemy.getY() + 33);
			Enemy.setxAfterMove(Enemy.getX());
			break;
		case 3:
			Enemy.setxAfterMove(Enemy.getX() - 33);
			Enemy.setyAfterMove(Enemy.getY());
			break;
		default:
			break;
		}
	}

}
