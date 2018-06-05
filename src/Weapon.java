import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

//
public class Weapon {

private int health;
private int hitValue;
private int x_coordinate;
private int y_coordinate;
private ImageIcon image;
private int speed;
private int type;
private int direction;

public Weapon(){
	this(40, 450, 288, 1,1);
}
public Weapon(int health, int x_coordinate, int y_coordinate, int type, int direction){
	this.health=health;
	this.x_coordinate = x_coordinate;
	this.y_coordinate = y_coordinate;
	this.type = type;
	this.direction = direction;

	ClassLoader cldr = this.getClass().getClassLoader();	// These eight lines of code load the Character's png
	String imagePath;	

	imagePath = "images/player1.png";

	URL imageURL = cldr.getResource(imagePath);				
    image = new ImageIcon(imageURL);
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
	if(direction == 0){
		x_coordinate--;
	}else if(direction == 1){
		x_coordinate++;
	}else if(direction == 2){
		y_coordinate--;
	}else if(direction == 3){
		y_coordinate ++;
	}
}

public void draw(Graphics g, Component c) {
    image.paintIcon(c, g, (int)x_coordinate, (int)y_coordinate);
}
}
