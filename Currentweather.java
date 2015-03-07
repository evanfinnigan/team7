package team7;

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
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;



public class Currentweather {

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
		this.currentData = data;
		this.currentPrefs = prefs;
		initComponents();
		creategridLayout();
		//createLayout();
		applyPrefs(prefs);
		
	}
	public JPanel getPanel() {
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
		Font smlfont = new Font("sml", Font.PLAIN, 10);
		// create labels to display basic data
		pane = new JPanel();
		//pane.setPreferredSize(new Dimension(600,200)); 
		sunrise_l = new JLabel("Sunrise: " + currentData.getSunrise());
		sunrise_l.setFont(medfont);
		sunset_l = new JLabel("Sunset: " + currentData.getSunset());
		sunset_l.setFont(medfont);
		windspeed_l = new JLabel("Wind Speed: " + currentData.getWindSpeed());
		windspeed_l.setFont(medfont);
		winddirection_l = new JLabel("Wind Direction: "
				+ currentData.getWindDirection());
		winddirection_l.setFont(medfont);
		weatherdescription_l = new JLabel("" + currentData.getDescription());
		weatherdescription_l.setFont(medfont);
		skycondition_l = new JLabel("" + currentData.getSkyConditionCurrent());
		skycondition_l.setFont(medfont);
		temp_l = new JLabel(currentData.getTempCurrent() +"\u00b0" + currentPrefs.getTempUnit());
		temp_l.setFont(medfont);
		tempmax_l = new JLabel("High: " + currentData.getHigh());
		tempmax_l.setFont(medfont);
		//tempmax_l.setForeground(Color.RED);
		tempmin_l = new JLabel("Low: " + currentData.getLow());
		tempmin_l.setFont(medfont);
		//tempmin_l.setForeground(Color.BLUE);
		humidity_l = new JLabel("Humidity: " + currentData.getHumidity());
		humidity_l.setFont(medfont);
		airpressure_l = new JLabel("Air Pressure: " + currentData.getPressure());
		airpressure_l.setFont(medfont);
		image_l = new JLabel(new ImageIcon(currentData.getIcon()));
	}
	private void creategridLayout(){
		GridLayout glayout = new GridLayout(2,6);
		
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



//	public static void main(String[] args){
//		
//		JFrame frame = new JFrame();
//		frame.setTitle("Weather for London, Canada");
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.setPreferredSize(null);
//		WeatherPreferences prefs = new WeatherPreferences();
//		InputTest intest = new InputTest("London,ca");
//		WeatherData data = new WeatherData(intest);
//		System.out.println(data.getCityName());
//		Currentweather test = new Currentweather(data, prefs);
//		frame.getContentPane().add(test.getPanel());
//		System.out.println("finished");
//		frame.pack();
//		frame.show();
//	}
}
