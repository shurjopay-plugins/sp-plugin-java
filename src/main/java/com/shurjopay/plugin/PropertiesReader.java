package com.shurjopay.plugin;

import java.util.Properties;

/**
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
public class PropertiesReader {
	
	private static PropertiesReader instance = null;

	private PropertiesReader() {
	}

	public Properties getProperties(){
		try {
			Properties prop = new Properties();
			prop.load(PropertiesReader.class.getClassLoader().getResourceAsStream("shurjopay.properties"));
			return prop;
		} catch (Exception e) {
			throw new RuntimeException("shurjopay.properties is missing in resource path or Resources path is not exist");
		}
	}

	public static PropertiesReader instance() {
		if (instance == null) instance = new PropertiesReader();
		return instance;
	}
}
