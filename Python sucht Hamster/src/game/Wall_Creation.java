package game;

import java.util.ArrayList;

import chars.Enemy;
import chars.Player;
import data.Collision;
import gui.Grid;

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

	private void setWallsLevel104() {
		addWall(4, 1);
		addWall(4, 2);
		addWall(4, 3);
	}

	private void setWallsLevel105() {
		addWall(2, 2);
		addWall(3, 2);
		addWall(4, 2);
		addWall(5, 2);
		addWall(5, 3);
		addWall(5, 4);
		addWall(5, 5);
		addWall(4, 5);
		addWall(3, 5);
		addWall(2, 5);
		addWall(2, 4);
		addWall(2, 3);
	}

	private void setWallsLevel106() {
		addWall(3, 0);
		addWall(3, 1);
		addWall(3, 4);
		addWall(3, 5);
		addWall(3, 6);
		addWall(3, 7);
		addWall(7, 0);
		addWall(7, 1);
		addWall(7, 2);
		addWall(7, 3);
		addWall(7, 4);
		addWall(7, 6);
		addWall(7, 7);
		addWall(10, 0);
		addWall(10, 3);
		addWall(10, 4);
		addWall(10, 5);
		addWall(10, 6);
		addWall(10, 7);
	}

	private void setWallsLevel107() {
		addWall(1, 1);
		addWall(1, 2);
		addWall(2, 1);
		addWall(2, 2);
		addWall(3, 1);
		addWall(3, 2);
		addWall(4, 1);
		addWall(4, 2);
		addWall(5, 1);
		addWall(5, 2);
		addWall(7, 0);
		addWall(7, 1);
		addWall(8, 1);
		addWall(9, 1);
		addWall(10, 1);
		addWall(11, 1);
		addWall(12, 1);
		addWall(13, 1);
	}

	private void setWallsLevel108() {
		for (int y = 0; y < 5; y++) {
			if (y != 2) { // leave space at y=2
				for (int x = 0; x < 5; x++) {
					addWall(x, y);
				}
			}
		}
		addWall(8, 0);
		addWall(8, 1);
		addWall(8, 3);
		addWall(8, 4);
	}

	/*
	 * Add Wall manually
	 */
	private void addWall(int x, int y) {
		Wall w = new Wall();
		w.setX(Grid.getX() + (x * 33 + 1));
		w.setY(Grid.getY() + (y * 33 + 1));
		walls.add(w);
	}

}
