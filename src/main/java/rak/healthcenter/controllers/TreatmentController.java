package rak.healthcenter.controllers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import rak.healthcenter.model.Tool;
import rak.healthcenter.model.Treatment;
import rak.healthcenter.ui.HealthStationHelper;

public class TreatmentController {
	
	@FXML private Label title;
	@FXML private GridPane labelGrid;
	
	private Treatment treatment;
	private HealthStationHelper healthStationHelper;
	
	private static GridPane treatPane;
	
	public TreatmentController(Treatment treatment, HealthStationHelper healthStationHelper){
		this.treatment = treatment;
		this.healthStationHelper = healthStationHelper;
	}
	
	public void initialize(){
		title.setText(treatment.getName());

		createTreatmentLabels();
	}
	
	@FXML
	public void applyTreatment(){
		healthStationHelper.applyTreatment(treatment);
	}

	private void createTreatmentLabels() {
		labelGrid.getChildren().clear();
		createLabels(treatment.getTreatedConditionIds(), 0, "Treated");
		createLabels(treatment.getAddedConditionIds(), 1, "Side Effects");
	}

	private void createLabels(List<String> ids, int column, String columnTitle) {
		if (!ids.isEmpty()){
			ids.add(0, columnTitle);
		}
		int i=0;
		for (String id : ids){
			Label label = new Label(id);
			labelGrid.add(label, column, i++);
		}
	}

	public static void createGrid(GridPane parentPane, HealthStationHelper healthStationHelper) {
		String panelName = "view/TreatmentPanel.fxml";
		int i=0;
		for (Treatment treatment : healthStationHelper.getAllTreatments()) {
			TreatmentController controller = new TreatmentController(treatment, healthStationHelper);
			GridPane grid = MainMenuController.loadController(controller, panelName);
			parentPane.add(grid, 0, i++);
		}
	}
	
	public static void createTreatGrid(GridPane parentPane, HealthStationHelper healthStationHelper) {
		treatPane = parentPane;
		parentPane.getChildren().clear();
		String panelName = "view/TreatmentPanel.fxml";
		int i=0;
		for (Tool tool : healthStationHelper.getHealthStation().getAllTools()) {
			for (Treatment treatment : tool.getTreatments()){
				TreatmentController controller = new TreatmentController(treatment, healthStationHelper);
				GridPane grid = MainMenuController.loadController(controller, panelName);
				parentPane.add(grid, 0, i++);
			}
		}
	}

}
