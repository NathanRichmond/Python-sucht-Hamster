package clocks;

import java.util.ArrayList;

import game.Korn;

public class Korn_Creation {

	public static ArrayList<Korn> koerner = new ArrayList<>();
	private int numberOfKoerner = 5;

	public Korn_Creation() {
		/*
		 * Add a Korn until numberOfKoerner is reached
		 */
		for (int i = 0; i < numberOfKoerner; i++) {
			koerner.add(new Korn());
		}
	}
	
	public static void remove(int index) {
		koerner.remove(index);
	}
	

}
