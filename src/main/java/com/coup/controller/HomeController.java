package com.coup.controller;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coup.model.FleetEngineersResponse;
import com.coup.model.MaintenanceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping(value="/coup",consumes = "application/json")
	public @ResponseBody String listCountries(@RequestBody JSONObject obj) throws JSONException, JsonProcessingException {
		JSONArray jsonScooters =  obj.getJSONArray("scooters");
		int fleetManagerCapacity = obj.getInt("C");
		int fleetEngineerCapacity = obj.getInt("P");
		int[] scooters = parse(jsonScooters);
		HashMap<String, String> invalidFieldsWithValidationMessage = validateCoupRequest(scooters,fleetEngineerCapacity,fleetManagerCapacity);
		ObjectMapper mapper = new ObjectMapper();
		if(invalidFieldsWithValidationMessage.size()>0){
			return mapper.writeValueAsString(invalidFieldsWithValidationMessage);
		}
		MaintenanceService maintenanceService = new MaintenanceService(scooters, fleetEngineerCapacity, fleetManagerCapacity);
		return mapper.writeValueAsString(new FleetEngineersResponse(maintenanceService.getTotalEngineersNeeded()));
	}

	private HashMap<String, String> validateCoupRequest(int[] scooters, int fleetEngineerCapacity, int fleetManagerCapacity) {
		HashMap<String, String> validationErrors = new HashMap<String,String>(); 
		if(scooters.length>100 || scooters.length<1){
			validationErrors.put("scootersValidationError", "Value needs to be between 1 and 100");
		}
		int invalidElement = 0;
		for (int i : scooters) {
			if(i<0 || i>1000){
				invalidElement++;
			}
		}
		if(invalidElement>0){
			validationErrors.put("scooterElementValidationError", invalidElement+" invalid elements in scooters. Elements needs to be between 0 and 1000");
		}
		if(scooters.length>100 || scooters.length<1){
			validationErrors.put("scootersValidationError", "Value needs to be between 1 and 100");
		}
		if(fleetManagerCapacity<1 || fleetManagerCapacity>999){
			validationErrors.put("CValidationError", "Value needs to be between 1 and 999");
		}
		if(fleetEngineerCapacity<1 || fleetEngineerCapacity>1000){
			validationErrors.put("PValidationError", "Value needs to be between 1 and 1000");
		}
		return validationErrors;
			
	}

	private int[] parse(JSONArray jsonScooters) {
		int[] intArray = new int[jsonScooters.length()];
		for (int i = 0; i < jsonScooters.length(); ++i) {
			intArray[i] = jsonScooters.optInt(i);
		}
		return intArray;
	}
	
}
