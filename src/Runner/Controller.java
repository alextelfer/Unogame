package Runner;
import java.util.ArrayList;

import Card.Card;
import Card.Deck;
import Card.DisplayCards;
import Card.SpecialFunction;
import Logic.Logic;
import Player.PlayerAI;
import Player.PlayerHuman;


public class Controller {
	/**
	 * Instantiation of all the 4 players in the game; 1 Human Player, 3 AI players
	 */
	private PlayerHuman player1 = new PlayerHuman();
	private PlayerAI player2 = new PlayerAI();
	private PlayerAI player3 = new PlayerAI();
	private PlayerAI player4 = new PlayerAI();
	private int winner;
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
	private DisplayCards display = new DisplayCards();
	private SpecialFunction sf = new SpecialFunction();

	
	/**A getter method for the arrayList that contains the topcard
	 * @return the arrayList <Card> that contains the topCard object for the game
	 */
	public ArrayList<Card> getTopCard(){
		ArrayList <Card> topcard = new ArrayList<Card>();
		for(Card c : topCard) {
			topcard.add(c);
		}
		return topcard;
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
	 */
	public ArrayList<Card> getCardPlayed() {
		ArrayList <Card> playedCard = cardPlayed;
		return playedCard;
	}
	
	/**Setter for the cardPLayed of type arrayList <Card> 
	 * @param aCardPlayed the arrayList <Card> played by the user 
	 */
	public void setCardPlayed(Card aCardPlayed) {
		cardPlayed.clear();
		cardPlayed.add(aCardPlayed);
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
	}
	/**
	 * the play method is what controls the state of the game, keeps track of which player's turn it is
	 * and the player that wins the game
	 */
	public void play() {
		boolean gameComplete = false;
		while (!gameComplete){
			if (logic.getPlayerTurn() == 1) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 1's Turn" + "\n");
				boolean valid = false;
				while (!valid){
					int cardIndex = display.displayDeck(topCard, player1.getHand());
					if (cardIndex > 0 ) {
						cardPlayed.clear();
						cardPlayed.add(player1.getHand().get(cardIndex-1));					
						if (valid = logic.isValid(topCard.get(0),cardPlayed.get(0))) {							
							sf.SpecialFunc(cardPlayed.get(0), deck, logic, this);
							System.out.println(cardPlayed.get(0));
							topCard.clear();
							topCard.add(cardPlayed.get(0));		
							player1.getHand().remove(cardIndex-1);
							cardPlayed.clear();
							logic.gameState();
							logic.numOfCards(player1, player2, player3, player4);
						}	
					}else if (cardIndex == 0) {
						deck.draw(1, getPlayer1().getHand());
					}
				}
				logic.lastCard(player1.getHand());
				if (player1.getHand().size() == 0) {
					winner = 1;
					gameComplete = true;
				}
			}
			else if (logic.getPlayerTurn() == 2) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 2's Turn" + "\n");
				Card aCard = player2.cardAI(deck, logic, topCard.get(0));
				sf.SpecialFunc(aCard, deck, logic, this);
				topCard.clear();
				topCard.add(aCard);		
				cardPlayed.clear();
				logic.gameState();
				logic.numOfCards(player1, player2, player3, player4);
				if (player2.getHand().size() == 0) {
					winner = 2;
					gameComplete = true;
				}
			}
			else if (logic.getPlayerTurn() == 3) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 3's Turn" + "\n");
				Card aCard = player3.cardAI(deck, logic, topCard.get(0));
				sf.SpecialFunc(aCard, deck, logic, this);
				topCard.clear();
				topCard.add(aCard);		
				cardPlayed.clear();
				logic.gameState();
				logic.numOfCards(player1, player2, player3, player4);
				if (player3.getHand().size() == 0) {
					winner = 3;
					gameComplete = true;
				}
			}
			else if (logic.getPlayerTurn() == 4) {
				System.out.println("\n\n\n\n\n\n\n" + "It is now Player 4's Turn" + "\n");
				Card aCard = player4.cardAI(deck, logic, topCard.get(0));
				sf.SpecialFunc(aCard, deck, logic, this);
				topCard.clear();
				topCard.add(aCard);		
				cardPlayed.clear();
				logic.gameState();
				logic.numOfCards(player1, player2, player3, player4);
				if (player4.getHand().size() == 0) {
					winner = 4;
					gameComplete = true;
				}
			} else {
				System.out.println("How tho?");
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
