package Card;

public class NumberCard extends Card{
	
	private String number;
	public int specval;
	
	@Override
	public String getNumber() {
		return number;
	}
	
	public int getSpecval() {
		return specval;
	}

	/** Setter for number of type String
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String toString(){
		return super.toString() + "" + this.number;		
	}
	/** Constructor that creates number cards
	 * @param col
	 * @param num
	 */
	public NumberCard(int col, int num) {
		super.cardColor(col);
		this.specval = num;
		this.number = String.valueOf(specval);
	}

	
}
