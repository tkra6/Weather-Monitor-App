/**
 * 
 */
package main;
import java.lang.Exception;

import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.*;

/**
 * @author Tom
 *
 */
class Weather extends Subject {

	private String[] locations;
	private int state;
	
	public Weather() throws Exception{
		final MelbourneWeather2Stub MelbourneWeatherService = new MelbourneWeather2Stub();
		GetLocationsResponse LocationsResponse = MelbourneWeatherService.getLocations();
		locations = LocationsResponse.get_return();
	}

	public String[] getLocations() {
		return locations;
	}
	
	public int getState() {
	      return state;
	   }
}
