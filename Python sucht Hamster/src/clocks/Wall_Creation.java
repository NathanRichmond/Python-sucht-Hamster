package clocks;

import java.util.ArrayList;

import game.Wall;

public class Wall_Creation {

	public static ArrayList<Wall> walls = new ArrayList<>();
	private int numberOfWalls = 5;

	public Wall_Creation() {
		/*
		 * Add a Wall with random coordinates to the array until numberOfWalls is reached
		 */
		for (int i = 0; i < numberOfWalls; i++) {
			walls.add(new Wall());
		}
	}

}
