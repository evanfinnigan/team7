package team7;



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import temporary.InputTest;
import temporary.WeatherData;

public class SearchBar extends JToolBar implements DocumentListener,ActionListener{
	
	final private static String DEG = "\u00b0";
    private	JLabel usermsg_l,status_l;
    private JButton search_b;
    private JRadioButton degc_rb,degf_rb;
    private JTextField searchfield_tf;
    private JButton mars_b;
    private JToolBar tb;
    private WeatherPreferences currentprefs;
    
	public SearchBar(WeatherPreferences prefs){
		initComponents();
		addActionListeners();
		currentprefs = prefs;
	}
	private void addActionListeners(){
		searchfield_tf.getDocument().addDocumentListener(this);
		search_b.addActionListener(this);
        degc_rb.addActionListener(this);
        degf_rb.addActionListener(this);
	}
	private void initComponents(){
	status_l = new JLabel();	
	 // create a label to prompt the user for a location
	usermsg_l = new JLabel();
	usermsg_l.setText("Find Your Forecast:");
	// create a click-able button to begin the search
	search_b = new JButton("Search");
	search_b.setMnemonic(KeyEvent.VK_D);
	search_b.setActionCommand("Search");
	
	// create celcius button
	degc_rb = new JRadioButton( DEG+"C");
	degc_rb.setMnemonic(KeyEvent.VK_C);
	degc_rb.setActionCommand("Celcius");
	degc_rb.setSelected(true);
	
	// create Fahrenheit button
	degf_rb = new JRadioButton(DEG+"F");
	degf_rb.setMnemonic(KeyEvent.VK_F);
	degf_rb.setActionCommand("Fahrenheit");
	degf_rb.setSelected(false);
	
	mars_b = new JButton("Check The Weather on Mars");
	// create search text field		
	searchfield_tf = new JTextField();
	searchfield_tf.setMinimumSize(new Dimension(125,30));
	searchfield_tf.setMaximumSize(new Dimension(175,30));
	
	
	
	// create JToolBar
	tb = new JToolBar();
	// add components to toolbar
	//tb.addSeparator(new Dimension(100,1));
	tb.add(usermsg_l);
	tb.add(searchfield_tf);
	tb.add(search_b);
	//tb.addSeparator(new Dimension(100,1));
	tb.add(status_l);
	tb.add(degc_rb);
	tb.add(degf_rb);
	tb.setFloatable(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("Search".equals(e.getActionCommand())){
			String check = getQuery();
			   if (check!=null){
				   	   InputTest test = new InputTest(check);
				   	   if (test.getValid()){
					   Location loc = new Location(new WeatherData(test),currentprefs);
			           }
				   	   else{
				   		status_l.setText("Please check your spelling and try again..");   
				   	   }
			   }
			   status_l.setText("Enter a city to check the weather");
		}
		if ("Celcius".equals(e.getActionCommand())){
		    	degf_rb.setSelected(false);
		    	System.out.println("Celcius");
		    	
		}
		if ("Fahrenheit".equals(e.getActionCommand())){
			   degc_rb.setSelected(false);
			   System.out.println("fahrenheit");
		}
	}
	private String getQuery() {
		String prompted = searchfield_tf.getText();
		if (prompted.length() <= 0) {
            searchfield_tf.setText("Enter a valid location");
            return null;
        }
		return prompted;
		
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
