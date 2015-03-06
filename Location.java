package team7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;



public class Location implements ActionListener{
	TransparentPanel locpane;
	String name;
	Currentweather weather;
	Forecast24Hour shortterm;
	Forecast5Day longterm;
	JButton sh_shortterm, sh_longterm;
	boolean bshort,blong;
	
	public Location(WeatherData data, WeatherPreferences prefs) {
		locpane = new TransparentPanel();
		name = data.getCityName();
		JLabel locname_l = new JLabel(name);
		
		locpane.add(locname_l);
		
		weather = new Currentweather(data, prefs);
		shortterm = new Forecast24Hour(data,prefs);
		longterm = new Forecast5Day(data,prefs);
		
		locpane.add(weather.getPanel());
		sh_shortterm = new JButton("");
		locpane.add(sh_shortterm);
		sh_shortterm.setActionCommand("Hide_short");
		bshort=true;
		
		locpane.add(shortterm.getPanel());
		sh_longterm = new JButton("");
		locpane.add(longterm.getPanel());
		sh_longterm.setActionCommand("Hide_long");
		locpane.add(sh_longterm);
		blong=true;
	
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.equals("Hide_short")){
			if (bshort==true){
			locpane.remove(sh_shortterm);
			}
			else {
				if (blong==true){
					locpane.remove(sh_longterm);
					locpane.remove(longterm.getPanel());
					locpane.add(sh_shortterm);
					locpane.add(shortterm.getPanel());
					locpane.add(sh_longterm);
					locpane.add(longterm.getPanel());
				}
			}
		}
		if (event.equals("Hide_long")){
			locpane.remove(sh_longterm);
		}
	}
	
}
