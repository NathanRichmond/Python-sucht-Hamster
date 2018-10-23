package game;

import clocks.Timer_Clock;

public class GameTimer {

	private static double gameDuration = 10; // time in seconds until game is over
	private static int amountImages = 89; // amount of different images for animation
	private static int gameTime; // image that is to be displayed currently

	/*
	 * When making a new GameTimer, set gameTime to 1 and start the clock
	 */
	public GameTimer() {
		GameTimer.gameTime = 1;
		Timer_Clock.start();
	}

	public static int getGameTime() {
		return gameTime;
	}

	public static void setGameTime(int gameTime) {
		GameTimer.gameTime = gameTime;
	}

	public static double getGameDuration() {
		return gameDuration;
	}

	public static int getAmountImages() {
		return amountImages;
	}

}
