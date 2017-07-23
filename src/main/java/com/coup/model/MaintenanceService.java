package com.coup.model;

import java.util.ArrayList;

public class MaintenanceService {

	private ArrayList<District> districts = new ArrayList<District>();
	private int fleetManagerCapacity;
	private int fleetEngineerCapacity;
	int managerDistrict = 0;
	
	public MaintenanceService(int[] district,int fleetEngineerCapacity, int fleetManagerCapacity) {
		this.fleetManagerCapacity = fleetManagerCapacity;
		this.fleetEngineerCapacity = fleetEngineerCapacity;
		Integer lowestWaste = null;
		int count = 0;
		for (int numberOfScooters : district) {
			int waste = calculateWaste(numberOfScooters);
			District d = new District(count, numberOfScooters,calculateEngineersNeeded(true,numberOfScooters),calculateEngineersNeeded(false,numberOfScooters),waste);
			districts.add(d);
			if(lowestWaste == null || waste<lowestWaste){
				lowestWaste= waste;
				managerDistrict = count;
			}
			count++;
		}
		if(districts.size()>0){
			districts.get(managerDistrict).setManagerAlocated(true);
		}
	}
	
	public int getManagerDistrict(){
		return managerDistrict;
	}
	
	public int getTotalEngineersNeeded(){
		int total = 0;
		for (District district : getDistricts()) {
			total+= district.getEngineersNeeded();
		}
		return total;
	}
	
	private int calculateWaste(int numberOfScooters) {
		int sum = fleetManagerCapacity;
		if(getFleetManagerCapacity()%numberOfScooters==0){
			return 0;
		}
		while(sum<numberOfScooters){
			sum+=getFleetEngineerCapacity();
		}
		return sum-numberOfScooters;
	}

	public int calculateEngineersNeeded(boolean withManager, int numScooters) {
		if(withManager && getFleetManagerCapacity()%numScooters==0){
			return 0;
		}
		int countEngineers = 0;
		int sum = withManager? getFleetManagerCapacity() : 0;
		while(sum<numScooters){
			countEngineers++;
			sum+= Math.ceil(getFleetEngineerCapacity());
			if(sum%numScooters==0){
				return countEngineers;
			}
		}
		return countEngineers;
	}

	public ArrayList<District> getDistricts() {
		return districts;
	}

	public int getFleetManagerCapacity() {
		return fleetManagerCapacity;
	}

	public int getFleetEngineerCapacity() {
		return fleetEngineerCapacity;
	}

}
