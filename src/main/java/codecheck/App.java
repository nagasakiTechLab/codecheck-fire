package codecheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {
		int cardCount = Integer.parseInt(args[0]);
		int mp = Integer.parseInt(args[1]);

		List<Card> cards = new ArrayList<Card>();
		for (int i = 2, l = args.length; i < l; i += 2) {
			int attack = Integer.parseInt(args[i]);
			int cost = Integer.parseInt(args[i + 1]);
			cards.add(new Card(attack, cost));
		}
		cards.sort((a, b) -> b.apc.subtract(a.apc).intValue());

		int total = 0;
		for (Card card: cards) {
			int count = mp / card.cost;
			if (count > 0) {
				int sammonCount = Math.min(count, cardCount);
				cardCount -= sammonCount;
				mp -= card.cost * sammonCount;
				total += card.attack * sammonCount;
			}
		}
		System.out.println(total);
	}

	static class Card {
		private int attack;
		private int cost;
		private BigDecimal apc;

		Card(int attack, int cost) {
			this.attack = attack;
			this.cost = cost;
			this.apc = BigDecimal.valueOf(attack).divide(BigDecimal.valueOf(cost));
		}
	}
}

