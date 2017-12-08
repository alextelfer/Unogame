package Runner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import Card.Card;
import Card.Deck;
import Card.SpecialCard;
import Card.SpecialFunction;
import Display.DisplayCards;
import Logic.Logic;
import Player.PlayerAI;
import Player.PlayerHuman;

/**
 * Class that handles communication between GUI and logic classes. 
 * Class also handles the general turn structure for the game, and contains instances of player classes, the topCard and cardPlayed.
 */
public class Controller {
	
	/*
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
	private ArrayList <Card> discardPile = new ArrayList<Card>();
	
	/**
	 * Instantiation of all the classes required to make the game run. These include the logic, deck, 
	 * display cards and special function of cards classes
	 */
	private Logic logic = new Logic();
	private Deck deck = new Deck(this);
	private SpecialFunction specialFunction = new SpecialFunction();
	private DisplayCards display = new DisplayCards();

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
		addDiscardPile(aTopCard);
		topCard.clear();
		topCard.add(aTopCard);
	}
	
	/**Called by deck.draw() when the size of the deck is 0
	 * A getter method for the discardPile ArrayList
	 * @return the arrayList <Card> that contains the discardPile
	 */
	public ArrayList<Card> getDiscardPile(){
		return discardPile;
	}
	
	/**Called by setTopCard when a new topCard is set
	 * method that adds an old topCard to the discard pile
	 * @param Card topCard
	 */
	public void addDiscardPile(Card topCard){
		//recreates a new wild card from an artificial card
		if (topCard.getSpecval() == SpecialFunction.WILD_ARTIFICIAL_CARD_SPECVAL) {
			topCard = new SpecialCard(3);
		}
		//recreates a new wild draw 4 from an artificial card
		else if (topCard.getSpecval() == SpecialFunction.WILD_DRAW4_ARTIFICIAL_CARD_SPECVAL) {
			topCard = new SpecialCard(4);
		}
		discardPile.add(topCard);
	}
	
	/**Called by deck.draw() after the new deck is set to the old discardPile
	 * method clears the discard pile
	 */
	public void clearDiscardPile() {
		discardPile.clear();
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

	/**Getter for playerTurn
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
	}
	

	/**
	 * the play method is what controls the state of the game, keeps track of which player's turn it is
	 * and the player that wins the game
	 */
	public void play() {
		int winner = -1;
		boolean gameComplete = false;
		while (!gameComplete){
			if (logic.getPlayerTurn() == 1) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 1's Turn" + "\n");
				player1.cardAction(logic, specialFunction, this, deck, display);
				if (player1.getHand().size() == 0) {
					winner = 1;
					gameComplete = true;
				}
			}
			else if (logic.getPlayerTurn() == 2) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 2's Turn" + "\n");
				player2.cardAction(logic, specialFunction, this, deck);
				System.out.println("\nPlayer 2 has played a " + topCard.get(0));
				if (player2.getHand().size() == 0) {
					winner = 2;
					gameComplete = true;
				}
			}
			else if (logic.getPlayerTurn() == 3) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 3's Turn" + "\n");
				player2.cardAction(logic, specialFunction, this, deck);
				System.out.println("\nPlayer 3 has played a " + topCard.get(0));
				if (player3.getHand().size() == 0) {
					winner = 3;
					gameComplete = true;
				}
			}
			else if (logic.getPlayerTurn() == 4) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 4's Turn" + "\n");
				player2.cardAction(logic, specialFunction, this, deck);
				System.out.println("\nPlayer 4 has played a " + topCard.get(0));
				if (player4.getHand().size() == 0) {
					winner = 4;
					gameComplete = true;
				}
			} else {
				System.out.println("Turn Error: Player turn:" + logic.getPlayerTurn());
				try {
					Thread.sleep(5000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (winner > 0){
			System.out.println("Winner of the game is: player " + winner);
			System.exit(0);
		}
	}
}
