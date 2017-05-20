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
	
	public float convertToCelsius(float value, String format) {
		
		if (format == "K") {
			value = (float) (value - 273.15);
		} else if (format != "C") {
			System.out.println("Unknown format.");
			return value;
		}
		
		return (float) (Math.round(value*100.0)/100.0);
		
	}
	
	// Converts value to MM, if it is not already in MM, and rounds to 2 decimal places
	public float convertToMM(float value, String format) {
		
		if (format == "cm") {
			value = (float) (value * 10);
		} else if (format != "mm") {
			System.out.println("Unknown format.");
			return value;
		}
		
		return (float) (Math.round(value*100.0)/100.0);
		
	}
	
	/**
	 * Method to create and display the window for a weather monitor
	 * Also contains all event handlers needed for window closing
	 */
	
	public abstract void createWindow();
	public abstract void displayWindow();
	
}
