package main.java;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MarsPanel {

	// Attributes
		private WeatherData dataMars;
		private WeatherPreferences currentPrefs;
		private TransparentPanel pane;
		private Image bg;
		private JLabel image_l;
		private JLabel wind_speed_l;
		private JLabel wind_direction_l;
		private JLabel sky_condition_mars_l;
		private JLabel humidity_mars_l;
		private JLabel min_temp_l;
		private JLabel max_temp_l;
		private JLabel pressure_l;
		private BackgroundPanel bgpanel;

		public MarsPanel(WeatherData data) {
			this.dataMars = data;
			initComponents();
			createDisplay();
		} 
		
		public JPanel getPanel(){
			return pane;
		}
		
		public BackgroundPanel getBackgroundPanel(){
			return bgpanel;
		}
		
		private void initComponents() {
			// create labels to display basic data
			this.pane = new TransparentPanel();
			try {
				 bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("./resources/images/mars.jpg"));
			} catch (Exception e) {
				System.out.println("Cannot read file for clear.jpg: " + e);
			}
			this.bgpanel = new BackgroundPanel(bg);
			
			Font mainfont = new Font("lrg", Font.PLAIN, 30);
			Font minorfont = new Font("sml", Font.PLAIN, 15);
			
			JLabel minlabel = new JLabel("Min: " + dataMars.getMinTempMars());
			JLabel maxlabel = new JLabel("Max: " + dataMars.getMaxTempMars());
			JLabel windspeedlabel = new JLabel("Windspeed: " + dataMars.getWindSpeedMars());
			JLabel winddirectionlabel = new JLabel("Wind Direction: " + dataMars.getWindDirectionMars());
			JLabel pressurelabel = new JLabel("Pressure: " + dataMars.getPressure() + " Pa");
			JLabel humiditylabel = new JLabel("Humidity: " + dataMars.getHumidityMars() + "%");
			JLabel skylabel = new JLabel("Sky Condition: " + dataMars.getSkyConditionMars());
			JLabel imagelabel = new JLabel(new ImageIcon(dataMars.getIconMars()));
			
			this.image_l = imagelabel;
			this.wind_speed_l = windspeedlabel;
			this.wind_direction_l = winddirectionlabel;
			this.sky_condition_mars_l = skylabel;
			this.humidity_mars_l = humiditylabel;
			this.min_temp_l = minlabel;
			this.max_temp_l = maxlabel;
			this.pressure_l = pressurelabel;
		}

		private void createDisplay() {

			GridLayout panelayout = new GridLayout(2, 0);
			
			pane.setLayout(panelayout);
			
			pane.add(sky_condition_mars_l);
			pane.add(image_l);
			pane.add(max_temp_l);
			pane.add(min_temp_l);
			pane.add(wind_speed_l);
			pane.add(wind_direction_l);
			pane.add(humidity_mars_l);
			pane.add(pressure_l);
		}

		// Test
		public static void main(String[] args){
			
			InputTest t = new InputTest("London, Canada");
			WeatherData d = new WeatherData(t);
			MarsPanel test = new MarsPanel(d);
			JFrame frame = new JFrame();
			frame.setContentPane(test.getBackgroundPanel());
			frame.add(test.getPanel());
			frame.setVisible(true);
			frame.pack();
			frame.setTitle("Weather on Mars");
		}
	
}