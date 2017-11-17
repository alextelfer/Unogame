
public class Logic {
	private int playerTurn = 1;
	private boolean clockwise = true;


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
		String topCardColor = topCard.getColor();
		String topCardNumber = topCard.getNumber();
		String cardPlayedColor = cardPlayed.getColor();
		String cardPlayedNumber = cardPlayed.getNumber();
		System.out.println("Colors:  " + cardPlayedColor + "  " + topCardColor);
		System.out.println("Numbers:  " + cardPlayedNumber + "  " + topCardNumber);
		if (cardPlayedColor.equals(topCardColor) || cardPlayedNumber.equals(topCardNumber)) {
			valid = true;
		}
		System.out.println(valid);
		return valid;
	}
	
	public void gameState(){
		if (clockwise == true){
			setPlayerTurn(playerTurn+1);
			if (playerTurn == 5) {
				setPlayerTurn(1);
			} 
		}
		else {
			setPlayerTurn(playerTurn--);
			if (playerTurn == 0) {
				setPlayerTurn(4);
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
