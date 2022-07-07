package com.allaboutspring.demo.spel;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsingValueWithSpel {
	
	//we can set default value if property is not present
	@Value("${some.property:42}")
	private int defaultInt;
	
	@Value("${some.property:}")
	private String emptyString;
	
	//using SPEL
	//Spel expressin has structure #{expression}
	@Value("#{1+2}")
	private int addition;
	
	@Value("#{true && false}")
	private boolean logical;

//	private static HashMap<String, String> map = new HashMap<String, String>();
//
//	@Value("#{map['key']}")
//	private boolean hashmapValue;
	
	public void printValues() {
		System.out.println(defaultInt);
		System.out.println(emptyString);
		System.out.println(addition);
		System.out.println(logical);
	}
}
