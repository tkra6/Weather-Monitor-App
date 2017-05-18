package main;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import melbourneweather2.ExceptionException;
import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.*;

/**
 * 
 * @author douglas
 * A concrete implementation of a WeatherWebService, using the MelbourneWeather2 SOAP service
 */

public class MelbourneWeather2 extends WeatherWebService {
	
	private MelbourneWeather2Stub MelbourneWeatherService;
	
	public MelbourneWeather2() {
		
		try {
			MelbourneWeatherService = new MelbourneWeather2Stub();
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
		GetRainfall RainfallRequest = new GetRainfall();
		RainfallRequest.setLocation(location);
		GetRainfallResponse RainfallResponse;
		try {
			RainfallResponse = this.MelbourneWeatherService.getRainfall(RainfallRequest);
		} catch (RemoteException | ExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		String[] rainfall = RainfallResponse.get_return();
		return new String[] {rainfall[1], "mm", rainfall[0]};
	}

	@Override
	public String[] getTemperatureForLocation(String location) {
		GetTemperature TemperatureRequest = new GetTemperature();
		TemperatureRequest.setLocation(location);
		GetTemperatureResponse TemperatureResponse;
		try {
			TemperatureResponse = this.MelbourneWeatherService.getTemperature(TemperatureRequest);
		} catch (RemoteException | ExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		String[] temperature = TemperatureResponse.get_return();
		return new String[] {temperature[1], "C", temperature[0]};
	}

}
