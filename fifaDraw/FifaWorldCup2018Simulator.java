package fifaDraw;

import java.util.ArrayList;
import java.util.Collections;

public class FifaWorldCup2018Simulator {
	private Drawer validDrawer;
	private ArrayList<Country> quarterfinalists;
	private ArrayList<Country> semifinalists;
	private ArrayList<Country> finalists;

	public FifaWorldCup2018Simulator() {
		this.quarterfinalists = new ArrayList<>();
		this.semifinalists = new ArrayList<>();
		this.finalists = new ArrayList<>();
	}

	public void simulateGames() {
		// Group
		this.simulateGames(this.validDrawer.getA());
		this.simulateGames(this.validDrawer.getB());
		this.simulateGames(this.validDrawer.getC());
		this.simulateGames(this.validDrawer.getD());
		this.simulateGames(this.validDrawer.getE());
		this.simulateGames(this.validDrawer.getF());
		this.simulateGames(this.validDrawer.getG());
		this.simulateGames(this.validDrawer.getH());
	}

	public void simulateGames(Group g) {
		// groupA: rusia, saudi, egipt, uru
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
		this.sortGroups();
		this.printFinalGroupTable(this.validDrawer.getA());
		this.printFinalGroupTable(this.validDrawer.getB());
		this.printFinalGroupTable(this.validDrawer.getC());
		this.printFinalGroupTable(this.validDrawer.getD());
		this.printFinalGroupTable(this.validDrawer.getE());
		this.printFinalGroupTable(this.validDrawer.getF());
		this.printFinalGroupTable(this.validDrawer.getG());
		this.printFinalGroupTable(this.validDrawer.getH());
	}

	public void sortGroups() {
		Collections.sort(this.validDrawer.getA());
		Collections.sort(this.validDrawer.getB());
		Collections.sort(this.validDrawer.getC());
		Collections.sort(this.validDrawer.getD());
		Collections.sort(this.validDrawer.getE());
		Collections.sort(this.validDrawer.getF());
		Collections.sort(this.validDrawer.getG());
		Collections.sort(this.validDrawer.getH());
	}

	public void printFinalGroupTable(Group g) {
		String indent = "";
		String indent2 = "    ";
		String indent3 = "    ";
		System.out.println("Group " + g.getName() + "\t\tPts  PLD  W    D    L    GF   GA   GD");
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

	public void printGroupWR() {
		printGroupWR(this.validDrawer.getA());
		printGroupWR(this.validDrawer.getB());
		printGroupWR(this.validDrawer.getC());
		printGroupWR(this.validDrawer.getD());
		printGroupWR(this.validDrawer.getE());
		printGroupWR(this.validDrawer.getF());
		printGroupWR(this.validDrawer.getG());
		printGroupWR(this.validDrawer.getH());
	}

	public void printGroupWR(Group g) {
		g.setWinner(g.get(0));
		g.setRunnerUp(g.get(1));
		System.out.println("Group " + g.getName());
		System.out.println("  Winner:   " + g.getWinner().getName());
		System.out.println("  RunnerUp: " + g.getRunnerUp().getName());
	}

	public void simulateRoundOf16() {
		// Game 1 : winnerA vs runnerupB
		Country q1 = this.getValidDrawer().getA().getWinner().vs(this.getValidDrawer().getB().getRunnerUp());
		this.quarterfinalists.add(q1);

		// Game 2 : winnerC vs runnerupD
		Country q2 = this.getValidDrawer().getC().getWinner().vs(this.getValidDrawer().getD().getRunnerUp());
		this.quarterfinalists.add(q2);

		// Game 3 : winnerE vs runnerupF
		Country q3 = this.getValidDrawer().getE().getWinner().vs(this.getValidDrawer().getF().getRunnerUp());
		this.quarterfinalists.add(q3);

		// Game 4 : winnerG vs runnerupH
		Country q4 = this.getValidDrawer().getG().getWinner().vs(this.getValidDrawer().getH().getRunnerUp());
		this.quarterfinalists.add(q4);

		// Game 5 : winnerB vs runnerupA
		Country q5 = this.getValidDrawer().getB().getWinner().vs(this.getValidDrawer().getA().getRunnerUp());
		this.quarterfinalists.add(q5);
		// Game 6 : winnerD vs runnerupC
		Country q6 = this.getValidDrawer().getD().getWinner().vs(this.getValidDrawer().getC().getRunnerUp());
		this.quarterfinalists.add(q6);

		// Game 7 : winnerF vs runnerupE
		Country q7 = this.getValidDrawer().getF().getWinner().vs(this.getValidDrawer().getE().getRunnerUp());
		this.quarterfinalists.add(q7);

		// Game 8 : winnerH vs runnerupG
		Country q8 = this.getValidDrawer().getH().getWinner().vs(this.getValidDrawer().getG().getRunnerUp());
		this.quarterfinalists.add(q8);
	}

	public void simulateQuarterfinals() {
		// Game 1 : 0 vs 1
		Country s1 = this.quarterfinalists.get(0).vs(this.quarterfinalists.get(1));
		this.semifinalists.add(s1);

		// Game 2 : 2 vs 3
		Country s2 = this.quarterfinalists.get(2).vs(this.quarterfinalists.get(3));
		this.semifinalists.add(s2);

		// Game 3 : 4 vs 5
		Country s3 = this.quarterfinalists.get(4).vs(this.quarterfinalists.get(5));
		this.semifinalists.add(s3);

		// Game 4 : 5 vs 7
		Country s4 = this.quarterfinalists.get(6).vs(this.quarterfinalists.get(7));
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
		System.out.println("2018 World Cup Champion: " + champ.getName() + "!!!");
	}

	public void setValidDrawer(Drawer d) {
		validDrawer = d;
	}

	public Drawer getValidDrawer() {
		return validDrawer;
	}
}