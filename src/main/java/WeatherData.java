package main.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * 
 * @author TEAM 7 CS-2212 2015
 *
 */

public class WeatherData {

	final static String imgURL = "http://openweathermap.org/img/w/";
	final static String marsURL = "http://marsweather.ingenology.com/v1/latest/?format=json";
	
	// Attributes
	private String timeOfLastRequest;

	// Current Weather Attributes
	private JSONObject currentJSONObject;
	private String cityName;
	private String skyConditionCurrent;
	private String description;
	private double tempCurrent;
	private String sunset;
	private String sunrise;
	private double pressure;
	private int humidity;
	private double windSpeed;
	private String windDirection;
	private double low;
	private double high;
	private Image icon;

	// 24 hour Forecast Attributes
	private JSONObject forecast24hJSONObject;
	private double[] temp24h;
	private String[] skyCondition24h;
	private String[] description24h;
	private Image[] icon24h;

	// 5 day Forecast Attributes
	private JSONObject forecast5dJSONObject;
	private double[] temp5d;
	private String[] skyCondition5d;
	private String[] description5d;
	private int[] percentPrecipitation5d;
	private Image[] icon5d;
	private double[] low5d;
	private double[] high5d;
	
	private int cnt5d;
	private int cnt24h;

	// Mars Weather Attributes
	private JSONObject marsJSONObject;
	private double minTempMars;
	private double maxTempMars;
	private double windSpeedMars;
	private String windDirectionMars;
	private double pressureMars;
	private int humidityMars;
	private String skyConditionMars;
	private Image iconMars;
	

	/**
	 * Constructor for WeatherData
	 * @param test InputTest 
	 */
	public WeatherData(InputTest test) {
		this.cityName = test.getCityName();
		this.currentJSONObject = test.getCurrentWeather();
		this.forecast24hJSONObject = test.getForecast24h();
		this.forecast5dJSONObject = test.getForecast5d();
		this.marsJSONObject = requestMarsData(marsURL);
		this.timeOfLastRequest = new Time(5318008).getCurrent();

		// Initialize Current Weather Variables
		setSkyConditionCurrent();
		setDescription();
		setTempCurrent();
		setSunset();
		setSunrise();
		setIcon();
		setPressure();
		setHumidity();
		setWindSpeed();
		setWindDirection();
		setHigh();
		setLow();

		// Initialize 24 hour forecast variables
		setTemp24h();
		setSkyCondition24h();
		setDescription24h();
		setIcon24h();
		setCNT24h();

		// Initialize 5 day forecast variables
		setTemp5d();
		setSkyCondition5d();
		setDescription5d();
		setIcon5d();
		setLow5d();
		setHigh5d();
		setCNT5d();

		// Initialize Mars variables
		setMinTempMars();
		setMaxTempMars();
		setWindSpeedMars();
		setWindDirectionMars();
		setPressureMars();
		setHumidityMars();
		setSkyConditionMars();
		setMarsIcon();
	}

	// Getters
	
	/**
	 * Gets the time of Last Request
	 * @return String
	 */
	public String getTimeOfLastRequest() {
		return timeOfLastRequest;
	}

	/**
	 * Gets the current location
	 * @return JSONObject
	 */
	public JSONObject getCurrentJSONObject() {
		return currentJSONObject;
	}

	/**
	 * Gets the city name
	 * @return String representing the city name
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Gets the current sky condition
	 * @return String representing the current sky condition
	 */
	public String getSkyConditionCurrent() {
		return skyConditionCurrent;
	}

	/**
	 * Gets the description of the weather
	 * @return String representing weather description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the current temperature
	 * @return String representing current temperature
	 */
	public double getTempCurrent() {
		return tempCurrent;
	}
	
	/**
	 * Gets the current temperature
	 * @return String representing current temperature
	 */
	public double getTempCurrentF(){
		return (double)(tempCurrent*1.8 + 32);
	}

	/**
	 * Gets the sunset time
	 * @return String representing sunset time
	 */
	public String getSunset() {
		return sunset;
	}

	/**
	 * Gets the sunrise time
	 * @return String representing sunrise time
	 */
	public String getSunrise() {
		return sunrise;
	}

	/**
	 * Gets the weather pressure 
	 * @return String representing the weather pressure
	 */
	public double getPressure() {
		return pressure;
	}

	/**
	 * Gets the weather humidity
	 * @return String representing weather humidity
	 */
	public int getHumidity() {
		return humidity;
	}

	/**
	 * Gets the wind speed 
	 * @return String representing the wind speed 
	 */
	public double getWindSpeed() {
		return windSpeed;
	}

	/**
	 * Gets the wind direction
	 * @return String representing wind direction
	 */
	public String getWindDirection() {
		return windDirection;
	}

	/**
	 * Gets the low temperature 
	 * @return double representing low temperature
	 */
	public double getLow() {
		return low;
	}
	
	/**
	 * Gets the low temperature in fahrenheit
	 * @return double representing low temperature in fahrenheit
	 */
	public double getLowF(){
		return (double)(low*1.8 + 32);
	}

	/**
	 * Gets the high temperature 
	 * @return Gets the high temperature 
	 */
	public double getHigh() {
		return high;
	}
	
	/**
	 * Gets the high temperature in fahrenheit
	 * @return Gets the high temperature in fahrenheit
	 */
	public double getHighF(){
		return (double)(high*1.8 + 32);
	}

	/**
	 * Gets the icon representing the weather
	 * @return Image representing the weather
	 */
	public Image getIcon() {
		return icon;
	}
	
	/**
	 * Returns String with the Icons file name
	 * @return String
	 */
	public String getIconCode(){
		try {
			JSONArray jInfo = currentJSONObject.getJSONArray("weather");
			JSONObject jInfo2 = jInfo.getJSONObject(0);
			String jInfo3 = jInfo2.getString("icon");
			return jInfo3;
		} catch (JSONException e) {
			return "";
		}
	}
	
	/**
	 * Gets the JSONObject for 24hr temperature 
	 * @return JSONObject representing 24hr temperature
	 */
	public JSONObject getForecast24hJSONObject() {
		return forecast24hJSONObject;
	}

	/**
	 * Gets the 24hr temperatures in an array 
	 * @return double array representing the 24hr temperatures 
	 */
	public double[] getTemp24h() {
		return temp24h;
	}
	
	public double[] getTemp24hF(){
		double[] d = new double[8];
		for (int i = 0; i < 8; i++){
			d[i] = temp24h[i]*1.8 + 32;
		}
		return d;
	}

	/**
	 * Gets the 24hr sky conditions in an array 
	 * @return String array representing the 24hr sky conditions
	 */
	public String[] getSkyCondition24h() {
		return skyCondition24h;
	}

	/**
	 * Gets the 24hr temperatures in an array 
	 * @return String array representing the 24hr temperatures 
	 */
	public String[] getDescription24h() {
		return description24h;
	}

	/**
	 * Gets the Images of 24hr temperatures in an array 
	 * @return Image array representing the 24hr temperature icons 
	 */
	public Image[] getIcon24h() {
		return icon24h;
	}
	
	public int getCNT24h() {
		return cnt24h;
	}

	// 5 day Forecast Getters
	
	/**
	 * Gets the JSONObject of 5day weather
	 * @return JSONObject of 5day weather forecast
	 */
	public JSONObject getForecast5dJSONObject() {
		return forecast5dJSONObject;
	}

	/**
	 * Gets the array of 5 day temperatures 
	 * @return double array of 5 day temperatures 
	 */
	public double[] getTemp5d() {
		return temp5d;
	}
	
	/**
	 * Gets the array of 5 day temperatures in Fahrenheit
	 * @return double array of 5 day fahrenheit temperatures
	 */
	public double[] getTemp5dF(){
		double[] d = new double[5];
		for (int i = 0; i < 5; i++){
			d[i] = temp5d[i]*1.8 + 32;
		}
		return d;
	}

	/**
	 * Gets the 5day sky conditions of the weather
	 * @return String array of 5days of sky conditions 
	 */
	public String[] getSkyCondition5d() {
		return skyCondition5d;
	}

	/**
	 * Gets the String array of 5day weather descriptions
	 * @return String array of 5day descriptions of the weather
	 */
	public String[] getDescription5d() {
		return description5d;
	}

	/**
	 * Getter for 5day precipitation
	 * @return int[] array  
	 */
	public int[] getPercentPreicipitation5d() {
		return percentPrecipitation5d;
	}

	/**
	 * Gets the Images of 5day temperatures in an array 
	 * @return Image array representing the 5day temperature icons 
	 */
	public Image[] getIcon5d() {
		return icon5d;
	}

	/**
	 * Gets array of 5day low temperatures
	 * @return double array representing the 5day low temperatures
	 */
	public double[] getLow5d() {
		return low5d;
	}
	
	/**
	 * Gets array of 5day low temperatures in fahrenheit
	 * @return double array representing the 5day low temperatures in fahrenheit
	 */
	public double[] getLow5dF(){
		double[] d = new double[5];
		for (int i = 0; i < 5; i++){
			d[i] = low5d[i]*1.8 + 32;
		}
		return d;
	}

	/**
	 * Gets array of 5day high temperatures
	 * @return double array representing the 5day high temperatures
	 */
	public double[] getHigh5d() {
		return high5d;
	}
	
	/**
	 * Gets array of 5day high temperatures in fahrenheit
	 * @return double array representing the 5day high temperatures in fahrenheit
	 */
	public double[] getHigh5dF(){
		double[] d = new double[5];
		for (int i = 0; i < 5; i++){
			d[i] = high5d[i]*1.8 + 32;
		}
		return d;
	}

	/**
	 * Gets number of available days for 5 day forecast
	 * @return int 
	 */
	public int getCNT5d(){
		return cnt5d;
	}
	
	// Mars Getters
	
	/**
	 * Gets JSONObject of Mars' weather info
	 * @return JSONObject consists of Mars' weather info
	 */
	public JSONObject getMarsJSONObject() {
		return marsJSONObject;
	}

	/**
	 * Gets the minimum temperature of Mars
	 * @return double representing the minimum temperature in Mars
	 */
	public double getMinTempMars() {
		return minTempMars;
	}
	
	/**
	 * Gets the maximum temperature of Mars
	 * @return double representing the maximum temperature in Mars
	 */
	public double getMaxTempMars() {
		return maxTempMars;
	}
	

	/**
	 * Gets the wind speed at Mars
	 * @return double representing the wind speed in Mars
	 */
	public double getWindSpeedMars() {
		return windSpeedMars;
	}

	/**
	 * Gets the wind direction in Mars
	 * @return String representing the wind direction in Mars
	 */
	public String getWindDirectionMars() {
		return windDirectionMars;
	}

	/**
	 * Gets the air pressure in Mars
	 * @return double representing the air pressure in Mars
	 */
	public double getPressureMars() {
		return pressureMars;
	}

	/**
	 * Gets the air humidity in Mars
	 * @return int representing the humidity in Mars
	 */
	public int getHumidityMars() {
		return humidityMars;
	}

	/**
	 * Gets the sky condition in Mars
	 * @return String representing the sky condition in Mars
	 */
	public String getSkyConditionMars() {
		return skyConditionMars;
	}

	/**
	 * Gets and Image representing the weather in Mars
	 * @return Image representing the weather in Mars
	 */
	public Image getIconMars() {
		return iconMars;
	}
	
	/**
	 * Setter for sky condition
	 * @param skyConditionCurrent is to be set 
	 */
	private void setSkyConditionCurrent() {
		try {
			JSONArray jInfo = currentJSONObject.getJSONArray("weather");
			JSONObject jInfo2 = jInfo.getJSONObject(0);
			String jInfo3 = jInfo2.getString("main");
			this.skyConditionCurrent = jInfo3;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for description
	 * @param description is to be set 
	 */
	private void setDescription() {
		try {
			JSONArray jInfo = currentJSONObject.getJSONArray("weather");
			JSONObject jInfo2 = jInfo.getJSONObject(0);
			String jInfo3 = jInfo2.getString("description");
			this.description = jInfo3;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for current temperature
	 * @param tempCurrent is to be set 
	 */
	private void setTempCurrent() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			double jInfo2 = jInfo.getDouble("temp");
			jInfo2 -= 273.15;
			double tempInput = Math.round(jInfo2 * 100.0) / 100.0;
			this.tempCurrent = tempInput;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for Sunset
	 * @param sunset is to be set 
	 */
	private void setSunset() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("sys");
			long jInfo2 = jInfo.getLong("sunset");
			Time t = new Time(jInfo2);
			this.sunset = t.getConverted();
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for sunrise
	 * @param sunrise is to be set 
	 */
	private void setSunrise() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("sys");
			long jInfo2 = jInfo.getLong("sunrise");
			Time t = new Time(jInfo2);
			this.sunrise = t.getConverted();
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter pressure
	 * @param pressure is to be set 
	 */
	private void setPressure() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			double jInfo2 = jInfo.getDouble("pressure");
			jInfo2 *= 0.1; // Convert to kPa
			double pressureInput = Math.round(jInfo2 * 100.0) / 100.0;
			this.pressure = pressureInput;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for humidity
	 * @param humidity is to be set 
	 */
	private void setHumidity() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			int jInfo2 = jInfo.getInt("humidity");
			this.humidity = jInfo2;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for Wind Speed
	 * @param windSpeed is to be set
	 */
	private void setWindSpeed() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("wind");
			double jInfo2 = jInfo.getDouble("speed");
			this.windSpeed = jInfo2;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for Wind Direction
	 * @param windDirection is to be set
	 */	
	private void setWindDirection() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("wind");
			double jInfo2 = jInfo.getDouble("deg");
			String direction = "";
			if (jInfo2 >= 0 && jInfo2 < 22.5) {
				direction = "North";
			} else if (jInfo2 >= 22.5 && jInfo2 < 67.5) {
				direction = "Northeast";
			} else if (jInfo2 >= 67.5 && jInfo2 < 112.5) {
				direction = "East";
			} else if (jInfo2 >= 112.5 && jInfo2 < 157.5) {
				direction = "Southeast";
			} else if (jInfo2 >= 157.5 && jInfo2 < 202.5) {
				direction = "South";
			} else if (jInfo2 >= 202.5 && jInfo2 < 247.5) {
				direction = "Southwest";
			} else if (jInfo2 >= 247.5 && jInfo2 < 292.5) {
				direction = "West";
			} else if (jInfo2 >= 292.5 && jInfo2 < 337.5) {
				direction = "Northwest";
			} else if (jInfo2 >= 337.5 && jInfo2 <= 360) {
				direction = "North";
			} else {
				direction = "Up";
			}
			this.windDirection = direction;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for high temperature
	 * @param high is to be set
	 */
	private void setHigh() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			double jInfo2 = jInfo.getDouble("temp_max");
			jInfo2 -= 273.15;
			jInfo2 = Math.round(jInfo2 * 100.0) / 100.0;
			this.high = jInfo2;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for low temperature
	 * @param low is to be set
	 */
	private void setLow() {
		try {
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			double jInfo2 = jInfo.getDouble("temp_min");
			jInfo2 -= 273.15;
			jInfo2 = Math.round(jInfo2 * 100.0) / 100.0;
			this.low = jInfo2;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for icon
	 * @param icon is to be set
	 */
	private void setIcon() {
		try {
			JSONArray jInfo = currentJSONObject.getJSONArray("weather");
			JSONObject jInfo2 = jInfo.getJSONObject(0);
			String jInfo3 = jInfo2.getString("icon");
			Image image = requestImage(jInfo3);
			this.icon = image;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	// //24 hour Forecast Setters

	/**
	 * Setter for Short Term Temperature
	 */
	private void setTemp24h() {
		try {
			double[] array = new double[8];
			JSONArray list = forecast24hJSONObject.getJSONArray("list");
			for (int i = 0; i < 8; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONObject jInfo3 = jInfo2.getJSONObject("main");
				double jInfo4 = jInfo3.getDouble("temp");
				jInfo4 -= 273.15; // Convert to celsius
				double arrayInput = Math.round(jInfo4 * 100.0) / 100.0;
				array[i] = arrayInput;
			}
			this.temp24h = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for short term forecast skycondition
	 */
	private void setSkyCondition24h() {
		try {
			String[] array = new String[8];
			JSONArray list = forecast24hJSONObject.getJSONArray("list");
			for (int i = 0; i < 8; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONArray jInfo3 = jInfo2.getJSONArray("weather");
				JSONObject jInfo4 = jInfo3.getJSONObject(0);
				String jInfo5 = jInfo4.getString("main");
				array[i] = jInfo5;
			}
			this.skyCondition24h = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for short term weather descriptions
	 */
	private void setDescription24h() {
		try {
			String[] array = new String[8];
			JSONArray list = forecast24hJSONObject.getJSONArray("list");
			for (int i = 0; i < 8; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONArray jInfo3 = jInfo2.getJSONArray("weather");
				JSONObject jInfo4 = jInfo3.getJSONObject(0);
				String jInfo5 = jInfo4.getString("description");
				array[i] = jInfo5;
			}
			this.description24h = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for short term icons
	 */
	private void setIcon24h() {
		try {
			Image[] array = new Image[8];
			JSONArray list = forecast24hJSONObject.getJSONArray("list");
			for (int i = 0; i < 8; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONArray jInfo3 = jInfo2.getJSONArray("weather");
				JSONObject jInfo4 = jInfo3.getJSONObject(0);
				String jInfo5 = jInfo4.getString("icon");
				array[i] = requestImage(jInfo5);
			}
			this.icon24h = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * setter for count (amount of data returned by the api)
	 */
	private void setCNT24h(){
		try {
			this.cnt24h = forecast24hJSONObject.getInt("cnt");
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Long Term Forecast Setters
	
	/**
	 * Setter for long term forecast temperature
	 */
	private void setTemp5d() {
		try {
			double[] array = new double[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONObject jInfo3 = jInfo2.getJSONObject("temp");
				double jInfo4 = jInfo3.getDouble("day");
				jInfo4 -= 273.15; // Convert to celsius
				double arrayInput = Math.round(jInfo4 * 100.0) / 100.0;
				array[i] = arrayInput;
			}
			this.temp5d = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for long term lows
	 */
	private void setLow5d() {
		try {
			double[] array = new double[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONObject jInfo3 = jInfo2.getJSONObject("temp");
				double jInfo4 = jInfo3.getDouble("min");
				jInfo4 -= 273.15; // Convert to celsius
				double arrayInput = Math.round(jInfo4 * 100.0) / 100.0;
				array[i] = arrayInput;
			}
			this.low5d = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for long term highs
	 */
	private void setHigh5d() {
		try {
			double[] array = new double[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONObject jInfo3 = jInfo2.getJSONObject("temp");
				double jInfo4 = jInfo3.getDouble("max");
				jInfo4 -= 273.15; // Convert to celsius
				double arrayInput = Math.round(jInfo4 * 100.0) / 100.0;
				array[i] = arrayInput;
			}
			this.high5d = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for long term sky conditions
	 */
	private void setSkyCondition5d() {
		try {
			String[] array = new String[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONArray jInfo3 = jInfo2.getJSONArray("weather");
				JSONObject jInfo4 = jInfo3.getJSONObject(0);
				String jInfo5 = jInfo4.getString("main");
				array[i] = jInfo5;
			}
			this.skyCondition5d = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for long term descriptions
	 */
	private void setDescription5d() {
		try {
			String[] array = new String[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONArray jInfo3 = jInfo2.getJSONArray("weather");
				JSONObject jInfo4 = jInfo3.getJSONObject(0);
				String jInfo5 = jInfo4.getString("description");
				array[i] = jInfo5;
			}
			this.description5d = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for long term icons
	 */
	private void setIcon5d() {
		try {
			Image[] array = new Image[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONArray jInfo3 = jInfo2.getJSONArray("weather");
				JSONObject jInfo4 = jInfo3.getJSONObject(0);
				String jInfo5 = jInfo4.getString("icon");
				array[i] = requestImage(jInfo5);
			}
			this.icon5d = array;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for long term count (number of days with data available)
	 */
	private void setCNT5d(){
		try {
			this.cnt5d = forecast5dJSONObject.getInt("cnt");
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	// Mars Weather Setters

	/**
	 * Setter for mars minimum temperature
	 */
	private void setMinTempMars() {
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			double jInfo2 = jInfo.getDouble("min_temp");
			double jInfo3 = Math.round(jInfo2 * 100.0) / 100.0;
			this.minTempMars = jInfo3;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for mars maximum temperature
	 */
	private void setMaxTempMars() {
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			double jInfo2 = jInfo.getDouble("max_temp");
			double jInfo3 = Math.round(jInfo2 * 100.0) / 100.0;
			this.maxTempMars = jInfo3;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for wind speed on mars
	 */
	private void setWindSpeedMars() {
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			double jInfo2 = jInfo.getDouble("wind_speed");
			double jInfo3 = Math.round(jInfo2 * 100.0) / 100.0;
			this.windSpeedMars = jInfo3;
		} catch (JSONException e) {
			this.windSpeedMars = 0;
		}
	}

	/**
	 * Setter for wind direction on mars
	 */
	private void setWindDirectionMars() {
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			String jInfo2 = jInfo.getString("wind_direction");
			this.windDirectionMars = jInfo2;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for pressure on mars
	 */
	private void setPressureMars() {
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			double jInfo2 = jInfo.getDouble("pressure");
			double jInfo3 = Math.round(jInfo2 * 100.0) / 100.0;
			this.pressureMars = jInfo3;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for humidity on mars
	 */
	private void setHumidityMars() {
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			int jInfo2 = jInfo.getInt("abs_humidity");
			this.humidityMars = jInfo2;
		} catch (JSONException e) {
			this.humidityMars = 0;
		}
	}

	/**
	 * Setter for sky condition on mars
	 */
	private void setSkyConditionMars() {
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			String jInfo2 = jInfo.getString("atmo_opacity");
			this.skyConditionMars = jInfo2;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setter for icon on mars
	 */
	private void setMarsIcon() {
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			String jInfo2 = jInfo.getString("atmo_opacity");
			Image marsIcon;
			if (jInfo2.equalsIgnoreCase("Sunny")) {
				marsIcon = requestImage("01d");
			} else {
				marsIcon = requestImage("11d");
				System.out.println("But it's always sunny on Mars!");
			}
			this.iconMars = marsIcon;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method to test validity of a JSONObject
	 * @param object JSONObject object
	 * @return true if object is a valid JSON, false otherwise
	 */
	public static boolean testValidity(JSONObject object) {
		try {
			int jInfo = object.getInt("cod");
			if (jInfo == 404) {
				return false;
			} else {
				return true;
			}
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// Method to request an image from Open Weather Map
	/**
	 * Method to request an Image from OpenWeatherMap API
	 * @param info : image icon code
	 * @return the image, or null if no image is available
	 */
	private static Image requestImage(String info) {
		String imageURL = imgURL + info + ".png";
		try {
			URL url = new URL(imageURL);
			Image image = ImageIO.read(url);
			return image;
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Method to request Mars Weather JSON data
	 * @param url : URL to request mars data
	 * @return JSONObject representing the weather on Mars
	 */
	private static JSONObject requestMarsData(String url) {
		JSONObject json = new JSONObject();
		try {
			InputStream stream = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, Charset.forName("UTF-8")));
			String jsondata = readAll(reader);
			json = new JSONObject(jsondata);
			stream.close();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		return json;
	}

	/**
	 * Helper method for requesting Mars Data
	 * @param reader : BufferedReader
	 * @return String representing the Mars JSONObject
	 * @throws IOException
	 */
	private static String readAll(Reader reader) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		int current;
		while ((current = reader.read()) != -1) {
			stringBuilder.append((char) current);
		}
		return stringBuilder.toString();
	}
}