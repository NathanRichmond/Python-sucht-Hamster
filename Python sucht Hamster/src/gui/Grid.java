package gui;

import actions.Game;

public class Grid {

	private static int numberOfTiles = 10;
	private static int x = 20; // position in window
	private static int y = 20;
	private static int width = 331;
	private static int height = 331;

	public Grid() {
		Grid.numberOfTiles = Game.getGridsize();
		width = (int) (getNumberOfTiles() * 33.1); // 33.1: width of one tile
		height = (int) (getNumberOfTiles() * 33.1);
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

	public static int getNumberOfTiles() {
		return numberOfTiles;
	}

}
