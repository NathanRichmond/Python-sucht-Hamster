package clocks;

import java.util.ArrayList;

import actions.Game;
import game.SpecialTile;

public class SpecialTile_Creation {

	public static ArrayList<SpecialTile> specialtiles = new ArrayList<>(); // global array list with all Special Tiles
	
	public SpecialTile_Creation() {
		/*
		 * Add a Special Tile until nSpecialTiles is reached
		 */
		for (int i = 0; i < Game.getnKorn(); i++) {
			specialtiles.add(new SpecialTile("korn"));
		}
		for (int i = 0; i < Game.getnHourglass(); i++) {
			specialtiles.add(new SpecialTile("hourglass"));
		}
		for (int i = 0; i < Game.getnHammer(); i++) {
			specialtiles.add(new SpecialTile("hammer"));
		}
	}
	
	public static void remove(int index) {
		specialtiles.remove(index);
	}
	

}
