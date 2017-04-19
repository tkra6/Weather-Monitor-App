package main;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import melbourneweather2.ExceptionException;
import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.*;

public class MelbourneWeather2 extends WeatherWebService {

	@Override
	public String[] getAllLocations() {
		MelbourneWeather2Stub MelbourneWeatherService;
		try {
			MelbourneWeatherService = new MelbourneWeather2Stub();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			// Add code for if the weather service cannot be instantiated
			e.printStackTrace();
			return null;
		}
		
		GetLocationsResponse LocationsResponse;
		
		try {
			LocationsResponse = MelbourneWeatherService.getLocations();
		} catch (RemoteException | ExceptionException e) {
			// TODO Auto-generated catch block
			// Add code for handling if the location cannot be found
			e.printStackTrace();
			return null;
		}
		
		String[] locations = LocationsResponse.get_return();
		return locations;
	}

	@Override
	public String[] getRainfallForLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTemperatureForLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

}
