package actions;

import javafx.application.Application;
import javafx.stage.Stage;

import chars.Enemy;
import chars.Player;
import clocks.Enemy_Movement;
import clocks.GameLoop;
import clocks.Korn_Creation;
import clocks.Wall_Creation;
import game.GameTimer;
import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;

public class Main extends Application {

	public static Player p;
	public static Enemy e;

	Gui g = new Gui();

	@Override
	public void start(Stage stage) throws Exception {

		g.init();
		g.create(stage);

		new Thread(new GameLoop()).start();

	}

	public static void spawn() {
		e = new Enemy();
		p = new Player();
	}

	public static void startNewGame() {
		/*
		 * Literally kill the existing chars
		 */
		e = null;
		p = null;

		spawn(); // Generate new chars

		Gamestate.state = Gamestate_e.ingame;

		reset(); // Remove all existing Walls and Koerner

		new Wall_Creation();
		new Korn_Creation();

		new GameTimer(); // Start the Timer

		/*
		 * Start Movement of Enemy
		 */
		e.em = new Enemy_Movement();
		e.em.start();
	}

	private static void reset() {
		Wall_Creation.walls.clear(); // empty the array
		Korn_Creation.koerner.clear(); // empty the array
	}

	public static void main(String[] args) {
		launch(args);
	}

}
