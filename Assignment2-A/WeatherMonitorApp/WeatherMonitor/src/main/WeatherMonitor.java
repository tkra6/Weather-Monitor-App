package main;

import javax.swing.SwingUtilities;

/**
 * @desc ***************************NO LONGER USED*************************
 * @author Tom, Doug
 *
 */
public abstract class WeatherMonitor extends Observer {
	
	MonitorWindow window;
	
	@Override
	public abstract void update();
	
	public String getLocation() {
			
		return ((Location) this.getSubject()).getLocation();
		
	}
	
	public abstract String[] getRenderContent();
	
	/**
	 * Method to create and display the window for a weather monitor
	 * Also contains all event handlers needed for window closing
	 */
	public void createAndDisplayWindow() {
		
		this.window = new MonitorWindow(this.getLocation(), this.getRenderContent());
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Figure out how to access this.window
            }
        });	
		
		
	}
	
}
