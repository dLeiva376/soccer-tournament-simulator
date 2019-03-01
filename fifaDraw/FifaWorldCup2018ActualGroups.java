package fifaDraw;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FifaWorldCup2018ActualGroups {

	public static void main(String[] args) throws InterruptedException {

		FifaWorldCup2018Simulator cup = new FifaWorldCup2018Simulator();
		Drawer dr = new Drawer();

		// group B
		Group a = new Group("A");
		a.add(dr.russia);
		a.add(dr.saudiArabia);
		a.add(dr.egypt);
		a.add(dr.uruguay);
		dr.setA(a);

		// group B
		Group b = new Group("B");
		b.add(dr.portugal);
		b.add(dr.spain);
		b.add(dr.iran);
		b.add(dr.moroco);
		dr.setB(b);

		// group C
		Group c = new Group("C");
		c.add(dr.france);
		c.add(dr.australia);
		c.add(dr.peru);
		c.add(dr.denmark);
		dr.setC(c);
		
		// group D
		Group d = new Group("D");
		d.add(dr.argentina);
		d.add(dr.iceland);
		d.add(dr.croatia);
		d.add(dr.nigeria);
		dr.setD(d);

		// group E
		Group e = new Group("E");
		e.add(dr.brazil);
		e.add(dr.switzerland);
		e.add(dr.costaRica);
		e.add(dr.serbia);
		dr.setE(e);
		
		// group F
		Group f = new Group("F");
		f.add(dr.germany);
		f.add(dr.mexico);
		f.add(dr.sweden);
		f.add(dr.southKorea);
		dr.setF(f);
		
		// group G
		Group g = new Group("G");
		g.add(dr.belgium);
		g.add(dr.panama);
		g.add(dr.tunisia);
		g.add(dr.england);
		dr.setG(g);
		
		// group H
		Group h = new Group("H");
		h.add(dr.poland);
		h.add(dr.senegal);
		h.add(dr.colombia);
		h.add(dr.japan);
		dr.setH(h);

		// m has a reference to the valid group
		System.out.println("2018 FIFA World Cup actual\n");
		TimeUnit.SECONDS.sleep(1);

		// sets the pots to list countries in the correct group
		ArrayList<Country> pot1 = new ArrayList<>();
		ArrayList<Country> pot2 = new ArrayList<>();
		ArrayList<Country> pot3 = new ArrayList<>();
		ArrayList<Country> pot4 = new ArrayList<>();
		pot1.add(dr.russia);
		pot1.add(dr.portugal);
		pot1.add(dr.france);
		pot1.add(dr.argentina);
		pot1.add(dr.brazil);
		pot1.add(dr.germany);
		pot1.add(dr.belgium);
		pot1.add(dr.poland);
		pot2.add(dr.saudiArabia);
		pot2.add(dr.spain);
		pot2.add(dr.australia);
		pot2.add(dr.iceland);
		pot2.add(dr.switzerland);
		pot2.add(dr.mexico);
		pot2.add(dr.panama);
		pot2.add(dr.senegal);
		pot3.add(dr.egypt);
		pot3.add(dr.moroco);
		pot3.add(dr.peru);
		pot3.add(dr.croatia);
		pot3.add(dr.costaRica);
		pot3.add(dr.sweden);
		pot3.add(dr.tunisia);
		pot3.add(dr.colombia);
		pot4.add(dr.uruguay);
		pot4.add(dr.iran);
		pot4.add(dr.denmark);
		pot4.add(dr.nigeria);
		pot4.add(dr.serbia);
		pot4.add(dr.southKorea);
		pot4.add(dr.england);
		pot4.add(dr.japan);
		
		dr.setPot1(pot1);
		dr.setPot2(pot2);
		dr.setPot3(pot3);
		dr.setPot4(pot4);
		
		// prints the groups, in delayed printing
		cup.setValidDrawer(dr);
		cup.getValidDrawer().printAllGroupsSlow(TimeUnit.MILLISECONDS, 250);

		// simulates the games
		// calculates the points and sets winners and runner ups
		// print the final results. ie: Russia 0 - 2 Peru
		System.out.println();
		cup.simulateGames();

		// prints each group. Country - points earned
		for (Country co : cup.getValidDrawer().getQualifiedTeams()) {
			co.calcPoints();
		}
		cup.printFinalGroupTables();

		// print teams that advance to round of 16 (Group Winners and
		// RunnerUppers)
		cup.printGroupWR();

		// ***** ROUND OF 16 *****
		System.out.println("\n  ROUND OF 16 MATCHES");
		cup.simulateRoundOf16();

		// ***** QUARTERFINAL *****
		System.out.println("\n  QUARTERFINALS");
		cup.simulateQuarterfinals();

		// ***** SEMIFINAL *****
		System.out.println("\n  SEMIFINALS");
		cup.simulateSemifinals();

		// ***** FINAL *****
		System.out.println("\n  FINALS");
		cup.simulateFinal();

	}
}
