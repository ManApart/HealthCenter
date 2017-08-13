package rak.healthcenter.events;

import rak.utility.events.Event;

public class TimePassEvent implements Event{
	private int amountOfTimePassed;
	
	public TimePassEvent(int amountOfTimePassed){
		this.amountOfTimePassed = amountOfTimePassed;
	}
	
	public int getAmountOfTimePassed(){
		return amountOfTimePassed;
	}

}
