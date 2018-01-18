/*
 * HSR Hochschule fuer Technik Rapperswil
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * 
 * Thomas Briner, thomas.briner@gmail.com
 */
package ch.testing.selenium.helloworld;

import java.util.concurrent.TimeUnit;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ch.testing.selenium.weekenddiscount.pageobjects.HomePage;
import ch.testing.selenium.weekenddiscount.pageobjects.HotSaucesPage;

/**
 * The Class HelloWorldHeatClinic. This class can be used for first experiments
 * with selenium against the system under test.
 * 
 * Make sure the system under test is running! (Ant View --> site -->
 * jetty-demo-no-db)
 */
public class HelloWorldHeatClinic {

	private WebDriver driver;


	@FindBy(xpath = "//nav//a[contains(@href,'/hot-sauces')]")
	private WebElement hotSaucesButton1;
	
	
	public HotSaucesPage jumpToHotSauces() {
		hotSaucesButton1.click();
		return PageFactory.initElements(driver, HotSaucesPage.class);
	}
	
	@Before
	public void setup() {
		//driver = new HtmlUnitDriver();

		// Uncomment this to switch to chromedriver

		 System.setProperty("webdriver.chrome.driver",
		 "C:/SWTesting/tools/chromedriver/chromedriver.exe");
		 driver = new ChromeDriver();

	     driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}


	@Test
	public void testPriceOfSauceGreenGhost() throws InterruptedException {

		driver.get("localhost:8080");

		// check if the home page is loaded
		Assert.assertThat("Should be home page of heat clinic", driver.getTitle(),
				Is.is("Broadleaf Demo - Heat Clinic"));

		// now go to "Hot Sauces"
		driver.findElement(By.xpath("//nav//a[contains(@href,'/hot-sauces')]")).click();

		// and check the price of the green ghost sauce: should be $11.99
		Assert.assertThat("$11.99", driver.getTitle(),
				Is.is("Broadleaf Demo - Heat Clinic"));
		

	}

}
