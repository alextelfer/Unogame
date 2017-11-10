
public class NumberCard extends Card{
	
	private String number;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String toString(){
		return super.toString() + this.number;		
	}
}
