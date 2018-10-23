package clocks;

import java.util.Timer;
import java.util.TimerTask;

import actions.Main;
import chars.Enemy;
import data.EnemyAI;
import game.Gamestate;
import game.Gamestate_e;

public class Enemy_Movement {

	private Enemy e = Main.e;

	private Timer timer;
	private int delay = 1000; // 1s delay upon spawning
	private double period = e.getSpeed() * 1000; // multiply Enemy.speed with 1000ms (=1s)

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
