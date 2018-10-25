package chars;

import data.Collision;
import data.CustomMath;
import game.Gamestate;
import game.Gamestate_e;
import gui.Grid;

public class Player {

	private int x, y;
	private int xAfterMove, yAfterMove;
	private int width, height;
	private int faceDirection;
	private boolean isUpgraded;

	public Player() {
		this.setValidSpawn(); // spawns at random position
		this.width = 32;
		this.height = 32;
		this.faceDirection = 0;
		this.isUpgraded = false;
	}

	public void move(int direction) {
		if (Gamestate.state == Gamestate_e.ingame) {
			this.setCoords(direction);
			switch (direction) {
			case 0:
				this.turn(0);
				if (this.yAfterMove > Grid.getY()) { // don't leave the grid
					if (Collision.cWall(this.x, this.yAfterMove) == false) { // don't walk into walls
						this.y = this.yAfterMove; // move up
					}
				}
				break;
			case 1:
				this.turn(1);
				if (this.xAfterMove < Grid.getX() + Grid.getWidth()) { // don't leave the grid
					if (Collision.cWall(this.xAfterMove, this.y) == false) { // don't walk into walls
						this.x = this.xAfterMove; // move right
					}
				}
				break;
			case 2:
				this.turn(2);
				if (this.yAfterMove < Grid.getY() + Grid.getHeight()) { // don't leave the grid
					if (Collision.cWall(this.x, this.yAfterMove) == false) { // don't walk into walls
						this.y = this.yAfterMove; // move down
					}
				}
				break;
			case 3:
				this.turn(3);
				if (this.xAfterMove > Grid.getX()) { // don't leave the grid
					if (Collision.cWall(this.xAfterMove, this.y) == false) { // don't walk into walls
						this.x = this.xAfterMove; // move left
					}
				}
				break;
			}
		}
	}

	private void setCoords(int direction) {
		switch (direction) {
		case 0:
			this.yAfterMove = this.y - 33;
			break;
		case 1:
			this.xAfterMove = this.x + 33;
			break;
		case 2:
			this.yAfterMove = this.y + 33;
			break;
		case 3:
			this.xAfterMove = this.x - 33;
			break;
		default:
			break;
		}

	}

	private void turn(int direction) {
		switch (direction) {
		case 0: // turn upwards
			this.faceDirection = 0;
			break;
		case 1: // turn right
			this.faceDirection = 1;
			break;
		case 2: // turn downwards
			this.faceDirection = 2;
			break;
		case 3: // turn left
			this.faceDirection = 3;
			break;
		}
	}

	private void setValidSpawn() {
		/*
		 * Generate random spawn point.
		 */
		int x, y;
		x = CustomMath.genRandomX();
		y = CustomMath.genRandomY();
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

	public int getxAfterMove() {
		return xAfterMove;
	}

	public void setxAfterMove(int xAfterMove) {
		this.xAfterMove = xAfterMove;
	}

	public int getyAfterMove() {
		return yAfterMove;
	}

	public void setyAfterMove(int yAfterMove) {
		this.yAfterMove = yAfterMove;
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

	public int getFaceDirection() {
		return faceDirection;
	}

	public void setFaceDirection(int faceDirection) {
		this.faceDirection = faceDirection;
	}

	public boolean isUpgraded() {
		return isUpgraded;
	}

	public void setUpgraded(boolean isUpgraded) {
		this.isUpgraded = isUpgraded;
	}

}
