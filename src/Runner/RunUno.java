package Runner;

import Runner.Controller;
import GUI.UnoActionPerformer;
import GUI.UnoGUI;

public class RunUno {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller();
				controller.initializeCards();
				UnoActionPerformer actions = new UnoActionPerformer(controller);
				UnoGUI gui = new UnoGUI();
				gui.display(controller, actions);
			}
		});
	}
}