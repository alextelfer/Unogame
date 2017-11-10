
public abstract class Card {
	
	private String color;

	public abstract String getNumber();
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString(){
		return color;
	}
}
