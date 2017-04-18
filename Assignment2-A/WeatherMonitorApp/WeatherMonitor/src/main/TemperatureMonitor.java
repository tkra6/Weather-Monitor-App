package main;

class TemperatureMonitor extends Observer {
	
	void temperatureMonitor(Subject subject){
		this.subject = subject;
	    this.subject.attach(this);
	}

	@Override
	public void update() {
		// getTemperature
		// returnTemperature
		
	}

}
