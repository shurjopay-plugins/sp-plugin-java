package com.shurjomukhi;

import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * A reader to read shurjopay's properties.
 * @author Al - Amin
 * @since 2022-06-13
 */
@Slf4j
public class PropertiesReader {
	
	/** Static field to store PropertiesReader.*/
	private static PropertiesReader instance = null;

	/** Instantiates ShurjopayException with message and exception.*/
	private PropertiesReader() {
	}

	/**
	 * Loads shurjopay's properties from {@code shurjopay.properties} file.
	 * <hr>
	 * <b>Shurjopay's properties:</b>
	 * <ul>
	 * 	<li>Merchant username provided by shurjopay.</li>
	 * 	<li>Merchant password provided by shurjopay.</li>
	 * 	<li>Shurjopay base URL to access end-points.</li>
	 * 	<li>Shurjopay logging file information.</li>
	 * </ul>
	 * @return Shurjopay's properties.
	 */
	public Properties getProperties(){
		try {
			Properties prop = new Properties();
			prop.load(PropertiesReader.class.getClassLoader().getResourceAsStream("shurjopay.properties"));
			return prop;
		} catch (NullPointerException | IOException e) {
			log.error("shurjopay.properties is missing in resource path or resources path is not exist", e);
			return null;
		}
	}

	/**
	 * Creates singleton PropertiesReader instance.
	 * @return the {@link PropertiesReader} instance.
	 */
	public static PropertiesReader instance() {
		if (instance == null) instance = new PropertiesReader();
		return instance;
	}
}
