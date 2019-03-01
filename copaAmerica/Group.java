package copaAmerica;

import java.util.ArrayList;

public class Group extends ArrayList<Country> {
	private String name;
	private Country winner;
	private Country runnerUp;
	private Country third;

	public Group(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getWinner() {
		return winner;
	}

	public void setWinner(Country winner) {
		this.winner = winner;
	}

	public Country getRunnerUp() {
		return runnerUp;
	}

	public void setRunnerUp(Country runnerUp) {
		this.runnerUp = runnerUp;
	}
	
	public Country getThird() {
		return this.third;		
	}

	public void setThird(Country third) {
		this.third = third;		
	}

}