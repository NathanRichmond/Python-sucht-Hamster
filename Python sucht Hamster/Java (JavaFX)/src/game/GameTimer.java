package game;

public class GameTimer {

	private static double gameDuration; // time in seconds until game is over
	private static int amountImages = 89; // amount of different images for animation
	private static int gameTime; // image that is to be displayed currently
	private static boolean isModified;

	/*
	 * When making a new GameTimer, set gameTime to 1 and isModified to false
	 */
	public GameTimer() {
		GameTimer.gameTime = 1;
		GameTimer.setModified(false);
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

	public static void setGameDuration(double gameDuration) {
		GameTimer.gameDuration = gameDuration;
	}

	public static int getAmountImages() {
		return amountImages;
	}

	public static boolean isModified() {
		return isModified;
	}

	public static void setModified(boolean isModified) {
		GameTimer.isModified = isModified;
	}

}
