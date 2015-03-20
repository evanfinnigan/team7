package main.java;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TheW {

	JFrame frame = new JFrame("BorderLayout");
	JButton btn1 = new JButton("NORTH");
	JButton btn2 = new JButton("SOUTH");
	JButton btn3 = new JButton("CENTER");
	JButton btn4 = new JButton("WEST");
	JButton btn5 = new JButton("EAST");
	
	JPanel panel = new JPanel();
	
	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();
	
	JLabel firstLabel = new JLabel("FirstTab");
	JLabel secondLabel = new JLabel("SecondTab");
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	JComboBox<String> comboBox = new JComboBox<String>();
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	JTextField txtAdd = new JTextField(15);
	JButton btnAdd = new JButton("Add");
	JButton btnRemove = new JButton("Remove");
	String selectedValue;
	Currentweather current;
	Forecast5Day longterm;
	Forecast24Hour shortterm;

	public TheW() {
		
		comboBox.setModel(model);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
				   	 selectedValue = model.getSelectedItem().toString();
				   	 
				}
			}
		});

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.removeElement(selectedValue);
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.removeAll();
				InputTest t = new InputTest(txtAdd.getText());
				if (t.getValid()){
					model.addElement(t.getCityName());
					WeatherData w = new WeatherData(t);
					WeatherPreferences p = new WeatherPreferences();
					
					current = new Currentweather(w,p);
					longterm = new Forecast5Day(w);
					shortterm = new Forecast24Hour(w);
					
					tabbedPane.add("Current",current.getPanel());
					tabbedPane.add("Long Term",longterm.getPanel());
					tabbedPane.add("Short Term",shortterm.getPanel());
					
				} else {
					System.out.println("Error");
				}
			}
		});
		
		
		firstPanel.add(firstLabel);
		secondPanel.add(secondLabel);
		
		//tabbedPane.add("Current",current.getPanel());
		//tabbedPane.add("Long Term",secondPanel);

		panel.add(txtAdd);
		panel.add(btnAdd);
		panel.add(comboBox);
		panel.add(btnRemove);
		
		
		
		//frame.add(panel);
		
	
		
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.NORTH);
		frame.add(btn2, BorderLayout.SOUTH);
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.add(btn4, BorderLayout.WEST);
		frame.add(btn5, BorderLayout.EAST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		

		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TheW();
			}
		});
	}

}