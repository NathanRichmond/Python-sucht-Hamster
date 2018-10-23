package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import actions.KeyPressed;
import actions.KeyReleased;
import actions.Main;
import actions.MouseMoved;
import actions.MousePressed;
import chars.Enemy;
import chars.Player;
import draw.Draw_Main;
import draw.IL;

public class Gui {

	public static Player p = Main.p;
	public static Enemy e = Main.e;

	public static Draw_Main dm;

	public static GraphicsContext gc_main;

	private static int width = 768, height = 432; // size of the window

	public static Button[] pausebuttons = new Button[3];
	public static Button[] startmenubuttons = new Button[2];
	public static Button[] gameendbuttons = new Button[2];

	public void init() {

		dm = new Draw_Main();

		pausebuttons[0] = new Button(getWidth() / 2 - 150, getHeight() - 180, 300, 50);
		pausebuttons[0].setText("Resume");

		pausebuttons[1] = new Button(getWidth() / 2 - 150, getHeight() - 120, 300, 50);
		pausebuttons[1].setText("Exit to Menu");

		pausebuttons[2] = new Button(getWidth() / 2 - 150, getHeight() - 60, 300, 50);
		pausebuttons[2].setText("Quit");

		startmenubuttons[0] = new Button(getWidth() / 2 - 150, getHeight() - 120, 300, 50);
		startmenubuttons[0].setText("Start New Game");

		startmenubuttons[1] = new Button(getWidth() / 2 - 150, getHeight() - 60, 300, 50);
		startmenubuttons[1].setText("Quit");

		gameendbuttons[0] = new Button(getWidth() / 2 - 150, getHeight() - 120, 300, 50);
		gameendbuttons[0].setText("Exit to Menu");

		gameendbuttons[1] = new Button(getWidth() / 2 - 150, getHeight() - 60, 300, 50);
		gameendbuttons[1].setText("Quit");
	}

	public void create(Stage stage) {

		Canvas canvas_main;
		StackPane root = new StackPane();
		int cWidth = getWidth() - 10, cHeight = getHeight() - 10; // fix of placement error

		canvas_main = new Canvas(getWidth(), getHeight());
		gc_main = canvas_main.getGraphicsContext2D();
		dm.draw(gc_main);

		root.getChildren().add(canvas_main);
		Scene scene = new Scene(root, cWidth, cHeight);

		/*
		 * KeyHandler
		 */
		scene.setOnKeyPressed(new KeyPressed());
		scene.setOnKeyReleased(new KeyReleased());
		scene.setOnMouseMoved(new MouseMoved());
		scene.setOnMousePressed(new MousePressed());

		/*
		 * Window properties
		 */
		stage.setTitle("Python sucht Hamster");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.getIcons().add(IL.iplayerzunge0); // icon in upper left corner of the window

		stage.setScene(scene);
		stage.show();

		/*
		 * Close window when "X" in upper right corner is clicked
		 */
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});

	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Gui.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Gui.height = height;
	}

}
