package actions;

import javafx.application.Application;
import javafx.stage.Stage;

import chars.Enemy;
import chars.Player;
import clocks.Enemy_Movement;
import clocks.GameLoop;
import clocks.Timer_Clock;
import gui.Gui;

public class Main extends Application {

	Gui g = new Gui();

	@Override
	public void start(Stage stage) throws Exception {

		g.init();
		g.create(stage);

		new Player();
		new Enemy();

		new Thread(new GameLoop()).start();
		Timer_Clock.start();
		Enemy_Movement.start();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
