package shapeways.seleniumdemo;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import shapeways.selenium.util.DataProperties;
import shapeways.seleniumdemo.ProductDetail;

public class ProductDetailDiscussionTest {

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
	public void testNotLoggedIn() {
		// Test to make sure comment cannot be added without being logged in

		ProductDetail comment = ProductDetail.navigateTo(driver);
		String comment_prompt = driver.findElementByXPath(
				".//*[@id='commentsSignInLink']").getText();
		Assert.assertEquals(comment_prompt, "Sign In");
	}

	@Test
	public void testLogin() {
		// Selenium has trouble recognizing the SignIn Window
		// Fancybox
		// Test to login to Shapeways site, and leave comment
		ProductDetail product = ProductDetail.Login(driver);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Actions builder = new Actions(driver);
		builder.sendKeys("p").click().build().perform();
		
		String comment_box = driver
				.findElementByXPath(".//*[@id='commentBox']").getAttribute(
						"name");
		// Verify that user is logged in, by validating comment box is active
		Assert.assertEquals(comment_box, "commentText");

	}
	
	@Test
	public void testLoginandComment(){
		
		//Login using username/password
		ProductDetail product = ProductDetail.Login(driver);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Actions builder = new Actions(driver);
		builder.sendKeys("p").click().build().perform();
		
		
		//Find comment box and write comment
		driver.findElementByXPath(".//*[@id='commentBox']").sendKeys(DataProperties.getProperty("comment_text"));
		
		driver.findElementByXPath(".//*[@id='submitComment']").click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Find last comment written and get comment text
		String comment_num = driver.findElementByXPath(".//*[@id='commentsContainer']/a[1]").getAttribute("name");
		
		
		String comment_text = driver.findElementByXPath("//*[@id='" + comment_num + "']/div[2]/div[2]/div").getText();
		
		Assert.assertEquals(comment_text, DataProperties.getProperty("comment_text"));
	
	}
	@Test
	public void testLoginandReply(){
		//Login using username/password
		
		ProductDetail product = ProductDetail.Login(driver);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Actions builder = new Actions(driver);
		builder.sendKeys("p").click().build().perform();
		

		//Find staged comment to reply to
		driver.findElementByXPath(".//*[@id='reply-comment-79854']/a").click();
		
		driver.findElementByXPath(".//*[@id='commentBox']").sendKeys(DataProperties.getProperty("reply_text"));
		driver.findElementByXPath(".//*[@id='submitComment']").click();

		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String reply_num = driver.findElementByXPath(".//*[@id='commentsContainer']/a[1]").getAttribute("name");
		String reply_text = driver.findElementByXPath("//*[@id='" + reply_num + "']/div[2]/div[2]/div").getText();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(reply_text, "@bnotluvinit " + DataProperties.getProperty("reply_text"));

		
		
	}
}
