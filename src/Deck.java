
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList <Card> unoDeck = new ArrayList <Card> ();
	
	public ArrayList<Card> getUnoDeck() {
		return unoDeck;
	}
	public Deck(){
		// number cards
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int numval = 0; numval <= 9; numval++) {
				for (int i = 0; i < 2; i++) {
					unoDeck.add(new NumberCard(colval, numval));
				}
			}
		}
		// special cards skip, reverse, draw 2
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int specval = 0; specval <= 2; specval++) {
				for (int j = 0; j < 2; j++) {
					unoDeck.add(new SpecialCard(colval, specval));
				}
			}	
		// special cards wild and wild draw 4
		}
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int specval = 3; specval <= 4; specval++) {
				unoDeck.add(new SpecialCard(specval));
			}
		}
	}
	
	public void draw(int numOfCards, ArrayList<Card> player){
		Random rand = new Random();
		for (int i=0; i <numOfCards; i++)
			player.add(unoDeck.remove(rand.nextInt(unoDeck.size())) );
	}
	
	public int cardsLeft() {
		return this.unoDeck.size();
	}
}
