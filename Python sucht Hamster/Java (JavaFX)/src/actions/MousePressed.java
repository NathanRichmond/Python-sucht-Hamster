package actions;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import data.Collision;
import game.Game;
import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;

public class MousePressed implements EventHandler<MouseEvent> {
	@Override
	public void handle(MouseEvent e) {

		int x = (int) e.getX(); // x coord of mouse position where clicked
		int y = (int) e.getY(); // y coord of mouse position where clicked

		switch (Gamestate.state) {

		case startmenu:
			if (Collision.cButton(Gui.startmenubuttons[0], x, y)) {
				System.exit(0);
			}
			if (Collision.cButton(Gui.startmenubuttons[1], x, y)) { // Hamster skins
				if (Gui.getHamsterSkin() + 1 == Gui.getnHamsterSkins()) { // if last skin is reached
					Gui.setHamsterSkin(0); // select first skin
				} else {
					Gui.setHamsterSkin(Gui.getHamsterSkin() + 1); // select next skin
				}
			}
			if (Collision.cButton(Gui.startmenubuttons[2], x, y)) { // Python skins
				if (Gui.getPythonSkin() + 1 == Gui.getnPythonSkins()) { // if last skin is reached
					Gui.setPythonSkin(0); // select first skin
				} else {
					Gui.setPythonSkin(Gui.getPythonSkin() + 1); // select next skin
				}
			}
			if (Collision.cButton(Gui.startmenubuttons[3], x, y)) {
				Gamestate.state = Gamestate_e.tutorialmenu;
			}
			for (int i = 0; i < Gui.getnLvls(); i++) { // cycle through level select buttons
				if (Collision.cButton(Gui.lvlselectbuttons[i], x, y)) {
					if (Game.getMaxLevelAvailable() >= i + 1) { // only if level was unlocked already
						Game.setLevel(i + 1); // i+1 because i starts at 0 but levels start at 1
						Game.startNewGame();
					}
				}
			}
			break;

		case tutorialmenu:
			if (Collision.cButton(Gui.startmenubuttons[0], x, y)) {
				System.exit(0);
			}
			for (int i = 0; i < 7; i++) { // cycle through tutlevel select buttons
				if (Collision.cButton(Gui.tutorialmenubuttons[i], x, y)) {
					if (i == 0) {
						Game.setLevel(101);
					} else {
						Game.setLevel(i + 102); // Ex.: Array index [3] - tutlvl 105 (because 102 has no button in
												// array)
					}
					Game.startNewGame();
				}
			}
			if (Collision.cButton(Gui.tutorialmenubuttons[7], x, y)) { // Button Fragezeichen
				Game.setBehindTheGame(false);
			}
			if (Collision.cButton(Gui.tutorialmenubuttons[8], x, y)) {
				Game.setLevel(1);
				Game.startNewGame();
			}
			if (Collision.cButton(Gui.tutorialmenubuttons[9], x, y)) { // Button Compiler
				Game.setBehindTheGame(true);
			}
			if (Collision.cButton(Gui.tutorialmenubuttons[10], x, y)) {
				if(Game.isAltManual()==true) {
				Gamestate.state = Gamestate_e.manual;
				} else {
					Gamestate.state = Gamestate_e.anleitung;
				}
			}
			if (Collision.cButton(Gui.tutorialmenubuttons[11], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.tutorialmenubuttons[0], x, y)) {
				Game.setLevel(101);
				Game.startNewGame();
			}
			break;

		case anleitung:
			if (Collision.cButton(Gui.anleitungsbuttons[1], x, y)) {
				Game.setLevel(1);
				Game.startNewGame();
			}
			break;
			
		case manual:
			if (Collision.cButton(Gui.manualbuttons[1], x, y)) {
				Game.setManualpage(1);
			}
			if (Collision.cButton(Gui.manualbuttons[2], x, y)) {
				Game.setLevel(1);
				Game.startNewGame();
			}
			break;
			
		case ingame:
			if (Collision.cButton(Gui.ingamebuttons[0], x, y)) {
				if (Game.getLevel() < 100) {
					Gamestate.state = Gamestate_e.startmenu;
				} else { // return to Tutorial menu when in a Tutorial level
					Gamestate.state = Gamestate_e.tutorialmenu;
				}
			}
			if (Collision.cButton(Gui.ingamebuttons[1], x, y)) {
				Gamestate.state = Gamestate_e.pause;
			}
			if (Collision.cButton(Gui.ingamebuttons[2], x, y)) {
				Game.restartLevel();
			}
			break;

		case pause:
			if (Collision.cButton(Gui.ingamebuttons[0], x, y)) {
				if (Game.getLevel() < 100) {
					Gamestate.state = Gamestate_e.startmenu;
				} else { // return to Tutorial menu when in a Tutorial level
					Gamestate.state = Gamestate_e.tutorialmenu;
				}
			}
			if (Collision.cButton(Gui.ingamebuttons[1], x, y)) {
				Gamestate.state = Gamestate_e.ingame;
			}
			if (Collision.cButton(Gui.ingamebuttons[2], x, y)) {
				Game.restartLevel();
			}
			break;

		case victory:
			if (Collision.cButton(Gui.ingamebuttons[0], x, y)) {
				if (Game.getLevel() < 100) {
					Gamestate.state = Gamestate_e.startmenu;
				} else { // return to Tutorial menu when in a Tutorial level
					Gamestate.state = Gamestate_e.tutorialmenu;
				}
			}
			if (Collision.cButton(Gui.ingamebuttons[2], x, y)) {
				Game.restartLevel();
			}
			if (Game.getLevel() + 1 <= Gui.getnLvls() || Game.getLevel() > 100) {
				if (Collision.cButton(Gui.victorybutton, x, y)) {
					Game.setLevel(Game.getLevel() + 1);
					Game.startNewGame();
				}
			}
			break;

		case defeat:
			if (Collision.cButton(Gui.ingamebuttons[0], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.ingamebuttons[2], x, y)) {
				Game.restartLevel();
			}
			break;

		default:
			break;
		}

	}
}
