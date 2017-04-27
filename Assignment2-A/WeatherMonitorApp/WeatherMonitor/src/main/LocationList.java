package main;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author douglas
 * @desc: Holds all of the Locations used by the application, and handling adding / 
 * deletion of the locations from this list. Also responsible for telling each location
 * to update itself (should be called by a timed object from main).
 * 
 */

public class LocationList {
	
	private HashMap<String, Location> locationMap;
	
	public LocationList(String[] locationList) {
		
		this.locationMap = new HashMap<String, Location>();
		
		for (String location : locationList) {
			
			this.locationMap.put(location, null);
			
		}
		
	}
	
	public void addLocation(Location location) {
		
		this.locationMap.put(location.getLocation(), location);
		
	}
	
	public void removeLocation(Location location) {
		
		this.locationMap.put(location.getLocation(), null);
		
	}
	
	public Location[] getAllLocations() {
		
		return this.locationMap.values().toArray(new Location[0]);
		
	}
	
	public void updateAllLocations() {
		
		for (Location location : this.locationMap.values()) {
			
			location.setState();
			
		}
		
	}
	
}
