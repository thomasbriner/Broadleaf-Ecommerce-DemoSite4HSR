package ch.testing.selenium.weekenddiscount.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ch.testing.selenium.weekenddiscount.Constants;

public class Page implements Constants {

	WebDriver driver;

	@FindBy(xpath = "//a[contains(text(),'The Heat Clinic Home')]")
	protected WebElement logo;

	@FindBy(xpath = "//nav//a[contains(@href,'/hot-sauces')]")
	private WebElement hotSaucesButton;

	@FindBy(xpath = "//*[@id='cartLink']//*[@class='headerCartItemsCount']")
	private WebElement nofObjectsInCart;

	@FindBy(id = "cartLink")
	private WebElement cartLink;
	
	@FindBy(xpath = "//*[@id='subtotal']")
	private WebElement chartSubtotal;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage clickLogo() {
		logo.click();
		return PageFactory.initElements(driver, HomePage.class);

	}

	public HotSaucesPage jumpToHotSauces() {
		hotSaucesButton.click();
		return PageFactory.initElements(driver, HotSaucesPage.class);
	}

	public CartPage goToCart() {
		cartLink.click();
		return PageFactory.initElements(driver, CartPage.class);
	}

	public int getNofObjectsInCart() {
		String nofObjects = nofObjectsInCart.getText();
		return Integer.parseInt(nofObjects);
	}
	
	public String getSubtotal(){
		String subtotal = chartSubtotal.getText();
		return subtotal;
	}
	

}
