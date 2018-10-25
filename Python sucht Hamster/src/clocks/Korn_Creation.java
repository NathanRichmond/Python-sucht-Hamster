package clocks;

import java.util.ArrayList;

import actions.Game;
import game.Korn;

public class Korn_Creation {

	public static ArrayList<Korn> koerner = new ArrayList<>();
	
	public Korn_Creation() {
		/*
		 * Add a Korn until nKoerner is reached
		 */
		for (int i = 0; i < Game.getnKoerner(); i++) {
			koerner.add(new Korn());
		}
	}
	
	public static void remove(int index) {
		koerner.remove(index);
	}
	

}
