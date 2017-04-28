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
	private LocationList storageLocaiton;
	
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
	
	@Override
	public void attach(Observer o) {
		// TODO: Find a better way of doing this
		this.observers.add(o);
		
		for (DataType data : ((WeatherMonitor) o).getRequiredData()) {
			
				
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
				
			}
				
			
		}
		
		System.out.println("Attached observer. " + this.rainListeners + " rainListeners and " + this.tempListeners + " tempListeners.");
		
	}
	
	@Override
	public void detach(Observer o) {
		
		observers.remove(o);
		
		if (observers.size() == 0) {
			
			this.storageLocaiton.removeLocation(this);
			System.out.println("Destroyed location");
			
		} else {
			
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
			
			System.out.println("Detatched observer. " + this.rainListeners + " rainListeners and " + this.tempListeners + " tempListeners.");
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

