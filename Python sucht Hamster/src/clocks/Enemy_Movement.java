package clocks;

import java.util.Timer;
import java.util.TimerTask;

import chars.Enemy;
import data.EnemyAI;
import game.Gamestate;
import game.Gamestate_e;

public class Enemy_Movement {

	private static Timer timer;
	private static int delay = 1000; // 1s delay upon spawning
	private static double period = Enemy.getSpeed() * 2000; // multiply Enemy.speed with 1000ms (=1s)

	public static void start() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				EnemyAI ai = new EnemyAI(); 
				Enemy.move(ai.moveDirection()); // move in random direction (0-3)
				while (Gamestate.state == Gamestate_e.defeat) {
					Enemy.taunt();
				}
			}
		}, delay, (long) period);
	}

}
