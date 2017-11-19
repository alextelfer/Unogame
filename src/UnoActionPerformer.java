import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UnoActionPerformer implements ActionListener{

	private Controller controller = new Controller();

	/**
	*Constructor, sets the instance variable "controller" to the same instance that requires button input
	*@param takes an instance of Uno
	*/
	public UnoActionPerformer(Controller controller){
		this.controller = controller;
	}

	/**
	*Action listener, recieves button input from the user and calls play() from the Uno class to update the 
	*state of the game based on what button was pressed
	@param an ActionEvent
	*/
	public void actionPerformed(ActionEvent event){
		this.controller.initializeCards();
		System.out.println(event.getActionCommand());
		int cardIndex = Integer.parseInt(event.getActionCommand());
		this.controller.play();
	}
}
