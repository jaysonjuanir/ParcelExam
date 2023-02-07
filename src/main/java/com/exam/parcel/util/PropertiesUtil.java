package com.exam.parcel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String RESOURCE_NAME = "parcel.properties";

	public static String getPropertyValue(String key) {
		Properties props = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream resourceStream = loader.getResourceAsStream(RESOURCE_NAME)) {
			props.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return props.getProperty(key);
	}
}
