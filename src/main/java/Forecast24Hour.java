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

	// Attributes
	private WeatherData data24h;
	private WeatherPreferences preferences;
	private JLabel[] image_l;
	private JLabel[] weatherdescription_l;
	private JLabel[] skycondition_l;
	private JLabel[] temp_l;
	

 /**
  * Forecast24Hour Constructor
  * @param data WeatherData current weatherdata model
  * @param p WeatherPreferences current user preferences
  */

	public Forecast24Hour(WeatherData data, WeatherPreferences p) {
		this.data24h = data;
		this.preferences = p;
		initComponents();
		createDisplay();
	}
   /**
   * getter method which is a convenience method  
   * @return TransparentPanel 
   */
	public TransparentPanel getPanel() {
		return this;
	}
	/**
	 *  private initialization method to be used by constructor
	 */
	private void initComponents() {
		// create labels to display basic data
		Font minorfont = new Font("sml", Font.PLAIN, 12);

		JLabel[] wdlabel = new JLabel[8];
		JLabel[] sclabel = new JLabel[8];
		JLabel[] templabel = new JLabel[8];
		JLabel[] imagelabel = new JLabel[8];

		String[] skyConditionArray = data24h.getSkyCondition24h();
		String[] descriptionArray = data24h.getDescription24h();
		Image[] imgArray = data24h.getIcon24h();

		double[] tempArray;

		if (preferences.getTempUnit().equalsIgnoreCase("F")) {
			tempArray = data24h.getTemp24hF();
		} else {
			tempArray = data24h.getTemp24h();
		}

		int cnt = data24h.getCNT24h();
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

			if (preferences.getTempUnit().equalsIgnoreCase("F")) {
				templabel[i] = new JLabel((int)tempArray[i] + "\u00b0" + "F");
				templabel[i].setFont(minorfont);
			} else {
				templabel[i] = new JLabel((int)tempArray[i] + "\u00b0" + "C");
				templabel[i].setFont(minorfont);
			}

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

		this.weatherdescription_l = wdlabel;
		this.skycondition_l = sclabel;
		this.temp_l = templabel;
		this.image_l = imagelabel;
	}
	/**
	 *  create the panel to display the 24 hour forecast
	 *  used by the constructor
	 */
	private void createDisplay() {

		GridLayout panelayout = new GridLayout();
		this.setLayout(panelayout);
		TransparentPanel[] blocks = new TransparentPanel[8];
		for (int i = 0; i < 8; i++) {
			blocks[i] = new Create24hourBlock(weatherdescription_l[i], temp_l[i], image_l[i],
					skycondition_l[i]);
		}
		TransparentPanel paininthebutt = new TransparentPanel();
		paininthebutt = new Layout24hour(blocks);
		paininthebutt.setAlignmentY(TOP_ALIGNMENT);
		this.add(paininthebutt);
	}
	/**
	 * Changes the label's text to the specified color
	 * @param acolor color to change to
	 */
	public void setLabelColor(Color acolor){
		for (int i=0;i<8;i++){
		temp_l[i].setForeground(acolor);
		weatherdescription_l[i].setForeground(acolor);
		skycondition_l[i].setForeground(acolor);
		}
	}
	
}
