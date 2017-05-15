package main;

/**
 * @desc 
 * @author Tom, Doug
 * Provides an abstract interface for any type of WeatherMonitor - allows Location objects to see what data is needed by each monitor
 */
public abstract class WeatherMonitor extends Observer {
	
	protected DataType[] requiredData;
	
	@Override
	public abstract void update();
	
	public DataType[] getRequiredData() {
		
		return this.requiredData;
		
	}
	
	/**
	 * Method to create and display the window for a weather monitor
	 * Also contains all event handlers needed for window closing
	 */
	
	public abstract void createWindow();
	public abstract void displayWindow();
	
}
