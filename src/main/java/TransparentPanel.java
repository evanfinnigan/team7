package main.java;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;
/**
 * Creates a Transparent JPanel
 *
 */
public class TransparentPanel extends JPanel {
	
		/**
		 * Constructor for the TransparentPanel
		 */
	    public TransparentPanel()
	    {
	        super() ;
	        this.setOpaque( false ) ; // this will make the JPanel transparent 
	                                  // but not its components (JLabel, TextField etc.)
	        this.setLayout( null ) ;
	    }
	    /**
	     * Overrides paintComponentMethod
	     */
	    public void paintComponent(Graphics g) {
	        
	        //in the constructor after declaring the frame undecorated
	          super.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	          super.paintComponent(g);
	      }
}

