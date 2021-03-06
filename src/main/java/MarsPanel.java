package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MarsPanel {

		// Attributes
		
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
		
		/**
		 * Constructor for the Mars Panel
		 * @param data WeatherData object as input
		 */
		public MarsPanel(WeatherData data) {
			
			initComponents(data);
			createDisplay();
		} 
		
		/**
		 * Gets the JPanel (MarsPanel)
		 * @return JPanel
		 */
		public JPanel getPanel(){
			return pane;
		}
		
		/**
		 * Gets the BackgroundPanel
		 * @return BackgroundPanel
		 */
		public BackgroundPanel getBackgroundPanel(){
			return bgpanel;
		}
		
		/**
		 * Private method for the constructor
		 * Initializes items on the panel
		 */
		private void initComponents(WeatherData data) {
			// create labels to display basic data
			this.pane = new TransparentPanel();
			try {
				 bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("./resources/images/mars.jpg"));
			} catch (Exception e) {
				System.out.println("Cannot read file for clear.jpg: " + e);
			}
			this.bgpanel = new BackgroundPanel(bg);
			
			Font mainfont = new Font("med", Font.PLAIN, 30);
			
			JLabel minlabel = new JLabel("Min: " + data.getMinTempMars() + "\u00b0" + "C");
			minlabel.setFont(mainfont);
			minlabel.setForeground(new Color(255,255,255));
			JLabel maxlabel = new JLabel("Max: " + data.getMaxTempMars() + "\u00b0" + "C");
			maxlabel.setFont(mainfont);
			maxlabel.setForeground(new Color(255,255,255));
			JLabel windspeedlabel = new JLabel("Windspeed: " + data.getWindSpeedMars());
			windspeedlabel.setFont(mainfont);
			windspeedlabel.setForeground(new Color(255,255,255));
			JLabel winddirectionlabel = new JLabel("Wind Direction: " + data.getWindDirectionMars());
			winddirectionlabel.setFont(mainfont);
			winddirectionlabel.setForeground(new Color(255,255,255));
			JLabel pressurelabel = new JLabel("Pressure: " + data.getPressure() + " Pa");
			pressurelabel.setFont(mainfont);
			pressurelabel.setForeground(new Color(255,255,255));
			JLabel humiditylabel = new JLabel("Humidity: " + data.getHumidityMars() + "%");
			humiditylabel.setFont(mainfont);
			humiditylabel.setForeground(new Color(255,255,255));
			JLabel skylabel = new JLabel("Sky Condition: " + data.getSkyConditionMars());
			skylabel.setFont(mainfont);
			skylabel.setForeground(new Color(255,255,255));
			ImageIcon icon = new ImageIcon(data.getIconMars());
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(140, 140,  java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			JLabel imagelabel = new JLabel(icon);
			
			this.image_l = imagelabel;
			this.wind_speed_l = windspeedlabel;
			this.wind_direction_l = winddirectionlabel;
			this.sky_condition_mars_l = skylabel;
			this.humidity_mars_l = humiditylabel;
			this.min_temp_l = minlabel;
			this.max_temp_l = maxlabel;
			this.pressure_l = pressurelabel;
		}

		/**
		 * private method for creating the display
		 */
		private void createDisplay() {
			
			pane.setLayout(null);
			pane.add(sky_condition_mars_l);
			sky_condition_mars_l.setBounds(40, 60, 500, 30);
			pane.add(image_l);
			image_l.setBounds(550, 90, 350, 350);
			image_l.setSize(60,60);
			pane.add(max_temp_l);
			max_temp_l.setBounds(40, 100, 500, 30);
			pane.add(min_temp_l);
			min_temp_l.setBounds(40, 140, 500, 30);
			pane.add(wind_speed_l);
			wind_speed_l.setBounds(40, 180, 500, 30);
			pane.add(wind_direction_l);
			wind_direction_l.setBounds(40, 220, 500, 30);
			pane.add(humidity_mars_l);
			humidity_mars_l.setBounds(40, 260, 500, 30);
			pane.add(pressure_l);
			pressure_l.setBounds(40, 300, 500, 30);
		}	
}