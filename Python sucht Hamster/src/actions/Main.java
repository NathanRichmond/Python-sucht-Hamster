package actions;

import javafx.application.Application;
import javafx.stage.Stage;

import clocks.GameLoop;
import gui.Gui;

public class Main extends Application {

	
	Gui g = new Gui();

	@Override
	public void start(Stage stage) throws Exception {

		g.init();
		g.create(stage);

		new Thread(new GameLoop()).start();
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
