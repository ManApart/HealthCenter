package rak.healthcenter;

import java.util.ArrayList;
import java.util.List;

import rak.healthcenter.events.ContractConditionEvent;
import rak.healthcenter.events.PatientStateChangedEvent;
import rak.healthcenter.events.RecoverFromConditionEvent;
import rak.healthcenter.events.TimePassEvent;
import rak.healthcenter.listeners.ContractConditionListener;
import rak.healthcenter.listeners.RecoverFromConditionListener;
import rak.healthcenter.listeners.TimePassListener;
import rak.healthcenter.model.Condition;
import rak.healthcenter.model.NextCondition;
import rak.healthcenter.model.Patient;
import rak.healthcenter.model.Treatment;
import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.model.enums.Location;
import rak.healthcenter.model.enums.ZoomLevel;
import rak.healthcenter.parsers.ConditionParser;
import rak.utility.MathFunctions;
import rak.utility.events.EventDirector;

public class HealthManager {
	private ConditionParser conditionParser;
	private List<Patient> patients = new ArrayList<>();
	
	public HealthManager(ConditionParser conditionParser){
		this.conditionParser = conditionParser;
		EventDirector.registerListener(new TimePassListener(this));
		EventDirector.registerListener(new ContractConditionListener(this));
		EventDirector.registerListener(new RecoverFromConditionListener(this));
	}
	
	public void addPatient(Patient patient){
		patients.add(patient);
	}

	public void handle(TimePassEvent event) {
		int timePassed = event.getAmountOfTimePassed();
		System.out.println("Time pass event " + timePassed);
		for (Patient patient : patients){
			for (Condition condition : patient.getConditions()){
				passTime(timePassed, patient, condition);
			}
		}
	}

	private void passTime(int timePassed, Patient patient, Condition condition) {
		condition.addAge(timePassed);
		if (condition.hasExceededLifeSpan()){
			ageOutCondition(patient, condition);
		}
	}

	private void ageOutCondition(Patient patient, Condition condition) {
		EventDirector.postEvent(new RecoverFromConditionEvent(patient, condition, true));
		Location overrideLocation = condition.getOverrideLocation();
		for (NextCondition nextCondition : condition.getNextConditions()){
			contractPossibleCondition(patient, nextCondition, overrideLocation);
		}
	}
	
	private void contractPossibleCondition(Patient patient, NextCondition nextCondition, Location overrideLocation) {
		if(MathFunctions.probability(nextCondition.getChance())){
			Condition condition = conditionParser.getCondition(nextCondition.getConditionId(), overrideLocation);
			EventDirector.postEvent(new ContractConditionEvent(patient, condition));
		}
	}

	public void handle(ContractConditionEvent event) {
		event.getPatient().contractCondition(event.getCondition());
		System.out.println("Contracted " + event.getCondition().getName());
		EventDirector.postEvent(new PatientStateChangedEvent(event.getPatient()));
	}
	
	public void handle(RecoverFromConditionEvent event) {
		if (event.isAgedOut()){
			System.out.println("Recovered From " + event.getCondition().getName());
		} else {
			System.out.println("Treatment removed " + event.getCondition().getName());
		}
		event.getPatient().recoverFromCondition(event.getCondition());
		EventDirector.postEvent(new PatientStateChangedEvent(event.getPatient()));
	}
	
	
	public void applyTreatment(Patient patient, Treatment treatment) {
		cureConditions(patient, treatment);
		contractSideEffects(patient, treatment);
		EventDirector.postEvent(new PatientStateChangedEvent(patient));
	}

	private void cureConditions(Patient patient, Treatment treatment) {
		for (String id : treatment.getTreatedConditionIds()) {
			Condition condition = conditionParser.getCondition(id);
			if (patient.hasCondition(condition)) {
				patient.recoverFromCondition(condition);
			}
		}
	}

	private void contractSideEffects(Patient patient, Treatment treatment) {
		for (String id : treatment.getAddedConditionIds()) {
			Condition condition = conditionParser.getCondition(id);
			patient.contractCondition(condition);
		}
	}
	
	public void diagnoseSymptoms(Patient patient, HealthSystem system, Location location, ZoomLevel level) {
		patient.diagnoseSymptoms(system, location, level);
	}

}
