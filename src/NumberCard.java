
public class NumberCard extends Card{
	
	private String number;
	private int numval;
	
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String toString(){
		number = String.valueOf(this.numval);
		return super.toString() + " " + this.number;		
	}
	public NumberCard(int col, int num) {
		super.colval = col;
		this.numval = num;
	}
}
