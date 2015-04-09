package main.java;

import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class WeatherPreferences implements java.io.Serializable{

	/**
	 * @author TEAM 7 CS-2212 2015
	 * 
	 * A Preference class to control the application. 
	 * Allows the user to hide and show certain elements of the application.
	 * 
	 * It does so by having getters and setters for each of the elements
	 * 
	 * 	
	 */


	private String defaultLocation;
	private String tempUnit;
	private LinkedList<String> locationlist;
	private boolean showSky, showTemp, showPressure;
	private boolean showWindDirection, showIcon;
	private boolean windSpeed, showHumidity, showSunset, showSunrise;
	private boolean showPOP, showLow, showHigh;
	private boolean showDescription;
	private boolean showMars;
	
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
		this.showMars = false;
		this.list = new JList<>();
		this.modelloc = new DefaultListModel<>();
	}
	
	
	public DefaultListModel<String> getlist(){
		
		return modelloc;
	}
	

	
	
	/**
	 * gets the current location
	 * @return String
	 */
	public String getLocation(){
		return defaultLocation;
	}
	
	/**
	 * gets the list of locations
	 * @return LinkedList<String>
	 */
	public LinkedList<String> getLocationList(){
		return locationlist;
	}

	/**
	 * sets the current location
	 * @param newLocation String name of location
	 */
	public void setLocation(String newLocation) {
		defaultLocation = newLocation;
	}
	
	/**
	 * adds a location to the location list
	 * @param location String name of location
	 */
	public void addLocation(String location) {
		locationlist.add(location);
	}

	/**
	 * getter for the temperature unit being used
	 * @return String
	 */
	public String getTempUnit(){
		return tempUnit;
	}

	/**
	 * Setter for the current temperature unit
	 * @param tempUn temp unit char "C" or "F"
	 */
	public void setTempUnit(String tempUn) {
		this.tempUnit = tempUn;
	}

	/**
	 * getter for showing the sky condition
	 * @return boolean
	 */
	public boolean getShowSky() {
		return showSky;
	}

	/**
	 * getter for showing current temperature
	 * @return boolean
	 */
	public boolean getShowTemperature() {
		return showTemp;
	}

	/**
	 * getter for show wind pressure
	 * @return boolean
	 */
	public boolean getShowPressure() {
		return showPressure;
	}

	/**
	 * getter for show wind speed
	 * @return boolean
	 */
	public boolean getShowWindSpeed() {
		return windSpeed;
	}

	/**
	 * getter for if the current humidity is showing
	 * @return boolean
	 */
	public boolean getShowHumidity() {
		return showHumidity;
	}

	/**
	 * getter for if the sunset time is showing
	 * @return boolean
	 */
	public boolean getShowSunset() {
		return showSunset;
	}

	/**
	 * getter for if the sunrise time is showing
	 * @return boolean
	 */
	public boolean getShowSunrise() {
		return showSunrise;
	}

	/**
	 * getter for if the wind direction is showing
	 * @return boolean
	 */
	public boolean getShowWindDirection() {
		return showWindDirection;
	}

	/**
	 * getter for if the current weather icon is showing
	 * @return boolean
	 */
	public boolean getShowIcon(){
		return showIcon;
	}

	/**
	 * getter for if the percentage of percipitation is showing
	 * @return boolean
	 */
	public boolean getShowPOP() {
		return showPOP;
	}

	/**
	 * getter for if the daily low is showing
	 * @return boolean
	 */
	public boolean getShowLow() {
		return showLow;
	}

	/**
	 * getter for if the daily high is showing
	 * @return boolean
	 */
	public boolean getShowHigh() {
		return showHigh;
	}

	/**
	 * getter for if the current humidity is showing
	 * @return String
	 */
	public boolean getShowDescription() {
		return showDescription;
	}
	
	/**
	 * getter for mars
	 * @return String
	 */
	public boolean getShowMars() {
		return showMars;
	}

	/**
	 * setter to hide or show the sky condition
	 * @param set boolean
	 */
	public void setShowSky(boolean set) {
		this.showSky = set;
	}

	/**
	 * setter to hide or show the sky condition
	 * @param set boolean
	 */
	public void setShowTemperature(boolean set) {
		this.showTemp = set;
	}

	/**
	 * setter to hide or show the pressure level
	 * @param set boolean
	 */ 
	 public void setShowPressure(boolean set) {
		 this.showPressure = set;
	 }

	 /**
	  * setter to hide or show the current wind speed
	  * @param set boolean
	  */
	 public void setShowWindSpeed(boolean set) {
		 this.windSpeed = set;
	 }

	 /**
	  * setter to hide or show the current humidity
	  * @param set boolean
	  */
	 public void setShowHumidity(boolean set) {
		 this.showHumidity = set;
	 }

	 /**
	  * setter to hide or show the sunset time
	  * @param set boolean
	  */
	 public void setShowSunset(boolean set) {
		 this.showSunset = set;
	 }

	 /**
	  * setter to hide or show the current day's sunrise time
	  * @param set boolean
	  */
	 public void setShowSunrise(boolean set) {
		 this.showSunrise = set;
	 }

	 /**
	  * setter to hide or show the current wind direction
	  * @param set boolean
	  */
	 public void setShowWindDirection(boolean set) {
		 this.showWindDirection = set;
	 }

	 /**
	  * setter to hide or show the current weather condition's icon
	  * @param set boolean
	  */
	 public void setShowIcon(boolean set){
		 this.showIcon = set;
	 }

	 /**
	  * setter to hide or show the current percentage of percipitation
	  * @param set boolean
	  */
	 public void setShowPOP(boolean set) {
		 this.showPOP = set;
	 }

	 /**
	  * setter to hide or show the daily low temperature
	  * @param set boolean
	  */
	 public void setShowLow(boolean set) {
		 this.showLow = set;
	 }

	 /**
	  * setter to hide or show the daily high temperature
	  * @param set boolean
	  */
	 public void setShowHigh(boolean set) {
		 this.showHigh = set;
	 }

	 /**
	  * setter to hide or show the description the current weather
	  * @param set boolean
	  */
	 public void setShowDescription(boolean set) {
		 this.showDescription = set;
	 }
	 
	     /**
		 * setter to hide or show mars
		 * @param set boolean
		 */ 
		 public void setShowMars(boolean set) {
			 this.showMars = set;
		 }
}	