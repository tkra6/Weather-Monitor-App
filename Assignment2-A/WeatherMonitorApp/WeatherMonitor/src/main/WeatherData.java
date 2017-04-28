/**
 * 
 */
package main;

/**
 * @author Tom, Doug
 * Abstract class that defines the WeatherData objects - currently only stores a string but this could be easily modified
 */
abstract class WeatherData {
	
	private String data;

	public void setData(String data) {
		
		this.data = data;
		
	}
	
	public String getData() {
		
		return this.data;
		
	}
}
