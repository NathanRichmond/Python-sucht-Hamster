package clocks;

import actions.Game;
import chars.Enemy;
import chars.Player;
import data.Collision;
import game.Gamestate;
import game.Gamestate_e;
import game.SpecialTile;
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
			 * Execute Special Tile Function when colliding with Enemy/Player
			 */
			if (Game.isFirstKeyPressInGame() == false) { // only when clocks are running

				/*
				 * Collision with Player
				 */
				if (Collision.cSpecialTile(p.getX(), p.getY()) == true) {
					for (SpecialTile st : SpecialTile_Creation.specialtiles) { // detect the specific Special Tile that
																				// was collided with
						if (p.getX() == st.getX() && p.getY() == st.getY()) {
							st.activate("player");
							break;
						}
					}
				}
				/*
				 * Collision with Enemy
				 */
				if (Collision.cSpecialTile(e.getX(), e.getY()) == true) {
					for (SpecialTile st : SpecialTile_Creation.specialtiles) { // see above
						if (e.getX() == st.getX() && e.getY() == st.getY()) {
							st.activate("enemy");
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
			 * Remove all activated (thus "dead" -- isAlive = false) Special Tiles
			 */
			for (int i = 0; i < SpecialTile_Creation.specialtiles.size(); i++) {
				if ((SpecialTile_Creation.specialtiles.get(i)).isAlive() == false) {
					SpecialTile_Creation.remove(i);
				}
			}
		}
	}

	public void render() {
		Gui.gc_main.clearRect(0, 0, Gui.getWidth(), Gui.getHeight());
		Gui.dm.draw(Gui.gc_main);
	}

}
