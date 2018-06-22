package codecheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {
		String[] inputs = args[0].split(" ");
//		String[] inputs = args;

		int cardCount = Integer.parseInt(inputs[0]);
		int mp = Integer.parseInt(inputs[1]);

		List<Card> cards = new ArrayList<Card>();
		for (int i = 2, l = inputs.length; i < l; i += 2) {
			int attack = Integer.parseInt(inputs[i]);
			int cost = Integer.parseInt(inputs[i + 1]);
			cards.add(new Card(attack, cost));
		}
		cards.sort((a, b) -> b.apc.subtract(a.apc).intValue());
//		System.out.println(cards.toString());

		int total = 0;
		for (Card card: cards) {
//			System.out.println("<loop>cardCount:" + cardCount + ", mp:" + mp);
			if (card.cost <= mp) {
//				System.out.println("<loop>cardCost:" + card.cost + ", card.attack:" + card.attack);
				mp -= card.cost;
				total += card.attack;
			}
		}
		System.out.println(total);

//		System.out.println("<final>cardCount:" + cardCount + ", mp:" + mp);
	}

	static class Card {
		private int attack;
		private int cost;
		private BigDecimal apc;

		Card(int attack, int cost) {
			this.attack = attack;
			this.cost = cost;
			this.apc = BigDecimal.valueOf(attack).divide(BigDecimal.valueOf(cost), 5, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100000));
		}

		public String toString() {
			return "attack:" + attack + " cost:" + cost + " apc:" + apc.toString();
		}
	}
}

