package main.java;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Panel24hour extends TransparentPanel {
	private Block24hour panel_3hr;
	private Block24hour panel_6hr;
	private Block24hour panel_9hr;
	private Block24hour panel_12hr;
	private Block24hour panel_15hr;
	private Block24hour panel_18hr;
	private Block24hour panel_21hr;
	private Block24hour panel_24hr;
	/**
	 * Constructor
	 * @param blocks is an array of transparent panels to be arranged in 1 row and 8 columns
	 */
	public Panel24hour(Block24hour[] blocks) {
		this.setPreferredSize(new Dimension(1000,200));
		panel_3hr = new Block24hour();
		panel_3hr = blocks[0];
		panel_3hr.setPreferredSize(new Dimension(125,200));
		panel_3hr.setLocation(0, 0);
		panel_6hr = new Block24hour();
		panel_6hr = blocks[1];
		panel_6hr.setPreferredSize(new Dimension(125,200));
		panel_6hr.setLocation(125, 0);
	    panel_9hr = new Block24hour();
		panel_9hr = blocks[2];
		panel_9hr.setPreferredSize(new Dimension(125,200));
		panel_9hr.setLocation(125, 0);
		panel_12hr = new Block24hour();
		panel_12hr = blocks[3];
		panel_12hr.setPreferredSize(new Dimension(125,200));
		panel_12hr.setLocation(250, 0);
		panel_15hr = new Block24hour();
		panel_15hr = blocks[4];
		panel_15hr.setPreferredSize(new Dimension(125,200));
		panel_15hr.setLocation(325, 0);
		panel_18hr = new Block24hour();
		panel_18hr = blocks[5];
		panel_18hr.setPreferredSize(new Dimension(125,200));
		panel_18hr.setLocation(475, 0);
		panel_21hr = new Block24hour();
		panel_21hr = blocks[6];
		panel_21hr.setPreferredSize(new Dimension(125,200));
		panel_21hr.setLocation(600, 0);
		panel_24hr = new Block24hour();
		panel_24hr = blocks[7];
		panel_24hr.setPreferredSize(new Dimension(125,200));
		panel_24hr.setLocation(725, 0);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_9hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_12hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_15hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_18hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_21hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_24hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(5, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_24hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_21hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_18hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_15hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_12hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_9hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3hr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(5, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	/**
	 * updates panel labels with specified color
	 * @param acolor Color.awt
	 */
	public void setPanelLabelColor(Color acolor){
		 panel_24hr.setBlockLabelColor(acolor);
		 panel_21hr.setBlockLabelColor(acolor);
		 panel_18hr.setBlockLabelColor(acolor);
		 panel_15hr.setBlockLabelColor(acolor);
		 panel_12hr.setBlockLabelColor(acolor);
		 panel_9hr.setBlockLabelColor(acolor);
		 panel_6hr.setBlockLabelColor(acolor);
		 panel_3hr.setBlockLabelColor(acolor);
		 this.repaint();
	}
	/**
	 * takes and array of JLabels which contain temperature values for the 8 3hr panels
	 * @param temparray
	 */
	public void updateTemp(JLabel[] temparray){
		 panel_24hr.setTemp(temparray[7]);
		 panel_21hr.setTemp(temparray[6]);
		 panel_18hr.setTemp(temparray[5]);
		 panel_15hr.setTemp(temparray[4]);
		 panel_12hr.setTemp(temparray[3]);
		 panel_9hr.setTemp(temparray[2]);
		 panel_6hr.setTemp(temparray[1]);
		 panel_3hr.setTemp(temparray[0]);
		 this.repaint();
	}
	
}
