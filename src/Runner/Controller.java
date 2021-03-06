package Runner;
import java.util.ArrayList;

import Card.Card;
import Card.Deck;
import Card.SpecialFunction;
import GUI.UnoActionPerformer;
import GUI.UnoGUI;
import Logic.Logic;
import Player.PlayerAI;
import Player.PlayerHuman;

/**
 * Class that handles communication between GUI and logic classes. 
 * Class also handles the general turn structure for the game, and contains instances of player classes, the topCard and cardPlayed.
 */

public class Controller {
	
	/**
	 * INTENTIONAL PRIVACY LEAKS: All references relating to hand ArrayLists and card objects within hand ArrayLists must be shared. Various classes edit/create
	 * artificial card objects and remove/add new card objects so they must be provided with the instance itself.
	 */
	
	/**
	 * Instantiation of all the 4 players in the game; 1 Human Player, 3 AI players
	 */
	private PlayerHuman player1 = new PlayerHuman();
	private PlayerAI player2 = new PlayerAI();
	private PlayerAI player3 = new PlayerAI();
	private PlayerAI player4 = new PlayerAI();
	/**
	 * Instantiation of topCard list and the CardPlayed list that is used
	 * as a medium for checking valid play
	 */
	private ArrayList <Card> topCard = new ArrayList <Card>();
	private ArrayList <Card> cardPlayed = new ArrayList <Card>();
	
	/**
	 * Instantiation of all the classes required to make the game run. These include the logic, deck, 
	 * display cards and special function of cards classes
	 */
	private Logic logic = new Logic();
	private Deck deck = new Deck();
	private SpecialFunction specialFunction = new SpecialFunction();
	private UnoActionPerformer actions;
	
	/**
	 * Instance variable used to pass the color selected by the player in runWildGUI to the player classes
	 */
	private String colorWild;

	/**Called by SpecialFunction.WildCard() 
	 * method that communicates between UnoActionPerformer and SpecialFunction to pass the color selected by the player to the logic classes
	 * @return colorWild
	 */
	public String getColorWild() {
		return colorWild;
	}
	
	/** called by UnoActionPerformer after player selects a color for the wild card
	 * method that communicates between UnoActionPerformer and SpecialFunction to pass the color selected by the player to the logic classes
	 * @param colorWild
	 */
	public void setColorWild(String colorWild) {
		this.colorWild = colorWild;
		
	}
	
	/**A getter method for the arrayList that contains the topcard
	 * @return the arrayList <Card> that contains the topCard object for the game
	 * INTENTIONAL PRIVACY LEAK: creates a new list but references to individual cards must be shared
	 */
	public ArrayList<Card> getTopCard(){
		ArrayList <Card> topcardCopy = new ArrayList<Card>();
		for(Card c : topCard) {
			topcardCopy.add(c);
		}
		return topcardCopy;
	}
	/**Setter for topCard list to take care of privacy leaks
	 * @param aTopCard object of type Card to set the new topcard in the game
	 */
	public void setTopCard(Card aTopCard) {
		topCard.clear();
		topCard.add(aTopCard);
	}

	
	/**Getter of type arrayList<Card> to get the card played by the user
	 * @return the arrayList <Card> of the cardPLayed by the user 
	 * INTENTIONAL PRIVACY LEAK: creates a new list but references to individual cards must be shared
	 */
	public ArrayList<Card> getCardPlayed() {
		ArrayList <Card> cardPlayedCopy = new ArrayList<Card>();
		for(Card c : cardPlayed) {
			cardPlayedCopy.add(c);
		}
		return cardPlayedCopy;
	}
	
	/**Setter for the cardPLayed of type arrayList <Card> 
	 * @param aCardPlayed the arrayList <Card> played by the user 
	 */
	public void setCardPlayed(Card aCardPlayed) {
		cardPlayed.clear();
		cardPlayed.add(aCardPlayed);
	}
	
	public void clearCardPlayed() {
		cardPlayed.clear();
	}

	/**Getter for the player turn of type integer that helps determine the player's turn
	 * @return an integer from logic class that determines the player's turn
	 */
	public int getPlayerTurn() {
		return logic.getPlayerTurn();
	}

	/**Getter for the class of player1 (Human PLayer) to have access to their hand and the methods in the class
	 * @return player1 of type PlayerHuman
	 */
	public PlayerHuman getPlayer1() {
		return new PlayerHuman(player1);
	}

	/**Getter for the class of player2 (Computer) to have access to their hand and the methods in the class
	 * @return player2 of type PlayerAI
	 */
	public PlayerAI getPlayer2() {
		return new PlayerAI(player2);
	}

	/**Getter for the class of player3 (Computer) to have access to their hand and the methods in the class
	 * @return player3 of type PlayerAI
	 */
	public PlayerAI getPlayer3() {
		return new PlayerAI(player3);
	}

	/**Getter for the class of player4 (Computer) to have access to their hand and the methods in the class
	 * @return player4 of type PlayerAI
	 */
	public PlayerAI getPlayer4() {
		return new PlayerAI(player4);
	}

	/**
	 * A method that deals cards to all the players in the game and has a topcard created on the discard pile 
	 * in order to start the game.
	 */
	public void initializeCards() {
		deck.topDraw(topCard);
		player1.initialize(deck);
		player2.initialize(deck);
		player3.initialize(deck);
		player4.initialize(deck);
		actions = new UnoActionPerformer(this);

	}

	/** Called by UnoActionPerformer.actionPerformed() (Called after every button press)
	 * the play method is what controls the state of the game and the order of turns between AI players and human players
	 */
	public void play(int cardIndex) {
		while (true) {
			if (logic.getPlayerTurn() == 1) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 1's Turn" + "\n");
				player1.cardAction(cardIndex, logic, specialFunction, this, deck);
			}
			if (logic.getPlayerTurn() == 2) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 2's Turn" + "\n");
				player2.cardAction(logic, specialFunction, this, deck);
			}
			if (logic.getPlayerTurn() == 3) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 3's Turn" + "\n");
				player3.cardAction(logic, specialFunction, this, deck);
			}
			if (logic.getPlayerTurn() == 4) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 4's Turn" + "\n");
				player4.cardAction(logic, specialFunction, this, deck);
			}
			if (logic.getPlayerTurn() == 1){
				break;
			}
		}
	}
	
	/** Called by SpecialFunction.specialFunc() whenever a wild card is played
	 * method calls gui.wildCardButtons() which creates the window to select a color after a wild card is played
	 */
	public void runWildGUI() {
		UnoGUI gui = actions.getGUI();
		gui.wildCardButtons(actions);
	}
}