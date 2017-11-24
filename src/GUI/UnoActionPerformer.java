package GUI;

import Runner.Controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class UnoActionPerformer implements ActionListener{
	private Controller controller;
	private UnoGUI gui;
	/**
	*Constructor, sets the instance variable "controller" to the same instance that requires button input
	*@param takes an instance of Uno
	*/
	public UnoActionPerformer(Controller controller){
		this.controller = controller;
		gui = new UnoGUI();
		gui.display(controller, this);
	}

	/**
	*Action listener, recieves button input from the user and calls play() from the Uno class to update the 
	*state of the game based on what button was pressed
	@param an ActionEvent
	*/
	public void actionPerformed(ActionEvent event){
		System.out.println(event.getActionCommand());
		int cardIndex = Integer.parseInt(event.getActionCommand());
		this.controller.play(cardIndex); //is supposed to have the card index passed onto the play method
		gui.display(controller, this);
	}
}
