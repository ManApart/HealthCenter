package rak.healthcenter.model;

import java.util.ArrayList;
import java.util.List;

import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.model.enums.ZoomLevel;

public class HealthArea {
	private HealthSystem system;
	private List<ZoomLevel> levels = new ArrayList<>();
	
	
	public HealthArea(HealthSystem system){
		this.system = system;
	}
	
	public HealthSystem getHealthSystem(){
		return system;
	}
	
	public void addLevel(ZoomLevel level){
		this.levels.add(level);
	}
	
	public void removeLevel(ZoomLevel level){
		this.levels.remove(level);
	}
	
	public List<ZoomLevel> getLevels(){
		return new ArrayList<>(levels);
	}
	
}
