
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList <Card> unoDeck = new ArrayList <Card> ();
		
	/** Getter for unoDeck
	 * @return reference of unoDeck
	 */
	public ArrayList<Card> getUnoDeck() {
		return unoDeck;
	}
	/** Constructor for Deck
	 * 	Creates the Deck of 4 0's, 8 1-9's, 6 Special cards, 1 Wild, 
	 *  and 1 Wild Draw 4 for each color
	 */
	public Deck(){
		/** Creating Zero number cards */
		for (int colval = 0; colval <= 3; colval++ ) {
			unoDeck.add(new NumberCard(colval, 0));
		}
		/** Creating 1-9 number cards */
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int numval = 1; numval <= 9; numval++) {
				for (int i = 0; i < 2; i++) {
					unoDeck.add(new NumberCard(colval, numval));
				}
			}
		}
		/** Creating Special cards: Skip, Reverse, Draw 2 */
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int specval = 0; specval <= 2; specval++) {
				for (int j = 0; j < 2; j++) {
					unoDeck.add(new SpecialCard(colval, specval));
				}
			}	
		/** Creating Special cards: Wild and Wild Draw 4 */
		}
		for (int colval = 0; colval <= 3; colval++ ) {
			for (int specval = 3; specval <= 4; specval++) {
				unoDeck.add(new SpecialCard(specval));
			}
		}
	}
	
	/** Method that picks draws random cards to player hand
	 * @param numOfCards
	 * @param player
	 */
	public void draw(int numOfCards, ArrayList<Card> player){
		Random rand = new Random();
		for (int i=0; i <numOfCards; i++)
			player.add(unoDeck.remove(rand.nextInt(unoDeck.size())) );
	}
	
	/** Method that picks a Number card for Top card
	 * @param topcard
	 */
	public void topDraw(ArrayList<Card> topcard) {
		Random rand = new Random();
		for (int i=0; i <1; i++)
			topcard.add(unoDeck.remove(rand.nextInt(80) ));
	}
	
	/** Method that shows how much cards are left
	 * @return integer of cards that are left
	 */
	public int cardsLeft() {
		return this.unoDeck.size();
	}
}
