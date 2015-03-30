package main.java;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Panel5day extends TransparentPanel {
	private Block5day panelday1;
	private Block5day panelday2;
	private Block5day panelday3;
	private Block5day panelday4;
	private Block5day panelday5;
	
	/**
	 * Constructor
	 * @param blocks an array of transparent panels to be arranged in 1 row and 5 columns
	 */
	 public Panel5day(Block5day[] blocks) {
		
		panelday1 = new Block5day();
		panelday1 = blocks[0];
		panelday2 = new Block5day();
		panelday2 = blocks[1];
		panelday3 = new Block5day();
		panelday3 = blocks[2];
		panelday4 = new Block5day();
		panelday4 = blocks[3];
		panelday5 = new Block5day();
		panelday5 = blocks[4];
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelday1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGap(22)
					.addComponent(panelday2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGap(22)
					.addComponent(panelday3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGap(22)
					.addComponent(panelday4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGap(22)
					.addComponent(panelday5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(5, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelday5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelday4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelday3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelday2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelday1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(5, Short.MAX_VALUE))
		);
		this.setLayout(groupLayout);

	}
	 /**
	  * changes the Labels foreground color in each of the 5 day Blocks
	  * @param acolor
	  */
	 public void setPanelLabelColor(Color acolor){
		 panelday1.setBlockLabelColor(acolor);
		 panelday2.setBlockLabelColor(acolor);
		 panelday3.setBlockLabelColor(acolor);
		 panelday4.setBlockLabelColor(acolor);
		 panelday5.setBlockLabelColor(acolor);
		 this.repaint();
	 }
	 /**
	  * updates the Temperature labels in each of the 5 day blocks 
	  * @param JLabel[] temperaturearray
	  */
	 public void updateTemp(JLabel[] temperaturearray){
		 panelday1.setTemp(temperaturearray[0]);
		 panelday2.setTemp(temperaturearray[1]);
		 panelday3.setTemp(temperaturearray[2]);
		 panelday4.setTemp(temperaturearray[3]);
		 panelday5.setTemp(temperaturearray[4]);
		 this.repaint();
	 }
}
