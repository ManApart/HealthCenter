package rak.healthcenter.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Treatment {
	
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty("treatedConditions")
	private List<String> treatedConditionIds = new ArrayList<>();
	
	@JsonProperty("addedConditions")
	private List<String> addedConditionIds = new ArrayList<>();
	
	@Override
	public String toString(){
		return getName();
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	public List<String> getTreatedConditionIds(){
		return new ArrayList<>(treatedConditionIds);
	}
	
	public List<String> getAddedConditionIds(){
		return new ArrayList<>(addedConditionIds);
	}

}
