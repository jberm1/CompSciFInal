import java.awt.event.ActionEvent;

public class ClockListener {

	GraphicsPanel f;
	
	ClockListener(GraphicsPanel c)
	{
		f = c;
	}
	
	// method: actionPerformed
	// description: This method is called every five milliseconds by the Timer.  It then calls the clock 
	//				method in the GraphicsPanel class.
	public void actionPerformed(ActionEvent e) {
            f.clock();
	}

}
