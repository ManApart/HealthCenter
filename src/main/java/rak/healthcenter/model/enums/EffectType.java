package rak.healthcenter.model.enums;

public enum EffectType {
	BREATHING("Breathing"), MOBILITY("Mobility"), FOCUS("Focus"), HEALTH("Health");
	
	private String name;
	
	private EffectType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
