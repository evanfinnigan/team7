package team7;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Forecast24Hour {

	// Attributes
	private WeatherData data24h;
	private JPanel pane;
	private JLabel[] image_l;
	private JLabel[] weatherdescription_l;
	private JLabel[] skycondition_l;
	private JLabel[] temp_l;

	// private JLabel[] percentPrecipitation_l;

	public Forecast24Hour(WeatherData data) {
		this.data24h = data;
		initComponents();
		createDisplay();
	}

	public JPanel getPanel() {
		return pane;
	}

	private void initComponents() {
		// create labels to display basic data
		this.pane = new JPanel();
		Font minorfont = new Font("sml", Font.PLAIN, 10);

		JLabel[] wdlabel = new JLabel[8];
		JLabel[] sclabel = new JLabel[8];
		JLabel[] templabel = new JLabel[8];
		JLabel[] imagelabel = new JLabel[8];

		String[] skyConditionArray = data24h.getSkyCondition24h();
		String[] descriptionArray = data24h.getDescription24h();
		double[] tempArray = data24h.getTemp24h();
		Image[] imgArray = data24h.getIcon24h();

		for (int i = 0; i < 8; i++) {
			int j = (i + 1) * 3;
			wdlabel[i] = new JLabel("Conditions in " + j + " hours: "
					+ descriptionArray[i]);
			wdlabel[i].setFont(minorfont);

			sclabel[i] = new JLabel("    Sky:  " + skyConditionArray[i]);
			sclabel[i].setFont(minorfont);

			templabel[i] = new JLabel("Temperature :" + tempArray[i] + " C");
			templabel[i].setFont(minorfont);

			imagelabel[i] = new JLabel(new ImageIcon(imgArray[i]));
			imagelabel[i].setSize(new Dimension(3,3));
		}

		this.weatherdescription_l = wdlabel;
		this.skycondition_l = sclabel;
		this.temp_l = templabel;
		this.image_l = imagelabel;
	}

	private void createDisplay() {

		GridLayout panelayout = new GridLayout(0, 4);

		pane.setLayout(panelayout);
		for (int i = 0; i < 8; i++) {
			pane.add(weatherdescription_l[i]);
			pane.add(image_l[i]);
			pane.add(temp_l[i]);
			pane.add(skycondition_l[i]);
		}
	}

	// Test
	public static void main(String[] args) {

		InputTest t = new InputTest("London, Canada");
		WeatherData d = new WeatherData(t);
		Forecast24Hour test = new Forecast24Hour(d);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(test.getPanel());
		frame.setVisible(true);
		frame.pack();
		frame.setTitle("24 Hour Weather Forecast for London, Canada");
	}

}
