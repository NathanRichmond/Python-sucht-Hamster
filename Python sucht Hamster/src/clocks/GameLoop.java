package clocks;

import actions.Game;
import chars.Enemy;
import chars.Player;
import data.Collision;
import game.Gamestate;
import game.Gamestate_e;
import game.Korn;
import game.Upgrade;
import gui.Gui;

public class GameLoop implements Runnable {

	private boolean running = true;
	private Player p;
	private Enemy e;

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		final double FPS = 60.0;
		double ns = 1000000000 / FPS;
		double deltaTime = 0;

		long timer = System.currentTimeMillis();

		while (running) {

			long currentTime = System.nanoTime();
			deltaTime += (currentTime - lastTime) / ns;
			lastTime = currentTime;

			if (deltaTime >= 1) { // is every 60 Ticks greater than 1
				update();
				deltaTime--;
				render();
			}

			if (System.currentTimeMillis() - timer > 1000) { // not really used in this project
				timer += 1000;
			}
		}

	}

	public void update() {
		p = Game.p;
		e = Game.e;

		/*
		 * COLLISION CHECK
		 */
		new Collision();

		if (Gamestate.state == Gamestate_e.ingame) {

			/*
			 * Consume Korn when colliding with Player
			 */
			if (Game.isFirstKeyPressInGame() == false) { // only when clocks are running
				if (Collision.cKorn(p.getX(), p.getY()) == true) {
					for (Korn k : Korn_Creation.koerner) { // detect the specific Korn that was collided with
						if (p.getX() == k.getX() && p.getY() == k.getY()) {
							k.consume();
							new Upgrade("Korn");
							break;
						}
					}
				}
			}

			/*
			 * Kill enemy when colliding with Player
			 */
			if (Collision.cEnemyPlayer() == true) {
				e.killEnemy();
			}

			/*
			 * Remove all consumed Koerner
			 */
			for (int i = 0; i < Korn_Creation.koerner.size(); i++) {
				if ((Korn_Creation.koerner.get(i)).isConsumed() == true) {
					Korn_Creation.remove(i);
				}
			}
		}
	}

	public void render() {
		Gui.gc_main.clearRect(0, 0, Gui.getWidth(), Gui.getHeight());
		Gui.dm.draw(Gui.gc_main);
	}

}
