package actions;

import chars.Enemy;
import chars.Player;
import clocks.Enemy_Movement;
import clocks.Korn_Creation;
import clocks.Timer_Clock;
import clocks.Wall_Creation;
import game.GameTimer;
import game.Gamestate;
import game.Gamestate_e;
import gui.Grid;

public class Game {

	public static Player p;
	public static Enemy e;

	private static int level;

	private static boolean firstKeyPressInGame = true;

	/*
	 * Level Properties:
	 */
	private static int gridsize = 10;
	private static double gameDuration;
	private static boolean walls;
	private static int nWalls; // number of Walls
	private static boolean koerner;
	private static int nKoerner; // number of Koerner
	private static double espeed;

	public static void startNewGame() {
		resetGame();

		setLevelProperties();
		new Grid();

		spawn(); // Generate new chars

		applyLevelPropertiesToChars();

		if (isWalls() == true) {
			new Wall_Creation();
		}
		if (isKoerner() == true) {
			new Korn_Creation();
		}

		new GameTimer();

		Gamestate.state = Gamestate_e.ingame; // actually start the game (draw ingame elements)
	}

	private static void setLevelProperties() {
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
		default:
			level1();
			break;
		}

	}

	private static void applyLevelPropertiesToChars() {
		e.setSpeed(getEspeed());
	}

	public static void resetGame() {
		/*
		 * Literally kill the existing chars
		 */
		e = null;
		p = null;

		/*
		 * Empty arrays of grid elements
		 */
		Wall_Creation.walls.clear();
		Korn_Creation.koerner.clear();

		setFirstKeyPressInGame(true);
	}

	public static void startClocks() {
		e.em = new Enemy_Movement();
		e.em.start(); // Start Movement of Enemy

		GameTimer.setGameDuration(getGameDuration());
		new Timer_Clock();
		Timer_Clock.start(); // Start the Timer count down
		setFirstKeyPressInGame(false);
	}

	/*
	 * Generate new chars
	 */
	public static void spawn() {
		p = new Player();
		e = new Enemy();
	}

	private static void level1() {
		setEspeed(2.5);
		setGridsize(10);
		setGameDuration(3);
		setWalls(false);
		setKoerner(false);
	}

	private static void level2() {
		setEspeed(12);
		setGridsize(10);
		setGameDuration(6);
		setWalls(true);
		setnWalls(1);
		setKoerner(false);
	}

	private static void level3() {
		setEspeed(2.5);
		setGridsize(10);
		setGameDuration(5);
		setWalls(true);
		setnWalls(5);
		setKoerner(true);
		setnKoerner(5);
	}

	private static void level4() {
		setEspeed(6.6);
		setGridsize(20);
		setGameDuration(30);
		setWalls(true);
		setnWalls(330);
		setKoerner(false);
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Game.level = level;
	}

	public static boolean isFirstKeyPressInGame() {
		return firstKeyPressInGame;
	}

	public static void setFirstKeyPressInGame(boolean keyPressedFirstTime) {
		Game.firstKeyPressInGame = keyPressedFirstTime;
	}

	public static int getGridsize() {
		return gridsize;
	}

	public static void setGridsize(int gridsize) {
		Game.gridsize = gridsize;
	}

	public static double getGameDuration() {
		return gameDuration;
	}

	public static void setGameDuration(double gameDuration) {
		Game.gameDuration = gameDuration;
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

	public static boolean isKoerner() {
		return koerner;
	}

	public static void setKoerner(boolean koerner) {
		Game.koerner = koerner;
	}

	public static int getnKoerner() {
		return nKoerner;
	}

	public static void setnKoerner(int nKoerner) {
		Game.nKoerner = nKoerner;
	}

	private static double getEspeed() {
		return espeed;
	}

	private static void setEspeed(double espeed) {
		Game.espeed = espeed;
	}

}
