package main.java;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

public class Create24hourBlock extends TransparentPanel {
	private JLabel temp_l;
	private JLabel icon_l;
	private JLabel skycondition_l;
	private JLabel details_l;
	
	/**
	 * Create a panel to represent one 3hour block of weather data
	 * input labels containing the temperature, a weather icon, the sky condition and details  
	 * 
	 */
	public Create24hourBlock(JLabel temp,JLabel icon, JLabel skycondition, JLabel details) {
		
		temp_l = new JLabel();
		temp_l = temp;
		
		icon_l = new JLabel();
		icon_l = icon;
		skycondition_l = new JLabel();
		skycondition_l = skycondition;
		
		details_l = new JLabel();
		details_l = details;
	
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(temp_l)
						.addComponent(icon_l)
						.addComponent(skycondition_l)
						.addComponent(details_l))
					.addContainerGap(5, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(temp_l)
					.addGap(30)
					.addComponent(icon_l)
					.addGap(25)
					.addComponent(skycondition_l)
					.addGap(30)
					.addComponent(details_l)
					.addContainerGap(5, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
