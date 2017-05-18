/**
 * 
 */
package main;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Tom, Doug
 * Monitor that listens to and displays Rainfall for a location
 */
class RainfallMonitor extends WeatherMonitor {
	
	// References to the display and labels of the monitor
	private JFrame frame;
	private JLabel dataLabel;
	private JLabel locationLabel;
	// References to what the Monitor is storing
	private Location location;
	private WeatherData rainfall;

	public RainfallMonitor(Location subject) {
		
		this.requiredData = new DataType[]{DataType.rainfall};
		this.location = subject;
		this.location.attach(this);
	    this.update();
	    
	}
	
	/**
	 * Gets the latest data from its location and then updates the window to display the new data
	 */
	@Override
	public void update() {
		
		this.rainfall = this.location.getState(DataType.rainfall);
		
		this.displayWindow();

	}
	
	public String getRainfall() {
		
		return String.valueOf(this.rainfall);
		
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
		
		if (this.rainfall == null || this.rainfall.getFormat() == null) {
			
			return ("Rainfall data is not currently available.");
			
		} else {
			
			return ("Rainfall is currently " + this.rainfall.getData() + " millilitres.");
			
		}
		
	}

}
