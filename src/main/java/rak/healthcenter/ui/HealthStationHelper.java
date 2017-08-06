package rak.healthcenter.ui;

import java.util.List;

import rak.healthcenter.controllers.MainMenuController;
import rak.healthcenter.controllers.SymptomController;
import rak.healthcenter.model.Condition;
import rak.healthcenter.model.HealthStation;
import rak.healthcenter.model.Patient;
import rak.healthcenter.model.Symptom;
import rak.healthcenter.model.Tool;
import rak.healthcenter.model.Treatment;
import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.model.enums.Location;
import rak.healthcenter.model.enums.ZoomLevel;
import rak.healthcenter.parsers.ConditionParser;
import rak.healthcenter.parsers.SymptomParser;
import rak.healthcenter.parsers.ToolParser;
import rak.healthcenter.parsers.TreatmentParser;

public class HealthStationHelper {
	private MainMenuController mainMenuController;
	private HealthStation healthStation = new HealthStation();
	private Patient patient = new Patient();
	private ConditionParser conditionParser = new ConditionParser();
	private SymptomParser symptomParser = new SymptomParser();
	private ToolParser toolParser = new ToolParser();
	private TreatmentParser treatmentParser = new TreatmentParser();
	
	private HealthSystem currentSystem = HealthSystem.NONE;
	private ZoomLevel currentZoom = ZoomLevel.NAKED_EYE;
	
	 public HealthStationHelper(){
		 parseResources();
		 initializePatient();
		 initializeHealthStation();
	 }


	private void initializePatient() {
		Condition condition = conditionParser.getCondition("common_cold");
		patient.contractCondition(condition);
	}


	private void initializeHealthStation() {
		healthStation.setPatient(patient);
		
		for (Tool tool : toolParser.getAllTools()){
			if (tool.isDefault()){
				healthStation.addTool(tool);
			}
		}
	}

	private void parseResources() {
		symptomParser.parseSymptoms(); 
		conditionParser.parseConditions(symptomParser);
		treatmentParser.parseTreatments();
		toolParser.parseTools(treatmentParser);
	}

	public void setMainController(MainMenuController controller){
		this.mainMenuController = controller;
	}

	public List<Condition> getAllConditions() {
		return conditionParser.getAllConditions();
	}
	
	public List<Symptom> getAllSymptoms() {
		return symptomParser.getAllSymptoms();
	}
	
	public List<Treatment> getAllTreatments() {
		return treatmentParser.getAllTreatments();
	}
	
	public List<Tool> getAllTools() {
		return toolParser.getAllTools();
	}
	
	public HealthStation getHealthStation(){
		return healthStation;
	}
	
	public void setPatientView(HealthSystem system, ZoomLevel level){
		currentSystem = system;
		currentZoom = level;
		mainMenuController.setPatientView(system, level);
	}

	public void diagnoseSymptoms(Location location) {
		patient.diagnoseSymptoms(currentSystem, location, currentZoom);
		SymptomController.refreshDiagnoseGrid(this);
		mainMenuController.setPatientView(currentSystem, currentZoom);
	}

	public void applyTreatment(Treatment treatment) {
		cureConditions(treatment);
		contractSideEffects(treatment);
		SymptomController.refreshDiagnoseGrid(this);
		mainMenuController.setPatientView(currentSystem, currentZoom);
	}

	private void cureConditions(Treatment treatment) {
		for (String id : treatment.getTreatedConditionIds()){
			Condition condition = conditionParser.getCondition(id);
			if (patient.hasCondition(condition)){
				patient.recoverFromCondition(condition);
			}
		}
	}
	
	private void contractSideEffects(Treatment treatment) {
		for (String id : treatment.getAddedConditionIds()){
			Condition condition = conditionParser.getCondition(id);
			patient.contractCondition(condition);
		}
	}

}
