package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Forecast5Day extends TransparentPanel{

	// Attributes
	private Panel5day panel;
	private Font minorfont = new Font("sml", Font.PLAIN, 12);
	/**
	 *  Forecast5Day constructor
	 * @param data WeatherData data model used to construct this object
	 * @param preferences WeatherPreferences user preferences
	 */
	public Forecast5Day(WeatherData data, WeatherPreferences preferences) {
		initComponents(data,preferences);
	}
	/**
	 * Updates the Temperature Labels in each of the 5 day blocks
	 * @param data WeatherData
	 * @param preferences WeatherPreferences
	 */
	public void updateTemps(WeatherData data, WeatherPreferences preferences){
		JLabel[] templabel = new JLabel[5];
		JLabel[] highlabel = new JLabel[5];
		JLabel[] lowlabel = new JLabel[5];
		double[] tempArray = new double[5];
		double[] highArray = new double[5];
		double[] lowArray = new double[5];
		tempArray = data.getTemp5d();
		highArray = data.getHigh5d();
		lowArray = data.getLow5d();
		for (int i=0;i<5;i++){
			templabel[i] = new JLabel((int)tempArray[i] + "\u00b0" + preferences.getTempUnit());
			templabel[i].setFont(minorfont);

			highlabel[i] = new JLabel("High:" + (int) highArray[i] + "\u00b0" + preferences.getTempUnit());
			highlabel[i].setFont(minorfont);

			lowlabel[i] = new JLabel("Low:" + (int)lowArray[i] + "\u00b0" + preferences.getTempUnit());
			lowlabel[i].setFont(minorfont);
		}
		panel.updateTemp(templabel);
	}
	/**
	 * Convenience method
	 * @return Forecast5day object which is a TransparentPanel
	 */
	public Panel5day getPanel() {
		return this.panel;
	}
	/**
	 * Private method that initializes components 
	 */
	private void initComponents(WeatherData data,WeatherPreferences preferences) {
		// create labels to display basic data
		
		Font minorfont = new Font("sml", Font.PLAIN, 15);
		

		JLabel[] wdlabel = new JLabel[5];
		JLabel[] sclabel = new JLabel[5];
		JLabel[] templabel = new JLabel[5];
		JLabel[] imagelabel = new JLabel[5];
		JLabel[] highlabel = new JLabel[5];
		JLabel[] lowlabel = new JLabel[5];

		String[] descriptionArray = data.getDescription5d();
		Image[] imgArray = data.getIcon5d();
		
		double[] tempArray;
		double[] highArray;
		double[] lowArray;
		
		if (preferences.getTempUnit().equalsIgnoreCase("F")){
			tempArray = data.getTemp5dF();
			highArray = data.getHigh5dF();
			lowArray = data.getLow5dF();
		} else {
			tempArray = data.getTemp5d();
			highArray = data.getHigh5d();
			lowArray = data.getLow5d();
		}
		
		int cnt = data.getCNT5d();
		
		Date date = new Date();
		for (int i = 0; i < cnt; i++) {
			date.setDate(date.getDate() + 1);
			DateFormat format = new SimpleDateFormat("EEEEEEEE, MMMMMMMMM dd");
			TimeZone est = TimeZone.getTimeZone("America/Toronto");
			format.setTimeZone(est);
			String j = format.format(date);
			
			
			wdlabel[i] = new JLabel( j );
			wdlabel[i].setFont(minorfont);

			String temp = descriptionArray[i].substring(0, 1).toUpperCase() + descriptionArray[i].substring(1);
			sclabel[i] = new JLabel(temp);
			sclabel[i].setFont(minorfont);

			
				templabel[i] = new JLabel((int)tempArray[i] + "\u00b0" + preferences.getTempUnit());
				templabel[i].setFont(minorfont);

				highlabel[i] = new JLabel("High:" + (int) highArray[i] + "\u00b0" + preferences.getTempUnit());
				highlabel[i].setFont(minorfont);

				lowlabel[i] = new JLabel("Low:" + (int)lowArray[i] + "\u00b0" + preferences.getTempUnit());
				lowlabel[i].setFont(minorfont);
		
			imagelabel[i] = new JLabel(new ImageIcon(imgArray[i]));
			imagelabel[i].setPreferredSize(new Dimension(5,5));
		}
		
		for (int i = cnt; i < 5; i++) {
			int j = i + 1;
			wdlabel[i] = new JLabel(j + " days:");
		
			
			wdlabel[i].setFont(minorfont);
			sclabel[i] = new JLabel(" Data ");
			sclabel[i].setFont(minorfont);
			
			
			templabel[i] = new JLabel("Unavailable");
			templabel[i].setFont(minorfont);
			
			highlabel[i] = new JLabel("-");
			highlabel[i].setFont(minorfont);
			
			lowlabel[i] = new JLabel("-");
			lowlabel[i].setFont(minorfont);
			
			imagelabel[i] = new JLabel("-");
			imagelabel[i].setFont(minorfont);
			
		}
		GridLayout panelayout = new GridLayout();
		this.setLayout(panelayout);
		Block5day[] blocks = new Block5day[5];
		for (int i = 0; i < 5; i++) {
			blocks[i] = new Block5day(wdlabel[i], highlabel[i], lowlabel[i], templabel[i], sclabel[i],imagelabel[i]);
		}
		panel = new Panel5day(blocks);
		this.add(panel);
	}
	
	/**
	 * changes the label's text to the specified color
	 * @param acolor color to change the JLabels text to
	 */
	public void setLabelColor(Color acolor){
		panel.setPanelLabelColor(acolor);
	}
		
}
