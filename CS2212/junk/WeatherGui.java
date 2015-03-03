import gvjava.org.json.JSONException;
import gvjava.org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class WeatherGui extends JFrame implements DocumentListener, ActionListener{
	final static String owmurl = "http://api.openweathermap.org/data/2.5/weather?q=";
	final static String SUBMIT_ACTION = "submit";
	
	private JTextField location_tf;
	private JButton submit_b;
	private JLabel cityprompt_l;
	private JLabel usermsg_l;
	private JTextArea display_ta;
	private JScrollPane jScrollPane1;
	
	public WeatherGui() {
		// TODO Auto-generated constructor stub
		initComponents();
		location_tf.getDocument().addDocumentListener(this);
		InputMap im = location_tf.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = location_tf.getActionMap();
        submit_b.setMnemonic(KeyEvent.VK_D);
        submit_b.addActionListener(this);
        submit_b.setActionCommand("submit");
        im.put(KeyStroke.getKeyStroke("Enter"), SUBMIT_ACTION);
        am.put(SUBMIT_ACTION, new submit_bAction());
	}
	public void initComponents(){
		
		location_tf = new JTextField();
		submit_b = new JButton("submit");
		display_ta = new JTextArea();
		cityprompt_l = new JLabel();
		usermsg_l = new JLabel();
		display_ta.setColumns(10);
        display_ta.setLineWrap(true);
        display_ta.setRows(20);
        display_ta.setWrapStyleWord(false);
        display_ta.setEditable(false);
        jScrollPane1 = new JScrollPane(display_ta);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("WeatherDemo");
		cityprompt_l.setText("Enter a location:");
		usermsg_l.setText("");
		GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        
        //Create a parallel group for the horizontal axis
        ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
         
        //Create a sequential and a parallel groups
        SequentialGroup h1 = layout.createSequentialGroup();
        ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
		
        //Add a scroll pane and a label to the parallel group h2
        h2.addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
        h2.addComponent(usermsg_l, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
        
        //Add a container gap to the sequential group h1
	    h1.addContainerGap();
	    //Create a sequential group h3
	    SequentialGroup h3 = layout.createSequentialGroup();
	    h3.addComponent(cityprompt_l);
	    h3.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
	    h3.addComponent(location_tf, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
	    h3.addComponent(submit_b, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
	    
	    h2.addGroup(h3);
	    h1.addGroup(h2);
	    h1.addContainerGap();
	    
	    hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
	    layout.setHorizontalGroup(hGroup);
	    
	    //Create a parallel group for the vertical axis
	    ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	    
	    //Create a sequential group v1
	    SequentialGroup v1 = layout.createSequentialGroup();
	    
	    //Add a container gap to the sequential group v1
	    v1.addContainerGap();
	    
	    //Create a parallel group v2
	    ParallelGroup v2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
	    v2.addComponent(cityprompt_l);
	    v2.addComponent(submit_b, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
	    v2.addComponent(location_tf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
	    // add the group v2 to the group v1
	    v1.addGroup(v2);
	    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
	    v1.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE);
	    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
	    v1.addComponent(usermsg_l);
	    v1.addContainerGap();
	     
	    //Add the group v1 to the group vGroup
	    vGroup.addGroup(v1);
	    //Create the vertical group
	    layout.setVerticalGroup(vGroup);
	    pack();
	    
	}
	void message(String msg) {
        cityprompt_l.setText(msg);
    }
	public String getQuery() {
		String prompted = location_tf.getText();
		if (prompted.length() <= 0) {
            message("Nothing to search");
            return null;
        }
		String searchtoken = owmurl.concat(prompted);
		return searchtoken;
		
	}
	// takes a url to openweathermap.org and returns a json object  
		public static JSONObject requestJson(String url) {
			InputStream stream;
			JSONObject json;
			try {
				stream = new URL(url).openStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
			      String jsonText = data2string(rd);
			      json = new JSONObject(jsonText);
			      return json;
			} catch (MalformedURLException e) {
						e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// if here uh-oh...
		return null;
			
		}
		// reads all chars from data stream and returns a string 
		private static String data2string(Reader rjson) throws IOException{
			StringBuilder jstring = new StringBuilder();
			int letter;
			try {
				while ((letter = rjson.read()) != -1){
					jstring.append((char)letter);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jstring.toString();
		}
		
	class submit_bAction extends AbstractAction{
		public void actionPerformed(ActionEvent ev) {
			
			JSONObject data = requestJson(getQuery());
            display_ta.setText(data.toString());
            
			//location_tf.setText("");
             
            
        }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
    SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                new WeatherGui().setVisible(true);
            }
        });
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("submit".equals(e.getActionCommand())){
			JSONObject data = requestJson(getQuery());
			display_ta.setText(data.toString());
		}
	}
	
}
