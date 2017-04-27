package main;

class TemperatureMonitor extends WeatherMonitor {
	
	private String temperature;
	

	public TemperatureMonitor(Weather subject) {
		
		this.setSubject(subject);
	    this.getSubject().attach(this);
	    this.update();
	    
	}

	@Override
	public void update() {
		
		String[] weatherState = ((Weather) this.getSubject()).getState();
		
		this.temperature = weatherState[0];
		
	}
	
	public String getTemperature() {
		
		return this.temperature;
		
	}
	
	public String getLocation() {
		
		return ((Weather) this.getSubject()).getLocation();
		
	}
	
	public String getRenderString() {
		
		return "The temperature at " + this.getLocation() + " is currently " + this.getTemperature();
		
	}

	@Override
	public String[] getRenderContent() {
		// TODO Auto-generated method stub
		return new String[]{"The temperature at " + this.getLocation() + " is currently " + this.getTemperature()};
	}

}
