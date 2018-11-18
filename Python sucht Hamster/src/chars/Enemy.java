package chars;

//import actions.Game;
import clocks.Enemy_Movement;
import clocks.ST_BoostEnemy;
import data.Collision;
import data.CustomMath;
import game.Gamestate;
import game.Gamestate_e;
import gui.Grid;

public class Enemy {

//	public int index; // for testing purposes
	private int x, y;
	private int xAfterMove, yAfterMove; // coords of enemy after making next move
	private int width, height;
	private int faceDirection;
	private double speed; // amount of moves Enemy can make within one second
	private boolean isAlive;
	private boolean isSpeedBoosted;
	public Enemy_Movement em;
	public ST_BoostEnemy sb;

	public Enemy() {
//		this.index = index;
		this.setValidSpawn(); // Spawn at random position
		this.width = 32;
		this.height = 32;
		this.faceDirection = (int) (Math.random() * 3); // random faceDirection
		this.speed = 2.5; // default speed
		this.isAlive = true;
		this.isSpeedBoosted = false;
	}
  public Enemy(int a, int b) {
	    this.x = a; 
	    this.y = b; 
		this.width = 32;
		this.height = 32;
		this.faceDirection = (int) (Math.random() * 3); // random faceDirection
		this.speed = 2.5; // default speed
		this.isAlive = true;
		this.isSpeedBoosted = false;
	}

	private void setValidSpawn() {
		/*
		 * Generate random spawn point. Don't spawn inside Player.
		 */
		int x, y;
		x = CustomMath.genRandomX();
		y = CustomMath.genRandomY();
		if (Collision.cPlayer(x, y) == false) {
			this.setX(Grid.getX() + x);
			this.setY(Grid.getY() + y);
		} else {
			setValidSpawn();
		}
	}

	public void move(int direction) {
		if (Gamestate.state == Gamestate_e.ingame || Gamestate.state == Gamestate_e.defeat) {
			if (this.isAlive == true) {
				switch (direction) {
				case 0:
					this.turn(0);
					this.y = this.y - 33; // move up
					break;
				case 1:
					this.turn(1);
					this.x = this.x + 33; // move right
					break;
				case 2:
					this.turn(2);
					this.y = this.y + 33; // move down
					break;
				case 3:
					this.turn(3);
					this.x = this.x - 33; // move left
					break;
				}
			}
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

	public void killEnemy() {
		this.setAlive(false);
//		Game.hamstercount++;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxAfterMove() {
		return this.xAfterMove;
	}

	public void setxAfterMove(int xAfterMove) {
		this.xAfterMove = xAfterMove;
	}

	public int getyAfterMove() {
		return this.yAfterMove;
	}

	public void setyAfterMove(int yAfterMove) {
		this.yAfterMove = yAfterMove;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getFaceDirection() {
		return this.faceDirection;
	}

	public void setFaceDirection(int faceDirection) {
		this.faceDirection = faceDirection;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isAlive() {
		return this.isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isSpeedBoosted() {
		return isSpeedBoosted;
	}

	public void setSpeedBoosted(boolean isSpeedBoosted) {
		this.isSpeedBoosted = isSpeedBoosted;
	}

}
