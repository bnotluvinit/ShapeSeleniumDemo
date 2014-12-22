package shapeways.seleniumdemo;


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import shapeways.seleniumdemo.ProductDetail;

public class ProductDetailAddProductTest {

	private FirefoxDriver driver;

	@Before
	public void setupSelenium() {
		driver = new FirefoxDriver();
	}

	@After
	public void closeSelenium() {
		driver.close();
		driver.quit();
	}

	@Test
	public void TestAddProductFavorites() {
		// Login using username/password
		ProductDetail product = ProductDetail.Login(driver);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Actions builder = new Actions(driver);
		builder.sendKeys("p").click().build().perform();
		

		// Add TapHandle to WishList
		// driver.findElementByLinkText("Favorite").click();
		driver.findElementByXPath(".//*[@id='favoritesLink']/span").click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Go to Favorites Page
		driver.get("https://www.shapeways.com/wishlist/?listName=favorites");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Verify TapHandle is on Favorites
		String taphandle_favorites = driver.findElementByXPath(
				".//*[@id='product-box-835204']/div/div[1]/a/img")
				.getAttribute("src");

		Assert.assertEquals(
				taphandle_favorites,
				"https://images4.sw-cdn.net/model/picture/290x218_835204_5857454_1408794841.jpg");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Remove from favorites

		driver.findElementByXPath(
				".//*[@id='product-box-835204']/div/div[2]/div[4]/div[1]/a")
				.click();

	}

	@Test
	public void TestAddProductWishList() {
		// Login using username/password
		ProductDetail product = ProductDetail.Login(driver);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Actions builder = new Actions(driver);
		builder.sendKeys("p").click().build().perform();
		

		// Add TapHandle to WishList

		driver.findElementByXPath(".//*[@id='wishlistLink']/span").click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Go to Favorites Page
		driver.get("https://www.shapeways.com/wishlist/?listName=wishlist");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Verify TapHandle is on Favorites
		String taphandle_wishlist = driver.findElementByXPath(
				".//*[@id='product-box-835204']/div/div[1]/a/img")
				.getAttribute("src");
		Assert.assertEquals(
				taphandle_wishlist,
				"https://images4.sw-cdn.net/model/picture/290x218_835204_5857454_1408794841.jpg");

		// Remove from favorites

		driver.findElementByXPath(
				".//*[@id='product-box-835204']/div/div[2]/div[4]/div[1]/a")
				.click();

	}
}