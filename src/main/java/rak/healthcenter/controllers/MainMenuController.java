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
import rak.healthcenter.events.PatientStateChangedEvent;
import rak.healthcenter.events.ToolSelectEvent;
import rak.healthcenter.listeners.ui.PatientStateListener;
import rak.healthcenter.listeners.ui.ToolSelectListener;
import rak.healthcenter.ui.HealthStationHelper;
import rak.healthcenter.ui.InputKey;
import rak.utility.events.EventDirector;

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
	@FXML private GridPane mainOptions;
	
	@FXML private Pane patientView;
	
	@FXML private TabPane mainTabs;
	@FXML private TabPane diagnoseTabs;
	@FXML private Tab patientInfoTab;
	@FXML private Tab conditionsTab;
	@FXML private Tab symptomsTab;
	@FXML private Tab toolsTab;
	@FXML private Tab treatmentsTab;
	
	@FXML private MainOptionsController mainOptionsController;
	
	private boolean showDebugTabs = true;
	private HealthStationHelper healthStationHelper = new HealthStationHelper();
	PatientViewController patientViewController;
	
	public MainMenuController(){
		EventDirector.registerListener(new PatientStateListener(this));
		EventDirector.registerListener(new ToolSelectListener(this));
	}
	
	public void initialize(){
		healthStationHelper.setMainController(this);
		
		createPatientPanels();
		createNonPatientPanels();
		hideDebugTabs();
	}

	public void createPatientPanels() {
		SymptomController.createDiagnoseGrid(diagnoseGrid, healthStationHelper);
		TreatmentController.createTreatGrid(treatGrid, healthStationHelper);
		patientViewController = PatientViewController.createGrid(patientView, healthStationHelper);
		ConditionController.createPatientInfoGrid(patientInfoGrid, healthStationHelper);
	}

	public void createNonPatientPanels() {
		ToolController.createInspectGrid(inspectGrid, healthStationHelper);
		ConditionController.createGrid(conditionsGrid, healthStationHelper);
		SymptomController.createGrid(symptomsGrid, healthStationHelper);
		TreatmentController.createGrid(treatmentsGrid, healthStationHelper);
		ToolController.createGrid(toolsGrid, healthStationHelper);
		mainOptionsController.setHealthStationHelper(healthStationHelper);
	}

	@FXML
	public void onKeyTyped(KeyEvent event){
		if (InputKey.DEBUG.isKey(event.getCharacter())){
			toggleDebugTabs();
		}
	}
	
	private void hideDebugTabs() {
		if (showDebugTabs){
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

	public void handle(PatientStateChangedEvent event) {
		createPatientPanels();
	}

	public void handle(ToolSelectEvent event) {
		patientViewController.setPatientView(event.getTool().getAffectedSystem(), event.getTool().getAffectedLevel());		
	}
	
}
