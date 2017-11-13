
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
		if (specval == 0) {
			number = "Draw 2";
		} else if (specval == 1) {
			number = "Reverse";
		} else if (specval == 2) {
			number = "Skip";
		} else if (specval == 3) {
			number = "Wild";
		} else if (specval == 4) {
			number = "Wild Draw 4";
		}
		return super.toString() + this.number;
	}
	public SpecialCard(int col, int spec) {
		super.colval = col;
		this.specval = spec;
	}
	//Put action here (maybe)
}
