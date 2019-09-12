package game;

import java.util.ArrayList;

import gui.Grid;
import gui.Gui;

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

	private void setSpecialTilesLevel105() {
		String type = "korn";
		addSpecialTile(type, 3, 0);
		addSpecialTile(type, 3, 1);
		addSpecialTile(type, 5, 0);
		addSpecialTile(type, 5, 1);
		addSpecialTile(type, 7, 5);
		addSpecialTile(type, 0, 6);
	}

	private void setSpecialTilesLevel106() {
		String type = "hourglass";
		addSpecialTile(type, 3, 2);
		addSpecialTile(type, 3, 3);
		addSpecialTile(type, 7, 5);
		addSpecialTile(type, 10, 1);
		addSpecialTile(type, 10, 2);
		addSpecialTile(type, 13, 2);
		addSpecialTile(type, 14, 1);
		addSpecialTile(type, 14, 3);
	}

	private void setSpecialTilesLevel107() {
		String type = "babyhamsterTwo";
		addSpecialTile(type, 7, 2);
		addSpecialTile(type, 10, 2);
		addSpecialTile(type, 11, 0);

		type = "babyhamsterThree";
		addSpecialTile(type, 1, 0);
		addSpecialTile(type, 13, 2);

		type = "babyhamsterFour";
		addSpecialTile(type, 9, 0);
	}

	private void setSpecialTilesLevel108() {
		String type;

		type = "hammer";
		addSpecialTile(type, 5, 2);
		addSpecialTile(type, 6, 0);
		addSpecialTile(type, 11, 2);
	}

	public static void remove(int index) {
		specialtiles.remove(index);
	}

	/*
	 * Add Special Tile manually
	 */
	private void addSpecialTile(String type, int x, int y) {
		SpecialTile st = new SpecialTile(type);
		st.setX(Grid.getX() + (x * Gui.getTile() + 1));
		st.setY(Grid.getY() + (y * Gui.getTile() + 1));
		specialtiles.add(st);
	}

}
