package main.java;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LocationV1 extends TransparentPanel {
	private String name;
	private WeatherData locdata;
	private CW currentweather;
	private Forecast24Hour shortterm;
	private Forecast5Day longterm;
	
	/**
	 * Create the Panel.
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
		//locpane = new TransparentPanel();
		

	}
	public String getLocationName(){
		return name;
	}
	public TransparentPanel getPane(){
		return this;
	}
	public void updateViewPreferences(WeatherPreferences prefs){
		currentweather.applyPrefs(prefs);
	}
	public WeatherData getlocData(){
		return locdata;
	}
	public void refresh(WeatherPreferences prefs){
		currentweather = new CW(locdata,prefs);
		shortterm = new Forecast24Hour(locdata,prefs);
		longterm = new Forecast5Day(locdata,prefs);
		
	}
	public static void main(String[] args) {

		InputTest t = new InputTest("Vancouver");
		WeatherData d = new WeatherData(t);
		LocationV1 test = new LocationV1(d,new WeatherPreferences());
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(test);
		frame.setVisible(true);
		frame.pack();
		frame.setTitle("Location Test");
	}
}
