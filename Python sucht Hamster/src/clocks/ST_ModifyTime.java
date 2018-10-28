package clocks;

import java.util.Timer;
import java.util.TimerTask;

import game.GameTimer;
import game.Gamestate;
import game.Gamestate_e;

public class ST_ModifyTime {

	private static int images; // number of images with modified speed
	private static int imagecounter; // number of images that have already been shown

	private static int counter; // number of queued time modifiers

	private static long period;
	private static long delay;

	private static String activatedBy;
	
	public static Timer timer;

	public ST_ModifyTime(double duration, double factor, String activatedBy) {
		period = (long) (((GameTimer.getGameDuration() * 1000) / GameTimer.getAmountImages()) / factor);
		delay = period;

		images = (int) ((duration * 1000) / period);

		setCounter(1);
		
		setActivatedBy(activatedBy);
	}

	public void start() {

		imagecounter = 0;

		GameTimer.setModified(true);

		timer = new Timer();

		if (Gamestate.state == Gamestate_e.ingame) {

			Timer_Clock.timer.cancel(); // Stop the regular Timer_Clock

			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {

					if (imagecounter < images) {

						if (GameTimer.getGameTime() < GameTimer.getAmountImages() - 2) { // all images except last two
							if (Gamestate.state == Gamestate_e.ingame) {
								GameTimer.setGameTime(GameTimer.getGameTime() + 1);
							}
						} else { // has reached third to last image
							stop();
						}

						if (Gamestate.state == Gamestate_e.startmenu) {
							timer.cancel();
						}

						imagecounter++;

					} else {
						stop();
					}
				}

			}, delay, period);
		}

	}

	private void stop() {
		GameTimer.setModified(false);
		timer.cancel();

		setCounter(getCounter() - 1);

		if (getCounter() > 0) {
			delay = 0; // seamless transition to next queued time modifier
			start();
		} else {
			Timer_Clock.start(); // Restart regular Timer_Clock
		}
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		ST_ModifyTime.counter = counter;
	}

	public static String getActivatedBy() {
		return activatedBy;
	}

	public static void setActivatedBy(String activatedBy) {
		ST_ModifyTime.activatedBy = activatedBy;
	}

}
