package ch.testing.selenium.weekenddiscount.pageobjects;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

	private static final Log LOG = LogFactory.getLog(HomePage.class);

	@FindBy(id="products")
	private WebElement listOfShownProducts;


	public HomePage(WebDriver driver) {
		super(driver);

		if (!(driver.getTitle().startsWith("Broadleaf Demo"))) {
			throw new IllegalStateException("This is not the home page");
		}
		LOG.debug("HomePage created successfully");
	}

	public static HomePage navigateTo(WebDriver driver) {
		driver.get(BASE_URL);
		return PageFactory.initElements(driver, HomePage.class);
	}

	public int getNofProductsShown() {
		List<WebElement> rows = listOfShownProducts.findElements(By.xpath("li"));
		return rows.size();
		
	}


}
