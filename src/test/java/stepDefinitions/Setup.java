package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigFileReader;
import utils.ExtentManager;

public class Setup {
	
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<>();
	static public Logger log;
	static String ScenarioName;
	static String JsonFileName;
	public static ThreadLocal<Scenario> scenario = new ThreadLocal<>();
	public static ThreadLocal<String> scName = new ThreadLocal<>();
	public static ThreadLocal<String> jsFileName = new ThreadLocal<>();
	public static String pathOfProject = System.getProperty("user.dir");
	
	public static void setDriver(WebDriver driver)
	{
		dr.set(driver);
	}
	
	public static WebDriver getDriver()
	{
		return dr.get();
	}
	
	public static String getScenarioName() {
		return scName.get();
	}

	public static void setScenarioName(String scenarioName) {
		scName.set(scenarioName);
	}

	public static String getJsonFileName() {
		return jsFileName.get();
	}

	public static void setJsonFileName(String jsonFileName) {
		jsFileName.set(jsonFileName);
	}
	
	public static void setTheDataFromJson(String ScenarioName, String JsonFileName) throws Exception
	{
		setScenarioName(ScenarioName);
		setJsonFileName(JsonFileName);
	}

	@Before
	public static void driverSetup(Scenario scenarioName) throws IOException
	{
		ConfigFileReader.initializePropertyFile();
		log=LogManager.getLogger("SeleniumLogger");
		
		scenario.set(scenarioName);
		ExtentManager.createTest(scenarioName.getName());
		
		if(ConfigFileReader.property.getProperty("toggle").equalsIgnoreCase("off"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			System.out.println("Starting test on Local");
			WebDriverManager.chromedriver().setup();
			if(ConfigFileReader.property.getProperty("headless").equalsIgnoreCase("true"))
			{
				options.addArguments("headless");
				System.out.println("Running test in Headless mode on Local");
			}
			setDriver(new ChromeDriver(options));
		}
		
		else if(ConfigFileReader.property.getProperty("toggle").equalsIgnoreCase("on"))
		{
			//Provide below details from Saucelabs 
			
			System.out.println("Starting test on Saucelabs");
			MutableCapabilities sauceOptions = new MutableCapabilities();
	        sauceOptions.setCapability("username", "");
	        sauceOptions.setCapability("access_key", "");
	        sauceOptions.setCapability("tunnelIdentifier", "");
	        sauceOptions.setCapability("name","");
	        sauceOptions.setCapability("browserVersion", "latest");
	        sauceOptions.setCapability("videoUploadOnPass", false);
	        sauceOptions.setCapability("maxDuration", 3600);

	        ChromeOptions options = new ChromeOptions();
	        options.setCapability("sauce:options", sauceOptions);
	        URL url = new URL("");

	        setDriver(new RemoteWebDriver(url, options));
		}
		
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		getDriver().manage().window().maximize();
		
	}
	
	public void extentReportUpdateResult(String status, String msg) throws Exception
	{
		try
		{
			if (status.equalsIgnoreCase("pass"))
			{
				String base64Screenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
				ExtentManager.getTest().pass(msg, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
			}
			else if (status.equalsIgnoreCase("fail"))
			{
				String base64Screenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
				ExtentManager.getTest().fail(msg, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occurred while updating result: " + ex.getMessage());
		}
	}
	
	public static JavascriptExecutor getJS() {
        return (JavascriptExecutor) getDriver();
    }
	
	public static void launchUrl(String url)
	{
		getDriver().get(url);
	}
	
	public static void scrollToElement(WebElement element)
	{
        log.info("Scrolling to the Element " + element);
        try
        {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            forceSleep(2);
        } catch (Exception e) {
            log.error("Exception occurred : " + e.getMessage());
        }
    }
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public static boolean fluentWaitElementClickable(WebElement ele)
	{
        boolean flag = false;
        try
        {
            FluentWait wait = new FluentWait(getDriver());
            wait.withTimeout(Duration.ofSeconds(60));
            wait.pollingEvery(Duration.ofMillis(500));
            List list = new ArrayList();
            list.add(NoSuchElementException.class);
            list.add(NoSuchFileException.class);
            list.add(StaleElementReferenceException.class);
            wait.ignoreAll(list);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            flag = true;
            return flag;
        } catch (Exception e) {
            log.error("Exception Occured : " + e.getMessage());
            return flag;
        }

    }
	
	public static void jsClick(WebElement element)
	{
		try
		{
			fluentWaitElementClickable(element);
			log.info("Clicking on :" + element.getText());
			scrollToElement(element);
			getJS().executeScript("arguments[0].click();", element);
		} catch (Exception e)
		{
			log.error("Exception Occurred : " + e.getMessage());
		}

    }
	
	public static void hoverAndClick(WebElement locatorToHoverOn, WebElement locatorToClickOn)
	{
		try
		{
			Actions hoverAndClickAction = new Actions(getDriver());
			hoverAndClickAction.moveToElement(locatorToHoverOn).build().perform();
			actionClick(locatorToClickOn);
		} catch (NoSuchElementException e)
		{
			log.error("Element not found to hover and click" + e.getMessage());
		} catch (Exception e) {
			log.error("Exception Occurred : " + e.getMessage());
		}
	}
	
	public static void actionClick(WebElement element)
	{
		try
		{
			Actions act = new Actions(getDriver());
			act.click(element).build().perform();
		} catch (NoSuchElementException e) {
			log.error("Exception Occurred : " + e.getMessage());
		}
	}
	
	public static String captureScreenshot(String screenshotname) throws IOException
	{
		File fs = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String screenshotFilePath = pathOfProject+"/TestResultScreenShots/" + screenshotname + ".png";
		FileUtils.copyFile(fs, new File(screenshotFilePath));
		log.debug("Screenshot Taken");
		return screenshotFilePath;
	}
	
	public static void handleAlertMsg(String action)
	{
		try
		{
			if (action.equalsIgnoreCase("Accept"))
			{
				getDriver().switchTo().alert().accept();
			}
			else if (action.equalsIgnoreCase("dismiss"))
			{
				getDriver().switchTo().alert().dismiss();
			}

		} catch (Exception e)
		{
			log.error("Exception Occurred : " + e.getMessage());
		}
	}
	
	public static void forceSleep(long seconds) throws InterruptedException
	{
		Thread.sleep(seconds * 1000);
	}
	
	public static void moveToElement(WebElement ele)
	{
		try
		{
			Actions actions = new Actions(getDriver());
			actions.moveToElement(ele);
			actions.perform();

		} catch (NoSuchElementException e)
		{
			log.error("Exception Occurred : " + e.getMessage());
		}
	}
	
	public static void switchToWindow()
	{
		try
		{
			String currentWinHandle = getDriver().getWindowHandle();
			Set<String> allWinHandles = getDriver().getWindowHandles();
			log.debug(allWinHandles.size());
			for (String iterator : allWinHandles)
			{
				if (!iterator.equals(currentWinHandle))
				{
					getDriver().switchTo().window(iterator);
				}
			}
		} catch (NoSuchElementException e)
		{
			log.error("Exception Occurred : " + e.getMessage());
		}
	}
	
	public static void switchToWindowByIndex(int windowIndex) throws Exception
	{
		try
		{
			boolean result = false;
			Object[] windowNames = getDriver().getWindowHandles().toArray();
			getDriver().switchTo().window(windowNames[windowIndex].toString());
			result = true;
			if (!result)
				throw (new Exception("Window Not Found"));
		} catch (Exception e)
		{
			log.error("Exception Occurred : " + e.getMessage());
		}
	}
	
	public static void selectByVisibleText(WebElement locatorValue, String Value)
	{
		try
		{
			Select sel = new Select(locatorValue);
			sel.selectByVisibleText(Value);

		} catch (NoSuchElementException e)
		{
			log.error("No element found to select" + e.getMessage());
		} catch (Exception e)
		{
			log.error("Exception Occurred : " + e.getMessage());
		}
	}
	
	public static boolean explicitWait(WebElement ele, String waitCondition, long waitSeconds)
	{
		boolean result = false;
		try 
		{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitSeconds));

			switch (waitCondition) 
			{
				case "Invisibility":
					wait.until(ExpectedConditions.invisibilityOf(ele));
					break;
				case "Visibility":
					wait.until(ExpectedConditions.visibilityOf(ele));
					break;
				case "Clickable":
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					break;
				default:
			}
			result = true;
		} catch (Exception e)
		{
			log.error("Error while explicit wait - " + e.getMessage());
		}
		return result;
	}
	
	@After
	public static void tearDown()
	{
		Scenario scenarioName = scenario.get();
		if (scenarioName.isFailed())
		{
            ExtentManager.getTest().fail("Scenario failed: " + scenarioName.getName());
        } else
        {
            ExtentManager.getTest().pass("Scenario passed: " + scenarioName.getName());
        }
		scenario.remove();
		
//		getDriver().close();
		getDriver().quit();
	}

}
