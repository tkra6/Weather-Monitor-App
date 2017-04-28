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
 *
 */
class RainfallMonitor extends WeatherMonitor {
	
	private JFrame frame;
	private JLabel dataLabel;
	private JLabel locationLabel;
	private Location location;
	private String rainfall;

	public RainfallMonitor(Location subject) {
		
		this.requiredData = new DataType[]{DataType.rainfall};
		this.location = subject;
		this.location.attach(this);
	    this.update();
	    
	}

	@Override
	public void update() {
		
		this.rainfall = this.location.getState(DataType.rainfall);
		this.displayWindow();

	}
	
	public String getRainfall() {
		
		return this.rainfall;
		
	}
	
	public String getLocation() {
		
		return this.location.getLocation();
		
	}
	
	public void createWindow() {
		
		this.frame = new JFrame();
		this.frame.setLayout(new GridLayout(0, 1));
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
	   	this.frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent ev) {
	    	  location.detach(mon);
	          frame.dispose();
	      }
	   	});
		
	}
	
	public void displayWindow() {
		
		if (this.frame == null) {
			
			this.createWindow();
			
		} else {
			
			this.dataLabel.setText(this.getRenderContent());
			frame.pack();
			
		}
		
	}
	
	private String getRenderContent() {
		
		if (this.rainfall == null) {
			
			return ("Rainfall data is not currently available.");
			
		} else {
			
			return ("Rainfall is currently " + this.rainfall + " millilitres.");
			
		}
		
	}

}
