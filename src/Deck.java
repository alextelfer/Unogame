import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList <Card> unoDeck = new ArrayList <Card> ();
	
	public ArrayList<Card> getUnoDeck() {
		return unoDeck;
	}
	public Deck(){
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int numval = 0; numval <= 9; numval++) {
				unoDeck.add(new NumberCard(colval, numval));
			}
		}
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int specval = 0; specval <= 2; specval++) {
				unoDeck.add(new SpecialCard(colval, specval));
			}
		//Nested for loops, for number cards and special cards
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
