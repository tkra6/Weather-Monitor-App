/**
 * 
 */
package main;

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
		WeatherWebService webService = new MelbourneWeather2();
		String[] locations = webService.getAllLocations();
		String currLocation;
		String[] currTemperature;
		String[] currRainfall;
		
		
		// Loop over the locations, and display the temperature and rainfall at each
		for (int i = 0; i < 5; i++) {
			String locationString = "";
			currLocation = locations[i];
			currTemperature = webService.getTemperatureForLocation(currLocation);
			currRainfall = webService.getRainfallForLocation(currLocation);
			
			locationString += "Location: " + currLocation;
			locationString += " Temperature: " + currTemperature[1];
			locationString += " Rainfall: " + currRainfall[1];
			
			createWeatherGUI(locationString);
			
		}
		
//		createWeatherGUI(locationString);
		
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
