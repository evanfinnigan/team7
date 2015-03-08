package team7;

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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



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
    private JPanel locpane;

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
    	
    	//backround.add(tb);
    	
    	}
    	
	private void initMenu(WeatherPreferences p){
		// create preferences menu
	    menubar = new JMenuBar();
        view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);
        
		prefsmenu = new LinkedList<JCheckBoxMenuItem>();
		JCheckBoxMenuItem item = new JCheckBoxMenuItem("Temperature", p.getShowTemperature());
			item.setMnemonic(KeyEvent.VK_T);
		prefsmenu.add(item); 
			item = new JCheckBoxMenuItem("Temp High", p.getShowHigh());
			item.setMnemonic(KeyEvent.VK_H);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Temp Low", p.getShowLow());
			item.setMnemonic(KeyEvent.VK_L);
		prefsmenu.add(item);	
		    item = new JCheckBoxMenuItem("Wind Speed", p.getShowWindSpeed());
		    item.setMnemonic(KeyEvent.VK_W);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Wind Direction", p.getShowWindDirection());
			item.setMnemonic(KeyEvent.VK_D);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Icon", p.getShowIcon());
			item.setMnemonic(KeyEvent.VK_I);
		prefsmenu.add(item); 
			item = new JCheckBoxMenuItem("Sunrise", p.getShowSunrise());
			item.setMnemonic(KeyEvent.VK_R);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Sunset", p.getShowSunset());
			item.setMnemonic(KeyEvent.VK_S);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Air Pressure", p.getShowPressure());
			item.setMnemonic(KeyEvent.VK_A);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Humidity", p.getShowHumidity());
			item.setMnemonic(KeyEvent.VK_H);
		prefsmenu.add(item); 
			item = new JCheckBoxMenuItem("Sky Condition", p.getShowSky());
			item.setMnemonic(KeyEvent.VK_C);
		prefsmenu.add(item);
			item = new JCheckBoxMenuItem("Description", p.getShowDescription());
			item.setMnemonic(KeyEvent.VK_N);
		prefsmenu.add(item);
		
		menubar.add(view);
        setJMenuBar(menubar);
        
	}
	 private void createLayout(){
	    	GroupLayout layout = new GroupLayout(getContentPane());
	    	getContentPane().setLayout(layout);
	    	getContentPane().setSize(800, 600);
	    	//layout.setAutoCreateGaps(true);
	    	//layout.setAutoCreateContainerGaps(true);
	    	ParallelGroup hl = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	    	SequentialGroup vl = layout.createSequentialGroup();
	    	hl.addComponent(tb);
	    	hl.addComponent(locpane);
	    	vl.addComponent(tb);
	    	vl.addComponent(locpane);
	    	layout.setHorizontalGroup(hl);
	        layout.setVerticalGroup(vl);
	       
	    	
	    }
	private void initWindow(){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("WeatherDemo version 0.0");
		locpane = new JPanel();
	    initToolbarComponents();
	    System.out.println(this.getWidth());
    	initMenu(currentprefs);
    	addActionListeners();
    	createLayout();
    	pack();
	    
	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		Object source = ie.getItemSelectable();
		Iterator<JCheckBoxMenuItem> prefsiter = prefsmenu.iterator();
		boolean didChange = false;
		while (prefsiter.hasNext()){
			JCheckBoxMenuItem item = prefsiter.next();
			if (source==item){
				didChange = true;
				System.out.print(item.getState());
				if (item.getText().equals("Temperature")){
					
					currentprefs.setShowTemperature(item.getState());
				}
				if (item.getText().equals("Temp High")){
					currentprefs.setShowHigh(item.getState());
				}
				if (item.getText().equals("Temp Low")){
					currentprefs.setShowLow(item.getState());
				}
				if (item.getText().equals("Wind Speed")){
					currentprefs.setShowWindSpeed(item.getState());
				}
				if (item.getText().equals("Wind Direction")){
					currentprefs.setShowWindDirection(item.getState());
				}
				if (item.getText().equals("Icon")){
					currentprefs.setShowIcon(item.getState());
				}
				if (item.getText().equals("Sunrise")){
					currentprefs.setShowSunrise(item.getState());
				}
				if (item.getText().equals("Sunset")){
					currentprefs.setShowSunset(item.getState());
				}
				if (item.getText().equals("Sky Condition")){
					currentprefs.setShowSky(item.getState());
				}
				if (item.getText().equals("Air Pressure")){
					currentprefs.setShowPressure(item.getState());
				}
				if (item.getText().equals("Humidity")){
					currentprefs.setShowHumidity(item.getState());
				}
				if (item.getText().equals("Description")){
					currentprefs.setShowDescription(item.getState());
				}
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
					   locpane.add(loc.getPane());
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

