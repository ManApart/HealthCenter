package rak.healthcenter.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import rak.healthcenter.ui.HealthStationHelper;

public class MainOptionsController {
	private HealthStationHelper healthStationHelper;
	
	@FXML private TextField ageAmount;
	@FXML private TextField conditionCount;
	
	@FXML private Button advanceAge;
	@FXML private Button addRandomConditions;
	
	public void setHealthStationHelper(HealthStationHelper healthStationHelper){
		this.healthStationHelper = healthStationHelper;
	}

	
	@FXML
	public void addRandomConditions(){
		
	}
	
	@FXML
	public void advanceAge(){
		
	}
	
}
