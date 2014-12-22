package shapeways.seleniumdemo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import shapeways.seleniumdemo.ProductDetail;

public class ProductDetailDisplayTest {

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
	public void TestTaphandleTitle() {
		// Open Shapeways TapHandle page

		ProductDetail taphandle = ProductDetail.navigateTo(driver);

		// Find Product title
		String product_title = driver.findElementByClassName("product-title-header").getText();
		//Verify the title of page is TapHandle
		Assert.assertEquals(product_title, "TapHandle");
	}

	@Test
	public void TestTaphandleImage() {
		//Verify first TapHandleImage is loaded
		ProductDetail taphandle = ProductDetail.navigateTo(driver);
		
		String taphandle_image = driver.findElementByXPath(
				".//*[@id='slideshow-big']/center/img").getAttribute("src");
		Assert.assertEquals(taphandle_image, "http://images1.sw-cdn.net/model/picture/625x465_835204_5857454_1408794841.jpg");
	}

	@Test
	public void TestChangeTaphandleImage() {
		// Click 2nd TapHandle image
		// Validate that it is now center main image

		driver.get("http://www.shapeways.com/model/835204/taphandle.html");
		driver.findElementByXPath(".//*[@id='film-strip']/a[2]/center/img")
				.click();

		String taphandle_image2 = driver.findElementByXPath(
				".//*[@id='slideshow-big']/center/img").getAttribute("src");
		Assert.assertEquals(taphandle_image2,
				"http://images3.sw-cdn.net/model/picture/625x465_835204_714245_1355847178.jpg");

	}

	@Test
	public void TestMoveFilmStrip() {
		// Select image at end of filmstrip
		// Validate that it is center main image

		driver.get("http://www.shapeways.com/model/835204/taphandle.html");
		driver.findElementByClassName("slideshow-right-arrow").click();
		driver.findElementByTagName("center").click();
	
		String taphandle_gif = driver.findElementByTagName("iframe")
				.getAttribute("src");
		System.out.println(taphandle_gif);
		Assert.assertEquals(taphandle_gif,
				"http://images1.sw-cdn.net/3dviewer?model=835204&v=0");

	}
}
