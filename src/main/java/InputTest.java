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

	boolean valid;
	JSONObject currentWeather;
	String cityName;
	String timeOfLastRequest;
	

	public InputTest(String city) {
		testCity(city);
	}

	public String getTime(){
		return timeOfLastRequest;
	}
	
	public boolean getValid() {
		return valid;
	}

	public JSONObject getCurrentWeather() {
		return currentWeather;
	}

	public String getCityName() {
		return cityName;
	}

	private void testCity(String city) {
		this.currentWeather = requestData(city, currentURL);
		try {
			int jInfo = currentWeather.getInt("cod");
			if (jInfo == 404) {
				this.valid = false;
			} else {
				this.valid = true;
				String cityname = currentWeather.getString("name");
				JSONObject j = currentWeather.getJSONObject("sys");
				String country = j.getString("country");
				if (cityname.equalsIgnoreCase("")){
					this.cityName = city;
				} else {
					this.cityName = cityname + ", " + country;
				}
			}
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			this.valid = false;
		}
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
			try {
				long jInfo = json.getLong("dt");
				Time t = new Time(jInfo);
				this.timeOfLastRequest = t.getConverted();
			} catch (Exception e) {
				System.out.println(e.getMessage());
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

}
