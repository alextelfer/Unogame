import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class PlayerAITest {

	@Test
	public void test_wild() {
		Deck deck = new Deck();
		Logic logic = new Logic();
		PlayerAI ai = new PlayerAI();
		Card topCard = new NumberCard(1,2); //blue 2
		Card c1 = new NumberCard(1,2); //blue 2
		Card c2 = new NumberCard(0,9); //red 9
		Card c3 = new SpecialCard(3); //wild
		Card c4 = new SpecialCard(3,2); //green skip
		Card c5 = new SpecialCard(3,1); //green reverse
		Card c6 = new SpecialCard(3,0); //green draw 2
		Card c7 = new NumberCard(2,5); //yellow 5
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(c1);hand.add(c2);hand.add(c3);hand.add(c4);hand.add(c5);hand.add(c6);hand.add(c7);
		ai.setHand(hand);
		Card cardPlayed= ai.cardAI(deck, logic, topCard);
		assertEquals("TopCard: Blue 2. Expecting AI to play Wild and set topCard to green (green is the most common in the hand)","Green ",cardPlayed.getColor());
	}
	
	@Test
	public void test_wild2() {
		Deck deck = new Deck();
		Logic logic = new Logic();
		PlayerAI ai = new PlayerAI();
		Card topCard = new NumberCard(1,2); //blue 2
		Card c1 = new NumberCard(1,2); //blue 2
		Card c2 = new NumberCard(0,9); //red 9
		Card c3 = new SpecialCard(3); //wild
		Card c4 = new SpecialCard(2,2); //Yellow skip
		Card c5 = new SpecialCard(0,1); //Red reverse
		Card c6 = new SpecialCard(3,0); //Green draw 2
		Card c7 = new NumberCard(2,5); //yellow 5
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(c1);hand.add(c2);hand.add(c3);hand.add(c4);hand.add(c5);hand.add(c6);hand.add(c7);
		ai.setHand(hand);
		Card cardPlayed= ai.cardAI(deck, logic, topCard);
		assertEquals("TopCard: Blue 2. Expecting AI to play Wild and set topCard to Red (Red is tied for the most common in the hand, should default to red)","Red ",cardPlayed.getColor());
	}
	
	@Test
	public void test_Special() {
		Deck deck = new Deck();
		Logic logic = new Logic();
		PlayerAI ai = new PlayerAI();
		Card topCard = new NumberCard(2,4); //Yellow 4
		Card c1 = new NumberCard(1,2); //blue 2
		Card c2 = new NumberCard(0,9); //red 9
		Card c3 = new NumberCard(0,0); //Red 0
		Card c4 = new SpecialCard(2,2); //Yellow skip
		Card c5 = new SpecialCard(0,1); //Red reverse
		Card c6 = new SpecialCard(2,0); //Yellow draw 2
		Card c7 = new NumberCard(2,5); //yellow 5
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(c1);hand.add(c2);hand.add(c3);hand.add(c4);hand.add(c5);hand.add(c6);hand.add(c7);
		ai.setHand(hand);
		Card cardPlayed= ai.cardAI(deck, logic, topCard);
		assertEquals("TopCard: Blue 2. Expecting AI to play Yellow Skip",c4,cardPlayed);
	}
	
	@Test
	public void no_valid_cards() {
		Deck deck = new Deck();
		Logic logic = new Logic();
		PlayerAI ai = new PlayerAI();
		Card topCard = new NumberCard(2,4); //Yellow 4
		Card c1 = new NumberCard(1,2); //blue 2
		Card c2 = new NumberCard(0,9); //red 9
		Card c3 = new NumberCard(0,0); //Red 0
		Card c4 = new SpecialCard(3,2); //Green skip
		Card c5 = new SpecialCard(0,1); //Red reverse
		Card c6 = new SpecialCard(0,0); //Red draw 2
		Card c7 = new NumberCard(1,5); //Blue 5
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(c1);hand.add(c2);hand.add(c3);hand.add(c4);hand.add(c5);hand.add(c6);hand.add(c7);
		ai.setHand(hand);
		Card cardPlayed = ai.cardAI(deck, logic, topCard);
		assertNotEquals("TopCard: Yellow 4. No valid cards in hand. Expecting AI to draw cards (hand size should not be 6)",6, ai.getHand().size());
	}


}
