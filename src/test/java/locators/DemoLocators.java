package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.Setup;


public class DemoLocators{
	
	WebDriver driver= Setup.getDriver();

	@FindBy(xpath="(//input[@value='Google Search'])[2]")
	public WebElement searchBtn;
	
	@FindBy(xpath="//button[@id='ybar-search']")
	public WebElement searchBtnYahoo;

	@FindBy(name="q")
	public WebElement searchBar;
	
	@FindBy(name="p")
	public WebElement searchBarYahoo;
	
//	@FindBy(xpath="//button[contains(text(),'Save Changes')]")
//	public List<WebElement> saveChanges;
	
	public DemoLocators()
	{
		PageFactory.initElements(driver, this);
	}

}