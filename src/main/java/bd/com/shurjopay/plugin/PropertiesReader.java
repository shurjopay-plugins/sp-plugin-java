package bd.com.shurjopay.plugin;

import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is used to read shurjopay's properties.
 * @author Al - Amin
 * @since 2022-06-13
 */
@Slf4j
public class PropertiesReader {
	
	/**
	 * This class instance to fetch single instance.
	 */
	private static PropertiesReader instance = null;

	/**
	 * Private constructor to prohibit creating class instance from outside.
	 */
	private PropertiesReader() {
	}

	/**
	 * This method is used to provide shurjopay properties.
	 * @return properties in shurjopay.properties resource.
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
	 * This method is used to get class instance.
	 * @return this.class instance.
	 */
	public static PropertiesReader instance() {
		if (instance == null) instance = new PropertiesReader();
		return instance;
	}
}
