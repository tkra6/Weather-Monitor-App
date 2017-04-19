/**
 * 
 */
package main;
import java.lang.Exception;

import main.WeatherWebService;

/**
 * @author Tom
 *
 */
class Weather extends Subject {

	private String location;
	private WeatherWebService webService;
	private int state;
	
	public Weather(String location, WeatherWebService webService) throws Exception {
		this.location = location;
		this.webService = webService;
	}

	public String getLocation() {
		return this.location;
	}
	
	public int getState() {
      return state;
	}
}
