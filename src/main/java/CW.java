package main.java;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CW extends TransparentPanel {
	private JLabel sunrise_l;
	private JLabel sunset_l;
	private JLabel weatherdescription_l;
	private JLabel skycondition_l;
	private JLabel windspeed_l;
	private JLabel winddirection_l;
	private JLabel temp_l;
	private JLabel tempmin_l;
	private JLabel tempmax_l;
	private JLabel humidity_l;
	private JLabel airpressure_l;
	private JLabel cityname;
	private JLabel lastupdate_l;
	private JLabel image_l;

	/**
	 * Creates the current weather panel and arranges labels on a transparent panel.
	 */
	public CW(WeatherData data,WeatherPreferences prefs) {
		Font sprlrgfont = new Font("lrg", Font.PLAIN, 44);
		Font lrgfont = new Font("lrg", Font.PLAIN, 24);
		Font medfont = new Font("med", Font.PLAIN, 16);
		Font smlfont = new Font("sml", Font.PLAIN, 13);
		cityname = new JLabel(data.getCityName());
		cityname.setFont(lrgfont);
		
		weatherdescription_l = new JLabel(data.getDescription());
		weatherdescription_l.setFont(smlfont);
		
		humidity_l = new JLabel("Humidity:" + data.getHumidity() +"%");
		humidity_l.setFont(medfont);
		
		windspeed_l = new JLabel("Wind Speed:" + data.getWindSpeed());
		windspeed_l.setFont(medfont);
		
		winddirection_l = new JLabel("Wind Direction:" + data.getWindDirection());
		winddirection_l.setFont(medfont);
		

		airpressure_l = new JLabel("Air Pressure:" + data.getPressure());
		airpressure_l.setFont(medfont);
	
		temp_l = new JLabel("" + data.getTempCurrent() + "\u00b0" + prefs.getTempUnit());
		temp_l.setFont(sprlrgfont);
		
		tempmax_l = new JLabel("high:" + data.getHigh() + "\u00b0" + prefs.getTempUnit());
		tempmax_l.setFont(medfont);
		
		tempmin_l = new JLabel("low:" + data.getLow() + "\u00b0" + prefs.getTempUnit());
		tempmin_l.setFont(medfont);
		
		skycondition_l = new JLabel(data.getSkyConditionCurrent());
		skycondition_l.setFont(lrgfont);
		
		lastupdate_l = new JLabel("Time of Last Update:"+ data.getTimeOfLastRequest());
		lastupdate_l.setFont(smlfont);
		
		sunrise_l = new JLabel("Sunrise:" + data.getSunrise());
		sunrise_l.setFont(medfont);
		
		sunset_l = new JLabel("Sunset:" + data.getSunset());
		sunset_l.setFont(medfont);
		
		image_l = new JLabel(new ImageIcon(data.getIcon()));
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cityname)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(weatherdescription_l)
								.addComponent(airpressure_l)
								.addComponent(humidity_l)
								.addComponent(winddirection_l)
								.addComponent(windspeed_l))
							.addGap(56)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(image_l)
								.addComponent(skycondition_l)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(temp_l, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(tempmin_l)
										.addComponent(tempmax_l)
										.addComponent(sunrise_l)
										.addComponent(sunset_l)))))
						.addComponent(lastupdate_l))
					.addContainerGap(628, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(cityname)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(temp_l)
									.addComponent(tempmin_l))
								.addComponent(tempmax_l))
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addComponent(image_l))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(weatherdescription_l, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
							.addGap(31)
							.addComponent(windspeed_l, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(winddirection_l, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(skycondition_l)
						.addComponent(humidity_l, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sunrise_l)
						.addComponent(airpressure_l, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sunset_l)
					.addGap(18)
					.addComponent(lastupdate_l, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(553))
		);
		setLayout(groupLayout);
		applyPrefs(prefs);
		
	}
	/*
	 * Convenience getter 	
	 * returns a CW object which is a TransparentPanel
	 */
	public TransparentPanel getPanel() {
		return this;
	}
	/*
	 * changes the label's text to the specified color
	 * input: Color 
	 */
	public void setLabelColor(Color acolor){
		 sunrise_l.setForeground(acolor);;
		 sunset_l.setForeground(acolor);;
		 weatherdescription_l.setForeground(acolor);;
		 skycondition_l.setForeground(acolor);;
		 windspeed_l.setForeground(acolor);;
		 winddirection_l.setForeground(acolor);;
		 temp_l.setForeground(acolor);;
		 tempmin_l.setForeground(acolor);;
		 tempmax_l.setForeground(acolor);;
		 humidity_l.setForeground(acolor);;
		 airpressure_l.setForeground(acolor);;
		 cityname.setForeground(acolor);;
		 lastupdate_l.setForeground(acolor);;
	}
	/*
	 * apply preferences method
	 * input: takes a WeatherPreferences object and applies preferences to 
	 * the current weather panel
	 * 
	 */
	public void applyPrefs(WeatherPreferences prefs) {

		temp_l.setVisible(prefs.getShowTemperature());
		tempmin_l.setVisible(prefs.getShowLow());
		tempmax_l.setVisible(prefs.getShowHigh());
		image_l.setVisible(prefs.getShowIcon());
		weatherdescription_l.setVisible(prefs.getShowDescription());
		skycondition_l.setVisible(prefs.getShowSky());
		sunrise_l.setVisible(prefs.getShowSunrise());
		sunset_l.setVisible(prefs.getShowSunset());
		windspeed_l.setVisible(prefs.getShowWindSpeed());
		winddirection_l.setVisible(prefs.getShowWindDirection());
		humidity_l.setVisible(prefs.getShowHumidity());
		airpressure_l.setVisible(prefs.getShowPressure());

	}

}
