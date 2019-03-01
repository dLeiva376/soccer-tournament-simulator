package copaAmerica;

import java.util.Random;

public class Country implements Comparable<Country> {
	private String name; // name of the country
	private int potNumber; // pot Number (1-4) the country is in
	private int power; // the power of the country
	
	private int group; // group int (1-4) the country is in
	private int wins; // the number of wins of the country
	private int draws; // the number of draws of the country
	private int losses; // the number of losses of the country
	private int points; // the number of points of the country
	private int gf; // goals in favor
	private int ga; // goals against
	private int goalDiff; // goal difference

	// Country constructor
	public Country(String name, int potNumber, int power) {
		this.name = name;
		this.potNumber = potNumber;
		this.power = power;
		
		this.group = 0;
		this.wins = 0;
		this.draws = 0;
		this.losses = 0;
		this.points = 0;
		this.goalDiff = 0;
	}

	public int getGf() {
		return gf;
	}

	public int getGa() {
		return ga;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getPoints() {
		return points;
	}

	public void calcPoints() {
		int winPoints = this.wins * 3;
		int drawPoints = this.draws;
		this.points = winPoints + drawPoints;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public int getPotNumber() {
		return potNumber;
	}

	// Simulates the number of goals the team makes in a game
	public int simulateGoals() {
		Random r = new Random();
		// return a number between 0 and power
		return r.nextInt(this.power);
	}

	// prints the result of this game (counrtryA vs countryB)
	// return the winner of the game
	public Country vs(Country c) {
		int leftGoals = this.simulateGoals();
		int rightGoals = c.simulateGoals();

		this.gf = this.gf + leftGoals;
		this.ga = this.ga + rightGoals;
		c.gf = c.gf + rightGoals;
		c.ga = c.ga + leftGoals;

		if (leftGoals > rightGoals) {
			this.wins++;
			c.losses++;
		} else if (leftGoals < rightGoals) {
			this.losses++;
			c.wins++;
		} else {
			this.draws++;
			c.draws++;
		}
		this.addToGoalDiff(leftGoals - rightGoals);
		c.addToGoalDiff(rightGoals - leftGoals);

		System.out.println(this.getName() + " " + leftGoals + " - " + rightGoals + " " + c.getName());

		if (leftGoals >= rightGoals) {
			return this;
		} else {
			return c;
		}
	}

	public int getGoalDiff() {
		return goalDiff;
	}

	public void addToGoalDiff(int gameGoalDiff) {
		this.goalDiff = this.goalDiff + gameGoalDiff;
	}

	@Override
	public int compareTo(Country o) {
		Country other = (Country) o;
		if (this.getPoints() != other.getPoints()) {
			return (-1) * (this.getPoints() - other.getPoints());
		}
		if (this.getGoalDiff() != other.getGoalDiff()) {
			return (-1) * (this.getGoalDiff() - other.getGoalDiff());
		}
		// both have same points and same goal dif
		// tiebreaker: team with most goals
		return (-1) * (this.getGf() - other.getGf());
	}
}