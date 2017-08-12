package rak.healthcenter.controllers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import rak.healthcenter.model.Tool;
import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.ui.HealthStationHelper;

public class ToolController {
	
	@FXML private Label title;
	@FXML private Label zoom;
	@FXML private Label system;
	@FXML private GridPane labelGrid;
	
	private Tool tool;
	private HealthStationHelper healthStationHelper;
	private boolean toggleTool;
	
	private static GridPane inspectGrid;
	
	public ToolController(Tool tool, HealthStationHelper healthStationHelper){
		this.tool = tool;
		this.healthStationHelper = healthStationHelper;
	}
	
	public void initialize(){
		title.setText(tool.getName());

		zoom.setText(tool.getAffectedLevel().getName());
		system.setText(tool.getAffectedSystem().getName());
		
		createLabels(tool.getTreatmentIds(), 0, "Treats");
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
	
	@FXML
	public void selectTool(){
		if (toggleTool){
			toggleTool();
		} else {
			healthStationHelper.setPatientView(tool.getAffectedSystem(), tool.getAffectedLevel());
		}
		createInspectGrid(inspectGrid, healthStationHelper);
	}
	
	private void toggleTool() {
		boolean hasTool = healthStationHelper.getHealthStation().getAllTools().contains(tool);
		if (hasTool){
			System.out.println("Removing " + tool);
			healthStationHelper.getHealthStation().removeTool(tool);
		} else {
			System.out.println("Adding " + tool);
			healthStationHelper.getHealthStation().addTool(tool);
		}
	}

	public static void createGrid(GridPane parentPane, HealthStationHelper healthStationHelper) {
		String panelName = "view/ToolPanel.fxml";
		int i=0;
		for (Tool tool : healthStationHelper.getAllTools()) {
			ToolController controller = new ToolController(tool, healthStationHelper);
			controller.toggleTool = true;
			GridPane grid = MainMenuController.loadController(controller, panelName);
			parentPane.add(grid, 0, i++);
		}
	}
	
	public static void createInspectGrid(GridPane parentPane, HealthStationHelper healthStationHelper) {
		inspectGrid = parentPane;
		parentPane.getChildren().clear();
		String panelName = "view/ToolPanel.fxml";
		int i=0;
		for (Tool tool : healthStationHelper.getHealthStation().getAllTools()) {
			if (tool.getAffectedSystem() != HealthSystem.NONE){
				ToolController controller = new ToolController(tool, healthStationHelper);
				controller.toggleTool = false;
				GridPane grid = MainMenuController.loadController(controller, panelName);
				parentPane.add(grid, 0, i++);
			}
		}
	}

}
