package clocks;

import java.util.Timer;
import java.util.TimerTask;

import chars.Enemy;
import game.Gamestate;
import game.Gamestate_e;

public class Enemy_Movement {

	private static Timer timer;
	private static int delay = 1000; // 1sec delay upon spawning
	private static double period = Enemy.getSpeed() * 1000; // multiply Enemy.speed with 1000ms (=1s)

	public static void start() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (Enemy.isAlive()) {
					Enemy.move((int) (Math.random() * 4)); // move in random direction (0-3)
					while (Gamestate.state == Gamestate_e.defeat) {
						Enemy.taunt();
					}
				}
			}
		}, delay, (long) period);
	}

}
