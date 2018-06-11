// Class: GraphicsPanel
// Written by: Mr. Swope
// Date: 10/28/15
// Description: This class is the main class for this project.  It extends the Jpanel class and will be drawn on
// 				on the JPanel in the GraphicsMain class.  Your project should have at least one character that moves
//				with the arrow keys and one character that moves with the clock.  Finally, you should detect if the
//				two items intersect and have something happen if they do intersect.
//
// Since you will modify this class you should add comments that describe when and how you modified the class.  
//////
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.stream.events.Comment;
//
public class GraphicsPanel extends JPanel  implements KeyListener{
	//test
	private Timer t;								 // The timer is used to move objects at a consistent time interval.
	private Character player;					 // A spaceship
	private Character zombie;						     // A zombie 
	private double background_y;
	private double background_x;
	public int direction;
	public double speed;
	public boolean moving;
	private ArrayList<Character> enemies;
	private ArrayList<Character> trees;
	private double timeCount;
	private boolean attack;
	public boolean touch;
	public int bDirection;
	public int ammo;
	private int difficulty;
	private int round;
	private boolean change;
	AudioClip shot, walk, reload;

	private ArrayList<Weapon> bullets;

	public GraphicsPanel()
	{
		change = false;
		round = 0;
		ammo = 12;
		moving = false;
		speed = 1;
		bDirection = 2;
		direction = 2;
		background_y = 0;
		background_x = 0;
		enemies = new ArrayList<>();
		attack=false;
		trees=new ArrayList<>();
		difficulty=5;
		for(int i=0;i<100;i++){

			trees.add(new Character(20,(int)(Math.random()*4000)+500,(int)(Math.random()*4000)+1,0,50));
		}


		bullets = new ArrayList<>();

		setPreferredSize(new Dimension(1024,700));   // Set these dimensions to the width 
		// of your background picture.   
		player = new Character(0, 450, 288,1,100);
		//zombie = new Character(1, 450, 50);

		t = new Timer(5, new ClockListener(this));   // t is a timer.  This object will call the ClockListener's
		// action performed method every 5 milliseconds once the 
		// method is called by changing the first parameter.



		t.start();
		this.setFocusable(true);																		 // for keylistener
		this.addKeyListener(this);
	}

	// method: paintComponent
	// description: This method will paint the items onto the graphics panel.  This method is called when the panel is
	//   			first rendered.  It can also be called by this.repaint()
	// parameters: Graphics g - This object is used to draw your images onto the graphics panel.
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		//test
		ClassLoader cldr = this.getClass().getClassLoader();	// These five lines of code load the background picture.
		String imagePath = "images/Background.png";			// Change this line if you want to use a different 
		URL imageURL = cldr.getResource(imagePath);				// background image.  The image should be saved in the
		ImageIcon image = new ImageIcon(imageURL);				// images directory.
		image.paintIcon(this, g2, 0, 0);

		ImageIcon background1Image = new ImageIcon(imageURL);	// Two ImageIcon's are used to scroll the background.
		background1Image.paintIcon(this, g2, (int)background_x, (int)background_y);
		ImageIcon background2Image = new ImageIcon(imageURL);
		background2Image.paintIcon(this, g2, (int)background_x, (int)background_y-700);

		
		//if(player.getBounds().intersects(zombie.getBounds())){	// This code will detect if the pirate and parrot have
		// collided.  Make something happen if they do intersect.
		//	}

		for(Weapon s : bullets){
			s.draw(g2, this);
		}

		player.draw(g2, this);

		for(Character e : enemies){
			e.draw(g2, this);
		}
		for(Character t:trees){
			t.draw(g2, this);
		}

		g2.setFont(new Font("Zapfino", 0, 30));
		g2.setColor(Color.WHITE);
		g2.drawString(" " + (int)ammo + "/12", 820, 690);
		g2.setColor(Color.RED);
		g2.drawString(" "+enemies.size() + " Zombies",0 , 690);
		if(ammo <= 4){
		g2.setColor(Color.WHITE);	
		g2.setFont(new Font("Zapfino", 0, 25));
		g2.drawString("R To Reload",400 , 690);
		}
		
		
		if(change == true){
			ClassLoader cldr1 = this.getClass().getClassLoader();	// These five lines of code load the background picture.
			String imagePath1 = "images/blood.png";			// Change this line if you want to use a different 
			URL imageURL1 = cldr1.getResource(imagePath1);				// background image.  The image should be saved in the
			ImageIcon image1 = new ImageIcon(imageURL1);				// images directory.
			image1.paintIcon(this, g2, 255,240);
			g2.setColor(Color.RED);	
			g2.setFont(new Font("Zapfino", 0, 60));
			g2.drawString("WAVE " + round, 270, 360);
		}
		ClassLoader cldr1 = this.getClass().getClassLoader();	// These five lines of code load the background picture.
		String imagePath1 = "images/ammo1.png";			// Change this line if you want to use a different 
		URL imageURL1 = cldr1.getResource(imagePath1);				// background image.  The image should be saved in the
		ImageIcon image1 = new ImageIcon(imageURL1);				// images directory.
		image1.paintIcon(this, g2, 800,635);

	
	}

	// method:clock
	// description: This method is called by the clocklistener every 5 milliseconds.  You should update the coordinates
	//				of one of your characters in this method so that it moves as time changes.  After you update the
	//				coordinates you should repaint the panel.
	public void clock(){
		timeCount += 1;



		if(direction == 0 && background_x <= 0 && player.getX() == 450){
			background_x += speed;
			for(Character i:enemies){
				i.setX((int)(i.getX() + speed));
			}
			for(Character t: trees){
				t.setX( (t.getX() + speed));
			}
			for(Weapon s: bullets){
				s.setX(s.getX() + speed);
			}
		}else if(direction == 1 && background_x > -3954 && player.getX() == 450){
			background_x -= speed;
			for(Character i:enemies){
				i.setX((int)(i.getX() - speed));
			}
			for(Character t: trees){
				t.setX((t.getX() - speed));
			}
			for(Weapon s: bullets){
				s.setX(s.getX() - speed);
			}
		}else if(direction == 2  && background_y < 685 && player.getY() == 288){
			background_y += speed;
			for(Character i:enemies){
				i.setY((i.getY() + speed));
			}
			for(Character t: trees){
				t.setY((t.getY() + speed));
			}
			for(Weapon s: bullets){
				s.setY(s.getY() + speed);
			}
		}else if(direction == 3  && background_y > -3584 && player.getY() == 288){
			background_y -= speed;
			for(Character i:enemies){
				i.setY((int)(i.getY() - speed));
			}
			for(Character t: trees){
				t.setY((t.getY() - speed));
			}
			for(Weapon s: bullets){
				s.setY(s.getY() - speed);
			}
		}else{
			player.keyPressedMove(direction);
		}


		for(Weapon s: bullets){
			if(s.getSpeed()>0)
				s.setSpeed(s.getSpeed()*.98);
		}

		if(moving && speed < 2){
			speed += .5;

		}
		else if(moving == false && speed > 0){
			speed -= .05;
		}

		for(int i=bullets.size()-1;i>=0;i--){
			if(bullets.get(i).getSpeed()<=1){

				bullets.remove(i);
			}
		}


		for(int i=enemies.size()-1;i>=0;i--){
			if(enemies.get(i).getHealth()<=0){
				enemies.remove(i);
			}
		}
		if(enemies.size()==0&&timeCount%1000==0){
			round++;
			change = true;
			for(int i=0;i<difficulty;i++){
				enemies.add(new Character(20,(int)(((Math.random()*4000)+500) + background_x),(int)(((Math.random()*4000)+1)+background_y),((Math.random()*22)/10),78));		
			}
			difficulty+=(int)(Math.random()*5)+1;

		}

		if(timeCount%1500==0 && change == true){
			change = false;
		}

		//		for(int o=bullets.size()-1;o>0;o--){
		//			for(int n=enemies.size()-1;n>1;n--){
		//				if(bullets.get(o).getBounds().intersects(enemies.get(0).getBounds())){
		//					enemies.get(0).setHealth(enemies.get(0).getHealth()-15);
		//					System.out.println(enemies.get(o).getHealth());
		//
		//				}
		//				if(bullets.get(o).getBounds().intersects(enemies.get(n).getBounds())){
		////					bullets.remove(o);
		//					enemies.get(o).setHealth(enemies.get(o).getHealth()-15);
		//					System.out.println(enemies.get(o).getHealth());
		//				}
		//			}
		//		}




		if(timeCount%2 == 0){
			for(Character zomb:enemies){
				zomb.timerMove();
			}
		}

		for(Weapon b:bullets){
			for(Character c:enemies){
				if(b.getBounds().intersects(c.getBounds())){
					c.setHealth(c.getHealth()-15);
					b.setSpeed(0);
					if(c.getDirection() == 4){
						c.setChoice(8);
						this.repaint();
					}
					if(c.getDirection()==1){
						c.setChoice(9);
						this.repaint();
					}
					if(c.getDirection()==2){
						c.setChoice(10);
						this.repaint();
					}
					if(c.getDirection()==3){
						c.setChoice(11);
						this.repaint();
					}
					//					System.out.println(c.getChoice());
				}
			}
		}
		if(timeCount%100==0){
			System.out.println(enemies.size());
		}
		if(timeCount%100==0){
			for(Character zomb:enemies){
				if(attack==false&&player.getBounds().intersects(zomb.getBounds())){
					player.setHealth(player.getHealth()-15);
				}
			}
		}

		if(player.getHealth()<=0){
			//			System.out.println(player.getHealth());
		}

		for(Weapon s: bullets){
			s.shoot();
		}

		//				for(int i =  bullets.size(); i > 0; i--){
		//					if((bullets.get(i).getX() < 0 || bullets.get(i).getX() > 5000)){
		//						bullets.remove(bullets.get(i));
		//					}
		//		}
		for(Character tree:trees){
			if(player.getBounds().intersects(tree.getBounds())){

				switch(direction){

				case 0:
					moving=false;
					direction=-1;
					moving = true;
					direction = 1;
					bDirection = 1;
					player.setChoice(1);
					break;

				case 1:
					moving=false;
					direction=-1;
					moving = true;
					direction = 0;
					bDirection = 0;
					player.setChoice(2);
					break;


				case 2:
					moving=false;
					direction=-1;
					moving = true;
					direction = 3;
					bDirection = 3;
					player.setChoice(0);
					break;
				case 3:
					moving=false;
					direction=-1;
					moving = true;
					direction = 2;
					bDirection = 2;
					player.setChoice(3);
					break;
				}
			}
		}
		for(Character tree:trees){
			for(Character zomb:enemies){
				if(player.getBounds().intersects(tree.getBounds())){

					switch(direction){

					case 0:
						zomb.setSpeed(0);
						zomb.setSpeed(1);
						
						break;

					case 1:
						zomb.setSpeed(0);
						zomb.setSpeed(1);
						break;


					case 2:
						zomb.setSpeed(0);
						zomb.setSpeed(1);
						break;
					case 3:
						zomb.setSpeed(0);
						zomb.setSpeed(1);
						break;
					}
				}
			}
		}
		//t4etr




		//		for(Character c:enemies){
		//			if(c.getX() < player.getX() && (c.getY() == player.getY() +20 || c.getY() ==player.getY()-20) ){
		//				c.setChoice(5);
		//			}
		//			if((c.getX()== player.getX()+20 || c.getX() == player.getX()-20) && c.getY() < player.getY()){
		//				c.setChoice(7);
		//			}
		//		}


		this.repaint();

	}



	//this.repaint();

	//
	// method: keyPressed()
	// description: This method is called when a key is pressed. You can determine which key is pressed using the 
	//				KeyEvent object and .  For example if(e.getKeyCode() == KeyEvent.VK_LEFT) would test to see if
	//				the left key was pressed.
	// parameters: KeyEvent e
	@Override
	public void keyPressed(KeyEvent e) {

		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			moving = true;
			direction = 0;
			bDirection = 0;
			player.setChoice(1);
			break;
		case KeyEvent.VK_RIGHT:
			moving = true;
			direction = 1;
			bDirection = 1;
			player.setChoice(2);
			break;
		case KeyEvent.VK_UP:
			moving = true;
			direction = 2;
			bDirection = 2;
			player.setChoice(0);
			break;
		case KeyEvent.VK_DOWN:
			moving = true;
			direction = 3;
			bDirection = 3;
			player.setChoice(3);
			break;
			//		case KeyEvent.VK_SPACE:
			//			bullets.add(new Weapon(30, 500, 288,1,bDirection,5));
			//			break;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent h) {
		switch(h.getKeyCode()){
		case KeyEvent.VK_LEFT:
			moving = false;
			direction = -1;
			break;
		case KeyEvent.VK_RIGHT:
			moving = false;
			direction = -1;
			break;
		case KeyEvent.VK_UP:
			moving = false;
			direction = -1;
			break;
		case KeyEvent.VK_DOWN:
			moving = false;
			direction = -1;
			break;
		case KeyEvent.VK_SPACE:
			if(ammo >0 && player.getChoice() == 2){
				bullets.add(new Weapon(30, 500, 344,1,bDirection,10));
				ammo--;
			}else if(ammo >0 && player.getChoice() == 1){
				bullets.add(new Weapon(30, 500, 320,1,bDirection,10));
				ammo--;
			}else if(ammo >0 && player.getChoice() == 3){
				bullets.add(new Weapon(30, 478, 360,1,bDirection,10));
				ammo--;
			}
			break;
		case KeyEvent.VK_R:
			ammo =12;
			break;
		}
		//speed = 0;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyCode()){


		}
	}
}
