package Card;

public class NumberCard extends Card{
	
	private String number;
	public int numval;
	
	@Override
	public String getNumber() {
		return number;
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
		super.colval = col;
		super.cardColor(col);
		this.numval = num;
		this.number = String.valueOf(numval);
	}

	
}
