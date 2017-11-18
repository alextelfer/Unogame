
public class Logic {
	private int playerTurn = 1;
	private boolean clockwise = true;

	private SpecialFunction sf = new SpecialFunction();
	private SpecialCard sc = new SpecialCard();
	
	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public boolean isClockwise() {
		return clockwise;
	}

	public void setClockwise(boolean clockwise) {
		this.clockwise = clockwise;
	}
	
	public boolean isValid(Card topCard, Card cardPlayed){
		boolean valid = false;
		boolean special = false;

		String topCardColor = topCard.getColor();
		String topCardNumber = topCard.getNumber();
		String cardPlayedColor = cardPlayed.getColor();
		String cardPlayedNumber = cardPlayed.getNumber();
		System.out.println("Colors:  " + cardPlayedColor + "  " + topCardColor);
		System.out.println("Numbers:  " + cardPlayedNumber + "  " + topCardNumber);
		if (cardPlayedColor.equals(topCardColor) || cardPlayedNumber.equals(topCardNumber) || cardPlayedNumber.equals("Wild") || cardPlayedNumber.equals("Wild Draw 4")) {
			valid = true;
			if (sc.getSpecval() > 0 && sc.getSpecval() < 5) {
			
				
			} else if (special = false) {
				valid = true;
			} 
		}
		System.out.println(valid);
		return valid;
	}
	
	public void gameState(){
		if (clockwise == true){
	
			if (playerTurn == 5) {
				setPlayerTurn(1);
				
			} 
			else if (playerTurn == 6) {
				setPlayerTurn(2);
			}
		}
		else {

			if (playerTurn ==0 ) {
				setPlayerTurn(4);
			} else if (playerTurn == -1) {
				setPlayerTurn(3);
				
			}
		}
	}
	public void numOfCards(PlayerHuman player1, PlayerHuman player2, PlayerAI player3, PlayerAI player4) {
		int numPlayer1 = player1.getHand().size();
		int numPlayer2 = player2.getHand().size();
		int numPlayer3 = player3.getHand().size();
		int numPlayer4 = player4.getHand().size();
		System.out.println("Player 1 has " + numPlayer1 + " Cards");
		System.out.println("Player 2 has " + numPlayer2 + " Cards");
		System.out.println("Player 3 has " + numPlayer3 + " Cards");
		System.out.println("Player 4 has " + numPlayer4 + " Cards");
	}
}