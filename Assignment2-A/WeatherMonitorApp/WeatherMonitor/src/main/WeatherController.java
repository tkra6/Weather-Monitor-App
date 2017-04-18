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
		Weather weather = new Weather();
		createWeatherGUI(weather.getLocations());
		
	}
	
	// creates initial GUIframe with weather locations
	static void createWeatherGUI(String[] locations) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIController(locations);
            }
        });	
	}
	
	//creates new monitor to attach to subject
	//index: used as identifier in GUI and index in Subject
	void createMonitor(Subject subject, int index) {
		//new Observer - need way to determine what type of observer to create
	}
	
	

}
