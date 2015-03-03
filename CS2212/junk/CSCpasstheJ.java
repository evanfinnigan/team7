import gvjava.org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CSCpasstheJ {
	// reads all chars from data stream and returns a string 
	private static String data2string(Reader rjson) throws IOException{
		StringBuilder jstring = new StringBuilder();
		int letter;
		try {
			while ((letter = rjson.read()) != -1){
				jstring.append((char)letter);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jstring.toString();
	}
	// takes a url to openweathermap.org and returns a json object  
	public static JSONObject requestJson(String url) {
		InputStream stream;
		JSONObject json;
		try {
			stream = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
		      String jsonText = data2string(rd);
		      json = new JSONObject(jsonText);
		      return json;
		} catch (MalformedURLException e) {
					e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	// if here uh-oh...
	return null;
		
	}

/*	public static void main(String[] args) {
		
		JSONObject currentdata = requestJson(" http://api.openweathermap.org/data/2.5/weather?q=london,ca");
	    System.out.println(currentdata.toString());
	    public void presentData(JSONObject currentdata){
	    
	      try {
			
			// mine json data
	    	// sunrise, sunset
	    	JSONObject jbomb = currentdata.getJSONObject("sys");
	    	int sunrise = jbomb.getInt("sunrise");
	    	int sunset = jbomb.getInt("sunset");
	
	    	Date date = new Date(sunrise*1000L); // *1000 is to convert seconds to milliseconds
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
	    	//sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
	    	String formattedsunrise = sdf.format(date);
	    	System.out.println(formattedsunrise);
	    	
	    	date = new Date(sunset*1000L); // *1000 is to convert seconds to milliseconds
	        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
	    	//sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
	    	String formattedsunset = sdf.format(date);
	    	System.out.println(formattedsunset);
	    	
	    	// description and details
	    	JSONArray conditions = currentdata.getJSONArray("weather");
	    	jbomb = (JSONObject) conditions.get(0);
	    	String description = jbomb.getString("main");
	    	String details = jbomb.getString("description");
	    	
	    	// wind
	        jbomb = currentdata.getJSONObject("wind");
			double windspeed = jbomb.getDouble("speed");
			double winddirection = jbomb.getDouble("deg");
			
			// temp, air pressure, humidity  
			jbomb = currentdata.getJSONObject("main");
			
			double temp = (jbomb.getDouble("temp") - 272.15);
			double temp_max = (jbomb.getDouble("temp_max") - 272.15);
			double temp_min = (jbomb.getDouble("temp_min") - 272.15);
			double humidity = jbomb.getDouble("humidity");
			double airpressure = jbomb.getDouble("pressure");
			// out-->
			System.out.printf("wind speed: %.2f%n", windspeed);
			System.out.printf("wind direction: %.3f%n", winddirection);
			
			System.out.printf("temp: %.2f degrees c%n", temp);
			System.out.printf("temp_max: %.2f degrees c%n", temp_max);
			System.out.printf("temp_min: %.2f degrees c%n", temp_min);
			
			System.out.printf("humidity: %.2f%n", humidity);
			System.out.printf("air pressure: %.2f%n", airpressure);
			
			System.out.printf("sunrise %s%n",formattedsunrise);
			System.out.printf("sunset %s%n",formattedsunset);
			
			System.out.printf("Current conditions: %s%n",description);
			System.out.printf("Details: %s%n",details);
			
			
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
*/
}
