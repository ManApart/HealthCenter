package rak.healthcenter.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import rak.healthcenter.model.Symptom;
import rak.healthcenter.ui.HealthStationHelper;

public class SymptomController {
	
	@FXML private Label title;
	@FXML private Label system;
	@FXML private Label zoom;
	@FXML private Label affectedLocation;
	@FXML private Label effect;
	@FXML private Label amount;
	@FXML private GridPane labelGrid;
	
	private Symptom symptom;
	private HealthStationHelper healthStationHelper;
	
	private static GridPane diagnoseGrid;
	
	public SymptomController(Symptom symptom, HealthStationHelper healthStationHelper){
		this.symptom = symptom;
		this.healthStationHelper = healthStationHelper;
	}
	
	public void initialize(){
		title.setText(symptom.getName());
		system.setText(symptom.getAffectedSystem().getName());
		zoom.setText(symptom.getAffectedLevel().getName());
		affectedLocation.setText(symptom.getAffectedLocation().getName());
		effect.setText("Affects " + symptom.getEffect().getType().getName() + " by");
		amount.setText("" + symptom.getEffect().getAmount());

	}
	
	public static void createGrid(GridPane parentPane, HealthStationHelper healthStationHelper) {
		String panelName = "view/SymptomPanel.fxml";
		int i=0;
		for (Symptom symptom: healthStationHelper.getAllSymptoms()) {
			SymptomController controller = new SymptomController(symptom, healthStationHelper);
			GridPane grid = MainMenuController.loadController(controller, panelName);
//			parentPane.getChildren().add(grid);
			parentPane.add(grid, 0, i++);
		}
	}
	
	public static void createDiagnoseGrid(GridPane parentPane, HealthStationHelper healthStationHelper) {
		if (parentPane != null){
			diagnoseGrid = parentPane;
		}
		refreshDiagnoseGrid(healthStationHelper);
	}
	
	public static void refreshDiagnoseGrid(HealthStationHelper healthStationHelper) {
		diagnoseGrid.getChildren().clear();
		String panelName = "view/SymptomPanel.fxml";
		int i=0;
		for (Symptom symptom: healthStationHelper.getHealthStation().getPatient().getDiagnosedSymptoms()) {
			SymptomController controller = new SymptomController(symptom, healthStationHelper);
			GridPane grid = MainMenuController.loadController(controller, panelName);
			diagnoseGrid.add(grid, 0, i++);
		}
	}


}
