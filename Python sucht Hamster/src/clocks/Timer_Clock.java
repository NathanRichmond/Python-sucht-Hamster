package clocks;

import java.util.Timer;
import java.util.TimerTask;

import game.Gamestate;
import game.Gamestate_e;

public class Timer_Clock {

	private static Timer timer;
	private static int delay = 1010; // delay before it starts
	private static int period = 10; // interval between updates

	public static void start() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (Gamestate.state == Gamestate_e.ingame) {
					game.Timer.reduceHeight();
				}

			}
		}, delay, period);
	}

}
