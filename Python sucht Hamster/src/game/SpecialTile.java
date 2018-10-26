package game;

import data.Collision;
import data.CustomMath;
import gui.Grid;

public class SpecialTile {

	private int x, y;
	private int width = 32, height = 32;
	private String type;
	private boolean isAlive;

	public SpecialTile(String type) {
		this.setValidSpawn();
		this.isAlive = true;
		this.type = type;
	}

	public void activate(String activatedBy) {
		this.setAlive(false);
		switch (getType()) {
		case "korn":
			activateKorn(activatedBy);
			break;
		case "hourglass":
			activateHourglass(activatedBy);
			break;
		case "hammer":
			activateHammer(activatedBy);
			break;
		}
	}

	private void activateKorn(String activatedBy) {
		if (activatedBy == "enemy") {
			// increase Enemy speed for limited period of time
		} else {
			if (activatedBy == "player") {
				// ?
			}
		}
	}

	private void activateHourglass(String activatedBy) {
		if (activatedBy == "enemy") {
			// increase game speed for limited period of time
		} else {
			if (activatedBy == "player") {
				// slow down game speed for limited period of time
			}
		}
	}

	private void activateHammer(String activatedBy) {
		if (activatedBy == "enemy") {
			// generate Walls behind Enemy
		} else {
			if (activatedBy == "player") {
				// generate Walls in front of Player
			}
		}
	}

	private void setValidSpawn() {
		/*
		 * Generate random spawn point. Don't spawn inside Player/Enemy or on top of a
		 * Wall.
		 */
		int x, y;
		x = CustomMath.genRandomX();
		y = CustomMath.genRandomY();
		if (Collision.cWall(x, y) == false && Collision.cPlayerOrEnemy(x, y) == false
				&& Collision.cSpecialTile(x, y) == false) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
