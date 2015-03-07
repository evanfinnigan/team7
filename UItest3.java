package team7;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class UItest3 extends JFrame {

	private JPanel contentPane;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 0, 0);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Weather Project");
		lblNewLabel_1.setBounds(203, 10, 97, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCityName = new JLabel("City Name:");
		lblCityName.setBounds(10, 37, 69, 16);
		contentPane.add(lblCityName);
		
		textField = new JTextField();
		textField.setBounds(84, 31, 347, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setBounds(329, 176, 79, 16);
		contentPane.add(lblTemperature);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(443, 32, 85, 29);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel_2 = new JLabel("1");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(203, 132, 85, 106);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 418, 584, 254);
		contentPane.add(panel);
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				InputTest t = new InputTest(textField.getText());

				if (t.getValid()) {
					WeatherData test = new WeatherData(t);
					lblNewLabel.setText(test.getDescription());
					System.out.println(Double.toString(test.getTempCurrent()));
					
					int n = (int)test.getTempCurrent();
					lblNewLabel_2.setText(Integer.toString(n));
				
				}
			
			
			 }
			});
	}
}
