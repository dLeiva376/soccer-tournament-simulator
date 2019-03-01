package fifaDraw;

import java.util.ArrayList;

public class Group extends ArrayList<Country> {
	private String name;
	private Country winner;
	private Country runnerUp;

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

}