package actions;

import java.util.ArrayList;

import chars.Enemy;
import chars.Player;
import clocks.Enemy_Movement;
import clocks.ST_ModifyTime;
import clocks.SpecialTile_Creation;
import clocks.Timer_Clock;
import clocks.Wall_Creation;
import game.GameTimer;
import game.Gamestate;
import game.Gamestate_e;
import gui.Grid;

public class Game {

	public static Player p;
	public static ArrayList<Enemy> enemies = new ArrayList<>();

	private static int level;
	private static int maxLevelAvailable = 1; // highest level that was won in current session

	private static boolean firstKeyPressInGame = true;

//	public static int hamstercount;
	private static int hamsterinflationCounter = 0;

	/*
	 * Level Properties:
	 */
	private static String gridsize = "10x10"; // widthxheight of the grid
	public static int nEnemy; // number of Enemies
	private static double espeed; // speed of Enemies
	private static double gameDuration; // time in seconds until game is lost
	private static double playerMargin; // see EnemyAI. Default: 4

	private static boolean hamsterinflation; // activate Inflation?

	private static boolean walls; // any Walls?
	private static int nWalls; // number of Walls

	private static boolean specialTiles; // any Special Tiles?

	private static int nKorn; // number of Korn Tiles
	private static double kornDuration; // duration of Korn effect in seconds
	private static double kornBoost; // factor by which Enemy base speed is increased

	private static int nBabyhamster; // number of Babyhamster Tiles

	private static int nHourglass; // number of Hourglass Tiles
	private static double hourglassEDuration; // duration of Hourglass effect (activated by Enemy) in seconds
	private static double hourglassEFactor; // factor by which time is increased.

	private static double hourglassPDuration; // duration of Hourglass effect (activated by Player) in seconds
	private static double hourglassPFactor; // factor. Needs to be 0<factor<1 to have it slow down the time

	private static int nHammer; // number of Hammer Tiles.

	public static void startNewGame() {

//		hamstercount = 0;
		hamsterinflationCounter = 0;
		resetGame(); // Empty the grid

		setLevelProperties();

		new Grid();
		spawn(); // Generate new chars

		applyLevelPropertiesToChars();

		if (isWalls() == true) {
			new Wall_Creation();
		}
		if (isSpecialTiles() == true) {
			new SpecialTile_Creation();
		}

		new GameTimer();

		Gamestate.state = Gamestate_e.ingame; // actually start the game (draw ingame elements)
	}

	public static void resetGame() {
		/*
		 * Literally kill the existing chars
		 */
		p = null;
		enemies.clear(); // Empty array of Enemies

		/*
		 * Empty arrays of grid elements
		 */
		Wall_Creation.walls.clear();
		SpecialTile_Creation.specialtiles.clear();

		setFirstKeyPressInGame(true);
	}

	private static void setLevelProperties() {
		/*
		 * Reset any existing Level Properties to default values
		 */
		setGridsize("10x10");
		setnEnemy(1);
		setEspeed(2.5);
		setGameDuration(10);
		setPlayerMargin(4);
		setHamsterinflation(false);
		setWalls(false);
		setnWalls(0);
		setSpecialTiles(false);
		setnKorn(0);
		setKornDuration(0);
		setKornBoost(0);
		setnBabyhamster(0);
		setnHourglass(0);
		setHourglassEDuration(0);
		setHourglassEFactor(0);
		setHourglassPDuration(0);
		setHourglassPFactor(0);
		setnHammer(0);
		/*
		 * Apply new Level Properties
		 */
		switch (getLevel()) {
		case 1:
			level1();
			break;
		case 2:
			level2();
			break;
		case 3:
			level3();
			break;
		case 4:
			level4();
			break;
		case 5:
			level5();
			break;
		case 6:
			level6();
			break;
		case 7:
			level7();
			break;
		case 8:
			level8();
			break;
		case 9:
			level9();
			break;
		case 10:
			level10();
			break;
		case 11:
			level11();
			break;
		case 12:
			level12();
			break;
		default:
			level1();
			break;
		}

	}

	public static void spawn() {
		/*
		 * Generate new chars
		 */
		p = new Player();
		for (int i = 0; i < getnEnemy(); i++) {
			enemies.add(new Enemy());
		}
	}

	private static void applyLevelPropertiesToChars() {
		for (Enemy e : enemies) {
			e.setSpeed(getEspeed());
		}
	}

	public static void restartLevel() {
		if (isFirstKeyPressInGame() == false) {
			/*
			 * Cancel all clocks, in case they have already begun running
			 */
			if (GameTimer.isModified() == true) {
				ST_ModifyTime.timer.cancel();
			}
			Timer_Clock.timer.cancel();
			for (Enemy e : enemies) {
				if (e.isSpeedBoosted() == false) {
					e.em.timer.cancel();
				}
				if (e.isSpeedBoosted() == true) {
					e.sb.timer.cancel();
				}
			}
		}
		startNewGame();
	}

	public static void startClocks() {
		for (Enemy e : enemies) {
			e.em = new Enemy_Movement(e);
			e.em.start(); // Start Movement of Enemy
		}

		GameTimer.setGameDuration(getGameDuration());
		new Timer_Clock();
		Timer_Clock.start(); // Start the Timer count down

		setFirstKeyPressInGame(false);
	}

	public static void hamsterinflation() {
		if (hamsterinflationCounter > 3 * (enemies.size() / 4)) {
			if (enemies.size() <= 64) { // don't spawn more than 64 Enemies
				Enemy enemy = new Enemy();
				enemy.setSpeed(10);
				enemies.add(enemy);
				enemy.em = new Enemy_Movement(enemy);
				enemy.em.start();
			}

		}
		hamsterinflationCounter++;
	}

	/*
	 * Properties for the individual levels:
	 */
	private static void level1() {
		setGameDuration(3);
	}

	private static void level2() {
		setEspeed(5.5);
		setGameDuration(8.5);
		setWalls(true);
		setnWalls(1);
	}

	private static void level3() {
		setGameDuration(5);
		setWalls(true);
		setnWalls(5);
		setSpecialTiles(true);
		setnKorn(5);
		setKornDuration(1);
		setKornBoost(3);
	}

	private static void level4() {
		setGridsize("20x20");
		setEspeed(6.6);
		setGameDuration(20);
		setWalls(true);
		setnWalls(220);
	}

	private static void level5() {
		setGridsize("05x10");
		setEspeed(5);
		setGameDuration(2.4);
	}

	private static void level6() {
		setGridsize("10x05");
		setEspeed(3);
		setGameDuration(10);
		setSpecialTiles(true);
		setnKorn(1);
		setKornDuration(6);
		setKornBoost(2.5);
		setnHourglass(3);
		setHourglassEDuration(1);
		setHourglassEFactor(2.5);
		setHourglassPDuration(1);
		setHourglassPFactor(0.25);
	}

	private static void level7() {
		setGridsize("20x20");
		setnEnemy(3);
		setGameDuration(30);
		setWalls(true);
		setnWalls(10);
		setSpecialTiles(true);
		setnBabyhamster(50);
	}

	private static void level8() {
		setGridsize("32x20");
		setGameDuration(20);
		setPlayerMargin(6.5); // at Gridsize 32x20 that's about 4 tiles
		setWalls(true);
		setnWalls(200);
		setSpecialTiles(true);
		setnKorn(125);
		setKornDuration(0.6);
		setKornBoost(5);
	}

	private static void level9() {
		setGridsize("32x20");
		setEspeed(4);
		setGameDuration(20);
		setWalls(true);
		setnWalls(125);
		setSpecialTiles(true);
		setnKorn(50);
		setKornDuration(1);
		setKornBoost(2);
		setnHourglass(50);
		setHourglassEDuration(0.5);
		setHourglassEFactor(2);
		setHourglassPDuration(0.5);
		setHourglassPFactor(0.5);
		setnHammer(50);
	}

	private static void level10() {
		setGridsize("20x20");
		setGameDuration(99);
		setWalls(true);
		setnWalls(10);
		setSpecialTiles(true);
		setnHammer(200);
	}

	private static void level11() {
		setGridsize("32x20");
		setnEnemy(3);
		setEspeed(2);
		setGameDuration(180);
		setPlayerMargin(6.5); // at Gridsize 32x20 that's about 4 tiles
		setSpecialTiles(true);
		setnKorn(680);
		setKornDuration(0.2);
		setKornBoost(4);
	}

	private static void level12() {
		setGridsize("20x20");
		setEspeed(10);
		setGameDuration(10);
		setHamsterinflation(true);
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Game.level = level;
	}

	public static int getMaxLevelAvailable() {
		return maxLevelAvailable;
	}

	public static void setMaxLevelAvailable(int maxLevelCleared) {
		Game.maxLevelAvailable = maxLevelCleared;
	}

	public static boolean isFirstKeyPressInGame() {
		return firstKeyPressInGame;
	}

	public static void setFirstKeyPressInGame(boolean keyPressedFirstTime) {
		Game.firstKeyPressInGame = keyPressedFirstTime;
	}

	public static String getGridsize() {
		return gridsize;
	}

	public static void setGridsize(String gridsize) {
		Game.gridsize = gridsize;
	}

	public static int getnEnemy() {
		return nEnemy;
	}

	public static void setnEnemy(int nEnemy) {
		Game.nEnemy = nEnemy;
	}

	public static double getEspeed() {
		return espeed;
	}

	public static void setEspeed(double espeed) {
		Game.espeed = espeed;
	}

	public static double getGameDuration() {
		return gameDuration;
	}

	public static void setGameDuration(double gameDuration) {
		Game.gameDuration = gameDuration;
	}

	public static double getPlayerMargin() {
		return playerMargin;
	}

	public static void setPlayerMargin(double playerMargin) {
		Game.playerMargin = playerMargin;
	}

	public static boolean isHamsterinflation() {
		return hamsterinflation;
	}

	public static void setHamsterinflation(boolean hamsterinflation) {
		Game.hamsterinflation = hamsterinflation;
	}

	public static boolean isWalls() {
		return walls;
	}

	public static void setWalls(boolean walls) {
		Game.walls = walls;
	}

	public static int getnWalls() {
		return nWalls;
	}

	public static void setnWalls(int nWalls) {
		Game.nWalls = nWalls;
	}

	public static boolean isSpecialTiles() {
		return specialTiles;
	}

	public static void setSpecialTiles(boolean specialTiles) {
		Game.specialTiles = specialTiles;
	}

	public static int getnKorn() {
		return nKorn;
	}

	public static void setnKorn(int nKorn) {
		Game.nKorn = nKorn;
	}

	public static double getKornDuration() {
		return kornDuration;
	}

	public static void setKornDuration(double kornDuration) {
		Game.kornDuration = kornDuration;
	}

	public static double getKornBoost() {
		return kornBoost;
	}

	public static void setKornBoost(double kornBoost) {
		Game.kornBoost = kornBoost;
	}

	public static int getnBabyhamster() {
		return nBabyhamster;
	}

	public static void setnBabyhamster(int nBabyhamster) {
		Game.nBabyhamster = nBabyhamster;
	}

	public static int getnHourglass() {
		return nHourglass;
	}

	public static void setnHourglass(int nHourglass) {
		Game.nHourglass = nHourglass;
	}

	public static double getHourglassEDuration() {
		return hourglassEDuration;
	}

	public static void setHourglassEDuration(double hourglassEDuration) {
		Game.hourglassEDuration = hourglassEDuration;
	}

	public static double getHourglassEFactor() {
		return hourglassEFactor;
	}

	public static void setHourglassEFactor(double hourglassEFactor) {
		Game.hourglassEFactor = hourglassEFactor;
	}

	public static double getHourglassPDuration() {
		return hourglassPDuration;
	}

	public static void setHourglassPDuration(double hourglassPDuration) {
		Game.hourglassPDuration = hourglassPDuration;
	}

	public static double getHourglassPFactor() {
		return hourglassPFactor;
	}

	public static void setHourglassPFactor(double hourglassPFactor) {
		Game.hourglassPFactor = hourglassPFactor;
	}

	public static int getnHammer() {
		return nHammer;
	}

	public static void setnHammer(int nHammer) {
		Game.nHammer = nHammer;
	}

}
