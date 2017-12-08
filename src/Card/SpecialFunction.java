package Card;

import Logic.Logic;
import Runner.Controller;

import java.util.ArrayList;

public class SpecialFunction {
	
	public int specval;
	
	private ArrayList<Card>nextPlayer = new ArrayList<Card>();
	public static final int WILD_ARTIFICIAL_CARD_SPECVAL = -1;
	public static final int WILD_DRAW4_ARTIFICIAL_CARD_SPECVAL = -2;

	/** Method that implements all the special card functionality
	 * @param cardPlayed
	 * @param deck instance of Deck
	 * @param logic instance of Logic
	 * @param controller instance of Controller
	 */
	public void SpecialFunc(Card cardPlayed, Deck deck, Logic logic, Controller controller) {
		if (cardPlayed.getNumber().equals("Draw 2")) {
			playerCheck(logic,controller);
			deck.draw(2, nextPlayer);
			logic.turnAdjuster(2,controller,logic);
		} else if (cardPlayed.getNumber().equals("Reverse")) {
			logic.turnAdjuster(-1,controller,logic);
			logic.setClockwise(!logic.isClockwise());
		} else if (cardPlayed.getNumber().equals("Skip")) {
			logic.turnAdjuster(2,controller,logic);
		} else if (cardPlayed.getNumber().equals("Wild") || cardPlayed.getSpecval() == WILD_ARTIFICIAL_CARD_SPECVAL) {
			if (logic.getPlayerTurn() == 1) {
				WildCard(controller);
			}
			logic.turnAdjuster(1,controller,logic);
		} else if (cardPlayed.getNumber().equals("Wild Draw 4") || cardPlayed.getSpecval() == WILD_DRAW4_ARTIFICIAL_CARD_SPECVAL) {
			if (logic.getPlayerTurn() == 1) {
				WildCard(controller);
			}
			playerCheck(logic, controller);
			deck.draw(4, nextPlayer);
			logic.turnAdjuster(2,controller,logic);
		}
		else {
			logic.turnAdjuster(1,controller,logic);
		}
	}
	
	/**Method for Wild cards that takes input to choose Top card color
	 * @param controller
	 */
	public void WildCard(Controller controller) {
		controller.runWildGUI();
		String color = controller.getColorWild();
		if (color == "Red") {
			SpecialCard scard = new SpecialCard(0, -1);
			controller.setCardPlayed(scard);
		} else if (color == "Blue") {		
			SpecialCard scard = new SpecialCard(1, -1);
			controller.setCardPlayed(scard);
		} else if (color == "Yellow") {		
			SpecialCard scard = new SpecialCard(2, -1);
			controller.setCardPlayed(scard);
		} else if (color == "Green") {		
			SpecialCard scard = new SpecialCard(3, -1);
			controller.setCardPlayed(scard);
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