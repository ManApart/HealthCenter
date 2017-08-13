package rak.healthcenter.events;

import rak.healthcenter.HealthManager;
import rak.utility.events.EventDirector;
import rak.utility.events.EventListener;

public class TimePassListener implements EventListener<TimePassEvent> {
	HealthManager manager;
	
	public TimePassListener(HealthManager manager){
		this.manager = manager;
		EventDirector.registerListener(this);
	}

	@Override
	public void handle(TimePassEvent event) {
		manager.handle(event);		
	}

}
