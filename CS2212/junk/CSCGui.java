import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class CSCGui {
	final static String PREFERENCEPANEL = "Preferences";
	final static String LOCATIONPANEL = "No City Selected";
	final static int    extraWindowWidth = 100;
	
	private void addtoPane(Container pane){
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel page1 = new JPanel() {
            //Make the page wider than it really needs, so
            //the window's wide enough for the tabs to stay
            //in one row.
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
        JButton b1 = new JButton("Submit");
        JTextField t1 = new JTextField("enter location", 25);
        page1.add(t1);
        page1.add(b1);
        
        JPanel page2 = new JPanel();
        page2.add(new JButton("Back"));
        
        tabbedPane.addTab(LOCATIONPANEL, page1);
        tabbedPane.addTab(PREFERENCEPANEL, page2);
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setActionCommand("submit");
      //  page1.addActionListener(this);
        b1.isDefaultButton();
        pane.add(tabbedPane, BorderLayout.CENTER);
	}
	
	private static void CSCcreateandshowGui() {
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame("Weather Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CSCGui demo = new CSCGui();
		demo.addtoPane(frame.getContentPane());
		
		//Display the window.
        frame.pack();
        frame.setVisible(true);
    
	}

	/*public static void main(String[] args) {
        // Use an appropriate Look and Feel 
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // Turn off metal's use of bold fonts 
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	CSCcreateandshowGui();
            }
            });
}*/
}
   

