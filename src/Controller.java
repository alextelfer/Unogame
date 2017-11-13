import java.util.ArrayList;


public class Controller {
	private PlayerHuman player1 = new PlayerHuman();
	private PlayerAI player2 = new PlayerAI();
	private PlayerAI player3 = new PlayerAI();
	private PlayerAI player4 = new PlayerAI();
	private ArrayList<Card> topCard = new ArrayList<Card>();
	
	private Logic logic = new Logic();
	private DisplayCards displayCards = new DisplayCards();
	private Deck deck = new Deck();
	
	public PlayerHuman getPlayer1() {
		return player1;
	}

	public PlayerAI getPlayer2() {
		return player2;
	}

	public PlayerAI getPlayer3() {
		return player3;
	}

	public PlayerAI getPlayer4() {
		return player4;
	}
	
	public void initializeCards() {
		player1.initialize(deck);
		player2.initialize(deck);
		player3.initialize(deck);
		player4.initialize(deck);
		deck.draw(1,topCard);
	}
	
	public void play() {
		System.out.println(deck.cardsLeft());
		boolean gameComplete = false;
		while (!gameComplete) {
			if(logic.getPlayerTurn()==1) {
				displayCards.displayDeck(topCard, deck.getUnoDeck());
				
			}
		}
	}
}
