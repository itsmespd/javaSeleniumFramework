package utils;

import org.json.JSONObject;

import stepDefinitions.Setup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDataReader extends Setup
{
	
	public static String pathOfProject = System.getProperty("user.dir");
	public static String pathToJson = pathOfProject+"/src/test/resources/TestData/";
	
	
    public static JSONObject readJsonData(String fileName, String scenarioName)
    {
        String content=null;
        JSONObject jsonObject=null;
        JSONObject scenarioData=null;
        
        try
        {
        	String env= ConfigFileReader.property.getProperty("environment");
        	
        	// Read the JSON file content based on environment passed in config file
        	switch(env)
        	{
        		case "DemoEnv":  content = new String(Files.readAllBytes(Paths.get(pathToJson+"/"+env+"/"+fileName)));
                				 jsonObject = new JSONObject(content);
                				 break;
                				 
        		case "QA":  	 content = new String(Files.readAllBytes(Paths.get(pathToJson+"/"+env+"/"+fileName)));
				 				 jsonObject = new JSONObject(content);
				 				 break;
				 				 
        		case "UAT":  	 content = new String(Files.readAllBytes(Paths.get(pathToJson+"/"+env+"/"+fileName)));
				 				 jsonObject = new JSONObject(content);
				 				 break;
        	}

            // Check if scenarioName exists and extract its contents
            if (jsonObject.has(scenarioName))
            {
                scenarioData = jsonObject.getJSONObject(scenarioName);
                
//                for (String key : scenarioData.keySet())
//                {
//                    resultMap.put(key, scenarioData.getString(key));
//                }
            }
            else
            {
            	log.error(scenarioName + " not found in the supplied JSON file.");
            }
        } catch (IOException e)
        {
        	log.error("Exception occurred : " + e.getMessage());
        }
        return scenarioData;
    }
}
