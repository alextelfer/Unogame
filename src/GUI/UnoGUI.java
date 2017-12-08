package GUI;

import Card.*;

import Runner.Controller;

import javax.swing.border.*;

import org.omg.CORBA.portable.InputStream;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
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
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class UnoGUI extends JFrame {
	

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	private JButton drawCardButton = new JButton();
	private JButton card = new JButton();
	private Card aCard;
    // from https://opengameart.org/content/uno
	private ImageIcon cardBack = new ImageIcon("src/UNO-Back.png");
	
	private JPanel wildPanel = new JPanel();
	private JDialog wildCardWindow = new JDialog();
	private JFrame gameWindow = new JFrame();
	private JPanel playerPanel = new JPanel(new FlowLayout());
	private JPanel centerPanel = new JPanel(new GridBagLayout());
	
	JButton redColor = new JButton("RED");
	JButton yellowColor = new JButton("YELLOW");
	JButton greenColor = new JButton("GREEN");
	JButton blueColor = new JButton("BLUE");
	
	JLabel topCardLabel = new JLabel("test", JLabel.CENTER);
	Border blackline = BorderFactory.createLineBorder(Color.black);

	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	private Color blueCard = new Color(66, 104, 208);

	public UnoGUI(ActionListener listener) {
		topCardLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		topCardLabel.setPreferredSize(new Dimension(80,100));
		TitledBorder topCard = new TitledBorder(blackline, "Top Card");
		topCardLabel.setBorder(topCard);
		topCardLabel.setOpaque(true);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.CENTER;
		centerPanel.add(topCardLabel, gbc);

		TitledBorder cardLegend = new TitledBorder(blackline, "Card Legend:");
		JLabel playerTurnLabel = new JLabel("<html>D = Draw 2<br>R = Reverse<br>S = Skip<br>w = Wild<br>M = Wild Draw 4</html>");
		playerTurnLabel.setPreferredSize(new Dimension(200, 100));
		playerTurnLabel.setBorder(cardLegend);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		centerPanel.add(playerTurnLabel, gbc);

		Image unoCard = cardBack.getImage();
		Image newUnoCard = unoCard.getScaledInstance(50,80, java.awt.Image.SCALE_SMOOTH) ;
		cardBack = new ImageIcon(newUnoCard);

		drawCardButton = new JButton();
		drawCardButton.setIcon(cardBack);
		drawCardButton.setPreferredSize(new Dimension(50,80));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.PAGE_END;
		drawCardButton.setActionCommand(Integer.toString(0));
		drawCardButton.addActionListener(listener);
		
		centerPanel.add(drawCardButton, gbc);
		gameWindow.setVisible(true);
	}
	
	/**
	 * Creates display window and displays the topCard. Calls cardButton to display
	 * player hands
	 * 
	 * @param takes
	 *            an instance of Uno which contains the ArrayLists belonging to the
	 *            topCard and player hands, ActionListener
	 */

	public void display(Controller game, ActionListener listener) {
		gameWindow.setTitle("UNO");
		
		gameWindow.getContentPane().removeAll();
		gameWindow.getContentPane().revalidate();


		gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		gameWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JLabel player2Cards = new JLabel("Player 2 has: " + game.getPlayer2().getHand().size() + " Cards");
		JLabel player3Cards = new JLabel("Player 3 has: " + game.getPlayer3().getHand().size() + " Cards",
				JLabel.CENTER);
		JLabel player4Cards = new JLabel("Player 4 has: " + game.getPlayer4().getHand().size() + " Cards");
		gameWindow.getContentPane().add(player2Cards, BorderLayout.WEST);
		gameWindow.getContentPane().add(player4Cards, BorderLayout.EAST);
		JPanel topPanel = new JPanel();
		topPanel.add(player3Cards);
		topPanel.setPreferredSize(new Dimension(1000,200));
		gameWindow.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		String topCardAsString = game.getTopCard().get(0).printFace();

		topCardLabel.setText(topCardAsString);
		setBackgroundColor(game.getTopCard().get(0), topCardLabel);
		
	//	centerPanel.setBorder(blackline);
				
		cardButton(game.getPlayer1().getHand(), listener);
		gameWindow.getContentPane().add(centerPanel, BorderLayout.CENTER);
		gameWindow.getContentPane().repaint();
	
		
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
		playerPanel.setPreferredSize(new Dimension(1000,200));
		for (int i = 0; i < playerCards.size(); i++) {
			aCard = playerCards.get(i);
			String aFace = aCard.printFace();	
			card = new JButton(aFace);
			
			card.setPreferredSize(new Dimension(50,80));
			if (aCard instanceof NumberCard) {
				card.setFont(new Font("Arial", Font.PLAIN, 20));			
			} else if (aCard instanceof SpecialCard) {
				card.setFont(new Font("Arial", Font.PLAIN, 19));
			}
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
		
		//gameWindow.getContentPane().add(centerPanel, BorderLayout.CENTER);
		gameWindow.getContentPane().add(playerPanel, BorderLayout.SOUTH);
	}

	public void clearWildCardButtons() {
		wildCardWindow.getContentPane().removeAll();
		wildCardWindow.getContentPane().revalidate();
		wildCardWindow.setVisible(false);
	}

	public void wildCardButtons(ActionListener listener) {
		wildCardWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		JPanel promptPanel = new JPanel();
		JLabel prompt = new JLabel("Pick a Color");
		prompt.setAlignmentY(CENTER_ALIGNMENT);
		promptPanel.add(prompt);
		wildCardWindow.getContentPane().add(promptPanel, BorderLayout.NORTH);
		redColor.setActionCommand("RED");
		redColor.addActionListener(listener);
		redColor.setBackground(Color.RED);
		wildPanel.add(redColor);
		blueColor.setActionCommand("BLUE");
		blueColor.addActionListener(listener);
		blueColor.setBackground(Color.BLUE);
		wildPanel.add(blueColor);
		yellowColor.setActionCommand("YELLOW");
		yellowColor.addActionListener(listener);
		yellowColor.setBackground(Color.YELLOW);
		wildPanel.add(yellowColor);
		greenColor.setActionCommand("GREEN");
		greenColor.addActionListener(listener);
		greenColor.setBackground(Color.GREEN);
		wildPanel.add(greenColor);
		wildCardWindow.getContentPane().add(wildPanel, BorderLayout.CENTER);
		wildCardWindow.pack();
		wildCardWindow.setModal(true);
		wildCardWindow.setVisible(true);
		wildCardWindow.getContentPane().repaint();
	}
	
	public void WinnerWindow(String winningMessage) {
		JDialog winnerBox = new JDialog();
		JLabel winner = new JLabel(winningMessage);
		winnerBox.getContentPane().add(winner, BorderLayout.CENTER);
		winnerBox.pack();
		winnerBox.setModal(true);
		winnerBox.setVisible(true);
	}

	public void setBackgroundColor(Card aCard, JLabel topCardLabel) {
		if (aCard.getColor() == "Red ") {
			topCardLabel.setBackground(Color.RED);
		} else if (aCard.getColor() == "Blue ") {
			topCardLabel.setBackground(blueCard);
		} else if (aCard.getColor() == "Yellow ") {
			topCardLabel.setBackground(Color.YELLOW);
		} else if (aCard.getColor() == "Green ") {
			topCardLabel.setBackground(Color.GREEN);
		}
	}

}