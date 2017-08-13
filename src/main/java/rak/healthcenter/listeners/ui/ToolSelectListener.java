package rak.healthcenter.listeners.ui;

import rak.healthcenter.controllers.MainMenuController;
import rak.healthcenter.events.ToolSelectEvent;
import rak.utility.events.EventListener;

public class ToolSelectListener implements EventListener<ToolSelectEvent> {
	private MainMenuController mainMenuController;
	
	public ToolSelectListener(MainMenuController mainMenuController){
		this.mainMenuController = mainMenuController;
	}

	@Override
	public void handle(ToolSelectEvent event) {
		mainMenuController.handle(event);		
	}

}
