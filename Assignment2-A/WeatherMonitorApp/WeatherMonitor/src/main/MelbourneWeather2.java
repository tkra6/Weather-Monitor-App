package main;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import melbourneweather2.ExceptionException;
import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.*;

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
		return rainfall;
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
		return temperature;
	}

}
