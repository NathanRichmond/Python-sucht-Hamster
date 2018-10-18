package game;

public class Timer {

	private static double width = 22, height = 200;
	private static double x = 430, y = 66;
	private static double timeSpeed = 0.25; // amount of pixels the timer decreases in Timer_Clock.period intervals

	public static void reduceHeight() {
		if (height - timeSpeed >= 0) { // as long as it doesn't reach the bottom
			y = y + timeSpeed; // move the column one unit down
			height = height - timeSpeed; // decrease the height one unit
		} else { // once it reaches the bottom
			Gamestate.state = Gamestate_e.defeat;
		}
	}

	public static double getX() {
		return x;
	}

	public static void setX(int x) {
		Timer.x = x;
	}

	public static double getY() {
		return y;
	}

	public static void setY(int y) {
		Timer.y = y;
	}

	public static double getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Timer.width = width;
	}

	public static double getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Timer.height = height;
	}

}
