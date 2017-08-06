package rak.healthcenter.ui;

public enum InputKey {
	DEBUG("d");
	
	private String character;
	
	private InputKey(String charecter){
		this.character = charecter;
	}

	public boolean isKey(String character) {
		return this.character.equalsIgnoreCase(character);
	}
	
	
}
