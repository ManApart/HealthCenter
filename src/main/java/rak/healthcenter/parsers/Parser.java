package rak.healthcenter.parsers;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import rak.healthcenter.HealthCenterApplication;

public class Parser {
	
	public static <E> E[] parseList(Class<E[]> resultClass, String name) {
		try {
			InputStream in = HealthCenterApplication.getResourceAsStream(name);
			ObjectMapper mapper = new ObjectMapper();
			E[] list = mapper.readValue(in, resultClass);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
