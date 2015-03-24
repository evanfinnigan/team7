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
	final static String forcast24hURL = "http://api.openweathermap.org/data/2.5/forecast?q=";

	boolean valid;
	JSONObject currentWeather;
	JSONObject forecast24h;
	JSONObject forecast5d;
	String cityName;
	String timeOfLastRequest;

	public InputTest(String city) {
		testCity(city);
	}

	public String getTime() {
		return timeOfLastRequest;
	}

	public boolean getValid() {
		return valid;
	}

	public JSONObject getCurrentWeather() {
		return currentWeather;
	}

	public JSONObject getForecast24h() {
		return forecast24h;
	}

	public JSONObject getForecast5d() {
		return forecast5d;
	}

	public String getCityName() {
		return cityName;
	}

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
				this.valid = false;
				System.out.println("5 day forecast list is too short");
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

	// Method to request JSON data from OpenWeatherMap API
	private JSONObject requestData(String city, String url) {
		JSONObject json = new JSONObject();
		try {
			InputStream stream = new URL(url + "" + city).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, Charset.forName("UTF-8")));
			String jsondata = dataToString(reader);
			json = new JSONObject(jsondata);
			if (url.equalsIgnoreCase(currentURL)) {
				try {
					int jInfo = json.getInt("dt");
					Time t = new Time(jInfo);
					this.timeOfLastRequest = t.getConverted();
				} catch (Exception e) {
					System.out.println("Time was not updated.");
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

}
