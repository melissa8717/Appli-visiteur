package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Config {
	Properties config;

	public Config() {
		config = new Properties();
		try {
			config.load(new FileInputStream(new File("config.properties")));
			//System.out.println("toto");
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public String getProp(String prop) {
		return config.getProperty(prop).toString();
	}
}