package team7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

public class UItest3 extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private Forecast5Day update;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UItest3 frame = new UItest3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UItest3() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		//this.setMinimumSize(new Dimension(int, int));
		//this.setMaximumSize(new Dimension(int, int));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 0, 0);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Weather Project");
		lblNewLabel_1.setBounds(249, 0, 97, 12);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCityName = new JLabel("City Name:");
		lblCityName.setBounds(45, 23, 69, 16);
		contentPane.add(lblCityName);
		
		textField = new JTextField();
		textField.setBounds(130, 17, 342, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(477, 17, 85, 29);
		contentPane.add(btnSearch);
		
		JLabel lblSunrise = new JLabel("Sunrise:");
		lblSunrise.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSunrise.setBounds(33, 100, 105, 20);
		contentPane.add(lblSunrise);
		
		JLabel lblNewLabel_2 = new JLabel("1");
		lblNewLabel_2.setBounds(224, 100, 161, 115);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblSunset = new JLabel("Sunset");
		lblSunset.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSunset.setBounds(451, 100, 100, 20);
		contentPane.add(lblSunset);
		//panel = Forecast5day().get
		
		JLabel lblSunriseVal = new JLabel("SunriseVal");
		lblSunriseVal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSunriseVal.setBounds(33, 130, 105, 20);
		contentPane.add(lblSunriseVal);
		
		JLabel lblSunsetval = new JLabel("SunsetVal");
		lblSunsetval.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSunsetval.setBounds(451, 130, 100, 20);
		contentPane.add(lblSunsetval);
		
		JLabel lblWindSpeed = new JLabel("Wind Speed");
		lblWindSpeed.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblWindSpeed.setBounds(33, 170, 191, 20);
		contentPane.add(lblWindSpeed);
		
		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblCondition.setBounds(451, 170, 100, 20);
		contentPane.add(lblCondition);
		
		JLabel lblWindSpeedVal = new JLabel("WindSpeedVal");
		lblWindSpeedVal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblWindSpeedVal.setBounds(33, 200, 191, 20);
		contentPane.add(lblWindSpeedVal);
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(277, 195, 69, 124);
		contentPane.add(lblImg);
		
		JLabel lblConditionVal = new JLabel("ConditionVal");
		lblConditionVal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblConditionVal.setBounds(451, 200, 131, 20);
		contentPane.add(lblConditionVal);
		
		JLabel lblAirPressure = new JLabel("Air Pressure");
		lblAirPressure.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAirPressure.setBounds(33, 240, 174, 20);
		contentPane.add(lblAirPressure);
		
		JLabel lblHumudity = new JLabel("Humudity");
		lblHumudity.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHumudity.setBounds(451, 240, 117, 22);
		contentPane.add(lblHumudity);
		
		JLabel lblAirPressureVal = new JLabel("AirPressureVal");
		lblAirPressureVal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAirPressureVal.setBounds(33, 270, 174, 20);
		contentPane.add(lblAirPressureVal);
		
		JLabel lblHumudityVal = new JLabel("HumudityVal");
		lblHumudityVal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHumudityVal.setBounds(451, 270, 131, 20);
		contentPane.add(lblHumudityVal);
		
		panel = new JPanel();
		panel.setBounds(0, 433, 639, 254);
		contentPane.add(panel);
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				InputTest t = new InputTest(textField.getText());

				if (t.getValid()) {
					WeatherData test = new WeatherData(t);
					
					lblNewLabel.setText(test.getDescription());
					System.out.println(Double.toString(test.getTempCurrent()));
					
					int tn = (int)test.getTempCurrent();
					lblNewLabel_2.setText(Integer.toString(tn));
					
					lblSunriseVal.setText(test.getSunrise().substring(11,16));
					//System.out.println(test.getSunrise().substring(11,16));
					
					int tn2 = (int)test.getWindSpeed();
					lblWindSpeedVal.setText(Integer.toString(tn2) + " km/h");
					
					int tn3 = (int)test.getPressure();
					lblAirPressureVal.setText(Integer.toString(tn3) + "kPa" );
					
					lblSunsetval.setText(test.getSunset().substring(11,16));
					
					lblConditionVal.setText(test.getSkyConditionCurrent());
					
					lblHumudityVal.setText(Integer.toString(test.getHumidity()));
					
					lblImg.setIcon(new ImageIcon(test.getIcon()));
					
					
					
					
					
					//update = new Forecast5Day(test);
					//panel = update.getPanel();
					
					
					
				
				}
			
			
			 }
			/*public void updatePanel(Forecast5Day f){
				panel = f.getPanel();
				panel.revalidate();
				panel.repaint();
				
				
				
				
			}*/
			
			});
		
		
	}
}
