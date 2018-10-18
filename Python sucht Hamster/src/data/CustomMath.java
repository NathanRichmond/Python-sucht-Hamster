package data;

import gui.Gui;

public class CustomMath {

	/*
	 * Generate a random int that matches character positioning within the grid:
	 * 1. create random int between 0 and gridwidth-3
	 * 2. repeat until that int can be divided by 33 (as this is the width of one square)
	 * 3. add +5 to create offset (center character in square) and return
	 */

	public static int genRandom() {
		int random;
		do {
			random = (int) (Math.random() * Gui.getGridwidth() - 3);
		} while (random % 33 != 0);
		return random + 5;
	}

}
