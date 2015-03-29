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

/**
 * 
 * @author TEAM 7 CS-2212 2015
 *
 */
public class Forecast5Day extends TransparentPanel{

	// Attributes
	private WeatherData data5d;
	private WeatherPreferences preferences;
	private JLabel[] image_l;
	private JLabel[] weatherdescription_l;
	private JLabel[] skycondition_l;
	private JLabel[] temp_l;
	private JLabel[] high_l;
	private JLabel[] low_l;
	
	/**
	 *  Forecast5Day constructor
	 * @param data WeatherData data model used to construct this object
	 * @param p WeatherPreferences user preferences
	 */
	public Forecast5Day(WeatherData data, WeatherPreferences p) {
		this.preferences = p;
		this.data5d = data;
		initComponents();
		createDisplay();
	}
	/**
	 * Convenience method
	 * @return Forecast5day object which is a TransparentPanel
	 */
	public TransparentPanel getPanel() {
		return this;
	}
	/**
	 * Private method that initializes components 
	 */
	private void initComponents() {
		// create labels to display basic data
		
		Font minorfont = new Font("sml", Font.PLAIN, 15);
		

		JLabel[] wdlabel = new JLabel[5];
		JLabel[] sclabel = new JLabel[5];
		JLabel[] templabel = new JLabel[5];
		JLabel[] imagelabel = new JLabel[5];
		JLabel[] highlabel = new JLabel[5];
		JLabel[] lowlabel = new JLabel[5];

		String[] descriptionArray = data5d.getDescription5d();
		Image[] imgArray = data5d.getIcon5d();
		
		double[] tempArray;
		double[] highArray;
		double[] lowArray;
		
		if (preferences.getTempUnit().equalsIgnoreCase("F")){
			tempArray = data5d.getTemp5dF();
			highArray = data5d.getHigh5dF();
			lowArray = data5d.getLow5dF();
		} else {
			tempArray = data5d.getTemp5d();
			highArray = data5d.getHigh5d();
			lowArray = data5d.getLow5d();
		}
		
		int cnt = data5d.getCNT5d();
		
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

			if (preferences.getTempUnit().equalsIgnoreCase("F")){
				templabel[i] = new JLabel((int)tempArray[i] + "\u00b0F");
				templabel[i].setFont(minorfont);

				highlabel[i] = new JLabel("High:" + (int) highArray[i] + "\u00b0" + "F");
				highlabel[i].setFont(minorfont);

				lowlabel[i] = new JLabel("Low:" + (int)lowArray[i] + "\u00b0"+"F");
				lowlabel[i].setFont(minorfont);
			} else {
				templabel[i] = new JLabel((int)tempArray[i] + "\u00b0" +"C");
				templabel[i].setFont(minorfont);

				highlabel[i] = new JLabel("High: " + (int)highArray[i] + "\u00b0" +"C");
				highlabel[i].setFont(minorfont);

				lowlabel[i] = new JLabel("Low: " + (int)lowArray[i] +"\u00b0" + "C");
				lowlabel[i].setFont(minorfont);
			}

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

		this.weatherdescription_l = wdlabel;
		this.skycondition_l = sclabel;
		this.temp_l = templabel;
		this.image_l = imagelabel;
		this.high_l = highlabel;
		this.low_l = lowlabel;
	}
	
	/**
	 * changes the label's text to the specified color
	 * @param acolor color to change the JLabels text to
	 */
	public void setLabelColor(Color acolor){
		for (int i = 0; i < 5; i++) {
			weatherdescription_l[i].setForeground(acolor);
			high_l[i].setForeground(acolor);
			low_l[i].setForeground(acolor);
			temp_l[i].setForeground(acolor);
			skycondition_l[i].setForeground(acolor);
		}
	}
	/**
	 *  private method used in constructor
	 */
	private void createDisplay() {

		GridLayout panelayout = new GridLayout();
		this.setLayout(panelayout);
		TransparentPanel[] blocks = new TransparentPanel[5];
		for (int i = 0; i < 5; i++) {
			blocks[i] = new Create5dayBlock(weatherdescription_l[i], high_l[i], low_l[i], temp_l[i], skycondition_l[i],image_l[i]);
		}
	
		this.add(new Layout5Day(blocks));
	}

}
