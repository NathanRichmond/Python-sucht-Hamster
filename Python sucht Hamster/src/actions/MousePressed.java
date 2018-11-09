package actions;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import data.Collision;
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
				// Gamestate.state = Gamestate_e.manual;
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

		case ingame:
			if (Collision.cButton(Gui.ingamebuttons[0], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
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
				Gamestate.state = Gamestate_e.startmenu;
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
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.ingamebuttons[2], x, y)) {
				Game.restartLevel();
			}
			if (Game.getLevel() + 1 <= Gui.getnLvls()) {
				if (Collision.cButton(Gui.victorybutton, x, y)) {
					Game.setLevel(Game.getLevel() + 1);
					Game.restartLevel();
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
