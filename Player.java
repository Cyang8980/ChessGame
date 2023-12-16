
public class Player{
	private String color; 
	private String name; 
	private boolean check;
	
	public Player(){
	}

	public Player(String color){
		this.color = color;
	}

	public Player(String color, String name){
		this.color = color;
		this.name = name;
	}	

	public String getColor(){
		return color;
	}	

	public void setColor(String color){
		this.color = color;
	}	

	public int getEnemyColor(){
		if(getColor().equals("white")){
			return 1;
		} else {
			return 0;
		}
	}

	public String getName(){
		return name;
	}	

	public void setName(String name){
		this.name = name;
	}
	public void setCheck(boolean isInCheck) {
		this.check = isInCheck;
	}
	public boolean getCheck() {
		return this.check;
	}
}