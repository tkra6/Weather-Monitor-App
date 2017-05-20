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
		
		// Get the current weather data
		String[] Weather = this.getWeatherForLocation(location);
		
		// Rainfall data is at Weather[2]
		return new String[] {Weather[2], "cm", Weather[0]};
	}

	@Override
	public String[] getTemperatureForLocation(String location) {
		
		// Get the current weather data
		String[] Weather = this.getWeatherForLocation(location);
		
		// Temperature data is at Weather[1]
		return new String[] {Weather[1], "cm", Weather[0]};

	}
	
	private String[] getWeatherForLocation(String location) {
		
		// Create a request for the web service
		GetWeather WeatherRequest = new GetWeather();
		// Get rainfall
		WeatherRequest.setLocation(location);
		GetWeatherResponse WeatherResponse;
		try {
			WeatherResponse = MelbourneWeatherService.getWeather(WeatherRequest);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		String[] Weather = WeatherResponse.get_return();
		return Weather;
		
	}
	
	public static void main(String[] args) {
		
		MelbourneWeatherTimeLapse webService = new MelbourneWeatherTimeLapse();
		
		String[] locationList = webService.getAllLocations();
		String[] weather;
		
		for (String location : locationList) {
			
			System.out.println(location);
			weather = webService.getRainfallForLocation(location);
			System.out.println("Rainfall: " + weather[0] + "; format " + weather[1] + "; timestamp " + weather[2]);
			
		}
		
	}

}
