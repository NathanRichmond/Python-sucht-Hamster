package clocks;

import java.util.Timer;
import java.util.TimerTask;

import chars.Enemy;
import data.EnemyAI;
import game.Gamestate;
import game.Gamestate_e;

public class ST_BoostEnemy {

	private Enemy e;

	private static int moves; // number of moves with boosted speed
	private static int movecounter; // number of moves already executed

	private static int counter; // number of queued speed boosts

	private static long delay; // delay before starting the speed boost
	private static long period; // time until next move

	public Timer timer;

	public ST_BoostEnemy(Enemy enemy, double duration, double boostedEnemySpeed) {
		e = enemy;

		moves = (int) (duration * boostedEnemySpeed);

		setCounter(1);

		period = (long) (1000 / boostedEnemySpeed); // time until next move
		delay = period;
	}

	public void start() {

		movecounter = 0;

		e.setSpeedBoosted(true);

		timer = new Timer();

		if (Gamestate.state == Gamestate_e.ingame) {

			e.em.timer.cancel(); // Stop the regular Enemy_Movement

			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {

					if (movecounter < moves) {

						EnemyAI ai = new EnemyAI(e);
						e.move(ai.moveDirection()); // move in random direction

						/*
						 * Stop timer if Enemy is dead or game is quit
						 */
						if (e.isAlive() == false) {
							timer.cancel();
						} else {
							if (Gamestate.state != Gamestate_e.ingame && Gamestate.state != Gamestate_e.pause) {
								timer.cancel();
							}
						}

						movecounter++;

					} else {
						stop();
					}
				}

			}, delay, period);
		}

	}

	private void stop() {
		e.setSpeedBoosted(false);
		timer.cancel();

		setCounter(getCounter() - 1);

		if (getCounter() > 0) {
			delay = 0; // seamless transition to next queued speed boost
			start();
		} else {
			e.em.delay = 0; // seamless transition to Enemy_Movement
			e.em.start(); // Restart Enemy_Movement
		}
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		ST_BoostEnemy.counter = counter;
	}

}
