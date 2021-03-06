package rak.healthcenter.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import rak.healthcenter.model.enums.Location;

/*
 * What happens when you treat a broken limb with a cast? We remove the broken bone condition
 * and add a cast condition. How do we prevent instant removal of the cast?
 * add a minimum age before a treatment can be treated?
 */
public class Condition {
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private int lifeSpan;
	
	@JsonProperty("symptoms")
	private List<String> symptomIds = new ArrayList<>();
	
	@JsonProperty
	private List<NextCondition> nextConditions = new ArrayList<>();
	
	private int age;
	
	private List<Symptom> symptoms = new ArrayList<>();
	
	private Location overrideLocation;
	
	@Override
	public String toString(){
		String message = getName() + ": ";
		for (Symptom symptom : symptoms){
			message += symptom.getName() + ", ";
		}
		return message;
	}
	
	@Override
	public Condition clone(){
		Condition clone = new Condition();
		clone.id = id;
		clone.name = name;
		clone.lifeSpan = lifeSpan;
		
		clone.nextConditions = new ArrayList<>(nextConditions);
		
		for (Symptom symptom : symptoms){
			clone.addSymptom(symptom.clone());
		}
		
		return clone;
	}
	
	@Override
	public int hashCode() {
	    return id.hashCode();
	}
	
	@Override
	public boolean equals(Object other){
		if (other != null && other instanceof Condition){
			Condition condition = (Condition) other;
			if (id != null){
				return id.equals(condition.id);
			} else {
				return (id == null && condition.id == null); 
			}
		}
		return false;
	}
	
	public void addSymptom(Symptom symptom){
		this.symptoms.add(symptom);
	}
	
	public List<Symptom> getSymptoms(){
		return new ArrayList<>(symptoms);
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	public int getLifeSpan() {
		return lifeSpan;
	}

	public List<NextCondition> getNextConditions() {
		return nextConditions;
	}

	public int getAge() {
		return age;
	}

	public List<String> getSymptomIds(){
		return new ArrayList<>(symptomIds);
	}
	
	public void addAge(int amount){
		this.age += amount;
	}
	
	public boolean hasExceededLifeSpan(){
		return lifeSpan !=0 && age >= lifeSpan;
	}

	public void setLocation(Location location) {
		if (location != null){
			overrideLocation = location;
			for (Symptom symptom: symptoms){
				symptom.setLocationIfOverridable(location);
			}
		}
	}

	/**
	 * Returns an override location if it exists; will usually return null
	 */
	public Location getOverrideLocation() {
		return overrideLocation;
	}

}
