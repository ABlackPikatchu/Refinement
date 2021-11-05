package com.ablackpikatchu.refinement.api.event;

import net.minecraftforge.eventbus.api.Event;

public abstract class RefinementEvent extends Event {

	public enum Action {
		CANCEL, CONTINUE;
		
		public boolean isCancelled() {
			return this == CANCEL;
		}
	}
	
	public Action getAction() {
		return this.isCanceled() ? Action.CONTINUE : Action.CONTINUE;
	}
	
}
