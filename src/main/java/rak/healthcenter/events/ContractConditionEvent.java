package rak.healthcenter.events;

import rak.healthcenter.model.Condition;
import rak.utility.events.Event;

public class ContractConditionEvent implements Event {
	private Condition condition;

	public ContractConditionEvent(Condition condition) {
		this.condition = condition;
	}
	
	public Condition getCondition(){
		return condition;
	}

}
