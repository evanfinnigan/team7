package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InputTest {

	final static String currentURL = "http://api.openweathermap.org/data/2.5/weather?q=";
	final static String forcast5dURL = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=5&q=";
	final static String forcast24hURL = "http://api.openweathermap.org/data/2.5/forecast?cnt=8&q=";
	final static String appId = "c4b344fb48bf77d11a102795333fea60";

	boolean valid;
	JSONObject currentWeather;
	JSONObject forecast24h;
	JSONObject forecast5d;
	String cityName;
	String timeOfLastRequest;

	/**
	 * Constructor takes a string(city name)
	 * @param city User's input of city name to be tested later
	 */
	public InputTest(String city) {
		testCity(city);
	}

	/**
	 * Gets time of last request
	 * @return timeOfLastRequest String
	 */
	public String getTime() {
		return timeOfLastRequest;
	}

	/**
	 * Returns true if the InputTest is valid, false otherwises
	 * @return boolean checks if InputTest is valid
	 */
	public boolean getValid() {
		return valid;
	}

	/**
	 * Gets the CurrentWeather
	 * @return JSONObject 
	 */
	public JSONObject getCurrentWeather() {
		return currentWeather;
	}
	
	/**
	 * Gets the 24 Hour Forecast
	 * @return JSONObject
	 */
	public JSONObject getForecast24h() {
		return forecast24h;
	}
	
	/**
	 * Gets the 5 day Forecast
	 * @return JSONObject
	 */
	public JSONObject getForecast5d() {
		return forecast5d;
	}

	/**
	 * Gets the City Name
	 * @return String
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Tests if the requested data is complete
	 * @param city String as the city name
	 */
	private void testCity(String city) {
		try {
			this.valid = true;
			this.currentWeather = requestData(city, currentURL);
			this.forecast24h = requestData(city, forcast24hURL);
			this.forecast5d = requestData(city, forcast5dURL);

			int cod = currentWeather.getInt("cod");
			if (cod != 200) {
				this.valid = false;
				System.out.println("current weather code failure");
			}
			
			cod = forecast24h.getInt("cod");
			
			if (cod != 200) {
				this.valid = false;
				System.out.println("24 hour forecast code failure");
			}
			
			cod = forecast5d.getInt("cod");
			
			if (cod != 200) {
				this.valid = false;
				System.out.println("5 day forecast code failure");
			}
			
			JSONArray a = forecast5d.getJSONArray("list");
			
			if (a.length() < 5) {
				this.valid = true;
				System.out.println("Some data may be unavailable");
			}

			String citytest = currentWeather.getString("name");
			String country = currentWeather.getJSONObject("sys").getString(
					"country");
			if (citytest.equals("")) {
				this.cityName = city;
			} else {
				this.cityName = citytest + ", " + country;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.valid = false;
		}
	}

	/**
	 * Method to request JSON data from OpenWeatherMap API
	 * @param city String as the city name
	 * @param url API URL
	 * @return JSONObject with the input data 
	 */
	private JSONObject requestData(String city, String url) {
		JSONObject json = new JSONObject();
		try {
			InputStream stream = new URL(url + "" + city + "&APPID=" + appId).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, Charset.forName("UTF-8")));
			String jsondata = dataToString(reader);
			json = new JSONObject(jsondata);
			

			stream.close();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			this.valid = false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			this.valid = false;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			this.valid = false;
		}
		return json;
	}

	 
	/**
	 * Helper Method for requesting data
	 * @param rjson java.io.Reader
	 * @return String of the data
	 * @throws IOException
	 */
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

}
