/*
 * HSR Hochschule fuer Technik Rapperswil
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * 
 * Thomas Briner, thomas.briner@gmail.com
 */
package ch.testing.selenium.weekenddiscount.tests;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ch.testing.selenium.weekenddiscount.Constants;
import ch.testing.selenium.weekenddiscount.executionrule.ScreenshotOnFailureRule;
import ch.testing.selenium.weekenddiscount.executionrule.WebDriverKeeper;
import ch.testing.selenium.weekenddiscount.pageobjects.CartPage;
import ch.testing.selenium.weekenddiscount.pageobjects.HomePage;
import ch.testing.selenium.weekenddiscount.pageobjects.HotSaucesPage;
import ch.testing.selenium.weekenddiscount.pageobjects.SauceDetailPage;
import ch.testing.selenium.weekenddiscount.util.DBUtil;
import ch.testing.selenium.weekenddiscount.util.DateFactory;

/**
 * The Class HeatClinicAcceptanceTests. In this class the acceptance Tests for
 * the weekend discount features are implemented.
 */
public class WeekendDiscountAcceptanceTests implements Constants {

	private static final Log LOG = LogFactory
			.getLog(WeekendDiscountAcceptanceTests.class);

	@Rule
	public ScreenshotOnFailureRule screenshot = new ScreenshotOnFailureRule();

	private WebDriver driver;

	@BeforeClass
	public static void classSetup() {
		LOG.info("Loading initial data dump...");
		DBUtil.loadDump();
	}

	@Before
	public void setup() throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		WebDriverKeeper.getInstance().setDriver(driver);
	}

	@After
	public void tearDown() {
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//		driver.close();
	}

	@Test
	public void testWeekendDiscountEnabled() {

		// set date to 4th weekend
		Date weekendDate = DateFactory.createDate(2016, 7, 24, 23, 49, 3);
		DBUtil.setTestTime(weekendDate);

		// get the home page
		HomePage homePage = HomePage.navigateTo(driver);
		
		// make sure that cart is empty at the beginning
		Assert.assertEquals("Cart should be empty at the beginning", 0,
				homePage.goToCart().getNofObjectsInCart());

		// go to the sauces
		homePage = HomePage.navigateTo(driver);
		HotSaucesPage hotSaucesPage = homePage.jumpToHotSauces();

		// and pick one
		SauceDetailPage saucePage = hotSaucesPage
				.sauceDayOfTheDeadHabaneroDetails();

		// now buy this one
		saucePage.buySauce();

		// jump back to the home page
		homePage = HomePage.navigateTo(driver);

		// now go to the cart
		CartPage cart = homePage.goToCart();

		// and check that there is 1 product in the cart.
		Assert.assertEquals("Now should have 1 product in cart", 1,
				cart.getNofObjectsInCart());

		// Check the Price of the Sauce
		Assert.assertEquals("Price of the Sauce must be $3.49", "$3.49",
				cart.getSubtotal());
	}

	@Test
	public void testWeekendDiscountDisabled() {

		// set date to monday after 4th weekend
		Date weekendDate = DateFactory.createDate(2016, 7, 25, 0, 0, 1);
		DBUtil.setTestTime(weekendDate);

		// get the home page
		HomePage homePage = HomePage.navigateTo(driver);

		// make sure that cart is empty at the beginning
		Assert.assertEquals("Cart should be empty at the beginning", 0,
				homePage.goToCart().getNofObjectsInCart());

		// go to the sauces
		homePage = HomePage.navigateTo(driver);
		HotSaucesPage hotSaucesPage = homePage.jumpToHotSauces();

		// and pick one
		SauceDetailPage saucePage = hotSaucesPage
				.sauceDayOfTheDeadHabaneroDetails();

		// now buy this one
		saucePage.buySauce();

		// jump back to the home page
		homePage = HomePage.navigateTo(driver);

		// now go to the cart
		CartPage cart = homePage.goToCart();

		// and check that there is 1 product in the cart.
		Assert.assertEquals("Now should have 1 product in cart", 1,
				cart.getNofObjectsInCart());

		// Check the Price of the Sauce
		Assert.assertEquals("Price of the Sauce must be $6.99", "$6.99",
				cart.getSubtotal());
	}

}
