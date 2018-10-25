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
	private static String gridsize = "10x10";
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
	
	public static void restartLevel() {
		Timer_Clock.timer.cancel();
		startNewGame();
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
		setGridsize("10x10");
		setGameDuration(3);
		setWalls(false);
		setKoerner(false);
	}

	private static void level2() {
		setEspeed(9.5);
		setGridsize("10x10");
		setGameDuration(8.5);
		setWalls(true);
		setnWalls(1);
		setKoerner(false);
	}

	private static void level3() {
		setEspeed(2.5);
		setGridsize("10x10");
		setGameDuration(5);
		setWalls(true);
		setnWalls(5);
		setKoerner(true);
		setnKoerner(5);
	}

	private static void level4() {
		setEspeed(6.6);
		setGridsize("20x20");
		setGameDuration(30);
		setWalls(true);
		setnWalls(220);
		setKoerner(false);
	}

	private static void level5() {
		setEspeed(5);
		setGridsize("05x10");
		setGameDuration(2.4);
		setWalls(false);
		setKoerner(false);
	}

	private static void level6() {
		setEspeed(5);
		setGridsize("10x05");
		setGameDuration(2.4);
		setWalls(false);
		setKoerner(false);
	}

	private static void level7() {
		setEspeed(4);
		setGridsize("34x20");
		setGameDuration(20);
		setWalls(false);
		setKoerner(true);
		setnKoerner(680);
	}

	private static void level8() {
		setEspeed(2.5);
		setGridsize("10x10");
		setGameDuration(3);
		setWalls(false);
		setKoerner(false);
	}

	private static void level9() {
		setEspeed(2.5);
		setGridsize("10x10");
		setGameDuration(3);
		setWalls(false);
		setKoerner(false);
	}

	private static void level10() {
		setEspeed(2.5);
		setGridsize("10x10");
		setGameDuration(3);
		setWalls(false);
		setKoerner(false);
	}

	private static void level11() {
		setEspeed(2.5);
		setGridsize("10x10");
		setGameDuration(3);
		setWalls(false);
		setKoerner(false);
	}

	private static void level12() {
		setEspeed(2.5);
		setGridsize("10x10");
		setGameDuration(3);
		setWalls(false);
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

	public static String getGridsize() {
		return gridsize;
	}

	public static void setGridsize(String gridsize) {
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
