package rak.healthcenter.model;

import rak.healthcenter.model.enums.EffectType;

public class Effect {
	private EffectType type;
	private float amount;
	
	public Effect(){
	}
	
	public Effect(EffectType type){
		this.type = type;
	}

	public EffectType getType() {
		return type;
	}

	public float getAmount() {
		return amount;
	}
	
	public void clearAmount(){
		this.amount = 0;
	}

	public void increaseAmount(float amount) {
		this.amount += amount;
	}
	
	

}
