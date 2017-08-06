package rak.healthcenter.model.enums;

public enum HealthSystem {
	NERVOUS("Nervous", "Nerves.png"), 
	CICULATORY_RESPITORY("Circulatory and Respitory", "Circulatory.png"), 
	SKELETAL("Skeletal", "Skeleton.png"), 
	MUSCULAR("Muscular", "Muscle.png"), 
	DIGESTIVE("Digestive", "Digestive.png"),
	NONE("Skin", "Skin.png");
	
	private String name;
	private String imageName;
	
	private HealthSystem(String name, String imageName){
		this.name = name;
		this.imageName = imageName;
	}
	
	public String getName(){
		return name;
	}
	
	public String getImageName(){
		return imageName;
	}
}
