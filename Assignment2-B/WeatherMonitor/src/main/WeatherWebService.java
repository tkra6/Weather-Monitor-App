package main;

/**
 * @desc: Provides an interface for concrete implementations of a weather service
 * @author: Douglas
 */

abstract class WeatherWebService {
	
	public abstract String[] getAllLocations();
	// getRainfallForLocation should return {weatherData, format, timestamp}
	public abstract String[] getRainfallForLocation(String location);
	public abstract String[] getTemperatureForLocation(String location);
	
}
