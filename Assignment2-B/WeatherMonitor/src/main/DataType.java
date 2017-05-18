package main;

/**
 * 
 * @author Tom
 * An enum to store all of the types of WeatherData that can exist
 * Used to allow locations to check against monitors to see what type of
 * data they need to be storing / updating to minimise web service calls
 *
 */

public enum DataType {
	temperature,
	rainfall;
}