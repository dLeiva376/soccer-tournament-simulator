package copaAmerica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Drawer {
	
	private ArrayList<Country> pot1;
	private ArrayList<Country> pot2;
	private ArrayList<Country> pot3;
	private ArrayList<Country> pot4;
	private Group a;
	private Group b;
	private Group c;
	private ArrayList<Country> teams;
	
	public Drawer() {
		this.pot1 = new ArrayList<>();
		this.pot2 = new ArrayList<>();
		this.pot3 = new ArrayList<>();
		this.pot4 = new ArrayList<>();
		this.a = new Group("A");
		this.b = new Group("B");
		this.c = new Group("C");
		this.teams = new ArrayList<>();
		Country brazil = new Country("Brazil", 1, 6);
		Country uruguay = new Country("Uruguay", 1, 5);
		Country argentina = new Country("Argentina", 1, 5);
		Country colombia = new Country("Colombia", 2, 5);
		Country chile = new Country("Chile", 2, 4);
		Country peru = new Country("Peru", 2, 4);
		Country venezuela = new Country("Venezuela", 3, 3);
		Country paraguay = new Country("Paraguay", 3, 3);
		Country japan = new Country("Japan", 3, 3);
		Country ecuador	= new Country("Ecuador", 4, 3);
		Country bolivia = new Country("Bolivia", 4, 2);
		Country qatar = new Country("Qatar", 4, 2);
		//teams.add(brazil);
		teams.add(uruguay);
		teams.add(argentina);
		teams.add(colombia);
		teams.add(chile);
		teams.add(peru);
		teams.add(venezuela);
		teams.add(paraguay);
		teams.add(japan);
		teams.add(ecuador);
		teams.add(bolivia);
		teams.add(qatar);
		for(Country c : teams) {
			if(c.getPotNumber() == 1) {
				this.pot1.add(c);
			}
			if(c.getPotNumber() == 2) {
				this.pot2.add(c);
			}
			if(c.getPotNumber() == 3) {
				this.pot3.add(c);
			}
			if(c.getPotNumber() == 4) {
				this.pot4.add(c);
			}
		}
		teams.add(0, brazil);
		
	}
	
	public void startDraw() {
		Collections.shuffle(this.pot1);
		Collections.shuffle(this.pot2);
		Collections.shuffle(this.pot3);
		Collections.shuffle(this.pot4);
		this.pot1.add(0, this.teams.get(0)); // adds brazil to pot1
		for(int i = 0; i < 3; i++) {
			Country c1 = this.pot1.get(i);
			c1.setGroup(i+1);
			Country c2 = this.pot2.get(i);
			c2.setGroup(i+1);
			Country c3 = this.pot3.get(i);
			c3.setGroup(i+1);
			Country c4 = this.pot4.get(i);
			c4.setGroup(i+1);	
		}
		for (int i = 0; i < this.teams.size(); i++) {
			Country c = this.teams.get(i);
			if (c.getGroup() == 1) {
				this.a.add(c);
			}
			if (c.getGroup() == 2) {
				this.b.add(c);
			}
			if (c.getGroup() == 3) {
				this.c.add(c);
			}
		}	
	}

	public void printGroups(TimeUnit unit, long delay) throws InterruptedException {
		System.out.println("Group A\t\tGroup B\t\tGroup C");
		unit.sleep(delay);
		unit.sleep(delay);
		printCountriesSlow(pot1, unit, delay);
		printCountriesSlow(pot2, unit, delay);
		printCountriesSlow(pot3, unit, delay);
		printCountriesSlow(pot4, unit, delay);
		System.out.println();
	}
	private void printCountriesSlow(ArrayList<Country> list, TimeUnit unit, long delay) throws InterruptedException {
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

	public Group getA() {
		return this.a;
	}
	
	public Group getB() {
		return this.b;
	}
	
	public Group getC() {
		return this.c;
	}

	public ArrayList<Country> getTeams() {
		return teams;
	}
	
}
