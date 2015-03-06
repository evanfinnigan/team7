package team7;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JFrame;// used in the test to be removed

import javax.swing.WindowConstants;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

public class Currentweather extends JPanel{

	private WeatherData currentData;
	private WeatherPreferences currentPrefs;
	private JPanel pane;
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
		currentData = data;
		currentPrefs = prefs;
		initComponents();
		createLayout();
		applyPrefs(prefs);
	}

	private void initComponents() {
		// create fonts for text
		Font mainfont = new Font("lrg", Font.PLAIN, 30);
		Font minorfont = new Font("sml", Font.PLAIN, 15);
		
		// create labels to display basic data
		
		this.pane = new JPanel();
		
		this.sunrise_l = new JLabel("Sunrise: " + currentData.getSunrise());
		sunrise_l.setFont(minorfont);
		
		this.sunset_l = new JLabel("Sunset: " + currentData.getSunset());
		sunset_l.setFont(minorfont);
		
		this.windspeed_l = new JLabel("Wind Speed: " + currentData.getWindSpeed());
		windspeed_l.setFont(minorfont);
		
		this.winddirection_l = new JLabel("Wind Direction: "
				+ currentData.getWindDirection());
		winddirection_l.setFont(minorfont);
		
		this.weatherdescription_l = new JLabel("Current Conditions: "
				+ currentData.getDescription());
		weatherdescription_l.setFont(minorfont);
		
		this.skycondition_l = new JLabel("" + currentData.getSkyConditionCurrent());
		skycondition_l = new JLabel("" + currentData.getSkyConditionCurrent());
		skycondition_l.setFont(minorfont);
		
		this.temp_l = new JLabel("Current Temperature: "
				+ currentData.getTempCurrent());
		temp_l.setFont(mainfont);
		
		this.tempmax_l = new JLabel("High: " + currentData.getHigh());
		tempmax_l.setFont(minorfont);
		
		this.tempmin_l = new JLabel("Low: " + currentData.getLow());
		tempmin_l.setFont(minorfont);
		
		this.humidity_l = new JLabel("Humidity: " + currentData.getHumidity());
		humidity_l.setFont(minorfont);
		
		this.airpressure_l = new JLabel("Air Pressure: " + currentData.getPressure());
		airpressure_l.setFont(minorfont);
		
		this.image_l = new JLabel(new ImageIcon(currentData.getIcon()));
	}
	
	private void createLayout(){
		
		GroupLayout panelayout = new GroupLayout(pane);
		ParallelGroup hmiddle = panelayout.createParallelGroup(GroupLayout.Alignment.LEADING);
	      
        hmiddle.addComponent(temp_l);
        hmiddle.addComponent(tempmin_l);
        hmiddle.addComponent(tempmax_l);
        hmiddle.addGap(10);
        hmiddle.addComponent(weatherdescription_l);
        hmiddle.addComponent(skycondition_l);
        hmiddle.addComponent(image_l);  
        hmiddle.addGap(10);
        hmiddle.addComponent(windspeed_l);
        hmiddle.addComponent(winddirection_l);
        hmiddle.addGap(10);
        hmiddle.addComponent(sunrise_l);
        hmiddle.addComponent(sunset_l);
        hmiddle.addGap(10);
        hmiddle.addComponent(humidity_l);
        hmiddle.addComponent(airpressure_l);
        
        SequentialGroup toplabels = panelayout.createSequentialGroup();
        SequentialGroup bottomlabels = panelayout.createSequentialGroup();
        
        toplabels.addComponent(temp_l);
        toplabels.addComponent(tempmin_l);
        toplabels.addComponent(tempmax_l);
        toplabels.addComponent(skycondition_l);
        toplabels.addComponent(image_l);
        toplabels.addComponent(windspeed_l);
        toplabels.addComponent(winddirection_l);
        
        bottomlabels.addComponent(sunrise_l);
	    bottomlabels.addComponent(sunset_l);
        bottomlabels.addComponent(humidity_l);
        bottomlabels.addComponent(airpressure_l);
        bottomlabels.addComponent(weatherdescription_l);
        
        ParallelGroup hGroup = panelayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        ParallelGroup vGroup = panelayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        
        hGroup.addGroup(toplabels);
        hGroup.addGroup(bottomlabels);
        vGroup.addGroup(toplabels);
        vGroup.addGroup(bottomlabels);
      
        panelayout.setHorizontalGroup(hGroup);
        panelayout.setVerticalGroup(vGroup);
        pane.setLayout(panelayout);
        
  }
	private void applyPrefs(WeatherPreferences prefs){
		
		temp_l.setVisible(prefs.showTemperature());
		tempmin_l.setVisible(prefs.showLow());
	    tempmax_l.setVisible(prefs.showHigh());
		image_l.setVisible(prefs.showIcon()); 
		weatherdescription_l.setVisible(prefs.showDescription());
		skycondition_l.setVisible(prefs.showSky());
	    sunrise_l.setVisible(prefs.showSunrise());
	    sunset_l.setVisible(prefs.showSunset());
		windspeed_l.setVisible(prefs.showWindSpeed());
		winddirection_l.setVisible(prefs.showWindDirection());	
	    humidity_l.setVisible(prefs.showHumidity());
		airpressure_l.setVisible(prefs.showPressure());

	}

	// Test

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Weather for London, Canada");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		WeatherPreferences prefs = new WeatherPreferences();
		WeatherData data = new WeatherData("London,ca");
		JPanel test = new Currentweather(data, prefs);
		frame.add(test);
		frame.setVisible(true);
		frame.pack();
		
	}
}
