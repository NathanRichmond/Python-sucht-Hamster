package clocks;

import java.util.ArrayList;

import actions.Game;
import chars.Enemy;
import chars.Player;
import data.Collision;
import game.Wall;

public class Wall_Creation {

	public static ArrayList<Wall> walls = new ArrayList<>(); // global array list with all Walls

	public Wall_Creation() {
		/*
		 * Add a Wall with random coordinates to the array until nWalls is reached
		 */
		for (int i = 0; i < Game.getnWalls(); i++) {
			walls.add(new Wall());
		}

		/*
		 * Remove all walls that accidentally somehow spawned on Player/Enemy
		 */
		Player p = Game.p;
		for (int i = 0; i < walls.size(); i++) {
			for (Enemy e : Game.enemies) {
				if (Collision.cWall(p.getX(), p.getY()) == true || Collision.cWall(e.getX(), e.getY()) == true) {
					walls.remove(i);
				}
			}
		}

		/*
		 * Planned placement for the Tutorial levels
		 */
		switch (Game.getLevel()) {
		case 104:
			setWallsLevel104();
			break;
		case 105:
			setWallsLevel105();
			break;
		case 106:
			setWallsLevel106();
			break;
		case 107:
			setWallsLevel107();
			break;
		case 108:
			setWallsLevel108();
			break;
		default:
			break;
		}
	}

	private void setWallsLevel108() {
		int amount = 3; // amount of Walls in this level

	}

	private void setWallsLevel107() {
		int amount = 3; // amount of Walls in this level

	}

	private void setWallsLevel106() {
		int amount = 3; // amount of Walls in this level

	}

	private void setWallsLevel105() {
		int amount = 3; // amount of Walls in this level

	}

	private void setWallsLevel104() {
		int amount = 3; // amount of Walls in this level
		for (int i = 0; i < amount; i++) {
			Wall st = new Wall(); // generate new Wall
			/*
			 * Set x and y coordinates
			 */
			switch (i) {
			case 1: // first Wall
				st.setX(4 * 33 + 1); // x coordinate of this particular Wall
				st.setY(1 * 33 + 1); // y coordinate of this particular Wall
				break;
			case 2: // second Wall
				st.setX(4 * 33 + 1); // x coordinate of this particular Wall
				st.setY(2 * 33 + 1); // y coordinate of this particular Wall
				break;
			case 3: // third Wall
				st.setX(4 * 33 + 1); // x coordinate of this particular Wall
				st.setY(3 * 33 + 1); // y coordinate of this particular Wall
				break;
			}
			walls.add(st);
		}
	}

}
