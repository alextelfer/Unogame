import java.util.Scanner;
import java.util.ArrayList;

public class SpecialFunction extends Card {

	private String special;
	public int specval;
	private ArrayList<Card>WildList = new ArrayList<Card>();
	



	private ArrayList<Card>nextPlayer = new ArrayList<Card>();
	
	private boolean isSpecial;
	

	
	@Override
	public String getNumber() {
		return special;
	}

	public boolean isSpecial(Card cardPlayed) {
		isSpecial = false;
		if (cardPlayed instanceof SpecialCard) {
			isSpecial = true;
			return isSpecial;

		} else {
			return isSpecial;
		}
	}
	
	public void SpecialFunc(Card cardPlayed, Deck deck, Logic logic, Controller controller) {
		boolean turn = logic.isClockwise();
		// functionality for Draw 2 card **** 
		if (cardPlayed.getNumber().equals("Draw 2")) {
			System.out.println(nextPlayer.size());
			playerCheck(logic,controller);
			deck.draw(2, nextPlayer);
			System.out.println(nextPlayer.size());
			if (turn = true) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 2);
			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 2);
	
			}
				
			
		// functionality for Reverse card	
		} else if (cardPlayed.getNumber().equals("Reverse")) {
			if (turn = true) {
				logic.setPlayerTurn(logic.getPlayerTurn()-1);
			} else {
				logic.setPlayerTurn(logic.getPlayerTurn() + 1);
			}
			logic.setClockwise(!logic.isClockwise());
			
		// functionality for Skip card 	
		} else if (cardPlayed.getNumber().equals("Skip")) {
			if (turn = true) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 2);

			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 2);
			}
			// functionality for Wild Card
		} else if (cardPlayed.getNumber().equals("Wild")) {
			if (logic.getPlayerTurn() == 1 || logic.getPlayerTurn() == 2) {
				WildCard(controller);
			}
			if (turn = true) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 1);

			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 1);
			}
		// functionality for Wild Draw 4 *** 
		} else if (cardPlayed.getNumber().equals("Wild Draw 4")) {
			if (logic.getPlayerTurn() == 1 || logic.getPlayerTurn() == 2) {
				WildCard(controller);
			}
			playerCheck(logic, controller);
			deck.draw(4, nextPlayer);
			if (turn = true) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 2);
			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 2);
			}
		}
		else {
			if (turn) {
				logic.setPlayerTurn(logic.getPlayerTurn()+1);
			}
			else {
				logic.setPlayerTurn(logic.getPlayerTurn()-1);
			}
		}
	}
	
	public void WildCard(Controller controller) {
		System.out.println("Choose a color: [1] RED, [2] BLUE, [3] YELLOW, [4] GREEN");
		Scanner keyboard = new Scanner(System.in);
		int next = keyboard.nextInt();
		if (next == 1) {
			Card scard = new SpecialCard("Red ", -1);
			System.out.println("wwwww");
			System.out.println(scard);
			controller.setCardPlayed(scard);
		} else if (next ==2 ) {		
			Card scard = new SpecialCard("Blue ", -1);
			controller.setCardPlayed(scard);
			System.out.println(scard);
			System.out.println("AAAAAAAAA");
		} else if (next == 3) {		
			Card scard = new SpecialCard("Yellow ", -1);
			System.out.println("wwwww");
			System.out.println(scard);
			controller.setCardPlayed(scard);
		} else if (next ==4) {		
			Card scard = new SpecialCard("Green ", -1);
			System.out.println("wwwww");
			System.out.println(scard);
			controller.setCardPlayed(scard);
		}
	}
	
	public ArrayList<Card> playerCheck(Logic logic, Controller controller) {
		//PRIVACY LEAK: EDITING THROUGH GETTER; NEEDED BECAUSE CHANGSE TO NEXTPLAYER NEED TO HAPPEN TO 
		//THE PLAYER HAND LISTS
		//REPLACE RETURN WITH SET()
		boolean turn = logic.isClockwise();
		if (turn = true) {
			int thisPlayer = logic.getPlayerTurn();
			if (thisPlayer == 1) {
				nextPlayer = controller.getPlayer2().getHand();
				return nextPlayer;
			} else if (thisPlayer == 2) {
				nextPlayer = controller.getPlayer3().getHand();
				return nextPlayer;
			} else if (thisPlayer == 3) {
				nextPlayer = controller.getPlayer4().getHand();
				return nextPlayer;
			} else if (thisPlayer == 4) {
				nextPlayer = controller.getPlayer1().getHand();
				return nextPlayer;
			}
		} else {
			int thisPlayer = logic.getPlayerTurn();
			if (thisPlayer == 1) {
				nextPlayer = controller.getPlayer4().getHand();
				return nextPlayer;
			} else if (thisPlayer == 2) {
				nextPlayer = controller.getPlayer1().getHand();
				return nextPlayer;
			} else if (thisPlayer == 3) {
				nextPlayer = controller.getPlayer2().getHand();
				return nextPlayer;
			} else if (thisPlayer == 4) {
				nextPlayer = controller.getPlayer3().getHand();
				return nextPlayer;
			}
		}
		return nextPlayer;
	}
	
	
}
