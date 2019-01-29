package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Config {
	Properties config;
	public void readProperties() {
		config = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			config.load(fis);
			System.out.println(config.getProperty("dbpassword"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Config c = new Config();
		c.readProperties();
	}
}