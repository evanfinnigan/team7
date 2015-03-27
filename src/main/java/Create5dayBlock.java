package main.java;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Create5dayBlock extends TransparentPanel {
	private JLabel temp_l;
	private JLabel low_l;
	private JLabel high_l;
	private JLabel image_l;
	private JLabel skycondition_l;
	private JLabel weatherdescription_l;
	/**
	 * Create the panel.
	 */
	public Create5dayBlock(JLabel temp, JLabel high, JLabel low, JLabel desc, JLabel skycond, JLabel img) {
		
		temp_l = new JLabel("t");
		temp_l = temp;
		temp_l.setForeground(Color.WHITE);
		high_l = new JLabel("h");
		high_l = high;
		high_l.setForeground(Color.WHITE);
	    low_l = new JLabel("l");
		low_l = low;
		low_l.setForeground(Color.WHITE);
	    image_l = new JLabel("img");
		image_l =img;
		skycondition_l = new JLabel("cond");
		skycondition_l = skycond;
		skycondition_l.setForeground(Color.WHITE);
		weatherdescription_l = new JLabel("desc");
		weatherdescription_l = desc;
		weatherdescription_l.setForeground(Color.WHITE);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(image_l)
						.addComponent(skycondition_l)
						.addComponent(weatherdescription_l)
						.addComponent(low_l)
						.addComponent(high_l)
						.addComponent(temp_l))
					.addContainerGap(5, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(temp_l)
					.addGap(5)
					.addComponent(weatherdescription_l)
					.addGap(25)
					.addComponent(image_l)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(skycondition_l)
					.addGap(30)
					.addComponent(high_l)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(low_l)
					.addContainerGap(5, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
			
	}
	public static void main(String[] args) {

		InputTest t = new InputTest("tokyo");
		WeatherData d = new WeatherData(t);
		WeatherPreferences p = new WeatherPreferences();
		Forecast5Day test = new Forecast5Day(d,p);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(test.getPanel());
		frame.setVisible(true);
		frame.pack();
		frame.setTitle("5 Day Weather Forecast for London, Canada");
	}
}
