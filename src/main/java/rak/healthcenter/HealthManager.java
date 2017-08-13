package rak.healthcenter;

import java.util.ArrayList;
import java.util.List;

import rak.healthcenter.events.ContractConditionEvent;
import rak.healthcenter.events.RecoverFromConditionEvent;
import rak.healthcenter.events.TimePassEvent;
import rak.healthcenter.listeners.ContractConditionListener;
import rak.healthcenter.listeners.RecoverFromConditionListener;
import rak.healthcenter.listeners.TimePassListener;
import rak.healthcenter.model.Condition;
import rak.healthcenter.model.NextCondition;
import rak.healthcenter.model.Patient;
import rak.healthcenter.model.enums.Location;
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
				passTime(timePassed, condition);
			}
		}
	}

	private void passTime(int timePassed, Condition condition) {
		condition.addAge(timePassed);
		if (condition.hasExceededLifeSpan()){
			ageOutCondition(condition);
		}
	}

	private void ageOutCondition(Condition condition) {
		EventDirector.postEvent(new RecoverFromConditionEvent(condition, true));
		Location overrideLocation = condition.getOverrideLocation();
		for (NextCondition nextCondition : condition.getNextConditions()){
			contractPossibleCondition(nextCondition, overrideLocation);
		}
	}
	
	private void contractPossibleCondition(NextCondition nextCondition, Location overrideLocation) {
		if(MathFunctions.probability(nextCondition.getChance())){
			Condition condition = conditionParser.getCondition(nextCondition.getConditionId(), overrideLocation);
			EventDirector.postEvent(new ContractConditionEvent(condition));
		}
	}

	public void handle(ContractConditionEvent event) {
		System.out.println("Contracted " + event.getCondition().getName());
	}
	
	public void handle(RecoverFromConditionEvent event) {
		if (event.isAgedOut()){
			System.out.println("Recovered From " + event.getCondition().getName());
		} else {
			System.out.println("Treatment removed " + event.getCondition().getName());
		}
	}

}
