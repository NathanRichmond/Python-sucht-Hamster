package data;

import gui.Grid;
import gui.Gui;

public class CustomMath {

	/*
	 * Generate a random int that matches character positioning within the grid:
	 * 1. Calculate number of tiles in the grid
	 * 2. Generate random int in the range 0 - number of tiles.
	 * 3. Multiply that with number of tiles to have real coordinates.
	 * 3. Add +1 to create offset (center character in tile) and return.
	 */
	public static int genRandomX() {
		int tiles = (Grid.getWidth() - 1) / Gui.getTile();
		int random = (int) (Math.random() * tiles) * Gui.getTile() + 1;
		return random;
	}

	public static int genRandomY() {
		int tiles = (Grid.getHeight() - 1) / Gui.getTile();
		int random = (int) (Math.random() * tiles) * Gui.getTile() + 1;
		return random;
	}

	/*
	 * Round input to a multiple of tile size.
	 * Round down if difference is <(half of tile size), round up otherwise
	 */
	public static int roundToTile(int input) {
		int result = 0;
		int halfOfTile = Math.round(Gui.getTile() / 2);
		if (input % Gui.getTile() <= halfOfTile) {
			result = input - (input % Gui.getTile());
		} else {
			result = input + (Gui.getTile() - (input % Gui.getTile()));
		}
		return result;
	}

	/*
	 * Divide size of grid by factor. Round that to a multiple of 33
	 */
	public static int calcPlayerMargin(double factor) {
		return roundToTile((int) Math.round(((Grid.getWidth() - 1) + (Grid.getHeight() - 1) / 2) / factor)) + 1;
	}

}
