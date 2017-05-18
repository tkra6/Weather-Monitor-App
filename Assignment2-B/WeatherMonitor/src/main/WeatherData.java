/**
 * 
 */
package main;

/**
 * @author Tom, Doug
 * Abstract class that defines the WeatherData objects - currently only stores a string but this could be easily modified
 */
abstract class WeatherData {
	
	private float data;
	private String format;
	private String timestamp;
	
	/**
	 * 
	 * @param values: Should be an array of [value, format, timestamp]
	 */
	public void setData(String[] values) {
		
		// If the value returned is null, something has gone wrong - no data is available.
		if (values[0] == null) {
			
			this.data = 0;
			this.format = "null";
			
		} else {
			
			this.data = Float.parseFloat(values[0]);
			this.format = values[1];
			
		}
		
		this.timestamp = values[2];
		
	}
	
	public float getData() {
		
		return this.data;
		
	}
	
	public String getFormat() {
		
		return this.format;
		
	}
	
	public String getTimestamp() {
		
		return this.timestamp;
		
	}
}
