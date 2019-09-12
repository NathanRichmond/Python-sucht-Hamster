package clocks;

import java.util.Timer;
import java.util.TimerTask;

import chars.Enemy;
import data.EnemyAI;
import game.Game;
import game.Gamestate;
import game.Gamestate_e;

public class Enemy_Movement {

	private Enemy e;

	public Timer timer;
	public long delay;
	/*
	 * Interval: 1 / e.speed (Enemy moves in one >second<) * 1000 (as period is in
	 * >milliseconds<)
	 */
	private long period;

	private int i = 0; // interval between hamsterinflation spawns

	public Enemy_Movement(Enemy enemy) {
		e = enemy;
		double enemyspeed = e.getSpeed();
		if (enemyspeed == 0) {
			enemyspeed = 4.656612875245796924105750827168e-7; // smallest number possible to create the maximum long
		}
		period = (long) (1000 / enemyspeed); // time until next move; see top
		if (e.getSpeed() != 0) {
			delay = (long) (Math.random() * period + 1); // generate random delay in time margin of one move
		} else { // if Enemy is not supposed to move (speed==0), make delay maximum
			delay = period;
		}
	}

	public void start() {

		timer = new Timer();

		if (Gamestate.state == Gamestate_e.ingame) {

			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {

					if (e.isSpeedBoosted() == false) {

						EnemyAI ai = new EnemyAI(e);
						e.move(ai.moveDirection()); // move in random direction (0-3)

						if (Game.isHamsterinflation() == true) {
							hamsterinflation();
						}

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

					}

				}

			}, delay, period);
		}
	}

	private void hamsterinflation() {
		if (i == 1) { // every 1 moves
			Game.hamsterinflation();
			i = 0;
		}
		i++;

	}

}
