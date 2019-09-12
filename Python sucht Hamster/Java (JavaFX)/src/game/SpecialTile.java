package game;

import chars.Enemy;
import chars.Player;
import clocks.ST_BoostEnemy;
import clocks.ST_ModifyTime;
import data.Collision;
import data.CustomMath;
import gui.Grid;
import gui.Gui;

import java.util.Timer;
import java.util.TimerTask;

public class SpecialTile {

	private int x, y;
	private int width = Gui.getTile() - 1, height = Gui.getTile() - 1;
	private String type;
	private boolean isAlive;

	private int stepsWent;

	public SpecialTile(String type) {
		this.setValidSpawn();
		this.isAlive = true;
		this.type = type;
	}

	public void activate(String activatedBy, Enemy enemy, Player player) {
		this.setAlive(false);
		switch (getType()) {
		case "korn":
			activateKorn(activatedBy, enemy);
			break;
		case "babyhamsterTwo":
			activateBabyhamsterTwo(activatedBy, player);
			break;
		case "babyhamsterThree":
			activateBabyhamsterThree(activatedBy, player);
			break;
		case "babyhamsterFour":
			activateBabyhamsterFour(activatedBy, player);
			break;
		case "hourglass":
			activateHourglass(activatedBy, enemy);
			break;
		case "hammer":
			activateHammer(activatedBy, enemy, player);
			break;
		}
	}

	private void activateKorn(String activatedBy, Enemy e) {
		if (activatedBy == "enemy") {
			// increase Enemy speed for limited period of time
			if (e.isSpeedBoosted() == false) {
				double duration = Game.getKornDuration(); // duration in seconds
				double speedBoost = Game.getKornBoost(); // factor by which base speed is increased
				e.sb = new ST_BoostEnemy(e, duration, e.getSpeed() * speedBoost);
				e.sb.start();
			} else { // if Enemy is currently boosted
				e.sb.setCounter(e.sb.getCounter() + 1); // queue another speed boost
			}
		} else {
			if (activatedBy == "player") {
				// destroy Korn. Is already destroyed by now (see line 31), so nothing else here.
			}
		}
	}

	private void activateBabyhamsterTwo(String activatedBy, Player p) {
		if (activatedBy == "enemy") {
			// nothing
		} else {
			if (activatedBy == "player") {
				// do a fast dash of two tiles in the face direction
				p.move(p.getFaceDirection()); // One instant step, followed by the others, each after a delay
				stepsWent++;
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					public void run() {
						if (stepsWent < 2) {
							stepsWent++;
							p.move(p.getFaceDirection());
						} else {
							stepsWent = 0;
							timer.cancel();
						}

					}
				}, 30, 30);

			}
		}
	}

	private void activateBabyhamsterThree(String activatedBy, Player p) {
		if (activatedBy == "enemy") {
			// nothing
		} else {
			if (activatedBy == "player") {
				// do a fast dash of three tiles in the face direction
				p.move(p.getFaceDirection()); // One instant step, followed by the others, each after a delay
				stepsWent++;
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					public void run() {
						if (stepsWent < 3) {
							stepsWent++;
							p.move(p.getFaceDirection());
						} else {
							stepsWent = 0;
							timer.cancel();
						}

					}
				}, 30, 30);
			}
		}
	}

	private void activateBabyhamsterFour(String activatedBy, Player p) {
		if (activatedBy == "enemy") {
			// nothing
		} else {
			if (activatedBy == "player") {
				// do a fast dash of four tiles in the face direction
				p.move(p.getFaceDirection()); // One instant step, followed by the others, each after a delay
				stepsWent++;
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					public void run() {
						if (stepsWent < 4) {
							stepsWent++;
							p.move(p.getFaceDirection());
						} else {
							stepsWent = 0;
							timer.cancel();
						}

					}
				}, 30, 30);
			}
		}
	}

	private void activateHourglass(String activatedBy, Enemy e) {
		if (activatedBy == "enemy") {
			// increase game speed for limited period of time
			double duration = Game.getHourglassEDuration(); // duration in seconds
			double factor = Game.getHourglassEFactor(); // factor by which time is increased
			if (GameTimer.isModified() == false) {
				new ST_ModifyTime(duration, factor, "enemy").start();
			} else { // if time is currently modified
				if (ST_ModifyTime.getActivatedBy() == "enemy") { // if current time modifier was activated by enemy
					ST_ModifyTime.setCounter(ST_ModifyTime.getCounter() + 1); // queue another time modifier
				} else {
					ST_ModifyTime.timer.cancel();
					new ST_ModifyTime(duration, factor, "enemy").start(); // overwrite player time modifier
				}
			}
		} else {
			if (activatedBy == "player") {
				// slow down game speed for limited period of time
				double duration = Game.getHourglassPDuration(); // duration in seconds
				double factor = Game.getHourglassPFactor(); // factor by which time is increased. Needs to be 0<factor<1
															// to have it slow
				// down the time
				if (GameTimer.isModified() == false) {
					new ST_ModifyTime(duration, factor, "player").start();
				} else { // if time is currently modified
					if (ST_ModifyTime.getActivatedBy() == "player") {
						ST_ModifyTime.setCounter(ST_ModifyTime.getCounter() + 1); // queue another time modifier
					} else {
						ST_ModifyTime.timer.cancel();
						new ST_ModifyTime(duration, factor, "player").start(); // overwrite enemy time modifier
					}
				}
			}
		}
	}

	private void activateHammer(String activatedBy, Enemy e, Player p) {
		if (activatedBy == "enemy") {
			// generate Walls behind Enemy
			switch (e.getFaceDirection()) {
			case 0:
				Wall_Creation.walls.add(new Wall(e.getX() - Gui.getTile(), e.getY() + Gui.getTile()));
				Wall_Creation.walls.add(new Wall(e.getX(), e.getY() + Gui.getTile()));
				Wall_Creation.walls.add(new Wall(e.getX() + Gui.getTile(), e.getY() + Gui.getTile()));
				break;
			case 1:
				Wall_Creation.walls.add(new Wall(e.getX() - Gui.getTile(), e.getY() - Gui.getTile()));
				Wall_Creation.walls.add(new Wall(e.getX() - Gui.getTile(), e.getY()));
				Wall_Creation.walls.add(new Wall(e.getX() - Gui.getTile(), e.getY() + Gui.getTile()));
				break;
			case 2:
				Wall_Creation.walls.add(new Wall(e.getX() + Gui.getTile(), e.getY() - Gui.getTile()));
				Wall_Creation.walls.add(new Wall(e.getX(), e.getY() - Gui.getTile()));
				Wall_Creation.walls.add(new Wall(e.getX() - Gui.getTile(), e.getY() - Gui.getTile()));
				break;
			case 3:
				Wall_Creation.walls.add(new Wall(e.getX() + Gui.getTile(), e.getY() + Gui.getTile()));
				Wall_Creation.walls.add(new Wall(e.getX() + Gui.getTile(), e.getY()));
				Wall_Creation.walls.add(new Wall(e.getX() + Gui.getTile(), e.getY() - Gui.getTile()));
				break;
			}
		} else {
			if (activatedBy == "player") {
				// generate Walls in front of Player
				switch (p.getFaceDirection()) {
				case 0:
					Wall_Creation.walls.add(new Wall(p.getX() + Gui.getTile(), p.getY() - Gui.getTile()));
					Wall_Creation.walls.add(new Wall(p.getX(), p.getY() - Gui.getTile()));
					Wall_Creation.walls.add(new Wall(p.getX() - Gui.getTile(), p.getY() - Gui.getTile()));
					break;
				case 1:
					Wall_Creation.walls.add(new Wall(p.getX() + Gui.getTile(), p.getY() + Gui.getTile()));
					Wall_Creation.walls.add(new Wall(p.getX() + Gui.getTile(), p.getY()));
					Wall_Creation.walls.add(new Wall(p.getX() + Gui.getTile(), p.getY() - Gui.getTile()));
					break;
				case 2:
					Wall_Creation.walls.add(new Wall(p.getX() - Gui.getTile(), p.getY() + Gui.getTile()));
					Wall_Creation.walls.add(new Wall(p.getX(), p.getY() + Gui.getTile()));
					Wall_Creation.walls.add(new Wall(p.getX() + Gui.getTile(), p.getY() + Gui.getTile()));
					break;
				case 3:
					Wall_Creation.walls.add(new Wall(p.getX() - Gui.getTile(), p.getY() - Gui.getTile()));
					Wall_Creation.walls.add(new Wall(p.getX() - Gui.getTile(), p.getY()));
					Wall_Creation.walls.add(new Wall(p.getX() - Gui.getTile(), p.getY() + Gui.getTile()));
					break;
				}
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
