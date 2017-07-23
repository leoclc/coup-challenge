package com.coup.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FleetEngineersResponse {

	@JsonProperty("fleet_engineers")
	private int fleetEngineers;
	
	public FleetEngineersResponse(int totalEngineersNeeded) {
		this.fleetEngineers = totalEngineersNeeded;
	}
	
	public int getFleetEngineers() {
		return fleetEngineers;
	}
	
}
