
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList <Card> unoDeck = new ArrayList <Card> ();
	private NumberCard numcard;
	
	public ArrayList<Card> getUnoDeck() {
		return unoDeck;
	}
	public Deck(){
		// number cards (zeros)
		for (int colval = 0; colval <= 3; colval++ ) {
			unoDeck.add(new NumberCard(colval, 0));
		}
		// number cards (1-9)
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int numval = 1; numval <= 9; numval++) {
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
	
	public void topDraw(ArrayList<Card> topcard) {
		Random rand = new Random();
		for (int i=0; i <1; i++)
			topcard.add(unoDeck.remove(rand.nextInt(80) ));
	}
	
	public int cardsLeft() {
		return this.unoDeck.size();
	}
}
