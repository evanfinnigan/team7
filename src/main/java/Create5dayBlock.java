package main.java;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
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
	 *  Constructor Create5DayBlock
	 * @param temp JLabel to display the temperature
	 * @param high JLabel for the temperature high
	 * @param low JLabel for the temperature low
	 * @param desc JLabel for the weather description
	 * @param skycond JLabel for the skycondition
	 * @param img JLabel for the icon 
	 */
	public Create5dayBlock(JLabel temp, JLabel high, JLabel low, JLabel desc, JLabel skycond, JLabel img) {
		
		temp_l = new JLabel("t");
		temp_l = temp;
		
		high_l = new JLabel("h");
		high_l = high;
		
	    low_l = new JLabel("l");
		low_l = low;
	
	    image_l = new JLabel("img");
		image_l =img;
		skycondition_l = new JLabel("cond");
		skycondition_l = skycond;
		
		weatherdescription_l = new JLabel("desc");
		weatherdescription_l = desc;
	
		
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
}
