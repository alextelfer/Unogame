import static org.junit.Assert.*;

import org.junit.Test;

public class LogicTest {
	
	
	/**
	 * Red = 0
	 * Blue = 1
	 * Yellow = 2
	 * Green = 3
	 * 
	 * Draw 2 = 0
	 * Reverse = 1
	 * Skip = 2
	 */
	
	/**
	 * Valid test block
	 */
	@Test
	public void test1_NumberCards_SameNumber_DifferentColor() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(1,2);
		Card c2 = new NumberCard(2,2);
		assertEquals("Created 2 cards, blue 2, Yellow 2",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test2_NumberCards_SameNumber_SameColor() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(3,7);
		Card c2 = new NumberCard(3,7);
		assertEquals("Created 2 cards, Green 7, green 7",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test3_NumberCards_DifferentNumber_SameColor() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(0,2);
		Card c2 = new NumberCard(0,9);
		assertEquals("Created 2 cards, Red 2, Red 9",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test4_SpecialCards_DifferentNumber_SameColor() {
		Logic logic = new Logic();
		Card c1 = new SpecialCard(0,2);
		Card c2 = new SpecialCard(0,0);
		assertEquals("Created 2 cards, Red Skip, Red Draw 2",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test5_SpecialCards_SameNumber_DifferentColor() {
		Logic logic = new Logic();
		Card c1 = new SpecialCard(3,1);
		Card c2 = new SpecialCard(1,1);
		assertEquals("Created 2 cards, Green Reverse, Blue Reverse",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test6_MixedCards_DifferentNumber_SameColor_1() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(3,1);
		Card c2 = new SpecialCard(3,1);
		assertEquals("Created 2 cards, Green Reverse, Green 1",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test7_MixedCards_DifferentNumber_SameColor_2() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(2,2);
		Card c2 = new SpecialCard(2,2);
		assertEquals("Created 2 cards, Yellow Skip, Yellow 2",true,logic.isValid(c1, c2));
	}
	
	/**
	 * Invalid test block
	 */
	@Test
	public void test8_NumberCards_DifferentNumber_DifferentColor() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(1,6);
		Card c2 = new NumberCard(2,5);
		assertEquals("Created 2 cards, blue 6, Yellow 6",false,logic.isValid(c1, c2));
	}
	
	@Test
	public void test9_SpecialCards_DifferentNumber_DifferentColor() {
		Logic logic = new Logic();
		Card c1 = new SpecialCard(0,2);
		Card c2 = new SpecialCard(2,1);
		assertEquals("Created 2 cards, Red Reverse, Yellow Skip",false,logic.isValid(c1, c2));
	}
	
	@Test
	public void test10_MixedCards_DifferentNumber_DifferentColor() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(1,2);
		Card c2 = new SpecialCard(3,2);
		assertEquals("Created 2 cards, Green Reverse, Blue 2",false,logic.isValid(c1, c2));
	}
	
	/**
	 * WildCard block
	 */
	@Test
	public void test11_NumberCard_Wild() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(2,8);
		Card c2 = new SpecialCard(3);
		assertEquals("Created 2 cards, Yellow 8, Wild",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test12_NumberCard_WildDraw4() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(0,0);
		Card c2 = new SpecialCard(4);
		assertEquals("Created 2 cards, Red 0, Wild Draw 4",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test13_SpecialCard_Wild() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(3,9);
		Card c2 = new SpecialCard(3);
		assertEquals("Created 2 cards, Green 9, Wild",true,logic.isValid(c1, c2));
	}
	
	@Test
	public void test14_SpecialCard_WildDraw4() {
		Logic logic = new Logic();
		Card c1 = new NumberCard(0,0);
		Card c2 = new SpecialCard(4);
		assertEquals("Created 2 cards, Red 0, Wild Draw 4",true,logic.isValid(c1, c2));
	}

}
