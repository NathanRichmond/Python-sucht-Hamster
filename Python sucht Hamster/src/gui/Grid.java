package gui;

import game.Game;

public class Grid {

	private static String size;
	private static int x; // position in window
	private static int y;
	private static int width;
	private static int height;

	public Grid() {
		size = Game.getGridsize();

		/*
		 * Determine grid width and height: Take string input (e.g. "20x05"), explode
		 * into "20" and "05" and convert to int. Then multiply with 33 (width/height of
		 * one grid tile) and set this as width/height.
		 */
		String strWidth = size.substring(0, 2); // part in front of the "x"
		String strHeight = size.substring(3); // part behind the "x"
		int intWidth = Integer.parseInt(strWidth); // convert String to int
		int intHeight = Integer.parseInt(strHeight);
		width = (intWidth * Gui.getTile()) + 1;
		height = (intHeight * Gui.getTile()) + 1;

		setX(Gui.getWidth() / 2 - getWidth() / 2);

		if (Game.getLevel() < 100) { // regular levels (that have a level title)
			setY((Gui.getHeight() / 2 - getHeight() / 2) / 2
					+ (int) (Gui.getHeight() / 8.2285714285714285714285714285714));
		} else { // Tutorial levels (that have no level title)
			setY(Gui.getHeight() / 2 - getHeight() / 2);
		}
	}
	
	public Grid(int width, int height, int x, int y) { // constr for navigational manual grid
		Grid.width = width;
		Grid.height = height;
		Grid.x = x;
		Grid.y = y;
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Grid.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Grid.y = y;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Grid.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Grid.height = height;
	}

	public static String getSize() {
		return size;
	}

}
