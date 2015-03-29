package main.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Currentweather {

	private WeatherData currentData;
	private WeatherPreferences currentPrefs;
	private TransparentPanel pane;
	private String time = "Unavailable";
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

	/**
	 * Currentweather constructor
	 * @param data WeatherData data model for weather program 
	 * @param prefs WeatherPreferences for user preferences 
	 */
	public Currentweather(WeatherData data, WeatherPreferences prefs) {
		this.currentData = data;
		this.currentPrefs = prefs;
		initComponents();
		createLayout();
		applyPrefs(prefs);
	}
	
	/**
	 * Currentweather constructor
	 * @param data WeatherData data model for weather program
	 * @param prefs WeatherPreferences for user preferences 
	 * @param time String for current time in String format
	 */
	public Currentweather(WeatherData data, WeatherPreferences prefs, String time) {
		this.currentData = data;
		this.currentPrefs = prefs;
		this.time = time;
		initComponents();
		createLayout();
		applyPrefs(prefs);

	}

	/**
	 * Gets the CurrentWeather panel 
	 * @return pane TransparentPanel
	 */
	public TransparentPanel getPanel() {
		return pane;
	}

	
	/**
	 * Celcius to fahrenheit conversion
	 * @param temp Temperature of type double in celcius units
	 * @return Temperature of type double in fahrenheit
	 */
	private double convertctof(double temp) {
		double n = ((temp * 1.8) + 32);
		return n;
	}
	
	
	/**
	 * Initializes components to be used by the constructor 
	 */
	private void initComponents() {
		// create fonts for text
		Font sprlrgfont = new Font("lrg", Font.PLAIN, 60);
		Font lrgfont = new Font("lrg", Font.PLAIN, 24);
		Font medfont = new Font("med", Font.PLAIN, 16);
		Font smlfont = new Font("sml", Font.PLAIN, 13);
		// create labels to display basic data
		pane = new TransparentPanel();
		
		
		
		
		
		lastupdate_l = new JLabel("Last Update: "
				+ time);
		lastupdate_l.setFont(smlfont);

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
		weatherdescription_l.setFont(smlfont);

		skycondition_l = new JLabel("" + currentData.getSkyConditionCurrent());
		skycondition_l.setFont(lrgfont);

		if (currentPrefs.getTempUnit().equalsIgnoreCase("F")) {
			temp_l = new JLabel((int) currentData.getTempCurrentF() + "\u00b0" + "F");
			temp_l.setFont(sprlrgfont);

			tempmax_l = new JLabel("High: " + (int) currentData.getHighF() + "\u00b0" + "F");
			tempmax_l.setFont(medfont);

			tempmin_l = new JLabel("Low: " + (int) currentData.getLowF() + "\u00b0" + "F");
			tempmin_l.setFont(medfont);

		} else {
			temp_l = new JLabel((int) currentData.getTempCurrent() + "\u00b0" + "C");
			temp_l.setFont(sprlrgfont);

			tempmax_l = new JLabel("High: " + (int) currentData.getHigh() + "\u00b0" + "C");
			tempmax_l.setFont(medfont);


			tempmin_l = new JLabel("Low: " + ((int) (currentData.getLow())) + "\u00b0" + "C");
			tempmin_l.setFont(medfont);

		}

		humidity_l = new JLabel("Humidity: " + currentData.getHumidity() + "%");
		humidity_l.setFont(medfont);

		airpressure_l = new JLabel("Air Pressure: " + currentData.getPressure());
		airpressure_l.setFont(medfont);

		image_l = new JLabel(new ImageIcon(currentData.getIcon()));
		image_l.setPreferredSize(new Dimension(10, 10));
		cityname = new JLabel(currentData.getCityName());
		cityname.setFont(lrgfont);

	}
	
	
	
	/**
	 * Changes the label's text to the specified color
	 * @param acolor Color for labels text
	 */
	public void setLabelColor(Color acolor){
		 sunrise_l.setForeground(acolor);;
		 sunset_l.setForeground(acolor);;
		 weatherdescription_l.setForeground(acolor);;
		 skycondition_l.setForeground(acolor);;
		 windspeed_l.setForeground(acolor);;
		 winddirection_l.setForeground(acolor);;
		 temp_l.setForeground(acolor);;
		 tempmin_l.setForeground(acolor);;
		 tempmax_l.setForeground(acolor);;
		 humidity_l.setForeground(acolor);;
		 airpressure_l.setForeground(acolor);;
		 cityname.setForeground(acolor);;
		 lastupdate_l.setForeground(acolor);;
	}
	
	
	/**
	 * Creates the layout for the current weather
	 */
	private void createLayout() {
		pane.setLayout(null);
		
		JLabel[] labels = new JLabel[6];
		labels[0] = humidity_l;
		labels[1] = winddirection_l;
		labels[2] = windspeed_l;
		labels[3] = airpressure_l;
		labels[4] = sunrise_l;
		labels[5] = sunset_l;
		
		Boolean[] show = new Boolean[6];
		show[0] = currentPrefs.getShowHumidity();
		show[1] = currentPrefs.getShowWindDirection();
		show[2] = currentPrefs.getShowWindSpeed();
		show[3] = currentPrefs.getShowPressure();
		show[4] = currentPrefs.getShowSunrise();
		show[5] = currentPrefs.getShowSunset();

		pane.add(cityname);
		cityname.setBounds(20, 40, 250, 30);

		pane.add(skycondition_l);
		skycondition_l.setBounds(20, 75, 200, 30);

		int a = 0;
		
		for (int i = 0; i < 6; i++) {
			if (show[i]) {
				pane.add(labels[i]);
				a++;
				labels[i].setBounds(20, 115 + 20*a, 250, 20);
			}
		}
	
		pane.add(temp_l);
		temp_l.setBounds(480, 90, 400, 60);

		pane.add(image_l);
		image_l.setBounds(430, 100, 40, 40);

		pane.add(tempmin_l);
		tempmin_l.setBounds(435, 190, 100, 20);

		pane.add(tempmax_l);
		tempmax_l.setBounds(535, 190, 100, 20);

		pane.add(lastupdate_l);
		lastupdate_l.setBounds(20, 310, 200, 20);

		pane.setBackground(new Color(255,255,255));
		pane.setSize(780, 375);
		pane.validate();

	}
	
	
	/**
	 * Applies the preferences for Currentweather 
	 * @param prefs WeatherPreferences object to apply preferences to the current weather panel
	 */
	public void applyPrefs(WeatherPreferences prefs) {

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

}
