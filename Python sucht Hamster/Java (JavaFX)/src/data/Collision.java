package data;

import chars.Enemy;
import chars.Player;
import game.Game;
import game.Gamestate;
import game.Gamestate_e;
import game.SpecialTile;
import game.SpecialTile_Creation;
import game.Wall;
import game.Wall_Creation;
import gui.Button;

public class Collision {

	private static Player p;

	public Collision() {
		p = Game.p;
	}
	
	/*
	 * Check collision with any button
	 */
	public static boolean cButton(Button button, int x, int y) {
		return (x < button.getX() + button.getWidth() && x > button.getX() && y < button.getY() + button.getHeight()
				&& y > button.getY());
	}

	// Always round all coordinates to tile, because the dynmic sizing of all game
	// elements might sometimes result in unfortunate rounding
	
	/*
	 * Check collision of Enemy and Player. Caution: Although this method is a
	 * boolean method, it kills the Enemy that was collided with in the process.
	 */
	public static boolean cEnemyPlayer() {
		p = Game.p;
		boolean flag = false;
		if (Gamestate.state == Gamestate_e.ingame) {
			for (Enemy e : Game.enemies) {
				if (CustomMath.roundToTile(p.getX()) == CustomMath.roundToTile(e.getX())
						&& CustomMath.roundToTile(p.getY()) == CustomMath.roundToTile(e.getY())) {
					e.killEnemy(); // Kill Enemy that Player collided with
					flag = true;
				} else {
					flag = false;
				}
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
			if (CustomMath.roundToTile(x) == CustomMath.roundToTile(w.getX())
					&& CustomMath.roundToTile(y) == CustomMath.roundToTile(w.getY())) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/*
	 * Check collision with any Special Tile
	 */
	public static boolean cSpecialTile(int x, int y) {
		boolean flag = false;
		for (SpecialTile st : SpecialTile_Creation.specialtiles) {
			if (CustomMath.roundToTile(x) == CustomMath.roundToTile(st.getX())
					&& CustomMath.roundToTile(y) == CustomMath.roundToTile(st.getY())) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/*
	 * Check collision with any Babyhamster (as they are like Walls for Enemies)
	 */
	public static boolean cBabyhamster(int x, int y) {
		boolean flag = false;
		if (Game.isSpecialTiles() == true) {
			for (SpecialTile st : SpecialTile_Creation.specialtiles) {
				if ((st.getType() == "babyhamsterTwo" || st.getType() == "babyhamsterThree"
						|| st.getType() == "babyhamsterFour")
						&& CustomMath.roundToTile(x) == CustomMath.roundToTile(st.getX())
						&& CustomMath.roundToTile(y) == CustomMath.roundToTile(st.getY())) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/*
	 * Check collision with Player or Enemy. Used for determining valid spawns of
	 * Wall and Special Tile
	 */
	public static boolean cPlayerOrEnemy(int x, int y) {
		p = Game.p;
		boolean flag = false;
		if (CustomMath.roundToTile(x) == CustomMath.roundToTile(p.getX())
				&& CustomMath.roundToTile(y) == CustomMath.roundToTile(p.getY())) {
			flag = true;
		}
		for (Enemy e : Game.enemies) {
			if (CustomMath.roundToTile(x) == CustomMath.roundToTile(e.getX())
					&& CustomMath.roundToTile(y) == CustomMath.roundToTile(e.getY())) {
				flag = true;
			}
		}
		return flag;
	}

	/*
	 * Check collision with Player. Used for determining valid spawn of Enemy
	 */
	public static boolean cPlayer(int x, int y) {
		p = Game.p;
		boolean flag = false;
		if (CustomMath.roundToTile(x) == CustomMath.roundToTile(p.getX())
				&& CustomMath.roundToTile(y) == CustomMath.roundToTile(p.getY())) {
			flag = true;
		} else {
			flag = false;

		}
		return flag;
	}

}
