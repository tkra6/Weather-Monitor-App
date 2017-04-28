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
	
	WeatherWebService webService;
	LocationList locationList;
	
	public WeatherController() {
		this.webService = new MelbourneWeather2();
		this.locationList = new LocationList(this.webService.getAllLocations());
	}
	
	private void createMonitor(Location location, String monitorType) {
		
		System.out.println("Creating monitor for " + location.getLocation() + " of type " + monitorType);
		
		switch (monitorType) {
		
		case "temperature":
			new TemperatureMonitor(location);
			break;
			
		case "rainfall":
			new RainfallMonitor(location);
			break;
		
		}
		
	}
	
	private void createAndShowUI(String[] locations) {
		JFrame mainFrame = new JFrame("Melbourne Weather Application");
	    mainFrame.setLayout(new GridLayout(1, 1));
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(mainFrame, locations);

	    mainFrame.pack();
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setVisible(true);
    }
	
    private void initComponents(final JFrame frame, String[] locations) {

    	// creates panel to group components together
    	JPanel panel = new JPanel();
	    panel.setLayout(new FlowLayout());
	    
	    // creating buttons
	    JButton temperatureButton = new JButton("Temperature");        
	    JButton rainfallButton = new JButton("Rainfall");

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
        
        panel.add(new JScrollPane(list));
	    panel.add(temperatureButton, BorderLayout.SOUTH);
	    panel.add(rainfallButton, BorderLayout.SOUTH);
	    frame.add(panel);
    }
    
    public static void main(String[] args) throws Exception {
		
		WeatherController controller = new WeatherController();
		
		//TODO find better method to send over locations to GUI (change method in LocationList for returning locations)
		controller.createAndShowUI(controller.webService.getAllLocations());

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
