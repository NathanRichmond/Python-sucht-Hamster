package data;

import gui.Grid;

public class CustomMath {

	/*
	 * Generate a random int that matches character positioning within the grid:
	 * 1. Create random int between 0 and gridwidth-3.
	 * 2. Repeat until that int can be divided by 33 (as this is the width of one square).
	 * 3. Add +1 to create offset (center character in square) and return.
	 */
	public static int genRandomX() {
		int random;
		do {
			random = (int) (Math.random() * Grid.getWidth() - 3);
		} while (random % 33 != 0);
		return random + 1;
	}

	public static int genRandomY() {
		int random;
		do {
			random = (int) (Math.random() * Grid.getHeight() - 3);
		} while (random % 33 != 0);
		return random + 1;
	}

	/*
	 * Round input to a multiple of 33.
	 * Round down if difference is <16, round up otherwise
	 */
	public static int roundTo33(int input) {
		int result = 0;
		if (input % 33 <= 16) {
			result = input - (input % 33);
		} else {
			result = input + (33 - (input % 33));
		}
		return result;
	}

	/*
	 * Divide size of grid by input. Round that to a multiple of 33
	 */
	public static int calcPlayerMargin(double input) {
		return roundTo33((int) Math.round((Grid.getWidth() - 1) / input)) + 1;
	}

}
