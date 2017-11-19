import java.util.ArrayList;
public class Controller {
	private PlayerHuman player1 = new PlayerHuman();
	private PlayerHuman player2 = new PlayerHuman();
	private PlayerAI player3 = new PlayerAI();
	private PlayerAI player4 = new PlayerAI();

	private ArrayList <Card> topCard = new ArrayList <Card>();
	private ArrayList <Card> cardPlayed = new ArrayList <Card>();

	private Logic logic = new Logic();
	private Deck deck = new Deck();
	private DisplayCards display = new DisplayCards();
	private boolean valid;
	
	private SpecialCard sc;
	private SpecialFunction sf = new SpecialFunction();

	public ArrayList <Card> getTopCardAsList(){
		return topCard;
	}
	
	public ArrayList<Card> getCardPlayed() {
		return cardPlayed;
	}
	
	public int getPlayerTurn() {
		return logic.getPlayerTurn();
	}
	public PlayerHuman getPlayer1() {
		return player1;
	}

	public PlayerHuman getPlayer2() {
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
		deck.draw(1, topCard);
	}

	public void play() {

		boolean gameComplete = false;
		while (!gameComplete){
			if (getPlayerTurn() == 1) {
				valid = false;
					System.out.println("Player 1's turn");
					int cardIndex = display.displayDeck(topCard, player1.getHand());
					if(cardIndex > 0) {
						cardPlayed.add(player1.getHand().get(cardIndex-1));					
						if (valid = logic.isValid(topCard.get(0),cardPlayed.get(0))) {							
							sf.SpecialFunc(cardPlayed.get(0), deck, logic, this);
							topCard.clear();
							topCard.add(cardPlayed.get(0));		
							player1.getHand().remove(cardIndex-1);
							cardPlayed.clear();
							logic.gameState();
							logic.numOfCards(player1, player2, player3, player4);
						}
					}
					else if (cardIndex == 0) {
						deck.draw(1,getPlayer1().getHand());
					}
			}
			else if (getPlayerTurn() == 2) {
				valid = false;
				System.out.println("Player 2's turn");
				int cardIndex = display.displayDeck(topCard, player1.getHand());
				if(cardIndex > 0) {
					cardPlayed.add(player1.getHand().get(cardIndex-1));					
					if (valid = logic.isValid(topCard.get(0),cardPlayed.get(0))) {							
						sf.SpecialFunc(cardPlayed.get(0), deck, logic, this);
						topCard.clear();
						topCard.add(cardPlayed.get(0));		
						player1.getHand().remove(cardIndex-1);
						cardPlayed.clear();
						logic.gameState();
						logic.numOfCards(player1, player2, player3, player4);
					}
				}
				else if (cardIndex == 0) {
					deck.draw(1,getPlayer2().getHand());
				}
			}
			else if (getPlayerTurn() == 3) {
				System.out.println("Player 3's turn");
				Card aCard = player3.cardAI(deck, logic, topCard.get(0));
				sf.SpecialFunc(aCard, deck, logic, this);
				topCard.clear();
				topCard.add(aCard);		
				player3.removeCard(aCard);
				cardPlayed.clear();
				logic.gameState();
				logic.numOfCards(player1, player2, player3, player4);
			}
			else if (getPlayerTurn() == 4) {
				System.out.println("Player 4's turn");
				Card aCard = player4.cardAI(deck, logic, topCard.get(0));
				sf.SpecialFunc(aCard, deck, logic, this);
				topCard.clear();
				topCard.add(aCard);		
				player4.removeCard(aCard);
				cardPlayed.clear();
				logic.gameState();
				logic.numOfCards(player1, player2, player3, player4);
			} else {
				System.out.println("NNNNNNNNNNNNNN");
			}
		}
	}
}