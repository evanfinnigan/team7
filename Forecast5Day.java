package team7;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Forecast5Day {

	// Attributes
	private WeatherData data5d;
	private WeatherPreferences currentPrefs;
	private JPanel pane;
	private JLabel[] image_l;
	private JLabel[] weatherdescription_l;
	private JLabel[] skycondition_l;
	private JLabel[] temp_l;
	private JLabel[] percentPrecipitation_l;
	private JLabel[] high_l;
	private JLabel[] low_l;

	public Forecast5Day(WeatherData data, WeatherPreferences prefs) {
		this.data5d = data;
		this.currentPrefs = prefs;
		initComponents();
		createDisplay(prefs);
	}

	public JPanel getPanel() {
		return pane;
	}

	public WeatherPreferences getWeatherPreferences() {
		return currentPrefs;
	}

	private void initComponents() {
		// create labels to display basic data
		this.pane = new JPanel();
		Font minorfont = new Font("sml", Font.PLAIN, 15);

		JLabel[] wdlabel = new JLabel[5];
		JLabel[] sclabel = new JLabel[5];
		JLabel[] templabel = new JLabel[5];
		JLabel[] imagelabel = new JLabel[5];
		JLabel[] highlabel = new JLabel[5];
		JLabel[] lowlabel = new JLabel[5];

		String[] skyConditionArray = data5d.getSkyCondition5d();
		String[] descriptionArray = data5d.getDescription5d();
		double[] tempArray = data5d.getTemp5d();
		Image[] imgArray = data5d.getIcon5d();
		double[] highArray = data5d.getHigh5d();
		double[] lowArray = data5d.getLow5d();

		for (int i = 0; i < 5; i++) {
			int j = i + 1;
			wdlabel[i] = new JLabel("Conditions in " + j + " days: "
					+ skyConditionArray[i]);
			wdlabel[i].setFont(minorfont);

			sclabel[i] = new JLabel("  Description: " + descriptionArray[i]);

			templabel[i] = new JLabel("Temperature: " + tempArray[i]);
			templabel[i].setFont(minorfont);

			highlabel[i] = new JLabel("High: " + highArray[i]);

			lowlabel[i] = new JLabel("Low: " + lowArray[i]);

			imagelabel[i] = new JLabel(new ImageIcon(imgArray[i]));
		}

		this.weatherdescription_l = wdlabel;
		this.skycondition_l = sclabel;
		this.temp_l = templabel;
		this.image_l = imagelabel;
		this.high_l = highlabel;
		this.low_l = lowlabel;
	}

	private void createDisplay(WeatherPreferences prefs) {
		
		int j = 0;
		
		if (prefs.showDescription()) {
			j++;
		}
		
		if (prefs.showIcon()) {
			j++;
		}
		
		if (prefs.showSky()) {
			j++;
		}

		if (prefs.showTemperature()) {
			j++;
		}

		if (prefs.showHigh()) {
			j++;
		}

		if (prefs.showLow()) {
			j++;
		}

		GridLayout panelayout = new GridLayout(0, 6);
		pane.setLayout(panelayout);
		for (int i = 0; i < 5; i++) {

			if (prefs.showDescription() == true) {
				pane.add(weatherdescription_l[i]);
			}
			
			if (prefs.showIcon() == true) {
				pane.add(image_l[i]);
			}
			
			if (prefs.showSky() == true) {
				pane.add(skycondition_l[i]);
			}

			if (prefs.showTemperature() == true) {
				pane.add(temp_l[i]);
			}

			if (prefs.showHigh() == true) {
				pane.add(high_l[i]);
			}

			if (prefs.showLow() == true) {
				pane.add(low_l[i]);
			}
		}

	}

	// Test
	public static void main(String[] args) {

		WeatherPreferences p = new WeatherPreferences();
		InputTest t = new InputTest("London, Canada");
		WeatherData d = new WeatherData(t);
		Forecast5Day test = new Forecast5Day(d, p);
		JFrame frame = new JFrame();
		frame.add(test.getPanel());
		frame.setVisible(true);
		frame.pack();
		frame.setTitle("5 Day Weather Forecast for London, Canada");
	}

}
