/**
 * 
 */
package main;
import java.lang.Exception;

import main.WeatherWebService;

/**
 * @author Tom, Doug
 * Represents the weather of a single location
 */
class Weather extends Subject {

	private WeatherWebService webService;
	private String location;
	private String temperature;
	private String rainfall;
	
	public Weather(String location, WeatherWebService webService) {
		this.location = location;
		this.webService = webService;
	}

	public String getLocation() {
		return this.location;
	}
	
	public String[] getState() {
		return new String[] {temperature, rainfall};
	}
	
	public void setState() {
		
		this.temperature = this.webService.getTemperatureForLocation(this.location);
		this.rainfall = this.webService.getRainfallForLocation(this.location);
		this.notifyObservers();
		
	}
}
