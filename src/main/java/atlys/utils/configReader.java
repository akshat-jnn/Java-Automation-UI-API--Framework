package atlys.utils;

import java.io.*;
import java.util.*;

public class configReader {
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties");
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
