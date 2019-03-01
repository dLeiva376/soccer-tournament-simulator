package copaAmerica;

import java.util.ArrayList;
import java.util.Collections;

public class CopaAmerica2019Simulator {
	private Drawer validDrawer;
	private Country ab;
	private Country bc;
	private Group thirdPlaceTeams;
	private ArrayList<Country> semifinalists;
	private ArrayList<Country> finalists;

	public CopaAmerica2019Simulator(Drawer d) {
		this.thirdPlaceTeams = new Group("Teams");
		this.semifinalists = new ArrayList<>();
		this.finalists = new ArrayList<>();
		this.validDrawer = d;
	}

	public void simulateGames() {
		// Group
		this.simulateGames(this.validDrawer.getA());
		this.simulateGames(this.validDrawer.getB());
		this.simulateGames(this.validDrawer.getC());
	}

	public void simulateGames(Group g) {
		System.out.println("Group " + g.getName() + " matches");
		g.get(0).vs(g.get(1));
		g.get(2).vs(g.get(3));
		g.get(0).vs(g.get(2));
		g.get(3).vs(g.get(1));
		g.get(3).vs(g.get(0));
		g.get(1).vs(g.get(2));
		System.out.println();
	}

	public void printFinalGroupTables() {
		Collections.sort(this.validDrawer.getA());
		Collections.sort(this.validDrawer.getB());
		Collections.sort(this.validDrawer.getC());
		this.printFinalGroupTable(this.validDrawer.getA());
		this.printFinalGroupTable(this.validDrawer.getB());
		this.printFinalGroupTable(this.validDrawer.getC());
	}

	public void printFinalGroupTable(Group g) {
		String indent = "";
		String indent2 = "    ";
		String indent3 = "    ";
		if(g.getName().equals("Teams")) {
			System.out.println(" " + g.getName() + "\t\tPts  PLD  W    D    L    GF   GA   GD");
		}
		else {
			System.out.println("Group " + g.getName() + "\t\tPts  PLD  W    D    L    GF   GA   GD");
		}
		
		
		for (Country c : g) {
			if (c.getName().length() < 8) {
				indent = "\t\t";
			} else {
				indent = "\t";
			}
			if (c.getGf() >= 10) {
				indent2 = "   ";
			}
			if (c.getGoalDiff() < 0) {
				indent3 = "   ";
			}
			System.out.println(c.getName() + indent + c.getPoints() + "    3    " + c.getWins() + "    " + c.getDraws()
					+ "    " + c.getLosses() + "    " + c.getGf() + indent2 + c.getGa() + indent3 + c.getGoalDiff());
			indent2 = "    ";
			indent3 = "    ";
		}
		System.out.println();
	}

	public void printGroupWRT() {
		printGroupWRT(this.validDrawer.getA());
		printGroupWRT(this.validDrawer.getB());
		printGroupWRT(this.validDrawer.getC());

	}

	public void printGroupWRT(Group g) {
		g.setWinner(g.get(0));
		g.setRunnerUp(g.get(1));
		g.setThird(g.get(2));
		System.out.println("  Winner:   " + g.getWinner().getName());
		System.out.println("  RunnerUp: " + g.getRunnerUp().getName());
		System.out.println("  Third: 	" + g.getThird().getName());
		Country third = g.getThird();
		this.thirdPlaceTeams.add(third);
		System.out.println();
	}
	
	public void printRankingThirdPlaceTeams() {
		Collections.sort(this.thirdPlaceTeams);
		this.ab = thirdPlaceTeams.get(0);
		this.bc = thirdPlaceTeams.get(1);
		System.out.println("Ranking of Third Place Teams");
		printFinalGroupTable(thirdPlaceTeams);
		System.out.println();
		
	}

	public void simulateQuarterfinals() {
		// Game 1 : 0 vs 1
		Country s1 = this.getValidDrawer().getA().getWinner().vs(this.bc);
		this.semifinalists.add(s1);

		// Game 2 : 2 vs 3
		Country s2 = this.getValidDrawer().getA().getRunnerUp().vs(this.getValidDrawer().getB().getRunnerUp());
		this.semifinalists.add(s2);

		// Game 3 : 4 vs 5
		Country s3 = this.getValidDrawer().getB().getWinner().vs(this.getValidDrawer().getC().getRunnerUp());
		this.semifinalists.add(s3);

		// Game 4 : 5 vs 7
		Country s4 = this.getValidDrawer().getC().getWinner().vs(this.ab);
		this.semifinalists.add(s4);
	}

	public void simulateSemifinals() {
		// Game 1 : 0 vs 1
		Country f1 = this.semifinalists.get(0).vs(this.semifinalists.get(1));
		this.finalists.add(f1);

		// Game 1 : 0 vs 1
		Country f2 = this.semifinalists.get(2).vs(this.semifinalists.get(3));
		this.finalists.add(f2);
	}

	public void simulateFinal() {
		Country champ = this.finalists.get(0).vs(this.finalists.get(1));
		System.out.println("2019 Copa America Champion: " + champ.getName() + "!!!");
	}

	public void setValidDrawer(Drawer d) {
		validDrawer = d;
	}

	public Drawer getValidDrawer() {
		return validDrawer;
	}
}
