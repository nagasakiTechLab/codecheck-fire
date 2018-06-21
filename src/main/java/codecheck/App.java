package codecheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {
		String[] inputs = args[0].split(" ");

		int cardCount = Integer.parseInt(inputs[0]);
		int mp = Integer.parseInt(inputs[1]);

		List<Card> cards = new ArrayList<Card>();
		for (int i = 2, l = inputs.length; i < l; i += 2) {
			int attack = Integer.parseInt(inputs[i]);
			int cost = Integer.parseInt(inputs[i + 1]);
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

