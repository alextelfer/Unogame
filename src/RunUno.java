
public class RunUno {
	public static void main(String [] args) {
		Controller controller = new Controller();
		controller.initializeCards();
		UnoActionPerformer actions = new UnoActionPerformer(controller);
		UnoGUI gui = new UnoGUI();
		gui.display(controller,actions);
		System.out.println("Player 1 plays first.");
	}
}
