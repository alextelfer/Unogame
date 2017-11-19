import java.util.ArrayList;
public class Controller {
	private PlayerHuman player1 = new PlayerHuman();
	private PlayerHuman player2 = new PlayerHuman();
	private PlayerAI player3 = new PlayerAI();
	private PlayerAI player4 = new PlayerAI();
	
	public ArrayList<Card> getCardPlayed() {
		return cardPlayed;
	}
	
	public void setCardPlayed(Card aCardPlayed) {
		cardPlayed.clear();
		cardPlayed.add(aCardPlayed);
	}

	private ArrayList <Card> topCard = new ArrayList <Card>();
	private ArrayList <Card> cardPlayed = new ArrayList <Card>();
	
	private Logic logic = new Logic();
	private Deck deck = new Deck();
	private DisplayCards display = new DisplayCards();
	
	private SpecialCard sc;
	private SpecialFunction sf = new SpecialFunction();
	
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
				if (logic.getPlayerTurn() == 1) {
					System.out.println("\n\n\n\n\n\n\n" + "It is now Player 1's Turn" + "\n");
					boolean valid = false;
					while (!valid){
						int cardIndex = display.displayDeck(topCard, player1.getHand());
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
					} 
				}
				else if (logic.getPlayerTurn() == 2) {
					System.out.println("\n\n\n\n\n\n\n" + "It is now Player 2's Turn" + "\n");
					boolean valid = false;
					while (!valid){
						int cardIndex = display.displayDeck(topCard, player2.getHand());
						cardPlayed.clear();
						cardPlayed.add(player2.getHand().get(cardIndex-1));					
						if (valid = logic.isValid(topCard.get(0),cardPlayed.get(0))) {
							sf.SpecialFunc(cardPlayed.get(0), deck, logic, this);
							topCard.clear();
							topCard.add(cardPlayed.get(0));		
							player2.getHand().remove(cardIndex-1);
							cardPlayed.clear();
							logic.gameState();
							logic.numOfCards(player1, player2, player3, player4);
						}
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
				} else {
					System.out.println("How tho?");
					try {
						Thread.sleep(5000000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
	}
}