package rak.healthcenter.model.enums;

public enum Location {
	HEAD("Head"), ARM_LEFT("Left Arm"), ARM_RIGHT("Right Arm"), TORSO("Torso"), LEG_LEFT("Left Leg"), LEG_RIGHT("Right Leg");
	
	private String name;
	
	private Location(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
