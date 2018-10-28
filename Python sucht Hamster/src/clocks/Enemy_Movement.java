package clocks;

import java.util.Timer;
import java.util.TimerTask;

import actions.Game;
import chars.Enemy;
import data.EnemyAI;
import game.Gamestate;
import game.Gamestate_e;

public class Enemy_Movement {

	private Enemy e;

	public Timer timer;
	public int delay;
	/*
	 * Interval: 1 / e.speed (Enemy moves in one >second<) * 1000 (as period is in
	 * >milliseconds<)
	 */
	private long period;

	private int i = 0; // interval between hamsterinflation spawns

	public Enemy_Movement(Enemy enemy) {
		e = enemy;
		period = (long) (1000 / e.getSpeed()); // time until next move; see above
		delay = (int) (Math.random() * period + 1); // generate random delay in time margin of one move
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
						
						if (Game.getLevel() == 11) {
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
		if (i == 10) { // every 10 moves
			Game.hamsterinflation();
			i = 0;
		}
		i++;

	}

}
