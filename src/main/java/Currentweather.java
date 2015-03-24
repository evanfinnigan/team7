/*package main.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;// used in the test to be removed
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;



public class Currentweather {

	private WeatherData currentData;
	private WeatherPreferences currentPrefs;
	private TransparentPanel pane;
	private JLabel image_l;
	private JLabel sunrise_l;
	private JLabel sunset_l;
	private JLabel weatherdescription_l;
	private JLabel skycondition_l;
	private JLabel windspeed_l;
	private JLabel winddirection_l;
	private JLabel temp_l;
	private JLabel tempmin_l;
	private JLabel tempmax_l;
	private JLabel humidity_l;
	private JLabel airpressure_l;

	public Currentweather(WeatherData data, WeatherPreferences prefs){
		this.currentData = data;
		this.currentPrefs = prefs;
		initComponents();
		creategridLayout();
		
		//createLayout();
		applyPrefs(prefs);
		
	}
	public TransparentPanel getPanel() {
		return pane;
	}

	// helper method to convert celcius to fahrenheit
    private double convertctof(double temp){
		double n = ((temp*1.8)+32);
		return n;
	}

	private void initComponents() {
		// create fonts for text
		Font lrgfont = new Font("lrg", Font.PLAIN, 24);
		Font medfont = new Font("med", Font.PLAIN, 15);
		Font smlfont = new Font("sml", Font.PLAIN, 13);
		// create labels to display basic data
		pane = new TransparentPanel(); 
		sunrise_l = new JLabel("Sunrise: " + currentData.getSunrise());
		sunrise_l.setFont(smlfont);
		sunset_l = new JLabel("Sunset: " + currentData.getSunset());
		sunset_l.setFont(smlfont);
		windspeed_l = new JLabel("Wind Speed: " + currentData.getWindSpeed());
		windspeed_l.setFont(smlfont);
		winddirection_l = new JLabel("Wind Direction: "
				+ currentData.getWindDirection());
		winddirection_l.setFont(smlfont);
		weatherdescription_l = new JLabel("" + currentData.getDescription());
		weatherdescription_l.setFont(smlfont);
		skycondition_l = new JLabel("" + currentData.getSkyConditionCurrent());
		skycondition_l.setFont(smlfont);
		temp_l = new JLabel(currentData.getTempCurrent() +"\u00b0" + currentPrefs.getTempUnit());
		temp_l.setFont(smlfont);
		tempmax_l = new JLabel("High: " + currentData.getHigh());
		tempmax_l.setFont(smlfont);
		//tempmax_l.setForeground(Color.RED);
		tempmin_l = new JLabel("Low: " + currentData.getLow());
		tempmin_l.setFont(smlfont);
		//tempmin_l.setForeground(Color.BLUE);
		humidity_l = new JLabel("Humidity: " + currentData.getHumidity() + "%");
		humidity_l.setFont(smlfont);
		airpressure_l = new JLabel("Air Pressure: " + currentData.getPressure());
		airpressure_l.setFont(smlfont);
		image_l = new JLabel(new ImageIcon(currentData.getIcon()));
		image_l.setPreferredSize(new Dimension(10,10));
		
	}
	
	private void creategridLayout(){
		GridLayout glayout = new GridLayout(0,6);
		pane.setLayout(glayout);
		
		pane.add(temp_l);
		pane.add(tempmin_l);
		pane.add(tempmax_l);
		pane.add(image_l);
		pane.add(skycondition_l);
		pane.add(weatherdescription_l);
		pane.add(windspeed_l);
		pane.add(winddirection_l);
		pane.add(humidity_l);
		pane.add(airpressure_l);
		pane.add(sunrise_l);
		pane.add(sunset_l);
		//pane.setBounds(0, 0, 800, 600);
		pane.validate();
		
		
	}
	
	
	public void applyPrefs(WeatherPreferences prefs){
		
		temp_l.setVisible(prefs.getShowTemperature());
		tempmin_l.setVisible(prefs.getShowLow());
	    tempmax_l.setVisible(prefs.getShowHigh());
		image_l.setVisible(prefs.getShowIcon()); 
		weatherdescription_l.setVisible(prefs.getShowDescription());
		skycondition_l.setVisible(prefs.getShowSky());
	    sunrise_l.setVisible(prefs.getShowSunrise());
	    sunset_l.setVisible(prefs.getShowSunset());
		windspeed_l.setVisible(prefs.getShowWindSpeed());
		winddirection_l.setVisible(prefs.getShowWindDirection());	
	    humidity_l.setVisible(prefs.getShowHumidity());
		airpressure_l.setVisible(prefs.getShowPressure());

	}



	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.setTitle("Weather for London, Canada");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		WeatherPreferences prefs = new WeatherPreferences();
		InputTest intest = new InputTest("London,ca");
		WeatherData data = new WeatherData(intest);
		System.out.println(data.getCityName());
		Currentweather test = new Currentweather(data, prefs);
		frame.getContentPane().add(test.getPanel());
		System.out.println("finished");
		frame.pack();
		frame.setVisible(true);
	}




}*/




////////
////////




package main.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;// used in the test to be removed
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;



public class Currentweather {

	private WeatherData currentData;
	private WeatherPreferences currentPrefs;
	private TransparentPanel pane;
	private JLabel image_l;
	private JLabel sunrise_l;
	private JLabel sunset_l;
	private JLabel weatherdescription_l;
	private JLabel skycondition_l;
	private JLabel windspeed_l;
	private JLabel winddirection_l;
	private JLabel temp_l;
	private JLabel tempmin_l;
	private JLabel tempmax_l;
	private JLabel humidity_l;
	private JLabel airpressure_l;
	private JLabel cityname;
	private JLabel lastupdate_l;

	public Currentweather(WeatherData data, WeatherPreferences prefs){
		this.currentData = data;
		this.currentPrefs = prefs;
		initComponents();
		creategridLayout();
		
		//createLayout();
		applyPrefs(prefs);
		
	}
	public TransparentPanel getPanel() {
		return pane;
	}

	// helper method to convert celcius to fahrenheit
    private double convertctof(double temp){
		double n = ((temp*1.8)+32);
		return n;
	}

	private void initComponents() {
		// create fonts for text
		Font sprlrgfont = new Font("lrg", Font.PLAIN, 44);
		Font lrgfont = new Font("lrg", Font.PLAIN, 24);
		Font medfont = new Font("med", Font.PLAIN, 16);
		Font smlfont = new Font("sml", Font.PLAIN, 13);
		// create labels to display basic data
		pane = new TransparentPanel(); 
		
		lastupdate_l = new JLabel("Last Update: " + currentData.getTimeOfLastRequest());
		lastupdate_l.setFont(smlfont);
		
		sunrise_l = new JLabel("Sunrise: " + currentData.getSunrise());
		sunrise_l.setFont(medfont);
		
		sunset_l = new JLabel("Sunset: " + currentData.getSunset());
		sunset_l.setFont(medfont);
		
		windspeed_l = new JLabel("Wind Speed: " + currentData.getWindSpeed());
		windspeed_l.setFont(medfont);
		
		winddirection_l = new JLabel("Wind Direction: "+ currentData.getWindDirection());
		winddirection_l.setFont(medfont);
		
		weatherdescription_l = new JLabel("" + currentData.getDescription());
		weatherdescription_l.setFont(smlfont);
		
		skycondition_l = new JLabel("" + currentData.getSkyConditionCurrent());
		skycondition_l.setFont(lrgfont);
		
		temp_l = new JLabel((int)currentData.getTempCurrent() +"\u00b0" + currentPrefs.getTempUnit());
		temp_l.setFont(sprlrgfont);
		
		tempmax_l = new JLabel("H:" +  (int)currentData.getHigh());
		tempmax_l.setFont(medfont);
		
		//tempmax_l.setForeground(Color.RED);
		
		tempmin_l = new JLabel("L:" + ( (int)(currentData.getLow())));
		tempmin_l.setFont(medfont);
		
		//tempmin_l.setForeground(Color.BLUE);
		
		humidity_l = new JLabel("Humidity: " + currentData.getHumidity() + "%");
		humidity_l.setFont(medfont);
		
		airpressure_l = new JLabel("Air Pressure: " + currentData.getPressure());
		airpressure_l.setFont(medfont);
		
		image_l = new JLabel(new ImageIcon(currentData.getIcon()));
		image_l.setPreferredSize(new Dimension(10,10));
		cityname = new JLabel(currentData.getCityName());
		cityname.setFont(lrgfont);
		
	}
	
	private void creategridLayout(){
		//GridLayout glayout = new GridLayout(0,6);
		pane.setLayout(null);
		
		
		
		pane.add(cityname);
		cityname.setBounds(20, 40, 250, 30);
		
		pane.add(skycondition_l);
		skycondition_l.setBounds(20, 75, 200, 30);
		
		pane.add(humidity_l);
		humidity_l.setBounds(20, 115, 200, 20);
		
		pane.add(winddirection_l);
		winddirection_l.setBounds(20, 135, 250, 20);
		
		pane.add(windspeed_l);
		windspeed_l.setBounds(20, 155, 200, 20);
		
		pane.add(airpressure_l);
		airpressure_l.setBounds(20, 175, 200, 20);
		
		pane.add(sunrise_l);
		sunrise_l.setBounds(20, 195, 200, 20);
		
		pane.add(sunset_l);
		sunset_l.setBounds(20, 215, 200, 20);
		
		pane.add(temp_l);
		temp_l.setBounds(390, 90, 200, 40);
		
		pane.add(image_l);
		image_l.setBounds(430, 145, 40, 40);
		
		pane.add(tempmin_l);
		tempmin_l.setBounds(400, 190, 50, 20);
		
		pane.add(tempmax_l);
		tempmax_l.setBounds(470, 190, 50, 20);
		
		pane.add(lastupdate_l);
		lastupdate_l.setBounds(20, 350, 200, 20);
		
		/*pane.add(temp_l);
		
		pane.add(tempmin_l);
		pane.add(tempmax_l);
		pane.add(image_l);
		pane.add(skycondition_l);
		pane.add(weatherdescription_l);
		pane.add(windspeed_l);
		pane.add(winddirection_l);
		pane.add(humidity_l);
		pane.add(airpressure_l);
		pane.add(sunrise_l);
		pane.add(sunset_l);*/
		//pane.setBounds(0, 0, 800, 600);
		
		pane.setSize(780,375);
		pane.validate();
		
		
	}
	
	
	public void applyPrefs(WeatherPreferences prefs){
		
		temp_l.setVisible(prefs.getShowTemperature());
		tempmin_l.setVisible(prefs.getShowLow());
	    tempmax_l.setVisible(prefs.getShowHigh());
		image_l.setVisible(prefs.getShowIcon()); 
		weatherdescription_l.setVisible(prefs.getShowDescription());
		skycondition_l.setVisible(prefs.getShowSky());
	    sunrise_l.setVisible(prefs.getShowSunrise());
	    sunset_l.setVisible(prefs.getShowSunset());
		windspeed_l.setVisible(prefs.getShowWindSpeed());
		winddirection_l.setVisible(prefs.getShowWindDirection());	
	    humidity_l.setVisible(prefs.getShowHumidity());
		airpressure_l.setVisible(prefs.getShowPressure());

	}



	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.setTitle("Weather for London, Canada");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		WeatherPreferences prefs = new WeatherPreferences();
		InputTest intest = new InputTest("Samandag");
		WeatherData data = new WeatherData(intest);
		System.out.println(data.getCityName());
		Currentweather test = new Currentweather(data, prefs);
		frame.getContentPane().add(test.getPanel());
		System.out.println("finished");
		//frame.pack();
		frame.setSize(690, 410);
		frame.setVisible(true);
	}




}


