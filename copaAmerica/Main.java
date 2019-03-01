package copaAmerica;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("2019 Copa America (Brasil)\n");
		TimeUnit.SECONDS.sleep(1);

		Drawer drawer = new Drawer();
		drawer.startDraw();

		// print groups
		drawer.printGroups(TimeUnit.MILLISECONDS, 25);

		// simulate games
		// calculates the points and sets winners and runner ups
		// print the final results
		CopaAmerica2019Simulator ca = new CopaAmerica2019Simulator(drawer);
		ca.simulateGames();

		for (Country c : ca.getValidDrawer().getTeams()) {
			c.calcPoints();
		}

		ca.printFinalGroupTables();

		ca.printGroupWRT();

		ca.printRankingThirdPlaceTeams();
		
		// ***** QUARTERFINAL *****
		System.out.println("\n  QUARTERFINALS");
		ca.simulateQuarterfinals();

		// ***** SEMIFINAL *****
		System.out.println("\n  SEMIFINALS");
		ca.simulateSemifinals();

		// ***** FINAL *****
		System.out.println("\n  FINAL");
		ca.simulateFinal();

	}

}
