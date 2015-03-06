package team7;



public class Location {
	String name;
	Currentweather weather;
	Forecast24Hour shortterm;
	Forecast5Day longterm;
	
	public Location(WeatherData data, WeatherPreferences prefs) {
		name = data.getCityName();
		weather = new Currentweather(data, prefs);
		shortterm = new Forecast24Hour(data,prefs);
		longterm = new Forecast5Day(data,prefs);
	}
	
}
