package main.java;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.AbstractButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.Component;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

public class Gui extends JFrame implements ItemListener,ActionListener,DocumentListener{

	private BackgroundPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel flabel;
	private JRadioButton fradiobutton;
	private JLabel clabel;
	private JRadioButton cradiobutton;
	private Component horizontalGlue;
	private Component horizontalStrut_1;
	private JButton removelocationbutton;
	private JComboBox mylocationcomboBox;
	private JLabel mylocationslabel;
	private Component horizontalStrut;
	private JButton searchbutton;
	private JLabel searchlabel;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JButton addlocationbutton;
	private JMenu view;
	private LinkedList<JCheckBoxMenuItem> prefsmenu;
	private WeatherPreferences currentprefs;
	private LinkedList<LocationV1> locations;
	private AbstractButton status_l;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		locations = new LinkedList<LocationV1>();
		currentprefs = new WeatherPreferences();
		initGuiComponents(currentprefs);
		addActionListeners();
	}
	private void initGuiComponents(WeatherPreferences prefs){
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 800);
		
		menuBar = new JMenuBar();
		view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);
        
		prefsmenu = new LinkedList<JCheckBoxMenuItem>();
				
		JCheckBoxMenuItem item = new JCheckBoxMenuItem("Wind Speed", prefs.getShowWindSpeed());
		prefsmenu.add(item);
			
		item = new JCheckBoxMenuItem("Wind Direction", prefs.getShowWindDirection());
		prefsmenu.add(item);
		
		item = new JCheckBoxMenuItem("Sunrise", prefs.getShowSunrise());
		prefsmenu.add(item);
		
		item = new JCheckBoxMenuItem("Sunset", prefs.getShowSunset());
		prefsmenu.add(item);
		
		item = new JCheckBoxMenuItem("Air Pressure", prefs.getShowPressure());
		prefsmenu.add(item);
			
		item = new JCheckBoxMenuItem("Humidity", prefs.getShowHumidity());
		prefsmenu.add(item); 
		
		
		menuBar.add(view);
		setJMenuBar(menuBar);
		
		
		BufferedImage img = null;
		try {
			 img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("./resources/default.jpg"));
			// System.out.println("File " + img.toString());
		} catch (Exception e) {
			System.out.println("Cannot read file: " + e);
		}
		
		contentPane = new BackgroundPanel(img, BackgroundPanel.SCALED, 0.50f, 0.5f);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		searchlabel = new JLabel("Find your forecast:");
		toolBar.add(searchlabel);
		
		textField = new JTextField();
		textField.setMaximumSize(new Dimension(125, 30));
		toolBar.add(textField);
		textField.setColumns(10);
		textField.setActionCommand("Search");
		
		searchbutton = new JButton("Search");
		searchbutton.setMnemonic(KeyEvent.VK_ENTER);
		searchbutton.setActionCommand("Search");
		toolBar.add(searchbutton);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setMaximumSize(new Dimension(50, 32767));
		toolBar.add(horizontalStrut);
		
		mylocationslabel = new JLabel("My Locations");
		mylocationslabel.setFont(UIManager.getFont("ToolBar.font"));
		toolBar.add(mylocationslabel);
		
		mylocationcomboBox = new JComboBox();
		mylocationcomboBox.setPreferredSize(new Dimension(150, 30));
		mylocationcomboBox.setMaximumSize(new Dimension(150, 30));
		toolBar.add(mylocationcomboBox);
		
		addlocationbutton = new JButton("+");
		addlocationbutton.setActionCommand("add");
		toolBar.add(addlocationbutton);
		
		removelocationbutton = new JButton("-");
		removelocationbutton.setActionCommand("remove");
		toolBar.add(removelocationbutton);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		
		horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		
	    cradiobutton = new JRadioButton("\u00b0" + "C");
		cradiobutton.setSelected(true);
		cradiobutton.setActionCommand("Celcius");
		toolBar.add(cradiobutton);
		
		clabel = new JLabel("");
		toolBar.add(clabel);
		
		fradiobutton = new JRadioButton("\u00b0" + "F");
		fradiobutton.setSelected(false);
		fradiobutton.setActionCommand("Fahrenheit");
		toolBar.add(fradiobutton);
		
		flabel = new JLabel("");
		toolBar.add(flabel);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(panel);
		contentPane.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(1024,900));
		scrollPane.setViewportView(panel);
		this.getRootPane().setDefaultButton(searchbutton);
		
	}
	private void addActionListeners(){
		textField.getDocument().addDocumentListener(this);
		searchbutton.addActionListener(this);
        cradiobutton.addActionListener(this);
        fradiobutton.addActionListener(this);
        Iterator<JCheckBoxMenuItem> iter = prefsmenu.iterator();
        while (iter.hasNext()){
    		JCheckBoxMenuItem item = (JCheckBoxMenuItem) iter.next();
    		item.addItemListener(this);
    		view.add(item);
        }
	}
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
			Iterator<LocationV1> iter = locations.iterator();
			while (iter.hasNext()) {
				LocationV1 current = ((LocationV1) iter.next());
				System.out.println(current.getLocationName());
				current.updateViewPreferences(currentprefs);	
			}
		}
		pack();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Search".equals(e.getActionCommand())){
			String check = getQuery();
			System.out.println(check);
			   if (check!=null){
				   	   InputTest test = new InputTest(check);
				   	   if (test.getValid()){
				   		   LocationV1 loc = new LocationV1(new WeatherData(test),currentprefs);
				   		   if (shouldaddLocation(loc)==true){
				   			   locations.add(loc);
				   			   panel.add(loc);
				   		   }
				   	   }
				   	   else{
				   		//status_l.setText("Please check your spelling and try again..");   
				   	   }
			   } else {
			   //status_l.setText("Enter a city to check the weather");
			   }
		pack();
		}
		if ("Celcius".equals(e.getActionCommand())){
		        cradiobutton.setEnabled(false);
				fradiobutton.setSelected(false);
				fradiobutton.setEnabled(true);
		    	currentprefs.setTempUnit("C");
		    	Iterator<LocationV1> iter = locations.listIterator();
		    	while (iter.hasNext()){
		    		((LocationV1) iter.next()).refresh(currentprefs);
		    	}
		    	System.out.println("Celcius");
		    	
		    	
		}
		if ("Fahrenheit".equals(e.getActionCommand())){
			   cradiobutton.setSelected(false);
			   fradiobutton.setEnabled(false);
			   cradiobutton.setEnabled(true);
			   currentprefs.setTempUnit("F");
			   
			   Iterator iter = locations.listIterator();
		    	while (iter.hasNext()){
		    		((LocationV1) iter.next()).refresh(currentprefs);
		    	}
		        System.out.println("fahrenheit");
			    
		}
	}
	public boolean shouldaddLocation(LocationV1 loc){
		if (locations.isEmpty()){
			return true;
		}
		else{
			Iterator<LocationV1> lociter = locations.listIterator();
			boolean alreadyexists = false;
			while (lociter.hasNext()){
				LocationV1 temploc = (LocationV1)lociter.next();
				System.out.println(loc.getLocationName());
				if (temploc.getLocationName().equalsIgnoreCase(loc.getLocationName())){
					alreadyexists = true;
				}
			}	
			if (alreadyexists==true){
				return false;
				
			}
		return true;
		}
	}
	
	private String getQuery() {
		String prompted = textField.getText();
		if (prompted.length() <= 0) {
            textField.setText("Enter a valid location");
            return null;
        }
		return prompted;
		
	}

}

