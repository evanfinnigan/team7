package main.java;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Time {
	
	// Attributes
	private String convertedTimeData;
	
	// Constructor
	public Time(long rawTimeData){
		this.convertedTimeData = formatTime(rawTimeData);
	}
	
	// Methods
	public String getConverted(){
		return convertedTimeData;
	}
	
	//Format time
	private static String formatTime(long utc){
		utc = utc*1000;
		TimeZone est = TimeZone.getTimeZone("EST");
		Date date = new Date(utc);
		DateFormat format = new SimpleDateFormat("h:mm a z ");
		format.setTimeZone(est);
		String converted = format.format(date);
		return converted;
	}
	
	// Test
	public static void main(String[] args){
		long utcExample = 1427599999;
		String ex = formatTime(utcExample);
		System.out.println(ex);
	}
	
}