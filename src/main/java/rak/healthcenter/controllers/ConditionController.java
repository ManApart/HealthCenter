package rak.healthcenter.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import rak.healthcenter.model.Condition;
import rak.healthcenter.model.Symptom;
import rak.healthcenter.ui.HealthStationHelper;

public class ConditionController {
	
	@FXML private Label title;
	@FXML private GridPane labelGrid;
	
	private Condition condition;
	private HealthStationHelper healthStationHelper;
	private boolean addCondition;
	
	private static GridPane patientInfoGrid;
	
	public ConditionController(Condition condition, HealthStationHelper healthStationHelper){
		this.condition = condition;
		this.healthStationHelper = healthStationHelper;
	}
	
	public void initialize(){
		title.setText(condition.getName());
		createSymptomLabels();
	}
	
	private void createSymptomLabels() {
		labelGrid.getChildren().clear();
		int i=0;
		for (Symptom symptom : condition.getSymptoms()){
			addSymptomLabel(symptom, i++);
		}
	}

	private void addSymptomLabel(Symptom symptom, int i) {
		Label label = new Label("-" + symptom.getName());
		labelGrid.add(label, 0, i);
	}
	
	@FXML
	public void selectCondition(){
		if (addCondition){
			addConditionToPatient();
		} else {
			removeConditionFromPatient();
		}
		createPatientInfoGrid(patientInfoGrid, healthStationHelper);
	}
	
	private void addConditionToPatient() {
		healthStationHelper.getHealthStation().getPatient().contractCondition(condition);
	}

	private void removeConditionFromPatient() {
		healthStationHelper.getHealthStation().getPatient().recoverFromCondition(condition);
	}

	public static void createGrid(GridPane parentPane, HealthStationHelper healthStationHelper) {
		String panelName = "view/ConditionPanel.fxml";
		int i=0;
		for (Condition condition : healthStationHelper.getAllConditions()) {
			ConditionController controller = new ConditionController(condition, healthStationHelper);
			controller.addCondition = true;
			GridPane grid = MainMenuController.loadController(controller, panelName);
			parentPane.add(grid, 0, i++);
		}
	}
	
	public static void createPatientInfoGrid(GridPane parentPane, HealthStationHelper healthStationHelper) {
		patientInfoGrid = parentPane;
		parentPane.getChildren().clear();
		String panelName = "view/ConditionPanel.fxml";
		int i=0;
		for (Condition condition : healthStationHelper.getHealthStation().getPatient().getConditions()) {
			ConditionController controller = new ConditionController(condition, healthStationHelper);
			controller.addCondition = false;
			GridPane grid = MainMenuController.loadController(controller, panelName);
			parentPane.add(grid, 0, i++);
		}
	}

}
