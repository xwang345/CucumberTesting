package setpDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.*;

public class Steps {
	public WebDriver driver;
	
	// when test cases fails then take screenshots
	 @After
	  public void embedScreenshot(Scenario scenario) {
	    if (scenario.isFailed()) {
	      try {
	        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	        scenario.embed(screenshot, "image/png");
	      } catch (WebDriverException wde) {
	        System.err.println(wde.getMessage());
	      } catch (ClassCastException cce) {
	        cce.printStackTrace();
	      }
	    }
	  }
	
	@Given("^Open the Chrome and start application$")
	public void open_the_chrome_and_start_application() throws Throwable
	{
		System.out.println("Open the Chrome and start application");
		System.setProperty("webdriver.chrome.driver", "./chromedriver");
	}
	
	@When("^Find restaurants near (.*)$")
	public void find_restaurants_near_by(String postCode) throws Throwable
	{
		System.out.println("Find restaurants near" + postCode);
		
		// Go to http://www.just-eat.co.uk/ web-site
		driver = new ChromeDriver();
		driver.get("http://www.just-eat.co.uk/");
		
		// Click input area and sendkey as "AR51 1AA"
		driver.findElement(By.className("Form_c-search-label_cVBKJ")).click();
		driver.findElement(By.className("Form_c-search-label_cVBKJ")).sendKeys(postCode);
		
		// Click button to search
		driver.findElement(By.cssSelector("[data-test-id='find-restaurants-button']")).click();
		
	}
	
	@Then("^Page location area should be (.*)$")
	public void page_location_area_should_be(String postCode)
	{
		System.out.println("Page location area should be" + postCode);
		// Verfiy the location is getting correctly
		String myLocaltion = driver.findElement(By.className("c-locationHeader-title")).getText();
		String[] myLocationCode = myLocaltion.split("[,]",0);
		
		Assert.assertEquals(myLocationCode[0], postCode);
	}
	
	
	@And("^Close browser$")
	public void click_top_restaurants() throws Throwable
	{
		System.out.println("Close browser");
		driver.close();
		driver.quit();
	}
}
