//
public class Weapon {

private int health;
private int hitValue;
private int x_coordinate;
private int y_coordinate;
private int speed;
private int type;

public Weapon(){
	this(40, 450, 288, 1);
}
public Weapon(int health, int x_coordinate, int y_coordinate, int type){
	this.health=health;
	this.x_coordinate = x_coordinate;
	this.y_coordinate = y_coordinate;
	this.type = type;
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

public void setSpeed(int speed){
	this.speed = speed;
}

public void checkHealth(){
	health--;
	if(health<=0){
		this.setHitValue(0);
	}
}

public void shoot(){
	
}

}
