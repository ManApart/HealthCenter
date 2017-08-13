package rak.healthcenter.listeners;

import rak.healthcenter.events.ToolSelectEvent;
import rak.healthcenter.model.HealthStation;
import rak.utility.events.EventListener;

public class ToolSelectListener implements EventListener<ToolSelectEvent> {
	private HealthStation healthStation;
	
	public ToolSelectListener(HealthStation healthStation){
		this.healthStation = healthStation;
	}

	@Override
	public void handle(ToolSelectEvent event) {
		healthStation.setCurrentTool(event.getTool());		
	}

}
