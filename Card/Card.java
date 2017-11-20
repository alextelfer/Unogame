package Card;

public abstract class Card {
	
	private String color;
	public int colval;

	public abstract String getNumber();
	public abstract int getSpecval();
	
	/** Getter for color of type String
	 * @return a reference of color
	 */
	public String getColor() {
		return color;
	}

	/** Setter for color of type String
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString(){
		return color;
	}
	
	/** Method for creating the color portion of cards
	 * @param colval
	 * @return String of the color
	 */
	public String cardColor(int colval) {
		if (colval == 0) {
			color = "Red ";
		} else if (colval == 1) {
			color = "Blue ";
		} else if (colval == 2) {
			color = "Yellow ";
		} else if (colval == 3) {
			color = "Green ";
		} else if (colval == 5) {
			color = "";
		}
		return color;
	}

}
