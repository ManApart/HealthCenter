package rak.healthcenter.events;

import rak.healthcenter.model.Condition;
import rak.utility.events.Event;

public class RecoverFromConditionEvent implements Event {
	private boolean agedOut;
	private Condition condition;
	
	public RecoverFromConditionEvent(Condition condition, boolean agedOut){
		this.condition = condition;
		this.agedOut = agedOut;
	}

	public Condition getCondition(){
		return condition;
	}
	
	public boolean isAgedOut(){
		return agedOut;
	}
}
