package rak.healthcenter.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import rak.healthcenter.events.TimePassEvent;
import rak.healthcenter.ui.HealthStationHelper;
import rak.utility.events.EventDirector;

public class MainOptionsController {
	private HealthStationHelper healthStationHelper;
	
	@FXML private TextField ageAmount;
	@FXML private TextField conditionCount;
	
	@FXML private Button advanceAge;
	@FXML private Button addRandomConditions;
	
	private static final int AGE_AMOUNT_DEFAULT = 1;
	private static final int CONDITION_COUNT_DEFAULT = 3;
	
	
	public void initialize(){
		initializeAgeControls();
		initializeRandomConditionControls();
	}

	public void initializeAgeControls() {
		ageAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				advanceAge.setText("Advance Age by " + parseInt(newValue, AGE_AMOUNT_DEFAULT));
			}
		});
		ageAmount.setText("" + AGE_AMOUNT_DEFAULT);
	}

	public void initializeRandomConditionControls() {
		conditionCount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				addRandomConditions.setText("Add " + parseInt(newValue, CONDITION_COUNT_DEFAULT) + " Random Conditions");
			}
		});
		conditionCount.setText("" + CONDITION_COUNT_DEFAULT);
	}
	
	public void setHealthStationHelper(HealthStationHelper healthStationHelper){
		this.healthStationHelper = healthStationHelper;
	}
	
	private int parseInt(String in, int defaultValue){
		try {
			return Integer.parseInt(in);
		} catch(Exception e){
			System.out.println(in + " must be an int");
		}
		return defaultValue;
	}
	
	@FXML
	public void advanceAge(){
		int amount = parseInt(ageAmount.getText(), AGE_AMOUNT_DEFAULT);
		EventDirector.postEvent(new TimePassEvent(amount));
	}

	@FXML
	public void addRandomConditions(){
		int count = parseInt(conditionCount.getText(), CONDITION_COUNT_DEFAULT);
		healthStationHelper.addRandomConditions(count);
	}
	

	
}
