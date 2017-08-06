package rak.healthcenter.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rak.healthcenter.model.Condition;
import rak.healthcenter.model.Symptom;
import rak.healthcenter.model.enums.Location;

public class ConditionParser {
	Map<String, Condition> conditions;

	public List<Condition> getAllConditions(){
		return new ArrayList<>(conditions.values());
	}
	
	public Condition getCondition(String id, Location location){
		Condition condition = getCondition(id).clone();
		condition.setLocation(location);
		return condition;
	}
	
	private Condition getCondition(String id){
		if (conditions.containsKey(id)){
			return conditions.get(id);
		}
		throw new RuntimeException("No condition for " + id);
	}
	
	public void parseConditions(SymptomParser symptomParser){
		
		String name = "../json/Conditions.json";
		Condition[] conditions = Parser.parseList(Condition[].class, name);
		
		addSymptoms(conditions, symptomParser);
		
		this.conditions = mapConditions(conditions);
	}

	private void addSymptoms(Condition[] conditions, SymptomParser symptomParser) {
		for (Condition condition : conditions){
			for (String symptomId : condition.getSymptomIds()){
				Symptom symptom = symptomParser.getSymptom(symptomId);
				condition.addSymptom(symptom);
			}
		}
	}
	
	private Map<String, Condition> mapConditions(Condition[] conditions) {
		Map<String, Condition> conditionMap = new HashMap<>();
		for (Condition condition : conditions){
			conditionMap.put(condition.getId(), condition);
		}
		return conditionMap;
	}
	
}
