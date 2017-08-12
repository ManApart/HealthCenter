package rak.healthcenter.controllers;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import rak.healthcenter.HealthCenterApplication;
import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.model.enums.ZoomLevel;
import rak.healthcenter.ui.HealthStationHelper;
import rak.healthcenter.ui.InputKey;

public class MainMenuController {

	@FXML private Slider resolutionSlider;
	
	@FXML private GridPane conditionsGrid;
	@FXML private GridPane symptomsGrid;
	@FXML private GridPane treatmentsGrid;
	@FXML private GridPane toolsGrid;
	@FXML private GridPane patientInfoGrid;
	@FXML private GridPane inspectGrid;
	@FXML private GridPane diagnoseGrid;
	@FXML private GridPane treatGrid;

	@FXML private Pane patientView;
	
	@FXML private TabPane mainTabs;
	@FXML private TabPane diagnoseTabs;
	@FXML private Tab patientInfoTab;
	@FXML private Tab conditionsTab;
	@FXML private Tab symptomsTab;
	@FXML private Tab toolsTab;
	@FXML private Tab treatmentsTab;
	
	private boolean showDebugTabs = true;
	private HealthStationHelper healthStationHelper = new HealthStationHelper();
	PatientViewController patientViewController;
	
	public void initialize(){
		healthStationHelper.setMainController(this);
		
		ConditionController.createGrid(conditionsGrid, healthStationHelper);
		ConditionController.createPatientInfoGrid(patientInfoGrid, healthStationHelper);

		SymptomController.createGrid(symptomsGrid, healthStationHelper);
		SymptomController.createDiagnoseGrid(diagnoseGrid, healthStationHelper);

		TreatmentController.createGrid(treatmentsGrid, healthStationHelper);
		TreatmentController.createTreatGrid(treatGrid, healthStationHelper);

		ToolController.createGrid(toolsGrid, healthStationHelper);
		ToolController.createInspectGrid(inspectGrid, healthStationHelper);
		
		patientViewController = PatientViewController.createGrid(patientView, healthStationHelper);
		
		toggleDebugTabs();
	}
	
	
	@FXML
	public void onKeyTyped(KeyEvent event){
		if (InputKey.DEBUG.isKey(event.getCharacter())){
			toggleDebugTabs();
		}
	}
	
	private void toggleDebugTabs(){
		showDebugTabs = !showDebugTabs;
		if (showDebugTabs){
			diagnoseTabs.getTabs().add(patientInfoTab);
			mainTabs.getTabs().add(conditionsTab);
			mainTabs.getTabs().add(symptomsTab);
			mainTabs.getTabs().add(toolsTab);
			mainTabs.getTabs().add(treatmentsTab);
		} else {
			diagnoseTabs.getTabs().remove(patientInfoTab);
			mainTabs.getTabs().remove(conditionsTab);
			mainTabs.getTabs().remove(symptomsTab);
			mainTabs.getTabs().remove(toolsTab);
			mainTabs.getTabs().remove(treatmentsTab);
		}
	}
	
	public void setPatientView(HealthSystem system, ZoomLevel level){
		patientViewController.setPatientView(system, level);
	}
	
	public static GridPane loadController(Object controller, String panelName) {
		FXMLLoader loader = new FXMLLoader(HealthCenterApplication.class.getResource(panelName));
		loader.setController(controller);
		try {
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
