package rak.healthcenter.listeners;

import rak.healthcenter.HealthManager;
import rak.healthcenter.events.RecoverFromConditionEvent;
import rak.utility.events.EventListener;

public class RecoverFromConditionListener implements EventListener<RecoverFromConditionEvent> {
	HealthManager manager;
	
	public RecoverFromConditionListener(HealthManager manager){
		this.manager = manager;
	}

	@Override
	public void handle(RecoverFromConditionEvent event) {
		manager.handle(event);		
	}

}
