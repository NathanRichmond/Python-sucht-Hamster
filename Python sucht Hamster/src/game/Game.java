package game;

import java.util.ArrayList;

import chars.Enemy;
import chars.Player;
import clocks.Enemy_Movement;
import clocks.ST_ModifyTime;
import clocks.Timer_Clock;
import gui.Grid;

public class Game {

	public static Player p;
	public static ArrayList<Enemy> enemies = new ArrayList<>();

	public static int ex, ey, px, py; // Koordinaten für die nicht zufällig gesetzten Chars

	private static int level;
	private static int maxLevelAvailable = 12; // highest level that was won in current session
	private static boolean behindTheGame = false; // whether tutlevels show behind the game info

	private static boolean firstKeyPressInGame = true;

	public static int hamstercount = 0; // number of devoured Hamster
	private static int hamsterinflationCounter = 0; // counter for hamsterinflation()

	/*
	 * Level Properties:
	 */
	private static String gridsize = "10x10"; // widthxheight of the grid
	public static int nEnemy; // number of Enemies
	private static double espeed; // speed of Enemies. When set to 0, it's not really 0 (because long isn't
									// infinite) but it would take the Enemies 293.274.701 years to make a move
	private static double gameDuration; // time in seconds until game is lost
	private static double playerMargin; // see EnemyAI. Default: 4

	private static boolean hamsterinflation; // activate Inflation?

	private static boolean walls; // any Walls?
	private static int nWalls; // number of Walls

	private static boolean specialTiles; // any Special Tiles?

	private static int nKorn; // number of Korn Tiles
	private static double kornDuration; // duration of Korn effect in seconds
	private static double kornBoost; // factor by which Enemy base speed is increased

	private static int nBabyhamsterTwo; // number of Babyhamster Tiles with two hamsters
	private static int nBabyhamsterThree; // number of Babyhamster tiles with three hamsters
	private static int nBabyhamsterFour; // number of Babyhamster tiles with four hamsters

	private static int nHourglass; // number of Hourglass Tiles
	private static double hourglassEDuration; // duration of Hourglass effect (activated by Enemy) in seconds
	private static double hourglassEFactor; // factor by which time is increased

	private static double hourglassPDuration; // duration of Hourglass effect (activated by Player) in seconds
	private static double hourglassPFactor; // factor. Needs to be 0<factor<1 to have it slow down the time

	private static int nHammer; // number of Hammer Tiles.

	public static void startNewGame() {
		hamstercount = 0;
		hamsterinflationCounter = 0;

		resetGame(); // Empty the grid

		setLevelProperties();

		new Grid();
		spawn(); // Generate new chars

		if (isWalls() == true) {
			new Wall_Creation(); // Generate Walls
		}
		if (isSpecialTiles() == true) {
			new SpecialTile_Creation(); // Generate Special Tiles
		}

		new GameTimer(); // Zeitleiste

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
		setKornDuration(1);
		setKornBoost(2);
		setnBabyhamsterTwo(0);
		setnBabyhamsterThree(0);
		setnBabyhamsterFour(0);
		setnHourglass(0);
		setHourglassEDuration(0.5);
		setHourglassEFactor(2);
		setHourglassPDuration(0.5);
		setHourglassPFactor(0.5);
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
		case 101:
			level101();
			break;
		case 102:
			level102();
			break;
		case 103:
			level103();
			break;
		case 104:
			level104();
			break;
		case 105:
			level105();
			break;
		case 106:
			level106();
			break;
		case 107:
			level107();
			break;
		case 108:
			level108();
			break;
		default:
			level1();
			break;
		}

	}

	public static void spawn() {
		// Generate new chars

		if (Game.getLevel() < 100) { // for regular levels: random placement
			p = new Player();
			for (int i = 0; i < getnEnemy(); i++) {
				enemies.add(new Enemy());
			}
		} else { // for Tutorial levels: manual placement (coordinates are set in Level
					// properties)
			p = new Player(Grid.getX() + getPx(), Grid.getY() + getPy());
			enemies.add(new Enemy(Grid.getX() + getEx(), Grid.getY() + getEy()));
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
			// no timer in tutlvl1 & tutlvl2. Might sometimes cause an error, thus try-catch
			if (Game.getLevel() != 101 && Game.getLevel() != 102) {
				try {
					Timer_Clock.timer.cancel();
				} catch (Exception e1) { // any exception that is thrown
					e1.printStackTrace(); // print error log
				}
			}
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

		if (Game.getLevel() != 101 && Game.getLevel() != 102) { // no timer in tut1 & tut2
			GameTimer.setGameDuration(getGameDuration());
			new Timer_Clock();
			Timer_Clock.start(); // Start the Timer count down
		}

		setFirstKeyPressInGame(false);
	}

	public static void hamsterinflation() {
		if (hamsterinflationCounter > enemies.size()) { // do not
			if (enemies.size() < 64) { // don't spawn more than 64 Enemies
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
	private static void level1() { // "Die Jagd beginnt..."
		setGameDuration(3);
	}

	private static void level2() { // "Um Mauern schlängeln"
		setEspeed(5.5);
		setGameDuration(8.5);
		setWalls(true);
		setnWalls(12);
	}

	private static void level3() { // "Wie eine Fliege"
		setGridsize("08x05");
		setEspeed(16);
		setGameDuration(6);
		setWalls(true);
		setnWalls(15);
		setSpecialTiles(true);
		setnKorn(2);
		setKornDuration(6);
		setKornBoost(3);
	}

	private static void level4() { // "Im Labyrinth"
		setGridsize("32x20");
		setEspeed(6.6);
		setGameDuration(13);
		setWalls(true);
		setnWalls(360);
	}

	private static void level5() { // "Chronismus"
		setGridsize("10x10");
		setEspeed(5);
		setGameDuration(10);
		setSpecialTiles(true);
		setnHourglass(50);
		setHourglassEDuration(1);
		setHourglassEFactor(10);
		setHourglassPDuration(1);
		setHourglassPFactor(0.3);
	}

	private static void level6() { // "Im Silo"
		setGridsize("32x20");
		setnEnemy(5);
		setEspeed(4);
		setGameDuration(30);
		setPlayerMargin(6.5); // at Gridsize 32x20 that's about 4 tiles
		setSpecialTiles(true);
		setnKorn(600);
		setKornDuration(0.2);
		setKornBoost(5);
	}

	private static void level7() { // "Familientreffen"
		setGridsize("15x08");
		setnEnemy(30);
		setEspeed(1.5);
		setGameDuration(16);
		setWalls(true);
		setnWalls(10);
	}

	private static void level8() { // "Zugemauert"
		setGridsize("32x20");
		setGameDuration(20);
		setPlayerMargin(6.5); // at Gridsize 32x20 that's about 4 tiles
		setWalls(true);
		setnWalls(200);
		setSpecialTiles(true);
		setnKorn(125);
		setKornDuration(0.75);
		setKornBoost(5);
		setnHammer(100);
	}

	private static void level9() { // "Das volle Programm"
		setGridsize("32x20");
		setEspeed(4);
		setGameDuration(20);
		setWalls(true);
		setnWalls(125);
		setSpecialTiles(true);
		setnKorn(100);
		setKornBoost(3);
		setnBabyhamsterTwo(30);
		setnBabyhamsterThree(25);
		setnBabyhamsterFour(20);
		setnHourglass(50);
		setHourglassEDuration(1);
		setHourglassEFactor(3);
		setHourglassPDuration(1);
		setHourglassPFactor(0.5);
		setnHammer(75);
	}

	private static void level10() { // "Baustelle"
		setGridsize("20x20");
		setGameDuration(99);
		setWalls(true);
		setnWalls(10);
		setSpecialTiles(true);
		setnHammer(200);
	}

	private static void level11() { // "Im Hamsternest"
		setGridsize("32x20");
		setnEnemy(10);
		setGameDuration(40);
		setWalls(true);
		setnWalls(50);
		setSpecialTiles(true);
		setnKorn(20);
		setKornBoost(3);
		setnBabyhamsterTwo(120);
		setnBabyhamsterThree(120);
		setnBabyhamsterFour(100);
	}

	private static void level12() { // "Hamsterinflation"
		setGridsize("20x20");
		setEspeed(7);
		setHamsterinflation(true);
	}

	private static void level101() {
		setGridsize("15x01");
		setEspeed(0);
		setCharsCoordinates(0, 0, 14, 0);
		// playerMargin: 1 or 2 tiles
	}

	private static void level102() {
		setCharsCoordinates(0, 0, 9, 9);
	}

	private static void level103() {
		setCharsCoordinates(0, 0, 9, 9);
	}

	private static void level104() {
		setGridsize("08x05");
		setCharsCoordinates(1, 2, 6, 2);
		setWalls(true);
	}

	private static void level105() {
		setGridsize("08x08");
		setCharsCoordinates(1, 0, 4, 0);
		setEspeed(4);
		setWalls(true);
		setSpecialTiles(true);
		setKornBoost(2);
	}

	private static void level106() {
		setGridsize("15x08");
		setCharsCoordinates(0, 7, 14, 2);
		setGameDuration(20);
		setWalls(true);
		setSpecialTiles(true);
		setHourglassEFactor(3);
		setHourglassEDuration(2);
		setHourglassPFactor(0.25);
		setHourglassPDuration(1.75);
		// playerMargin: 12 tiles
	}

	private static void level107() {
		setGridsize("14x03");
		setCharsCoordinates(0, 2, 11, 2);
		setWalls(true);
		setSpecialTiles(true);
	}

	private static void level108() {
		setGridsize("12x05");
		setCharsCoordinates(0, 2, 9, 2);
		setWalls(true);
		setSpecialTiles(true);

	}

	private static void setCharsCoordinates(int px, int py, int ex, int ey) {
		Game.px = px * 33 + 1; // *33 because 33 is size of one tile, +1 for placement fix
		Game.py = py * 33 + 1;
		Game.ex = ex * 33 + 1;
		Game.ey = ey * 33 + 1;
	}

	public static int getEx() {
		return ex;
	}

	public static int getEy() {
		return ey;
	}

	public static int getPx() {
		return px;
	}

	public static int getPy() {
		return py;
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

	public static boolean isBehindTheGame() {
		return behindTheGame;
	}

	public static void setBehindTheGame(boolean behindTheGame) {
		Game.behindTheGame = behindTheGame;
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

	public static int getnBabyhamsterTwo() {
		return nBabyhamsterTwo;
	}

	public static void setnBabyhamsterTwo(int nBabyhamsterTwo) {
		Game.nBabyhamsterTwo = nBabyhamsterTwo;
	}

	public static int getnBabyhamsterThree() {
		return nBabyhamsterThree;
	}

	public static void setnBabyhamsterThree(int nBabyhamsterThree) {
		Game.nBabyhamsterThree = nBabyhamsterThree;
	}

	public static int getnBabyhamsterFour() {
		return nBabyhamsterFour;
	}

	public static void setnBabyhamsterFour(int nBabyhamsterFour) {
		Game.nBabyhamsterFour = nBabyhamsterFour;
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
