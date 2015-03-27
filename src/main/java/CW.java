package main.java;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

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
	 * Create the panel.
	 */
	public CW(WeatherData data,WeatherPreferences prefs) {
		Font sprlrgfont = new Font("lrg", Font.PLAIN, 44);
		Font lrgfont = new Font("lrg", Font.PLAIN, 24);
		Font medfont = new Font("med", Font.PLAIN, 16);
		Font smlfont = new Font("sml", Font.PLAIN, 13);
		cityname = new JLabel(data.getCityName());
		cityname.setFont(lrgfont);
		cityname.setForeground(Color.WHITE);
		weatherdescription_l = new JLabel(data.getDescription());
		weatherdescription_l.setFont(smlfont);
		weatherdescription_l.setForeground(Color.WHITE);
		humidity_l = new JLabel("Humidity:" + data.getHumidity() +"%");
		humidity_l.setFont(medfont);
		humidity_l.setForeground(Color.WHITE);
		windspeed_l = new JLabel("Wind Speed:" + data.getWindSpeed());
		windspeed_l.setFont(medfont);
		windspeed_l.setForeground(Color.WHITE);
		winddirection_l = new JLabel("Wind Direction:" + data.getWindDirection());
		winddirection_l.setFont(medfont);
		winddirection_l.setForeground(Color.WHITE);

		airpressure_l = new JLabel("Air Pressure:" + data.getPressure());
		airpressure_l.setFont(medfont);
		airpressure_l.setForeground(Color.WHITE);
		temp_l = new JLabel("" + data.getTempCurrent() + "\u00b0" + prefs.getTempUnit());
		temp_l.setFont(sprlrgfont);
		temp_l.setForeground(Color.WHITE);
		tempmax_l = new JLabel("high:" + data.getHigh() + "\u00b0" + prefs.getTempUnit());
		tempmax_l.setFont(medfont);
		tempmax_l.setForeground(Color.WHITE);
		tempmin_l = new JLabel("low:" + data.getLow() + "\u00b0" + prefs.getTempUnit());
		tempmin_l.setFont(medfont);
		tempmin_l.setForeground(Color.WHITE);
		skycondition_l = new JLabel(data.getSkyConditionCurrent());
		skycondition_l.setFont(lrgfont);
		skycondition_l.setForeground(Color.WHITE);
		lastupdate_l = new JLabel("Time of Last Update:"+ data.getTimeOfLastRequest());
		lastupdate_l.setFont(smlfont);
		lastupdate_l.setForeground(Color.WHITE);
		sunrise_l = new JLabel("Sunrise:" + data.getSunrise());
		sunrise_l.setFont(medfont);
		sunrise_l.setForeground(Color.WHITE);
		sunset_l = new JLabel("Sunset:" + data.getSunset());
		sunset_l.setFont(medfont);
		sunset_l.setForeground(Color.WHITE);
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
	public TransparentPanel getPanel() {
		return this;
	}
	
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
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("Weather for London, Canada");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		WeatherPreferences prefs = new WeatherPreferences();
		InputTest intest = new InputTest("Samandag");
		WeatherData data = new WeatherData(intest);
		System.out.println(data.getCityName());
		prefs.setTempUnit("F");
		CW test = new CW(data, prefs);

		frame.getContentPane().add(test);
		System.out.println("finished");
		// frame.pack();
		frame.setSize(690, 410);
		frame.setVisible(true);
	}
}
