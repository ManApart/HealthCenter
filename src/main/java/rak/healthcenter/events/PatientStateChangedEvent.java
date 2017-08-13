package rak.healthcenter.events;

import rak.healthcenter.model.Patient;
import rak.utility.events.Event;

public class PatientStateChangedEvent implements Event {
	private Patient patient;

	public PatientStateChangedEvent(Patient patient) {
		this.patient = patient;
	}
	
	public Patient getPatient(){
		return patient;
	}

}
