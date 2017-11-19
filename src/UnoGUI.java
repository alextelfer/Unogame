import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class UnoGUI extends JFrame{
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	private JFrame frame = new JFrame();	
	private JPanel panel = new JPanel();
	private JButton card = new JButton();
	private JLabel topCardLabel = new JLabel("");
	private Card aCard; 
	private ArrayList<Card> topCardList = new ArrayList<Card>();

	/** 
	 *Creates display window and displays the topCard. Calls cardButton to display player hands
	 *@param takes an instance of Uno which contains the ArrayLists belonging to the topCard and player 
	 *hands, ActionListener
	 */	
	public void display(Controller game,ActionListener listener){
		this.setVisible(true);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Uno Game");
		topCardList = game.getTopCardAsList();
		Card topCardObject = topCardList.get(0);
		String topCardAsString = topCardObject.toString();
		topCardLabel.setText("TopCard: " + topCardAsString + "          ");
		panel.add(topCardLabel);
		cardButton(game.getPlayer1().getHand(),listener);
		super.getContentPane().add(panel);
	}

	/**
	 *creates buttons and ActionListeners for each card a player has
	 *@param takes an Arraylist of Unocards corresponding to players hand, ActionListener
	 */
	public void cardButton(ArrayList <Card> playerCards, ActionListener listener){
		for (int i = 0; i < playerCards.size(); i++){
			aCard = playerCards.get(i);
			String aFace = aCard.toString();
			card = new JButton(aFace);
			String stringI = Integer.toString(i+1);
			card.setActionCommand(stringI);
			card.addActionListener(listener);
			panel.add(card);
		}
		JButton drawCardButton = new JButton("Draw Card");
		drawCardButton.setActionCommand(Integer.toString(0));
		drawCardButton.addActionListener(listener);
		panel.add(drawCardButton);
	}
}
