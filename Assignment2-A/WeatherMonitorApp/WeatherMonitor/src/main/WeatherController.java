/**
 * 
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * @author Tom, Doug
 *
 */
public class WeatherController {
	/**
	 * @param args
	 */
		
	public static void main(String[] args) throws Exception {
		
		// Create the WeatherWebService object
		WeatherWebService webService = new MelbourneWeather2();
		
		// Create a LocationList object that contains keys for each of the locations (all objects defaulted to null)
		LocationList locationList = constructLocationList(webService);
		
		//TODO find better method to send over locations to GUI (change method in LocationList for returning locations)
		createAndShowUI(webService.getAllLocations());
		
		locationList.addLocation(new Location("Cranbourne", webService));
		locationList.addLocation(new Location("Rhyll", webService));

		Timer timer = new Timer();
		TimerTask updateTask = new TimerTask() {
		    @Override
		    public void run () {
		        locationList.updateAllLocations();
		    }
		};
		// schedule the task to run starting now and then every minutes
		timer.schedule(updateTask, 0l, 1000*60*5);
	}
	
	private static LocationList constructLocationList(WeatherWebService webService) {
		
		String[] locations = webService.getAllLocations();
		return new LocationList(locations);
		
	}
	
	private static void createAndShowUI(String[] locations) {
		JFrame mainFrame = new JFrame("Melbourne Weather Application");
	    mainFrame.setLayout(new GridLayout(1, 1));
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(mainFrame, locations);

	    mainFrame.pack();
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setVisible(true);
    }


    private static void initComponents(final JFrame frame, String[] locations) {

    	// creates panel to group components together
    	JPanel panel = new JPanel();
	    panel.setLayout(new FlowLayout());
	    
	    // creating buttons
	    JButton temperatureButton = new JButton("Temperature");        
	    JButton rainfallButton = new JButton("Rainfall");

	    // creates a selectable list that has weather locations as it's data set
	    final JList list= new JList(locations);
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    // listener for selecting a location --- using list.getSelectedValue() allows to you
	    // obtain the currently selected location as a string (used for buttons further down)
	    list.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        if (!e.getValueIsAdjusting()) {
	        }
	      }
	    });
	    
	    // button for selecting temperature according to the list item that's been selected
	    temperatureButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	//TODO:
	        	// Does location->Monitor exist
	        	// YES -> Do Nothing when button is clicked (clarification needed?)
	        	// NO -> Create Monitor and get temperature to display in locationLabel
	        	 
	        	JFrame f = new JFrame();
	     	   	f.setLayout(new GridLayout(1, 1));
	     	   	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     	    
	     	   	JLabel locationLabel = new JLabel();        
	     	   	locationLabel.setText("Location: " + (String) list.getSelectedValue());
	     	    
	     	    f.add(locationLabel);
	     	    f.pack();
	     	    f.setLocationRelativeTo(null);
	     	    f.setVisible(true);
	     	   
	     	    // TODO:
	        	// include the method: @Override windowClosing 
	        	// Implement this to allow for adding methods to call before the window closes entirely
	     	    // Will allow to detach/destroy the monitor as the frame is closed
	        	 
	         }
	      });
        
        panel.add(new JScrollPane(list));
	    panel.add(temperatureButton, BorderLayout.SOUTH);
	    panel.add(rainfallButton, BorderLayout.SOUTH);
	    frame.add(panel);
    }

}
