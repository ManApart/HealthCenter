package rak.healthcenter.parsers

import rak.healthcenter.HealthCenterApplication
import rak.healthcenter.model.Condition
import rak.healthcenter.model.Symptom
import rak.utility.ResourceLoader
import spock.lang.Specification

class ParserRunner extends Specification {

    def setupSpec() {
        ResourceLoader.setRootClass(HealthCenterApplication.class)
    }

    def "run Symptom parser"() {
        given:
            SymptomParser parser = new SymptomParser()
        when:
            parser.parseSymptoms()
            List<Symptom> symptoms = parser.getAllSymptoms()
        then:
            assert !symptoms.isEmpty()
    }

    def "run Condition parser"() {
        given:
            ConditionParser parser = new ConditionParser()
            SymptomParser symptomParser = new SymptomParser()
            symptomParser.parseSymptoms()
        when:
            parser.parseConditions(symptomParser)
            List<Condition> conditions = parser.getAllConditions()
        then:
            assert !conditions.isEmpty()
    }

}
