//
public class Zombie {
	//instance variable:
	private int health;
	private int hitValue;

	public Zombie(){
		this(50,6);
	}
	public Zombie(int health, int hitValue){
		this.setHealth(health);
		this.setHitValue(hitValue);
	}

	
	public int getHealth() {
		return health;
	}
	//
	//
	//
	//
	public void setHealth(int health) {
		this.health = health;
	}
	//
	//
	//
	//
	public int getHitValue() {
		return hitValue;
	}
	//
	//
	//
	//
	public void setHitValue(int hitValue) {
		this.hitValue = hitValue;
	}

}