package com.shurjopay.plugin;

import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
@Slf4j
public class PropertiesReader {
	
	private static PropertiesReader instance = null;

	private PropertiesReader() {
	}

	public Properties getProperties(){
		try {
			Properties prop = new Properties();
			prop.load(PropertiesReader.class.getClassLoader().getResourceAsStream("shurjopay.properties"));
			return prop;
		} catch (IOException e) {
			log.error("shurjopay.properties is missing in resource path or resources path is not exist", e);
			return null;
		}
	}

	public static PropertiesReader instance() {
		if (instance == null) instance = new PropertiesReader();
		return instance;
	}
}
