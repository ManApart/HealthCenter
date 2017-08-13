package rak.healthcenter.ui;

import java.util.List;

import rak.healthcenter.HealthManager;
import rak.healthcenter.controllers.MainMenuController;
import rak.healthcenter.controllers.SymptomController;
import rak.healthcenter.events.ContractConditionEvent;
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
import rak.utility.events.EventDirector;

public class HealthStationHelper {
	private MainMenuController mainMenuController;
	private HealthStation healthStation = new HealthStation();
	private Patient patient = new Patient();
	private ConditionParser conditionParser = new ConditionParser();
	private SymptomParser symptomParser = new SymptomParser();
	private ToolParser toolParser = new ToolParser();
	private TreatmentParser treatmentParser = new TreatmentParser();
	private HealthManager healthManager;

	public HealthStationHelper() {
		healthManager = new HealthManager(conditionParser);
		parseResources();
		initializePatient();
		initializeHealthStation();
	}

	private void parseResources() {
		symptomParser.parseSymptoms();
		conditionParser.parseConditions(symptomParser);
		treatmentParser.parseTreatments();
		toolParser.parseTools(treatmentParser);
	}

	private void initializePatient() {
		Condition condition = conditionParser.getCondition("common_cold");
		EventDirector.postEvent(new ContractConditionEvent(patient, condition));
	}

	private void initializeHealthStation() {
		healthStation.setPatient(patient);
		healthManager.addPatient(patient);

		for (Tool tool : toolParser.getAllTools()) {
			if (tool.isDefault()) {
				healthStation.addTool(tool);
			}
		}
	}

	public void setMainController(MainMenuController controller) {
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

	public HealthStation getHealthStation() {
		return healthStation;
	}

	public void diagnoseSymptoms(HealthSystem system, Location location, ZoomLevel level) {
		healthManager.diagnoseSymptoms(patient, system, location, level);
		SymptomController.refreshDiagnoseGrid(this);
	}
	
	public void applyTreatment(Treatment treatment) {
		healthManager.applyTreatment(patient, treatment);
	}

	public void addRandomConditions(int count) {
		System.out.println("Add " + count + " random condtions TODO");
	}



}
