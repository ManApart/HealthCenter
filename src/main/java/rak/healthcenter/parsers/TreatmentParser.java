package rak.healthcenter.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rak.healthcenter.model.Treatment;

public class TreatmentParser {
	Map<String, Treatment> treatments;

	public List<Treatment> getAllTreatments(){
		return new ArrayList<>(treatments.values());
	}
	
	public Treatment getTreatment(String id){
		if (treatments.containsKey(id)){
			return treatments.get(id);
		}
		throw new RuntimeException("No treatment for " + id);
	}
	
	public void parseTreatments(){
		String name = "json/Treatments.json";
		Treatment[] treatments = Parser.parseList(Treatment[].class, name);
		this.treatments = mapTreatments(treatments);
	}

	private Map<String, Treatment> mapTreatments(Treatment[] treatments) {
		Map<String, Treatment> treatmentMap = new HashMap<>();
		for (Treatment treatment : treatments){
			treatmentMap.put(treatment.getId(), treatment);
		}
		return treatmentMap;
	}
	
}
