package main.java;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LocationV1 extends TransparentPanel {
	private String name;
	private WeatherData locdata;
	private CW currentweather;
	private Forecast24Hour shortterm;
	private Forecast5Day longterm;
	
	/**
	 * Create a Transparent Panel that contains the Current Weather, Short term and long term panels.
	 * @param data WeatherData the current weather model
	 * @param prefs WeatherPreferences and user preferences
	 */
	public LocationV1(WeatherData data, WeatherPreferences prefs) {
		locdata = data;
		name = data.getCityName();
		currentweather = new CW(data,prefs);
		currentweather.setMaximumSize(new Dimension(1024, 300));
		currentweather.setMinimumSize(new Dimension(1024, 300));
		currentweather.setPreferredSize(new Dimension(1024,300));
		shortterm = new Forecast24Hour(data,prefs);
		shortterm.setPreferredSize(new Dimension(1024,250));
		longterm = new Forecast5Day(data,prefs);
		longterm.setPreferredSize(new Dimension(1024,250));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(currentweather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(shortterm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(longterm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(5, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(currentweather, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(shortterm, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(longterm, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addGap(5))
		);
		this.setLayout(groupLayout);
		
		

	}
	/**
	 * getter for the location's name
	 * @return String the locations name
	 */
	public String getLocationName(){
		return name;
	}
	/**
	 * Convenience method for other classes
	 * @return TransparentPanel a LocationV1 object
	 */
	public TransparentPanel getPane(){
		return this;
	}
	/**
	 * method that applies the user view preferences to the current weather panel
	 * @param prefs WeatherPreferences user display preferences
	 */
	public void updateViewPreferences(WeatherPreferences prefs){
		currentweather.applyPrefs(prefs);
	}
	/**
	 * getter for the location's WeatherData
	 * @return WeatherData returns the WeatherData model used to construct this location.
	 */
	public WeatherData getlocData(){
		return locdata;
	}
	
}
