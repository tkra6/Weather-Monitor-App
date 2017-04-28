package main;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

class TemperatureMonitor extends WeatherMonitor {
	
	private JFrame frame;
	private Location location;
	private String temperature;

	public TemperatureMonitor(Location subject) {
		
		this.requiredData = new DataType[]{DataType.temperature};
		this.location = subject;
		this.location.attach(this);
	    this.update();
	    
	}

	@Override
	public void update() {
		
		this.temperature = this.location.getState(DataType.temperature);
		this.displayWindow();
		
	}
	
	public String getTemperature() {
		
		return this.temperature;
		
	}
	
	public String getLocation() {
		
		return this.location.getLocation();
		
	}
	
	public void createWindow() {
		
		this.frame = new JFrame();
		this.frame.setLayout(new GridLayout(0, 1));
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// TODO:
    	// include the method: @Override windowClosing 
    	// Implement this to allow for adding methods to call before the window closes entirely
 	    // Will allow to detach/destroy the monitor as the frame is closed
		
	}
	
	public void displayWindow() {
		
		if (this.frame == null) {
			
			this.createWindow();
			
		} else {
			this.frame.removeAll();
		}
		
		JLabel locationLabel = new JLabel();        
 	   	locationLabel.setText("Location: " + this.getLocation());
 	   	
 	   	JLabel valueLabel = new JLabel();
 	   	valueLabel.setText(this.getRenderContent());
 	    
 	   	this.frame.add(locationLabel);
 	   	this.frame.add(valueLabel);
 	   	this.frame.pack();
 	   	this.frame.setLocationRelativeTo(null);
 	   	this.frame.setVisible(true);
		
	}
	
	private String getRenderContent() {
		
		if (this.temperature == null) {
			
			return ("Temperature data is not currently available.");
			
		} else {
			
			return ("Temperature is currently " + this.temperature + " degrees Celsius.");
			
		}
		
	}

}
