package app;

public class Ship {

	private char name;
	private int posx;
	private int posy;
	private int direction;
	private int length;
	private boolean isAlive;
	private int hp;

	public Ship(int posx, int posy, int direction, int length, char c) {
		this.posx = posx;
		this.posy = posy;
		this.direction = direction;
		this.length = length;
		this.hp = length;
		this.name = c;
		isAlive=true;
		Gameboard.setShips(this.posx, this.posy, this.direction, this.length,
				name);

	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public int getHp(){
		return hp;
	}
	
	public void gotShot(){
		this.hp--;
		if(hp==0){
			isAlive=false;
		}
	}
}
