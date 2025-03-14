package actions;



import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;

import locators.DemoLocators;
import stepDefinitions.Setup;
import utils.JsonDataReader;

public class DemoActions extends Setup
{
	
	DemoLocators locate= new DemoLocators();
	
	
	public void enterSearchQueryGoogle(String query) throws Exception
	{
		try
		{
			Assert.assertTrue(locate.searchBar.isDisplayed(),"Google Search Bar is not displayed");
			extentReportUpdateResult("pass","Google Search Bar is displayed");
			log.info("Google Search Bar is displayed");
			explicitWait(locate.searchBar,"Clickable",5);
			locate.searchBar.click();
			locate.searchBar.clear();
			
			log.info("Reading data for scenario: "+getScenarioName()+" from JSON File: "+getJsonFileName());
			JSONObject scenarioData = JsonDataReader.readJsonData(getJsonFileName(),getScenarioName());
			String username = scenarioData.getJSONObject(query).getString("username");
			locate.searchBar.sendKeys(username);
			extentReportUpdateResult("pass",username+ " is entered in the Searchbar");
			
		} catch (Exception e)
		{
			log.error("Exception occurred : " + e.getMessage());
			extentReportUpdateResult("fail","Google Search Bar is not displayed: "+e.getMessage());
			throw(e);
		}
	}
	
	public void clickSearchButtonGoogle() throws Exception
	{
		try
		{
//			locate.searchBtn.click();
			locate.searchBar.sendKeys(Keys.ENTER);
			extentReportUpdateResult("pass","Search Enter Key clicked");
			log.info("Search Enter Key clicked");
			
//			Assert.assertTrue(locate.welcomeHeader.isDisplayed(),"Login Failed. Configuartion Header not displayed");
//			((JavascriptExecutor) Setup.getDriver()).executeScript("arguments[0].scrollIntoView(true);", locate.addBtn.get(0));
//			getDriver().switchTo().alert().getText().contains("Do you really want to replace it?");
//			locate.username.clear();
//			locate.username.sendKeys(EncodeDecode.decrypt(configFileReader.property.getProperty("username")));
//			locate.password.clear();
//			locate.password.sendKeys(EncodeDecode.decrypt(configFileReader.property.getProperty("password")));
					
		} catch (Exception e)
		{
			log.error("Exception occurred : " + e.getMessage());
			extentReportUpdateResult("fail","Google Search Enter Key is not clicked: "+e.getMessage());
			throw(e);
		}
	}
	
	public void enterSearchQueryYahoo(String query) throws Exception
	{
		try
		{
			Assert.assertTrue(locate.searchBarYahoo.isDisplayed(),"Yahoo Search Bar is not displayed");
			extentReportUpdateResult("pass","Yahoo Search Bar is displayed");
			log.info("Yahoo Search Bar is displayed");
			locate.searchBarYahoo.click();
			locate.searchBarYahoo.clear();
			
			log.info("Reading data for scenario: "+getScenarioName()+" from JSON File: "+getJsonFileName());
			JSONObject scenarioData = JsonDataReader.readJsonData(getJsonFileName(),getScenarioName());
			locate.searchBarYahoo.sendKeys(scenarioData.getString(query));
			extentReportUpdateResult("pass",scenarioData.getString(query)+ " is entered in the Searchbar");
			
		} catch (Exception e)
		{
			captureScreenshot("Error Screenshot");
			log.error("Exception occurred : " + e.getMessage());
			extentReportUpdateResult("fail","Yahoo Search Query is not entered: "+e.getMessage());
			throw(e);
		}
	}
	
	public void clickSearchButtonYahoo() throws Exception
	{
		try
		{
			explicitWait(locate.searchBtnYahoo,"Clickable",5);
			locate.searchBtnYahoo.click();
			extentReportUpdateResult("pass","Yahoo Search Button clicked");
			log.info("Yahoo Search Button clicked");
			
		} catch (Exception e)
		{
			log.error("Exception occurred : " + e.getMessage());
			extentReportUpdateResult("fail","Yahoo Search Button is not clicked: "+e.getMessage());
			throw(e);
		}
	}
}
	
