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
	public static Timer timer;
	private static int delay = 0;
	private static double period; // is set in constructor, because GameTimer.gameDuration varies

	/*
	 * For last two images (mirror crack):
	 */
	private static int delay2 = 200; // time between third to last and second to last GameTimer image
	private static int period2 = 50; // time between second to last and last GameTimer image

	public Timer_Clock() {
		/*
		 * Interval between updates: Get total gameDuration (is in seconds --> multiply
		 * with 1000 to have milliseconds), divide it by amount of GameTimer images in
		 * order to switch to next GameTimer image at even intervals
		 */
		period = (GameTimer.getGameDuration() * 1000) / GameTimer.getAmountImages();
	}

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
							}
						}
					}, delay2, period2);
				}
				if (Gamestate.state == Gamestate_e.startmenu) {
					timer.cancel();
				}
			}
		}, delay, (long) period);

	}

}
