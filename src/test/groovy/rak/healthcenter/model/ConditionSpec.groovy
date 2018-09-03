package rak.healthcenter.model

import spock.lang.Specification

class ConditionSpec extends Specification{
	
	def "test equals"(){
		when:
			Condition a = new Condition(id: id)
			Condition b = new Condition(id: id2)
		then:
			assert a.equals(b) == expected
			assert b.equals(a) == expected
		where:
		id			|	id2			|	expected
		"123asdf"	|	"123asdf"	|	true
		"123asdf"	|	"adcasdf"	|	false
		null		|	"adcasdf"	|	false
		"adcasdf"	|	null		|	false
		null		|	null		|	true
	}
	
	def "test equals works with clone"(){
		given:
			String id = "123asdf"
		when:
			Condition a = new Condition(id: id)
			Condition b = a.clone()
		then:
			assert a.equals(b)
			assert b.equals(a)
	}
	
	def "test equals handles nulls"(){
				
		expect:
			assert a.equals(b) == expected
			assert b.equals(a) == expected
		where:
		a							|	b							|	expected
		new Condition(id: "123")	|	new Condition(id: "123")	|	true
		null						|	new Condition(id: "123")	|	false
		new Condition(id: "123")	|	null						|	false
	}

}
