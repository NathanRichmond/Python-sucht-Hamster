package clocks;

import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;

public class GameLoop implements Runnable {

	private boolean running = true;

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

			if (deltaTime >= 1) { // wird alle 60 Ticks > 1
				update();
				deltaTime--;
				render();
			}

			if (System.currentTimeMillis() - timer > 1000) { // wird in diesem Projekt nicht wirklich verwendet
				timer += 1000;
			}
		}

	}

	public void update() {
		if (Gamestate.state == Gamestate_e.ingame) {
		}
	}

	public void render() {
		Gui.gc_main.clearRect(0, 0, Gui.getWidth(), Gui.getHeight());
		Gui.dm.draw(Gui.gc_main);
	}

}
