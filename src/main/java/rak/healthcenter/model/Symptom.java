package rak.healthcenter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.model.enums.Location;
import rak.healthcenter.model.enums.ZoomLevel;

public class Symptom {
	
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty("system")
	private HealthSystem affectedSystem;
	
	@JsonProperty("zoom")
	private ZoomLevel affectedLevel;
	
	@JsonProperty("location")
	private Location affectedLocation;
	
	@JsonProperty("locationOverride")
	private boolean locationOverride;
	
	@JsonProperty
	private Effect effect;
	
	@Override
	public String toString(){
		return getName();
	}
	
	@Override
	public Symptom clone(){
		Symptom clone = new Symptom();
		clone.id = id;
		clone.name = name;
		clone.affectedSystem = affectedSystem;
		clone.affectedLevel = affectedLevel;
		clone.affectedLocation = affectedLocation;
		clone.locationOverride = locationOverride;
		clone.effect = effect;
		return clone;
	}
	
	public String getName(){
		return name;
	}

	public String getId() {
		return id;
	}

	public HealthSystem getAffectedSystem() {
		return affectedSystem;
	}

	public ZoomLevel getAffectedLevel() {
		return affectedLevel;
	}

	public Location getAffectedLocation() {
		return affectedLocation;
	}

	public Effect getEffect() {
		return effect;
	}
	
	protected void setLocationIfOverridable(Location location){
		if (locationOverride){
			this.affectedLocation = location;
		}
	}
	
	public boolean isVisible(HealthSystem system, Location location, ZoomLevel level) {
		return getAffectedSystem() == system && getAffectedLocation() == location && getAffectedLevel() == level;
	}
	
}
