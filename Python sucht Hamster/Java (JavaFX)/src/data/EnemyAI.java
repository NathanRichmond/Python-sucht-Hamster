package data;

import chars.Enemy;
import chars.Player;
import game.Game;
import gui.Grid;
import gui.Gui;

public class EnemyAI {

	private Player p = Game.p;
	private Enemy e;

	/*
	 * Area around the player that is blocked for enemy. Input: 1 = whole grid; 100
	 * = 1 tile. (exponential)
	 */
	private int playerMargin = CustomMath.calcPlayerMargin(Game.getPlayerMargin());

	private int moveRand;
	private boolean overwriteDirections = false;

	public EnemyAI(Enemy enemy) {
		e = enemy;
	}

	public int moveDirection() {
		do {
			moveRand = randomMove(); // generate random direction
			setCoords(moveRand);
		} while (isValidMove() == false);
		return moveRand;

	}

	private boolean isValidMove() {
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
			// don't walk into walls or babyhamsters
			if (Collision.cWall(e.getxAfterMove(), e.getyAfterMove()) == true
					|| Collision.cBabyhamster(e.getxAfterMove(), e.getyAfterMove()) == true) {
				flag = false;
			}
		}

		return flag;
	}

	private void leavePlayerMargin() {
		/*
		 * Detect position relative to player
		 */
		int pos = 0;
		/*
		 * int pos: 0 = North. 1 = Northeast. 2 = East. 3 = South-east. 4 = South. 5 =
		 * South-west. 6 = West. 7 = Northwest.
		 */
		if (e.getY() < p.getY()) { // if N of player
			pos = 0;
			if (e.getX() > p.getX()) {
				pos = 1;
			}
			if (e.getX() < p.getX()) {
				pos = 7;
			}
		}
		if (e.getX() > p.getX()) { // if E of player
			pos = 2;
		}
		if (e.getY() > p.getY()) { // if S of player
			pos = 4;
			if (e.getX() > p.getX()) {
				pos = 3;
			}
			if (e.getX() < p.getX()) {
				pos = 5;
			}
		}
		if (e.getX() < p.getX()) { // if W of player
			pos = 6;
		}

		/*
		 * Generate random move number that won't move enemy towards player. If pos == 1
		 * || 3 || 5 || 7, that doesn't matter --> dir1 = 4 (don't care)
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

	private void leavePlayerMargin(int dir1, int dir2, int dir3) { // dir1: preferred direction
		int rand = 0;
		if (dir1 < 4) { // if dir1 != don't care
			rand = dir1;
		} else {
			if (dir1 > 3) { // if dir1 == don't care (can also be because dir1 is blocked)
				if (overwriteDirections == false) {
					do {
						rand = (int) (Math.random() * 4);
					} while (rand != dir2 && rand != dir3); // pick random from dir2 and dir3
				} else {
					rand = (int) (Math.random() * 4);
					overwriteDirections = false;
				}
			}
		}
		setCoords(rand);
		if (withinGridAfterMove() == true && Collision.cWall(e.getxAfterMove(), e.getyAfterMove()) == false
				&& Collision.cBabyhamster(e.getxAfterMove(), e.getyAfterMove()) == false) {
			moveRand = rand;
		} else { // if dir1 is blocked (wall or end of grid)
			try {
				leavePlayerMargin(4, dir2, dir3); // don't care about dir1 anymore
			} catch (StackOverflowError e) {
				overwriteDirections = true; // if stuck, don't care about any directions anymore
				leavePlayerMargin(4, dir2, dir3);
			}
		}
	}

	// check if enemy is within grid after moving
	private boolean withinGridAfterMove() {
		boolean flag = false;

		if (e.getyAfterMove() > Grid.getY() && e.getxAfterMove() < Grid.getX() + Grid.getWidth()
				&& e.getyAfterMove() < Grid.getY() + Grid.getHeight() && e.getxAfterMove() > Grid.getX()) {
			flag = true;
		}

		return flag;
	}

	// check if enemy is within playerMargin after moving
	private boolean nearPlayerAfterMove() {
		if (e.getxAfterMove() > (p.getX() - playerMargin) && e.getxAfterMove() < (p.getX() + playerMargin)
				&& e.getyAfterMove() > (p.getY() - playerMargin) && e.getyAfterMove() < (p.getY() + playerMargin)) {
			return true;
		} else {
			return false;
		}

	}

	// check if enemy is within playerMargin
	private boolean nearPlayer() {
		if (e.getX() > (p.getX() - playerMargin) && e.getX() < (p.getX() + playerMargin)
				&& e.getY() > (p.getY() - playerMargin) && e.getY() < (p.getY() + playerMargin)) {
			return true;
		} else {
			return false;
		}

	}

	private int randomMove() {
		int randomMove = (int) (Math.random() * 4); // generate random int 0-3
		setCoords(randomMove);
		return randomMove;
	}

	public void setCoords(int direction) {
		switch (direction) {
		case 0:
			e.setyAfterMove(e.getY() - Gui.getTile());
			e.setxAfterMove(e.getX());
			break;
		case 1:
			e.setxAfterMove(e.getX() + Gui.getTile());
			e.setyAfterMove(e.getY());
			break;
		case 2:
			e.setyAfterMove(e.getY() + Gui.getTile());
			e.setxAfterMove(e.getX());
			break;
		case 3:
			e.setxAfterMove(e.getX() - Gui.getTile());
			e.setyAfterMove(e.getY());
			break;
		default:
			break;
		}
	}

}
