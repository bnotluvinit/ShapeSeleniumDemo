package shapeways.seleniumdemo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import shapeways.selenium.util.DataProperties;



public class ProductDetail 
{
	private FirefoxDriver driver;
	
	

	
	public static ProductDetail navigateTo(FirefoxDriver driver) {
		//URL TapHandleURL= new URL(DataProperties.getProperty("TapHandleURL"));
		
		
	
		driver.get("http://www.shapeways.com/model/835204/taphandle.html");
		return PageFactory.initElements(driver, ProductDetail.class);
	}




	public static ProductDetail Login(FirefoxDriver driver) {
		String shapeways_username = DataProperties.getProperty("username");
		String shapeways_password = DataProperties.getProperty("password");

		
		
		

		ProductDetail login = ProductDetail.navigateTo(driver);
		driver.findElementByXPath(".//*[@id='commentsSignInLink']").click();

		
		//Uses login info
		Actions builder = new Actions(driver);
		builder.sendKeys(shapeways_username).sendKeys(Keys.TAB)
				.sendKeys(shapeways_password).sendKeys(Keys.TAB)
				.sendKeys(Keys.TAB).sendKeys(Keys.SPACE).build().perform();


		
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		
		Actions builder_back = new Actions(driver);
		builder_back.sendKeys("p").build().perform();

		return PageFactory.initElements(driver, ProductDetail.class);
	}





	}


	
