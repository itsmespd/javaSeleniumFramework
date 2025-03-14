package stepDefinitions;


import actions.DemoActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.ConfigFileReader;

public class DemoSteps
{
	DemoActions actions= new DemoActions();
	
	@Given("I set the data for {string} from {string} JSON File")
	public void i_set_the_data_from_json(String ScenarioName, String JsonFileName) throws Exception {
		
		Setup.setTheDataFromJson(ScenarioName,JsonFileName);  
	}
	
	@Given("I launch {string} portal")
	public void i_launch_portal(String url) throws Exception {
		
		Setup.launchUrl(ConfigFileReader.property.getProperty(url));  
	}
	
	@And("I enter the {string} in Google")
	public void i_enter_the_search_query_google(String query) throws Exception {
		
		actions.enterSearchQueryGoogle(query);  
	}
	
	@Then("I click on search button in Google")
	public void i_click_on_search_button_google() throws Exception
	{ 
	    actions.clickSearchButtonGoogle();
	}
	
	@And("I enter the {string} in Yahoo")
	public void i_enter_the_search_query_yahoo(String query) throws Exception {
		
		actions.enterSearchQueryYahoo(query);  
	}
	
	@Then("I click on search button in Yahoo")
	public void i_click_on_search_button_yahoo() throws Exception
	{ 
	    actions.clickSearchButtonYahoo();
	}
}