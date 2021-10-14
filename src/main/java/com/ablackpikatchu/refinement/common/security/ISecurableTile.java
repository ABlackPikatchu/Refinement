package com.ablackpikatchu.refinement.common.security;

import java.util.UUID;

public interface ISecurableTile {
	
	void setSecurity(SecurityType security);

	SecurityType getSecurity();
	
	//void setSecurity(SecurityType security);
	
	UUID getOwnerUUID();
	
}