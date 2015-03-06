package team7;

public class Preferences {

	// Attributes
	
	//Current Weather Preferences
	private boolean showTemperature;
	private boolean showLow;
	private boolean showHigh;
	private boolean showDescription;
	private boolean showIcon;
	private boolean showSky;
	private boolean showSunrise;
	private boolean showSunset;
	private boolean showWindSpeed;
	private boolean showWindDirection;
	private boolean showHumidity;
	private boolean showPressure;
	
	// 24h Forecast Preferences
	
	// 5d Forecast Preferences
	
	// Mars Preferences
	
	// Constructor
	Preferences(){
		// Defaults for Current Weather
		showTemperature(true);
		showHigh(true);
		showLow(true);
		showDescription(true);
		showIcon(true);
		showDescription(true);
		showSky(true);
		showSunrise(true);
		showSunset(true);
		showWindSpeed(true);
		showWindDirection(true);
		showHumidity(true);
		showPressure(true);
	}

	// Getter & Setter Methods
	
	public void showTemperature(boolean b){
		this.showTemperature = b;
	}
	
	public boolean showTemperature() {
		if (showTemperature == true){
			return true;
		} else {
			return false;
		}
	}

	public void showLow(boolean b){
		this.showLow = b;
	}
	
	public boolean showLow() {
		if (showLow == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showHigh(boolean b){
		this.showHigh = b;
	}
	
	public boolean showHigh() {
		if (showHigh == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showDescription(boolean b){
		this.showDescription = b;
	}
	
	public boolean showDescription() {
		if (showDescription == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showIcon(boolean b){
		this.showIcon = b;
	}
	
	public boolean showIcon() {
		if (showIcon == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showSky(boolean b){
		this.showSky = b;
	}
	
	public boolean showSky() {
		if (showSky == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showSunrise(boolean b){
		this.showSunrise = b;
	}
	
	public boolean showSunrise() {
		if (showSunrise == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showSunset(boolean b){
		this.showSunset = b;
	}
	
	public boolean showSunset() {
		if (showSunset == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showWindSpeed(boolean b){
		this.showWindSpeed = b;
	}
	
	public boolean showWindSpeed() {
		if (showWindSpeed == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showWindDirection(boolean b){
		this.showWindDirection = b;
	}
	
	public boolean showWindDirection() {
		if (showWindDirection == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showHumidity(boolean b){
		this.showHumidity = b;
	}
	
	public boolean showHumidity() {
		if (showHumidity == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void showPressure(boolean b){
		this.showPressure = b;
	}
	
	public boolean showPressure() {
		if (showPressure == true){
			return true;
		} else {
			return false;
		}
	}
	
}
