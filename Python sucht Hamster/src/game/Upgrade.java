package game;

import actions.Game;
import chars.Player;
import clocks.Upgrade_Timer;

public class Upgrade {

	private static Player p;

	private static int type; // type of the Upgrade. Currently, there's only one type (Korn)
	private static int duration;

	public Upgrade(String type) {
		p = Game.p;

		switch (type) {
		case "Korn":
			Upgrade.type = 1;
		}
		run(Upgrade.type);
	}

	private void run(int type) {
		switch (type) {
		case 1:
			setDuration(3);
			p.setUpgraded(true);
			Upgrade_Timer ut = new Upgrade_Timer(1);
			ut.start();

		}
	}

	public static int getDuration() {
		return duration;
	}

	public static void setDuration(int duration) {
		Upgrade.duration = duration;
	}

}
