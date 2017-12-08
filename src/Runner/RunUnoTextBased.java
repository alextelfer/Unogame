package Runner;
public class RunUnoTextBased {
	public static void main(String [] args) {
		Controller controller = new Controller();
		controller.initializeCards();
		controller.play();
	}
}
