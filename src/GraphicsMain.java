import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphicsMain window = new GraphicsMain();
	    JPanel p = new JPanel();
	    p.add(new GraphicsPanel()); 
	    window.setTitle("Lost in Space!");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setContentPane(p);
	    window.pack();
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);
	}
	}

}
