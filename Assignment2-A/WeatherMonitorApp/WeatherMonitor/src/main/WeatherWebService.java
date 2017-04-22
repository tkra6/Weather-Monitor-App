package main;

/*
 * abstract class WeatherWebService
 * Provides an interface for concrete implementations of a weather service
 * @author: Douglas Rintoul
 */

abstract class WeatherWebService {
	
	public abstract String[] getAllLocations();
	public abstract String getRainfallForLocation(String location);
	public abstract String getTemperatureForLocation(String location);
	
}
