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
		for (int i = 0; i < Game.getnBabyhamsterTwo(); i++) {
			specialtiles.add(new SpecialTile("babyhamsterTwo"));
		}
		for (int i = 0; i < Game.getnBabyhamsterThree(); i++) {
			specialtiles.add(new SpecialTile("babyhamsterThree"));
		}
		for (int i = 0; i < Game.getnBabyhamsterFour(); i++) {
			specialtiles.add(new SpecialTile("babyhamsterFour"));
		}
		for (int i = 0; i < Game.getnHourglass(); i++) {
			specialtiles.add(new SpecialTile("hourglass"));
		}
		for (int i = 0; i < Game.getnHammer(); i++) {
			specialtiles.add(new SpecialTile("hammer"));
		}

		/*
		 * Planned placement for the Tutorial levels
		 */
		switch (Game.getLevel()) {
		case 105:
			setSpecialTilesLevel105();
			break;
		case 106:
			setSpecialTilesLevel106();
			break;
		case 107:
			setSpecialTilesLevel107();
			break;
		case 108:
			setSpecialTilesLevel108();
			break;
		default:
			break;
		}
	}

	private void setSpecialTilesLevel108() {
		int amount = 3; // amount of Special Tiles in this level
		for (int i = 0; i < amount; i++) {
			SpecialTile st = new SpecialTile("hammer"); // generate new Special Tile
			/*
			 * Set x and y coordinates
			 */
			switch (i) {
			case 1: // first Special Tile
				st.setX(5 * 33 + 1); // x coordinate of this particular Special Tile
				st.setY(2 * 33 + 1); // y coordinate of this particular Special Tile
				break;
			case 2: // second Special Tile
				st.setX(6 * 33 + 1); // x coordinate of this particular Special Tile
				st.setY(0 * 33 + 1); // y coordinate of this particular Special Tile
				break;
			case 3: // third Special Tile
				st.setX(11 * 33 + 1); // x coordinate of this particular Special Tile
				st.setY(2 * 33 + 1); // y coordinate of this particular Special Tile
				break;
			}
			specialtiles.add(st);
		}
	}

	private void setSpecialTilesLevel107() {
		int amount = 3; // amount of Special Tiles in this level

	}

	private void setSpecialTilesLevel106() {
		int amount = 3; // amount of Special Tiles in this level

	}

	private void setSpecialTilesLevel105() {
		int amount = 3; // amount of Special Tiles in this level

	}

	public static void remove(int index) {
		specialtiles.remove(index);
	}

}
