/*
 * HSR Hochschule fuer Technik Rapperswil
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * 
 * Thomas Briner, thomas.briner@gmail.com
 */
package ch.testing.selenium.weekenddiscount.tests;

import java.io.IOException;
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

/**
 * The Class HeatClinicSmokeTests. This class implements some smoke tests that
 * might be used as test entry criteria for further tests.
 */
public class WeekendDiscountSmokeTests implements Constants{

	private static final Log LOG = LogFactory
			.getLog(WeekendDiscountSmokeTests.class);

	@Rule
	public ScreenshotOnFailureRule screenshot = new ScreenshotOnFailureRule();

	private WebDriver driver;

	/*
	 * No modifications of the db in these tests. Therefore it is enough to
	 * restore the test data only once for all test methods of this class.
	 */
	@BeforeClass
	public static void classSetup() {
		DBUtil.loadDump();
	}

	@Before
	public void setup() throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverKeeper.getInstance().setDriver(driver);
	}

	@After
	public void tearDown() {
		// problems with timing: if driver is closed too soon, test fails :-(
		// driver.close();
	}

	@Test
	public void testLoadHomePage() {

		// get the home page
		HomePage homePage = HomePage.navigateTo(driver);

		// and make sure some sauces are show
		Assert.assertTrue("Some products should be shown",
				homePage.getNofProductsShown() > 0);
	}

	@Test
	public void testAddItemToCart() {

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

	}

}
