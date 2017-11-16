import java.util.Scanner;

public class SpecialFunction  extends SpecialCard {

	private String special;
	public int specval;
	
	SpecialCard sc = new SpecialCard(colval, specval); 
	
	@Override
	public String getNumber() {
		return special;
	}

	public SpecialFunction(int col, int spec) {
		super.colval = col;
		super.specval = spec;
		// functionality for Draw 2 card **** unsure what to put for player in line 21
		if (specval == 0) {
			Deck deck;
			deck.draw(1, player);
		// functionality for Reverse card	
		} else if (specval == 1) {
			Logic logic;
			boolean turn = logic.isClockwise();
			if (turn = true) {
				logic.setClockwise(false);
			} else {
				logic.setClockwise(true);
			}
		// functionality for Skip card 	
		} else if (specval == 2) {
			Logic logic;
			boolean turn = logic.isClockwise();
			if (turn = true) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 2);
			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 2);
			}
		}
	}
	public SpecialFunction(int spec) {
		super.specval = spec;
		// functionality for Wild card
		if (specval == 4) {
			WildCard(sc);
			SpecialCard sp = new SpecialCard();
			sp.setNumber("");
		// functionality for Wild Draw 4 *** unsure what to put in line 57 for player card
		} else if (specval == 5) {
			WildCard(sc);
			SpecialCard sp = new SpecialCard();
			sp.setNumber("");
			Deck deck;
			deck.draw(4, player);
		}
	}
	
	public void WildCard(Card cardPlayed) {
		System.out.println("Choose a color: [1] RED, [2] BLUE, [3] GREEN, [4] YELLOW");
		Scanner keyboard = new Scanner(System.in);
		int next = keyboard.nextInt();

		switch (next) {
			case 1: cardPlayed.setColor("Red");
				break;
			case 2: cardPlayed.setColor("Blue");
				break;
			case 3: cardPlayed.setColor("Green");
				break;
			case 4: cardPlayed.setColor("Yellow");
				break;
		}
	}
	
	
	
	
	
	
	
}
