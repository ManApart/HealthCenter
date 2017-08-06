package rak.healthcenter.parsers
import rak.healthcenter.model.Condition
import rak.healthcenter.model.Symptom
import spock.lang.Specification

class ParserRunner extends Specification {

	def "run Symptom parser"(){
		given:
			SymptomParser parser = new SymptomParser()
		when:
			Map<String, Symptom> symptoms = parser.parseSymptoms()
		then:
			assert !symptoms.values().isEmpty()
	}
	
	def "run Condition parser"(){
		given:
			ConditionParser parser = new ConditionParser()
		when:
			Map<String, Condition> conditions = parser.parseConditions()
		then:
			assert !conditions.values().isEmpty()
	}
	
}
