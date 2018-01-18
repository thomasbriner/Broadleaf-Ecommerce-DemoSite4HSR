package ch.testing.selenium.weekenddiscount.pageobjects;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDetailPage extends Page {

	private static final Log LOG = LogFactory.getLog(SauceDetailPage.class);

	@FindBy(id = "mainProductToCart")
	private WebElement buttonBuyNow;

	public SauceDetailPage(WebDriver driver) {
		super(driver);

		LOG.debug("HotSauces Detail Page created successfully");
	}

	public void buySauce() {
		WebElement nofItemsInCart = driver
				.findElement(By
						.xpath("//*[@id='cartLink']//*[@class='headerCartItemsCount']"));
		int nofItemsBefore = Integer.parseInt(nofItemsInCart.getText());

		buttonBuyNow.click();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.pollingEvery(1, TimeUnit.SECONDS);
		WebElement newNofItemsInCart = driver
				.findElement(By
						.xpath("//*[@id='cartLink']//*[@class='headerCartItemsCount']"));
		wait.until(ExpectedConditions.textToBePresentInElement(
				newNofItemsInCart, "" + (nofItemsBefore + 1)));

	}

}
