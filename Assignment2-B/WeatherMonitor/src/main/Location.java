/**
 * 
 */
package main;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import main.WeatherWebService;

/**
 * @author Tom, Doug
 * Represents a single location in the system
 * Holds a number of WeatherData objects that hold the actual weather data values for the location
 * and dictate what calls the location makes to the web service when updated
 */
class Location extends Subject {
	
	// A reference to the webService object
	private WeatherWebService webService;
	// The physical location this object maps to
	private String location;
	// A HashMap from DataTypes to WeatherDatas that hold the actual weather data for the location
	private HashMap <DataType, WeatherData> weatherData;
	// A reference to the LocationList that this location is stored in, to allow the object to dereference itself
	private LocationList storageLocaiton;
	
	// Counters for how many monitors are listening to each WeatherData, to allow for reduntant data to be removed
	private int tempListeners;
	private int rainListeners;
	
	public Location(String location, WeatherWebService webService, LocationList storageLocation) {
		this.location = location;
		this.webService = webService;
		this.weatherData = new HashMap<DataType, WeatherData>();
		this.storageLocaiton = storageLocation;
		this.tempListeners = 0;
		this.rainListeners = 0;
	}
	
	/**
	 *  Attaches a WeatherMonitor observer to the object, checks what data the observer requires, and updates its counters
	 *  If the observer requires data that isn't currently being stored, initialises and stores the data
	 */
	@Override
	public void attach(Observer o) {
		this.observers.add(o);
		
		// For each DataType that the WeatherMonitor requires
		for (DataType data : ((WeatherMonitor) o).getRequiredData()) {
			
			// If the data isn't currently being stored, initialise and store it, then increment the counter
			switch (data) {
				
				case rainfall:
					if (this.rainListeners == 0) {
						this.weatherData.put(data, new RainfallData(this.webService.getRainfallForLocation(this.location)));
					}
					this.rainListeners++;
					break;
				
				case temperature:
					if (this.tempListeners == 0) {
						this.weatherData.put(data, new TemperatureData(this.webService.getTemperatureForLocation(this.location)));
					}
					this.tempListeners++;
					break;
					
				case temperatureRainfall:
					if (this.tempListeners == 0) {
						this.weatherData.put(data.temperature, new TemperatureData(this.webService.getTemperatureForLocation(this.location)));
					}
					this.tempListeners++;
					if (this.rainListeners == 0) {
						this.weatherData.put(data.rainfall, new RainfallData(this.webService.getRainfallForLocation(this.location)));
					}
					this.rainListeners++;
					break;
			}	
		}
	}
	
	/**
	 * Detaches a WeatherMonitor object from this Location
	 * If the data that the monitor required is no longer needed, dereference it to avoid unecessary webservice calls
	 * If the location has no monitors left, dereference it completely
	 */
	@Override
	public void detach(Observer o) {
		
		observers.remove(o);
		
		// If there are no observers left for this location, dereference it
		if (observers.size() == 0) {
			
			this.storageLocaiton.removeLocation(this);
			
		} else {
			
			// For each datatype the monitor required, decrement the counter and remove if no longer necessary
			for (DataType data : ((WeatherMonitor) o).getRequiredData()) {
				
				
				switch (data) {
					
					case rainfall:
						this.rainListeners--;
						if (this.rainListeners == 0) {
							this.weatherData.remove(data);
						}
						break;
					
					case temperature:
						this.tempListeners--;
						if (this.tempListeners == 0) {
							this.weatherData.remove(data);
						}
						break;
					
				}
					
				
			}
			
		}
		
	}
	/**
	 * Returns the string location of this object
	 * @return A string, the location of this object
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * @desc returns data value of a specific type
	 * @return A string, the value of whatever data was requested
	 */
	public String getState(DataType type) {
		
		if (this.weatherData.get(type) == null) {
			
			System.err.println("Error: tried to access weatherData that does not exist.");
			
		}
		
		return this.weatherData.get(type).getData();
		
	}
	
	
	/**
	 * @desc iterates through weatherData, referencing own data types against 'enum DataType' and updating
	 * data through web service call
	 * Should be called every 5 minutes by the controller
	 */
	public void setState() {
		
		Iterator<Entry<DataType, WeatherData>> it = this.weatherData.entrySet().iterator();
		
		while (it.hasNext()) {
			
			Entry<DataType, WeatherData> entry = it.next();
			
			// If the WeatherData object isn't null - so the data is currently being tracked
			if (entry.getValue() != null) {
				
				// Get the required data from the webService
				switch (entry.getKey()) {
				case temperature: entry.getValue().setData(this.webService.getTemperatureForLocation(this.location));
				break;
					
				case rainfall:	entry.getValue().setData(this.webService.getRainfallForLocation(this.location));
				break;
				}
			}
		}
		// Tell all observers to update themselves
		this.notifyObservers();
	}

}

