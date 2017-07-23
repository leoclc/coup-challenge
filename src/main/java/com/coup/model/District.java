package com.coup.model;

public class District {
	
	private int id;
    private int scooters;
    private boolean managerAlocated;
	private int engineersNeededWithManager;
	private int engineersNeededWithoutManager;
	private int wasteWithManagerOnly;
	
	public District(int id, int scooters, int engineersNeededWithManager, int engineersNeededWithoutManager, int wasteWithManagerOnly) {
		this.id = id;
		this.scooters = scooters;
		this.engineersNeededWithManager = engineersNeededWithManager;
		this.engineersNeededWithoutManager = engineersNeededWithoutManager;
		this.wasteWithManagerOnly = wasteWithManagerOnly;
	}

	
	public int getWasteWithManagerOnly() {
		return wasteWithManagerOnly;
	}



	public int getId(){
		return this.id;
	}
	
	public int getScooters() {
		return scooters;
	}

	public int getEngineersNeeded(){
		if(managerAlocated){
			return engineersNeededWithManager;
		}
		return this.engineersNeededWithoutManager;
	}
	
	public boolean isManagerAlocated() {
		return managerAlocated;
	}

	public int getEngineersNeededWithManager() {
		return engineersNeededWithManager;
	}

	public int getEngineersNeededWithoutManager() {
		return engineersNeededWithoutManager;
	}

	public void setManagerAlocated(boolean managerAlocated) {
		this.managerAlocated = managerAlocated;
	}
	
	
	
}
