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
	public static Button[] manualbuttons = new Button[12]; 
	public static Button[] ingamebuttons = new Button[3];
	public static Button[] anleitungsbuttons = new Button[2]; 
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
		initAnleitungsbutton(); 

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
		manualbuttons[6] = new Button(gridx + 3 + 11*66 , gridy +3 + 2*66, 62, 62); //zu Level 1
		manualbuttons[6].setText("Level 1");
		manualbuttons[7] = new Button(gridx + 3 + 3*66 , gridy +3 + 3*66, 62, 62); // Zeitleiste
		manualbuttons[8] = new Button(gridx + 3 + 7*66 , gridy +3 + 3*66, 62, 62); //Babyhamster
		manualbuttons[9] = new Button(gridx + 3 + 1*66 , gridy +3 , 62, 62); //Compiler
		manualbuttons[10] = new Button(gridx + 3 + 3*66 , gridy +3 , 62, 62); //Clipboard
		manualbuttons[11] = new Button(gridx + 3 + 9*66 , gridy +3, 62, 62); //Back
	}
	
	private void initAnleitungsbutton () {
		 anleitungsbuttons[0] = new Button(1, 1, getWidth(), getHeight());
		 anleitungsbuttons[0].setText("An Alle, die den Hamster-Simulator genauso hassen wie wir: \n" + 
		    		"Hier ist endlich die Gelegenheit deine ganze Wut und deinen Frust herauszulassen und Rache an dem Hamster zu nehmen. \n" + 
		    		"\n" + 
		    		"Python-sucht-Hamster  ist ein Spiel nach dem „Hunt and Catch“ – Prinzip, dies bedeutet, \n" + 
		    		"dass der Spieler einem zufällig bewegtem Spielelement hinterherjagt und versucht dieses zu fangen.\n" + 
		    		"\n" + 
		    		"Du bist eine Python:        und hast Hunger! \n" + 
		    		"\n"+
		    		"Dies ist der Hamster:       ,deine bevorzugte Beute.\n" + 
		    		"Deshalb ist es dein Ziel den Hamster zu jagen und ihn zu fressen. \n" + 
		    		"\n" + 
		    		"Dein Jagdgebiet ist in kleinere Felder unterteilt. \n" + 
		    		" Jeweils du und der Hamster befinden sich auf einem Feld und \n" + 
		    		"könnt euch auf die vier angrenzenden Felder bewegen. \n" + 
		    		"Wenn du und der Hamster sich auf dem selben Feld befinden hast du gewonnen! \n" + 
		    		"Du steuerst die Python, indem du die Pfeiltasten drückst. \n" + 
		    		"\n" + 
		    		"Damit die Python nach rechts läuft drücke:               \n" + 
		    		"\n"+
		    		"Links :                  Oben: \n" + 
		    		"\n"+
		    		"Unten:                            \n" + 
		    		"\n"+
		    		"Pro Klicken bewegt sich die Python immer ein Feld auf dem Spielfeld in die jeweilige Richtung. \n" + 
		    		"Wie schnell du drückst ist dir überlassen, während die Geschwindigkeit mit der sich der Hamster immer ein Feld weiter bewegt gleich ist. \n" + 
		    		"Das wäre jetzt ja noch ziemlich einfach, deshalb gibt es einige Hindernisse, die dir im Weg stehen. \n" + 
		    		"Diese sind in den verschiedenen Level unterschiedlich. Für mehr Informationen bewege die Maus auf die Levelfelder im Auswahlmenü. \n" + 
		    		"Im ersten Level ist die Schwierigkeit, dass du den Hamster in einer bestimmten Zeit fangen musst. \n" + 
		    		"Pythons sind Kaltblüter, deshalb ist die Sonne sehr wichtig für sie. \n" + 
		    		"Ohne das wärmende Sonnenlicht erstarren sie. Die stylische Zeitleiste rechts von dem Spielfeld zeigt dir den Sonnenstand \n" + 
		    		"und läuft kontinuierlich ab. \n" + 
		    		"(Bild der Zeitleiste einfügen) \n" + 
		    		"Ist die Sonne untergegangen hast du verloren! \n" + 
		    		"\n" + 
		    		"Hier kommst du zum ersten Level:  \n" + 
		    		"");
		    
		    anleitungsbuttons[1] = new Button(getWidth()-460, getHeight() -50, 150, 35); 
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
