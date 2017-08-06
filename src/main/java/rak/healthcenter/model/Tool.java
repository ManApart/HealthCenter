package rak.healthcenter.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.model.enums.ZoomLevel;

public class Tool {
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty("system")
	private HealthSystem affectedSystem = HealthSystem.NONE;
	
	@JsonProperty("zoom")
	private ZoomLevel affectedLevel = ZoomLevel.NAKED_EYE;
	
	@JsonProperty("default")
	private boolean isDefault;
	
	@JsonProperty("treatments")
	private List<String> treatmentIds = new ArrayList<>();
	
	private List<Treatment> treatments = new ArrayList<>();
	
	@Override
	public String toString(){
		String message = getName() + ": ";
		for (Treatment treatment : treatments){
			message += treatment.getName() + ", ";
		}
		return message;
	}
	
	public void addTreatment(Treatment treatment){
		this.treatments.add(treatment);
	}
	
	public List<Treatment> getTreatments(){
		return new ArrayList<>(treatments);
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	public List<String> getTreatmentIds(){
		return new ArrayList<>(treatmentIds);
	}

	public HealthSystem getAffectedSystem() {
		return affectedSystem;
	}

	public ZoomLevel getAffectedLevel() {
		return affectedLevel;
	}
	
	public boolean isDefault(){
		return isDefault;
	}
	
	
}
