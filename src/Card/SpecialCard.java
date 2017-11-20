package Card;

public class SpecialCard extends Card{

	private String number;
	public int specval;
	
	/**Getter for specval
	 * @return a reference of specval
	 */
	public int getSpecval() {
		return this.specval;
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setColor(String color) {
		super.setColor(color);
	}
	
	/**Setter for number of type String
	 * @param number 
	 */
	public void setNumber(String number) {
		this.number = number;
	}


	public String toString(){
		return super.toString() + "" + this.number;
	}
	
	/** Constructor that creates special cards with colors
	 * @param col
	 * @param spec
	 */
	public SpecialCard(int col, int spec) {
		super.cardColor(col);
		this.specval = spec;
		if (specval == 0) {
			number = "Draw 2";
		} else if (specval == 1) {
			number = "Reverse";
		} else if (specval == 2) {
			number = "Skip";
		} else if (specval == -1) {
			number = "";
		}
	}
	
	/** Constructor that creates special cards without colors
	 * @param spec
	 */
	public SpecialCard(int spec) {
		this.specval = spec;
		 if (specval == 3) {
			super.cardColor(5);
			number = "Wild";
		 } else if (specval == 4) {
			super.cardColor(5);
			number = "Wild Draw 4";
		 } 
	
	}
	
}
