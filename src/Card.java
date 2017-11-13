
public abstract class Card {
	
	private String color;
	public int colval;

	public abstract String getNumber();
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString(){
		return cardColor(colval);
	}
	public String cardColor(int colval) {
		if (colval == 0) {
			color = "Red";
		} else if (colval == 1) {
			color = "Blue";
		} else if (colval == 2) {
			color = "Yellow";
		} else if (colval == 3) {
			color = "Green";
		} else if (colval == 5) {
			color = "";
		}
		return color;
	}
}
