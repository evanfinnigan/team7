import gvjava.org.json.JSONArray;
import gvjava.org.json.JSONException;
import gvjava.org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.nio.charset.Charset;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class CSCWeatherbar extends JFrame implements DocumentListener,ActionListener{

// URL to open weathermap.org location query and icon query
final static String owmurl = "http://api.openweathermap.org/data/2.5/weather?q=";
final static String iconurl = "http://openweathermap.org/img/w/"; // not yet implemented

// character for the degree symbol
private static final String DEG = "\u00b0";

// button actions
final static String SUBMIT_ACTION = "Search";
final static String CELCIUS_ACTION = "Celcius";
final static String FAHRENHEIT_ACTION = "Fahrenheit";

// stores latest data from openweathermap.org for later use
private JSONObject weatherdata;

// JToolBar 
private JToolBar tb;
// label to prompt user for a location
private JLabel usermsg_l;
// text field to capture user input
private JTextField searchfield_tf;
// button to submit location search
private JButton search_b;
// radio buttons for degrees c and f
private JRadioButton degc_rb;
private JRadioButton degf_rb;

// text area to display open weather map data 
private JTextArea display_ta;
private JScrollPane jScrollPane1;

// image icon and jlabel to hold it
private ImageIcon icon_i;
private JLabel image_l;

// JLabels to display basic requirement data
private JLabel sunrise_l;
private JLabel sunset_l;
private JLabel weatherdescription_l;
private JLabel weatherdetails_l;
private JLabel windspeed_l;
private JLabel winddirection_l;
private JLabel temp_l;
private JLabel tempmin_l;
private JLabel tempmax_l;
private JLabel humidity_l;
private JLabel airpressure_l;

// for converting between celcius and fahrenheit
private double temperature;
private double temp_min;
private double temp_max;

	public CSCWeatherbar() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("WeatherDemo version 0.0");
        initComponents();
        createLayout();
        
        searchfield_tf.getDocument().addDocumentListener(this);
		search_b.addActionListener(this);
        degc_rb.addActionListener(this);
        degf_rb.addActionListener(this);
        
	}
	// sets up and initializes toolbar ui components
	
	public void initComponents() {
		
		// create txt area for output and debugging
		// to be removed
		display_ta = new JTextArea();
		display_ta.setColumns(50);
        display_ta.setLineWrap(true);
        display_ta.setRows(20);
        display_ta.setWrapStyleWord(false);
        display_ta.setEditable(false);
        jScrollPane1 = new JScrollPane(display_ta);
        
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
		tb.add(degc_rb);
		tb.add(degf_rb);
		tb.setFloatable(false);
		
		
		// create labels to display basic data
		sunrise_l = new JLabel("Sunrise:");
		sunset_l = new JLabel("Sunset:");
		windspeed_l = new JLabel("Wind Speed:");
		winddirection_l = new JLabel("Wind Direction");
		weatherdescription_l = new JLabel("Current Conditions:");
		weatherdetails_l = new JLabel("");
		temp_l = new JLabel("Current Temperature:");
		tempmax_l = new JLabel("High:");
		tempmin_l = new JLabel("Low:");
		humidity_l = new JLabel("Humidity:");
		airpressure_l = new JLabel("Air Pressure:");
		// for storing latest json data from openweathermap
		weatherdata = new JSONObject();
		// create image icon and label to display it
		icon_i = new ImageIcon();
		image_l = new JLabel(icon_i);
	}
	// shitty layout in swing
	// needs work...
	
	public void createLayout(){
		// create layout
		GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        // Create a parallel group for the horizontal axis
        // split into three sections top, middle and bottom
        ParallelGroup htop = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        htop.addComponent(tb);
        
        ParallelGroup hmiddle = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
      
        hmiddle.addComponent(temp_l);
        hmiddle.addComponent(sunrise_l);
        hmiddle.addComponent(sunset_l);
        hmiddle.addComponent(windspeed_l);
        hmiddle.addComponent(winddirection_l);
        hmiddle.addComponent(tempmin_l);
        hmiddle.addComponent(tempmax_l);
        hmiddle.addComponent(humidity_l);
        hmiddle.addComponent(airpressure_l);
        hmiddle.addComponent(weatherdescription_l);
        hmiddle.addComponent(weatherdetails_l);
        hmiddle.addComponent(image_l);  
        
        ParallelGroup hbottom = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        hbottom.addComponent(jScrollPane1, GroupLayout.Alignment.CENTER, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);  
        
        ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hGroup.addGroup(hmiddle);
        hGroup.addGroup(hbottom);
        hGroup.addGroup(htop);
        layout.setHorizontalGroup(hGroup);
        
        //Create a sequential group for the vertical axis
        SequentialGroup vGroup = layout.createSequentialGroup();
        SequentialGroup vtoolbar = layout.createSequentialGroup();
        SequentialGroup vlabels = layout.createSequentialGroup();
        SequentialGroup vtextarea = layout.createSequentialGroup();
        
        vtoolbar.addComponent(tb);
        
        vlabels.addComponent(temp_l);
        vlabels.addComponent(sunrise_l);
	    vlabels.addComponent(sunset_l);
        vlabels.addComponent(windspeed_l);
        vlabels.addComponent(winddirection_l);
        vlabels.addComponent(tempmin_l);
        vlabels.addComponent(tempmax_l);
        vlabels.addComponent(humidity_l);
        vlabels.addComponent(airpressure_l);
        vlabels.addComponent(weatherdescription_l);
        vlabels.addComponent(weatherdetails_l);
        vlabels.addComponent(image_l);
        
        vtextarea.addComponent(jScrollPane1);
        
        vGroup.addGap(5);
	    vGroup.addGroup(vtoolbar);
	    vGroup.addGap(15);
	    vGroup.addGroup(vlabels);
	    vGroup.addGap(15);
        vGroup.addGroup(vtextarea);
	    
	    layout.setVerticalGroup(vGroup);
	    pack();
}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("Search".equals(e.getActionCommand())){
			String check = getQuery();
			   if (check!=null) {
				   weatherdata = requestJson(check);
				   display_ta.setText(weatherdata.toString());
				   presentData(weatherdata);
			   }
		}
		if ("Celcius".equals(e.getActionCommand())){
		    	degf_rb.setSelected(false);
		    	System.out.println("Celcius");
		    	presentData(weatherdata);
		    	validate();
		    	
		}
		if ("Fahrenheit".equals(e.getActionCommand())){
			   degc_rb.setSelected(false);
			   System.out.println("fahrenheit");
			   presentData(weatherdata);
			   validate();
		}
		
		
		
	}
	
	// a method that wraps user input into a string and 
	// combines it with the url for open weather map city query
	// the url will be then be supplied to requestJSON which handles 
	// json request
	public String getQuery() {
		String prompted = searchfield_tf.getText();
		if (prompted.length() <= 0) {
            searchfield_tf.setText("Enter a valid location");
            return null;
        }
		String searchtoken = owmurl.concat(prompted);
		return searchtoken;
		
	}
	// takes a string url to openweathermap.org and returns a json object  
	public static JSONObject requestJson(String url) {
		// InputStream to open a connection to openweathermap
			InputStream stream;
		// the result of the query to openweathermap	
		JSONObject json;
		try {
			// using the supplied url 
			// open a connection to openweathermap 
			stream = new URL(url).openStream();
			// a buffered reader to parse data 
			BufferedReader rd = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
		    // pass the reader to helper method which 
			// casts integer data to characters and returns a string
			String jsondata = data2string(rd);
			json = new JSONObject(jsondata);
		    return json;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
					e.printStackTrace();
		}
				
			// if here uh-oh...
			return null;
				
	}
	// reads all characters from data stream and returns a string 
	private static String data2string(Reader rjson) throws IOException{
		StringBuilder jstring = new StringBuilder();
		int letter;
			try {
				while ((letter = rjson.read()) != -1){
					jstring.append((char)letter);
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return jstring.toString();
			}
	// way too messy needs to be split up into smaller modules
   public void presentData(JSONObject currentdata){
		
	      try {
			
			// display json data
	    	// values in a json object that can either be a JSONObject
	    	// or a JSONArray depending on the key:value pair
	    	  
	    	// jbomb stores the result from .getJSONObject(key) method
	    	// only basic error checking implemented...
	    	// sunrise and sunset and date/time info 
	    	// are found using the "sys" key  
	    	JSONObject jbomb = currentdata.getJSONObject("sys");
	    	int srise = jbomb.getInt("sunrise");
	    	int sset = jbomb.getInt("sunset");
	    	
	    	// Time conversion and date
	    	// to-do: clean this up
	    	Date date = new Date(srise*1000L); // *1000 is to convert seconds to milliseconds
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss z dd-MM-yy"); // the format of your date
	    	String formattedsunrise = sdf.format(date);
	    	System.out.println(formattedsunrise);
	        // set the sunrise label txt
	    	sunrise_l.setText("Sunrise:"+formattedsunrise);
	    	
	    	date = new Date(sset*1000L); // *1000 is to convert seconds to milliseconds
	        //sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
	    	//sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
	    	sdf = new SimpleDateFormat("HH:mm:ss z dd-MM-yy"); // the format of your date
	    	String formattedsunset = sdf.format(date);
	    	System.out.println(formattedsunset);
	    	// set the sunset label txt
	    	sunset_l.setText("Sunset:"+formattedsunset);
	    	
	    	// description of current conditions and sky details
	    	JSONArray conditions = currentdata.getJSONArray("weather");
	    	jbomb = (JSONObject) conditions.get(0);
	    	String description = jbomb.getString("main");
	    	String details = jbomb.getString("description");
	        
	    	// get and display icon from openweathermap	
	    	String iconfilename = jbomb.getString("icon");
	    	BufferedImage img = ImageIO.read(new URL(iconurl+iconfilename+".png"));
	    	icon_i = new ImageIcon(img);
	    	image_l.setPreferredSize(new Dimension(icon_i.getIconWidth(), icon_i.getIconHeight()));
		    image_l.setIcon(icon_i);
	    	
	        // set the weather description and detail labels txt
	    	weatherdescription_l.setText("Current Conditions:"+description);
	    	weatherdetails_l.setText("Sky Condition:"+details);
	    	
	    	// wind info found using "wind" key
	        jbomb = currentdata.getJSONObject("wind");
			double winds = jbomb.getDouble("speed");
			double windd = jbomb.getDouble("deg");
			// set wind speed and direction labels
			// to-do change degrees into compass heading like North,South,etc...
			windspeed_l.setText("Wind Speed:"+  Double.toString(winds) );
			winddirection_l.setText("Wind Direction:"+ Double.toString(windd));
			
			// temp, air pressure, humidity values are found using "main" key 
			jbomb = currentdata.getJSONObject("main");
			
			// use a radiobutton to toggle converting from c to f from k
			// k to c -272.5 k to f -457.87
			// currently converted to celcius by default
			temperature = (jbomb.getDouble("temp") - 273.15);
			temp_max = (jbomb.getDouble("temp_max") - 273.15);
			temp_min = (jbomb.getDouble("temp_min") - 273.15);
			// clean up formating
			DecimalFormat dub2 = new DecimalFormat("#.##");
			String symbol = "C";
			// check which radio button is selected
			// convert if necessary
			if (degf_rb.isSelected()){
				temperature = convertctof(temperature);
				temp_min = convertctof(temp_min);
				temp_max = convertctof(temp_max);
				symbol = "F";
			}
			
			// set temp,tempmin,tempmax labels text
			temp_l.setText("Temperature Current:" + dub2.format(temperature) +DEG+symbol);
			tempmin_l.setText("Temperature Low:" + dub2.format(temp_min) +DEG+symbol);
			tempmax_l.setText("Temperature High:" + dub2.format(temp_max) +DEG+symbol);
			// set humidity, and airpressure labels text
			double humid = jbomb.getDouble("humidity");
			double pressure = jbomb.getDouble("pressure");
			humidity_l.setText("Humidity:" + Double.toString(humid));
			airpressure_l.setText("Air Pressue:" + Double.toString(pressure));
			
			// out to console --> to debug unit conversion 
			System.out.printf("wind speed: %.2f%n", winds);
			System.out.printf("wind direction: %.3f%n", windd);
			System.out.printf("temp: %.2f"+DEG+"c%n", temperature);
			System.out.printf("temp_max: %.2f"+DEG+"c%n", temp_max);
			System.out.printf("temp_min: %.2f"+DEG+"c%n", temp_min);
			System.out.printf("humidity: %.2f%n", humid);
			System.out.printf("air pressure: %.2f%n", pressure);
			System.out.printf("sunrise %s%n",formattedsunrise);
			System.out.printf("sunset %s%n",formattedsunset);
			System.out.printf("Current conditions: %s%n",description);
			System.out.printf("Details: %s%n",details);
			
			
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
   public static void main(String[] args) {
		
	   //Schedule a job for the event dispatch thread:
       //creating and showing this application's GUI.
       SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                    //Turn off metal's use of bold fonts
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    new CSCWeatherbar().setVisible(true);
               }
        });
	}	
    
    // helper method to convert celcius to fahrenheit
    public double convertctof(double temp){
		double n = ((temp*1.8)+32);
		return n;
	}
    // helper method to convert fahrenheit to celcius
	public double convertftoc(double temp){
		double n = ((temp-32)/1.8);
		return n;
	}
	
	// methods for documentListener due to searchbar textfield
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
	// not used --- was testing AbstractActions
	class search_bAction extends AbstractAction{
			public void actionPerformed(ActionEvent ev) {
				
				JSONObject data = requestJson(getQuery());
	            display_ta.setText(data.toString());
	        }
		}
}
