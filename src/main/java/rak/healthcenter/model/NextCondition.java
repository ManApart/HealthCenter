package rak.healthcenter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NextCondition {
	
	@JsonProperty("id")
	private String conditionId;
	
	@JsonProperty
	private float chance;

	public String getConditionId() {
		return conditionId;
	}

	public float getChance() {
		return chance;
	}
	

}
