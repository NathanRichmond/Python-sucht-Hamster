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
import actions.MouseMoved;
import actions.MousePressed;
import draw.Draw_Main;
import draw.IL;

public class Gui {

	public static Draw_Main dm;

	public static GraphicsContext gc_main;

	private static int width = 1536, height = 864; // size of the window

	private static int nLvls = 12; // number of levels

	public static Button[] pausebuttons = new Button[3];
	public static Button[] startmenubuttons = new Button[3];
	public static Button[] gameendbuttons = new Button[3];
	public static Button[] lvlselectbuttons = new Button[getnLvls()];
	public static Button lvlselectbutton_back;
	public static Button ingamebutton_restart;

	public void init() {
		dm = new Draw_Main();
		initButtons();
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
		stage.getIcons().add(IL.iplayer0); // icon in upper left corner of the window

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

	private void initButtons() {
		initPauseButtons();
		initStartMenuButtons();
		initGameEndButtons();
		initLvlSelectButtons();

		ingamebutton_restart = new Button(50, 30, 60, 60);
	}

	private void initLvlSelectButtons() {
		int buttonWidth = 90;
		int buttonHeight = 90;
		int buttonMargin = 60; // space between window borders and outer buttons (bottom, right and left)
		// space between individual buttons:
		int innerSpace = (getWidth() - 2 * buttonMargin - buttonWidth * getnLvls() / 2) / (getnLvls() / 2 - 1);
		int buttonX = buttonMargin; // x coordinate of the left column of buttons
		int buttonY = 11 * (getHeight() / 20); // y coordinate of upper row of buttons: 11/20 of screen height
		int j = 0;

		for (int i = 0; i < getnLvls(); i++) {
			if (i < getnLvls() / 2) {
				j = i;
			}
			if (i >= getnLvls() / 2) {
				j = i - getnLvls() / 2;
				if (i == getnLvls() / 2) {
					buttonY = buttonY + 4 * (getHeight() / 20);
				}
			}
			buttonX = buttonMargin + (buttonWidth + innerSpace) * j;
			lvlselectbuttons[i] = new Button(buttonX, buttonY, buttonWidth, buttonHeight);
			lvlselectbuttons[i].setText("" + (i + 1));
		}

		lvlselectbutton_back = new Button(50, 30, 150, 35);
		lvlselectbutton_back.setText("Back to Menu");
	}

	private void initGameEndButtons() {
		gameendbuttons[0] = new Button(getWidth() / 2 - 150, getHeight() - 180, 300, 50);
		gameendbuttons[0].setText("Replay");

		gameendbuttons[1] = new Button(getWidth() / 2 - 150, getHeight() - 120, 300, 50);
		gameendbuttons[1].setText("Exit to Menu");

		gameendbuttons[2] = new Button(getWidth() / 2 - 150, getHeight() - 60, 300, 50);
		gameendbuttons[2].setText("Quit");
	}

	private void initStartMenuButtons() {
		int width = 450, height = 175;
		startmenubuttons[0] = new Button(getWidth() / 2 - width / 2, 2 * (getHeight() / 3) - height / 2, width, height);
		startmenubuttons[0].setText("Start New Game");

		startmenubuttons[1] = new Button(55, getHeight() - 120, 150, 35);
		startmenubuttons[1].setText("Settings");

		startmenubuttons[2] = new Button(55, getHeight() - 70, 150, 35);
		startmenubuttons[2].setText("Quit");
	}

	private void initPauseButtons() {
		pausebuttons[0] = new Button(getWidth() / 2 - 150, getHeight() - 180, 300, 50);
		pausebuttons[0].setText("Resume");

		pausebuttons[1] = new Button(getWidth() / 2 - 150, getHeight() - 120, 300, 50);
		pausebuttons[1].setText("Exit to Menu");

		pausebuttons[2] = new Button(getWidth() / 2 - 150, getHeight() - 60, 300, 50);
		pausebuttons[2].setText("Quit");
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

	public static int getnLvls() {
		return nLvls;
	}

}
