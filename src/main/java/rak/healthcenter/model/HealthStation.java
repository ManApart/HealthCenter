package rak.healthcenter.model;

import java.util.ArrayList;
import java.util.List;

import rak.healthcenter.listeners.ToolSelectListener;
import rak.utility.events.EventDirector;

public class HealthStation {
	private Patient patient;
	private List<Tool> tools = new ArrayList<>();
	private Tool currentTool;
	
	public HealthStation(){
		EventDirector.registerListener(new ToolSelectListener(this));
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void addTool(Tool tool){
		if (!tools.contains(tool)){
			tools.add(tool);
		}
	}
	
	public void removeTool(Tool tool){
		tools.remove(tool);
	}
	
	public List<Tool> getAllTools(){
		return new ArrayList<>(tools);
	}

	public Tool getCurrentTool() {
		return currentTool;
	}

	public void setCurrentTool(Tool currentTool) {
		this.currentTool = currentTool;
	}
	
}
