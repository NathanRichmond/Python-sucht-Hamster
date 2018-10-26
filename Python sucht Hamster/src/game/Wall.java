package game;

import data.Collision;
import data.CustomMath;
import gui.Grid;

public class Wall {

	private int x, y;
	private int width = 32, height = 32;

	/*
	 * Constructor without parameters: For initial random distribution
	 */
	public Wall() {
		setValidSpawn();
	}
	
	/*
	 * Constructor with parameters: For adding specific Walls (e.g. through Special Tiles)
	 */
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private void setValidSpawn() {
		/*
		 * Generate random spawn point. Don't spawn inside Player/Enemy or on top of a
		 * Special Tile.
		 */
		int x, y;
		x = CustomMath.genRandomX();
		y = CustomMath.genRandomY();
		if (Collision.cSpecialTile(x, y) == false && Collision.cPlayerOrEnemy(x, y) == false
				&& Collision.cWall(x, y) == false) {
			this.setX(Grid.getX() + x);
			this.setY(Grid.getY() + y);
		} else {
			setValidSpawn();
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
