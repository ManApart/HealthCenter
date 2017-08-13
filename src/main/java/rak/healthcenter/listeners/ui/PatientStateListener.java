package rak.healthcenter.listeners.ui;

import rak.healthcenter.controllers.MainMenuController;
import rak.healthcenter.events.PatientStateChangedEvent;
import rak.utility.events.EventListener;

public class PatientStateListener implements EventListener<PatientStateChangedEvent> {
	private MainMenuController mainMenuController;
	
	public PatientStateListener(MainMenuController mainMenuController){
		this.mainMenuController = mainMenuController;
	}

	@Override
	public void handle(PatientStateChangedEvent event) {
		mainMenuController.handle(event);		
	}

}
