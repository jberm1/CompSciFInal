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
import java.awt.Dimension;
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
public class GraphicsPanel extends JPanel implements KeyListener{
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

	private ArrayList<Weapon> bullets;

	public GraphicsPanel()
	{
		moving = false;
		speed = 1;
		direction = 2;
		background_y = 0;
		background_x = 0;
		enemies = new ArrayList<>();
		attack=false;
		trees=new ArrayList<>();
		for(int i=0;i<100;i++){

			trees.add(new Character(5,(int)(Math.random()*4000)+500,(int)(Math.random()*4000)+1,0,50));
		}


		bullets = new ArrayList<>();

		setPreferredSize(new Dimension(1024,700));   // Set these dimensions to the width 
		// of your background picture.   
		player = new Character(0, 450, 288,1,100);
		//zombie = new Character(1, 450, 50);

		t = new Timer(5, new ClockListener(this));   // t is a timer.  This object will call the ClockListener's
		// action performed method every 5 milliseconds once the 
		enemies.add(new Character(3, 1150, 50,1.5,100));											 // timer is started. You can change how frequently this
		enemies.add(new Character(3, 500, 1500,1,50));												 // method is called by changing the first parameter.

		player.setHealth(100);

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


		player.draw(g2, this);
		for(Weapon s : bullets){
			s.draw(g2, this);
		}
		for(Character e : enemies){
			e.draw(g2, this);
		}
		for(Character t:trees){
			t.draw(g2, this);
		}
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

		if(moving && speed < 2){
			speed += .05;

		}
		else if(moving == false && speed > 0){
			speed -= .05;
		}



		if(timeCount%2 == 0){
			for(Character zomb:enemies){
				zomb.timerMove();
			}
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
		
//		for(int i =  bullets.size(); i > 0; i--){
//			if((bullets.get(i).getX() < 0 || bullets.get(i).getX() > 5000)){
//				bullets.remove(bullets.get(i));
//			}
		//}
		for(Character tree:trees){
			if(player.getBounds().intersects(tree.getBounds())){
				touch = true;
			}


			
			
			this.repaint();
		}
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
		case KeyEvent.VK_SPACE:
			bullets.add(new Weapon(30, 500, 288,1,bDirection));
			break;
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
		}
		//speed = 0;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
