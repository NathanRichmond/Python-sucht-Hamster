package actions;

import game.Gamestate;
import game.Gamestate_e;
import gui.Gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MousePressed implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent e) {


        switch (Gamestate.state) {
            case ingame:
                //Player.isShooting = true;
                break;
            case pause:
                if (Collision.cButton(Gui.buttons[0], (int) e.getX(), (int) e.getY())) {
                    Gamestate.state = Gamestate_e.ingame;
                }
                if (Collision.cButton(Gui.buttons[1], (int) e.getX(), (int) e.getY())) {
                    Gamestate.state = Gamestate_e.pause;
                }
                if (Collision.cButton(Gui.buttons[2], (int) e.getX(), (int) e.getY())) {
                    System.exit(0);
                }
                break;
		default:
			break;
        }

    }
}
