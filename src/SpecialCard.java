
public class SpecialCard extends Card{

	private String number;
	public int specval;
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String toString(){
		
		return super.toString() + " " + this.number;
	}
	public SpecialCard(int col, int spec) {
		super.colval = col;
		this.specval = spec;
		if (specval == 0) {
			number = "Draw 2";
		} else if (specval == 1) {
			number = "Reverse";
		} else if (specval == 2) {
			number = "Skip";
		}
	}
	
	public SpecialCard(int spec) {
		
		this.specval = spec;
		 if (specval == 3) {
			super.colval = 5;
			number = "Wild";
		
		 } else if (specval == 4) {
			super.colval = 5;
			number = "Wild Draw 4";
		 }
	
	}
	//Put action here (maybe)
}
