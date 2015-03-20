package main.java;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel {
	
	
	    public TransparentPanel()
	    {
	        super() ;
	        this.setOpaque( false ) ; // this will make the JPanel transparent 
	                                  // but not its components (JLabel, TextField etc.)
	        this.setLayout( null ) ;
	    }
}

