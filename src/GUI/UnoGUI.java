package GUI;

import Card.*;
import Runner.Controller;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class UnoGUI extends JFrame{
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	private JButton card = new JButton();
	private Card aCard; 

	/** 
	 *Creates display window and displays the topCard. Calls cardButton to display player hands
	 *@param takes an instance of Uno which contains the ArrayLists belonging to the topCard and player 
	 *hands, ActionListener
	 */	
	
	public void display(Controller game,ActionListener listener){
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();	
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel player1Cards = new JLabel("Player 1 has: " +  game.getPlayer1().getHand().size() + " Cards" );
		JLabel player2Cards = new JLabel("Player 2 has: " + game.getPlayer2().getHand().size() + " Cards");
		JLabel player3Cards = new JLabel("Player 3 has: " + game.getPlayer3().getHand().size() + " Cards");
		JLabel player4Cards = new JLabel("Player 4 has: " + game.getPlayer4().getHand().size() + " Cards");
		panel1.add(player1Cards);
		panel1.add(player2Cards);
		panel1.add(player3Cards);
		panel1.add(player4Cards);
		frame.getContentPane().add(panel1);
		String topCardAsString = game.getTopCard().get(0).toString();
		JLabel topCardLabel = new JLabel(topCardAsString);
		panel2.add(topCardLabel);
		frame.getContentPane().add(panel2);
		frame.getContentPane().add(panel1);
		cardButton(game.getPlayer1().getHand(),listener, frame);
	}

	/**
	 *creates buttons and ActionListeners for each card a player has
	 *@param takes an Arraylist of Unocards corresponding to players hand, ActionListener
	 */
	public void cardButton(ArrayList <Card> playerCards, ActionListener listener, JFrame frame){
		JPanel panel3 = new JPanel();
		for (int i = 0; i < playerCards.size(); i++){
			aCard = playerCards.get(i);
			String aFace = aCard.toString();
			card = new JButton(aFace);
			String stringI = Integer.toString(i+1);
			card.setActionCommand(stringI);
			card.addActionListener(listener);
			panel3.add(card);
			frame.getContentPane().add(panel3);
		}
		JPanel panel4 = new JPanel();
		JButton drawCardButton = new JButton("Draw Card");
		drawCardButton.setActionCommand(Integer.toString(0));
		drawCardButton.addActionListener(listener);
		panel4.add(drawCardButton);
		frame.getContentPane().add(panel4);
	}
	
}
