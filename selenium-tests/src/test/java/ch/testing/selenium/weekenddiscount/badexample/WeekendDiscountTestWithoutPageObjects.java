/*
 * HSR Hochschule fuer Technik Rapperswil
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * 
 * Thomas Briner, thomas.briner@gmail.com
 */
package ch.testing.selenium.weekenddiscount.badexample;

import static org.hamcrest.core.Is.is;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ch.testing.selenium.weekenddiscount.Constants;

/**
 * The Class WeekendDiscountTestWithoutPageObjects. This is the bad example how
 * the tests would look like without the page object pattern: a messy bunch of
 * implementation details spread over all tests, impossible to maintain
 */
public class WeekendDiscountTestWithoutPageObjects implements Constants{

	private WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
	}

	@Test
	public void testAddToCart() throws InterruptedException {

		driver.get(BASE_URL);

		WebElement nofItemsInCart = driver
				.findElement(By
						.xpath("//*[@id='cartLink']//*[@class='headerCartItemsCount']"));
		Assert.assertThat("Should be 0 items in cart",  nofItemsInCart.getText(), is("0"));

		WebElement element = driver.findElement(By
				.xpath("//nav//a[contains(@href,'/hot-sauces')]"));

		element.click();

		element = driver
				.findElement(By
						.xpath("//a[contains(@href,'/hot-sauces/day_of_the_dead_habanero_hot_sauce')]"));

		element.click();

		element = driver.findElement(By.id("mainProductToCart"));

		element.click();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.pollingEvery(1, TimeUnit.SECONDS);
		WebElement newNofItemsInCart = driver
				.findElement(By
						.xpath("//*[@id='cartLink']//*[@class='headerCartItemsCount']"));
		wait.until(ExpectedConditions.textToBePresentInElement(
				newNofItemsInCart, "1"));

		Assert.assertThat("Now 1 item should be in cart",  newNofItemsInCart.getText(), is("1"));
	}
}
