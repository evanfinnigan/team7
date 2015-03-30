package main.java;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
/**
 *  Class to create a transparent panel containing the data 
 *  to be used in 1 of the 8 24hour blocks
 * 
 */
public class Block24hour extends TransparentPanel {
	private JLabel temp_l;
	private JLabel icon_l;
	private JLabel skycondition_l;
	private JLabel details_l;
	/**
	 * default constructor
	 */
	public Block24hour(){
		temp_l = new JLabel();
		icon_l = new JLabel();
		skycondition_l = new JLabel();
		details_l = new JLabel();
	}
	/**
	 *  Create a panel to represent one 3hour block of weather data
	 *  labels containing the temperature, a weather icon, the sky condition and details  
	 * @param temp temperature JLabel
	 * @param icon icon JLabel
	 * @param skycondition skycondition JLabel
	 * @param details details JLabel
	 */
	public Block24hour(JLabel temp,JLabel icon, JLabel skycondition, JLabel details) {
		
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
	/**
	 * Sets the Block's labels foreground color
	 * @param acolor
	 */
	public void setBlockLabelColor(Color acolor){
		temp_l.setForeground(acolor);
		skycondition_l.setForeground(acolor);
		details_l.setForeground(acolor);
		icon_l.setForeground(acolor);
	}
	/**
	 * Sets the temp_l label to JLabel passed as input 
	 * @param JLabel new temperature label
	 */
	public void setTemp(JLabel tempnew){
		this.temp_l = tempnew;
	}
	/**
	 * Getter for the temp label
	 * @return JLabel temp_l
	 */
	public JLabel getTemp(){
		return temp_l;
	}
	
}
