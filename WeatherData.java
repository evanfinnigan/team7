package team7;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;

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
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/**
 * @author Evan
 * 
 */
public class WeatherData {

	final static String currentURL = "http://api.openweathermap.org/data/2.5/weather?q=";
	final static String forcast5dURL = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=5&q=";
	final static String forcast24hURL = "http://api.openweathermap.org/data/2.5/forecast?&q=";
	final static String imgURL = "http://openweathermap.org/img/w/";
	final static String marsURL = "http://marsweather.ingenology.com/v1/latest/?format=json";

	//Attributes
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
	private int[] percentPrecipitation24h;
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

	// Constructor
	public WeatherData(String city) {
		this.currentJSONObject = requestData(city, currentURL);
		this.forecast24hJSONObject = requestData(city, forcast24hURL);
		this.forecast5dJSONObject = requestData(city, forcast5dURL);
		this.marsJSONObject = requestMarsData(marsURL);
		
		//Initialize Current Weather Variables
		setCityName();
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
		
		//Initialize 24 hour forecast variables
		setTemp24h();
		setSkyCondition24h();
		setDescription24h();
		setIcon24h();
		
		//Initialize 5 day forecast variables
		setTemp5d();
		setSkyCondition5d();
		setDescription5d();
		setIcon5d();
		setLow5d();
		setHigh5d();
		
		//Initialize Mars variables
		setMinTempMars();
		setMaxTempMars();
		setWindSpeedMars();
		setWindDirectionMars();
		setPressureMars();
		setHumidityMars();
		setSkyConditionMars();
	}

	// Getters

	public String getTimeOfLastRequest() {
		return timeOfLastRequest;
	}

	// Current Weather Getters
	public JSONObject getCurrentJSONObject() {
		return currentJSONObject;
	}

	public String getCityName() {
		return cityName;
	}

	public String getSkyConditionCurrent() {
		return skyConditionCurrent;
	}

	public String getDescription() {
		return description;
	}

	public double getTempCurrent() {
		return tempCurrent;
	}

	public String getSunset() {
		return sunset;
	}

	public String getSunrise() {
		return sunrise;
	}

	public double getPressure() {
		return pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public double getLow() {
		return low;
	}

	public double getHigh() {
		return high;
	}

	public Image getIcon() {
		return icon;
	}

	// 24 hour Forecast Getters
	public JSONObject getForecast24hJSONObject() {
		return forecast24hJSONObject;
	}

	public double[] getTemp24h() {
		return temp24h;
	}

	public String[] getSkyCondition24h() {
		return skyCondition24h;
	}
	
	public String[] getDescription24h() {
		return description24h;
	}
	
	public int[] getPercentPreicipitation24h(){
		return percentPrecipitation24h;
	}

	public Image[] getIcon24h() {
		return icon24h;
	}

	// 5 day Forecast Getters
	public JSONObject getForecast5dJSONObject() {
		return forecast5dJSONObject;
	}

	public double[] getTemp5d() {
		return temp5d;
	}

	public String[] getSkyCondition5d() {
		return skyCondition5d;
	}
	
	public String[] getDescription5d() {
		return description5d;
	}
	
	public int[] getPercentPreicipitation5d(){
		return percentPrecipitation5d;
	}

	public Image[] getIcon5d() {
		return icon5d;
	}

	public double[] getLow5d() {
		return low5d;
	}

	public double[] getHigh5d() {
		return high5d;
	}

	// Mars Getters
	public JSONObject getMarsJSONObject() {
		return marsJSONObject;
	}

	public double getMinTempMars() {
		return minTempMars;
	}

	public double getMaxTempMars() {
		return maxTempMars;
	}
	
	public double getWindSpeedMars() {
		return windSpeedMars;
	}

	public String getWindDirectionMars() {
		return windDirectionMars;
	}

	public double getPressureMars() {
		return pressureMars;
	}

	public int getHumidityMars() {
		return humidityMars;
	}

	public String getSkyConditionMars() {
		return skyConditionMars;
	}

	public Image getIconMars() {
		return iconMars;
	}

	// Setters

	// Current Weather Setters
	private void refreshCurrentJSONObject(String city) {
		this.currentJSONObject = requestData(city, currentURL);
	}

	private void setCityName() {
		try{
			String jInfo = currentJSONObject.getString("name");
			jInfo = jInfo + ", " + currentJSONObject.getJSONObject("sys").getString("country");
			this.cityName = jInfo;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}

	private void setSkyConditionCurrent() {
		try{
			JSONArray jInfo = currentJSONObject.getJSONArray("weather");
			JSONObject jInfo2 = jInfo.getJSONObject(0);
			String jInfo3 = jInfo2.getString("main");
			this.skyConditionCurrent = jInfo3;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setDescription() {
		try{
			JSONArray jInfo = currentJSONObject.getJSONArray("weather");
			JSONObject jInfo2 = jInfo.getJSONObject(0);
			String jInfo3 = jInfo2.getString("description");
			this.description = jInfo3;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setTempCurrent() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			double jInfo2 = jInfo.getDouble("temp");
			jInfo2 -= 273.15;
			double tempInput = Math.round(jInfo2 * 100.0) / 100.0;
			this.tempCurrent = tempInput;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setSunset() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("sys");
			long jInfo2 = jInfo.getLong("sunset");
			Time t = new Time(jInfo2);
			this.sunset = t.getConverted();
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setSunrise() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("sys");
			long jInfo2 = jInfo.getLong("sunrise");
			Time t = new Time(jInfo2);
			this.sunrise = t.getConverted();
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setPressure() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			double jInfo2 = jInfo.getDouble("pressure");
			jInfo2 *= 0.1; //Convert to kPa
			double pressureInput = Math.round(jInfo2 * 100.0) / 100.0;
			this.pressure = pressureInput;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setHumidity() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			int jInfo2 = jInfo.getInt("humidity");
			this.humidity = jInfo2;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setWindSpeed() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("wind");
			double jInfo2 = jInfo.getDouble("speed");
			this.windSpeed = jInfo2;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setWindDirection() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("wind");
			double jInfo2 = jInfo.getDouble("deg");
			String direction = "";
			if (jInfo2 >= 0 && jInfo2 < 22.5){
				direction = "North";
			} else if (jInfo2 >= 22.5 && jInfo2 < 67.5){
				direction = "Northeast";
			} else if (jInfo2 >= 67.5 && jInfo2 < 112.5){
				direction = "East";
			} else if (jInfo2 >= 112.5 && jInfo2 < 157.5){
				direction = "Southeast";
			} else if (jInfo2 >= 157.5 && jInfo2 < 202.5){
				direction = "South";
			} else if (jInfo2 >= 202.5 && jInfo2 < 247.5){
				direction = "Southwest";
			} else if (jInfo2 >= 247.5 && jInfo2 < 292.5){
				direction = "West";
			} else if (jInfo2 >= 292.5 && jInfo2 < 337.5){
				direction = "Northwest";
			} else if (jInfo2 >= 337.5 && jInfo2 <= 360){
				direction = "North";
			} else {
				direction = "Up";
			}
			this.windDirection = direction;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setHigh() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			double jInfo2 = jInfo.getDouble("temp_max");
			jInfo2 -= 273.15;
			this.high = jInfo2;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setLow() {
		try{
			JSONObject jInfo = currentJSONObject.getJSONObject("main");
			double jInfo2 = jInfo.getDouble("temp_min");
			jInfo2 -= 273.15;
			this.low = jInfo2;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setIcon() {
		try{
			JSONArray jInfo = currentJSONObject.getJSONArray("weather");
			JSONObject jInfo2 = jInfo.getJSONObject(0);
			String jInfo3 = jInfo2.getString("icon");
			Image image = requestImage(jInfo3);
			this.icon = image;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}

	// //24 hour Forecast Setters
	private void refreshForecast24hJSONObject(String city){
	this.forecast24hJSONObject = requestData(city, forcast24hURL);
	}

	//get temperature for the next 24 hours in 3 hour increments
	private void setTemp24h() {
		try{
			double[] array = new double[8];
			JSONArray list = forecast24hJSONObject.getJSONArray("list");
			for (int i = 0; i < 8; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONObject jInfo3 = jInfo2.getJSONObject("main");
				double jInfo4 = jInfo3.getDouble("temp");
				jInfo4 -= 273.15; //Convert to celsius
				double arrayInput = Math.round(jInfo4 * 100.0) / 100.0;
				array[i] = arrayInput;
			}
			this.temp24h = array;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setSkyCondition24h() {
		try{
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
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setDescription24h() {
		try{
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
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setIcon24h() {
		try{
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
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
//	private int[] percentPrecipitation24h;

	//5 day Forecast Setters
	private void refreshForecast5dJSONObject(String city){
	this.forecast5dJSONObject = requestData(city, forcast5dURL);
	}

	private void setTemp5d() {
		try{
			double[] array = new double[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONObject jInfo3 = jInfo2.getJSONObject("temp");
				double jInfo4 = jInfo3.getDouble("day");
				jInfo4 -= 273.15; //Convert to celsius
				double arrayInput = Math.round(jInfo4 * 100.0) / 100.0;
				array[i] = arrayInput;
			}
			this.temp5d = array;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setLow5d() {
		try{
			double[] array = new double[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONObject jInfo3 = jInfo2.getJSONObject("temp");
				double jInfo4 = jInfo3.getDouble("min");
				jInfo4 -= 273.15; //Convert to celsius
				double arrayInput = Math.round(jInfo4 * 100.0) / 100.0;
				array[i] = arrayInput;
			}
			this.low5d = array;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setHigh5d() {
		try{
			double[] array = new double[5];
			JSONArray list = forecast5dJSONObject.getJSONArray("list");
			for (int i = 0; i < 5; i++) {
				JSONObject jInfo2 = list.getJSONObject(i);
				JSONObject jInfo3 = jInfo2.getJSONObject("temp");
				double jInfo4 = jInfo3.getDouble("max");
				jInfo4 -= 273.15; //Convert to celsius
				double arrayInput = Math.round(jInfo4 * 100.0) / 100.0;
				array[i] = arrayInput;
			}
			this.high5d = array;
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setSkyCondition5d() {
		try{
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
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}

	private void setDescription5d() {
		try{
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
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setIcon5d() {
		try{
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
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
//	private int[] percentPrecipitation5d;

	//Mars Weather Setters
	private void refreshMarsJSONObject(){
	this.marsJSONObject = requestMarsData(marsURL);
	}

	private void setMinTempMars(){
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			double jInfo2 = jInfo.getDouble("min_temp");
			double jInfo3 = Math.round(jInfo2 * 100.0) / 100.0;
			this.minTempMars = jInfo3;
		} catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setMaxTempMars(){
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			double jInfo2 = jInfo.getDouble("max_temp");
			double jInfo3 = Math.round(jInfo2 * 100.0) / 100.0;
			this.maxTempMars = jInfo3;
		} catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setWindSpeedMars(){
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			double jInfo2 = jInfo.getDouble("wind_speed");
			double jInfo3 = Math.round(jInfo2 * 100.0) / 100.0;
			this.windSpeedMars = jInfo3;
		} catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}

	private void setWindDirectionMars(){
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			String jInfo2 = jInfo.getString("wind_direction");
			this.windDirectionMars = jInfo2;
		} catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setPressureMars(){
		try{
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			double jInfo2 = jInfo.getDouble("pressure");
			this.pressureMars = jInfo2;
		} catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setHumidityMars(){
		try{
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			int jInfo2 = jInfo.getInt("abs_humidity");
			this.humidityMars = jInfo2;
		} catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void setSkyConditionMars(){
		try {
			JSONObject jInfo = marsJSONObject.getJSONObject("report");
			String jInfo2 = jInfo.getString("atmo_opacity");
			this.skyConditionMars = jInfo2;
		} catch (JSONException e){
			System.out.println(e.getMessage());
		}
	}
	
//	private ImageIO iconMars;

	// Method to test if the City Name is correct and returns a valid JSON object
	public static boolean testValidity(JSONObject object) {
		try{
			int jInfo = object.getInt("cod");
			if (jInfo == 404){
				return false;
			} else {
				return true;
			}
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	// Method to request an image from Open Weather Map
	private static Image requestImage(String info) {
		String imageURL = imgURL + info + ".png";
		try {
			URL url = new URL(imageURL);
			Image image = ImageIO.read(url);
			return image;
		} catch (MalformedURLException e){
			System.out.println(e.getMessage());
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Method to request Mars Weather JSON data
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

	// Helper Method realAll for requestMarsData
	private static String readAll(Reader reader) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		int current;
		while ((current = reader.read()) != -1) {
			stringBuilder.append((char) current);
		}
		return stringBuilder.toString();
	}

	// Method to request JSON data from OpenWeatherMap API
	private JSONObject requestData(String city, String url) {
		JSONObject json = new JSONObject();
		try {
			InputStream stream = new URL(url + "" + city).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, Charset.forName("UTF-8")));
			String jsondata = dataToString(reader);
			json = new JSONObject(jsondata);
			if (testValidity(json) && url.equals(currentURL)){
				try {
					long jInfo = json.getLong("dt");
					Time t = new Time(jInfo);
					this.timeOfLastRequest = t.getConverted();
				} catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
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

	// Helper Method dataToString for requestData
	private static String dataToString(Reader rjson) throws IOException {
		StringBuilder jstring = new StringBuilder();
		int letter;
		try {
			while ((letter = rjson.read()) != -1) {
				jstring.append((char) letter);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return jstring.toString();
	}
	
	// Helper Test method for displaying icons
	private static void iconTest(Image im){
		JLabel lblimg = new JLabel(new ImageIcon(im));
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.add(lblimg);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	// Method to update the data
	public void refreshAndUpdate(String city){
		
		refreshCurrentJSONObject(city);
		refreshForecast24hJSONObject(city);
		refreshForecast5dJSONObject(city);
		refreshMarsJSONObject();
		
		//Initialize Current Weather Variables
		setCityName();
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
		
		//Initialize 24 hour forecast variables
		setTemp24h();
		setSkyCondition24h();
		setDescription24h();
		setIcon24h();
				
		//Initialize 5 day forecast variables
		setTemp5d();
		setSkyCondition5d();
		setDescription5d();
		setIcon5d();
		setLow5d();
		setHigh5d();
				
		//Initialize Mars variables
		setMinTempMars();
		setMaxTempMars();
		setWindSpeedMars();
		setWindDirectionMars();
		setPressureMars();
		setHumidityMars();
		setSkyConditionMars();
	}
	
	// Main Test Method
	public static void main(String[] args) {
		
		WeatherData test = new WeatherData("London,ON");
		
		// Test cityName methods
		System.out.println("City: " + test.getCityName());
		
		// Test skyCondition methods
		System.out.println("Sky Condition: " + test.getSkyConditionCurrent());
		
		// Test description methods
		System.out.println("Description: " + test.getDescription());
		
		// Test tempCurrent methods
		System.out.println("Temperature: " + test.getTempCurrent() + " degrees Celsius");
		
		// Test sunset methods
		System.out.println("Sunset: " + test.getSunset());
		
		// Test sunrise methods
		System.out.println("Sunrise: " + test.getSunrise());
		
		// Test pressure methods
		System.out.println("Pressure: " + test.getPressure() + " kPa");
		
		// Test humidity methods
		System.out.println("Humidity: " + test.getHumidity() + "%");
		
		// Test windSpeed  methods
		System.out.println("Wind Speed: " + test.getWindSpeed() + " m/s ");
		
		// Test windDirection  methods
		System.out.println("Wind Direction: " + test.getWindDirection());
		
		// Test high methods
		System.out.println("High: " + test.getHigh());
		
		// Test low methods
		System.out.println("Low: " + test.getLow());
		
		// Test icon methods
		Image im = test.getIcon();
		iconTest(im);
		
		// Test temp24h methods
		double[] temp24array = test.getTemp24h();
		for (int i = 0; i < 8; i++){
			int j = 3*(i+1);
			System.out.println("Temperature in " + j + " hours: " + temp24array[i] + " degrees Celsius");
		}
		
		// Test skyCondition24h & description24h methods
		String[] skyCondition24array = test.getSkyCondition24h();
		String[] description24array = test.getDescription24h();
		for (int i = 0; i < 8; i++){
			int j = 3*(i+1);
			System.out.println("Sky Condition in " + j + " hours: " + skyCondition24array[i] + " (" + description24array[i] + ")");
		}
		
		// Test 24h icon array
		Image[] icon24array = test.getIcon24h();
		for (int i = 0; i < 8; i++){
			iconTest(icon24array[i]);
		}
		
		// Test temp5d methods
		double[] temp5darray = test.getTemp5d();
		for (int i = 0; i < 5; i++){
			int j = i+1;
			System.out.println("Temperature in " + j + " days: " + temp5darray[i] + " degrees Celsius");
		}
		
		// Test skyCondition5d & description5d methods
		String[] skyCondition5darray = test.getSkyCondition5d();
		String[] description5darray = test.getDescription5d();
		for (int i = 0; i < 5; i++){
			int j = i+1;
			System.out.println("Sky Condition in " + j + " days: " + skyCondition5darray[i] + " (" + description5darray[i] + ")");
		}
				
		// Test 5d icon array
		Image[] icon5darray = test.getIcon5d();
		for (int i = 0; i < 5; i++){
			iconTest(icon5darray[i]);
		}
		
		// Test low5d methods
		double[] low5darray = test.getLow5d();
		for (int i = 0; i < 5; i++){
			int j = i+1;
			System.out.println("Low in " + j + " days: " + low5darray[i] + " degrees Celsius");
		}
				
		// Test high5d methods
		double[] high5darray = test.getTemp5d();
		for (int i = 0; i < 5; i++){
			int j = i+1;
			System.out.println("High in " + j + " days: " + high5darray[i] + " degrees Celsius");
		}
		
		// Test Mars Sky Condition
		System.out.println("Mars Sky Condition: " + test.getSkyConditionMars());
		
		// Test Mars temp min & max
		System.out.println("Mars Temperature >>> MIN: " + test.getMinTempMars() + " degrees Celsius, MAX: " + test.getMaxTempMars() + " degrees Celsius");
		
		// Test Mars Windspeed & direction
		System.out.println("Mars Wind Speed: " + test.getWindSpeedMars() + " Direction: " + test.getWindDirectionMars());
		
		// Test Pressure Mars
		System.out.println("Mars Pressure: " + test.getPressureMars() + " units? ATM, kPa, hPa, ???");
		
		// Test Humidity Mars
		System.out.println("Mars Humidity: " + test.getHumidityMars() + "%");
		
		// Test timeOfLastRequest
		System.out.println("Most Recent Update: " + test.getTimeOfLastRequest());
		
	}

}