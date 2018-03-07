/*
 * HSR Hochschule fuer Technik Rapperswil
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * 
 * Thomas Briner, thomas.briner@gmail.com
 */
package ch.testing.selenium.helloworld;

import ch.testing.selenium.weekenddiscount.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * The Class HelloWorldGoogleExample. This class is provided as first example on
 * http://code.google.com/p/selenium/wiki/GettingStarted
 */
public class HelloWorldGoogleExample implements Constants{

	private WebDriver driver;

	@BeforeEach
	public void setup() {
		// Create a new instance of the html unit driver
		//driver = new HtmlUnitDriver();

		// Uncomment this to switch to chromedriver
		System.setProperty("webdriver.chrome.driver",
		"lib/chromedriver");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@Test
	public void testGoogleSearchPage() {


		// And now use this to visit Google
		driver.get("http://www.google.com");

		// Find the text input element by its name
		WebElement element = driver.findElement(By.name("q"));

		// Enter something to search for
		element.sendKeys("Cheese!");

		// Now submit the form. WebDriver will find the form for us from the
		// element
		element.submit();

		// Check the title of the page
		System.out.println("Page title is: " + driver.getTitle());
	}
}