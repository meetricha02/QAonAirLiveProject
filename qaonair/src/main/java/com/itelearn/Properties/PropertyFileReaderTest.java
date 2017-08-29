package com.itelearn.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class PropertyFileReaderTest {
	private static Properties props;
	

	
	static{
			props= new Properties();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream("C:\\Users\\Richa\\Desktop\\Richa\\Itelearn\\ITelearn_liveProject\\gitRepo\\Updated Live Project\\liveProject\\qaonair\\src\\main\\java\\Resources\\dataReading.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				props.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	
	// Returns the data from properties file//
	public static String getProperty(String a){
		return props.getProperty(a);
		
	}
	
	}
	
	

