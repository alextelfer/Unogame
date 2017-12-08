package Card;

import Logic.Logic;
import Runner.Controller;

import java.util.ArrayList;

public class SpecialFunction {
	
	public int specval;
	
	private ArrayList<Card>nextPlayer = new ArrayList<Card>();

	/** Method that implements all the special card functionality
	 * @param cardPlayed
	 * @param deck
	 * @param logic
	 * @param controller
	 */
	public void SpecialFunc(Card cardPlayed, Deck deck, Logic logic, Controller controller) {
		/** Draw 2 card functionality */
		if (cardPlayed.getNumber().equals("Draw 2")) {
			playerCheck(logic,controller);
			deck.draw(2, nextPlayer);
			turnAdjuster(2,controller,logic);
		/** Reverse card functionality */
		} else if (cardPlayed.getNumber().equals("Reverse")) {
			turnAdjuster(1,controller,logic);
			logic.setClockwise(!logic.isClockwise());
		/** Skip card functionality */
		} else if (cardPlayed.getNumber().equals("Skip")) {
			turnAdjuster(2,controller,logic);
		/** Wild card functionality */
		} else if (cardPlayed.getNumber().equals("Wild") || cardPlayed.getSpecval() == -1) { //-1 specval: variable to determine wildcards for AI
			if (logic.getPlayerTurn() == 1) {
				WildCard(controller);
			}
			turnAdjuster(1,controller,logic);
		/** Wild Draw 4 functionality */
		} else if (cardPlayed.getNumber().equals("Wild Draw 4") || cardPlayed.getSpecval() == -2) {//-2 specval: variable to determine wild draw 4 for AI
			if (logic.getPlayerTurn() == 1) {
				WildCard(controller);
			}
			playerCheck(logic, controller);
			deck.draw(4, nextPlayer);
			turnAdjuster(2,controller,logic);
		}
		else {
			turnAdjuster(1,controller,logic);
		}
	}
	
	/**Method for Wild cards that takes input to choose Top card color
	 * @param controller
	 */
	public void WildCard(Controller controller) {
		controller.runWildGUI();
		String color = controller.getColorWild();
		if (color == "RED") {
			SpecialCard scard = new SpecialCard(0, -1);
			controller.setCardPlayed(scard);
		} else if (color == "BLUE") {		
			SpecialCard scard = new SpecialCard(1, -1);
			controller.setCardPlayed(scard);
		} else if (color == "YELLOW") {		
			SpecialCard scard = new SpecialCard(2, -1);
			controller.setCardPlayed(scard);
		} else if (color == "GREEN") {		
			SpecialCard scard = new SpecialCard(3, -1);
			controller.setCardPlayed(scard);
		}
	}
	
	/**Called by SpecialFunc after a successful card is played
	 * Method is used to increment player turns after every successful card is played. Skips, Draw 4s and Draw 2s set the adjustment to 2 (skipping a players turn)
	 * @param int adjustment(1 for regular cards,wilds and reverses. 2 for skips, draw 4s and draw 2s)
	 * @param controller instance of Controller
	 * @param logic instance of Logic
	 */
	public void turnAdjuster(int adjustment, Controller controller,Logic logic) {
		boolean turn = logic.isClockwise();
		int playerTurn = logic.getPlayerTurn();
		if (turn) {
			logic.setPlayerTurn(playerTurn + adjustment);
		} else {
			logic.setPlayerTurn(playerTurn - adjustment);
		}
	}
	
	/** Method that checks who the next player is for Draw 2 cards and Wild Draw 4 cards.
	 * @param logic
	 * @param controller
	 * @return ArrayList<Card> of the nextPlayer
	 */
	public ArrayList<Card> playerCheck(Logic logic, Controller controller) {
		/**PRIVACY LEAK: EDITING THROUGH GETTER; NEEDED BECAUSE CHANGSE TO NEXTPLAYER NEED TO HAPPEN TO 
		 *THE PLAYER HAND LISTS
		REPLACE RETURN WITH SET() */
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