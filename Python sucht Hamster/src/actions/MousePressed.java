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
				Gamestate.state = Gamestate_e.lvlselect;
			}
			if (Collision.cButton(Gui.startmenubuttons[1], x, y)) {
//				Gamestate.state = Gamestate_e.settings;
			}
			if (Collision.cButton(Gui.startmenubuttons[2], x, y)) {
				System.exit(0);
			}
			break;

		case lvlselect:
			for (int i = 0; i < Gui.getnLvls(); i++) { // cycle through buttons
				if (Collision.cButton(Gui.lvlselectbuttons[i], x, y)) {
					if (Game.getMaxLevelAvailable() >= i + 1) { // only if level was unlocked already
						Game.setLevel(i + 1); // i+1 because i starts at 0 but levels start at 1
						Game.startNewGame();
					}
				}
			}
			if (Collision.cButton(Gui.lvlselectbutton_back, x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			break;

		case ingame:
			if (Collision.cButton(Gui.ingamebutton_restart, x, y)) {
				Game.restartLevel();
			}
			break;

		case pause:
			if (Collision.cButton(Gui.pausebuttons[0], x, y)) {
				Gamestate.state = Gamestate_e.ingame;
			}
			if (Collision.cButton(Gui.pausebuttons[1], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.pausebuttons[2], x, y)) {
				System.exit(0);
			}
			break;

		case victory:
			if (Collision.cButton(Gui.gameendbuttons[0], x, y)) {
				Game.restartLevel();
			}
			if (Collision.cButton(Gui.gameendbuttons[1], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.gameendbuttons[2], x, y)) {
				System.exit(0);
			}
			if (Game.getLevel() + 1 <= Gui.getnLvls()) {
				if (Collision.cButton(Gui.victorybutton_nextlvl, x, y)) {
					Game.setLevel(Game.getLevel() + 1);
					Game.restartLevel();
				}
			}
			break;
		case defeat:
			if (Collision.cButton(Gui.gameendbuttons[0], x, y)) {
				Game.restartLevel();
			}
			if (Collision.cButton(Gui.gameendbuttons[1], x, y)) {
				Gamestate.state = Gamestate_e.startmenu;
			}
			if (Collision.cButton(Gui.gameendbuttons[2], x, y)) {
				System.exit(0);
			}
			break;
		default:
			break;
		}

	}
}
