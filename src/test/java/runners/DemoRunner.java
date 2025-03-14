package runners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.ExtentManager;


@CucumberOptions(
		features = {"src/test/resources/Features"},
		monochrome = true,
		glue = "stepDefinitions",
		tags = "@demotag", 
		plugin = {"pretty","html:target/HTMLReports/report.html"})

public class DemoRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
	
	@BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        ExtentManager.setExtent();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        ExtentManager.flush();
    }
}