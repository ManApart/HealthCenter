package rak.healthcenter.events;

import rak.healthcenter.model.Tool;
import rak.utility.events.Event;

public class ToolSelectEvent implements Event {
	private Tool tool;

	public ToolSelectEvent(Tool tool) {
		this.tool = tool;
	}
	
	public Tool getTool(){
		return tool;
	}

}
