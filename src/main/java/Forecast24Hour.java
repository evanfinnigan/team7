package main.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Forecast24Hour extends TransparentPanel {

	private Panel24hour panel;
	private Font minorfont = new Font("sml", Font.PLAIN, 12);
	

 /**
  * Forecast24Hour Constructor
  * @param data WeatherData current weatherdata model
  * @param preferences WeatherPreferences current user preferences
  */

	public Forecast24Hour(WeatherData data, WeatherPreferences preferences) {
		initComponents(data,preferences);
	//	createDisplay();
	}
   /**
   * getter method which is a convenience method  
   * @return TransparentPanel 
   */
	public TransparentPanel getPanel() {
		return this;
	}
	/**
	 * Updates the Temperature Labels in each of the 24 hour blocks
	 * @param data WeatherData
	 * @param preferences WeatherPreferences
	 */
	public void updateTemps(WeatherData data,WeatherPreferences preferences){
		JLabel[] templabel = new JLabel[8];
		double[] tempArray = new double[8];
			tempArray = data.getTemp24h();
			for (int i=0;i<8;i++){
			    templabel[i] = new JLabel((int)tempArray[i] + "\u00b0" + preferences.getTempUnit());
			    templabel[i].setFont(minorfont);
			}
			panel.updateTemp(templabel);
	}
	/**
	 *  private initialization method to be used by constructor
	 */
	private void initComponents(WeatherData data,WeatherPreferences preferences) {
		// create labels to display basic data
		

		JLabel[] wdlabel = new JLabel[8];
		JLabel[] sclabel = new JLabel[8];
		JLabel[] templabel = new JLabel[8];
		JLabel[] imagelabel = new JLabel[8];

		String[] skyConditionArray = data.getSkyCondition24h();
		String[] descriptionArray = data.getDescription24h();
		Image[] imgArray = data.getIcon24h();

		double[] tempArray;

		if (preferences.getTempUnit().equalsIgnoreCase("F")) {
			tempArray = data.getTemp24hF();
		} else {
			tempArray = data.getTemp24h();
		}

		int cnt = data.getCNT24h();
		if (cnt > 8) {
			cnt = 8;
		}

		Date date = new Date();
		for (int i = 0; i < cnt; i++) {
			date.setHours(date.getHours() + 3);
			DateFormat format = new SimpleDateFormat("EEEEEEEE, h:mm a");
			TimeZone est = TimeZone.getTimeZone("America/Toronto");
			format.setTimeZone(est);
			String j = format.format(date);

			descriptionArray[i].substring(0, 1).toUpperCase();
			descriptionArray[i].substring(1);
			wdlabel[i] = new JLabel(j);
			wdlabel[i].setFont(minorfont);

			sclabel[i] = new JLabel("Sky:  " + skyConditionArray[i]);
			sclabel[i].setFont(minorfont);

			templabel[i] = new JLabel((int)tempArray[i] + "\u00b0" + preferences.getTempUnit());
			templabel[i].setFont(minorfont);

			imagelabel[i] = new JLabel(new ImageIcon(imgArray[i]));
			imagelabel[i].setPreferredSize(new Dimension(5, 5));
		}

		for (int i = cnt; i < 8; i++) {

			date.setHours(date.getHours() + 3);
			DateFormat format = new SimpleDateFormat("EEEEEEEE, h:mm a");
			TimeZone est = TimeZone.getTimeZone("America/Toronto");
			format.setTimeZone(est);
			String j = format.format(date);

			wdlabel[i] = new JLabel(j);
			wdlabel[i].setFont(minorfont);
			sclabel[i] = new JLabel("-");
			sclabel[i].setFont(minorfont);
			templabel[i] = new JLabel("Unavailable");
			templabel[i].setFont(minorfont);
			imagelabel[i] = new JLabel("Data");
			imagelabel[i].setFont(minorfont);
		}
		
		GridLayout panelayout = new GridLayout();
		this.setLayout(panelayout);
		Block24hour[] blocks = new Block24hour[8];
		for (int i = 0; i < 8; i++) {
			blocks[i] = new Block24hour(wdlabel[i], templabel[i], imagelabel[i],
					sclabel[i]);
		}
		panel = new Panel24hour(blocks);
		panel.setAlignmentY(TOP_ALIGNMENT);
		this.add(panel);
		
	}
	
	/**
	 * Changes the label's text to the specified color
	 * @param acolor color to change to
	 */
	public void setLabelColor(Color acolor){
		panel.setPanelLabelColor(acolor);
	}
	
}
