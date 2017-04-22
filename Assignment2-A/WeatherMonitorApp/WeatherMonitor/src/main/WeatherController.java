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
		
		
		// Loop over the locations, and display the temperature at each in a separate window
		for (int i = 0; i < 5; i++) {
			
			Weather weather = new Weather(locations[i], webService);
			weather.setState();
			TemperatureMonitor tempMonitor = new TemperatureMonitor(weather);
			
			createWeatherGUI(tempMonitor.getRenderString());
			
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
