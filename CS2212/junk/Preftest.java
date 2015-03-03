import java.util.prefs.Preferences;

public class Preftest {
	 private Preferences prefs;
	 private String display_maxtemp;
	 private String display_mintemp;
	 private String display_windspeed;
	 private String display_winddirection;
	 private String display_temp;
	 private String display_currentconditions;
	 private String display_skydetails;	
	
	public void setprefs() {
		  
		  prefs = Preferences.userRoot().node(this.getClass().getName());
		  display_maxtemp ="what";
		  display_mintemp ="huh";
		  display_windspeed ="qoui";
		  display_winddirection ="eh";
		  display_temp ="wa";
		  display_currentconditions ="shabooze";
		  display_skydetails ="barf";	
		System.out.println("--------------stored------------------------");
		System.out.println(prefs.getBoolean(display_temp, true));
		System.out.println(prefs.getBoolean(display_maxtemp, true));
		System.out.println(prefs.getBoolean(display_mintemp, true));
		System.out.println(prefs.getBoolean(display_windspeed, true));
		System.out.println(prefs.getBoolean(display_winddirection, true));
		System.out.println(prefs.getBoolean(display_currentconditions, true));
		System.out.println(prefs.getBoolean(display_skydetails, true));
	}
	private void changetotrue(){
	    System.out.println("---------------set to true-----------------------");
	  	prefs.putBoolean(display_temp, true);
		prefs.putBoolean(display_maxtemp, true);
		prefs.putBoolean(display_mintemp, true);
		prefs.putBoolean(display_windspeed, true);
		prefs.putBoolean(display_winddirection, true);
		prefs.putBoolean(display_currentconditions, true);
		prefs.putBoolean(display_skydetails, true);
	}
	private void changetofalse(){
		 System.out.println("---------------set to false-----------------------");
		prefs.putBoolean(display_temp, false);
		prefs.putBoolean(display_maxtemp, false);
		prefs.putBoolean(display_mintemp, false);
		prefs.putBoolean(display_windspeed, false);
		prefs.putBoolean(display_winddirection, false);
		prefs.putBoolean(display_currentconditions, false);
		prefs.putBoolean(display_skydetails, false);
	}
	 private void showprefs(){
		 System.out.println("---------------show prefs-----------------------");
		 System.out.println(prefs.get(display_temp, "true"));
		 System.out.println(prefs.get(display_maxtemp, "true"));
		 System.out.println(prefs.get(display_mintemp, "true"));
		 System.out.println(prefs.get(display_windspeed, "true"));
		 System.out.println(prefs.get(display_winddirection, "true"));
		 System.out.println(prefs.get(display_currentconditions, "true"));
		 System.out.println(prefs.get(display_skydetails, "true"));
		 
		 //System.out.println(prefs.getBoolean(display_temp, true));
		 //System.out.println(prefs.getBoolean(display_maxtemp, true));
		 //System.out.println(prefs.getBoolean(display_mintemp, true));
		 //System.out.println(prefs.getBoolean(display_windspeed, true));
		 //System.out.println(prefs.getBoolean(display_winddirection, true));
		 //System.out.println(prefs.getBoolean(display_currentconditions, true));
		 //System.out.println(prefs.getBoolean(display_skydetails, true));
	 }
	 
	public static void main(String[] args) {
		Preftest test = new Preftest();
		test.setprefs();
	    test.showprefs();
	    test.changetotrue();
	    test.showprefs();
	    test.changetofalse();
	    test.showprefs();
	}
}
