package GUI;

import Card.*;

import Runner.Controller;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class UnoGUI extends JFrame {

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	private JButton card = new JButton();
	private Card aCard;

	private JPanel playerPanel = new JPanel();

	/**
	 * Creates display window and displays the topCard. Calls cardButton to display
	 * player hands
	 * 
	 * @param takes
	 *            an instance of Uno which contains the ArrayLists belonging to the
	 *            topCard and player hands, ActionListener
	 */

	public void display(Controller game, ActionListener listener) {

		this.getContentPane().removeAll();
		this.getContentPane().revalidate();
		this.getContentPane().repaint();

		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JLabel player2Cards = new JLabel("Player 2 has: " + game.getPlayer2().getHand().size() + " Cards");
		JLabel player3Cards = new JLabel("Player 3 has: " + game.getPlayer3().getHand().size() + " Cards",
				JLabel.CENTER);
		JLabel player4Cards = new JLabel("Player 4 has: " + game.getPlayer4().getHand().size() + " Cards");
		this.getContentPane().add(player2Cards, BorderLayout.WEST);
		this.getContentPane().add(player3Cards, BorderLayout.NORTH);
		this.getContentPane().add(player4Cards, BorderLayout.EAST);

		String topCardAsString = game.getTopCard().get(0).toString();
		JLabel topCardLabel = new JLabel("Top Card: " + topCardAsString, JLabel.CENTER);

		this.getContentPane().add(topCardLabel, BorderLayout.CENTER);
		cardButton(game.getPlayer1().getHand(), listener);
		this.setVisible(true);
	}

	/**
	 * creates buttons and ActionListeners for each card a player has
	 * 
	 * @param takes
	 *            an Arraylist of Unocards corresponding to players hand,
	 *            ActionListener
	 */
	public void cardButton(ArrayList<Card> playerCards, ActionListener listener) {
		System.out.println("The number of cards..........." + playerCards.size());
		playerPanel = new JPanel();
		for (int i = 0; i < playerCards.size(); i++) {
			aCard = playerCards.get(i);
			String aFace = aCard.toString();
			card = new JButton(aFace);
			String stringI = Integer.toString(i + 1);
			card.setActionCommand(stringI);
			card.addActionListener(listener);
			playerPanel.add(card);
		}
		JButton drawCardButton = new JButton("Draw Card");
		drawCardButton.setBackground(Color.CYAN);
		drawCardButton.setActionCommand(Integer.toString(0));
		drawCardButton.addActionListener(listener);
		playerPanel.add(drawCardButton);
		this.getContentPane().add(playerPanel, BorderLayout.SOUTH);
	}

}
