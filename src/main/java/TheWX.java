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

public class TheWX {

	private WeatherData ref;
	private WeatherPreferences p;

	private JFrame frame = new JFrame("Team7 WeatherApp");

	private JPanel footerpanel = new JPanel();

	private JLabel footer = new JLabel("Choose a city");
	private JButton mylocadd = new JButton("Add to My Locations");
	private JButton refresh = new JButton("Refresh");
	private JButton degree = new JButton();
	private String time = "Unavailable";

	private JPanel panel = new JPanel();

	private JTabbedPane tabbedPane = new JTabbedPane();

	private JMenuBar menubar = new JMenuBar();

	private JComboBox<String> combo = new JComboBox<String>();
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	private String selectedValue;
	private JButton combobutton = new JButton("Search");

	private JTextField txtAdd = new JTextField(15);
	private JButton btnAdd = new JButton("Search");
	private JButton btnRemove = new JButton("Remove");
	private Currentweather current;
	private Forecast5Day longterm;
	private Forecast24Hour shortterm;

	private Hashtable<String, WeatherData> locations;
	private Hashtable<String, WeatherData> mylocations;

	private JList<String> list = new JList<>();


	private JButton btnMars = new JButton("MARS!");

	private BufferedImage img = null;
	private BackgroundPanel background;
	
	private JScrollPane eastPane;

	public TheWX() {


		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.setToolTipText("click file then exit to quit the program.");
		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.setMnemonic(KeyEvent.VK_E);

		JMenu preferences = new JMenu("Preferences");
		preferences.setMnemonic(KeyEvent.VK_F);
		preferences.setToolTipText("Change view preferences.");

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
				JOptionPane.showMessageDialog(frame,
						"Sorry, could not load preferences.");
				p = new WeatherPreferences();
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

		if (p.getLocation() == null || p.getLocation().equals("")) {
			boolean defaultSet = false;
			while (!defaultSet) {

				String a = JOptionPane.showInputDialog("Enter a Default Location:");
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
						JOptionPane.showMessageDialog(frame, "Try again!");
					}
				} else {
					defaultSet = false;
					int confirm = JOptionPane.showConfirmDialog(frame,
							"Exit program?");
					if (confirm == 0) {
						System.exit(0);
					}
				}
			}

		} else {
			InputTest t = new InputTest(p.getLocation());
			if (t.getValid()) {
				WeatherData w = new WeatherData(t);
				change(w, p);
				mylocations.put(t.getCityName(), w);

				if (!modelloc.contains(t.getCityName())) {
					modelloc.addElement(t.getCityName());
				}

				list.setSelectedIndex(0);
			} else {
				JOptionPane.showMessageDialog(frame, "Try again!");
			}
		}

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int i = 0;

					while (!modelloc.isEmpty()) {

						if (modelloc.get(i).equals(ref.getCityName())) {
							break;
						}
						i++;
					}

					modelloc.removeElementAt(i);
					mylocations.remove(ref.getCityName());
					if (modelloc.isEmpty())
						btnRemove.setEnabled(false);

				} catch (Exception a) {
					// Do nothing
				}
			}
		});

		btnMars.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (p.getShowMars()) {
					p.setShowMars(false);
					btnRemove.setEnabled(true);
					mylocadd.setEnabled(true);
					degree.setEnabled(true);
					refresh.setEnabled(true);
					combobutton.setEnabled(true);
					combo.setEnabled(true);
					list.setEnabled(true);
					change(ref, p);
					
				} else {
					p.setShowMars(true);
					btnRemove.setEnabled(false);
					mylocadd.setEnabled(false);
					degree.setEnabled(false);
					refresh.setEnabled(false);
					combobutton.setEnabled(false);
					combo.setEnabled(false);
					change(ref, p);
					footer.setText("Mars Weather");
					list.setEnabled(false);
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
						locations.put(t.getCityName(), w);
						if (!mylocations.containsKey(t.getCityName())) {
							list.clearSelection();
						}
						change(w, p);

					}

				} else {
					JOptionPane.showMessageDialog(frame,
							"Sorry, the city was not found!");
				}
			}
		});

		mylocadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int i = 0;
				boolean check = false;

				while (modelloc.size() > i) {

					if (modelloc.get(i).equals(ref.getCityName())) {
						check = true;
						break;
					}
					i++;
				}

				if (check) {
					JOptionPane.showMessageDialog(frame, ref.getCityName()
							+ " is already in your locations.");
				}

				else {
					mylocations.put(ref.getCityName(), ref);
					modelloc.addElement(ref.getCityName());
					list.setSelectedValue(ref.getCityName(), true);
					btnRemove.setEnabled(true);
				}
				// if (mylocations.containsKey(ref.getCityName())) {
				// JOptionPane.showMessageDialog(frame, ref.getCityName()
				// + " is already in your locations.");
				// } else {
				// mylocations.put(ref.getCityName(), ref);
				// modelloc.addElement(ref.getCityName());
				// list.setSelectedValue(ref.getCityName(), true);
				// btnRemove.setEnabled(true);
				// }

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
			}
		});

		list.getSelectionModel().addListSelectionListener(e -> {
			try {
				if (!mylocations.containsKey(list.getSelectedValue())) {
					InputTest t = new InputTest(list.getSelectedValue());
					WeatherData w = new WeatherData(t);
					mylocations.put(w.getCityName(), w);
					locations.put(w.getCityName(), w);
					combo.setSelectedIndex(-1);
					change(w, p);

				} else {
					change(mylocations.get(list.getSelectedValue()), p);
					combo.setSelectedIndex(-1);
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

		combo.setModel(model);
		combo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {

					selectedValue = model.getSelectedItem().toString();
					if (!locations.isEmpty()) {
						if (locations.containsKey(selectedValue)) {
							change(locations.get(model.getSelectedItem()
									.toString()), p);

						}
					}
				}
			}

		});

		combobutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				InputTest t = new InputTest(combo.getEditor().getItem()
						.toString());
				System.out.println(combo.getEditor().getItem().toString());
				if (t.getValid()) {
					if (locations.containsKey(t.getCityName())) {
						change(locations.get(t.getCityName()), p);

					} else {

						WeatherData w = new WeatherData(t);
						locations.put(t.getCityName(), w);
						combo.addItem(t.getCityName());
						change(w, p);

					}

				} else {
					JOptionPane.showMessageDialog(frame,
							"Sorry, the city was not found!");
				}
			}
		});

		combo.setEditable(true);

		panel.add(btnMars);
		panel.add(refresh);
		// panel.add(txtAdd);
		// panel.add(btnAdd);
		panel.add(combo);
		panel.add(combobutton);
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

		footerpanel.add(footer);

		frame.setJMenuBar(menubar);
		frame.setLayout(new BorderLayout());

		frame.add(panel, BorderLayout.NORTH);
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.add(footerpanel, BorderLayout.SOUTH);
		footer.setFont(new Font("lrg", Font.PLAIN, 24));

		eastPane = new JScrollPane(list);
		eastPane.setPreferredSize(new Dimension(100, 180));
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
		frame.setSize(1024, 500);
		// frame.setResizable(false);
		frame.setVisible(true);

		list.setSize(100, 500);

		frame.getRootPane().setDefaultButton(combobutton);
		setAccessibility();
	}

	public void change(WeatherData w, WeatherPreferences p) {

		tabbedPane.removeAll();
		ref = w;

		try {
			time = w.getTimeOfLastRequest();

			current = new Currentweather(w, p, time);
			longterm = new Forecast5Day(w, p);
			shortterm = new Forecast24Hour(w, p);

			// System.out.println(w.getSkyConditionCurrent());

			if (w.getIconCode().equals("01d") || w.getIconCode().equals("01n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO
							.read(getClass().getClassLoader()
									.getResourceAsStream(
											"./resources/images/clear.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
				current.setLabelColor(Color.black);
			}

			else if (w.getIconCode().equals("02d")
					|| w.getIconCode().equals("02n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO.read(getClass().getClassLoader()
							.getResourceAsStream(
									"./resources/images/fewclouds.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
			}

			else if (w.getIconCode().equals("03d")
					|| w.getIconCode().equals("03n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO.read(getClass().getClassLoader()
							.getResourceAsStream(
									"./resources/images/scatteredclouds.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
			}

			else if (w.getIconCode().equals("04d")
					|| w.getIconCode().equals("04n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO.read(getClass().getClassLoader()
							.getResourceAsStream(
									"./resources/images/brokenclouds.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
			}

			else if (w.getIconCode().equals("09d")
					|| w.getIconCode().equals("09n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO.read(getClass().getClassLoader()
							.getResourceAsStream(
									"./resources/images/showerrain.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
			}

			else if (w.getIconCode().equals("10d")
					|| w.getIconCode().equals("10n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO
							.read(getClass().getClassLoader()
									.getResourceAsStream(
											"./resources/images/rain.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
				current.setLabelColor(Color.WHITE);
				shortterm.setLabelColor(Color.WHITE);
				longterm.setLabelColor(Color.WHITE);
			}

			else if (w.getIconCode().equals("11d")
					|| w.getIconCode().equals("11n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO.read(getClass().getClassLoader()
							.getResourceAsStream(
									"./resources/images/thunderstorm.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
				current.setLabelColor(Color.WHITE);
				shortterm.setLabelColor(Color.WHITE);
				longterm.setLabelColor(Color.WHITE);
			}

			else if (w.getIconCode().equals("13d")
					|| w.getIconCode().equals("13n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO
							.read(getClass().getClassLoader()
									.getResourceAsStream(
											"./resources/images/snow.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
			}

			else if (w.getIconCode().equals("50d")
					|| w.getIconCode().equals("50n")) {
				// background = new BackgroundPanel(img,BackgroundPanel.SCALED,
				// 0.50f, 0.5f);
				try {
					img = ImageIO
							.read(getClass().getClassLoader()
									.getResourceAsStream(
											"./resources/images/mist.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
				current.setLabelColor(Color.WHITE);
				shortterm.setLabelColor(Color.WHITE);
				longterm.setLabelColor(Color.WHITE);
			}

			else {
				try {
					img = ImageIO
							.read(getClass().getClassLoader()
									.getResourceAsStream(
											"./resources/images/clear.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
			}

			tabbedPane.add("Current", current.getPanel());
			tabbedPane.add("Long Term", longterm.getPanel());
			tabbedPane.add("Short Term", shortterm.getPanel());

			if (p.getShowMars()) {
				tabbedPane.removeAll();
				MarsPanel marspanel = new MarsPanel(w);
				JPanel marsp = marspanel.getPanel();
				try {
					img = ImageIO
							.read(getClass().getClassLoader()
									.getResourceAsStream(
											"./resources/images/mars.jpg"));
				} catch (Exception e) {
					System.out.println("Cannot read file for clear.jpg: " + e);
				}
				background = new BackgroundPanel(img, BackgroundPanel.SCALED,
						0.50f, 0.5f);
				frame.setContentPane(background);
				tabbedPane.add("MARS WEATHER", marsp);
				p.setShowMars(true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,
					"There was a problem. Please try again.");
		}

		footer.setText(" " + w.getCityName());

		background.add(tabbedPane);
		frame.add(footerpanel, BorderLayout.SOUTH);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(tabbedPane, BorderLayout.CENTER);
		eastPane = new JScrollPane(list);
		eastPane.setPreferredSize(new Dimension(100, 180));
		frame.add(eastPane, BorderLayout.EAST);

	}
	private void setAccessibility(){
		btnMars.setToolTipText("click to check the weather on mars.");
		combobutton.setToolTipText("Search for a forecast.");
		degree.setToolTipText("Switch between " + "\u00b0" + "C" + " and " + "\u00b0" + "F.");
		refresh.setToolTipText("Click to refresh weather data.");
		mylocadd.setToolTipText("Add current city to my location list.");
		btnRemove.setToolTipText("Remove selected city from my locations list.");
		combo.setToolTipText("Enter city name here.");
		
		
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