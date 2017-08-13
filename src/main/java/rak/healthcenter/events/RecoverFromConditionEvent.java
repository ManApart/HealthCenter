package rak.healthcenter.events;

import rak.healthcenter.model.Condition;
import rak.healthcenter.model.Patient;
import rak.utility.events.Event;

public class RecoverFromConditionEvent implements Event {
	private Patient patient;
	private Condition condition;
	private boolean agedOut;
	
	public RecoverFromConditionEvent(Patient patient, Condition condition, boolean agedOut){
		this.patient = patient;
		this.condition = condition;
		this.agedOut = agedOut;
	}

	public Condition getCondition(){
		return condition;
	}
	
	public boolean isAgedOut(){
		return agedOut;
	}

	public Patient getPatient(){
		return patient;
	}
}
