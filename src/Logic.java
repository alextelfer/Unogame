
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
		if (cardPlayedColor.equals(topCardColor) || cardPlayedNumber.equals(topCardNumber)) {
			valid = true;
			gameState();
		}
		return valid;
	}
	
	public void gameState(){
		if (clockwise == true){
			setPlayerTurn(playerTurn++);
			if (playerTurn > 4) {
				setPlayerTurn(1);
			} 
		}
		else {
			setPlayerTurn(playerTurn--);
			if (playerTurn < 1) {
				setPlayerTurn(4);
			}
		}
	}
}
