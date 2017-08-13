package rak.healthcenter.events;

import rak.healthcenter.model.Condition;
import rak.healthcenter.model.Patient;
import rak.utility.events.Event;

public class ContractConditionEvent implements Event {
	private Condition condition;
	private Patient patient;

	public ContractConditionEvent(Patient patient, Condition condition) {
		this.condition = condition;
		this.patient = patient;
	}
	
	public Condition getCondition(){
		return condition;
	}

	public Patient getPatient(){
		return patient;
	}
}
