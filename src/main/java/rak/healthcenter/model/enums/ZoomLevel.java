package rak.healthcenter.model.enums;

public enum ZoomLevel {
	NAKED_EYE(1, "Naked Eye"), MAGNIFYING_GLASS(2, "Magnifying Glass"), MICROSCOPE(3, "Microscope"), MOLECULAR(4, "Molecular");
	
	private int level;
	private String name;
	
	private ZoomLevel(int level, String name){
		this.level = level;
		this.name = name;
	}
	
//	public int getLevel(){
//		return level;
//	}
	
	public String getName(){
		return name;
	}
	
	public static ZoomLevel getByLevel(int level){
		for (ZoomLevel zoomLevel : ZoomLevel.values()){
			if (zoomLevel.level == level){
				return zoomLevel;
			}
		}
		//Should probably throw exception here
		return null;
	}
}
