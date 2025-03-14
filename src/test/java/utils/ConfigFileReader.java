package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader
{
	public static Properties property;
	public static String pathOfProject = System.getProperty("user.dir");
	public static String configFilePath = pathOfProject+"/Config/config.properties";
	
	public static void initializePropertyFile() throws IOException
	{
		property = new Properties();
		
		InputStream inpt = new FileInputStream(configFilePath);
		property.load(inpt);
	}

}
