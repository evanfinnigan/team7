package main.java;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Layout24hour extends TransparentPanel {

	/**
	 * Create the panel.
	 */
	public Layout24hour(TransparentPanel[] blocks) {
		
		TransparentPanel panel_0 = new TransparentPanel();
		panel_0 = blocks[0];
		panel_0.setPreferredSize(new Dimension(125,200));
		panel_0.setLocation(0, 0);
		TransparentPanel panel_1 = new TransparentPanel();
		panel_1 = blocks[1];
		panel_1.setPreferredSize(new Dimension(125,200));
		panel_1.setLocation(125, 0);
		TransparentPanel panel_2 = new TransparentPanel();
		panel_2 = blocks[2];
		panel_2.setPreferredSize(new Dimension(125,200));
		panel_2.setLocation(125, 0);
		TransparentPanel panel_3 = new TransparentPanel();
		panel_3 = blocks[3];
		panel_3.setPreferredSize(new Dimension(125,200));
		panel_3.setLocation(250, 0);
		TransparentPanel panel_4 = new TransparentPanel();
		panel_4 = blocks[4];
		panel_4.setPreferredSize(new Dimension(125,200));
		panel_4.setLocation(325, 0);
		TransparentPanel panel_5 = new TransparentPanel();
		panel_5 = blocks[5];
		panel_5.setPreferredSize(new Dimension(125,200));
		panel_5.setLocation(475, 0);
		TransparentPanel panel_6 = new TransparentPanel();
		panel_6 = blocks[6];
		panel_6.setPreferredSize(new Dimension(125,200));
		panel_6.setLocation(600, 0);
		TransparentPanel panel_7 = new TransparentPanel();
		panel_7 = blocks[7];
		panel_7.setPreferredSize(new Dimension(125,200));
		panel_7.setLocation(725, 0);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGap(40)
					.addComponent(panel_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(330, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(284, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
