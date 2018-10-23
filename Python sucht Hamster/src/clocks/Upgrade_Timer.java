package clocks;

import java.util.Timer;
import java.util.TimerTask;

import actions.Main;
import chars.Player;
import game.Upgrade;

public class Upgrade_Timer {

	static Player p;

	private static Timer timer;
	private static int delay = 0;
	private static int period = 1000;
	private static int upgradetype;

	public Upgrade_Timer(int upgradetype) {
		p = Main.p;
		Upgrade_Timer.upgradetype = upgradetype;
	}

	public void start() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (Upgrade.getDuration() > 0) {
					Upgrade.setDuration(Upgrade.getDuration() - 1);
				} else {
					switch (upgradetype) {
					case 1:
						p.setUpgraded(false);
					}
					timer.cancel();
				}
			}
		}, delay, period);

	}

}
