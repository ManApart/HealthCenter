package rak.healthcenter.listeners;

import rak.healthcenter.HealthManager;
import rak.healthcenter.events.ContractConditionEvent;
import rak.utility.events.EventListener;

public class ContractConditionListener implements EventListener<ContractConditionEvent> {
	HealthManager manager;
	
	public ContractConditionListener(HealthManager manager){
		this.manager = manager;
	}

	@Override
	public void handle(ContractConditionEvent event) {
		manager.handle(event);		
	}

}
