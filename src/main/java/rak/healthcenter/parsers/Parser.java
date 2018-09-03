package rak.healthcenter.parsers;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import rak.utility.ResourceLoader;

public class Parser {
	
	public static <E> E[] parseList(Class<E[]> resultClass, String name) {
		try {
			InputStream in = ResourceLoader.getResourceAsStream(name);
			ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(in, resultClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
