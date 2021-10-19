package com.ablackpikatchu.refinement.common.te.security;

import java.util.UUID;

public interface ISecurableTile {
	
	void setSecurity(SecurityType security);

	SecurityType getSecurity();
	
	UUID getOwnerUUID();
	
}
