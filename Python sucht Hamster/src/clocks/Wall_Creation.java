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
		Enemy e = Game.e;
		for (int i = 0; i < walls.size(); i++) {
			if (Collision.cWall(p.getX(), p.getY()) == true || Collision.cWall(e.getX(), e.getY()) == true)
				walls.remove(i);
		}
	}

}
