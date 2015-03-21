package main.java;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TheWX {

	JFrame frame = new JFrame("BorderLayout");
	JButton btn1 = new JButton("NORTH");
	JLabel footer = new JLabel("Choose a city");
	JButton btn3 = new JButton("CENTER");
	//JButton btn4 = new JButton("WEST");
	//JButton btn5 = new JButton("EAST");
	
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
	
	 Hashtable<String, WeatherData> locations;

	public TheWX() {
		
		WeatherPreferences p = new WeatherPreferences();
		locations = new Hashtable<String, WeatherData>();
		
		comboBox.setModel(model);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
				   	 selectedValue = model.getSelectedItem().toString();
				   	
				   	if(!locations.isEmpty()) change(locations.get(model.getSelectedItem().toString()),p);
				   	 
				   	 
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
				
				
				InputTest t = new InputTest(txtAdd.getText());
				if (t.getValid()){
					
					if(locations.containsKey(t.getCityName())){
						change(locations.get(t.getCityName()),p);
					}
					else{
						
						WeatherData w = new WeatherData(t);
						model.addElement(t.getCityName());
						//model.setSelectedItem(t.getCityName());
						locations.put(t.getCityName(),w);
						change(w,p);
						
					}
					
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
		//frame.add(footer, BorderLayout.SOUTH);
		frame.add(tabbedPane, BorderLayout.CENTER);
		//frame.add(btn4, BorderLayout.WEST);
		//frame.add(btn5, BorderLayout.EAST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		

		
	}
	
	public void change(WeatherData w,WeatherPreferences p){
		
		
		
		tabbedPane.removeAll();
		
		current = new Currentweather(w,p);
		longterm = new Forecast5Day(w);
		shortterm = new Forecast24Hour(w);
		
		tabbedPane.add("Current",current.getPanel());
		tabbedPane.add("Long Term",longterm.getPanel());
		tabbedPane.add("Short Term",shortterm.getPanel());
		
		footer.setText(w.getCityName());
		
		frame.add(footer, BorderLayout.SOUTH);
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TheWX();
			}
		});
	}

}