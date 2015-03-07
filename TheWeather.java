package team7;

import WeatherPrefs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import temporary.InputTest;
import temporary.WeatherData;

public class TheWeather extends JFrame implements ItemListener,ActionListener,DocumentListener{
	// menu for view
	private JMenuBar menubar;
	// menu item for menubar
	private JMenu view;
	// checkboxes for items in view menu
	private LinkedList<JCheckBoxMenuItem> prefsmenu;
	// linked list to store locations
	private LinkedList<Location> locations;
	// the degree symbol
	final private static String DEG = "\u00b0";
    // labels for the user message and status 
	private	JLabel usermsg_l,status_l;
	// button for find forecast
    private JButton search_b;
    // Convenience radio buttons to switch what units are displayed
    private JRadioButton degc_rb,degf_rb;
    // text field to gather user input
    private JTextField searchfield_tf;
    // button to check the weather on mars
    private JButton mars_b;
    // toolbar to hold buttons and labels and textfield
    private JToolBar tb;
    // user preferences 
    private WeatherPreferences currentprefs;
    private JLabel backround;
    
    public TheWeather(){
    	currentprefs = new WeatherPreferences();
    	locations = new LinkedList<Location>();
    	initWindow();
    	System.out.println("window init");
    	
    }
    
    private void addActionListeners(){
		searchfield_tf.getDocument().addDocumentListener(this);
		search_b.addActionListener(this);
        degc_rb.addActionListener(this);
        degf_rb.addActionListener(this);
        Iterator<JCheckBoxMenuItem> iter = prefsmenu.iterator();
        while (iter.hasNext()){
    		JCheckBoxMenuItem item = (JCheckBoxMenuItem) iter.next();
    		item.addItemListener(this);
    		view.add(item);
        }
	}
    private void initToolbarComponents(){
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
    	
    	
    	tb.add(usermsg_l);
    	tb.add(searchfield_tf);
    	tb.add(search_b);
    	tb.add(status_l);
    	tb.add(degc_rb);
    	tb.add(degf_rb);
    	tb.setFloatable(false);
    	backround.add(tb);
    	
    	}
    	
	private void initMenu(WeatherPrefs p){
		// create preferences menu
	    menubar = new JMenuBar();
        view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);
        
		prefsmenu = new LinkedList<JCheckBoxMenuItem>();
		JCheckBoxMenuItem item = new JCheckBoxMenuItem("Temperature", p.showTemperature());
			item.setMnemonic(KeyEvent.VK_T);
		prefsmenu.add(item); 
			item = new JCheckBoxMenuItem("Temp High", p.showHigh());
			item.setMnemonic(KeyEvent.VK_H);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Temp Low", p.showLow());
			item.setMnemonic(KeyEvent.VK_L);
		prefsmenu.add(item);	
		    item = new JCheckBoxMenuItem("Wind speed", p.showWindSpeed());
		    item.setMnemonic(KeyEvent.VK_W);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Wind direction", p.showWindDirection());
			item.setMnemonic(KeyEvent.VK_D);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Icon", p.showIcon());
			item.setMnemonic(KeyEvent.VK_I);
		prefsmenu.add(item); 
			item = new JCheckBoxMenuItem("Sunrise", p.showSunrise());
			item.setMnemonic(KeyEvent.VK_R);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Sunset", p.showSunset());
			item.setMnemonic(KeyEvent.VK_S);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Air Pressure", p.showPressure());
			item.setMnemonic(KeyEvent.VK_A);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Humidity", p.showHumidity());
			item.setMnemonic(KeyEvent.VK_H);
		prefsmenu.add(item); 
			item = new JCheckBoxMenuItem("Sky Condition", p.showSky());
			item.setMnemonic(KeyEvent.VK_C);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Description", p.showDescription());
			item.setMnemonic(KeyEvent.VK_N);
		prefsmenu.add(item);
		
		menubar.add(view);
        setJMenuBar(menubar);
        
	}
	private void initWindow(){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("WeatherDemo version 0.0");
		backround = new JLabel(new ImageIcon("Weather_day.jpg"));
		add(backround);
		setContentPane(backround);
		setLayout(new GridLayout(0,1));
		setSize(400,800);
	    initToolbarComponents();
    	System.out.println("toolbar init");
    	initMenu(currentprefs);
    	System.out.println("menu init");
    	addActionListeners();
    	System.out.println("adding actionlisteners");
    	
	    
	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		Object source = ie.getItemSelectable();
		Iterator<JCheckBoxMenuItem> prefsiter = prefsmenu.iterator();
		boolean didChange = false;
		while (prefsiter.hasNext()){
		if (source==prefsiter.next()){
			didChange = true;
		}
		}
		if (didChange==true){
			Iterator<Location> iter = locations.iterator();
			while (iter.hasNext()) {
				Location current= ((Location) iter.next());
				System.out.println(current.getName());
				current.updateViewPreferences(currentprefs);	
			}
		}
		pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Search".equals(e.getActionCommand())){
			String check = getQuery();
			System.out.println(check);
			   if (check!=null){
				   	   InputTest test = new InputTest(check);
				   	   if (test.getValid()){
					   Location loc = new Location(new WeatherData(test),currentprefs);
					   this.add(loc.getPane());
					   locations.add(loc);
			           }
				   	   else{
				   		status_l.setText("Please check your spelling and try again..");   
				   	   }
			   } else {
			   status_l.setText("Enter a city to check the weather");
			   }
		pack();
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
	public static void main(String[] args){
		 //Schedule a job for the event dispatch thread:
	       //creating and showing this application's GUI.
	       SwingUtilities.invokeLater(new Runnable() {
	               public void run() {
	                    //Turn off metal's use of bold fonts
	                    UIManager.put("swing.boldMetal", Boolean.FALSE);
	                    new TheWeather().setVisible(true);
	               }
	        });
		}	
}

