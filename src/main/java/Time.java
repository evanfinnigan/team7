package main.java;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Time {
	
	// Attributes
	private String convertedTimeData;
	
	/**
	 * Constructor for a time object
	 * @param rawTimeData
	 */
	public Time(long rawTimeData){
		this.convertedTimeData = formatTime(rawTimeData);
	}
	
	/**
	 * Method to get the converted time as a pretty string
	 * @return converted time 
	 */
	public String getConverted(){
		return convertedTimeData;
	}
	
	/**
	 * Mthod to get the current time
	 * @return current time as string
	 */
	public String getCurrent(){
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("h:mm a z ");
		TimeZone est = TimeZone.getTimeZone("America/Toronto");
		format.setTimeZone(est);
		String converted = format.format(date);
		return converted;
	}
	
	/**
	 * Method to format the time to a pretty string
	 * @param utc
	 * @return nicely formatted time string
	 */
	private static String formatTime(long utc){
		utc = utc*1000;
		TimeZone est = TimeZone.getTimeZone("America/Toronto");
		Date date = new Date(utc);
		DateFormat format = new SimpleDateFormat("h:mm a z ");
		format.setTimeZone(est);
		String converted = format.format(date);
		return converted;
	}
	
}