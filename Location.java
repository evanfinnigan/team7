package team7;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class Location implements ActionListener{
	private JPanel locpane;
	private String name;
	private Currentweather weather;
	private Forecast24Hour shortterm;
	private Forecast5Day longterm;
	private JButton sh_shortterm, sh_longterm;
	private boolean bshort,blong;
	
	public Location(WeatherData data, WeatherPreferences prefs) {
		locpane = new JPanel();
		name = data.getCityName();
		JLabel locname_l = new JLabel(name);
		locpane.setLayout(new GridLayout(0,1));
		locpane.add(locname_l);
		
		weather = new Currentweather(data, prefs);
		shortterm = new Forecast24Hour(data);
		longterm = new Forecast5Day(data);
		
		locpane.add(weather.getPanel());
		//sh_shortterm = new JButton("Show 24 hour forecast");
		//locpane.add(sh_shortterm);
		//sh_shortterm.setActionCommand("Show_short");
		//bshort=true;
		
		locpane.add(shortterm.getPanel());
		//sh_longterm = new JButton("Show 5 day forecast");
		locpane.add(longterm.getPanel());
		//sh_longterm.setActionCommand("Show_long");
		//locpane.add(sh_longterm);
		//blong=true;
	
	}
	public String getName(){
		return name;
	}
	public JPanel getPane(){
		return locpane;
	}
	public Currentweather getCurrentweather(){
		return weather;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.equals("Show_short")){
			if (bshort==true){
			locpane.remove(shortterm.getPanel());
			bshort=false;
			sh_shortterm.setText("Show 24 hour forecast");
			}
			else {
				if (blong==true){
					locpane.remove(longterm.getPanel());
					locpane.add(shortterm.getPanel());
					locpane.add(longterm.getPanel());
					sh_shortterm.setText("Hide 24 hour forecast");
				}
			}
		}
		if (event.equals("Show_long")){
			if (blong==true){
				locpane.remove(longterm.getPanel());
				sh_longterm.setText("Show 5 day forecast");
				blong = false;
			}
			else {
				locpane.add(longterm.getPanel());
				sh_longterm.setText("Hide 5 day forecast");
				blong = true;
			}
		}
		locpane.validate();	
	}
	public void updateViewPreferences(WeatherPreferences prefs){
		weather.applyPrefs(prefs);
	}
	
	
}
