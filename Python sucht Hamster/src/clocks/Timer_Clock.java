package clocks;

import java.util.Timer;
import java.util.TimerTask;

import game.GameTimer;
import game.Gamestate;
import game.Gamestate_e;

public class Timer_Clock {

	/*
	 * Normal timer
	 */
	private static Timer timer;
	private static int delay = 1010; // delay before it starts in milliseconds
	/*
	 * Interval between updates: Get total gameDuration (is in seconds --> multiply
	 * with 1000 to have milliseconds), divide it by amount of GameTimer images in
	 * order to switch to next GameTimer image at even intervals
	 */
	private static double period = (GameTimer.getGameDuration() * 1000) / GameTimer.getAmountImages();

	/*
	 * For last two images (mirror crack):
	 */
	private static int delay2 = 500; // time between third to last and second to last GameTimer image
	private static int period2 = 50; // time between second to last and last GameTimer image

	public static void start() {

		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (GameTimer.getGameTime() < GameTimer.getAmountImages() - 2) { // all images except last two
					if (Gamestate.state == Gamestate_e.ingame) {
						GameTimer.setGameTime(GameTimer.getGameTime() + 1);
					}
				} else { // has reached third to last image
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							if (GameTimer.getGameTime() < GameTimer.getAmountImages()) { // end when reaching last image
								GameTimer.setGameTime(GameTimer.getGameTime() + 1);
							} else {
								Gamestate.state = Gamestate_e.defeat;
								timer.cancel();
							}
						}
					}, delay2, period2);
				}
			}
		}, delay, (long) period);

	}

}
