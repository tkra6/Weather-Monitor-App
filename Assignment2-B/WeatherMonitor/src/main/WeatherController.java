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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	
//    HashMap<String, LocationList> locationListCollection;
	
	// References to a webservice and locationlist (melbourne weather 2)
	WeatherWebService MW2Service;
	LocationList MW2LocationList;

	// References to a webservice and locationlist (melbourne time lapse)
	WeatherWebService MWTimeLapseService;
	LocationList MWTimeLapseLocationList;
	
	public WeatherController() {
		this.MW2Service = new MelbourneWeather2();
		this.MWTimeLapseService = new MelbourneWeatherTimeLapse();
		
		// Test the web service
		if (this.MW2Service.getAllLocations() == null) {
			System.err.println("Error: could not connect to the web service.");
			System.exit(1);
		}
		if (this.MWTimeLapseService.getAllLocations() == null) {
			System.err.println("Error: could not connect to the web service.");
			System.exit(1);
		}
		
		this.MW2LocationList = new LocationList(this.MW2Service.getAllLocations());
		this.MWTimeLapseLocationList = new LocationList(this.MWTimeLapseService.getAllLocations());
//		locationListCollection.put("MW2Locations", this.MW2LocationList);
//		locationListCollection.put("MWTimeLapseLocations", this.MWTimeLapseLocationList);
		


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
			new GraphicalTemperatureRainfallMonitor(location);
			break;
			
		case "graphTemperatureRainfall":
			new GraphicalTemperatureRainfallMonitor(location);
			break;
		
		}
		
	}
	
	/**
	 * Handles the creation and display of the program's main GUI
	 * @param location
	 */
	private void createAndShowUI() {
		JFrame mainFrame = new JFrame("Melbourne Weather Application");
	    mainFrame.setLayout(new GridLayout(1, 1));
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(mainFrame);

	    mainFrame.pack();
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setVisible(true);
    }
	
	/**
	 * Initialises the components needed by the GUI for the specified JFrame and array of location strings
	 */
    private void initComponents(final JFrame frame) {
    	
    	// Variable to hold the location list
    	String[] locations;
    	
    	// creates panel to group components together
    	JPanel baseLayoutPanel = new JPanel();
    	baseLayoutPanel.setLayout(new GridLayout(2, 1));
    	
    	JPanel MW2Panel = new JPanel();
	    MW2Panel.setLayout(new FlowLayout());
	    
	    JPanel MWTimeLapsePanel = new JPanel();
	    MWTimeLapsePanel.setLayout(new FlowLayout());
	    
	    // creating buttons
	    JButton MW2TempButton = new JButton("Temperature");
	    JButton MWTimeLapseTempButton = new JButton("Temperature"); 
	    JButton MW2RainfallButton = new JButton("Rainfall");
	    JButton MWTimeLapseRainfallButton = new JButton("Rainfall");
	    JButton MW2TempRainButton = new JButton("Temperature Rainfall");
	    JButton MWTimeLapseTempRainButton = new JButton("Temperature Rainfall");
	    JButton MWTimeLapseGraphTempButton = new JButton("Temperature Graph"); 
	    JButton MWTimeLapseGraphRainfallButton = new JButton("Rainfall Graph");
	    JButton MWTimeLapseGraphTempRainButton = new JButton("Temperature Rainfall Graph");

	    
	    // Populate the list for the MW2 Location List
	    locations = this.MW2LocationList.getAllLocationsSorted();
	    
	    // creates a selectable list that has weather locations as it's data set
	    final JList<String> MW2List = new JList<String>(locations);
	    MW2List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    // Populate the list for the MWTimeLapse Location List
	    locations = this.MWTimeLapseLocationList.getAllLocationsSorted();
	    
	    final JList<String> MWTimeLapseList = new JList<String>(locations);
	    MWTimeLapseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    // listener for selecting a location --- using list.getSelectedValue() allows to you
	    // obtain the currently selected location as a string (used for buttons further down)
	    MW2List.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        if (!e.getValueIsAdjusting()) {
	        }
	      }
	    });
	    
	    // button for selecting temperature according to the list item that's been selected
	    MW2TempButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	String currLocation = MW2List.getSelectedValue();
	        	
	        	if (currLocation == null) {
	        		
	        		// No list value has been selected - do nothing
	        		return;
	        		
	        	}
	        	
	        	if (MW2LocationList.getLocation(currLocation) == null) {
	        		
	        		MW2LocationList.addLocation(new Location(currLocation, MW2Service, MW2LocationList));
	        		
	        	}
	        	
	        	createMonitor(MW2LocationList.getLocation(currLocation), "temperature");
	        	 
	         }
	      });
	    
	    //button for selecting Rainfall according to the list item that's been selected
	    MW2RainfallButton.addActionListener(new ActionListener() {
	    
	    	public void actionPerformed(ActionEvent e) {
	        	 
	        	String currLocation = MW2List.getSelectedValue();
	        	
	        	if (currLocation == null) {
	        		
	        		// No list value has been selected - do nothing
	        		return;
	        		
	        	}
	        	
	        	if (MW2LocationList.getLocation(currLocation) == null) {
	        		
	        		MW2LocationList.addLocation(new Location(currLocation, MW2Service, MW2LocationList));
	        		
	        	}
	        	
	        	createMonitor(MW2LocationList.getLocation(currLocation), "rainfall");
	        	 
	         }
	    
	    });
	    
	    // button for selecting temperature and rainfall according to the list item that's been selected
	    MW2TempRainButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	String currLocation = MW2List.getSelectedValue();
        	
        	if (currLocation == null) {
        		
        		// No list value has been selected - do nothing
        		return;
        		
        	}
        	
        	if (MW2LocationList.getLocation(currLocation) == null) {
        		
        		MW2LocationList.addLocation(new Location(currLocation, MW2Service, MW2LocationList));
        		
        	}
        	
        	createMonitor(MW2LocationList.getLocation(currLocation), "temperatureRainfall");
        	 
         }
      });
	    
	 // listener for selecting a location --- using list.getSelectedValue() allows to you
	    // obtain the currently selected location as a string (used for buttons further down)
	    MWTimeLapseList.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        if (!e.getValueIsAdjusting()) {
	        }
	      }
	    });
	    
	    // button for selecting temperature according to the list item that's been selected
	    MWTimeLapseTempButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	String currLocation = MWTimeLapseList.getSelectedValue();
	        	
	        	if (currLocation == null) {
	        		
	        		// No list value has been selected - do nothing
	        		return;
	        		
	        	}
	        	
	        	if (MWTimeLapseLocationList.getLocation(currLocation) == null) {
	        		
	        		MWTimeLapseLocationList.addLocation(new Location(currLocation, MWTimeLapseService, MWTimeLapseLocationList));
	        		
	        	}
	        	
	        	createMonitor(MWTimeLapseLocationList.getLocation(currLocation), "temperature");
	        	 
	         }
	      });
	    
	    //button for selecting Rainfall according to the list item that's been selected
	    MWTimeLapseRainfallButton.addActionListener(new ActionListener() {
	    
	    	public void actionPerformed(ActionEvent e) {
	        	 
	        	String currLocation = MWTimeLapseList.getSelectedValue();
	        	
	        	if (currLocation == null) {
	        		
	        		// No list value has been selected - do nothing
	        		return;
	        		
	        	}
	        	
	        	if (MWTimeLapseLocationList.getLocation(currLocation) == null) {
	        		
	        		MWTimeLapseLocationList.addLocation(new Location(currLocation, MWTimeLapseService, MWTimeLapseLocationList));
	        		
	        	}
	        	
	        	createMonitor(MWTimeLapseLocationList.getLocation(currLocation), "rainfall");
	        	 
	         }
	    
	    });
	    
	    // button for selecting temperature and rainfall according to the list item that's been selected
	    MWTimeLapseTempRainButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	String currLocation = MWTimeLapseList.getSelectedValue();
        	
        	if (currLocation == null) {
        		
        		// No list value has been selected - do nothing
        		return;
        		
        	}
        	
        	if (MWTimeLapseLocationList.getLocation(currLocation) == null) {
        		
        		MWTimeLapseLocationList.addLocation(new Location(currLocation, MWTimeLapseService, MWTimeLapseLocationList));
        		
        	}
        	
        	createMonitor(MWTimeLapseLocationList.getLocation(currLocation), "temperatureRainfall");
        	 
         }
      });
	    
	 // button for selecting temperature and rainfall graphs according to the list item that's been selected
	    MWTimeLapseGraphTempRainButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	String currLocation = MWTimeLapseList.getSelectedValue();
        	
        	if (currLocation == null) {
        		
        		// No list value has been selected - do nothing
        		return;
        		
        	}
        	
        	if (MWTimeLapseLocationList.getLocation(currLocation) == null) {
        		
        		MWTimeLapseLocationList.addLocation(new Location(currLocation, MWTimeLapseService, MWTimeLapseLocationList));
        		
        	}
        	
        	createMonitor(MWTimeLapseLocationList.getLocation(currLocation), "graphTemperatureRainfall");
        	 
         }
      });
        
	    // Add all of the components to the panel
        MW2Panel.add(new JScrollPane(MW2List));
        MWTimeLapsePanel.add(new JScrollPane(MWTimeLapseList));
	    MW2Panel.add(MW2TempButton, BorderLayout.SOUTH);
	    MWTimeLapsePanel.add(MWTimeLapseTempButton, BorderLayout.SOUTH);
	    MW2Panel.add(MW2RainfallButton, BorderLayout.SOUTH);
	    MWTimeLapsePanel.add(MWTimeLapseRainfallButton, BorderLayout.SOUTH);
	    MW2Panel.add(MW2TempRainButton, BorderLayout.SOUTH);
	    MWTimeLapsePanel.add(MWTimeLapseTempRainButton, BorderLayout.SOUTH);
	    MWTimeLapsePanel.add(MWTimeLapseGraphTempButton, BorderLayout.SOUTH);
	    MWTimeLapsePanel.add(MWTimeLapseGraphRainfallButton, BorderLayout.SOUTH);
	    MWTimeLapsePanel.add(MWTimeLapseGraphTempRainButton, BorderLayout.SOUTH);
	    baseLayoutPanel.add(MW2Panel);
	    baseLayoutPanel.add(MWTimeLapsePanel);
	    frame.add(baseLayoutPanel);
	    
    }
    
    public static void main(String[] args) throws Exception {
		
    	System.out.println("Starting up application...");
    	// Create the controller object
		WeatherController controller = new WeatherController();
		System.out.println("Displaying GUI");
		// Create and display the GUI for the application
		controller.createAndShowUI();
		
		// This schedules the updateAllLocations function of the locationList to be run every 5 minutes
		Timer timer = new Timer();
		TimerTask MW2updateTask = new TimerTask() {
		    @Override
		    public void run () {
		        controller.MW2LocationList.updateAllLocations();
		    }
		};
		TimerTask MWTimeLapseupdateTask = new TimerTask() {
		    @Override
		    public void run () {
		        controller.MWTimeLapseLocationList.updateAllLocations();
		    }
		};
		// schedule the task to run starting now and then every 5 minutes
		timer.schedule(MW2updateTask, 0l, 1000*60*5);
		// schedule the task to run starting now and then every 20 seconds
		timer.schedule(MWTimeLapseupdateTask, 0l, 1000*20);
	}

}
