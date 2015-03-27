package main.java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

import org.json.JSONException;

public class TheWX {

	private WeatherData ref;
	private WeatherPreferences p;
	UIDefaults uiDefaults = UIManager.getDefaults();
	Color bgColor = new ColorUIResource(Color.BLUE);

	JFrame frame = new JFrame("Team7 WeatherApp");
	JButton btn1 = new JButton("NORTH");
	JLabel footer = new JLabel("Choose a city");
	JButton btn3 = new JButton("CENTER");
	JButton mylocadd = new JButton("Add to My Locations");
	JButton refresh = new JButton("Refresh");
	JButton degree = new JButton();
	String time = "Unavailable";
	// JButton btn5 = new JButton("EAST");

	JPanel panel = new JPanel();

	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();

	JLabel firstLabel = new JLabel("FirstTab");
	JLabel secondLabel = new JLabel("SecondTab");
	// JLabel myloc = new JLabel("My Locations");
	JTabbedPane tabbedPane = new JTabbedPane();

	JMenuBar menubar = new JMenuBar();

	// JComboBox<String> comboBox = new JComboBox<String>();
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

	
	JList<String> list = new JList<>();
	//DefaultListModel<String> modelloc = new DefaultListModel<>();

	public TheWX() {

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.setMnemonic(KeyEvent.VK_E);

		JMenu preferences = new JMenu("Preferences");
		preferences.setMnemonic(KeyEvent.VK_F);

		JCheckBoxMenuItem hideHumidity = new JCheckBoxMenuItem("Humidity", true);
		JCheckBoxMenuItem hideWindDir = new JCheckBoxMenuItem("Wind Direction",
				true);
		JCheckBoxMenuItem hideWindSpeed = new JCheckBoxMenuItem("Wind Speed",
				true);
		JCheckBoxMenuItem hideAirPre = new JCheckBoxMenuItem("Air Pressure",
				true);
		JCheckBoxMenuItem hideSunrise = new JCheckBoxMenuItem("Sunrise", true);
		JCheckBoxMenuItem hideSunset = new JCheckBoxMenuItem("Sunset", true);

		
		

		p = new WeatherPreferences();
		File f = new File("save.dat");
		if (f.exists()) {
			try {
				ObjectInputStream is = new ObjectInputStream(
						new FileInputStream(f));
				p = (WeatherPreferences) is.readObject();

				is.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if (p.getTempUnit().equalsIgnoreCase("C")) {
			degree.setText("\u00b0" + "F");
		} else {
			degree.setText("\u00b0" + "C");
		}

		if (!p.getShowHumidity())
			hideHumidity.setSelected(false);
		if (!p.getShowWindDirection())
			hideWindDir.setSelected(false);
		if (!p.getShowWindSpeed())
			hideWindSpeed.setSelected(false);
		if (!p.getShowPressure())
			hideAirPre.setSelected(false);
		if (!p.getShowSunrise())
			hideSunrise.setSelected(false);
		if (!p.getShowSunset())
			hideSunset.setSelected(false);

		locations = new Hashtable<String, WeatherData>();
		mylocations = new Hashtable<String, WeatherData>();
		
		DefaultListModel<String> modelloc = p.getlist();
		list.setModel(modelloc);

		list.setMaximumSize(new Dimension(100, 100));
		// modelloc.setMinimumSize(new Dimension(100,100));

		if (p.getLocation() == null || p.getLocation().equals("")) {
			boolean defaultSet = false;
			while (!defaultSet) {
				JOptionPane prompt = new JOptionPane();
				String a;
				a = prompt.showInputDialog("Enter a Default Location:");
				if (a != null && a.length() > 0) {
					InputTest t = new InputTest(a);

					if (t.getValid()) {
						WeatherData w = new WeatherData(t);
						mylocations.put(t.getCityName(), w);
						// list
						modelloc.addElement(t.getCityName());
						list.setSelectedIndex(0);
						p.setLocation(t.getCityName());
						change(w, p);
						defaultSet = true;
					} else {
						prompt.showMessageDialog(frame, "Try again!");
					}
				} else {
					defaultSet = false;
					int confirm = prompt.showConfirmDialog(frame,
							"Exit program?");
					if (confirm == 0) {
						System.exit(0);
					}
				}
			}

		} else {
			InputTest t = new InputTest(p.getLocation());
			WeatherData w = new WeatherData(t);
			change(w, p);
			//modelloc.addElement(t.getCityName());
			mylocations.put(t.getCityName(), w);
			list.setSelectedIndex(0);
		}

		// comboBox.setModel(model);
		// comboBox.addItemListener(new ItemListener() {
		// @Override
		// public void itemStateChanged(ItemEvent e) {
		// if (e.getStateChange() == ItemEvent.SELECTED) {
		// selectedValue = model.getSelectedItem().toString();
		//
		// if (!locations.isEmpty()) {
		// change(locations
		// .get(model.getSelectedItem().toString()), p);
		// }
		//
		// }
		// }
		// });

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int i = 0;
					// modelloc.addElement(ref.getCityName());
					while (!modelloc.isEmpty()) {

						if (modelloc.get(i).equals(ref.getCityName())) {
							// System.out.println(ref.getCityName());
							break;
						}
						i++;
					}

					modelloc.removeElementAt(i);
					mylocations.remove(ref.getCityName());

					// model.removeElement(selectedValue);
					// System.out.println(selectedValue.toString());
					// locations.remove(selectedValue.toString());

				} catch (Exception ex) {
					// Do nothing
				}

			}
		});

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				InputTest t = new InputTest(txtAdd.getText());
				if (t.getValid()) {
					if (locations.containsKey(t.getCityName())) {
						change(locations.get(t.getCityName()), p);
						if (!mylocations.containsKey(t.getCityName())) {
							list.clearSelection();
						}
					} else {

						WeatherData w = new WeatherData(t);
						model.addElement(t.getCityName());
						// selectedValue = t.getCityName();
						// model.setSelectedItem(t.getCityName());
						locations.put(t.getCityName(), w);
						if (!mylocations.containsKey(t.getCityName())) {
							list.clearSelection();
						}
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

				if (mylocations.containsKey(ref.getCityName())) {
					JOptionPane.showMessageDialog(frame, ref.getCityName()
							+ " is already in your locations.");
				} else {
					mylocations.put(ref.getCityName(), ref);
					modelloc.addElement(ref.getCityName());
					list.setSelectedValue(ref.getCityName(), true);
				}

			}
		});

		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InputTest t = new InputTest(ref.getCityName());
				if (t.getValid()) {
					WeatherData w = new WeatherData(t);
					change(w, p);
					mylocations.replace(ref.getCityName(), w);
				}
			}
		});

		degree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (degree.getText().equals("\u00b0" + "C")) {
					p.setTempUnit("C");
					change(ref, p);
					degree.setText("\u00b0" + "F");
				}

				else {
					p.setTempUnit("F");
					change(ref, p);
					degree.setText("\u00b0" + "C");
				}
				// mylocations.replace(ref.getCityName(),w);

			}
		});

		list.getSelectionModel().addListSelectionListener(e -> {
			// WeatherData w = list.getSelectedValue();
				try {
					if (!mylocations.containsKey(list.getSelectedValue())){
						InputTest t = new InputTest(list.getSelectedValue());
						WeatherData w = new WeatherData(t);
						mylocations.put(w.getCityName(),w);
						change(w,p);
					} else {
						change(mylocations.get(list.getSelectedValue()), p);
					}
					
				} catch (Exception b) {

				}
			});

		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String filename = "save.dat";
				ObjectOutputStream os;
				try {
					os = new ObjectOutputStream(new FileOutputStream(filename));
					os.writeObject(p);
					os.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.exit(0);
			}
		});

		// /PREFERENCES////
		hideSunset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (p.getShowSunset()) {
					p.setShowSunset(false);
					change(ref, p);
				} else {
					p.setShowSunset(true);
					change(ref, p);
				}
			}
		});

		hideSunrise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (p.getShowSunrise()) {
					p.setShowSunrise(false);
					change(ref, p);
				} else {
					p.setShowSunrise(true);
					change(ref, p);
				}
			}
		});

		hideAirPre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (p.getShowPressure()) {
					p.setShowPressure(false);
					change(ref, p);
				} else {
					p.setShowPressure(true);
					change(ref, p);
				}
			}
		});

		hideWindSpeed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (p.getShowWindSpeed()) {
					p.setShowWindSpeed(false);
					change(ref, p);
				} else {
					p.setShowWindSpeed(true);
					change(ref, p);
				}
			}
		});

		hideWindDir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (p.getShowWindDirection()) {
					p.setShowWindDirection(false);
					change(ref, p);
				} else {
					p.setShowWindDirection(true);
					change(ref, p);
				}
			}
		});

		hideHumidity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (p.getShowHumidity()) {
					p.setShowHumidity(false);
					change(ref, p);
				} else {
					p.setShowHumidity(true);
					change(ref, p);
				}
			}
		});

		firstPanel.add(firstLabel);
		secondPanel.add(secondLabel);

		// tabbedPane.add("Current",current.getPanel());
		// tabbedPane.add("Long Term",secondPanel);

		panel.add(refresh);
		panel.add(txtAdd);
		panel.add(btnAdd);
		panel.add(degree);
		panel.add(mylocadd);
		panel.add(btnRemove);

		file.add(eMenuItem);
		menubar.add(file);

		preferences.add(hideHumidity);
		preferences.add(hideWindDir);
		preferences.add(hideWindSpeed);
		preferences.add(hideAirPre);
		preferences.add(hideSunrise);
		preferences.add(hideSunset);

		menubar.add(preferences);

		frame.setJMenuBar(menubar);

		// frame.add(panel);

		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.NORTH);

		// frame.add(footer, BorderLayout.SOUTH);
		frame.add(tabbedPane, BorderLayout.CENTER);
		// frame.add(btn4, BorderLayout.WEST);

		JScrollPane eastPane = new JScrollPane(list);
		eastPane.setPreferredSize(new Dimension(80, 180));
		frame.add(eastPane, BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent event) {
				String filename = "save.dat";
				ObjectOutputStream os;
				try {
					os = new ObjectOutputStream(new FileOutputStream(filename));
					os.writeObject(p);
					os.close();
				} catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowOpened(WindowEvent e) {

			}
		});
		
		frame.pack();
		frame.setSize(900, 500);
		frame.setResizable(false);
		frame.setVisible(true);

		list.setSize(100, 500);
		//locframe.setSize(100, 500);
		frame.getRootPane().setDefaultButton(btnAdd);

	}

	public void change(WeatherData w, WeatherPreferences p) {

		tabbedPane.removeAll();
		ref = w;

		try {
			time = w.getTimeOfLastRequest();

			current = new Currentweather(w, p, time);
			longterm = new Forecast5Day(w, p);
			shortterm = new Forecast24Hour(w, p);
			tabbedPane.add("Current", current.getPanel());
			tabbedPane.add("Current", current.getPanel());
			tabbedPane.add("Long Term", longterm.getPanel());
			tabbedPane.add("Short Term", shortterm.getPanel());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(frame,
					"There was an issue. Try again.");
		}

		footer.setText(" " + w.getCityName());
		footer.setFont(new Font("lrg", Font.PLAIN, 24));

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