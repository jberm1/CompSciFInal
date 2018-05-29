// Class: GraphicsPanel
// Written by: Mr. Swope
// Date: 10/28/15
// Description: This class is the main class for this project.  It extends the Jpanel class and will be drawn on
// 				on the JPanel in the GraphicsMain class.  Your project should have at least one character that moves
//				with the arrow keys and one character that moves with the clock.  Finally, you should detect if the
//				two items intersect and have something happen if they do intersect.
//
// Since you will modify this class you should add comments that describe when and how you modified the class.  

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

public class GraphicsPanel extends JPanel implements KeyListener{
	
	private Timer t;								 // The timer is used to move objects at a consistent time interval.
	private Character player;					 // A spaceship
	private Character zombie;						     // A zombie 
	private int background_y;
	private int background_x;
	public int direction;
	
	public GraphicsPanel()
	{
        direction = -1;
		background_y = 0;
        background_x = 0;
		setPreferredSize(new Dimension(1024,700));   // Set these dimensions to the width 
        											 // of your background picture.   
		 player = new Character(0, 450, 550);
		zombie = new Character(1, 450, 50);
		
        t = new Timer(5, new ClockListener(this));   // t is a timer.  This object will call the ClockListener's
        											 // action performed method every 5 milliseconds once the 
        											 // timer is started. You can change how frequently this
        											 // method is called by changing the first parameter.
        t.start();
        this.setFocusable(true);					 // for keylistener
		this.addKeyListener(this);
	}
	
	// method: paintComponent
	// description: This method will paint the items onto the graphics panel.  This method is called when the panel is
	//   			first rendered.  It can also be called by this.repaint()
	// parameters: Graphics g - This object is used to draw your images onto the graphics panel.
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		ClassLoader cldr = this.getClass().getClassLoader();	// These five lines of code load the background picture.
		String imagePath = "images/backgroundtest.png";			// Change this line if you want to use a different 
		URL imageURL = cldr.getResource(imagePath);				// background image.  The image should be saved in the
		ImageIcon image = new ImageIcon(imageURL);				// images directory.
		image.paintIcon(this, g2, 0, 0);
		
		ImageIcon background1Image = new ImageIcon(imageURL);	// Two ImageIcon's are used to scroll the background.
		background1Image.paintIcon(this, g2, background_x, background_y);
		ImageIcon background2Image = new ImageIcon(imageURL);
		background2Image.paintIcon(this, g2, background_x, background_y-700);
		
		if(player.getBounds().intersects(zombie.getBounds())){	// This code will detect if the pirate and parrot have
																// collided.  Make something happen if they do intersect.
		}
		
		player.draw(g2, this);
		zombie.draw(g2, this);
		
	}
	
	// method:clock
	// description: This method is called by the clocklistener every 5 milliseconds.  You should update the coordinates
	//				of one of your characters in this method so that it moves as time changes.  After you update the
	//				coordinates you should repaint the panel.
	public void clock(){
		if(direction == 0)
			background_x += 1;
		else if(direction == 1)
			background_x -= 1;
		else if(direction == 2)
			background_y += 1;
		else if(direction == 3)
			background_y -= 1;
		this.repaint();
	}

	// method: keyPressed()
	// description: This method is called when a key is pressed. You can determine which key is pressed using the 
	//				KeyEvent object and .  For example if(e.getKeyCode() == KeyEvent.VK_LEFT) would test to see if
	//				the left key was pressed.
	// parameters: KeyEvent e
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				direction = 0;
				break;
			case KeyEvent.VK_RIGHT:
				direction = 1;
				break;
			case KeyEvent.VK_UP:
				direction = 2;
				break;
			case KeyEvent.VK_DOWN:
				direction = 3;
				break;
			//default:
				//direction = -1;
				//break;
		}
		//this.repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		direction = -1;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
