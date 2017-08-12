package rak.healthcenter.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rak.healthcenter.model.Symptom;

public class SymptomParser {
	private Map<String, Symptom> symptoms;
	
	public List<Symptom> getAllSymptoms(){
		return new ArrayList<>(symptoms.values());
	}
	
	public Symptom getSymptom(String id){
		if (symptoms.containsKey(id)){
			return symptoms.get(id);
		}
		throw new RuntimeException("No symptom for " + id);
	}

	public void parseSymptoms(){
		String name = "json/Symptoms.json";
		Symptom[] symptoms = Parser.parseList(Symptom[].class, name);
		this.symptoms = mapSymptoms(symptoms);
	}

	private Map<String, Symptom> mapSymptoms(Symptom[] symptoms) {
		Map<String, Symptom> symptomMap = new HashMap<>();
		for (Symptom symptom : symptoms){
			symptomMap.put(symptom.getId(), symptom);
		}
		return symptomMap;
	}
	
}
