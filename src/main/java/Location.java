package main.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.WindowConstants;


public class Location implements ActionListener{
	private TransparentPanel locpane;
	private String name;
	private CW weather;
	private Forecast24Hour shortterm;
	private Forecast5Day longterm;
	private JButton sh_shortterm, sh_longterm;
	private boolean bshort,blong;
	/**
	 * Constructor for a Location Object
	 * @param data WeatherData data model for this weather program
	 * @param prefs WeatherPreferences current user display preferences
	 */
	public Location(WeatherData data, WeatherPreferences prefs) {
		locpane = new TransparentPanel();
		name = data.getCityName();
		JLabel locname_l = new JLabel(name);
		
		weather = new CW(data, prefs);
		shortterm = new Forecast24Hour(data, prefs);
		longterm = new Forecast5Day(data, prefs);
		
		GroupLayout layout = new GroupLayout(locpane); 
		locpane.setLayout(layout);
		
		ParallelGroup horiz = layout.createParallelGroup();
		SequentialGroup vert = layout.createSequentialGroup();
		horiz.addComponent(locname_l);
		
		JPanel p1 = weather.getPanel();
		JPanel p2 = shortterm.getPanel();
		JPanel p3 = longterm.getPanel();
		
		horiz.addComponent(p1);
		horiz.addComponent(p2);
		horiz.addComponent(p3);
		vert.addComponent(locname_l);
		vert.addComponent(p1);
		vert.addComponent(p2);
		vert.addComponent(p3);
		layout.setHorizontalGroup(horiz);
		layout.setVerticalGroup(vert);
	
	}
	/**
	 * getter for the location name
	 * @return String which is the name of the location
	 */
	public String getName(){
		return name;
	}
	/**
	 * Getter 
	 * @return TransparentPanel 
	 */
	public TransparentPanel getPane(){
		return locpane;
	}
	/**
	 * gets the current weather object
	 * @return CW a current weather TransparentPanel
	 */
	public CW getCurrentweather(){
		return weather;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.equals("Show_short")){
			if (bshort==true){
			locpane.remove(shortterm.getPanel());
			bshort=false;
			sh_shortterm.setText("Show 24 hour forecast");
			}
			else {
				if (blong==true){
					locpane.remove(longterm.getPanel());
					locpane.add(shortterm.getPanel());
					locpane.add(longterm.getPanel());
					sh_shortterm.setText("Hide 24 hour forecast");
				}
			}
		}
		if (event.equals("Show_long")){
			if (blong==true){
				locpane.remove(longterm.getPanel());
				sh_longterm.setText("Show 5 day forecast");
				blong = false;
			}
			else {
				locpane.add(longterm.getPanel());
				sh_longterm.setText("Hide 5 day forecast");
				blong = true;
			}
		}
		locpane.validate();	
	}
	/**
	 * updates the current weather object with the user display preferences
	 * @param prefs WeatherPreferences object 
	 */
	public void updateViewPreferences(WeatherPreferences prefs){
		weather.applyPrefs(prefs);
	}
}
