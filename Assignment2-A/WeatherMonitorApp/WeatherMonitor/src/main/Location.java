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
 * Represents the weather of a single location
 */
class Location extends Subject {
	
	private WeatherWebService webService;
	private String location;
	private HashMap <DataType, WeatherData> weatherData;
	
	public Location(String location, WeatherWebService webService) {
		this.location = location;
		this.webService = webService;
		this.weatherData = new HashMap<DataType, WeatherData>();
	}
	
	@Override
	public void attach(Observer o) {
		// TODO: Find a better way of doing this
		this.observers.add(o);
		
		if (o instanceof TemperatureMonitor || o instanceof TemperatureRainfallMonitor) {
			
			// If the Location object isn't already tracking temperature, add it to the HashMap
			if (!weatherData.containsKey(DataType.temperature)) {
				
				weatherData.put(DataType.temperature, new TemperatureData(null));
				
			}
			
		} 
		
		if (o instanceof RainfallMonitor || o instanceof TemperatureRainfallMonitor) {
			
			// If the Location object isn't already tracking rainfall, add it to the HashMap
			if (!weatherData.containsKey(DataType.rainfall)) {
				
				weatherData.put(DataType.rainfall, new TemperatureData(null));
				
			}
			
		}
		
	}
	
	@Override
	public void detach(Observer o) {
		
		observers.remove(o);
		
		Iterator<Observer> it = observers.iterator();
		
		boolean temperature = false;
		boolean rainfall = false;
		
		Observer ob;
		
		while (it.hasNext()) {
			
			ob = it.next();
			
			if (ob instanceof TemperatureMonitor || ob instanceof TemperatureRainfallMonitor) {
				
				temperature = true;
				
			}
			
			if (ob instanceof RainfallMonitor || ob instanceof TemperatureRainfallMonitor) {
				
				rainfall = true;
				
			}
			
			if (temperature && rainfall) {
				break;
			}
			
		}
		
		if (!temperature) {
			this.weatherData.put(DataType.temperature, null);
		}
		if (!rainfall) {
			this.weatherData.put(DataType.rainfall, null);
		}
		
	}
	
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * @desc returns data value of a specific type
	 * @param type
	 * @return
	 */
	public String getState(DataType type) {
		
		return this.weatherData.get(type).getData();
		
	}
	
	
	/**
	 * @desc iterates through weatherData, referencing own data types against 'enum DataType' and updating
	 * data through web service call
	 * @param datatype
	 */
	public void setState() {
		
		Iterator<Entry<DataType, WeatherData>> it = this.weatherData.entrySet().iterator();
		
		while (it.hasNext()) {
			
			Entry<DataType, WeatherData> entry = it.next();
			
			// If the WeatherData object isn't null
			if (entry.getValue() != null) {
			
				switch (entry.getKey()) {
				case temperature: entry.getValue().setData(this.webService.getTemperatureForLocation(this.location));
				break;
					
				case rainfall:	entry.getValue().setData(this.webService.getRainfallForLocation(this.location));
				break;
				}
			}
		}
	}
	
	public void notifyObservers() {
		this.notifyObservers();
	}
}

