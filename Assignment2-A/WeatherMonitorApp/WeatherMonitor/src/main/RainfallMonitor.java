/**
 * 
 */
package main;

/**
 * @author Tom, Doug
 *
 */
class RainfallMonitor extends Observer {
	
	private Location subject;
	private String rainfall;

	public RainfallMonitor(Location subject) {
		
		this.setSubject(subject);
	    this.getSubject().attach(this);
	    this.update();
	    
	}

	@Override
	public void update() {
		
		this.rainfall = this.subject.getState(DataType.rainfall);

	}
	
	public String getRainfall() {
		
		return this.rainfall;
		
	}
	
	public String getLocation() {
		
		return ((Location) this.getSubject()).getLocation();
		
	}
	
	public String getRenderString() {
		
		return "The rainfall at " + this.getLocation() + " is currently " + this.getRainfall();
		
	}

}
