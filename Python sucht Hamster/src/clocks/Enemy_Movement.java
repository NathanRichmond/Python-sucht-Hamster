package clocks;

import java.util.Timer;
import java.util.TimerTask;

import actions.Game;
import chars.Enemy;
import data.EnemyAI;
import game.Gamestate;
import game.Gamestate_e;

public class Enemy_Movement {

	private Enemy e = Game.e;

	private Timer timer;
	private int delay = 0;
	/*
	 * Interval: 1 / e.speed (Enemy moves in one >second<) * 1000 (as period is in >milliseconds<)
	 */
	private double period = 1000 / e.getSpeed(); //

	public void start() {
		timer = new Timer();
		if (Gamestate.state == Gamestate_e.ingame) {
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					EnemyAI ai = new EnemyAI();
					e.move(ai.moveDirection()); // move in random direction (0-3)
					if (Gamestate.state != Gamestate_e.ingame && Gamestate.state != Gamestate_e.pause) {
						timer.cancel();
					}
				}
			}, delay, (long) period);
		}
	}

}
