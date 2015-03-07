
package team7;

public class WeatherPreferences implements java.io.Serializable{

	
	private Location defaultLocation;
	//private LocationList locationlist;
	private int tempUnit, pressureUnit, windSpeedUnit;
	private boolean showSky, showTemp, showPressure;
	private boolean showWindDirection, showIcon;
	private boolean windSpeed, showHumidity, showSunset, showSunrise;
	private boolean showPOP, showLow, showHigh;

	//WeatherPreferences Constructor
	//Creates the default preferences. Everything is shown
	public WeatherPreferences() {
		this.defaultLocation = null;
		//this.locationlist = null;
		//Temperature Unit used as default is Celsius
		this.tempUnit = 0;
		this.pressureUnit = 0;
		this.windSpeedUnit = 0;
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
	}

	//Gets the location information
	public Location getLocation(){
		return this.defaultLocation;
	}

	//Sets the location
	public void setLocation(Location newLocation) {
		this.defaultLocation = newLocation;
	}

	//Returns the temperature Unit being used
	//Celsius = 0, Fahrenheit = 1
	//Default is celsius
	public int getTempUnit(){
		return this.tempUnit;
	}

	//Celsius = 0. Fahrenheit = 1
	public void setTempUnit(int tempUn) {
		this.tempUnit = tempUn;
	}

	//Returns the Pressure Unit
	public int getPressure(){
		return this.pressureUnit;
	}

	//Sets the pressure unit to be used
	public void setPressureUnit(int pressUnit) {
		this.pressureUnit = pressUnit;
	}

	//Gets the current wind sppeed unit
	public int getWindSpeed(){
		return this.windSpeedUnit;
	}

	//Sets the wind speed unit to be used
	public void setWindSpeedUnit(int windSpd) {
		this.windSpeedUnit = windSpd;
	}

	//Hides or shows the sky. Default is shown
	public void showSky(boolean set) {
		this.showSky = set;
	}

	//Hides or shows the Temperature. Default is shown
	public void showTemperature(boolean set) {
		this.showTemp = set;
	}

	//Hides or shows the Pressure. Default is shown
	public void showPressure(boolean set) {
		this.showPressure = set;
	}

	//Hides or shows the Wind Speed. Default is shown
	public void showWindSpeed(boolean set) {
		this.windSpeed = set;
	}

	//Hides or shows the Humidity. Default is shown
	public void showHumidity(boolean set) {
		this.showHumidity = set;
	}

	//Hides or shows the Sunset time. Default is shown
	public void showSunset(boolean set) {
		this.showSunset = set;
	}

	//Hides or shows the Sunrise time. Default is shown
	public void showSunrise(boolean set) {
		this.showSunrise = set;
	}

	//Hides or shows the Wind Direction. Default is shown
	public void showWindDirection(boolean set) {
		this.showWindDirection = set;
	}
	
	//Hides or shows the Current Weather Icon. Default is shown
	public void showIcon(boolean set){
		this.showIcon = set;
	}

	//Hides or shows the % of Precipitation. Default is shown
	public void showPOP(boolean set) {
		this.showPOP = set;
	}

	//Hides or shows the daily low temperature. Default is shown
	public void showLow(boolean set) {
		this.showLow = set;
	}

	//Hides or shows the daily high temperature. Default is shown
	public void showHigh(boolean set) {
		this.showHigh = set;
	}
}	

