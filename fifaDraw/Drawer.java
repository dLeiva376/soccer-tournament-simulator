package fifaDraw;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Drawer {
	private Drawer finalGroups;
	private ArrayList<Country> qualifiedTeams; // all teams
	private ArrayList<Country> pot1; // pot1 teams
	private ArrayList<Country> pot2; // pot2 teams
	private ArrayList<Country> pot3; // pot3 teams
	private ArrayList<Country> pot4; // pot4 teams
	private Group a;
	private Group b;
	private Group c;
	private Group d;
	private Group e;
	private Group f;
	private Group g;
	private Group h;
	
	Country russia = new Country("Russia", 1, "UEFA", 3);
	Country germany = new Country("Germany", 1, "UEFA", 6);
	Country brazil = new Country("Brazil", 1, "CONMEBOL", 6);
	Country argentina = new Country("Argentina", 1, "CONMEBOL", 6);
	Country portugal = new Country("Portugal", 1, "UEFA", 6);
	Country belgium = new Country("Belgium", 1, "UEFA", 6);
	Country poland = new Country("Poland", 1, "UEFA", 6);
	Country france = new Country("France", 1, "UEFA", 5);
	Country spain = new Country("Spain", 2, "UEFA", 5);
	Country peru = new Country("Peru", 2, "CONMEBOL", 5);
	Country switzerland = new Country("Switzerlan", 2, "UEFA", 5);
	Country england = new Country("England", 2, "UEFA", 5);
	Country colombia = new Country("Colombia", 2, "CONMEBOL", 5);
	Country mexico = new Country("Mexico", 2, "CONCACAF", 6);
	Country uruguay = new Country("Uruguay", 2, "CONMEBOL", 6);
	Country croatia = new Country("Croatia", 2, "UEFA", 5);
	Country denmark = new Country("Denmark", 3, "UEFA", 5);
	Country iceland = new Country("Iceland", 3, "UEFA", 4);
	Country costaRica = new Country("CostaRica", 3, "CONCACAF", 3);
	Country sweden = new Country("Sweden", 3, "UEFA", 4);
	Country tunisia = new Country("Tunisia", 3, "CAF", 3);
	Country egypt = new Country("Egypt", 3, "CAF", 3);
	Country senegal = new Country("Senegal", 3, "CAF", 3);
	Country iran = new Country("Iran", 3, "AFC", 3);
	Country serbia = new Country("Serbia", 4, "UEFA", 4);
	Country nigeria = new Country("Nigeria", 4, "CAF", 4);
	Country australia = new Country("Australia", 4, "AFC", 2);
	Country japan = new Country("Japan", 4, "AFC", 2);
	Country moroco = new Country("Moroco", 4, "CAF", 2);
	Country panama = new Country("Panama", 4, "CONCACAF", 2);
	Country southKorea = new Country("SouthKorea", 4, "AFC", 2);
	Country saudiArabia = new Country("SaudiArabia", 4, "AFC", 2);

	public Drawer() {
		qualifiedTeams = new ArrayList<>();
		pot1 = new ArrayList<>();
		pot2 = new ArrayList<>();
		pot3 = new ArrayList<>();
		pot4 = new ArrayList<>();
		// sets up pots
		initPots();
		// sets up groups (empty), initializes groups
		initGroups();
	}

	// creates countries, fills qualifiedTeams, pots
	public void initPots() {
		qualifiedTeams.add(germany);
		qualifiedTeams.add(brazil);
		qualifiedTeams.add(portugal);
		qualifiedTeams.add(argentina);
		qualifiedTeams.add(belgium);
		qualifiedTeams.add(poland);
		qualifiedTeams.add(france);
		qualifiedTeams.add(spain);
		qualifiedTeams.add(peru);
		qualifiedTeams.add(switzerland);
		qualifiedTeams.add(england);
		qualifiedTeams.add(colombia);
		qualifiedTeams.add(mexico);
		qualifiedTeams.add(uruguay);
		qualifiedTeams.add(croatia);
		qualifiedTeams.add(denmark);
		qualifiedTeams.add(iceland);
		qualifiedTeams.add(costaRica);
		qualifiedTeams.add(sweden);
		qualifiedTeams.add(tunisia);
		qualifiedTeams.add(egypt);
		qualifiedTeams.add(senegal);
		qualifiedTeams.add(iran);
		qualifiedTeams.add(serbia);
		qualifiedTeams.add(nigeria);
		qualifiedTeams.add(australia);
		qualifiedTeams.add(japan);
		qualifiedTeams.add(moroco);
		qualifiedTeams.add(panama);
		qualifiedTeams.add(southKorea);
		qualifiedTeams.add(saudiArabia);
		for (Country c : qualifiedTeams) {
			if (c.getPotNumber() == 1) {
				this.pot1.add(c);
			}
			if (c.getPotNumber() == 2) {
				this.pot2.add(c);
			}
			if (c.getPotNumber() == 3) {
				this.pot3.add(c);
			}
			if (c.getPotNumber() == 4) {
				this.pot4.add(c);
			}
		}
		// to keep Russia out of pot1 for now.
		qualifiedTeams.add(0, russia);
	}

	public void initGroups() {
		this.a = new Group("A");
		this.b = new Group("B");
		this.c = new Group("C");
		this.d = new Group("D");
		this.e = new Group("E");
		this.f = new Group("F");
		this.g = new Group("G");
		this.h = new Group("H");
	}

	
	// MAIN //
	public static void main(String[] args) throws InterruptedException {
		
		FifaWorldCup2018Simulator m = new FifaWorldCup2018Simulator();
		int count = 0;
		while (true) {
			boolean valid = doDraw(m);
			if (!valid) {
				count++;
				continue;
			}
			else {
				break;
			}
		}
		System.out.println("Done in " + count);

		// m has a reference to the valid group
		System.out.println("2018 FIFA World Cup draw simulator\n");
		TimeUnit.SECONDS.sleep(1);

		// prints the groups, in delayed printing
		m.getValidDrawer().printAllGroupsSlow(TimeUnit.MILLISECONDS, 250);

		// simulates the games
		// calculates the points and sets winners and runner ups
		// print the final results. ie: Russia 0 - 2 Peru
		System.out.println();
		m.simulateGames();

		// prints each group. Country - points earned
		for (Country c : m.getValidDrawer().getQualifiedTeams()) {
			c.calcPoints();
		}
		m.printFinalGroupTables();

		// print teams that advance to round of 16 (Group Winners and
		// RunnerUppers)
		m.printGroupWR();

		// ***** ROUND OF 16 *****
		System.out.println("\n  ROUND OF 16 MATCHES");
		m.simulateRoundOf16();

		// ***** QUARTERFINAL *****
		System.out.println("\n  QUARTERFINALS");
		m.simulateQuarterfinals();

		// ***** SEMIFINAL *****
		System.out.println("\n  SEMIFINALS");
		m.simulateSemifinals();

		// ***** FINAL *****
		System.out.println("\n  FINALS");
		m.simulateFinal();

	}
	// end of main

	public static boolean doDraw(FifaWorldCup2018Simulator m) throws InterruptedException {
		// Creates drawer
		Drawer drawer = new Drawer();

		drawer.shufflePots();
		for (int i = 0; i < 8; i++) {
			Country c1 = drawer.pot1.get(i);
			c1.setGroup(i + 1);

			Country c2 = drawer.pot2.get(i);
			c2.setGroup(i + 1);

			Country c3 = drawer.pot3.get(i);
			c3.setGroup(i + 1);

			Country c4 = drawer.pot4.get(i);
			c4.setGroup(i + 1);
		}
		drawer.setGroups();

		if (drawer.isValid()) {
			m.setValidDrawer(drawer);
		}
		return drawer.isValid();
	}

	public void printAllGroupsSlow(TimeUnit unit, long delay) throws InterruptedException {
		System.out.println("Group A\t\tGroup B\t\tGroup C\t\tGroup D\t\tGroup E\t\tGroup F\t\tGroup G\t\tGroup H");
		unit.sleep(delay);
		unit.sleep(delay);
		this.printCountriesSlow(pot1, unit, delay);
		this.printCountriesSlow(pot2, unit, delay);
		this.printCountriesSlow(pot3, unit, delay);
		this.printCountriesSlow(pot4, unit, delay);
	}

	public void printCountriesSlow(ArrayList<Country> list, TimeUnit unit, long delay) throws InterruptedException {
		for (Country c : list) {
			System.out.print(c.getName());
			if (c.getName().length() < 8) {
				System.out.print("\t\t");
			} else {
				System.out.print("\t");
			}
			unit.sleep(delay);
		}
		System.out.println();
	}

	public void shufflePots() {
		Collections.shuffle(this.pot1);
		Collections.shuffle(this.pot2);
		Collections.shuffle(this.pot3);
		Collections.shuffle(this.pot4);
		this.pot1.add(0, this.qualifiedTeams.get(0)); // adds Russia to pot1
	}

	public void setGroups() {
		for (int i = 0; i < this.qualifiedTeams.size(); i++) {
			Country c = this.qualifiedTeams.get(i);
			if (c.getGroup() == 1) {
				this.a.add(c);
			}
			if (c.getGroup() == 2) {
				this.b.add(c);
			}
			if (c.getGroup() == 3) {
				this.c.add(c);
			}
			if (c.getGroup() == 4) {
				this.d.add(c);
			}
			if (c.getGroup() == 5) {
				this.e.add(c);
			}
			if (c.getGroup() == 6) {
				this.f.add(c);
			}
			if (c.getGroup() == 7) {
				this.g.add(c);
			}
			if (c.getGroup() == 8) {
				this.h.add(c);
			}
		}
	}

	public boolean isValid(int groupN) {
		int uefa = 0;
		int conmebol = 0;
		int concacaf = 0;
		int caf = 0;
		int afc = 0;
		for (Country c : this.qualifiedTeams) {
			if (c.getGroup() == groupN) {
				if (c.getConfederation().equals("UEFA")) {
					uefa++;
				}
				if (c.getConfederation().equals("CONMEBOL")) {
					conmebol++;
				}
				if (c.getConfederation().equals("CONCACAF")) {
					concacaf++;
				}
				if (c.getConfederation().equals("CAF")) {
					caf++;
				}
				if (c.getConfederation().equals("AFC")) {
					afc++;
				}
			}
		}
		if (uefa > 2 || conmebol > 1 || concacaf > 1 || caf > 2 || afc > 2) {
			return false;
		}
		return true;
	}

	public boolean isValid() {
		if (isValid(1) == false)
			return false;
		if (isValid(2) == false)
			return false;
		if (isValid(3) == false)
			return false;
		if (isValid(4) == false)
			return false;
		if (isValid(5) == false)
			return false;
		if (isValid(6) == false)
			return false;
		if (isValid(7) == false)
			return false;
		if (isValid(8) == false)
			return false;
		return true;
	}

	public ArrayList<Country> getQualifiedTeams() {
		return qualifiedTeams;
	}

	public ArrayList<Country> getPot1() {
		return pot1;
	}

	public ArrayList<Country> getPot2() {
		return pot2;
	}

	public ArrayList<Country> getPot3() {
		return pot3;
	}

	public ArrayList<Country> getPot4() {
		return pot4;
	}

	public Group getA() {
		return a;
	}

	public void setA(Group a) {
		this.a = a;
	}

	public Group getB() {
		return b;
	}

	public void setB(Group b) {
		this.b = b;
	}

	public Group getC() {
		return c;
	}

	public void setC(Group c) {
		this.c = c;
	}

	public Group getD() {
		return d;
	}

	public void setD(Group d) {
		this.d = d;
	}

	public Group getE() {
		return e;
	}

	public void setE(Group e) {
		this.e = e;
	}

	public Group getF() {
		return f;
	}

	public void setF(Group f) {
		this.f = f;
	}

	public Group getG() {
		return g;
	}

	public void setG(Group g) {
		this.g = g;
	}

	public Group getH() {
		return h;
	}

	public void setH(Group h) {
		this.h = h;
	}

	public void setPot1(ArrayList<Country> pot1) {
		this.pot1 = pot1;
	}

	public void setPot2(ArrayList<Country> pot2) {
		this.pot2 = pot2;
	}

	public void setPot3(ArrayList<Country> pot3) {
		this.pot3 = pot3;
	}

	public void setPot4(ArrayList<Country> pot4) {
		this.pot4 = pot4;
	}
}
