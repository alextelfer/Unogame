package GUI;

import Card.*;

import Runner.Controller;

import javax.swing.border.*;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class UnoGUI extends JFrame {

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	private JButton card = new JButton();
	private Card aCard;

	private JPanel wildPanel = new JPanel();
	private JDialog wildCardWindow = new JDialog();
	private JFrame gameWindow = new JFrame();
	private JPanel playerPanel = new JPanel();
	private JPanel centerPanel = new JPanel(new BorderLayout());
	
	private Dimension minimumSize = new Dimension(1000,1000);
	private Color blueCard = new Color(66, 104, 208);

	/**
	 * Creates display window and displays the topCard. Calls cardButton to display
	 * player hands
	 * 
	 * @param takes
	 *            an instance of Uno which contains the ArrayLists belonging to the
	 *            topCard and player hands, ActionListener
	 */

	public void display(Controller game, ActionListener listener) {
		// gameWindow.getContentPane().setBackground(Color.white);
		gameWindow.getContentPane().removeAll();
		gameWindow.getContentPane().revalidate();
		gameWindow.getContentPane().repaint();

		
		gameWindow.setMinimumSize(minimumSize);
	//	gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		gameWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JLabel player2Cards = new JLabel("Player 2 has: " + game.getPlayer2().getHand().size() + " Cards");
		JLabel player3Cards = new JLabel("Player 3 has: " + game.getPlayer3().getHand().size() + " Cards",
				JLabel.CENTER);
		JLabel player4Cards = new JLabel("Player 4 has: " + game.getPlayer4().getHand().size() + " Cards");
		gameWindow.getContentPane().add(player2Cards, BorderLayout.WEST);
		gameWindow.getContentPane().add(player3Cards, BorderLayout.NORTH);
		gameWindow.getContentPane().add(player4Cards, BorderLayout.EAST);

		String topCardAsString = game.getTopCard().get(0).toString();
		
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		JLabel topCardLabel = new JLabel(topCardAsString, JLabel.CENTER);
		TitledBorder topCard = new TitledBorder(blackline, "The Top Card is:", TitledBorder.CENTER, TitledBorder.ABOVE_TOP);
		topCardLabel.setBorder(topCard);
	//	centerPanel.add(topCardLabel);
		
//		JLabel playerTurnLabel = new JLabel("it is player" + game.getPlayerTurn() + "'s turn");
//		playerTurnLabel.setSize(50, 100);
//		playerTurnLabel.setBorder(new LineBorder(Color.BLACK));
		
		
//		playerTurnLabel.setPreferredSize(new Dimension(150,150));
		setLabelColor(game.getTopCard().get(0), topCardLabel);

		gameWindow.getContentPane().add(topCardLabel, BorderLayout.CENTER);
		
	//	gameWindow.getContentPane().add(centerPanel);
		cardButton(game.getPlayer1().getHand(), listener);

		gameWindow.setVisible(true);
		gameWindow.pack();
	}

	/**
	 * creates buttons and ActionListeners for each card a player has
	 * 
	 * @param takes
	 *            an Arraylist of Unocards corresponding to players hand,
	 *            ActionListener
	 */
	public void cardButton(ArrayList<Card> playerCards, ActionListener listener) {

		playerPanel = new JPanel();
		for (int i = 0; i < playerCards.size(); i++) {
			aCard = playerCards.get(i);
			String aFace = aCard.toString();
			card = new JButton(aFace);
			if (aCard.getColor() == "Red ") {
				card.setBackground(Color.RED);
			} else if (aCard.getColor() == "Blue ") {
				card.setBackground(blueCard);
			} else if (aCard.getColor() == "Yellow ") {
				card.setBackground(Color.YELLOW);
			} else if (aCard.getColor() == "Green ") {
				card.setBackground(Color.GREEN);
			} else {
				card.setBackground(Color.LIGHT_GRAY);
			}

			String stringI = Integer.toString(i + 1);
			card.setActionCommand(stringI);
			card.addActionListener(listener);
			playerPanel.add(card);
		}
		JButton drawCardButton = new JButton("Draw Card");
		drawCardButton.setBackground(Color.MAGENTA);
		drawCardButton.setActionCommand(Integer.toString(0));
		drawCardButton.addActionListener(listener);
		playerPanel.add(drawCardButton);
		gameWindow.getContentPane().add(playerPanel, BorderLayout.SOUTH);
	}
	
	public void clearWildCardButtons() {
		wildCardWindow.dispose();
	}

	public void wildCardButtons(ActionListener listener) {
		wildCardWindow.getContentPane().removeAll();
		wildCardWindow.getContentPane().revalidate();
		wildCardWindow.getContentPane().repaint();
		wildCardWindow.setModal(true);
		wildCardWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		JPanel promptPanel = new JPanel();
		JLabel prompt = new JLabel("Pick a Color");
		prompt.setAlignmentY(CENTER_ALIGNMENT);
		promptPanel.add(prompt);
		wildCardWindow.getContentPane().add(promptPanel, BorderLayout.NORTH);
		JButton redColor = new JButton("RED");
		redColor.setActionCommand("RED");
		redColor.addActionListener(listener);
		redColor.setBackground(Color.RED);
		wildPanel.add(redColor);
		JButton blueColor = new JButton("BLUE");
		blueColor.setActionCommand("BLUE");
		blueColor.addActionListener(listener);
		blueColor.setBackground(Color.BLUE);
		wildPanel.add(blueColor);
		JButton yellowColor = new JButton("YELLOW");
		yellowColor.setActionCommand("YELLOW");
		yellowColor.addActionListener(listener);
		yellowColor.setBackground(Color.YELLOW);
		wildPanel.add(yellowColor);
		JButton greenColor = new JButton("GREEN");
		greenColor.setActionCommand("GREEN");
		greenColor.addActionListener(listener);
		greenColor.setBackground(Color.GREEN);
		wildPanel.add(greenColor);
		wildCardWindow.getContentPane().add(wildPanel, BorderLayout.CENTER);
		wildCardWindow.pack();
		wildCardWindow.setVisible(true);

	}

	public void setLabelColor(Card aCard, JLabel topCardLabel) {
		if (aCard.getColor() == "Red ") {
			topCardLabel.setForeground(Color.RED);
		} else if (aCard.getColor() == "Blue ") {
			topCardLabel.setForeground(blueCard);
		} else if (aCard.getColor() == "Yellow ") {
			topCardLabel.setForeground(Color.YELLOW);
		} else if (aCard.getColor() == "Green ") {
			topCardLabel.setForeground(Color.GREEN);
		}
	}

}
