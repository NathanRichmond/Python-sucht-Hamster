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
import actions.MouseMoved;
import actions.MousePressed;
import draw.Draw_Main;

public class Gui {

	public static Draw_Main dm;

	public static GraphicsContext gc_main;

	private static int width = 491, height = 331; // size of the window
	private static int gridwidth = 331, gridheight = 331; // size of the grid

	public static Button[] buttons = new Button[3];

	public void init() {

		dm = new Draw_Main();

		buttons[0] = new Button(getWidth() / 2 - 150, getHeight() / 2 - 125, 300, 50);
		buttons[0].setText("Resume");

		buttons[1] = new Button(getWidth() / 2 - 150, getHeight() / 2 - 25, 300, 50);
		buttons[1].setText("(no effect)");

		buttons[2] = new Button(getWidth() / 2 - 150, getHeight() / 2 + 75, 300, 50);
		buttons[2].setText("Quit");
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
		scene.setOnMouseMoved(new MouseMoved());
		scene.setOnMousePressed(new MousePressed());

		/*
		 * Window properties
		 */
		stage.setTitle("Python tötet Hamster");
		stage.setResizable(false);
		stage.centerOnScreen();

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

	public static int getGridwidth() {
		return gridwidth;
	}

	public static void setGridwidth(int gridwidth) {
		Gui.gridwidth = gridwidth;
	}

	public static int getGridheight() {
		return gridheight;
	}

	public static void setGridheight(int gridheight) {
		Gui.gridheight = gridheight;
	}

}
