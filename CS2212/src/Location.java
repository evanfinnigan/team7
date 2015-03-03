
public class Location {
	String name;
	Currentweather weather;
	Shorttermforcast shortterm;
	Longtermforcast longterm;
	
	public Location(WeatherData data, Preferences prefs) {
		name = data.getName();
		weather = new Currentweather(data, prefs);
		//shortterm = new ShortTermForcast(data);
		//longterm = new LongTermForecast(data);
	}
	
}
