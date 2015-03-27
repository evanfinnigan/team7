package main.java;

import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

public class WeatherPreferences implements java.io.Serializable{

	/**
	 * @author Evan Kossman
	 * 
	 * A Preference class to control the application. 
	 * Allows the user to hide and show certain elements of the application.
	 * 
	 * It does so by having getters and setters for each of the elements
	 * 
	 * 	
	 */


	private String defaultLocation;
	//private LocationList locationlist;
	private String tempUnit;
	private LinkedList<String> locationlist;
	private boolean showSky, showTemp, showPressure;
	private boolean showWindDirection, showIcon;
	private boolean windSpeed, showHumidity, showSunset, showSunrise;
	private boolean showPOP, showLow, showHigh;
	private boolean showDescription;
	private int itemcounter;
	private String time;
	
	private JList<String> list;
	private DefaultListModel<String> modelloc;
	
	/**
	 * WeatherPreferences Constructor
	 * Creates the default preferences. 
	 * Everything is shown as default
	 */
	public WeatherPreferences() {
		this.defaultLocation = null;
		this.locationlist = null;
		//this.locationlist = null;
		//Temperature Unit used as default is Celsius
		this.tempUnit = "C";
		this.showSky = true;
		this.showTemp = true;
		this.showPressure = true;
		this.windSpeed = true;
		this.showHumidity = true;
		this.showWindDirection = true;
		this.showSunset = true;
		this.showSunrise = true;
		this.showIcon = true;
		this.showPOP = true;
		this.showLow = true;
		this.showHigh = true;
		this.showDescription = true;
		this.itemcounter = 6;
		
		this.list = new JList<>();
		this.modelloc = new DefaultListModel<>();
	}
	
	
	public DefaultListModel<String> getlist(){
		
		return modelloc;
	}
	

	
	
	/**
	 * gets the current location
	 * @returns string
	 */
	public String getLocation(){
		return defaultLocation;
	}
	
	/**
	 * gets the list of locations
	 */
	public LinkedList<String> getLocationList(){
		return locationlist;
	}

	/**
	 * sets the current location
	 * @returns
	 */
	public void setLocation(String newLocation) {
		defaultLocation = newLocation;
	}
	
	/**
	 * adds a location to the location list
	 */
	public void addLocation(String location) {
		locationlist.add(location);
	}

	/**
	 * getter for the temperature unit being used
	 * @returns String
	 */
	public String getTempUnit(){
		return tempUnit;
	}

	/**
	 * Setter for the current temperature unit
	 * @returns
	 */
	public void setTempUnit(String tempUn) {
		this.tempUnit = tempUn;
	}

	/**
	 * getter for showing the sky condition
	 * @returns boolean
	 */
	public boolean getShowSky() {
		return showSky;
	}

	/**
	 * getter for showing current temperature
	 * @returns boolean
	 */
	public boolean getShowTemperature() {
		return showTemp;
	}

	/**
	 * getter for show wind pressure
	 * @returns boolean
	 */
	public boolean getShowPressure() {
		return showPressure;
	}

	/**
	 * getter for show wind speed
	 * @returns boolean
	 */
	public boolean getShowWindSpeed() {
		return windSpeed;
	}

	/**
	 * getter for if the current humidity is showing
	 * @returns boolean
	 */
	public boolean getShowHumidity() {
		return showHumidity;
	}

	/**
	 * getter for if the sunset time is showing
	 * @returns boolean
	 */
	public boolean getShowSunset() {
		return showSunset;
	}

	/**
	 * getter for if the sunrise time is showing
	 * @returns boolean
	 */
	public boolean getShowSunrise() {
		return showSunrise;
	}

	/**
	 * getter for if the wind direction is showing
	 * @returns boolean
	 */
	public boolean getShowWindDirection() {
		return showWindDirection;
	}

	/**
	 * getter for if the current weather icon is showing
	 * @returns boolean
	 */
	public boolean getShowIcon(){
		return showIcon;
	}

	/**
	 * getter for if the percentage of percipitation is showing
	 * @returns boolean
	 */
	public boolean getShowPOP() {
		return showPOP;
	}

	/**
	 * getter for if the daily low is showing
	 * @returns boolean
	 */
	public boolean getShowLow() {
		return showLow;
	}

	/**
	 * getter for if the daily high is showing
	 * @returns boolean
	 */
	public boolean getShowHigh() {
		return showHigh;
	}

	/**
	 * getter for if the current humidity is showing
	 * @returns String
	 */
	public boolean getShowDescription() {
		return showDescription;
	}

	/**
	 * setter to hide or show the sky condition
	 * @returns
	 */
	public void setShowSky(boolean set) {
		this.showSky = set;
	}

	/**
	 * setter to hide or show the sky condition
	 * @returns
	 */
	public void setShowTemperature(boolean set) {
		this.showTemp = set;
	}

	/**
	 * setter to hide or show the pressure level
	 * @returns
	 */ 
	 public void setShowPressure(boolean set) {
		 this.showPressure = set;
	 }

	 /**
	  * setter to hide or show the current wind speed
	  * @returns
	  */
	 public void setShowWindSpeed(boolean set) {
		 this.windSpeed = set;
	 }

	 /**
	  * setter to hide or show the current humidity
	  * @returns
	  */
	 public void setShowHumidity(boolean set) {
		 this.showHumidity = set;
	 }

	 /**
	  * setter to hide or show the sunset time
	  * @returns
	  */
	 public void setShowSunset(boolean set) {
		 this.showSunset = set;
	 }

	 /**
	  * setter to hide or show the current day's sunrise time
	  * @returns
	  */
	 public void setShowSunrise(boolean set) {
		 this.showSunrise = set;
	 }

	 /**
	  * setter to hide or show the current wind direction
	  * @returns
	  */
	 public void setShowWindDirection(boolean set) {
		 this.showWindDirection = set;
	 }

	 /**
	  * setter to hide or show the current weather condition's icon
	  * @returns
	  */
	 public void setShowIcon(boolean set){
		 this.showIcon = set;
	 }

	 /**
	  * setter to hide or show the current percentage of percipitation
	  * @returns
	  */
	 public void setShowPOP(boolean set) {
		 this.showPOP = set;
	 }

	 /**
	  * setter to hide or show the daily low temperature
	  * @returns
	  */
	 public void setShowLow(boolean set) {
		 this.showLow = set;
	 }

	 /**
	  * setter to hide or show the daily high temperature
	  * @returns
	  */
	 public void setShowHigh(boolean set) {
		 this.showHigh = set;
	 }

	 /**
	  * setter to hide or show the description the current weather
	  * @returns
	  */
	 public void setShowDescription(boolean set) {
		 this.showDescription = set;
	 }
}	