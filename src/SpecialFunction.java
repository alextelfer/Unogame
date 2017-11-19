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
		// functionality for Draw 2 card **** 
		if (cardPlayed.getNumber().equals("Draw 2")) {
			System.out.println(nextPlayer.size());
			playerCheck(logic,controller);
			deck.draw(2, nextPlayer);
			System.out.println(nextPlayer.size());
			boolean turn = logic.isClockwise();
			if (turn) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 2);
			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 2);
	
			}
				
			
		// functionality for Reverse card	
		} else if (cardPlayed.getNumber().equals("Reverse")) {
			
			boolean turn = logic.isClockwise();
			if (turn) {
				logic.setClockwise(false);
				logic.setPlayerTurn(logic.getPlayerTurn()-1);
			} else {
				logic.setClockwise(true);
				logic.setPlayerTurn(logic.getPlayerTurn() + 1);
			}
			
		// functionality for Skip card 	
		} else if (cardPlayed.getNumber().equals("Skip")) {
		
			boolean turn = logic.isClockwise();
			if (turn) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 2);

			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 2);
	
			}
			// functionality for Wild Card
		} else if (cardPlayed.getNumber().equals("Wild")) {

			WildCard(controller);
			boolean turn = logic.isClockwise();
			if (turn) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 1);

			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 1);
	
			}
		// functionality for Wild Draw 4 *** 
		} else if (cardPlayed.getNumber().equals("Wild Draw 4")) {
	
			WildCard(controller);
			playerCheck(logic, controller);
			deck.draw(4, nextPlayer);
			boolean turn = logic.isClockwise();
			if (turn) {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn + 2);

			} else {
				int playerTurn = logic.getPlayerTurn();
				logic.setPlayerTurn(playerTurn - 2);
		
		}
		}
		else {
			logic.setPlayerTurn(logic.getPlayerTurn()+ 1);
		}
	}

	public void WildCard(Controller controller) {
		System.out.println("Choose a color: [1] RED, [2] BLUE, [3] YELLOW, [4] GREEN");
		Scanner keyboard = new Scanner(System.in);
		int next = keyboard.nextInt();
		if (next == 1) {
			SpecialCard scard = new SpecialCard(0, -1);
			System.out.println("wwwww");
			controller.getCardPlayed().clear();
			controller.getCardPlayed().add(scard);
		} else if (next ==2 ) {		
			SpecialCard scard = new SpecialCard(1, -1);
	
			controller.getCardPlayed().clear();
			controller.getCardPlayed().add(scard);
			System.out.println("AAAAAAAAA");
		} else if (next == 3) {		
			SpecialCard scard = new SpecialCard(2, -1);
			System.out.println("wwwww");
			controller.getCardPlayed().clear();
			controller.getCardPlayed().add(scard);
		} else if (next ==4) {		
			SpecialCard scard = new SpecialCard(3, -1);
			System.out.println("wwwww");
			controller.getCardPlayed().clear();
			controller.getCardPlayed().add(scard);
		}
	}
	
	public ArrayList<Card> playerCheck(Logic logic, Controller controller) {
		//PRIVACY LEAK: EDITING THROUGH GETTER; NEEDED BECAUSE CHANGSE TO NEXTPLAYER NEED TO HAPPEN TO 
		//THE PLAYER HAND LISTS
		boolean turn = logic.isClockwise();
		if (turn) {
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