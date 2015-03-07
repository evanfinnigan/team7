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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblCityName = new JLabel("City Name:");
		GridBagConstraints gbc_lblCityName = new GridBagConstraints();
		gbc_lblCityName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCityName.anchor = GridBagConstraints.EAST;
		gbc_lblCityName.gridx = 0;
		gbc_lblCityName.gridy = 0;
		contentPane.add(lblCityName, gbc_lblCityName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 1;
		contentPane.add(btnSearch, gbc_btnSearch);
		
		JLabel lblTemperature = new JLabel("Temperature");
		GridBagConstraints gbc_lblTemperature = new GridBagConstraints();
		gbc_lblTemperature.insets = new Insets(0, 0, 0, 5);
		gbc_lblTemperature.gridx = 0;
		gbc_lblTemperature.gridy = 3;
		contentPane.add(lblTemperature, gbc_lblTemperature);
		
		JLabel lblNewLabel = new JLabel();
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				InputTest t = new InputTest(textField.getText());

				if (t.getValid()) {
					WeatherData test = new WeatherData(t);
					lblNewLabel.setText(test.getDescription());
				
				}
			
			
			
			
			 }
			});
	}

}
