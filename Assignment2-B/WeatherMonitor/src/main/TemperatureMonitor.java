package main;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Tom, Doug
 * Monitor that listens to and displays temperature data for a location
 */

class TemperatureMonitor extends WeatherMonitor {
	
	// References to the display and labels of the monitor
	private JFrame frame;
	private JLabel dataLabel;
	private JLabel locationLabel;
	// References to what the Monitor is storing
	private Location location;
	private WeatherData temperature;

	public TemperatureMonitor(Location subject) {
		
		this.requiredData = new DataType[]{DataType.temperature};
		this.location = subject;
		this.location.attach(this);
	    this.update();
	    
	}
	
	/**
	 * Gets the latest data from its location and then updates the window to display the new data
	 */
	@Override
	public void update() {
		
		this.temperature = this.location.getState(DataType.temperature);

		this.displayWindow();
		
	}
	
	public String getTemperature() {
		
		return String.valueOf(this.temperature.getData());
		
	}
	
	public String getLocation() {
		
		return this.location.getLocation();
		
	}
	
	/**
	 * Handles the initial creation of the window
	 */
	public void createWindow() {
		
		this.frame = new JFrame();
		this.frame.setLayout(new GridLayout(0, 1));
		// This ensures that closing this monitor will not shut down the application
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.locationLabel = new JLabel();        
 	   	this.locationLabel.setText("Location: " + this.getLocation());
 	   	
 	   	this.dataLabel = new JLabel();
 	   	this.dataLabel.setText(this.getRenderContent());
		
		this.frame.setLocationRelativeTo(null);
		this.frame.add(this.locationLabel);
 	   	this.frame.add(this.dataLabel);
 	   	this.frame.pack();
 	   	this.frame.setVisible(true);
		
 	   	WeatherMonitor mon = this;
 	   	// Listens for the monitor to be closed and handles the detaching of it when it happens
 	   	this.frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent ev) {
	    	  location.detach(mon);
	          frame.dispose();
    	  }
 	   	});
		
	}
	
	/**
	 * Creates the window if it isn't already created, otherwise updates it with the current value of getRenderContent()
	 */
	public void displayWindow() {
		
		if (this.frame == null) {
			
			this.createWindow();
			
		} else {
			
			this.dataLabel.setText(this.getRenderContent());
			frame.pack();
			
		}
		
	}
	
	/**
	 * Handles the logic for generating what to display in the GUI
	 * @return The string that will be displayed in the GUI
	 */
	private String getRenderContent() {
		
		if (this.temperature == null || this.temperature.getFormat() == null) {
			
			return ("Temperature data is not currently available.");
			
		} else {
			
			return ("Temperature is currently " + this.temperature.getData() + " degrees Celsius.");
			
		}
		
	}

}
