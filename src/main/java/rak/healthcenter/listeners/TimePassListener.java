package rak.healthcenter.listeners;

import rak.healthcenter.HealthManager;
import rak.healthcenter.events.TimePassEvent;
import rak.utility.events.EventListener;

public class TimePassListener implements EventListener<TimePassEvent> {
	HealthManager manager;
	
	public TimePassListener(HealthManager manager){
		this.manager = manager;
	}

	@Override
	public void handle(TimePassEvent event) {
		manager.handle(event);		
	}

}
