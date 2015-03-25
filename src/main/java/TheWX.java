package main.java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class TheWX {

	private WeatherData ref;
	UIDefaults uiDefaults = UIManager.getDefaults();
	Color bgColor = new ColorUIResource(Color.BLUE);

	JFrame frame = new JFrame("Team7 WeatherApp");
	JButton btn1 = new JButton("NORTH");
	JLabel footer = new JLabel("Choose a city");
	JButton btn3 = new JButton("CENTER");
	JButton mylocadd = new JButton("Add to My Locations");
	JButton refresh = new JButton("Refresh");
	// JButton btn5 = new JButton("EAST");

	JPanel panel = new JPanel();

	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();

	JLabel firstLabel = new JLabel("FirstTab");
	JLabel secondLabel = new JLabel("SecondTab");
	//JLabel myloc = new JLabel("My Locations");
	JTabbedPane tabbedPane = new JTabbedPane();
	
	JMenuBar menubar = new JMenuBar();

	JComboBox<String> comboBox = new JComboBox<String>();
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	JTextField txtAdd = new JTextField(15);
	JButton btnAdd = new JButton("Search");
	JButton btnRemove = new JButton("Remove");
	String selectedValue;
	Currentweather current;
	Forecast5Day longterm;
	Forecast24Hour shortterm;

	Hashtable<String, WeatherData> locations;
	Hashtable<String, WeatherData> mylocations;

	JFrame locframe = new JFrame("Storage");
	JList<String> list = new JList<>();
	DefaultListModel<String> modelloc = new DefaultListModel<>();
	
	public TheWX() {

		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		
		
		list.setModel(modelloc);
		WeatherPreferences p = new WeatherPreferences();
		locations = new Hashtable<String, WeatherData>();
		mylocations = new Hashtable<String, WeatherData>();
		
		list.setMaximumSize(new Dimension(100, 100));
		//modelloc.setMinimumSize(new Dimension(100,100));
		
		//p.setShowPressure(false);
		
		boolean defaultSet = false;
		// if(location list is empty)
		while (!defaultSet) {
			JOptionPane prompt = new JOptionPane();
			String a;
			//prompt.getRootPane().setDefaultButton(JOptionPane);
			a = JOptionPane.showInputDialog("Enter a Default Location:");
			
			InputTest t = new InputTest(a);
			if (t.getValid()) {
				WeatherData w = new WeatherData(t);
				mylocations.put(t.getCityName(), w);
				// list
				modelloc.addElement(w.getCityName());
				change(w,p);
				defaultSet = true;
			} else {
				prompt.showMessageDialog(frame, "Try again!");
			}
		}
		
		
		
		comboBox.setModel(model);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectedValue = model.getSelectedItem().toString();

					if (!locations.isEmpty()) {
						change(locations
								.get(model.getSelectedItem().toString()), p);
					}

				}
			}
		});

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int i =0;
				//modelloc.addElement(ref.getCityName());
				while(!modelloc.isEmpty()){
				
					if(modelloc.get(i).equals(ref.getCityName())) break;
					i++;
					
				}
				
				modelloc.removeElementAt(i);
				//mylocations.remove(ref.getCityName());
				
				//model.removeElement(selectedValue);
				// System.out.println(selectedValue.toString());
				// locations.remove(selectedValue.toString());
				
				
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				InputTest t = new InputTest(txtAdd.getText());
				if (t.getValid()) {

					if (locations.containsKey(t.getCityName())) {
						change(locations.get(t.getCityName()), p);
					} else {

						WeatherData w = new WeatherData(t);
						model.addElement(t.getCityName());
						// selectedValue = t.getCityName();
						// model.setSelectedItem(t.getCityName());
						locations.put(t.getCityName(), w);

						change(w, p);

					}

				} else {
					System.out.println("Error");
					JOptionPane.showMessageDialog(frame,
							"Sorry there was an error please try again.");
				}
			}
		});

		mylocadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				
				if(mylocations.containsKey(ref.getCityName())){
					JOptionPane.showMessageDialog(frame, ref.getCityName() + " is already in your locations.");
				}
				else{
					mylocations.put(ref.getCityName(), ref);
					modelloc.addElement(ref.getCityName());
				}
				/*String a;
				a = JOptionPane.showInputDialog("Save a Location");
				InputTest t = new InputTest(a);
				if (t.getValid()) {

					if(mylocations.containsKey(t.getCityName())){
						change(mylocations.get(t.getCityName()),p);
					}
					
					else{
					WeatherData w = new WeatherData(t);

					mylocations.put(t.getCityName(), w);

					// list
					modelloc.addElement(t.getCityName());
					}
					// change(w, p);

				} else {
					System.out.println("Error");
					JOptionPane.showMessageDialog(frame,
							"Sorry there was an error please try again.");
				}*/

			}
		});

		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				InputTest t = new InputTest(ref.getCityName());
				WeatherData w = new WeatherData(t);
				change(w,p);
				mylocations.replace(ref.getCityName(),w);
				
			}
		});
		
		list.getSelectionModel().addListSelectionListener(e -> {
			// WeatherData w = list.getSelectedValue();
			try{
				change(mylocations.get(list.getSelectedValue()), p);
			}
			catch(Exception b){
				
			}
			});
		
		eMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        System.exit(0);
		    }
		});

		firstPanel.add(firstLabel);
		secondPanel.add(secondLabel);

		// tabbedPane.add("Current",current.getPanel());
		// tabbedPane.add("Long Term",secondPanel);

		panel.add(txtAdd);
		panel.add(btnAdd);
		panel.add(comboBox);
		
		//panel.add(myloc);
		panel.add(refresh);
		panel.add(btnRemove);
		panel.add(mylocadd);
		
		file.add(eMenuItem);
		menubar.add(file);
		
		frame.setJMenuBar(menubar);

		// frame.add(panel);

		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.NORTH);

		// frame.add(footer, BorderLayout.SOUTH);
		frame.add(tabbedPane, BorderLayout.CENTER);
		// frame.add(btn4, BorderLayout.WEST);

		frame.add(new JScrollPane(list), BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(900, 500);
		frame.setResizable(false);
		frame.setVisible(true);

		list.setSize(100, 500);
		locframe.setSize(100, 500);
		frame.getRootPane().setDefaultButton(btnAdd);

	}

	public void change(WeatherData w, WeatherPreferences p) {

		tabbedPane.removeAll();

		try {
			current = new Currentweather(w, p);
			longterm = new Forecast5Day(w);
			shortterm = new Forecast24Hour(w);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,
					"Sorry there was an error please try again.");
		}

		try {
			tabbedPane.add("Current", current.getPanel());
			tabbedPane.add("Long Term", longterm.getPanel());
			tabbedPane.add("Short Term", shortterm.getPanel());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,
					"Sorry there is a problem with the API");
		}

		footer.setText(" " + w.getCityName());
		footer.setFont(new Font("lrg", Font.PLAIN, 24));

		frame.add(footer, BorderLayout.SOUTH);
		ref = w;

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