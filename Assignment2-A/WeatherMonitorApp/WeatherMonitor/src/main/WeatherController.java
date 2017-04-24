/**
 * 
 */
package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.SwingUtilities;

/**
 * @author Tom
 *
 */
public class WeatherController {
	/**
	 * @param args
	 */
	
	
	
	public static void main(String[] args) throws Exception {
		// Create the WeatherWebService object
		WeatherWebService webService = new MelbourneWeather2();
		// Create a HashMap that maps locations to weather objects and populate it with all existing locations
		HashMap<String, Weather> locationWeather = createWeatherHashMap(webService);
		
		Iterator<String> it = locationWeather.keySet().iterator();
		
		while (it.hasNext()) {
			
			String location = it.next();
			System.out.println(location);
			
		}
		
		System.out.println("Input a location name: ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		
		if (locationWeather.get(input) != null) {
			
			TemperatureMonitor monitor = new TemperatureMonitor(locationWeather.get(input));
			createWeatherGUI(monitor.getRenderString());
			
		} else {
			
			System.out.println(input + " is not a valid location.");
			
		}
		
	}
	
	private static HashMap<String, Weather> createWeatherHashMap(WeatherWebService webService) {
		
		String[] locations = webService.getAllLocations();
		HashMap<String, Weather> locationWeather = new HashMap<String, Weather>();
		
		for (String location : locations) {
			
			Weather weather = new Weather(location, webService);
			weather.setState();
			locationWeather.put(location, weather);
			
		}
		
		return locationWeather;
		
	}
	
	// creates initial GUIframe with content in it
	static void createWeatherGUI(String content) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIController(content);
            }
        });	
	}
	
	//creates new monitor to attach to subject
	//index: used as identifier in GUI and index in Subject
	void createMonitor(Subject subject, int index) {
		//new Observer - need way to determine what type of observer to create
	}
	
	

}
