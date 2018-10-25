package data;

import actions.Game;
import chars.Enemy;
import chars.Player;
import clocks.Korn_Creation;
import clocks.Wall_Creation;
import game.Gamestate;
import game.Gamestate_e;
import game.Korn;
import game.Wall;
import gui.Button;

public class Collision {

	private static Player p;
	private static Enemy e;

	public Collision() {
		p = Game.p;
		e = Game.e;
	}

	/*
	 * Check collision with any button
	 */
	public static boolean cButton(Button button, int x, int y) {
		return (x < button.getX() + button.getWidth() && x > button.getX() && y < button.getY() + button.getHeight()
				&& y > button.getY());
	}

	/*
	 * Check collision of Enemy and Player
	 */
	public static boolean cEnemyPlayer() {
		p = Game.p;
		e = Game.e;
		boolean flag = false;
		if (Gamestate.state == Gamestate_e.ingame) {
			if (p.getX() == e.getX() && p.getY() == e.getY()) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/*
	 * Check collision with any Wall
	 */
	public static boolean cWall(int x, int y) {
		boolean flag = false;
		for (Wall w : Wall_Creation.walls) {
			if (x == w.getX() && y == w.getY()) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/*
	 * Check collision with any Korn
	 */
	public static boolean cKorn(int x, int y) {
		boolean flag = false;
		for (Korn k : Korn_Creation.koerner) {
			if (x == k.getX() && y == k.getY()) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/*
	 * Check collision with Player or Enemy. Used for determining valid spawns of
	 * Wall and Korn
	 */
	public static boolean cPlayerOrEnemy(int x, int y) {
		p = Game.p;
		e = Game.e;
		boolean flag = false;
		if ((x == p.getX() && y == p.getY()) || (x == e.getX() && y == e.getY())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/*
	 * Check collision with Player. Used for determining valid spawn of Enemy
	 */
	public static boolean cPlayer(int x, int y) {
		p = Game.p;
		boolean flag = false;
		if (x == p.getX() && y == p.getY()) {
			flag = true;
		} else {
			flag = false;

		}
		return flag;
	}

}
