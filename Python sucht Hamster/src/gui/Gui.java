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

	private static int width = 1200, height = 700; // size of the window

	private static int nLvls = 12; // number of levels

	private static int pythonSkin = 0; // appearance of Python
	private static int nPythonSkins = 2; // number of available Python skins

	private static int hamsterSkin = 0; // appearance of Hamster
	private static int nHamsterSkins = 2; // number of available Hamster skins

	public static Button[] startmenubuttons = new Button[4];
	public static Button[] lvlselectbuttons = new Button[getnLvls()];
	public static Button[] manualbuttons = new Button[7]; 
	public static Button[] ingamebuttons = new Button[3];
	public static Button victorybutton;

	int gridx = Draw_Main.getStartmenugridX();
	int gridy = Draw_Main.getStartmenugridY();

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
		initStartMenuButtons();
		initIngameButtons();
		initManualButtons(); 

		victorybutton = new Button(260, 30, 60, 60); // Next Level
	}

	private void initIngameButtons() {
		ingamebuttons[0] = new Button(50, 30, 60, 60); // Exit to Menu
		ingamebuttons[1] = new Button(120, 30, 60, 60); // Pause
		ingamebuttons[2] = new Button(190, 30, 60, 60); // Restart
	}

	private void initLvlSelectButtons() {
		int buttonX = gridx + 3; // general offset into tile
		int buttonY = gridy + 3 + 3 * 66; // on fourth row of grid
		int buttonWidth = 62;
		int buttonHeight = 62;
		int buttonsPerLine = 12;
		int innerSpace = 4; // space between individual buttons: 1px until tile border, 2px tile border, 1px
							// offset into tile
		int j = 0;

		for (int i = 0; i < getnLvls(); i++) {
			j = i % buttonsPerLine;
			if (j == 0 && i != 0) { // if end of line is reached
				buttonY = buttonY + 66; // apply line break
			}
			buttonX = (buttonWidth + innerSpace) * j + gridx + 3;
			lvlselectbuttons[i] = new Button(buttonX, buttonY, buttonWidth, buttonHeight);
		}
	}

	private void initStartMenuButtons() {
		initLvlSelectButtons();

		startmenubuttons[0] = new Button(gridx + 3 + 11 * 66, gridy + 3, 62, 62); // Cross - Quit
		startmenubuttons[1] = new Button(gridx + 3, gridy + 3, 62, 62); // Hamster
		startmenubuttons[2] = new Button(gridx + 3 + 2 * 66, gridy + 3, 62, 62); // Python
		startmenubuttons[3] = new Button(gridx + 3 + 9 * 66, gridy + 3, 62, 62); // Question Mark - Manual
	}
		
	private void initManualButtons() {
		manualbuttons[0] = new Button(gridx + 3 + 2*66, gridy +3 +2*66, 62, 62); //Keys 
		manualbuttons[1] = new Button(gridx + 3 + 4*66, gridy +3 +2*66 , 62, 62); //Wall
		manualbuttons[2] = new Button(gridx + 3 + 5*66 , gridy +3 + 3*66, 62, 62); //Korn 
		manualbuttons[3] = new Button(gridx + 3, gridy + 3, 62, 62);  //Fragezeichen 
		manualbuttons[4] = new Button(gridx + 3 + 6*66 , gridy +3 + 2*66, 62, 62); //Sanduhr
		manualbuttons[5] = new Button(gridx + 3 + 8*66 , gridy +3 + 2*66, 62, 62); //Hammer
		manualbuttons[6] = new Button(gridx + 3 + 9*66 , gridy +3 + 3*66, 62, 62); //zu Level 1
		manualbuttons[6].setText("Level 1");
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

	public static int getPythonSkin() {
		return pythonSkin;
	}

	public static void setPythonSkin(int pythonSkin) {
		Gui.pythonSkin = pythonSkin;
	}

	public static int getnPythonSkins() {
		return nPythonSkins;
	}

	public static void setnPythonSkins(int nPythonSkins) {
		Gui.nPythonSkins = nPythonSkins;
	}

	public static int getHamsterSkin() {
		return hamsterSkin;
	}

	public static void setHamsterSkin(int hamsterSkin) {
		Gui.hamsterSkin = hamsterSkin;
	}

	public static int getnHamsterSkins() {
		return nHamsterSkins;
	}

	public static void setnHamsterSkins(int nHamsterSkins) {
		Gui.nHamsterSkins = nHamsterSkins;
	}

}
