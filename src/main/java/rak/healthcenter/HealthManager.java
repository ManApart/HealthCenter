package rak.healthcenter;

import rak.healthcenter.events.TimePassEvent;
import rak.healthcenter.events.TimePassListener;

public class HealthManager {
	
	public HealthManager(){
		new TimePassListener(this);
	}

	public void handle(TimePassEvent event) {
		System.out.println("time pass event " + event.getAmountOfTimePassed());
	}

}
