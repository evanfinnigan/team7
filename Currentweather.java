package team7;

//import Preferences;
//import WeatherData;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Currentweather {
	
	// Attributes
	private WeatherData currentData;
	private Preferences currentPrefs;
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

	public Currentweather(String city, Preferences prefs) {
		this.currentData = new WeatherData(city);
		this.currentPrefs = prefs;
		initComponents();
		createDisplay(prefs);
	} 
	
	public JPanel getPanel(){
		return pane;
	}
	
	public Preferences getPreferences(){
		return currentPrefs;
	}

	private void initComponents() {
		// create labels to display basic data
		this.pane = new JPanel();
		Font mainfont = new Font("lrg", Font.PLAIN, 30);
		Font minorfont = new Font("sml", Font.PLAIN, 15);
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
		skycondition_l.setFont(minorfont);
		this.temp_l = new JLabel("Current Temperature: "
				+ currentData.getTempCurrent());
		temp_l.setFont(mainfont);
		this.tempmax_l = new JLabel("High: " + currentData.getLow());
		tempmax_l.setFont(minorfont);
		this.tempmin_l = new JLabel("Low: " + currentData.getHigh());
		tempmin_l.setFont(minorfont);
		this.humidity_l = new JLabel("Humidity: " + currentData.getHumidity());
		humidity_l.setFont(minorfont);
		this.airpressure_l = new JLabel("Air Pressure: " + currentData.getPressure());
		airpressure_l.setFont(minorfont);
		this.image_l = new JLabel(new ImageIcon(currentData.getIcon()));
	}

	private void createDisplay(Preferences prefs) {

		GridLayout panelayout = new GridLayout(0, 3);
		pane.setLayout(panelayout);
		if (prefs.showTemperature() == true) {
			pane.add(temp_l);
		}
		if (prefs.showLow() == true) {
			pane.add(tempmin_l);
		}
		if (prefs.showHigh() == true) {
			pane.add(tempmax_l);
		}
		if (prefs.showIcon() == true) {
			pane.add(image_l);
		}
		if (prefs.showDescription() == true) {
			pane.add(weatherdescription_l);
		}
		if (prefs.showSky() == true) {
			pane.add(skycondition_l);
		}
		if (prefs.showSunrise() == true) {
			pane.add(sunrise_l);
		}
		if (prefs.showSunset() == true) {
			pane.add(sunset_l);
		}
		if (prefs.showWindSpeed() == true) {
			pane.add(windspeed_l);
		}
		if (prefs.showWindDirection() == true) {
			pane.add(winddirection_l);
		}
		if (prefs.showHumidity() == true) {
			pane.add(humidity_l);
		}
		if (prefs.showPressure() == true) {
			pane.add(airpressure_l);
		}
	}

	// Test
	public static void main(String[] args){
		
		Preferences p = new Preferences();
		Currentweather test = new Currentweather("London,ON", p);
		JFrame frame = new JFrame();
		frame.add(test.getPanel());
		frame.setVisible(true);
		frame.pack();
		frame.setTitle("Weather for London, Canada");
	}
	
}
