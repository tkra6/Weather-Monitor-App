package main;

import java.util.Arrays;
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
	// Implementation of actual storage is a HashMap
	private HashMap<String, Location> locationMap;
	
	// Creates a null location entry for each string in locationList on creation
	public LocationList(String[] locationList) {
		
		this.locationMap = new HashMap<String, Location>();
		
		for (String location : locationList) {
			
			this.locationMap.put(location, null);
			
		}
		
	}
	
	/**
	 *  Adds a location object to the LocationList
	 */
	public void addLocation(Location location) {
		
		this.locationMap.put(location.getLocation(), location);
		
	}
	
	/**
	 *  Removes a location object from the LocationList - this should destroy it as there should be no other references
	 */
	public void removeLocation(Location location) {
		
		this.locationMap.put(location.getLocation(), null);
		
	}
	
	/**
	 * Returns whatever is stored in the locationMap at the key 'location'
	 * @return: Either a Location or null
	 */
	public Location getLocation(String location) {
		
		return this.locationMap.get(location);
		
	}
	
	/**
	 * Returns all of the keys in the HashMap, sorted alphabetically
	 * @return: A sorted array of strings
	 */
	public String[] getAllLocationsSorted() {
		
		String[] locationStrings = this.locationMap.keySet().toArray(new String[0]);
		Arrays.sort(locationStrings);
		return locationStrings;
		
	}
	
	/**
	 * Loops through all Location objects in the LocationList and calls setState() on them
	 */
	public void updateAllLocations() {
		
		for (Location location : this.locationMap.values()) {
			
			if (location != null) {
				System.out.println("Updating " + location.getLocation());
				location.setState();
			}
			
		}
		
	}
	
}
