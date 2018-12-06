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

	// size of the window: width = height * 1.77777 -- always retain this ratio!!
	private static int width = 1536, height = 864; // optimal size, normal resolution
//	private static int width = 1280, height = 720; // for 1280x800
//	private static int width = 640, height = 360; // small and ugly

	private static int tile = (int) Math.round(width / 46.545454545454545454545454545455); // width & height of 1 grid
																							// tile

	private static int nLvls = 12; // number of levels

	private static int pythonSkin = 0; // appearance of Python
	private static int nPythonSkins = 3; // number of available Python skins (Default, Bloody, Pokemon)

	private static int hamsterSkin = 0; // appearance of Hamster
	private static int nHamsterSkins = 3; // number of available Hamster skins (Default, Candy, Pokemon)

	public static Button[] startmenubuttons = new Button[4]; // Skins, Go to Tutorial Menu, Exit
	public static Button[] lvlselectbuttons = new Button[getnLvls()]; // Koerner for level selection in Start Menu
	public static Button[] tutorialmenubuttons = new Button[12];
	public static Button[] anleitungsbuttons = new Button[2];
	public static Button[] ingamebuttons = new Button[3]; // Back to Menu, Pause, Restart
	public static Button victorybutton; // Go to next Level

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
		stage.getIcons().add(IL.ilogo); // icon in upper left corner of the window

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
		initTutorialMenuButtons();
		initAnleitungsbuttons();

		victorybutton = new Button((int) (Gui.getWidth() / 5.9076923076923076923076923076923),
				(int) (Gui.getHeight() / 28.8), (int) (Gui.getWidth() / 25.6), (int) (Gui.getHeight() / 14.4)); // Next
		// Level
	}

	private void initIngameButtons() {
		ingamebuttons[0] = new Button((int) (Gui.getWidth() / 30.72), (int) (Gui.getHeight() / 28.8),
				(int) (Gui.getWidth() / 25.6), (int) (Gui.getHeight() / 14.4)); // Exit to Menu
		ingamebuttons[1] = new Button((int) (Gui.getWidth() / 12.8), (int) (Gui.getHeight() / 28.8),
				(int) (Gui.getWidth() / 25.6), (int) (Gui.getHeight() / 14.4)); // Pause
		ingamebuttons[2] = new Button((int) (Gui.getWidth() / 8.0842105263157894736842105263158),
				(int) (Gui.getHeight() / 28.8), (int) (Gui.getWidth() / 25.6), (int) (Gui.getHeight() / 14.4)); // Restart
	}

	private void initLvlSelectButtons() {
		int buttonX = gridx + 3; // general offset into tile
		int buttonY = (int) (gridy + 3 + 3 * (Gui.getHeight() / 13.090909090909090909090909090909)); // on fourth row of
																										// grid
		int buttonWidth = (int) (Gui.getWidth() / 24.774193548387096774193548387097);
		int buttonHeight = (int) (Gui.getHeight() / 13.935483870967741935483870967742);
		int buttonsPerLine = 12;
		int innerSpace = 4; // space between individual buttons: 1px until tile border, 2px tile border, 1px
							// offset into tile
		int j = 0;

		for (int i = 0; i < getnLvls(); i++) {
			j = i % buttonsPerLine;
			if (j == 0 && i != 0) { // if end of line is reached
				buttonY = (int) (buttonY + (Gui.getHeight() / 13.090909090909090909090909090909)); // apply line break
			}
			buttonX = (buttonWidth + innerSpace) * j + gridx + 3;
			lvlselectbuttons[i] = new Button(buttonX, buttonY, buttonWidth, buttonHeight);
		}
	}

	private void initStartMenuButtons() {
		initLvlSelectButtons();

		startmenubuttons[0] = new Button((int) (gridx + 3 + 11 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				gridy + 3, (int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Cross - Quit
		startmenubuttons[1] = new Button(gridx + 3, gridy + 3,
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Hamster Skins
		startmenubuttons[2] = new Button((int) (gridx + 3 + 2 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				gridy + 3, (int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Python Skins
		startmenubuttons[3] = new Button((int) (gridx + 3 + 9 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				gridy + 3, (int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Question Mark - Manual
	}

	private void initTutorialMenuButtons() {
		tutorialmenubuttons[0] = new Button(
				(int) (gridx + 3 + 2 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (gridy + 3 + 2 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Keys - tutlvl 101
		tutorialmenubuttons[1] = new Button(
				(int) (gridx + 3 + 3 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (gridy + 3 + 3 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Zeitleiste - tutlvl 103
		tutorialmenubuttons[2] = new Button(
				(int) (gridx + 3 + 4 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (gridy + 3 + 2 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Wall - tutlvl 104
		tutorialmenubuttons[3] = new Button(
				(int) (gridx + 3 + 5 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (gridy + 3 + 3 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Korn - tutlvl 105
		tutorialmenubuttons[4] = new Button(
				(int) (gridx + 3 + 6 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (gridy + 3 + 2 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Sanduhr - tutlvl 106
		tutorialmenubuttons[5] = new Button(
				(int) (gridx + 3 + 7 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (gridy + 3 + 3 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Babyhamster - tutlvl 107
		tutorialmenubuttons[6] = new Button(
				(int) (gridx + 3 + 8 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (gridy + 3 + 2 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Hammer - tutlvl 108
		tutorialmenubuttons[7] = new Button(gridx + 3, gridy + 3,
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Fragezeichen
		tutorialmenubuttons[8] = new Button(
				(int) (gridx + 3 + 10 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (gridy + 3 + 2 * (Gui.getHeight() / 13.090909090909090909090909090909)),
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // zu Level 1
		tutorialmenubuttons[8].setText("Level 1");
		tutorialmenubuttons[9] = new Button(
				(int) (gridx + 3 + 1 * (Gui.getHeight() / 13.090909090909090909090909090909)), gridy + 3,
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Compiler
		tutorialmenubuttons[10] = new Button(
				(int) (gridx + 3 + 6 * (Gui.getHeight() / 13.090909090909090909090909090909)), gridy + 3,
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Clipboard
		tutorialmenubuttons[11] = new Button(
				(int) (gridx + 3 + 9 * (Gui.getHeight() / 13.090909090909090909090909090909)), gridy + 3,
				(int) (Gui.getWidth() / 24.774193548387096774193548387097),
				(int) (Gui.getHeight() / 13.935483870967741935483870967742)); // Back
	}

	private void initAnleitungsbuttons() {
		anleitungsbuttons[0] = new Button(1, 1, getWidth(), getHeight());
		anleitungsbuttons[0].setText("An Alle, die den Hamster-Simulator genauso hassen wie wir: \n"
				+ "Hier ist endlich die Gelegenheit deine ganze Wut und deinen Frust herauszulassen und Rache an dem Hamster zu nehmen. \n"
				+ "\n" + "Python-sucht-Hamster  ist ein Spiel nach dem „Hunt and Catch“ – Prinzip, dies bedeutet, \n"
				+ "dass der Spieler einem zufällig bewegtem Spielelement hinterherjagt und versucht dieses zu fangen.\n"
				+ "\n" + "Du bist eine Python:        und hast Hunger! \n" + "\n"
				+ "Dies ist der Hamster:       ,deine bevorzugte Beute.\n"
				+ "Deshalb ist es dein Ziel den Hamster zu jagen und ihn zu fressen. \n" + "\n"
				+ "Dein Jagdgebiet ist in kleinere Felder unterteilt. \n"
				+ " Jeweils du und der Hamster befinden sich auf einem Feld und \n"
				+ "könnt euch auf die vier angrenzenden Felder bewegen. \n"
				+ "Wenn du und der Hamster sich auf dem selben Feld befinden hast du gewonnen! \n"
				+ "Du steuerst die Python, indem du die Pfeiltasten drückst. \n" + "\n"
				+ "Damit die Python nach rechts läuft drücke:               \n" + "\n"
				+ "Links :                  Oben: \n" + "\n" + "Unten:                            \n" + "\n"
				+ "Pro Klicken bewegt sich die Python immer ein Feld auf dem Spielfeld in die jeweilige Richtung. \n"
				+ "Wie schnell du drückst ist dir überlassen, während die Geschwindigkeit mit der sich der Hamster immer ein Feld weiter bewegt gleich ist. \n"
				+ "Das wäre jetzt ja noch ziemlich einfach, deshalb gibt es einige Hindernisse, die dir im Weg stehen. \n"
				+ "Diese sind in den verschiedenen Level unterschiedlich. Für mehr Informationen bewege die Maus auf die Levelfelder im Auswahlmenü. \n"
				+ "Im ersten Level ist die Schwierigkeit, dass du den Hamster in einer bestimmten Zeit fangen musst. \n"
				+ "Pythons sind Kaltblüter, deshalb ist die Sonne sehr wichtig für sie. \n"
				+ "Ohne das wärmende Sonnenlicht erstarren sie. Die stylische Zeitleiste rechts von dem Spielfeld zeigt dir den Sonnenstand \n"
				+ "und läuft kontinuierlich ab. \n"
				+ "Ist die Sonne untergegangen hast du verloren! \n" + "\n" + "Hier kommst du zum ersten Level:  \n"
				+ "");

		anleitungsbuttons[1] = new Button((int) (getWidth() - (Gui.getWidth() / 3.3391304347826086956521739130435)),
				(int) (getHeight() - (Gui.getHeight() / 17.28)), (int) (Gui.getHeight() / 10.24),
				(int) (Gui.getHeight() / 24.685714285714285714285714285714));
		anleitungsbuttons[1].setText("Level 1");

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

	public static int getTile() {
		return tile;
	}

	public static void setTile(int tile) {
		Gui.tile = tile;
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