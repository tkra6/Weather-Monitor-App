package main;

class TemperatureMonitor extends Observer {
	
	void temperatureMonitor(Subject subject){
		this.setSubject(subject);
	    this.getSubject().attach(this);
	}

	@Override
	public void update() {
		// getTemperature
		// returnTemperature
		
	}

}
