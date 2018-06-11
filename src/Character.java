// Class: Character
// Written by: Mr. Swope
// Date: 10/28/15
// Description: This class implements a Character.  This Character will be drawn onto a graphics panel. 
// ////
// If you modify this class you should add comments that describe when and how you modified the class.  
//
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;

public class Character {
	private int health;
	private ImageIcon image;			// The ImageIcon will be used to hold the Character's png.
	private double speed;	
	private int imageVal;
	// This png must be saved in the images folder and will be loaded 
										// in the constructor.
	
	private double x_coordinate;			// These ints will be used for the drawing the png on the graphics panel.
	private double y_coordinate;
	private int Direction;									// When the Character's move method is called you should update one or both
										// of these instance variables.  (0,0) is the top left hand corner of the
										// panel.  x increases as you move to the right, y increases as you move
										// down.
	
	// method: Default constructor - see packed constructors comments for a description of parameters.
	public Character(){
		this(0, 200, 300,1,100);
	}
		
	// method: Character's packed constructor
	// description: Initialize a new Character object.
	// parameters: imageChoice - used to determine which image to load when a Character is instantiated.  You can change
	//			   existing options or add other options. 0 - pirate, 1 - parrot.
	//			   x_coordinate - the initial x-coordinate for Character.
	//			   y_coordinate - the initial y-coordinate for Character.
	public Character(int imageChoice, int x_coordinate, int y_coordinate, double speed, int health){
        
		ClassLoader cldr = this.getClass().getClassLoader();	// These eight lines of code load the Character's png
		String imagePath;										// so that it later be painted on the graphics panel
																// when draw method is called.  You should modify
																// the imagePath if you change the Character's png.
		
		if(imageChoice == 0){									// if statement that determines which image to use for		
			imageVal = 0;
			imagePath = "images/player2.png";					// a Character object.  You can add other options as well.
		}
		else if(imageChoice == 1){
			imageVal = 1;
			imagePath = "images/player2L.png";
		}
		else if(imageChoice == 2){
			imageVal= 2;
			imagePath = "images/player2.png";
		}
		else if(imageChoice==3){
			imagePath = "images/zombie.png";
		}
		else if(imageChoice==4){
			imagePath = "images/zombieRight.png";
		}
		else if(imageChoice==5){
			imagePath = "images/zombieLeft.png";
		}
		else if(imageChoice==6){
			imagePath = "images/zombieDown.png";
		}
		else
			imagePath="images/tree.png";
		URL imageURL = cldr.getResource(imagePath);				
        image = new ImageIcon(imageURL);						
        
        this.x_coordinate = x_coordinate;						// Initial coordinates for the Character.
        this.y_coordinate = y_coordinate;  
        this.speed = speed;
        this.health=health;
	}
	
	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public double getSpeed(){
		return this.speed;
	}
//sdsad
	// method: getBounds
	// description: This method will return the coordinates of a rectangle that would be drawn around the 
	// 				Character's png.  This rectangle can be used to check to see if the Character bumps into 
	//				another character on your panel by calling the Rectangle's intersects method:
	//
	//							p.getBounds().intersects(c.getBounds());
	//
	//				in this example p is an instance of the Character class and c is an instance of another
	//				class that has a getBounds method that also returns a Rectangle, so p.getBounds and
	//				c.getBounds would both return or evaluate to Rectangle objects.  The intersects method
	//				return true if the two rectangles overlap, false if they do not.
	// return: A Rectangle - This rectangle would be like drawing a rectangle around the Character's image.
	public Rectangle getBounds(){
		return new Rectangle((int)x_coordinate, (int)y_coordinate, image.getIconWidth(), image.getIconHeight());
	}
	
	// method: getX
	// description:  This method will return the x-coordinate of the top left hand corner of the the image.
	// return: int - the x-coordinate of the top left hand corner of the the image.
	public double getX(){
		return x_coordinate;
	}
	
	// method: getY
	// description:  This method will return the y-coordinate of the top left hand corner of the the image.
	// return: int - the y-coordinate of the top left hand corner of the the image.
	public double getY(){
		return y_coordinate;
	}
	
	public void setY(double y_coordinate){
		this.y_coordinate = y_coordinate;
	}
	
	public void setX(double x_coordinate){
		this.x_coordinate = x_coordinate;
	}
	
	public void setChoice(int imageChoice){
		ClassLoader cldr = this.getClass().getClassLoader();	// These eight lines of code load the Character's png
		String imagePath;										// so that it later be painted on the graphics panel
		// when draw method is called.  You should modify															// the imagePath if you change the Character's 
		if(imageChoice == 0){	
			imageVal =0;														// if statement that determines which image to use for		
			imagePath = "images/player2.png";					    // a Character object.  You can add other options as well.
		}else if(imageChoice == 1){
			imageVal=1;
			imagePath = "images/player2L.png";
		}else if(imageChoice == 2){
			imageVal =2;
			imagePath = "images/player2R.png";
		}
		else if(imageChoice == 3){
			imageVal =3;
			imagePath="images/player2D.png";
		}
		else if(imageChoice == 4){
			imageVal =4;
			imagePath="images/zombie.png";
		}
		else if(imageChoice == 5){
			imageVal =5;
			imagePath="images/ZombieRight.png";
		}
		else if(imageChoice == 6){
			imageVal =6;
			imagePath="images/ZombieLeft.png";
		}
		else if(imageChoice == 7){
			imageVal = 7;
			imagePath="images/ZombieDown.png";
		}
		else if(imageChoice == 8){
			imageVal = 8;
			imagePath="images/ZombieUshot.png";
		}
		else if(imageChoice == 9){
			imageVal = 9;
			imagePath="images/ZombieRshot.png";
		}
		else if(imageChoice == 10){
			imageVal = 10;
			imagePath="images/ZombieLshot.png";
		}
		else if(imageChoice == 11){
			imageVal = 11;
			imagePath="images/ZombieDshot.png";
		}
		else{
			imageVal = -1;
			imagePath= "images/UFO.png";
		}
		URL imageURL = cldr.getResource(imagePath);				
		image = new ImageIcon(imageURL);
	}
	
	
	public int getChoice(){
		return imageVal;
	}
	
	public int getDirection(){
		return Direction;
	}
	// method: keyPressedMove
	// description: This method should modify the Character's x or y (or perhaps both) coordinates.  When the 
	//				graphics panel is repainted the Character will then be drawn in it's new location.
	// parameters: int direction - This parameter should represent the direction that you want to move
	//			   the Character, so decide on a standard for what each integer value will stand for and then
	//			   add comments below that describe these integer values, for example...
	//			   1 - move Character to the right.
	public void keyPressedMove(int direction){
		if(direction == 0)
			x_coordinate -= 1;
		else if(direction == 1)
			x_coordinate += 1;
		else if(direction == 2)
			y_coordinate -= 1;
		else if(direction == 3)
			y_coordinate += 1;
	}
	
	// method: timerMove
	// description: This method should modify the Character's x or y (or perhaps both) coordinates.  When the 
	//				graphics panel is repainted the Character will then be drawn in it's new location.
	// parameters: int direction - This parameter should represent the direction that you want to move
	//			   the Character, so decide on a standard for what each integer value will stand for and then
	//			   add comments below that describe these integer values, for example...
	//			   1 - move Character to the right.
	public void timerMove(){
		double differenceX=Math.abs(this.getX()-450);
		double differenceY=Math.abs(this.getY()-350);
		ClassLoader cldr = this.getClass().getClassLoader();	// These eight lines of code load the Character's png
		String imagePath = "images/zombie.png";
		
//		System.out.println(differenceX);
//		System.out.println(differenceY);
		//sfda
		
		if(this.x_coordinate<=450&&differenceX<2450&&differenceY<2450){
			this.x_coordinate+= speed;
			Direction = 1;
			imagePath = "images/ZombieRight.png";
		}
		if(this.x_coordinate>=470&&differenceX<1950&&differenceY<1950){
			this.x_coordinate-=speed;
			Direction = 2;
			imagePath = "images/ZombieLeft.png";
		}
		if(this.y_coordinate<320&&differenceX<2850&&differenceY<2850){
			this.y_coordinate+=speed;
			Direction = 3;
			imagePath = "images/ZombieDown.png";
		}
		if(this.y_coordinate>330&&differenceX<3900&&differenceY<3900){
			this.y_coordinate-=speed;
			Direction =4;
			imagePath = "images/zombie.png";
		}
		
		URL imageURL = cldr.getResource(imagePath);				
		image = new ImageIcon(imageURL);
	}
	
	// method: draw
	// description: This method is used to draw the image onto the GraphicsPanel.  You shouldn't need to 
	//				modify this method.
	// parameters: Graphics g - this object draw's the image.
	//			   Component c - this is the component that the image will be drawn onto.
	public void draw(Graphics g, Component c) {
        image.paintIcon(c, g, (int)x_coordinate, (int)y_coordinate);
    }
}
