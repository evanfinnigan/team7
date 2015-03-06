package team7;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Forecast24Hour {

	// Attributes
		private WeatherData data24h;
		private Preferences currentPrefs;
		private JPanel pane;
		private JLabel[] image_l;
		private JLabel[] weatherdescription_l;
		private JLabel[] skycondition_l;
		private JLabel[] temp_l;
		//private JLabel[] percentPrecipitation_l;

		public Forecast24Hour(WeatherData data, Preferences prefs) {
			this.data24h = data;
			this.currentPrefs = prefs;
			initComponents();
			createDisplay(prefs);
		} 
		
		public JPanel getPanel(){
			return pane;
		}
		
		public Preferences getPreferences(){
			return currentPrefs;
		}

		private void initComponents() {
			// create labels to display basic data
			this.pane = new JPanel();
			Font mainfont = new Font("lrg", Font.PLAIN, 30);
			Font minorfont = new Font("sml", Font.PLAIN, 15);
			
			JLabel[] wdlabel = new JLabel[8];
			JLabel[] sclabel = new JLabel[8];
			JLabel[] templabel = new JLabel[8];
			JLabel[] imagelabel = new JLabel[8];
			
			String[] skyConditionArray = data24h.getSkyCondition24h();
			String[] descriptionArray = data24h.getDescription24h();
			double[] tempArray = data24h.getTemp24h();
			Image[] imgArray = data24h.getIcon24h();
			
			for (int i = 0; i < 8; i++){
				int j = (i+1)*3;
				wdlabel[i] = new JLabel("Conditions in " + j + " hours: "
						 + descriptionArray[i]);
				wdlabel[i].setFont(minorfont);
				
				sclabel[i] = new JLabel("    Sky:  " + skyConditionArray[i]);
				sclabel[i].setFont(minorfont);
				
				templabel[i] = new JLabel("Temperature :"
						+ tempArray[i]);
				templabel[i].setFont(minorfont);
				
				imagelabel[i] = new JLabel(new ImageIcon(imgArray[i]));
			}
			
			this.weatherdescription_l = wdlabel;
			this.skycondition_l = sclabel;
			this.temp_l = templabel;
			this.image_l = imagelabel;
		}

		private void createDisplay(Preferences prefs) {

			GridLayout panelayout = new GridLayout(0, 4);
			pane.setLayout(panelayout);
			for (int i = 0; i < 8; i++){
				
				if (prefs.showDescription() == true) {
					pane.add(weatherdescription_l[i]);
				}
				
				if (prefs.showIcon() == true) {
					pane.add(image_l[i]);
				}
				
				if (prefs.showTemperature() == true) {
					pane.add(temp_l[i]);
				}
				
				if (prefs.showSky() == true) {
					pane.add(skycondition_l[i]);
				}
				
				
			}
		}

		// Test
		public static void main(String[] args){
			
			Preferences p = new Preferences();
			InputTest t = new InputTest("London, Canada");
			WeatherData d = new WeatherData(t);
			Forecast24Hour test = new Forecast24Hour(d, p);
			JFrame frame = new JFrame();
			frame.add(test.getPanel());
			frame.setVisible(true);
			frame.pack();
			frame.setTitle("24 Hour Weather Forecast for London, Canada");
		}
	
}
