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
 * The main controller for the application
 */
public class WeatherController {
	
	// References to a webservice and locationlist
	WeatherWebService webService;
	LocationList locationList;
	
	public WeatherController() {
		this.webService = new MelbourneWeather2();
		// Test the web service
		if (this.webService.getAllLocations() == null) {
			System.err.println("Error: could not connect to the web service.");
			System.exit(1);
		}
		this.locationList = new LocationList(this.webService.getAllLocations());
	}
	
	/**
	 *  Creates a new monitor based on the monitorType string for the specified location
	 */
	private void createMonitor(Location location, String monitorType) {
		
		switch (monitorType) {
		
		case "temperature":
			new TemperatureMonitor(location);
			break;
			
		case "rainfall":
			new RainfallMonitor(location);
			break;
			
		case "temperatureRainfall":
			new TemperatureRainfallMonitor(location);
			break;
		
		}
		
	}
	
	/**
	 * Handles the creation and display of the program's main GUI
	 * @param locations
	 */
	private void createAndShowUI(String[] locations) {
		JFrame mainFrame = new JFrame("Melbourne Weather Application");
	    mainFrame.setLayout(new GridLayout(1, 1));
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(mainFrame, locations);

	    mainFrame.pack();
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setVisible(true);
    }
	
	/**
	 * Initialises the components needed by the GUI for the specified JFrame and array of location strings
	 */
    private void initComponents(final JFrame frame, String[] locations) {

    	// creates panel to group components together
    	JPanel panel = new JPanel();
	    panel.setLayout(new FlowLayout());
	    
	    // creating buttons
	    JButton temperatureButton = new JButton("Temperature");        
	    JButton rainfallButton = new JButton("Rainfall");
	    JButton temperatureRainfallButton = new JButton("TemperatureRainfall");

	    // creates a selectable list that has weather locations as it's data set
	    final JList<String> list= new JList<String>(locations);
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
	        	 
	        	String currLocation = list.getSelectedValue();
	        	
	        	if (locationList.getLocation(currLocation) == null) {
	        		
	        		locationList.addLocation(new Location(currLocation, webService, locationList));
	        		
	        	}
	        	
	        	createMonitor(locationList.getLocation(currLocation), "temperature");
	        	 
	         }
	      });
	    
	    //button for selecting Rainfall according to the list item that's been selected
	    rainfallButton.addActionListener(new ActionListener() {
	    
	    	public void actionPerformed(ActionEvent e) {
	        	 
	        	String currLocation = list.getSelectedValue();
	        	
	        	if (locationList.getLocation(currLocation) == null) {
	        		
	        		locationList.addLocation(new Location(currLocation, webService, locationList));
	        		
	        	}
	        	
	        	createMonitor(locationList.getLocation(currLocation), "rainfall");
	        	 
	         }
	    
	    });
	    
	    // button for selecting temperature and rainfall according to the list item that's been selected
	    temperatureRainfallButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	String currLocation = list.getSelectedValue();
        	
        	if (locationList.getLocation(currLocation) == null) {
        		
        		locationList.addLocation(new Location(currLocation, webService, locationList));
        		
        	}
        	
        	createMonitor(locationList.getLocation(currLocation), "temperatureRainfall");
        	 
         }
      });
        
	    // Add all of the components to the panel
        panel.add(new JScrollPane(list));
	    panel.add(temperatureButton, BorderLayout.SOUTH);
	    panel.add(rainfallButton, BorderLayout.SOUTH);
	    panel.add(temperatureRainfallButton, BorderLayout.SOUTH);
	    frame.add(panel);
    }
    
    public static void main(String[] args) throws Exception {
		
    	System.out.println("Starting up application...");
    	// Create the controller object
		WeatherController controller = new WeatherController();
		System.out.println("Displaying GUI");
		// Create and display the GUI for the application
		controller.createAndShowUI(controller.locationList.getAllLocationsSorted());
		
		// This schedules the updateAllLocations function of the locationList to be run every 5 minutes
		Timer timer = new Timer();
		TimerTask updateTask = new TimerTask() {
		    @Override
		    public void run () {
		        controller.locationList.updateAllLocations();
		    }
		};
		// schedule the task to run starting now and then every 5 minutes
		timer.schedule(updateTask, 0l, 1000*60*5);
	}

}
