package ch.testing.selenium.weekenddiscount.pageobjects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotSaucesPage extends Page {

	private static final Log LOG = LogFactory.getLog(HotSaucesPage.class);
	
	@FindBy(xpath = "//a[contains(@href,'day_of_the_dead_habanero_hot_sauce')]")
	private WebElement habaneroHotSauce;

	
	public HotSaucesPage(WebDriver driver) {
		super(driver);

		if (!(driver.getTitle().startsWith("Hot Sauces"))) {
			throw new IllegalStateException("This is not the home page");
		}
		LOG.debug("HotSauces Page created successfully");
	}
	
	public SauceDetailPage sauceDayOfTheDeadHabaneroDetails () {
		habaneroHotSauce.click();
		return PageFactory.initElements(driver, SauceDetailPage.class);

	}

}
