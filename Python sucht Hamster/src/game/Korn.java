package game;

import actions.Collision;
import data.CustomMath;
import gui.Grid;

public class Korn {

	private int x, y;
	private int width = 32, height = 32;
	public boolean isConsumed;

	public Korn() {
		this.setValidSpawn();
		this.isConsumed = false;
	}

	public void consume() {
		this.setConsumed(true);
	}

	private void setValidSpawn() {
		/*
		 * Generate random spawn point. Don't spawn inside Player/Enemy or on top of a
		 * Wall.
		 */
		int x, y;
		do {
			x = CustomMath.genRandom();
			y = CustomMath.genRandom();
		} while (Collision.cWall(x, y) == true || Collision.cPlayerOrEnemy(x, y) == true);
		this.setX(Grid.getX() + x);
		this.setY(Grid.getY() + y);
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

	public boolean isConsumed() {
		return isConsumed;
	}

	public void setConsumed(boolean isConsumed) {
		this.isConsumed = isConsumed;
	}

}
