
public class SpecialCard extends Card{

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	private String number;
	
	public String toString(){
		return super.toString() + this.number;
	}
	
	//Put action here (maybe)
}
