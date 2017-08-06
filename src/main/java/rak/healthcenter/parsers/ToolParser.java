package rak.healthcenter.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rak.healthcenter.model.Tool;
import rak.healthcenter.model.Treatment;

public class ToolParser {
	Map<String, Tool> tools;

	public List<Tool> getAllTools(){
		return new ArrayList<>(tools.values());
	}
	
	public Tool getTool(String id){
		if (tools.containsKey(id)){
			return tools.get(id);
		}
		throw new RuntimeException("No tool for " + id);
	}
	
	public void parseTools(TreatmentParser treatmentParser){
		
		String name = "../json/Tools.json";
		Tool[] tools = Parser.parseList(Tool[].class, name);
		
		addTreatments(tools, treatmentParser);
		
		this.tools = mapTools(tools);
	}

	private void addTreatments(Tool[] tools, TreatmentParser treatmentParser) {
		for (Tool tool : tools){
			for (String id : tool.getTreatmentIds()){
				Treatment treatment = treatmentParser.getTreatment(id);
				tool.addTreatment(treatment);
			}
		}
	}
	
	private Map<String, Tool> mapTools(Tool[] tools) {
		Map<String, Tool> toolMap = new HashMap<>();
		for (Tool tool : tools){
			toolMap.put(tool.getId(), tool);
		}
		return toolMap;
	}
	
}
