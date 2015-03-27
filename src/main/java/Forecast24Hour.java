package main.java;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Forecast24Hour extends TransparentPanel {

	// Attributes
	private WeatherData data24h;
	private WeatherPreferences preferences;
	private TransparentPanel pane;
	private JLabel[] image_l;
	private JLabel[] weatherdescription_l;
	private JLabel[] skycondition_l;
	private JLabel[] temp_l;
	private int CNT;

	// private JLabel[] percentPrecipitation_l;

	public Forecast24Hour(WeatherData data, WeatherPreferences p) {
		this.data24h = data;
		this.preferences = p;
		initComponents();
		createDisplay();
	}

	public TransparentPanel getPanel() {
		return this;
	}

	private void initComponents() {
		// create labels to display basic data
		this.pane = new TransparentPanel();
		Font minorfont = new Font("sml", Font.PLAIN, 12);

		JLabel[] wdlabel = new JLabel[8];
		JLabel[] sclabel = new JLabel[8];
		JLabel[] templabel = new JLabel[8];
		JLabel[] imagelabel = new JLabel[8];

		String[] skyConditionArray = data24h.getSkyCondition24h();
		String[] descriptionArray = data24h.getDescription24h();
		Image[] imgArray = data24h.getIcon24h();
		
		double[] tempArray;
		
		if (preferences.getTempUnit().equalsIgnoreCase("F")){
			tempArray = data24h.getTemp24hF();
		} else {
			tempArray = data24h.getTemp24h();
		}
		
		int cnt = data24h.getCNT24h();
		if (cnt > 8) {
			cnt = 8;
		}
		
		for (int i = 0; i < cnt; i++) {
			int j = (i + 1) * 3;
			
			String temp = descriptionArray[i].substring(0, 1).toUpperCase() + descriptionArray[i].substring(1);
			wdlabel[i] = new JLabel(j + " hrs: " + temp);
			wdlabel[i].setFont(minorfont);

			sclabel[i] = new JLabel("Sky:  " + skyConditionArray[i]);
			sclabel[i].setFont(minorfont);

			if (preferences.getTempUnit().equalsIgnoreCase("F")){
				templabel[i] = new JLabel(tempArray[i] + "\u00b0" + "F");
				templabel[i].setFont(minorfont);
			} else {
				templabel[i] = new JLabel(tempArray[i] + "\u00b0" + "C");
				templabel[i].setFont(minorfont);
			}

			imagelabel[i] = new JLabel(new ImageIcon(imgArray[i]));
			imagelabel[i].setPreferredSize(new Dimension(5,5));
		}

		for (int i = cnt; i < 8; i++) {
			
			int j = (i + 1)*3;
			
			wdlabel[i] = new JLabel(j + " hours:");
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
		this.CNT = cnt;
	}

	private void createDisplay() {

		GridLayout panelayout = new GridLayout();
		this.setLayout(panelayout);
		TransparentPanel[] blocks = new TransparentPanel[8];
		for (int i = 0; i < 8; i++) {
			blocks[i] = new Create24hourBlock(temp_l[i],image_l[i],skycondition_l[i],weatherdescription_l[i]);
		}
		this.add(new Layout24hour(blocks));
}
	

	// Test
/*	public static void main(String[] args) {

		InputTest t = new InputTest("paris");
		WeatherData d = new WeatherData(t);
		WeatherPreferences p = new WeatherPreferences();
		CopyOfForecast24Hour test = new CopyOfForecast24Hour(d,p);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(test);
		frame.setVisible(true);
		frame.pack();
		frame.setTitle("24 Hour Weather Forecast for London, Canada");
	}*/

}
