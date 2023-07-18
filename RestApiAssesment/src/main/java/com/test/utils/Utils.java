package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.test.constants.SourcePath;

public class Utils {


	public static String getConfigProperty (String key) throws IOException {
		Properties propFile = new Properties();
		String propertyFilePath=SourcePath.CONFIG_PROPERTIES_PATH;
		FileInputStream stream = new FileInputStream(propertyFilePath);
		propFile.load(stream);
		String value = propFile.getProperty(key);
		System.out.println("Returning value = " +value);
		return value;
		}
	}
