package main;

/**
 * @desc: Provides an interface for concrete implementations of a weather service
 * @author: Douglas
 */

abstract class WeatherWebService {
	
	public abstract String[] getAllLocations();
	public abstract String getRainfallForLocation(String location);
	public abstract String getTemperatureForLocation(String location);
	
}
