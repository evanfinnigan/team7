package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Dimension;

public class CopyOfForecast5Day extends TransparentPanel{

	// Attributes
	private WeatherData data5d;
	private WeatherPreferences preferences;
	private JLabel[] image_l;
	private JLabel[] weatherdescription_l;
	private JLabel[] skycondition_l;
	private JLabel[] temp_l;
	private JLabel[] percentPrecipitation_l;
	private JLabel[] high_l;
	private JLabel[] low_l;
	private int CNT;

	public CopyOfForecast5Day(WeatherData data, WeatherPreferences p) {
		this.preferences = p;
		this.data5d = data;
		initComponents();
		createDisplay();
	}

	public TransparentPanel getPanel() {
		return this;
	}

	private void initComponents() {
		// create labels to display basic data
		
		Font minorfont = new Font("sml", Font.PLAIN, 15);
		

		JLabel[] wdlabel = new JLabel[5];
		JLabel[] sclabel = new JLabel[5];
		JLabel[] templabel = new JLabel[5];
		JLabel[] imagelabel = new JLabel[5];
		JLabel[] highlabel = new JLabel[5];
		JLabel[] lowlabel = new JLabel[5];

		String[] skyConditionArray = data5d.getSkyCondition5d();
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

		for (int i = 0; i < cnt; i++) {
			int j = i + 1;
			
			
			wdlabel[i] = new JLabel( j + " days: " + skyConditionArray[i]);
			//wdlabel[i] = new JLabel( j + " days: " + descriptionArray[i]);
			wdlabel[i].setFont(minorfont);

			String temp = descriptionArray[i].substring(0, 1).toUpperCase() + descriptionArray[i].substring(1);
			sclabel[i] = new JLabel(temp);
			sclabel[i].setFont(minorfont);

			if (preferences.getTempUnit().equalsIgnoreCase("F")){
				templabel[i] = new JLabel(tempArray[i] + "\u00b0F");
				templabel[i].setFont(minorfont);

				highlabel[i] = new JLabel("High:" + (int) highArray[i] + "\u00b0" + "F");
				highlabel[i].setFont(minorfont);

				lowlabel[i] = new JLabel("Low:" + (int)lowArray[i] + "\u00b0"+"F");
				lowlabel[i].setFont(minorfont);
			} else {
				templabel[i] = new JLabel(tempArray[i] + "\u00b0" +"C");
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
			wdlabel[i].setForeground(Color.WHITE);
			
			wdlabel[i].setFont(minorfont);
			sclabel[i] = new JLabel(" Data ");
			sclabel[i].setFont(minorfont);
			sclabel[i].setForeground(Color.WHITE);
			
			templabel[i] = new JLabel("Unavailable");
			templabel[i].setFont(minorfont);
			templabel[i].setForeground(Color.WHITE);
			highlabel[i] = new JLabel("-");
			highlabel[i].setFont(minorfont);
			highlabel[i].setForeground(Color.WHITE);
			lowlabel[i] = new JLabel("-");
			lowlabel[i].setFont(minorfont);
			lowlabel[i].setForeground(Color.WHITE);
			imagelabel[i] = new JLabel("-");
			imagelabel[i].setFont(minorfont);
			imagelabel[i].setForeground(Color.WHITE);
		}

		this.weatherdescription_l = wdlabel;
		this.skycondition_l = sclabel;
		this.temp_l = templabel;
		this.image_l = imagelabel;
		this.high_l = highlabel;
		this.low_l = lowlabel;
		this.CNT = cnt;
	}

	private void createDisplay() {

		GridLayout panelayout = new GridLayout();
		this.setLayout(panelayout);
		TransparentPanel[] blocks = new TransparentPanel[5];
		for (int i = 0; i < 5; i++) {
			blocks[i] = new Create5dayBlock(temp_l[i], high_l[i], low_l[i],weatherdescription_l[i],skycondition_l[i],image_l[i]);
		}
		//for (int i = 0; i < 5; i++) {
		//	pane.add(weatherdescription_l[i]);
		//	pane.add(image_l[i]);
		//	pane.add(skycondition_l[i]);
		//	pane.add(temp_l[i]);
		//	pane.add(high_l[i]);
		//	pane.add(low_l[i]);
		//}
		this.add(new Layout5Day(blocks));
	}

	// Test
	public static void main(String[] args) {

		InputTest t = new InputTest("paris");
		WeatherData d = new WeatherData(t);
		WeatherPreferences p = new WeatherPreferences();
		CopyOfForecast5Day test = new CopyOfForecast5Day(d,p);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(test);
		frame.setVisible(true);
		frame.pack();
		frame.setTitle("5 Day Weather Forecast for London, Canada");
	}

}
