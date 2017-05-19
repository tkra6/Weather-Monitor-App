/**
 * 
 */
package main;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import melbourneweathertimelapse.ExceptionException;
import melbourneweathertimelapse.MelbourneWeatherTimeLapseStub;
import melbourneweathertimelapse.MelbourneWeatherTimeLapseStub.*;

/**
 * 
 * @author douglas
 * A concrete implementation of a WeatherWebService, using the MelbourneWeatherTimeLapse SOAP service
 */

public class MelbourneWeatherTimeLapse extends WeatherWebService {
	
	private MelbourneWeatherTimeLapseStub MelbourneWeatherService;
	
	public MelbourneWeatherTimeLapse() {
		
		try {
			MelbourneWeatherService = new MelbourneWeatherTimeLapseStub();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			// Add code for if the weather service cannot be instantiated
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String[] getAllLocations() {
		
		GetLocationsResponse LocationsResponse;
		
		try {
			LocationsResponse = this.MelbourneWeatherService.getLocations();
		} catch (RemoteException | ExceptionException e) {
			e.printStackTrace();
			System.err.println("Error: could not connect to the web service.");
			return null;
		}
		
		String[] locations = LocationsResponse.get_return();
		return locations;
	}

	@Override
	public String[] getRainfallForLocation(String location) {
		/// Get rainfall
		GetWeatherResponse WeatherResponse;
		
		
		
		return new String[] {"ok"};
	}

	@Override
	public String[] getTemperatureForLocation(String location) {
		// Get temperature
		
		return new String[] {"ok"};
	}

}
