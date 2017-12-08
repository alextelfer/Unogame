package Player;
import java.util.ArrayList;
import java.util.Date;

import Card.Card;
import Card.Deck;
import Card.SpecialCard;
import Card.SpecialFunction;
import Logic.Logic;
import Runner.Controller;

/**
 * Player class that handles all AI related methods, AI decision making and editing game variables corresponding to AI turns
 * Each player class has an instance variable of a hand of cards
 */

public class PlayerAI {
	private ArrayList <Card> hand = new ArrayList <Card>();
	
	private static final int DEFAULT_AI_HAND_SIZE = 7;
	
	/*
	 * INTENTIONAL PRIVACY LEAKS: All references relating to hand ArrayLists and card objects within hand ArrayLists must be shared. Various classes edit/create
	 * artificial card objects and remove/add new card objects so they must be provided with the instance itself.
	 */
	
	/**
	 * Default constructor for the PlayerAI Class
	 */
	public PlayerAI() {
	}
	
	/**
	 * setter for hand, used in PlayerAITest to create an artificial hand
	 * @param aHand
	 */
	public void setHand(ArrayList<Card> aHand) {
		this.hand = aHand;
	}
	
	/**
	 * Copy Constructor for PlayerAI 
	 * @param toCopy of type PlayerAI to stop privacy leak
	 * INTENTIONAL PRIVACY LEAK: Hand references must be shared 
	 */
	public PlayerAI(PlayerAI toCopy) {
		this.hand = toCopy.hand;
	}	
	
	/**
	 * Getter for the arrayList that contains the cards in a player hand
	 * @return a reference to the playerHand
	 * INTENTIONAL PRIVACY LEAK: Hand references must be shared
	 */
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	/**
	 * Used to draw cards to the player's hand at the start of the game
	 * @param deck of type Deck
	 */
	public void initialize(Deck deck) {
		deck.draw(DEFAULT_AI_HAND_SIZE,hand);
	}
	
	/**
	 * Used to remove a Card that was played from the player's hand
	 * @param playedCard of type Card
	 */
	public void removeCard(Card playedCard) {
		this.hand.remove(hand.indexOf(playedCard));
	}
	
	/** Called by PlayerAI.cardAction()
	 * AI method: determines what card the AI will play based on the conditions of it's hand. 
	 * Current AI priority: From left to right: SpecialCards > NumberCards
	 * AI Functionality: WildCard: Changes the color to the most common color in it's own hand
	 * @param takes an instance of the Deck, Logic, and a Card topCard
	 * @return returns the Card that the AI plays
	 */
	public Card cardAI(Deck deck,Logic logic, Card topCard) {
		ArrayList <Card> validChoices = new ArrayList <Card>();
		ArrayList <Card> specialChoices = new ArrayList <Card>();
		Card cardPlayed;
		//Adds valid cards in hand to validChoices list
		while (validChoices.size() == 0) {
			for (int index1 = 0;index1<hand.size();index1++) {
				if (logic.isValid(topCard,hand.get(index1))) {
					validChoices.add(hand.get(index1));
				}
			}
			if (validChoices.size() == 0) {
				deck.draw(1,this.hand);
			}
		}
		//Adds special cards in validChoices to specialChoices list
		for (int index2 = 0 ; index2 < validChoices.size() ; index2++) {
			if (validChoices.get(index2).getNumber().equals("Skip") || validChoices.get(index2).getNumber().equals("Reverse") || validChoices.get(index2).getNumber().equals("Draw 2") || validChoices.get(index2).getNumber().equals("Wild") || validChoices.get(index2).getNumber().equals("Wild Draw 4")) {
				specialChoices.add(validChoices.get(index2));
			}
		}
		if (specialChoices.size() > 0) {
			cardPlayed = specialChoices.get(0);
		} else {
			cardPlayed = validChoices.get(0);
		}
		removeCard(cardPlayed);
		if (cardPlayed.getNumber().equals("Wild")){
			cardPlayed = new SpecialCard(handEvaluator(), SpecialFunction.WILD_ARTIFICIAL_CARD_SPECVAL);
		}
		if (cardPlayed.getNumber().equals("Wild Draw 4")){
			cardPlayed = new SpecialCard(handEvaluator(), SpecialFunction.WILD_DRAW4_ARTIFICIAL_CARD_SPECVAL);
		}
		return cardPlayed;
	}
	
	/** Called by cardAI
	 * Method that iterates through the player's hand and determines what color is the most common.
	 * Used to determine what color to set WildCards to when the AI decides to play one
	 * @return String color
	 */
	public int handEvaluator() {
		int numOfReds = 0;
		int numOfBlues = 0;
		int numOfGreens = 0;
		int numOfYellows = 0;
		int color = 0;
		for (Card aCard : hand) {
			if (aCard.getColor().equals("Red")){
				numOfReds += 1;
			}
			else if (aCard.getColor().equals("Blue")){
				numOfBlues += 1;
			}
			else if (aCard.getColor().equals("Green")){
				numOfGreens += 1;
			}
			else if (aCard.getColor().equals("Yellow")){
				numOfYellows += 1;
			}
		}
		int mostColor = numOfReds;
		if (numOfBlues > mostColor) {
			mostColor = numOfBlues;
			color = 1;
		}
		if (numOfGreens > mostColor) {
			mostColor = numOfGreens;
			color = 3;
		}
		if (numOfYellows > mostColor) {
			mostColor = numOfYellows;
			color = 2;
		}
		return color;
	}
	
	/** Called by controller.play();
	 * Method that handles AI moves and calls methods that adjust the state of the game.
	 * Calls specialFunction.specialFunc() to enact functionality for special cards. Updates cardPlayed and topCard when a correct card is played. Calls 
	 * logic.gameState() to update playerTurn. Also handles win condition.	 * @param logic instance of type Logic
	 * @param specialFunction instance of type SpecialFunction
	 * @param controller instance of type Controller
	 * @param deck instance of type Deck
	 */
	public void cardAction(Logic logic, SpecialFunction specialFunction, Controller controller, Deck deck ) {
		Card aCard = cardAI(deck, logic, controller.getTopCard().get(0));
		specialFunction.SpecialFunc(aCard, deck, logic, controller);
		controller.setTopCard(aCard);	
		controller.clearCardPlayed();
		logic.gameState();
		logic.numOfCards(controller.getPlayer1(), controller.getPlayer2(), controller.getPlayer3(), controller.getPlayer4());
	}
}