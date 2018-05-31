//
public class Weapon {

private int health;
private int hitValue;

public Weapon(){
	this(30,10);
}
public Weapon(int health, int hitValue){
	this.health=health;
	this.hitValue=hitValue;
}

//

public int getHitValue() {
	return hitValue;
}

public void setHitValue(int hitValue) {
	this.hitValue = hitValue;
}

public int getHealth() {
	return health;
}

public void setHealth(int health) {
	this.health = health;
}

public void checkHealth(){
	health--;
	if(health<=0){
		this.setHitValue(0);
	}
}

}
