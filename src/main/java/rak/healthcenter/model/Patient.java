package rak.healthcenter.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rak.healthcenter.model.enums.EffectType;
import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.model.enums.Location;
import rak.healthcenter.model.enums.ZoomLevel;

public class Patient {
	private List<Condition> conditions = new ArrayList<>();
	private List<Symptom> diagnosedSymptoms = new ArrayList<>();
	private Map<EffectType, Effect> effects = new HashMap<>();
	
	
	public Patient(){
		initializeEffects();
	}
	
	private void initializeEffects() {
		for (EffectType type : EffectType.values()){
			effects.put(type, new Effect(type));
		}
	}

	public void contractCondition(Condition condition){
		if (!conditions.contains(condition)){
			this.conditions.add(condition);
			calculateEffects();
		}
	}
	
	public void recoverFromCondition(Condition condition){
		this.conditions.remove(condition);
		calculateEffects();
		removeDiagnosedSymptoms(condition);
	}

	public List<Condition> getConditions(){
		return new ArrayList<>(conditions);
	}
	
	public float getEffectAmount(EffectType type){
		return effects.get(type).getAmount();
	}
	
	public List<Symptom> getSymptoms(){
		List<Symptom> symptoms = new ArrayList<>();
		for (Condition condition : getConditions()){
			symptoms.addAll(condition.getSymptoms());
		}
		return symptoms;
	}
	
	public List<Symptom> getDiagnosedSymptoms(){
		return new ArrayList<>(diagnosedSymptoms);
	}
	
	public boolean hasCondition(Condition condition){
		return conditions.contains(condition);
	}
	
	public boolean hasSymptoms(HealthSystem system, Location location, ZoomLevel level){
		return !getSymptoms(system, location, level).isEmpty();
	}
	
	public List<Symptom> getSymptoms(HealthSystem system, Location location, ZoomLevel level){
		List<Symptom> symptoms = new ArrayList<>();
		for (Symptom symptom : getSymptoms()){
			if (symptom.isVisible(system, location, level)){
				symptoms.add(symptom);
			}
		}
		return symptoms;
	}

	public void diagnoseSymptoms(HealthSystem system, Location location, ZoomLevel level){
		for (Symptom symptom : getSymptoms(system, location, level)){
			if (!diagnosedSymptoms.contains(symptom)){
				diagnosedSymptoms.add(symptom);
			}
		}
	}
	
	private void calculateEffects(){
		resetEffectValues();
		sumEffectsOfConditions();
	}

	private void resetEffectValues() {
		for (Effect effect : effects.values()){
			effect.clearAmount();
		}
	}

	private void sumEffectsOfConditions() {
		for (Condition condition : conditions){
			for (Symptom symptom : condition.getSymptoms()){
				addSymptomEffect(symptom);
			}
		}		
	}
	
	private void addSymptomEffect(Symptom symptom) {
		Effect symptomEffect = symptom.getEffect();
		Effect patientEffect = effects.get(symptomEffect.getType());
		patientEffect.increaseAmount(symptomEffect.getAmount());		
	}
	
	private void removeDiagnosedSymptoms(Condition condition) {
		for (Symptom symptom : condition.getSymptoms()){
			if (diagnosedSymptoms.contains(symptom)){
				diagnosedSymptoms.remove(symptom);
			}
		}
	}

}
